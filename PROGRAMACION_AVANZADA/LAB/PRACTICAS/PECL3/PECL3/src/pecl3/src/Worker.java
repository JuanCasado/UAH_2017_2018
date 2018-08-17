
package pecl3.src;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import pecl3.screens.FuelStationInterface;

/**
 *
 * @author mr.blissfulgrin
 */
public class Worker extends Thread
{
    private final int number;
    private final Log log;
    private final PriorityBlockingQueue <Pump> freePump;  //PRIORITY LIST
    private final LinkedBlockingQueue <Pump> caredPump;   //QUEUE
    private final FuelStationInterface visual;
    private final StopPoint stop;
    private final Controller controller;
    private boolean running;
    private Thread currentThread;
    private final ExecutorService executor;
    private final CountDownLatch count;
    private final Semaphore semaphore;
    private final LinkedBlockingQueue <Worker> workerBuffer;
    private final ConcurrentHashMap <Integer,String> pumpBuffer;
    private final boolean debugState;
    
    public Worker(int number, Log log, PriorityBlockingQueue <Pump> freePump, LinkedBlockingQueue <Pump> caredPump, 
                    FuelStationInterface visual, StopPoint stop, Controller controller,CountDownLatch barrier, 
                    Semaphore semaphore,LinkedBlockingQueue <Worker> workerBuffer, ConcurrentHashMap <Integer,String> pumpBuffer, boolean debugState)
    {
        this.number = number;
        this.log = log;
        this.freePump = freePump;
        this.caredPump = caredPump;
        this.visual = visual;
        this.stop = stop;
        this.controller = controller;
        this.running = false;
        this.executor = Executors.newSingleThreadExecutor();
        this.count = barrier;
        this.semaphore =semaphore;
        this.workerBuffer = workerBuffer;
        this.pumpBuffer = pumpBuffer;
        this.debugState = debugState;
    }
    
    /**
     * La ejecución de este hilo sigue las siguentes fases:
     *  · Toma un surtidor de la cola de surtidores con coche ordenada por el orden de entrada a la cola
     *  · Si no hubiera un surtidor espera en orden a que uno sea liberado
     *  · Entra en el surtidor y lo ejecuta desde el pool de hilos que tiene el trabajador
     *  · Cuando es despertado introduce el surtidor que había tomado en la cola de surtidores vacíos
     *  · Vuelve a realizar todos los pasos hasta que no haya más coches que servir
     */
    @Override
    public void run()
    {
        currentThread = Thread.currentThread();
        running = true;
        Pump pump;
        boolean repeatSleep;
        boolean retakePump;
        
        while (!controller.getState().equals(Controller.END))
        {
            do
            {
                retakePump = false;
                //Whait for a caredPump
                while (caredPump.isEmpty() && !controller.getState().equals(Controller.END))
                {
                    synchronized (caredPump)
                    {
                        try
                        {
                            log.write(this.toString(), "Esperando por un coche");
                            if (!workerBuffer.contains(this))
                                workerBuffer.add(this);
                            caredPump.wait();
                            workerBuffer.remove(this);
                            log.write(this.toString(), "Hay coches para servir"); 
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
                                    log.write(this.toString(), "WORKER PAUSED");
                                    stop.look();
                                    break;
                                case Controller.END:
                                    log.write(this.toString(), "WORKER FORCED TERMINATION");
                                    freePump.clear();
                                    caredPump.clear();
                                    executor.shutdownNow();
                                    break;
                                default:
                                    log.write(this.toString(), "CONTROLLER BADLY USED");
                                    break;
                            }
                        }
                    }
                }

                try
                {
                    //Adquire a Pump
                    pump = caredPump.remove();
                    pumpBuffer.put(pump.getNumber(), this.getID());
                    pump.addWorker(this);

                    if (debugState)
                        visual.workerStartFueling(number, pump.getNumber());
                    log.write(this.toString(), "Comienza a servir en "+ pump.toString() + " a " + pump.getCar());

                    try
                    {
                        executor.execute(pump);
                    }
                    catch (RejectedExecutionException e)
                    {
                        log.write(this.toString(), "REJECTED "+pump.toString());
                    }
                    //Wait for the Pump to refuel

                    do
                    {
                        repeatSleep = false;
                        try
                        {
                            pump.waitWorker();
                            pumpBuffer.remove(pump.getNumber());
                            log.write(this.toString(), "Ha terminado de servir");
                            if (debugState)
                                visual.workerEndsFueling(number, pump.getNumber());
                            //Free Pump
                            try
                            {
                                pump = new Pump(pump.getNumber(),log,stop,controller);
                                freePump.offer(pump);
                                synchronized (semaphore)
                                {
                                    semaphore.release();
                                }
                            } 
                            catch(ClassCastException c)
                            {
                                log.write(this.toString(), "PUMP NOT SORTABLE");
                            } 
                        }
                        catch (InterruptedException ex) 
                        {
                            log.write(this.toString(), "WORKER INTERRUPTED");
                            switch (controller.getState())
                            {
                                case Controller.CONTINUE:
                                    log.write(this.toString(), "MISS MATCH");
                                    repeatSleep = true;
                                    break;
                                case Controller.PAUSE:
                                    log.write(this.toString(), "WORKER PAUSED");
                                    pump.interruptT();
                                    stop.look();
                                    repeatSleep = true;
                                    log.write(this.toString(), "WORKER RESUMED");
                                    break;
                                case Controller.END:
                                    log.write(this.toString(), "WORKER FORCED TERMINATION");
                                    executor.shutdownNow();
                                    break;
                                default:
                                    log.write(this.toString(), "CONTROLLER BADLY USED");
                                    break;
                            }
                        }
                    }
                    while(repeatSleep);

                }
                catch (NoSuchElementException | RejectedExecutionException | NullPointerException e)
                {
                    log.write(this.toString(), "CARED_PUMP WAS EMPTY");
                    retakePump = !controller.getState().equals(Controller.END);
                }
            }
            while (retakePump);
        }
        try
        {
            executor.awaitTermination(20, TimeUnit.MINUTES);
        } 
        catch (InterruptedException ex)
        {
            log.write(this.toString(), "INTERRUPTED WHILE WAITING FOR EXECUTOR TO END");
        }
        log.write(this.toString(), "Ha terminado");
        
        count.countDown();
        running = false;
    }
    
    /**
     * Permite interrumpir al hilo
     * Este hilo podría ejecutarse en un pool y seguir siendo interrumpido
     */
    public synchronized void interruptT()
    {
        if (running)
        {
            currentThread.interrupt();
        }
    }
    
    /**
     * Da el ID del trabajador, es utilizado por la comunicación y la actualización pasiva
     * @return ID
     */
    public String getID ()
    {
        return String.valueOf(number);
    }
    /**
     * Da el ID del trabajador, es utilizado por la interface gráfica
     * @return WORKER ID
     */
    @Override
    public String toString()
    {
        return "Worker " + number;
    }
}
