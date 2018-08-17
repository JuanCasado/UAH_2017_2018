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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mr.blissfulgrin
 */
public class SenderUPD extends Thread
{
    AtomicBoolean control = new AtomicBoolean(true);
    @Override
    public void run ()
    {
        InetAddress destino;
        byte[] buf = new byte[] {'N'};
        byte [] IP = new byte [] {(byte)(255),(byte)(255),(byte)(255),(byte)(255)};
        try
        {
            DatagramSocket socket = new DatagramSocket(5555);
            System.out.println("SOCK OK");
            try
            {
                destino = InetAddress.getByAddress(IP);
                System.out.println("DEST OK");
                DatagramPacket paquete = new DatagramPacket(buf, buf.length, destino, 3333);
                do
                {
                    socket.send(paquete);
                    System.out.println("SENT");
                    try
                    {
                        Thread.sleep(500);
                    } catch (InterruptedException ex)
                    {
                        System.out.println("EX");
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
