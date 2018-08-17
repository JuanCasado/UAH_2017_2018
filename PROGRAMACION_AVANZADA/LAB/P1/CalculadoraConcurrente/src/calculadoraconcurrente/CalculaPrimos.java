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
public class CalculaPrimos extends Thread
{
    private final long max;
    private final long min;
    private final long sqrt;
    private long time;
    private long number;
    
    public CalculaPrimos (long min, long max)
    {
        this.max = max;
        this.min = min;
        this.sqrt = (long) Math.sqrt((double) max);
    }
    
    public long cuantos()
    {
        return number;
    }
    
    public long tiempo()
    {
        return time;
    }
    
    public void run()
    {
        long t0 = (new Date()).getTime();
        
        for (long i = min; i<= max; i++)
            if(es_primo(i))
                number++;
        
        long tf = (new Date()).getTime();
        time = tf - t0;
    }
    
    private boolean es_primo (long n)
    {
        boolean primo = true;
        long i=2;

        while(primo && (i <= sqrt))
        {
            if (n % i == 0)
                primo=false;
            i++;
        }
        return primo;
    }   
}