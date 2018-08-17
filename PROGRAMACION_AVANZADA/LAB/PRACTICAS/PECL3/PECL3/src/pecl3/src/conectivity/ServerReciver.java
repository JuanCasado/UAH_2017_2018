
package pecl3.src.conectivity;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import pecl3.src.Log;

/**
 *
 * @author mr.blissfulgrin
 */
public class ServerReciver extends Thread
{
    private final ServerSender sender;
    private final int id;
    private DataInputStream stream;
    private final Log log;
    private final AtomicBoolean control;
    private final Socket socket;
    private int errors;
    
    public ServerReciver (Socket socket, int id,ServerSender sender, Log log)
    {
        this.sender = sender;
        this.id = id;
        this.log = log;
        this.control = new AtomicBoolean (true);
        this.socket = socket;
        this.errors = 0;
        try 
        {
            stream = new DataInputStream(socket.getInputStream());
        }
        catch (IOException e)
        {
            log.write(this.toString(), "Error creating InputStream "+id);
        }
    }
    
    /**
     * Escucha a los Clientes y decodifica la información que cada uno recibe
     * ya que cada conexión tiene una identidad podremos saber con qué cliente nos comunicamos
     */
    @Override
    public void run ()
    {
        String data = "";
        while (control.get())
        {
            if (errors > 2)
                this.end(true);
            try
            {
                data = stream.readUTF().trim();
                log.write(this.toString(), data + " RECIVED FROM " + id);
                errors = 0;
            }
            catch (IOException ex)
            {
                log.write(this.toString(), "CONECTION CLOSED");
                errors++;
            }
            switch (data)
            {
                case "X":
                    this.end(true);
                    break;
                case "P":
                    sender.barrera(false);
                    break;
                case "R":
                    sender.barrera(true);
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Cierra de forma segura al receptor TCP
     * @param notify Indica si avisar al emisor o no del borrado
     */
    public void end (boolean notify)
    {
        control.set(false);
        try
        {
            stream.close();
            log.write(this.toString(), "RECIVER CLOSED "+ id);
        }
        catch (IOException e)
        {
            log.write(this.toString(), "ERROR CLOSING "+ id);
        }
        if (notify)
        {
            sender.remove(id);
        }
        try
        {
            socket.close();
            log.write(this.toString(), "SOCKET CLOSED CORRECTLY "+id);
        }
        catch (IOException e)
        {
            log.write(this.toString(), "ERROR CLOSING "+ id);
        }
    }
    
    @Override
    public String toString()
    {
        return "ServerReciver "+id;
    }
}
