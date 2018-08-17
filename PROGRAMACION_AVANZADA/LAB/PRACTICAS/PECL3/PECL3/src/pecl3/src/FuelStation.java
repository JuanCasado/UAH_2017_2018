
package pecl3.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import pecl3.src.conectivity.Server;
import pecl3.screens.FuelStationInterface;

/**
 *
 * @author mr.blissfulgrin
 */
public class FuelStation extends Thread
{
    public static final int PUMPS = 8;
    public final int WORKERS;
    public final int CARS;
    public static final int MAX_THEADS = 28;
    private final PriorityBlockingQueue <Pump> freePump;  //PRIORITY LIST
    private final LinkedBlockingQueue <Pump> caredPump;   //QUEUE
    private final LinkedBlockingQueue <Car> car;          //QUEUE
    private final Worker[] worker;                        //QUEUE
    private final FuelStationInterface visual;
    private final Log log;
    private final ExecutorService executor;
    private final StopPoint stop;
    private int timeToWait;
    private Controller controller;
    private boolean running;
    private Thread currentThread;
    private final CountDownLatch count;
    private final CountDownLatch end;
    private final CountDownLatch start;
    private final Semaphore semaphore;
    private final LinkedBlockingQueue <Car> carBuffer;
    private final LinkedBlockingQueue <Worker> workerBuffer;
    private final ConcurrentHashMap <Integer,String> pumpBufferCar;
    private final ConcurrentHashMap <Integer,String> pumpBufferWorker;
    private final Server server;
    private final Recollector recollector;
    
    /*
    * Estos atributos indican el modo de transferencia de los datos de las clases de trabajo a la interface gráfica:
    * Si alguno de debugger está a true la clase correspondiente mostrará sus datos de forma activa
    * Si todos están en false el hilo Recollector actualizará los gráficos
    */
    private final boolean [] debugState;
    private final boolean finalState;
    
    /**
     * Crea una simulación de una gasolinera
     * Crea los trabajadores y coches necesarios así como los surtidores y el servidor para permitir conexiones remotas
     * @param visual a JFrame implementing FuelStationInterface
     * @param workers Número de trabajadores
     * @param cars  Número de coches
     */
    public FuelStation (FuelStationInterface visual, int workers, int cars)
    {
        debugState = new boolean [] 
        {
            false,  //Cars          0
            false,  //Workers       1
            false,  //Log           2
        };
        finalState = !(debugState [0] || debugState [1] || debugState [2]);
        
        this.visual = visual;
        this.log = new Log(visual,debugState[2]);
        this.recollector = new Recollector(this,visual,log);
        this.stop = new StopPoint(log);
        this.executor = Executors.newFixedThreadPool(MAX_THEADS);
        this.WORKERS = workers;
        this.CARS = cars;
        this.controller = new Controller();
        controller.setState(Controller.PAUSE);
        this.running = false;
        this.count = new CountDownLatch(workers);
        this.end = new CountDownLatch(2);
        this.start = new CountDownLatch(1);
        this.semaphore = new Semaphore (8,true);
        timeToWait = 5500;
        
        this.carBuffer = new LinkedBlockingQueue <>();
        this.workerBuffer = new LinkedBlockingQueue <>();
        this.pumpBufferCar = new ConcurrentHashMap <>();
        this.pumpBufferWorker = new ConcurrentHashMap <>();
        //Create the Pumps
        freePump = new PriorityBlockingQueue<>();
        caredPump = new LinkedBlockingQueue<>();
        for (int i = 0; i < PUMPS; i++)
        {
            try
            {
                freePump.offer(new Pump(i+1,log, stop, controller));  
            }
            catch(ClassCastException c)
            {
                log.write(this.toString(), "PUMP NOT SORTABLE");
            }
        }
        
        //Create Queue of incoming Cars
        car = new LinkedBlockingQueue<>();
        for (int i = 0; i < CARS; i++)
        {
            Car c = new Car (i+1,log, freePump, caredPump,car, visual, stop, controller,semaphore,carBuffer,pumpBufferCar,debugState[0]);
            car.add(c);
        }
        
        //Create a Queue of Workers
        worker = new Worker[WORKERS];
        for (int i = 0; i < WORKERS; i++)
        {
            worker[i] = new Worker(i+1,log, freePump, caredPump, visual, stop, controller,count,semaphore,workerBuffer,pumpBufferWorker,debugState[1]);
        }   
        
        //Create Server
        server = new Server(log,this,end);
        log.write(this.toString(), "FUEL STATION READY");
    }
    
