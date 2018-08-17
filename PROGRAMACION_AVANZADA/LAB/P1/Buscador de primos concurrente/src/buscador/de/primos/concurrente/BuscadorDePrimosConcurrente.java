/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscador.de.primos.concurrente;

import java.util.Date;

/**
 *
 * @author mr.blissfulgrin
 */
public class BuscadorDePrimosConcurrente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long t0 = (new Date()).getTime();
        
        Primos p1 = new Primos(1,2000000);
        Primos p2 = new Primos(2000001,4000000);
        Primos p3 = new Primos(4000001,6000000);
        Primos p4 = new Primos(6000001,8000000);
        Primos p5 = new Primos(8000001,10000000);
        
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        
        try
        {
            p1.join(); p2.join(); p3.join(); p4.join(); p5.join(); //esperamos a que terminen todos
        }
        catch (InterruptedException e)
        {
            System.out.println("ERROR"+e.toString());
        }
        
        long n1 = p1.cuantos();
        long t1 = p1.tiempo();
        long n2 = p2.cuantos();
        long t2 = p2.tiempo();
        long n3 = p3.cuantos();
        long t3 = p3.tiempo();
        long n4 = p4.cuantos();
        long t4 = p4.tiempo();
        long n5 = p5.cuantos();
        long t5 = p5.tiempo();
        
        long nf = n1 + n2 + n3 + n4 + n5;
        long tf_calculated_by_sum = t1 + t2 + t3 + t4 + t5;
        
        long tf = (new Date()).getTime();
        long tf_total = tf - t0;
        
        System.out.println("Tramo 1 = "+ n1 +" primos; TIEMPO = "+t1);
        System.out.println("Tramo 2 = "+ n2 +" primos; TIEMPO = "+t2);
        System.out.println("Tramo 3 = "+ n3 +" primos; TIEMPO = "+t3);
        System.out.println("Tramo 4 = "+ n4 +" primos; TIEMPO = "+t4);
        System.out.println("Tramo 5 = "+ n5 +" primos; TIEMPO = "+t5);
        System.out.println("TOTAL   = "+ nf);
        System.out.println("TIEMPO SUMADO = "+ tf_calculated_by_sum);
        System.out.println("TIEMPO TOTAL  = "+ tf_total);
    }
    
}
