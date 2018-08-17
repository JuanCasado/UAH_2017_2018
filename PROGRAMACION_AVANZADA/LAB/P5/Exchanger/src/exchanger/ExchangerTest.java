/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchanger;

import static java.lang.Thread.sleep;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;

/**
 *
 * @author mr.blissfulgrin
 */
public class ExchangerTest
{
    public static void main(String[] args)
    {
        Portador [] portador = new Portador [3];
        Exchanger e = new Exchanger();
        Recolector r = new Recolector ();
        CyclicBarrier inicio = new CyclicBarrier(portador.length);
        CyclicBarrier fin = new CyclicBarrier(portador.length,r);
        
        for (int i = 0; i < portador.length; i++)
        {
            portador [i] = new Portador(i,i,e,inicio,fin);           
            try
            {
                sleep (500);
            } 
            catch (InterruptedException ex)
            {
                System.out.println("ERROR SLEEP MAIN");
            }
            r.setRecolector(portador);
            portador [i].start();
            System.out.println(i + " creado");

        }
    }   
}
