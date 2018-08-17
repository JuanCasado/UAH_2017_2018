/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecl3.src;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mr.blissfulgrin
 */
public class StopPoint
{
    private boolean close;
    private final Lock lock;
    private final Condition stop;
    private final Log log;
    
    public StopPoint (Log log)
    {
        this.close = false;
        this.log = log;
        this.lock = new ReentrantLock();
        this.stop = lock.newCondition();
    }
    
    /**
     * If this object was closed before when we use this method well be kept waiting until the open method is called.
     * If it was open we do nothing and continue the normal execution path
     */
    public void look()
    {
        try
        {
            lock.lock();
            while(close)
            {
                try
                {
                    stop.await();
                } 
                catch(InterruptedException i)
                { 
                    log.write(this.toString(),"INTERRUPTED");
                }
            }
        }
        finally
        {
            lock.unlock();
        }
    }

    /**
     * This makes the thread available to continue the execution whenever the look method is called
     */
    public void open()
    {
        try
        {
            lock.lock();
            close=false;
            stop.signalAll();
        }
        finally
        {
            lock.unlock();
        }
    }
   
    /**
     * This makes the thread stop until we reopen whenever the look method is called
     */
    public void close()
    {
        try
        {
            lock.lock();
            close=true;
            stop.signalAll();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    @Override
    public String toString()
    {
        return "StopPoint";
    }
}
