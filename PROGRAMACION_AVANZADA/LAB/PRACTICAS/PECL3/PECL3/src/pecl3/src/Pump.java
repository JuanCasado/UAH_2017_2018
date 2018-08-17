
package pecl3.src;

import java.util.concurrent.Semaphore;


/**
 *
 * @author mr.blissfulgrin
 */
public class Pump extends Thread implements Comparable<Pump>
{
    private final int number;
    private Car car;
    private Worker worker;
    private final Log log;
    private final StopPoint stop;
    private final Controller controller;
    private boolean running;
    private Thread currentThread;
    private final Semaphore semaphore;
    
    public Pump (int number, Log log, StopPoint stop, Controller controller)
    {
        this.number = number;
        this.log = log;
        this.stop = stop;
        this.controller = controller;
        this.running = false;
        this.semaphore =new Semaphore (0,true);
    }
    
    /**
     * Un coche enetra al Surtidor
     * @param car 
     */
    public synchronized void addCar (Car car)
    {
        this.car = car;
    }
    
    /**
     * Un trabajador entra al surtidor
     * @param worker 
     */
    public synchronized void addWorker(Worker worker)
    {
        this.worker = worker;
    }
    
    /**
     * Obtener el coche que está en el surtidor (para la interface gráfiuca)
     * @return 
     */
    public synchronized String getCar()
    {
        if (car != null)
            return car.toString();
        else 
            return "null";
    }
    
    /**
     * Obtener el coche que está en el surtidor (para la comunicación)
     * @return 
     */
    public synchronized String getCarID()
    {
        if (car != null)
            return car.getID();
        else
            return "N";
    }
    
    /**
     * Obtener el trabajador que está en el surtidor (para la comunicación)
     * @return 
     */
    public synchronized String getWorkerID()
    {
        if (worker != null)
            return worker.getID();
        else
            return "N";
    }
    
    /**
     * Obtener el número de surtidor (para la comunicación)
     * @return 
     */
    public synchronized int getNumber ()
    {
        return this.number;
    }
    
    /**
     * Permite al los coches esperar en el surtidor
     * @throws InterruptedException Admite interrupción de la espera con control esxterno
     */
    public void waitCar () throws InterruptedException
    {    
        semaphore.acquire();
    }
    
    /**
     * Vacia el surtidor
     */
    public synchronized void clear ()
    {    
        worker = null;
        car = null;
    }
    
    /**
     * Permite al los trabajadores esperar en el surtidor
     * @throws InterruptedException Admite interrupción de la espera con control esxterno
     */
    public void waitWorker () throws InterruptedException
    {        
        semaphore.acquire();
    }
    
    /**
     * La vida de este hilo consiste exclusivamente de un sleep y notificaqciones hacia el log
     * Se implementa esto así para evitar la relación directa entre el coche y el trabajador en todo momento
     */
    @Override
    public void run ()
    {    
        currentThread = Thread.currentThread();
        running = true;
        int time;
        boolean repeatSleep;
        do
        {
            repeatSleep = false;
            try
            {
                time = (int) (Math.random()*4000+4000);
                log.write(this.toString(), car.toString() + " Será servido por " + worker.toString() + " en " + time + "ms");

                Thread.sleep(time);
                
                log.write(this.toString(), car.toString() + " Fue servido por " + worker.toString() + " en " + time + "ms");
                clear();
            }
            catch(InterruptedException e)
            {
                log.write(this.toString(), "SLEEP PUMP INTERRUPTED");
                switch (controller.getState())
                {
                    case Controller.CONTINUE:
                        log.write(this.toString(), "MISS MATCH");
                        repeatSleep = true;
                        break;
                    case Controller.PAUSE:
                        log.write(this.toString(), "PUMP PAUSED");
                        car.interruptT();
                        stop.look();
                        repeatSleep = true;
                        log.write(this.toString(), "PUMP RESUMED");
                        break;
                    case Controller.END:
                        log.write(this.toString(), "PUMP FORCED TERMINATION");
                        car.interruptT();
                        break;
                    default:
                        log.write(this.toString(), "CONTROLLER BADLY USED");
                        break;
                }
            }       
        }
        while (repeatSleep);
        semaphore.release(2);
    }
    
    /**
     * Permite interrumpir los surtidores pues serán ejecutados dentro de un pool de hilos
     */
    public synchronized void interruptT()
    {
        if (running)
        {
            currentThread.interrupt();
        }
    }
    
    /**
     * Ordena los surtidores por su número
     * @param other
     * @return 
     */
    @Override
    public int compareTo(Pump other)
    {
        if (getNumber() < other.getNumber())
            return -1;
        else if (getNumber() == other.getNumber())
            return 0;
        else
            return 1;
    }
    
    @Override
    public String toString()
    {
        return "Pump "+ number;
    }
}
