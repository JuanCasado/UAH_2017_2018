
package pecl3.src;

import java.util.concurrent.atomic.AtomicBoolean;
import pecl3.screens.FuelStationInterface;

/**
 *
 * @author mr.blissfulgrin
 */
public class Recollector extends Thread
{
    private final FuelStation fuelStation;
    private final FuelStationInterface fuelStationInterface;
    private final AtomicBoolean control;
    private final Log log;
    private final int time;
    private final boolean write;
    
    public Recollector (FuelStation fuelStation, FuelStationInterface fuelStationInterface, Log log)
    {
        this.fuelStation = fuelStation;
        this.fuelStationInterface = fuelStationInterface;
        this.control = new AtomicBoolean(true);
        this.log = log;
        this.time = 500;
        this.write = false;
    }
    /**
     * Finaliza la ejecución del hilo
     */
    public void end ()
    {
        control.set(false);
        this.interrupt();
    }
    
    /**
     * Este hilo implementa el modo pasivo de refresco de la interface gráfica
     * Realiza un productor consumidor entre la gasolinera y la interface gráfica
     * utilizando las colas de recogida de datos como buffers de modo que la 
     * gasolinera no tenga que actualizarla de forma activa
     */
    @Override
    public void run ()
    {
        log.write(this.toString(), "Started ");
        while (control.get())
        {
            if (write)
                log.write(this.toString(), "Screen updated ");
            fuelStationInterface.write(log.retriveLog());
            fuelStationInterface.reset();
            fuelStationInterface.setData(fuelStation.getStatus().split(" "));
            try
            {
                Thread.sleep (time);
            }
            catch (InterruptedException e)
            {
                log.write(this.toString(), "Interrupted ");
            }
        }
        log.write(this.toString(), "Ended ");
    }
    
    @Override
    public String toString ()
    {
        return "Recollector";
    }
}