    /**
     * Este hilo inicia el servidor, los trabajadores, y el pool de hilos de coches.
     * Posteriormente espera a que terminen y lo cierra todo se forma correcta.
     */
    @Override
    public void run ()
    {
        //Inicia el servidor
        server.start();
        currentThread = Thread.currentThread();
        this.running = true;
        if (finalState)
            recollector.start();

        log.write(this.toString(), "WAITING FOR USER INPUT...");
        try 
        {
            start.await();
        }
        catch (InterruptedException e)
        {
            log.write(this.toString(), "INITIAL WAIT INTERRUPTED");
        }

        log.write(this.toString(), "FUEL STATION SIMULATION STARTS");
        
        if (controller.getState().equals(Controller.CONTINUE))
        {
            //Start Workers
            for (Worker w : worker)
            {
                w.start();
            }

            //Start Cars
            boolean repeatSleep;
            int elapsedTime;
            for (Car c : car)
            {
                do
                {
                    repeatSleep = false;
                    elapsedTime = (int)(Math.random()*timeToWait+500);
                    log.write(this.toString(), c.toString()+" llegará en "+elapsedTime+" ms");
                    try
                    {
                        //Wait for arrival
                        Thread.sleep(elapsedTime);
                        try
                        {
                            if (!controller.getState().equals(Controller.END))
                                this.useEXE().execute(c);
                        }
                        catch (RejectedExecutionException e)
                        {
                            log.write(this.toString(), "CAR REJECTED "+c.toString());
                        }
                    }
                    catch(InterruptedException ex)
                    {
                        log.write(this.toString(),"CAR CREATION SLEEP INTERRUPTED");
                        switch (controller.getState())
                        {
                            case Controller.CONTINUE:
                                log.write(this.toString(), "MISS MATCH");
                                repeatSleep = true;
                                break;
                            case Controller.PAUSE:
                                log.write(this.toString(), "CAR PRODUCTION PAUSED");
                                stop.look();
                                repeatSleep = true;
                                log.write(this.toString(), "CAR PRODUCTION RESUMED");
                                break;
                            case Controller.END:
                                log.write(this.toString(), "FORCE TERMINATION");
                                this.car.clear();
                                break;
                            default:
                                log.write(this.toString(), "CONTROLLER BADLY USED");
                                break;
                        }
                    }
                }
                while (repeatSleep);
            }

            log.write(this.toString(), "ALL CARS CREATED");

            //Wait for Cars
            this.useEXE().shutdown();

            do
            {
                repeatSleep = false;
                try
                {
                    this.useEXE().awaitTermination(20, TimeUnit.MINUTES);
                } 
                catch (InterruptedException ex)
                {
                    log.write(this.toString(), "INTERRUPTION WHILE WAITING FOR CARS TO END");
                    if (controller.getState().equals(Controller.CONTINUE))
                    {
                        log.write(this.toString(), "MISS MATCH");
                    }
                    else
                    {
                        log.write(this.toString(), "NOT POSSIBLE");
                    }
                    repeatSleep = true;
                }
            }
            while (repeatSleep);

            log.write(this.toString(), "ALL CARS ENDED, WORKERS GET INTERRUPT");
            controller.setState(Controller.END);
            //Wait for Workers
            for(Worker w : worker)
            {
                w.interruptT();
            }

            do
            {
                repeatSleep = false;
                try
                {
                    count.await();
                } 
                catch (InterruptedException ex)
                {
                    log.write(this.toString(), "INTERRUPTION WHILE WAITING FOR WORKERS TO END");
                    if (controller.getState().equals(Controller.CONTINUE))
                    {
                        log.write(this.toString(), "MISS MATCH");
                    }
                    else
                    {
                        log.write(this.toString(), "NOT POSSIBLE");
                    }
                    repeatSleep = true;

                } 
            }
            while(repeatSleep);
        }   
        //END SERVER
        server.end();
        try
        {
            server.join();
        } 
        catch (InterruptedException ex)
        {
            log.write(this.toString(), "SERVER JOIN INTERRUPTED");
        }
        log.write(this.toString(), "FUEL STATION SIMULATION ENDED CORRECTLY");
        visual.endSimulation();
        end.countDown();
        this.running = false;
    }
    
    /**
     * Pausa la simulación
     */
    public synchronized void pauseSimulation()
    {
        log.write(this.toString(), "SIMULATION PAUSED");
        
        controller.setState(Controller.PAUSE);
        stop.close();
        
        this.interruptT();
        for (Worker w : worker)
        {
            w.interruptT();
        }
        
        stop.close();
        if (debugState[0])
            visual.barrera(false);
    }
    
