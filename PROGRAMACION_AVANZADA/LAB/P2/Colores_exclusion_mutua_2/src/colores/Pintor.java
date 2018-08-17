/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colores;

import java.awt.Color;
import java.util.Date;
import javax.swing.JButton;

/**
 *
 * @author mr.blissfulgrin
 */
public class Pintor extends Thread
{
    private final JButton b;
    private final Color negro;
    private final Colores c;
    
    public Pintor(JButton b, Colores c)
    {
        this.b=b;
        this.c=c;
        negro = Color.BLACK;
    }

    @Override
    public void run()
    {      
        while (true)
        {
            b.setBackground(negro);
            try
            {
                Thread.sleep((long)(Math.random()*500)+1000);
            }
            catch(InterruptedException e)
            {
                System.out.println("ERROR "+ e.toString());
            }
             
            Color x = c.getColor();
            b.setBackground(x);
            
            try
            {
                Thread.sleep((long)(Math.random()*1000)+3000);
            }                                    
            catch(InterruptedException e)
            {
                System.out.println("ERROR "+ e.toString());
            }
            c.free(x);
        }
    }
}
