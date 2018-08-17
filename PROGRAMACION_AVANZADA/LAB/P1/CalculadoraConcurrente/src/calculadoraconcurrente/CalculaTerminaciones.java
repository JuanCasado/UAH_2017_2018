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
public class CalculaTerminaciones extends Thread
{
    private long time;
    private final long max;
    private final long min;
    private long number;
    private final long terms [];
    
    public CalculaTerminaciones (long min, long max, long [] terms)
    {
        this.min = min;
        this.max = max;
        this.terms = terms;
    }
    
    public long cuantos ()
    {
        return number;
    }
    
    public long tiempo ()
    {
        return time;
    }
    
    private boolean es_terminado (long n)
    {
        boolean terminado = false;
        int i = 0;
        while ((i < terms.length) && !terminado)
        {
            terminado = n % 10 == terms[i];
            i++;
        }
        
        return terminado;
    }
    
    @Override
    public void run ()
    {
        long t0 = (new Date()).getTime();
        
        for (long i = min; i <= max; i++)
            if (es_terminado(i))
                number++;
        
        long tf = (new Date()).getTime();
        time = tf - t0;
    }
}
