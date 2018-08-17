/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas_examen;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author mr.blissfulgrin
 */
public class Semaforo extends Thread
{
    private AtomicBoolean verde;
    private final String id;
    private final Exchanger <AtomicBoolean> ex;
    private final Semaphore sem;
    
    public Semaforo (Boolean verde, String id, Exchanger ex, Semaphore sem)
    {
        this.verde = new AtomicBoolean(verde);
        this.id = id;
        this.ex = ex;
        this.sem = sem;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(10000);
                verde = ex.exchange(verde);
                System.out.println("------------------------------------");
            } 
            catch (InterruptedException e){}
            if (verde.get())
            {
                sem.release(sem.getQueueLength());
            }
        }
    }
    
    
    public Boolean isVerde ()
    {
        return verde.get();
    }
    
    @Override
    public String toString ()
    {
        return "Semaforo " + id;
    }
}