    /**
     * Reanuda la simulación
     */
    public synchronized void resumeSimulation()
    {
        controller.setState(Controller.CONTINUE);
        if (start.getCount()==1)
        {
            start.countDown();
        }
        else
        {
            log.write(this.toString(), "SIMULATION RESUMED");
            stop.open();
        }
        if (debugState[0])
            visual.barrera(true);
    }
    
    /**
     * Termina la simulación
     */
    public void end()
    {
        if (this.running)
        {
            log.write(this.toString(), "BRUTE FORCE CLOSING");
            controller.setState(Controller.END);
            stop.open();
            this.interruptT();
            executor.shutdownNow();
            if (finalState)
            {
                recollector.end();
            }
            try
            {
                end.await();
            } 
            catch (InterruptedException ex)
            {
                log.write(this.toString(), "BAD SYNCRONIZATION END");
            }
        }
        log.save();
    }
    
    /**
     * Da el número de trabajadores
     * @return int WORKERS
     */
    public int getWorkers()
    {
        return WORKERS;
    }
    /**
     * Da el número de coches
     * @return int CARS
     */
    public int getCars()
    {
        return CARS;
    }
    
    /**
     * Permite modificar el tiempo de espera entre los coches para entrar en la gasolinera.
     * No modifica el tiempo de forma absoluta, siempre se esperará de forma aleatoria 
     * sobre el valor pasado con un mínimo de 500ms añadidos sobre el aleatorio
     * @param time 
     */
    public synchronized void setTime (int time)
    {
        if (time > 0)
            this.timeToWait = time;
    }
    
    /**
     * Retorna el executor
     * @return 
     */
    private synchronized ExecutorService useEXE ()
    {
        return this.executor;
    }
    
    /**
     * Permite interrumpir el hilo
     * @return Boolean Indica si la interrupción se ha realizado
     */
    public synchronized boolean interruptT()
    {
        if (running)
        {
            currentThread.interrupt();
        }
        return running;
    }
    
    /**
     * Se utiliza para el log
     * @return FuelStation
     */
    @Override
    public String toString()
    {
        return "FuelStation";
    }

    /**
     * Se resume la posición de todos los coches en la gasolinera en una cadena de datos
     * Procesa los buffers de estado de la gasolinera para obtener esta información
     * Es utilizada para mostrar los datos si se utiliza el modo se ejecución pasivo
     * @return Cadena de caracteres que describe el estado de la gasolinera
     */
    public String getStatus()
    {
        String data = "";
        ArrayList <String> bufferC = new ArrayList <>();
        HashMap <Integer,String> pumpsC = new HashMap <>();
        ArrayList <String> bufferW = new ArrayList <>();
        HashMap <Integer,String> pumpsW = new HashMap <>();
        
        carBuffer.forEach((c) ->
        {
            bufferC.add(c.getID());
        });
        workerBuffer.forEach((w) ->
        {
            bufferW.add(w.getID());
        });
        pumpBufferCar.entrySet().forEach((p) ->
        {
            pumpsC.put(p.getKey(), p.getValue());
        });
        pumpBufferWorker.entrySet().forEach((p) ->
        {
            pumpsW.put(p.getKey(), p.getValue());
        });
        
        pumpsC.values().forEach((p) ->
        {
            bufferC.remove(p);
        });
        pumpsW.values().forEach((p) ->
        {
            bufferW.remove(p);
        });

        data += (!controller.getState().equals(Controller.PAUSE))? "1 ":"0 ";
        data += bufferC.size() + " ";
        data = bufferC.stream().map((c) -> c + " ").reduce(data, String::concat);
        data += bufferW.size() + " ";
        data = bufferW.stream().map((w) -> w + " ").reduce(data, String::concat);

        for (int x = 1; x <= PUMPS; x++)
        {
            if (pumpsC.containsKey(x))
            {
                data += pumpsC.get(x) + " ";
            }
            else
            {
                data += "N ";
            }
            if (pumpsW.containsKey(x))
            {
                data += pumpsW.get(x) + " ";
            }
            else
            {
                data += "N ";
            }
        }
        
        return data; 
    }
    
    /**
     * Permite configurar el estado parado o contiar a partir de un boolean
     * @param state true=resume, false=pause
     */
    public void barrera (boolean state)
    {
        if (state)
        {
            this.resumeSimulation();
        }
        else
        {
            this.pauseSimulation();
        }
    }
}
