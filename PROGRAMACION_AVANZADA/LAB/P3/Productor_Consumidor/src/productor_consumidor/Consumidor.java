/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productor_consumidor;

/**
 *
 * @author mr.blissfulgrin
 */
public class Consumidor extends Thread
{
    private final int numMensajes;
    private final Buzon miBuzon;
    
    public Consumidor(int numMensajes, Buzon miBuzon)
    {
        this.numMensajes=numMensajes;
        this.miBuzon=miBuzon;
    }

    @Override
    public void run()
    {
        for(int i=0; i<numMensajes; i++)
        {
            try
            {
                Thread.sleep((int)(500+500*Math.random()));
            } 
            catch(InterruptedException e)
            { 
            }
            System.out.println(miBuzon.recibeMensaje());
        }
        System.out.println("Total mensajes leídos: " + numMensajes);
    }   
}
