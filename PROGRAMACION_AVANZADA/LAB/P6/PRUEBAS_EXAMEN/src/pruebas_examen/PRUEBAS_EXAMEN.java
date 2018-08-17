
package pruebas_examen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PRUEBAS_EXAMEN
{
    public static void main(String[] args) throws InterruptedException
    {
        /*Agencia agencia = new Agencia (10,8);
        for (int x = 0; x < 50; x++)
        {
            try
            {
                Thread.sleep((long)(Math.random()*500+100));
            }
            catch (InterruptedException e)
            {
                System.out.println("TE INTERRUPIERON WEYY!!!");
            }
            new Viajero(x,agencia).start();
        }*/

        Cruce cruce = new Cruce ();
        for (int x = 0; x < 50; x++)
        {
            Thread.sleep(1000);
            new Coche(x,cruce).start();
        }
        
        ExecutorService ex = Executors.newScheduledThreadPool(3);
        
        
    }
}
