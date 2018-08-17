
package pecl2.classes;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author mr.blissfulgrin
 */
public class Producer extends Thread
{
    private final String id;
    private int nextDelivery;
    private final Buffer buffer;
    private int productionRate;
    private boolean random;
    private final int total;
    private String log = "";
    private final JProgressBar bar;
    private final JTextArea stream;
    private Boolean print;
    private final Stop stop;
    
    /**
     * 
     * @param id 
     * @param buffer The shared variable where to store the packages produced
     * @param total The number of packages that the producer need to create
     * @param bar UI
     * @param stream UI
     * @param stop The class used to Stop the production of this producer
     */
    public Producer (String id, Buffer buffer, int total, JProgressBar bar, JTextArea stream, Stop stop)
    {
        this.id = id;
        this.buffer = buffer;
        this.nextDelivery = 1;
        this.random = true;
        this.total = total+1;
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
    
    public void setFixedProductionRate (int productionRate)
    {
        random = false;
        this.productionRate = productionRate;
    }
    public void setRandomProductionRate ()
    {
        random = true;
    }
    
    /**
     * 
     * @return The id of the next package we are going to create
     */
    public int getNextDelivery ()
    {
        return nextDelivery;
    }
    
    /**
     * 1. wait the time as set
     * 2. stop if needed
     * 3. create the package
     * 4. add it to the buffer when possible
     * 5. update UI
     */
    @Override
    public void run ()
    {
        for (;nextDelivery < total; nextDelivery ++)
        {
            if (random)
            {
                productionRate = (int)(Math.random()*400+400);
            }
            try
            {
                Thread.sleep(productionRate);
            }
            catch (InterruptedException i)
            {
                write("ERROR --> SLEEP IN PRODUCER " + i.toString());
            }
            stop.look();
            Package pack = new Package (id,nextDelivery);
            write(pack.toString());
            buffer.add(pack);
            bar.setValue(total-nextDelivery);
            bar.setString(String.valueOf((total-nextDelivery)-1));
        }
        write(this.toString() + " FINISHED");
        buffer.end();
    }
    
    @Override
    public String toString ()
    {
        return String.valueOf("Producer: " + id);
    }
}
