/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colores;

import java.awt.Color;
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
    private final Paso paso;
    
    public Pintor(JButton b, Colores c, Paso paso)
    {
        this.b=b;
        this.c=c;
        negro = Color.BLACK;
        this.paso=paso;
    }

    @Override
    public void run()
    {      
        Color x;
        while (true)
        {
            b.setBackground(negro);
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                System.out.println("ERROR "+ e.toString());
            }
            
            paso.mirar();              //Mira a ver si tiene que detenerse
            x = c.getColor();
            b.setBackground(x);
            
            try
            {
                Thread.sleep(3000);
            }                                    
            catch(InterruptedException e)
            {
                System.out.println("ERROR "+ e.toString());
            }
            
            paso.mirar();              //Mira a ver si tiene que detenerse
            c.free(x);
        }
    }
}
