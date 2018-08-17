/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchanger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author mr.blissfulgrin
 */
public class Portador extends Thread
{
    private int contenido;
    private int saltos;
    private final int id;
    private final Exchanger e;
    private final CyclicBarrier inicio;
    private final CyclicBarrier fin;
    
    public Portador (int id, int contenido, Exchanger e, CyclicBarrier inicio, CyclicBarrier fin)
    {
        this.id = id;
        this.contenido = contenido;
        this.e = e;
        this.inicio = inicio;
        this.fin = fin;
    }
    
    @Override
    public void run()
    {
        try
        {
            inicio.await();
        } catch (InterruptedException ex)
        {
            System.out.println("BARRERA INICIO INTERRUMPIDA");
        } catch (BrokenBarrierException ex)
        {
            System.out.println("BARRERA INICIO ROTA");
        }
        
        System.out.println(this.toString());
        for (int i = 0; i < 3; i++)
        {
            try
            {
                sleep(500);
                contenido = (int) e.exchange(contenido,400,TimeUnit.MILLISECONDS);
                System.out.println(this.toString());
                saltos++;
            } 
            catch (InterruptedException ex)
            {
                System.out.println("EXCEPTION IN EXCHANGER");
            }
            catch (TimeoutException ex)
            {
                System.out.println("TIMEOUT");
            }
        }
        
        try
        {
            fin.await();
        } catch (InterruptedException ex)
        {
            System.out.println("BARRERA INICIO INTERRUMPIDA");
        } catch (BrokenBarrierException ex)
        {
            System.out.println("BARRERA INICIO ROTA");
        }
    }
    
    public int getSaltos()
    {
        return saltos;
    }
    
    @Override
    public String toString()
    {
        return "Portador: " + id + " tiene " + contenido;
    }
}
