/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import ajedrez.piezas.Pieza;

/**
 *
 * @author mr.blissfulgrin
 */
public final class Celda 
{
    public static final int OCUPADA = 0;
    public static final int PATH = 1;
    public static final int VACIA = 2;
    public static final int COMIBLE = 3;
    private boolean [] estado;
    
    public static final char BLANCO = 0x2588;
    public static final char NEGRO = 0;
    
    private final char color;
    private Pieza pieza;
    

    public Celda (char color)
    {
        if (color != BLANCO && color != NEGRO)
            throw new UnsupportedOperationException("BAD CREATION (CELDA)");
        
        this.color = color;
        estado = new boolean[4];
        cambiar_estado(VACIA);
    }
    
    private void cambiar_estado(int estado)
    {
        for(int x = 0; x < this.estado.length; x++)
            this.estado[x] = false;
        this.estado[estado] = true;
    }
    
    public void añadir_pieza (Pieza pieza)
    {
        cambiar_estado(OCUPADA);
        this.pieza = pieza;
    }
    public void quitar_pieza ()
    {
        cambiar_estado(VACIA);
    }
    public void set_path (Pieza pieza)
    {
        if (hasPieza() && pieza.getColor()!=this.pieza.getColor())
            cambiar_estado(COMIBLE);
        else if (!hasPieza())
            cambiar_estado(PATH);
    }
    public void reset ()
    {
        if (hasPieza())
            cambiar_estado(OCUPADA);
        else if (estado[PATH])
            cambiar_estado(VACIA);
    }
    
    @Override
    public String toString()
    {
        if (estado[OCUPADA])
            return pieza.toString();
        else if (estado[COMIBLE])
            return "o";
        else if (estado[PATH])
            return "x";
        else
            return String.valueOf(color);    
    }
    
    public Boolean hasPieza ()
    {
        return estado[OCUPADA] || estado[COMIBLE];
    }
    
    public Pieza getPieza ()
    {
        return pieza;
    }
}
