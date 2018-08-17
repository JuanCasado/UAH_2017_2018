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
    JButton b;
    Color paleta[] = new Color[12];

    public Pintor(JButton b){
        this.b=b;
        paleta[0]=Color.GRAY;
        paleta[1]=Color.RED;
        paleta[2]=Color.GREEN;
        paleta[3]=Color.BLUE;
        paleta[4]=Color.YELLOW;
        paleta[5]=Color.MAGENTA;
        paleta[6]=Color.ORANGE;
        paleta[7]=Color.CYAN;
        paleta[8]=Color.LIGHT_GRAY;
        paleta[9]=Color.PINK;
        paleta[10]=Color.WHITE;
        paleta[11]=Color.BLACK;
    }

    @Override
    public void run()
    {      
        while (true)
        {
            b.setBackground(paleta[11]);
            try
            {
                Thread.sleep((long)(Math.random()*2000)+2000);
            }
            catch(Exception e)
            {
                System.out.println("ERROR "+ e.toString());
            }
            int i = (int)(Math.random()*10);
            b.setBackground(paleta[i]);
            try
            {
                Thread.sleep((long)(Math.random()*3000)+2000);
            }
            catch(Exception e)
            {
                System.out.println("ERROR "+ e.toString());
            }
        }
    }
}
