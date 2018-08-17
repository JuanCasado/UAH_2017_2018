/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto_conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author mr.blissfulgrin
 */
public class ReciverUDP extends Thread
{
    AtomicBoolean control = new AtomicBoolean(true);
    SenderUPD send;
    public ReciverUDP (SenderUPD send)
    {
        this.send = send;
    }
    @Override
    public void run ()
    {
        
        try
        {
            DatagramSocket socket = new DatagramSocket(3333);
            byte[] buf = new byte[1];
            try
            {
                DatagramPacket paquete = new DatagramPacket(buf, buf.length);
                do
                {
                    socket.receive(paquete);
                    System.out.println("RECIVED");
                    if (paquete.getData()[0]=='N')
                    {
                        System.out.println(paquete.getAddress());
                        this.end();
                        send.end();
                    }
                }
                while (control.get());
            } catch (UnknownHostException ex)
            {
                System.out.println("NO HOST");
            }
        }
        catch (IOException e)
        {
            System.out.println("NO IO");
        }
    } 
    
    public void end ()
    {
        this.interrupt();
        control.set(false);
    }
}
