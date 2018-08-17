/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecl2.classes;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import pecl2.screens.PECL1;

/**
 *
 * @author mr.blissfulgrin
 */
public class Server extends Thread
{
    private ServerSocket server;
    private Socket conexion;
    private final AtomicBoolean control;
    private final ExecutorService executor;
    private final ConcurrentHashMap <Integer,Conexion> services;

    private final JLabel label;
    private final PECL1 state;
    
    private boolean print;
    private String log;
    private final JTextArea stream;
    
    public Server (JLabel label, PECL1 state, JTextArea stream)
    {
        control = new AtomicBoolean(true);
        executor = Executors.newFixedThreadPool(20);
        services = new ConcurrentHashMap <> ();
        this.label = label;
        this.state = state;
        this.print = false;
        this.log = "";
        this.stream = stream;
    }
    
    /**
     * Escuchamos nuevas conexiones de clientes y creamos los objetos necesarios para gestionarlas.
     * La comunicación será full duplex controlado el envío de datos y su recepción por hilos distintos.
     * A cada cliente se le asigna una identidad aleatoria que lo identifica para poder comunicarnos con cada uno de forma individual
     */
    @Override
    public void run()
    {
        try
        {
            int id;
            server = new ServerSocket(4444);
            label.setText("Connect to: " + InetAddress.getLocalHost().getHostAddress());
            while (control.get())
            { 
                do
                {
                    id = (int)(Math.random()*(Integer.MAX_VALUE-1)+1);
                }
                while (services.containsKey(id));
                conexion = server.accept();  
                write("NEW CLIENT "+id);
                ServerSender s = new ServerSender (conexion,id,this,state);
                ServerReciver r = new ServerReciver (conexion,id,this,state);
                Conexion c = new Conexion (r,s);
                services.put(id, c);
                executor.execute(r);
                executor.execute(s);
            }
        }
        catch (IOException e)
        {
            label.setText("SERVER ERROR");
        }
    }
    
    /**
    * Enviamos los datos del estado de los botones a los clientes conectados
    */
    public synchronized void send ()
    {
        services.values().forEach((c) ->
        {
            c.send();
        });
    }
    
    /**
    * Enviamos un texto a los clientes que será decodificado para controlarlos desde el Servidor
    * X -> cerrar el cliente
    * @param s Texto a enviar a los clientes
    */
    public synchronized void send (String s)
    {
        services.values().forEach((c) ->
        {
            c.send(s);
        });
    }
    
    /**
     * Elimina un cliente con la identidad dada
     * @param id Identidad del cliente que se desea eliminar, es asiganada de forma aleatoria por el servidor
     */
    public synchronized void eliminar (int id)
    {
        if (services.containsKey(id))
        {
            services.get(id).end();
            services.remove(id);
            write("END CONNECTION "+id);
        }
        else
        {
            System.out.println("ERROR");
        }
    }
    
    /**
     * Finaliza la ejecición del servidor cerrando los streams y sockets abiertos así como finalizando las tareas
     */
    public synchronized void end ()
    {
        try
        {
            services.keySet().forEach((c) ->
            {
                services.get(c).end();
                write("END CONNECTION " + c);
            });
            control.set(false);
            server.close();
            executor.shutdown();
            executor.awaitTermination(30, TimeUnit.SECONDS);
            executor.shutdownNow();
        } 
        catch (IOException | InterruptedException ex)
        {
            label.setText("SERVER COULDN'T STOP");
        }
    }
    
    /*
     * Métodos para controlar la escitura de datos desde el código de la PECL1
     */
    public void stopPrinting()
    {
        print = false;
    } 
    public void beguinPrinting()
    {
        print = true;
        stream.setText(log);
    }
    public String getLog ()
    {
        return log;
    }
    /**
     * We update the log string and if needed the UI
     * Also we update the log of the buffer
     * @param newTxt 
     */
    public void write (String newTxt)
    {
        log += newTxt+"\n";
        if (print)
            stream.setText(log);
    }
    
    @Override
    public String toString()
    {
        return "Server";
    }
}
