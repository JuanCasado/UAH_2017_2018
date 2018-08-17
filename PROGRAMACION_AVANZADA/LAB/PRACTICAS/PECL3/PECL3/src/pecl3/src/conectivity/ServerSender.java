
package pecl3.src.conectivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import pecl3.src.FuelStation;
import pecl3.src.Log;

/**
 *
 * @author mr.blissfulgrin
 */
public class ServerSender extends Thread
{
    private final ConcurrentHashMap <Integer, DataOutputStream> stream;
    private final FuelStation fuelStation; 
    private final Log log;
    private final AtomicBoolean control;
    private Thread thread;
    private final Server server;
    private final int time;
    private final boolean write;
    
    public ServerSender(FuelStation fuelStation, Log log, Server server)
    {
        stream = new ConcurrentHashMap <>();
        this.fuelStation = fuelStation;
        this.log = log;
        this.control = new AtomicBoolean (true);
        this.server = server;
        this.time = 1000;
        this.write = false;
    }
    
    /**
     * Añade un nuevo cliente a la lista de aquellos a los que hay que dar sevicio de datos
     * @param socket socket a partir del cual crear el canal de envío
     * @param id identidad única para el socket
     * @param server nos permite retroalimental la comunicación para cerrar las conexiones.
     */
    public void add (Socket socket, int id, Server server)
    {
        try 
        {
            this.stream.put(id, new DataOutputStream(socket.getOutputStream()));
        }
        catch (IOException e)
        {
            log.write(this.toString(), "Error creating OutputStream "+id);
        }
    }
    
    /**
     * Cada segundo toma los datos procesados de los buffers de datos de la gasolinera 
     * y los envia a todos los crientes adscritos al servicio
     */
    @Override
    public void run ()
    {
        thread = Thread.currentThread();
        String data;
        while(control.get())
        {
            if (!stream.isEmpty())
            {
                data = fuelStation.getStatus();
                for (int key : stream.keySet())
                {
                    try
                    {
                        stream.get(key).writeUTF(data+key);
                        if (write)
                            log.write(this.toString(), "DATA SENT TO "+key);
                    }
                    catch (IOException e)
                    {
                        log.write(this.toString(),"ERROR SENDING DATA TO " + key);
                    }
                }
                try 
                {
                    Thread.sleep(time);
                }
                catch (InterruptedException e)
                {
                    log.write(this.toString(), "Sender Interrupted");
                }
            }
        }
        log.write(this.toString(), "SENDER END");
    } 
    
    /**
     * Cierra una conexión única se utiliza cuando un cliete se desconecta
     * @param toRemove 
     */    
    public void remove (int toRemove)
    {
        try
        {
            if (stream.get(toRemove)!=null)
            {
                stream.get(toRemove).close();
                log.write(this.toString(), "NOTYFIED SENDER CLOSED" + toRemove);
            }
            else
            {
                throw new IOException();
            }
        }
        catch (IOException e)
        {
            log.write(this.toString(),"NOTYFIED REMOVING SENDER STREAM IN " + toRemove);
        }
        server.toRemove(toRemove);
        stream.remove(toRemove);
    }
    
    /**
     * Finaliza de forma segura al em isor y borra las referencias del receptor en el servidor
     */
    public void end ()
    {
        control.set(false);
        thread.interrupt();
        stream.keySet().forEach((key) ->
        {
            try
            {
                stream.get(key).writeUTF("X");
                log.write(this.toString(), "CLIENT CLOSED" + key);
            }
            catch (IOException e)
            {
                log.write(this.toString(),"CLIENT ALREADY CLOSED IN " + key);
            }
            try
            {
                stream.get(key).close();
                log.write(this.toString(), "SENDER CLOSED" + key);
            }
            catch (IOException e)
            {
                log.write(this.toString(),"ERROR CLOSING SENDER STREAM IN " + key);
            }
        });
        log.write(this.toString(), "SENDER CLOSED CORRECTLY");
    }
    
    /**
     * Indica el estado que uno de los clientes solicita sobre la gasolinera, pausa o resumen
     * @param state true = resume; false = pausa
     */
    public synchronized void barrera(boolean state)
    {
        fuelStation.barrera(state);
    }
    
    @Override
    public String toString()
    {
        return "ServerSender";
    }
}
