/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bucadordeprimosruneblade;

import java.util.Date;

/**
 *
 * @author mr.blissfulgrin
 */
public class BucadorDePrimosRuneblade {

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
        
        Thread t1 = new Thread (p1);
        Thread t2 = new Thread (p2);
        Thread t3 = new Thread (p3);
        Thread t4 = new Thread (p4);
        Thread t5 = new Thread (p5);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        try
        {
            t1.join(); t2.join(); t3.join(); t4.join(); t5.join(); //esperamos a que terminen todos
        }
        catch (InterruptedException e)
        {
            System.out.println("ERROR"+e.toString());
        }
        
        long n1 = p1.cuantos();
        long tm1 = p1.tiempo();
        long n2 = p2.cuantos();
        long tm2 = p2.tiempo();
        long n3 = p3.cuantos();
        long tm3 = p3.tiempo();
        long n4 = p4.cuantos();
        long tm4 = p4.tiempo();
        long n5 = p5.cuantos();
        long tm5 = p5.tiempo();
        
        long nf = n1 + n2 + n3 + n4 + n5;
        long tf_calculated_by_sum = tm1 + tm2 + tm3 + tm4 + tm5;
        
        long tf = (new Date()).getTime();
        long tf_total = tf - t0;
        
        System.out.println("Tramo 1 = "+ n1 +" primos; TIEMPO = "+tm1);
        System.out.println("Tramo 2 = "+ n2 +" primos; TIEMPO = "+tm2);
        System.out.println("Tramo 3 = "+ n3 +" primos; TIEMPO = "+tm3);
        System.out.println("Tramo 4 = "+ n4 +" primos; TIEMPO = "+tm4);
        System.out.println("Tramo 5 = "+ n5 +" primos; TIEMPO = "+tm5);
        System.out.println("TOTAL   = "+ nf);
        System.out.println("TIEMPO SUMADO = "+ tf_calculated_by_sum);
        System.out.println("TIEMPO TOTAL  = "+ tf_total);
    }
    
}
