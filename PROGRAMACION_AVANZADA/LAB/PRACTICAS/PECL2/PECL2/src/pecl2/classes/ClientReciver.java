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
import pecl2.screens.PECL2;

/**
 *
 * @author mr.blissfulgrin
 */
public class ClientReciver extends Thread
{
    private final Socket client;
    private DataInputStream input;
    private final AtomicBoolean control;
    private final PECL2 state;
    
    public ClientReciver (Socket socket, PECL2 state)
    {
        this.client = socket;
        this.control = new AtomicBoolean (true);
        this.state = state;
        try 
        {
            input = new DataInputStream(client.getInputStream());
        }
        catch (IOException e){}
    }
    
    /**
     * Hilo que espera a la recepción de datos, los procesa y se los comunica a la interface gráfica
     */
    @Override
    public void run ()
    {
        String [] ms;
        boolean [] data = new boolean [11];
        while(control.get())
        {
            try 
            {
                ms = input.readUTF().split(" ");
                switch (ms[0])
                {
                    case "X":
                        state.end();
                        break;
                    default:
                        for (int x = 0; x < ms.length; x++)
                        {
                            data [x] = ms [x].equals("1");
                        }   
                        state.setState(data);
                        break;
                }
            }
            catch (IOException e){}
        }
    }
    
    /**
     * Cierra los canales de comunicación y finaliza el hilo
     */
    public void parar ()
    {
        try
        {
            control.set(false);
            input.close();
            client.close();
        } 
        catch (IOException ex){}
    }
}
