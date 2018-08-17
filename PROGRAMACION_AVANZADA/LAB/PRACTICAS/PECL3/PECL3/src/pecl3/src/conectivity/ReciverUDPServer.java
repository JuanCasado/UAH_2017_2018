
package pecl3.src.conectivity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import pecl3.src.Log;

/**
 *
 * @author mr.blissfulgrin
 */
public class ReciverUDPServer extends Thread
{
    private final AtomicBoolean control = new AtomicBoolean(true);
    private DatagramSocket socket;
    private final Log log;
    private final ExecutorService executor;
    
    public ReciverUDPServer (Log log)
    {
        this.log = log;
        this.executor = Executors.newSingleThreadExecutor();
    }
    
    /**
     * Escuchamos paquetes UDP y en el caso de recibir uno intentamos conectarnos con el emisor
     */
    @Override
    public void run ()
    {
        log.write(this.toString(), "UDP Server ON");
        while (control.get())
        {
            try
            {
                socket = new DatagramSocket(2222);
                byte[] buf = new byte[1];
                try
                {
                    DatagramPacket paquete = new DatagramPacket(buf, buf.length);
                    do
                    {
                        socket.receive(paquete);
                        log.write(this.toString(), "Reception "+paquete.getAddress().toString());
                        SenderUDPServer send = new SenderUDPServer (paquete.getAddress(),paquete.getPort(), log);
                        executor.execute(send);
                    }
                    while (control.get());
                } catch (UnknownHostException ex)
                {
                    log.write(this.toString(), "NO HOST");
                }
            }
            catch (IOException e)
            {
                log.write(this.toString(), "NO IO");
            }
        }
    } 
    
    /**
     * Finalizar el Servidor UDP
     */
    public void end ()
    {
        this.interrupt();
        executor.shutdown();
        try
        {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } 
        catch (InterruptedException ex)
        {
            log.write(this.toString(), "EXECUTOR AWAIT INTERRUPTED");
        }
        control.set(false);
        socket.close();
        log.write(this.toString(), "UDP CLOSED");
    }
    
    @Override
    public String toString()
    {
        return "Server UDP RECIVER";
    }
}
