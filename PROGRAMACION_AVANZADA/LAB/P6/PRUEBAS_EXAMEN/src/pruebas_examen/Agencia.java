/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas_examen;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author mr.blissfulgrin
 */
public class Agencia
{
    private final Semaphore semaphore;
    private final LinkedBlockingQueue <Bus> cola;
    private final int capacidad;
    
    public Agencia (int buses, int capacidad)
    {
        semaphore = new Semaphore (buses*capacidad,true);
        cola = new LinkedBlockingQueue <>(buses);
        this.capacidad = capacidad;
        for (int x = 0; x < buses; x++)
            cola.offer(new Bus(x,capacidad,this));
    }
    
    public void llegaViajero (Viajero v)
    {
        System.out.println(">El viajero " + v.toString() + " llega a la agencia");
        try
        {
            semaphore.acquire();
            System.out.println(">La agencia tiene "+ cola.size() +" buses disponibles");
        } 
        catch (InterruptedException ex){}
    }
    public synchronized Bus tomarBus ()
    {
        if (cola.peek().puedeSalir())
        {
            return cola.poll();
        }
        else
        {
            return cola.peek();
        }
    }
    public void vuelveBus (Bus b)
    {
        cola.offer(b);
        semaphore.release(capacidad);
    }
}
