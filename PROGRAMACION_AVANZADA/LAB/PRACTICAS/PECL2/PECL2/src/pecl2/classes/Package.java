
package pecl2.classes;

import java.util.Date;

/**
 *
 * @author mr.blissfulgrin
 */
public class Package 
{
    private final long startTime;
    private long timeAlive;
    private final String producer;
    private final int number;
    private String consumer;
    private boolean onItsWay;
    
    /**
     * The creation time is set
     * @param producer Who made the package?
     * @param number Which package is this?
     */
    public Package (String producer, int number)
    {
        this.producer = producer;
        this.number = number;
        this.startTime = new Date().getTime();
        this.onItsWay = true;
    }
    
    /**
     * The end of cycle time is set
     * @param consumer Who took the package from the buffer?
     */
    public void delivered (String consumer)
    {
        if (onItsWay)
        {
            this.consumer = consumer;
            this.timeAlive = new Date().getTime() - startTime;
            onItsWay = false;
        }
    }
    
    /**
     * We can see the status of each package 
     * @return The current information about the package, it changes over time
     */
    @Override
    public String toString ()
    {
        if (onItsWay)
            return String.valueOf(producer + ":" + number);
        else
            return String.valueOf(producer + ":" + number + "->" + consumer + "\t in " + timeAlive + "ms");
    }
}
