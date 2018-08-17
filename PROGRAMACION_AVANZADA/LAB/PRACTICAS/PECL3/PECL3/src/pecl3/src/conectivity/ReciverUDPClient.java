
package pecl3.src.conectivity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import pecl3.screens.VLoad;

/**
 *
 * @author mr.blissfulgrin
 */
public class ReciverUDPClient extends Thread
{
    private final DatagramSocket socket;
    private byte[] IP;
    private boolean timeout;
    
    public ReciverUDPClient (DatagramSocket socket)
    {
        timeout = false;
        this.IP = null;
        this.socket = socket;
        try
        {
            this.socket.setSoTimeout(1500);
        } 
        catch (SocketException ex){}
        try
        {
            timeout = this.socket.getSoTimeout() != 0;
        } 
        catch (SocketException ex){}
    }

    /**
     * Escuchamos durante un máximo de tiempo a recibir un paquete del Servidor
     * Si obtenemos de este un paquete tendremos su IP, pero de no ser así no podremos saberla
     * Es muy probable no poder obtener la IP del Servidor ya que existen múltipleas factores que nos impidan hacerlo
     */
    @Override
    public void run ()
    {  
        if (timeout)
        {
            try
            {
                byte[] buf = new byte[1];
                try
                {
                    DatagramPacket paquete = new DatagramPacket(buf, buf.length);

                    socket.receive(paquete);
                    IP = paquete.getAddress().getAddress();
                    socket.close();

                } catch (UnknownHostException | SocketTimeoutException ex)
                {
                    VLoad vLoad = new VLoad();
                    vLoad.go("Host failed, Retrying...");
                }
            }
            catch (IOException e)
            {
                VLoad vLoad = new VLoad();
                vLoad.go("IO failed, Retrying...");
            }
        }
        else
        {
            VLoad vLoad = new VLoad();
            vLoad.go("Timeout failed, Retrying...");
        }
    } 
    
    /**
     * Retorna la IP del cliente si logró obtenerla
     * @return De no obtenerse la IP valdrá null
     */
    public byte[] getIP ()
    {
        return IP;
    }
}
