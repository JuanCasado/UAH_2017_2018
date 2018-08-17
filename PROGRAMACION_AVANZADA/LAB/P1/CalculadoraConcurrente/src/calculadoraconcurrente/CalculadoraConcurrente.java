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
public class CalculadoraConcurrente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        long [] terminaciones = new long[] {3,5};
        long multiplo = 7;
        
        long t0 = (new Date()).getTime();
        CalculaMultiplos m = new CalculaMultiplos (1,10000,multiplo);
        CalculaPrimos p = new CalculaPrimos(1,10000);
        CalculaTerminaciones t = new CalculaTerminaciones(1,10000,terminaciones);
        
        m.start();
        p.start();
        t.start();
        
        try
        {
            m.join();
            p.join();
            t.join();
        } 
        catch (Exception e)
        {
            System.out.println("ERROR "+ e.toString());
        }
        
        long a = m.cuantos();
        long at = m.tiempo();
        long b = t.cuantos();
        long bt = t.tiempo();
        long c = p.cuantos();
        long ct = p.tiempo();
        
        long nf = a + b + c;
        long ts = at + bt + ct;
        
        long tf = (new Date()).getTime();      
        long tt = tf - t0;
        
        System.out.println("MULTIPLOS   = "+ a +"; TIEMPO = "+at);
        System.out.println("TERMINADOS  = "+ b +"; TIEMPO = "+bt);
        System.out.println("PRIMOS      = "+ c +"; TIEMPO = "+ct);

        System.out.println("TOTAL = "+ nf);
        System.out.println("TIEMPO SUMADO = "+ ts);
        System.out.println("TIEMPO TOTAL  = "+ tt);
    }
    
}
