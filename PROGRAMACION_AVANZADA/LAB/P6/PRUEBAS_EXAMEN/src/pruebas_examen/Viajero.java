/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas_examen;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author mr.blissfulgrin
 */
public class Viajero extends Thread
{
    private final int id;
    private Bus bus;
    private final Agencia agencia;
    private final AtomicBoolean control;
        
    public Viajero (int id, Agencia agencia)
    {
        this.id = id;
        this.agencia = agencia;
        control = new AtomicBoolean();
        control.set(true);
    }
    
    @Override
    public void run ()
    {  
        while (control.get())
        {
            agencia.llegaViajero(this);
            bus = agencia.tomarBus();
            bus.montaViajero(this);
            try
            {
                Thread.sleep((long)(Math.random()*2000+1000));
            }
            catch (InterruptedException e)
            {
                System.out.println(id + "TE INTERRUPIERON WEYY!!!");
            }
            System.out.println("> "+id + " termina el viaje");
            bus.bajaViajero(this);
            try
            {
                Thread.sleep((long)(Math.random()*2000+3000));
            }
            catch (InterruptedException e)
            {
                System.out.println(id + "TE INTERRUPIERON WEYY!!!");
            }
        }
    }
    
    public void parar ()
    {
        control.set(false);
    }
    
    @Override
    public String toString()
    {
        return ""+id;
    }
}
