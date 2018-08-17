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
public class Productor extends Thread

{
    private final String prefijo;
    private final int numMensajes;
    private final Buzon miBuzon;

    public Productor(String prefijo, int n, Buzon buzon)
    {
        this.prefijo=prefijo;
        numMensajes=n;
        miBuzon=buzon;
    }

    public void run()
    {
        for(int i=1; i<=numMensajes; i++)
        {
            try
            {
                Thread.sleep((int)(500+500*Math.random()));
            } 
            catch(InterruptedException e)
            {  
            }
            miBuzon.enviaMensaje(prefijo+i);
        }
    }  
}
