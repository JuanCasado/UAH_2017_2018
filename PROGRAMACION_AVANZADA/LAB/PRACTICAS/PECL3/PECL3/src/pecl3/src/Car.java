
package pecl3.src;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import pecl3.screens.FuelStationInterface;

/**
 *
 * @author mr.blissfulgrin
 */
public class Car extends Thread
{
    private final int number;
    private final Log log;
    private final PriorityBlockingQueue <Pump> freePump;    //PRIORITY LIST
    private final BlockingQueue <Pump> caredPump;           //QUEUE
    private final BlockingQueue <Car> car;                  //QUEUE
    private final FuelStationInterface visual;
    private final StopPoint stop;
    private final Controller controller;
    private boolean running;
    private Thread currentThread;
    private final Semaphore semaphore;
    private final LinkedBlockingQueue <Car> carBuffer;
    private final ConcurrentHashMap <Integer,String> pumpBuffer;
    private final boolean debugState;
    
    public Car (int number, Log log, PriorityBlockingQueue <Pump> freePump, LinkedBlockingQueue <Pump> caredPump, 
                LinkedBlockingQueue <Car> car, FuelStationInterface visual, StopPoint stop, Controller controller, 
                Semaphore semaphore,LinkedBlockingQueue <Car> carBuffer,ConcurrentHashMap <Integer,String> pumpBuffer,boolean debugState)
    {
        this.number = number;
        this.log = log;
        this.freePump = freePump;
        this.caredPump = caredPump;
        this.car = car;
        this.visual = visual;
        this.stop = stop;
        this.controller = controller;
        this.running = false;
        this.semaphore = semaphore;
        this.carBuffer = carBuffer;
        this.pumpBuffer = pumpBuffer;
        this.debugState = debugState;
    }
    
    /**
     * Funcionamiento de un coche:
     *  · Toma un surtidor de la cola de surtidores libres ordenado por el número de surtidor de menor a mayor
     *  · Si no hubiera un surtidor espera en orden de llegada de los coches a que uno sea liberado
     *  · Entra en el surtidor y lo coloca en la cola de surtidores con coche
     *  · Espera a que llegue un trabajador y le sirva
     *  · Sale de la gasolinera
     */
    @Override
    public void run()
    {
        currentThread = Thread.currentThread();
        this.running = true;
        boolean repeatSleep;
        boolean retakePump;
        log.write(this.toString(), "Ha llegado a la gasolinera");
        if (debugState)
            visual.carArrivesFuelStation(number);
        
        do
        {
            retakePump = false;
            //Whait for a free Pump
            do 
            {
                retakePump = false;
                try
                {
                    log.write(this.toString(), "Entra al parking");
                    if (semaphore.availablePermits() == 0)
                    {
                        log.write(this.toString(), "Los surtidores están llenos");
                    }
                    if (!carBuffer.contains(this))
                        carBuffer.add(this);
                    semaphore.acquire();
                    carBuffer.remove(this);
                    log.write(this.toString(), "Hay un surtidor libre");

                } 
                catch (InterruptedException ex)
                {
                    log.write(this.toString(), "INTERRUPTION WHILE WAITING FOR FREE PUM");
                    switch (controller.getState())
                    {
                        case Controller.CONTINUE:
                            log.write(this.toString(), "MISS MATCH");
                            break;
                        case Controller.PAUSE:
                            log.write(this.toString(), "CAR PAUSED");
                            stop.look();
                            retakePump = true;
                            log.write(this.toString(), "CAR RESUMED");
                            break;
                        case Controller.END:
                            log.write(this.toString(), "CAR FORCED END");
                            break;
                        default:
                            log.write(this.toString(), "CONTROLLER BADLY USED");
                            break;
                    }
                }
            }
            while ((freePump.isEmpty() || retakePump) && !controller.getState().equals(Controller.END));
            

            
            if (!controller.getState().equals(Controller.END))
            {

                try
                {
                    //Adquire a Pump
                    Pump pump = freePump.remove();
                    pumpBuffer.put(pump.getNumber(), this.getID());
                    log.write(this.toString(), "Surtidor libre adquirido");
                    if (debugState)
                    {
                        visual.carLeavesFuelStationQueue(number);
                        visual.carReachesPump(number, pump.getNumber());
                    }

                    pump.addCar(this);
                    caredPump.add(pump);
                    synchronized (caredPump)
                    {
                        caredPump.notify();
                    }

                    log.write(this.toString(), "Espera en el " + pump.toString());

                     //Wait for a Worker
                     do
                     {
                        repeatSleep = false;
                        try
                        {
                            pump.waitCar();
                            if (debugState)
                                visual.carLeavesPump(number, pump.getNumber());
                            pumpBuffer.remove(pump.getNumber());
                            log.write(this.toString(), "Ha terminado de repostar");
                        }
                        catch (InterruptedException i)
                        {
                            log.write(this.toString(), "INTERRUPTION WHILE WAITING FOR A WORKER");
                            switch (controller.getState())
                            {
                                case Controller.CONTINUE:
                                    log.write(this.toString(), "MISS MATCH");
                                    break;
                                case Controller.PAUSE:
                                    log.write(this.toString(), "CAR PAUSED");
                                    repeatSleep = true;
                                    stop.look();
                                    log.write(this.toString(), "CAR RESUMED");
                                    break;
                                case Controller.END:
                                    log.write(this.toString(), "CAR FORCED END");
                                    break;
                                default:
                                    log.write(this.toString(), "CONTROLLER BADLY USED");
                                    break;
                            }
                        }
                     }
                     while (repeatSleep);

                }
                catch (NoSuchElementException n)
                {
                    log.write(this.toString(), "FREE_PUMP WAS EMPTY");
                    retakePump = !controller.getState().equals(Controller.END);
                }
            }
        }
        while(retakePump);
        //The Car lefts the FuelStation
        stop.look();
        car.remove(this);
        log.write(this.toString(), "Abandona la gasolinera");
        this.running = false;
    }
     
    /**
     * Permite interrumpir al Coche cuando el pool de hilos lo esté ejecutando
     */
    public synchronized void interruptT ()
    {
        if (running)
        {
            currentThread.interrupt();
        }
    }
    
    /**
     * Permite realizar un notify sobre el cCoche cuando esté siendo ejecutado por el pool
     */
    public void notifyT ()
    { 
        log.write(this.toString(), "NOTIFY DONE");
        synchronized (currentThread)
        {
            if (running)
            {
                currentThread.notify();
            }
        }
    }
    
    /**
     * Retrieves the Car ID (used for connection)
     * @return String ID
     */
    public String getID ()
    {
        return String.valueOf(number);
    }
    /**
     * Retrieves the textual representation of the car (used for graphics)
     * @return String CAR ID
     */
    @Override
    public String toString()
    {
        return "CAR " + number;
    }
}
