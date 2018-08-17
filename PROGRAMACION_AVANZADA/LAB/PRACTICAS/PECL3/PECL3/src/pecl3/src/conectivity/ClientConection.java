
package pecl3.src.conectivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import pecl3.screens.VClientMenu;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import pecl3.screens.FuelStationInterface;

/**
 *
 * @author mr.blissfulgrin
 */
public class ClientConection extends Thread
{
    private InetAddress add;
    private Socket socket;
    private FuelStationInterface clientInterface;
    private DataInputStream input;
    private DataOutputStream output;
    private final AtomicBoolean control;
    private int errors;
    
    public ClientConection ()
    {
        this.errors = 0;
        this.control = new AtomicBoolean (true);
    }
          
    /**
     * Nos indica si la dirección IP proporcionada es accesible desde nuestro equipo
     * @param IP Dirección a probar
     * @return Es o no accesible
     */
    public boolean conectable (byte [] IP)
    {
        try
        {
            add = InetAddress.getByAddress(IP);
            if (!add.isReachable(3000))
                return false;
        }
        catch (IOException e)
        {
            return false;
        }
        VClientMenu vClientMenu= new VClientMenu (this);
        vClientMenu.setVisible(true);
        return true;
    }
    
    /**
     * Inicia los Streams de comunicación a la vez que nos vincula con una interface gráfica
     * en la que mostrar los datos recibidos
     * @param clientInterface 
     */
    public void setDisplay (FuelStationInterface clientInterface)
    {
        this.clientInterface = clientInterface;
        try 
        {
            socket= new Socket(add,4444);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            clientInterface.write("CONEXION -- OK");
        }
        catch (IOException e)
        {
            clientInterface.write("ERRORR AL CREAR CONEXION");
        }
        this.start();
    }
    
    /**
     * El cliente escucha los datos del servidor y los muestra por pantalla
     * de recibir una "X" finaliza su ejecución
     */
    @Override
    public void run ()
    {
        if (socket != null)
        {
            String [] data; 
            while (control.get())
            {
                if (errors > 2)
                    this.end();
                try
                {
                    data = input.readUTF().split(" ");
                    clientInterface.reset();
                    errors = 0;
                    if (data[0].equals("X"))
                    {
                        this.end();
                    }
                    else
                    {
                        clientInterface.setData(data);
                        clientInterface.write("DATA RECIVED");
                    }
                }
                catch (IOException e)
                {
                    clientInterface.write("ERRORR READING DATA");
                    errors++;
                }
            }
        }
    }
    
    /**
     * Finaliza de forma segura la simulación y lo notifica al servidor
     */
    public void end ()
    {
        if (socket != null)
        {
            control.set(false);
            try
            {
                output.writeUTF("X");
                clientInterface.write("X SENT");

            }
            catch (IOException e)
            {
                clientInterface.write("SENDING DATA X");
            }
            try
            {
                input.close();
                output.close();
                socket.close();
                clientInterface.endSimulation();
                clientInterface.write("CONECTION CLOSED");
            }
            catch (IOException e)
            {
                clientInterface.write("ERROR CLOSING");
            }
        }
    }
    
    /**
     * Indica al Servidor que queremos pausar la simulación
     */
    public void pauseSimulation ()
    {
        try
        {
            output.writeUTF("P");
            clientInterface.write("P SENT");
        }
        catch (IOException e)
        {
            clientInterface.write("ERROR SENDING DATA P");
        }
    }
    
    /**
     * Indica al Servidor que queremos reanudar la simulación
     */
    public void resumeSimulation ()
    {
        try
        {
            output.writeUTF("R");
             clientInterface.write("R SENT");
        }
        catch (IOException e)
        {
             clientInterface.write("ERROR SENDING DATA R");
        }
    }
}
