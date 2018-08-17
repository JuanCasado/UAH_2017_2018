/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculador_primos_exclusion_mutua;

import java.util.Date;

/**
 *
 * @author mr.blissfulgrin
 */
public class Calculador_primos_exclusion_mutua {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Resultado suma = new Resultado();
        long t0 = (new Date()).getTime(); //t0=instante de inicio de los cálculos
        Calculador p1 = new Calculador(1,2000000,suma);
        Calculador p2 = new Calculador(2000001,4000000,suma);
        Calculador p3 = new Calculador(4000001,6000000,suma);
        Calculador p4 = new Calculador(6000001,8000000,suma);
        Calculador p5 = new Calculador(8000001,10000000,suma);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        try
        {
            p1.join();
            p2.join();
            p3.join();
            p4.join();
            p5.join(); //esperamos a que terminen todos
        }
        catch (InterruptedException e)
        {
            System.out.println("ERROR"+e.toString());
        }

        long t1 = (new Date()).getTime(); //t1=instante de final de los cálculos

        System.out.println("La suma de los números primos hasta 10000000 es: "+suma.getSuma()+" calculado en "+(t1-t0)+" miliseg.");
    }
    
}
