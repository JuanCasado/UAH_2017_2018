/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mr.blissfulgrin
 */
public class Colores 
{
    private final Color[] paleta;
    private final ArrayList <Color> selected;
    Lock cerrojo;
    
    public Colores()
    {
        cerrojo = new ReentrantLock();
        paleta = new Color[11];
        selected = new ArrayList <>();
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
    }
    
    public void free (Color c)
    {            
        selected.remove(c);                
    }
    
    public Color getColor()
    {
        cerrojo.lock();
        
        Color i = Color.BLACK;
        try 
        {
            Boolean repeat = true;
            while (repeat)
            {
                repeat = false;
                i = paleta[(int)(Math.random()*10)];
                for (int x = 0; x < selected.size(); x++)
                {
                    if (i == selected.get(x))
                        repeat = true;
                }
            }
            selected.add(i);
        } 
        finally 
        {
            cerrojo.unlock();
        }
        return i;
    }
}
