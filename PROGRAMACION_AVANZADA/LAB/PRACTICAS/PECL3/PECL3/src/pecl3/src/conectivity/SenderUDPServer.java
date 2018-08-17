
package pecl3.src.conectivity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import pecl3.src.Log;

/**
 *
 * @author mr.blissfulgrin
 */
public class SenderUDPServer extends Thread
{
    private final InetAddress add;
    private final int port;
    private DatagramSocket socket;
    private final Log log;
    
    public SenderUDPServer (InetAddress add, int port,Log log)
    {
        this.add = add;
        this.port = port;
        int tries = 0;
        this.log = log;
        while (tries < 5 && socket == null)
        {
            try
            {
                this.socket = socket = new DatagramSocket((int)(Math.random()*3000+2000));
            } 
            catch (SocketException ex)
            {
                tries++;
            }
        }
    }

    /**
     * Si el socket UDP fue creado correctamente intenta enviar sobre él un paquete hacia la dirección de quien lo enviara
     */
    @Override
    public void run ()
    {
        if (socket!= null)
        {
            log.write(this.toString(), "DATA SENDABLE");
            byte[] buf = new byte[] {'N'};
            {
                try
                {
                    try
                    {
                        DatagramPacket paquete = new DatagramPacket(buf, buf.length, add, port);
                        socket.send(paquete);
                        socket.close();
                        log.write(this.toString(), "DATA SENT");
                    } 
                    catch (UnknownHostException ex)
                    {
                        log.write(this.toString(),"NO HOST");
                    }
                }
                catch (IOException e)
                {
                    log.write(this.toString(),"NO IO");
                }
            }  
        }
        else
            log.write(this.toString(), "DATA NOT SENDABLE");
    } 
    
        
    @Override
    public String toString ()
    {
        return "Server UDP SENDER";
    }
}
