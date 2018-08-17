/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productor_consumidor;

import java.util.concurrent.locks.*;

/**
 *
 * @author mr.blissfulgrin
 */
public class Buzon 
{
    private String mensaje;
    private boolean lleno=false;
    private final Lock cerrojo;
    private final Condition buzonLleno;
    private final Condition buzonVacio;
    
    public Buzon()
    {
        cerrojo = new ReentrantLock();
        buzonLleno = cerrojo.newCondition();
        buzonVacio = cerrojo.newCondition();
    }
    
    public void enviaMensaje(String msg) 
    {
        try
        {
            cerrojo.lock();
            
            while(lleno)
            {
                try
                {
                    buzonLleno.await();
                }
                catch(InterruptedException e)
                {
                    
                }
            }
            
            lleno=true;
            mensaje=msg;
            
            buzonVacio.signal();
        }
        finally
        {
            cerrojo.unlock();
        }

    }

    public String recibeMensaje()
    {
        try
        {
            cerrojo.lock();
            while (!lleno)
            {
                try
                {
                    buzonVacio.await();
                }
                catch(Exception e)
                {
                    
                }
            }
            lleno=false;
            buzonLleno.signal();
            return mensaje;
        }
        finally
        {
            cerrojo.unlock();
        }
    }
}
