/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tennis;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author mr.blissfulgrin
 */
public class Pista
{
    Semaphore juegoPar;
    Semaphore juegoImpar;
    ArrayList <Tenista> enEspera;
    ArrayList <Tenista> jugandoPar;
    ArrayList <Tenista> jugandoImpar;
    Tenista esperandoJuego;
    
    public Pista (int jugadoresPista)
    {
        juegoPar = new Semaphore(jugadoresPista, true);
        juegoImpar = new Semaphore(jugadoresPista, true);
        enEspera = new ArrayList <>();
        jugandoPar = new ArrayList <>();
        jugandoImpar = new ArrayList <>();
    }
    
    public synchronized void jugar (Tenista t) 
    {
        try
        {
            enEspera.add(t);
            System.out.println(t.toString() + " ESPERANDO");
            
            if (t.getSexo())
            {
                juegoPar.acquire();
                jugandoPar.add(t);
            }
            else
            {
                juegoImpar.acquire();
                jugandoImpar.add(t);
            }
        } 
        catch (InterruptedException i)
        {
            System.out.println("ERROR AL JUGAR " + i.toString());
        }
        
        if (juegoPar.availablePermits()==0)
            jugar(true);
        else if (juegoImpar.availablePermits()==0)
            jugar(false);
    }
    
    private void jugar (boolean cual)
    {
        if (cual)
        {
            for (Tenista tenista : jugandoPar)
            {
                enEspera.remove(tenista);
                System.out.println(tenista.toString() + " JUGANDO");
            }
        }
        else
        {
            for (Tenista tenista : jugandoImpar)
            {
                enEspera.remove(tenista);
                System.out.println(tenista.toString() + " JUGANDO");
            }
        }
        
        try
        {
            Thread.sleep(3000);
        } 
        
        catch (InterruptedException i)
        {
            System.out.println("ERROR JUGANDO "+i.toString());
        }
        
        if (cual)
        {
            for (Tenista tenista : jugandoPar)
            {
                System.out.println(tenista.toString() + " HA TERMINADO");
                juegoPar.release();
            }
            jugandoPar = new ArrayList <>();
        }
        else
        {
            for (Tenista tenista : jugandoImpar)
            {
                System.out.println(tenista.toString() + " HA TERMINADO");
                juegoImpar.release();
            }
            jugandoImpar = new ArrayList <>();
        }
    }
}
