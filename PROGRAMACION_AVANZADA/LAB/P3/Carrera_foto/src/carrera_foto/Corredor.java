/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrera_foto;

/**
 *
 * @author mr.blissfulgrin
 */
public class Corredor extends Thread
{
    private final int dorsal;
    private final Fotografo fotografo;
    
    public Corredor (int dorsal, Fotografo fotografo)
    {
        this.dorsal = dorsal;
        this.fotografo = fotografo;
    }
    
    public void run()
    {
        try
        {
            Thread.sleep((int)Math.random()*2000+3000);
        }
        catch(InterruptedException i)
        {
            System.out.println("excepción en sleep " + this.toString());
        }
        System.out.println(this.toString()+" terminado");
        fotografo.tomarFoto(dorsal);
    }
    
    @Override
    public String toString()
    {
        return String.valueOf("Corredor #"+dorsal);
    }
}
