
package pecl3.src.conectivity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import pecl3.screens.VLoad;

/**
 *
 * @author mr.blissfulgrin
 */
public class SenderUDPClient extends Thread
{
    private boolean control;
    private byte[] serverIP;
    private final ExecutorService executor;
    
    public SenderUDPClient ()
    {
        serverIP = null;
        control = true;
        this.executor = Executors.newSingleThreadExecutor();
    }
    
    /**
     * Intenta hasta tres veces conectarse con el Servidor UDP por broadcast
     * En caso de lograrlo de esta clase se podrá obtener la IP del servidor
     */
    @Override
    public void run ()
    {
        InetAddress destino;
        byte[] buf = new byte[] {'N'};
        byte [] IP = new byte [] {(byte)(255),(byte)(255),(byte)(255),(byte)(255)};
        int tries = 0;
        
        while (tries < 3 && control)
        {
            DatagramSocket socket;
            ReciverUDPClient reciver;
            try
            {
                socket = new DatagramSocket((int)(Math.random()*3000+2000));
                reciver = new ReciverUDPClient(socket);
                executor.execute(reciver);
                try
                {
                    destino = InetAddress.getByAddress(IP);
                    DatagramPacket paquete = new DatagramPacket(buf, buf.length, destino, 2222);
                    socket.send(paquete);
                    try
                    {
                        executor.awaitTermination(5, TimeUnit.SECONDS);
                        serverIP = reciver.getIP();
                        if (serverIP != null)
                            control = false;
                        socket.close();
                    } 
                    catch (InterruptedException ex)
                    {
                        VLoad vLoad = new VLoad();
                        vLoad.go("Server fail, Retrying...");
                    }
                } 
                catch (UnknownHostException ex)
                {
                    VLoad vLoad = new VLoad();
                    vLoad.go("Server fail, Retrying...");
                }
            }
            catch (IOException e)
            {
                VLoad vLoad = new VLoad();
                vLoad.go("Server fail, Retrying...");
            }
            tries ++;
        }
    }
    
    /**
     * Permite obtener la dirección IP del servidor
     * @return Valdrá null si la red no admite broadcast
     */
    public byte[] getAutoIP()
    {
        return serverIP;
    }
}
