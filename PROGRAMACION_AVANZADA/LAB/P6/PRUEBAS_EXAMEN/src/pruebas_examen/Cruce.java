/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas_examen;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

/**
 *
 * @author mr.blissfulgrin
 */
public class Cruce
{
    private final Semaforo norte;
    private final Semaforo oeste;
    private final Semaphore semNorte;
    private final Semaphore semOeste;
    private final Semaphore cruce;
    
    public Cruce ()
    {
        Exchanger ex = new Exchanger();
        this.semNorte = new Semaphore (0);
        this.semOeste = new Semaphore (0);
        this.cruce = new Semaphore (1);
        this.norte = new Semaforo (true,"norte",ex,semNorte);
        this.oeste = new Semaforo (false,"oeste",ex,semOeste);
        norte.start();
        oeste.start();
    }
    
    public void cruzar (Coche coche)
    {
        try
        {
            cruce.acquire();
            if (coche.getSentido())
            {
                if (!norte.isVerde())
                {
                    System.out.println(">" + coche.toString() + " para en " + norte.toString());
                    cruce.release();
                    semNorte.acquire();
                    cruce.acquire();
                }
                System.out.println(coche.toString() + " cruza " + norte.toString());
            }
            else
            {
                if (!oeste.isVerde())
                {
                    System.out.println(">" + coche.toString() + " para en " + oeste.toString());
                    cruce.release();
                    semOeste.acquire();
                    cruce.acquire();
                }
                System.out.println(coche.toString() + " cruza " + oeste.toString());
            }
            cruce.release();
            System.out.println(coche.toString() + " sale del cruce");
        }
        catch(InterruptedException e){}
    }
}
