/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas_examen;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author mr.blissfulgrin
 */
public class Bus
{
    private final AtomicInteger id;
    private final Agencia agencia;
    private final AtomicInteger capacidad;
    private final AtomicInteger capacidadMAX;
    private final CyclicBarrier entrada;
    private final AtomicBoolean print;
    
    public Bus (int id, int capacidad, Agencia agencia)
    {
        this.id = new AtomicInteger(id);

        this.capacidad = new AtomicInteger(0);
        this.capacidadMAX = new AtomicInteger(capacidad);
        this.agencia = agencia;
        
        this.entrada = new CyclicBarrier(capacidad);
        
        this.print = new AtomicBoolean (true);
    }
    
    public synchronized boolean puedeSalir ()
    {
        return ((capacidadMAX.get() - capacidad.getAndIncrement()) == 1);
    }
    public void montaViajero (Viajero v)
    {
        System.out.println("El viajero " + v.toString() + " está en el bus " + this.toString() + "; hay " + capacidad.get() + " pasajeros");
        try
        {
            entrada.await();
        } 
        catch (InterruptedException | BrokenBarrierException ex){}
        if (print.getAndSet(false))
        {
            System.out.println("----------------------BUS: " + this.toString() + " HA SALIDO-----------------------");
        }
    }
    public void bajaViajero (Viajero v)
    {
         System.out.println("El viajero " + v.toString() + " baja del bus " + this.toString() + "; hay " + capacidad.decrementAndGet() + " pasajeros");
         if (capacidad.get() == 0)
         {
             System.out.println("----------------------BUS: " + this.toString() + " HA VUELTO-----------------------");
             agencia.vuelveBus(this);
             print.set(true);
         }
    }
    
    @Override
    public String toString()
    {
        return ""+id.get();
    }
}
