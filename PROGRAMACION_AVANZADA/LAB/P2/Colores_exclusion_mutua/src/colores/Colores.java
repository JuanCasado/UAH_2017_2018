/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colores;

import java.awt.Color;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mr.blissfulgrin
 */
public class Colores 
{
    private final Color[] paleta;
    private final Lock[] selected;
    
    public Colores()
    {        
        paleta = new Color[11];
        selected = new ReentrantLock[11];
        for (int i = 0; i < selected.length; i++) {
            selected[i] = new ReentrantLock();
            
        }
        
        
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
        for(int x = 0; x < paleta.length; x++)
        {
            if (paleta[x]==c)
                selected[x].unlock();
        }
    }
    
    public Color getColor()
    {
        int i = (int)(Math.random()*10);
        selected[i].lock();
        
        return paleta[i];
    }
}
