/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraconcurrente;

import java.util.Date;

/**
 *
 * @author mr.blissfulgrin
 */
public class CalculaMultiplos extends Thread
{
    private long time;
    private final long max;
    private final long min;
    private long number;
    private final long mult;
    
    public CalculaMultiplos(long min, long max, long mult)
    {
        this.min = min;
        this.max = max;
        this.mult = mult;
    }
    
    public long cuantos ()
    {
        return number;
    }
    
    public long tiempo ()
    {
        return time;
    }
    
    private boolean es_multiplo (long n)
    {
            return n % mult == 0;
    }
    
    @Override
    public void run ()
    {
        long t0 = (new Date()).getTime();
        
        for (long i = min; i <= max; i++)
            if (es_multiplo(i))
                number++;
        
        long tf = (new Date()).getTime();
        time = tf - t0;
    }
    
}
