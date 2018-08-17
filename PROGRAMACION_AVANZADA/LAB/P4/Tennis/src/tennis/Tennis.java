/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tennis;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mr.blissfulgrin
 */
public class Tennis
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Pista p = new Pista(2);
        Tenista [] t = new Tenista [12];
        for (int i = 0; i < t.length; i++)
        {
            t[i] = new Tenista (i,p);
        }
        for (Tenista tenista : t)
        {
            tenista.start();
        }
        for (Tenista tenista : t)
        {
            try
            {
               tenista.join(); 
            }
            catch (InterruptedException i)
            {
                System.out.println("ERROR JOIN " + i.toString());
            }
        }
        System.out.println("FIN");
    }
    
}
