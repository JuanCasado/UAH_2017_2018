/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecl2.classes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import pecl2.screens.PECL1;

/**
 *
 * @author mr.blissfulgrin
 */
public class ServerSender extends Thread
{
    private final Socket conexion;
    private final DataOutputStream output; 
    private String mensaje;
    private int id;
    private final Server server;
    private final AtomicBoolean control;
    private final Semaphore semaphore;
    
    private final PECL1 state;
    
    public ServerSender (Socket conexion, int x,Server server, PECL1 state) throws IOException
    {
        output = new DataOutputStream(conexion.getOutputStream()); 
        this.id = x;
        this .conexion = conexion;
        this.server = server;
        this.control = new AtomicBoolean(true);
        this.semaphore = new Semaphore(0);
        this.state = state;
    }
    
    /**
     * Se envían datos al cliente enlazado a esta clase que describen el estado de los botones
     * El envío se produce a cada llamada de send()
     */
    @Override
    public void run ()
    {
        boolean [] data;

        while (control.get())
        {
            try
            {
                mensaje = "";
                data = state.getButtonState();
                for (boolean b : data)
                {
                    if (b)
                        mensaje += "1";
                    else
                        mensaje += "0";
                    mensaje += " ";
                }
                output.writeUTF(mensaje);

                semaphore.acquire(1);
            } 
            catch (InterruptedException | IOException ex){}
        }        
    }
    
    /**
     * Cada vez que se lanza este método se envían datos
     */
    public void send ()
    {
        semaphore.release(1);
    }
    
    /**
     * Permite el envío de datos concretos para controlar al cliente
     * @param mmss String de datos a enviar
     */
    public void send (String mmss)
    {
        try
        {
            output.writeUTF(mmss);
        } 
        catch (IOException ex){}
    }
    
    /**
     * Dota de identidad a la clase
     * @param id Identificador de la comunicación
     */
    public void setID(int id)
    {
        this.id = id;
    }
    
    /**
     * Finaliza la ejecición del hilo y cierra los sockets y streams utilizados
     */
    public void end ()
    {
        control.set(false);
        semaphore.release(1);
        try
        {
            output.close();
            conexion.close();
        } catch (IOException ex){}
    }
}
