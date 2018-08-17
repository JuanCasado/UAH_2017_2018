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
import pecl2.screens.PECL2;

/**
 *
 * @author mr.blissfulgrin
 */
public class ClientSender extends Thread
{
    private final Socket client;
    private DataOutputStream output;
    private final AtomicBoolean control;
    private final PECL2 state;
    private final Semaphore semaphore;
    
    public ClientSender (Socket socket, PECL2 state)
    {
        this.client = socket;
        this.control = new AtomicBoolean (true);
        this.state = state;
        this.semaphore = new Semaphore(0);
        try 
        {
            output = new DataOutputStream(client.getOutputStream());
        }
        catch (IOException e){}
    }
    
    /**
     * Espera una llamada a send() para enviar los datos del estado de los botones de PECL2
     */
    @Override
    public void run ()
    {
        String  mensaje;
        boolean [] data;
        while (control.get())
        {
            try 
            {
                semaphore.acquire(1);
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
            }
            catch (IOException | InterruptedException e){}
        }
    }
    
    /**
     * Produce el envío de datos
     */
    public void send ()
    {
        semaphore.release(1);
    }
    
    /**
     * Finaliza la comunicación y termina el hilo
     */
    public void parar ()
    {
        try
        {
            if (!state.isEnded())
                output.writeUTF("X");
            control.set(false);
            semaphore.release(1);
            output.close();
            client.close();
        } 
        catch (IOException ex){}
    }
}
