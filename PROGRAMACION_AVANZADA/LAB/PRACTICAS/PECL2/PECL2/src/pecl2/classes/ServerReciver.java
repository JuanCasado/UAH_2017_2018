/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecl2.classes;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import pecl2.screens.PECL1;

/**
 *
 * @author mr.blissfulgrin
 */
public class ServerReciver extends Thread
{
    private final Socket conexion;
    private final DataInputStream input;
    private String [] mensaje;
    private int id;
    private final Server server;
    private final AtomicBoolean control;
    private final PECL1 state;
    
    public ServerReciver (Socket conexion, int x,Server server,PECL1 state) throws IOException
    {
        input = new DataInputStream(conexion.getInputStream());
        this.id = x;
        this .conexion = conexion;
        this.server = server;
        this.control = new AtomicBoolean(true);
        
        this.state = state;
    }
    /**
     * Este hilo escucha los datos que los clientes envían al servidor cada vez que pulsan un botón
     */
    @Override
    public void run ()
    {
        boolean [] data = new boolean [11];
        while (control.get())
        {
            try 
            {
                mensaje = input.readUTF().split(" ");
                if (mensaje[0].equals("X"))
                {
                    this.parar();
                }
                else
                {
                    for (int x = 0; x < mensaje.length; x++)
                    {
                        data [x] = mensaje [x].equals("1");
                    }
                    state.setButtonState(data);
                }
            }
            catch (IOException e){}
        }
    }
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    /**
     * Cierra el canal de comunicación y finaliza el hilo
     */
    public void end ()
    {
        control.set(false);
        try
        {
            input.close();
            conexion.close();
        } catch (IOException ex){} 
    }
    
    /**
     * Notifica a un remisor que debe cerrarse, el emisor hermanado con este recerptor (Misma ID)
     */
    public void parar ()
    {
        server.eliminar(id);
    }
}
