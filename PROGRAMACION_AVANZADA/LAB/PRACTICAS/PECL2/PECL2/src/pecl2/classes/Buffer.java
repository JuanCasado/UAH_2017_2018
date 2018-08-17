
package pecl2.classes;

import java.util.Queue;
import java.util.LinkedList ;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import pecl2.screens.PECL1;

/**
 *
 * @author mr.blissfulgrin
 */
public class Buffer
{
    private final Queue <Package> buffer;
    private int MAX;
    private final int MIN;
    private final int numberOfDeliveriesTotal;
    private int numberOfDeliveries;
    
    private String log = "";
    
    private final Lock lock;
    private final Condition full;
    private final Condition empty;
    
    private final JProgressBar bufferBar;
    private final JProgressBar bufferDerivate;
    private final JTextArea bufferTxt;
    private int changes;
    private int previous;
    private final JTextArea stream;
    private Boolean print;
    private int ended;
    private final PECL1 state;
    
    /**
     * 
     * @param MAX Amount of packets the buffer can store at a time
     * @param numberOfDeliveriesTotal Number of packets the producers are gonna produce in total
     * @param bufferTxt UI
     * @param bufferBar UI
     * @param bufferDerivate UI
     * @param stream  UI
     */
    public Buffer (int MAX, int numberOfDeliveriesTotal, JTextArea bufferTxt, JProgressBar bufferBar, JProgressBar bufferDerivate, JTextArea stream, PECL1 state)
    {
        this.ended = 0;
        this.MIN = 0;
        this.MAX = MAX;
        this.lock = new ReentrantLock();
        this.full = lock.newCondition();
        this.empty = lock.newCondition();
        this.buffer = new LinkedList();
        this.numberOfDeliveriesTotal = numberOfDeliveriesTotal;
        bufferBar.setMaximum(MAX);
        bufferBar.setMinimum(MIN);
        bufferDerivate.setMaximum(10);
        bufferDerivate.setMinimum(-10);
        this.bufferBar = bufferBar;
        this.bufferDerivate = bufferDerivate;
        this.bufferTxt = bufferTxt;
        this.stream = stream;
        this.print = true;
        this.state = state;
    }
    
    public void stopPrinting()
    {
        print = false;
    }
    
    public void beguinPrinting()
    {
        print = true;
        stream.setText(log);
    }
    
    /**
     * 
     * @return it says if all the packages have been delivered or no
     */
    public boolean deliveriesLeft()
    {
        lock.lock();
        try
        {
            if (numberOfDeliveries >= numberOfDeliveriesTotal)
            {
                bufferDerivate.setValue(0);
                bufferDerivate.setString("0");
                bufferTxt.setText("FINISH");
            }
            return numberOfDeliveries < numberOfDeliveriesTotal;
        }
        finally
        {
            lock.unlock();
        }
    }
    
    /**
     * 
     * @return The amount of packages in the Buffer
     */
    public int getStatus ()
    {
        lock.lock();
        try
        {
            return buffer.size();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    /**
     * If there's space we add packages, if not we wait until more space is available
     * @param pack The new package of the Buffer
     */
    public void add (Package pack)
    {
        lock.lock();
        while (isFull())
        {
            try
            {
                full.await();
            }
            catch (InterruptedException i)
            {
                write("ERROR --> AWAIT IN ADD " + i.toString());
            }
        }
        try
        {
            buffer.add(pack);
            bufferTxt.setText(this.toString());
            bufferBar.setValue(this.getStatus());
            bufferBar.setString(String.valueOf(this.getStatus()));
            this.derivate();
            empty.signal();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    /**
     * 
     * @return used to update the UI
     */
    public String getLog ()
    {
        try
        {
            lock.lock();
            return log;
        }
        finally
        {
            lock.unlock();
        }
    }
    
    /**
     * 
     * @param newTxt The new information added to the log
     */
    public void write (String newTxt)
    {
        try
        {
            lock.lock();
            log += newTxt;
            if (print)
                stream.setText(log);
        }
        finally
        {
            lock.unlock();
        }
    }
    
    /**
     * 
     * @return used to update the UI with the content of the buffer
     */
    @Override
    public String toString()
    {
        try
        {
            lock.lock();
        
            String str = "";
            str = buffer.stream().map((p) -> p.toString()+"\n").reduce(str, String::concat);
            return String.valueOf(str);
        }
        finally
        {
            lock.unlock();
        }
    }
    
    /**
     * 
     * @return The package been removed from the buffer
     */
    public Package get ()
    {
        Package pack = null;
        lock.lock();
        while (isEmpty())
        {
            try
            {
                empty.await();
            }
            catch (InterruptedException i)
            {
                write("ERROR --> AWAIT IN GET " + i.toString());
            }
        }
        try
        {
            pack = buffer.remove();
            numberOfDeliveries++;
            bufferTxt.setText(this.toString());
            bufferBar.setValue(this.getStatus());
            bufferBar.setString(String.valueOf(this.getStatus()));
            this.derivate();
            full.signal();
        }
        catch(NoSuchElementException e)
        {
            write("ERROR --> BUFFER VACIO " + e.toString());
        }
        finally
        {
            lock.unlock();
        }
        return pack;
    }
    
    /**
     * Used tho calculate the variation in packages every four of them modified, it says if the buffer grows or decrements and the rete of change
     */
    private void derivate ()
    {
        if(changes%4 == 0)
        {
            int value = ((this.getStatus() - previous))%10;
            bufferDerivate.setValue(value);
            bufferDerivate.setString(String.valueOf(value));
            previous = this.getStatus();
        }
        changes++;
    }
    
    public void setMax (int MAX)
    {
        this.MAX = MAX;
    }
    private boolean isEmpty()
    {
        return buffer.size() <= MIN;
    }
    private boolean isFull()
    {
        return buffer.size() >= MAX;
    }
    
    public synchronized void end()
    {
        ended++;
        if (ended >= 8)
            state.finish();
    }
}
