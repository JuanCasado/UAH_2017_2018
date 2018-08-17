
package pecl3.src.conectivity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import pecl3.src.FuelStation;
import pecl3.src.Log;

/**
 *
 * @author mr.blissfulgrin
 */
public class Server extends Thread
{
    private ServerSocket server;
    private Socket socket;
    private final AtomicBoolean control;
    private final ExecutorService executor;
    private final ConcurrentHashMap <Integer,ServerReciver> services;
    private final Log log;
    private final ServerSender sender;
    private final CountDownLatch end;
    private final ReciverUDPServer udp;
    
    public Server (Log log, FuelStation fuelStation, CountDownLatch end)
    {
        control = new AtomicBoolean(true);
        executor = Executors.newFixedThreadPool(11);
        services = new ConcurrentHashMap <> ();
        this.log = log;
        this.end = end;
        try
        { 
            this.server = new ServerSocket(4444);
            log.write(this.toString(), "SERVER READY");
        }
        catch (IOException e)
        {
            log.write(this.toString(), "ERROR AL CREAR SERVIDOR");
        }
        sender = new ServerSender (fuelStation, log,this);
        try
        {
            executor.execute(sender);
        }
        catch (RejectedExecutionException e)
        {
            log.write(this.toString(), "ERROR AL CREAR SENDER");
        }
        udp = new ReciverUDPServer(log);
    }
    
    /**
     * Lanza un servidor UDP que atenderá en paralelo esas peticiones y se queda escuchando
     * a las peticiones TCP de conexión que le lleguen, cuando lo haga lanzará otros hilos que las controlen.
     */
    @Override
    public void run()
    {
        udp.start();
        if (server!=null)
        {
            int id;
            while (control.get())
            { 
                do
                {
                    id = (int)(Math.random()*(Integer.MAX_VALUE-1)+1);
                }
                while (services.contains(id));

                try
                { 
                    log.write(this.toString(), "WAITING FOR CLIENT "+id);
                    socket = server.accept();  
                    log.write(this.toString(), "NUEVO CLIENTE "+id);
                    ServerReciver r = new ServerReciver (socket,id,sender, log);
                    services.put(id,r);
                    sender.add(socket, id, this);
                    executor.execute(r);
                }
                catch (IOException | RejectedExecutionException e)
                {
                    log.write(this.toString(), "NO HUBO CLIENTE EN "+id);
                }
            }
        }
    }
    
    /**
     * Finaliza de forma segura al servidor UDP y al TCP
     */
    public synchronized void end ()
    {
        try
        {
            udp.end();
            log.write(this.toString(), "CLOSING SERVER");
            control.set(false);
            sender.end();
            services.keySet().forEach((reciver)->
            {
                services.get(reciver).end(false);
            });
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
            executor.shutdownNow();
            executor.awaitTermination(5, TimeUnit.SECONDS);
            if (server != null)
                server.close();
        } 
        catch (IOException | InterruptedException ex)
        {
            log.write(this.toString(),"ERROR AL TERMINAR ");
        }
        end.countDown();
        log.write(this.toString(), "SERVER CLOSED CORRECTLY");
        try
        {
            udp.join();
        } 
        catch (InterruptedException ex)
        {
            log.write(this.toString(), "UDP JOIN INTERRUPTED");
        }
    }
    
    /**
     * Elimina una conexión TCP de la lista de servicios
     * @param toRemove 
     */
    public synchronized void toRemove (int toRemove)
    {
        services.remove(toRemove);
    }
    
    @Override
    public String toString()
    {
        return "Server";
    }
}
