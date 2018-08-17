/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrera_foto;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mr.blissfulgrin
 */
public class Fotografo 
{
    private final int [] buffer = new int [3];
    private final Lock cerrojo = new ReentrantLock();
    //private final Condition vacio = cerrojo.newCondition();
    //private final Condition lleno = cerrojo.newCondition();
    private int dentro;
    
    public void tomarFoto (int dorsal)
    {
        try 
        {
            cerrojo.lock();
            buffer[dentro] = dorsal;
            
            if (dentro > 1)
            {
                System.out.println("FOTO A: "+ buffer[0] +" - "+ buffer[1]+ " - "+ buffer[2]);
                for (int x = 0; x < buffer.length; x++)
                {
                    System.out.println("Sacando corredor #"+ buffer[x]);
                    buffer[x] = 0;
                }
                dentro = 0;
            }
            else
                dentro ++;
        }
        finally
        {
            cerrojo.unlock();
        }
    }
}
