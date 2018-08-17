
package pecl2.classes;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author mr.blissfulgrin
 */
public class Consumer extends Thread
{
    private final String id;
    private final Buffer buffer;
    private int consumptionRate;
    private boolean random;
    private int deliveriesDone;
    String log = "";
    private final int packetsExpected;
    private final JProgressBar bar;
    private final JTextArea stream;
    private Boolean print;
    
    private final Stop stop;
    
    /**
     * 
     * @param id
     * @param buffer The shared variable from where the packages are extracted
     * @param packetsExpected The expected amount of packages each producer should in ideal conditions get from the buffer
     * @param bar UI
     * @param stream UI
     * @param stop The class used to Stop the production of this consumer
     */
    public Consumer (String id, Buffer buffer, int packetsExpected, JProgressBar bar, JTextArea stream, Stop stop)
    {
        this.id = id;
        this.buffer = buffer;
        this.random = true;
        this.packetsExpected = packetsExpected;
        this.bar = bar;
        this.stream = stream;
        this.print = false;
        this.stop = stop;
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
    
    public String getLog ()
    {
        return log;
    }
    
    /**
     * We update the log string and if needed the UI
     * Also we update the log of the buffer
     * @param newTxt 
     */
    public void write (String newTxt)
    {
        buffer.write(newTxt+"\n");
        log += newTxt+"\n";
        if (print)
            stream.setText(log);
    }
    
    public void setFixedProductionRate (int consumptionRate)
    {
        random = false;
        this.consumptionRate = consumptionRate;
    }
    public void setRandomProductionRate ()
    {
        random = true;
    }
    
    public int getDeliveriesDone ()
    {
        return deliveriesDone;
    }
    
    /**
     * 1. wait the time as set
     * 2. stop if needed
     * 3. extract the package from the buffer when possible
     * 4. update UI
     */
    @Override
    public void run ()
    {
        Package pack;
        while (buffer.deliveriesLeft())
        {
            if (random)
            {
                consumptionRate = (int)(Math.random()*300+300);
            }
            try
            {
                Thread.sleep(consumptionRate);
            }
            catch (InterruptedException i)
            {
                write("ERROR --> SLEEP IN CONSUMER " + i.toString());
            }
            if (buffer.deliveriesLeft())
            {
                stop.look();
                pack = buffer.get();
                pack.delivered(id);
                write(pack.toString());
                deliveriesDone ++;
                if (deliveriesDone >= packetsExpected)
                {
                    bar.setMaximum(deliveriesDone);
                }
                bar.setValue(deliveriesDone);
                bar.setString(String.valueOf(deliveriesDone));
            }
        }
        write(this.toString() + " FINISHED " + deliveriesDone + " deliveries done");
        buffer.end();
    }
    
    @Override
    public String toString ()
    {
        return String.valueOf("Consumer: " + id);
    }
}
