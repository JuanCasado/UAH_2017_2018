
package pecl2.classes;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mr.blissfulgrin
 */
public class Stop
{
    private boolean close=false;
    private final Lock lock = new ReentrantLock();
    private final Condition stop = lock.newCondition();
    private final Buffer buffer;
    
    public Stop (Buffer buffer)
    {
        this.buffer = buffer;
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
                    buffer.write("ERROR --> STOP " + i.toString());
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
}
