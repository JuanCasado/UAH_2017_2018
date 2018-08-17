/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez.piezas;

import ajedrez.Celda;
import ajedrez.Tablero;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Pieza 
{
    public static final int DERECHA = 0;
    public static final int DERECHA_ARRIBA = 1;
    public static final int ARRIBA = 2;
    public static final int ARRIBA_IZQUIERDA = 3;
    public static final int IZQUIERDA = 4;
    public static final int IZQUIERDA_ABAJO = 5;
    public static final int ABAJO = 6;
    public static final int ABAJO_DERECHA = 7;
    public static final int L1 = 8;
    public static final int L2 = 9;
    public static final int L3 = 10;
    public static final int L4 = 11;
    public static final int L5 = 12;
    public static final int L6 = 13;
    public static final int L7 = 14;
    public static final int L8 = 15;
    protected HashMap <Integer,ArrayList<int[]>> movimientos;
    
    protected static final char ALFIL = 'A';
    protected static final char PEON = 'P';
    protected static final char TORRE = 'T';
    protected static final char CABALLO = 'C';
    protected static final char REY = 'R';
    protected static final char DAMA = 'D';
    public static final char NEGRO = 'N';
    public static final char BLANCO = 'B';
    
    private final String nombre;
    private final char simbolo;
    protected int[] posicion;
    private final char color;
    
    private Boolean movida;
    
    /*private void crear_movimientos ()
    {
        if (posicion[1] -1 > 0)
            movimientos[IZQUIERDA] = new int [] {posicion[0],posicion[1]-1};
        else
            movimientos[IZQUIERDA] = FUERA;
        
        if (posicion[0] -1 > 0)
            movimientos[ARRIBA] = new int [] {posicion[0]-1,posicion[1]};
        else
            movimientos[ARRIBA] = FUERA;
        
        if (posicion[1] +1 < MAX)
            movimientos[DERECHA] = new int [] {posicion[0],posicion[1]+1};
        else
            movimientos[DERECHA] = FUERA;
        
        if (posicion[0] +1 < MAX)
            movimientos[ABAJO] = new int [] {posicion[0]+1,posicion[1]};
        else
            movimientos[ABAJO] = FUERA;
        
        if ((posicion[0] -1 > 0) && (posicion[1] -1 > 0))
            movimientos[ARRIBA_IZQUIERDA] = new int [] {posicion[0]-1,posicion[1]-1};
        else
            movimientos[ARRIBA_IZQUIERDA] = FUERA;
        
        if ((posicion[0] +1 < MAX) && (posicion[1] +1 < MAX))
            movimientos[ABAJO_DERECHA] = new int [] {posicion[0]+1,posicion[1]+1};
        else
            movimientos[ABAJO_DERECHA] = FUERA;
        
        if ((posicion[0] +1 < MAX) && (posicion[1] -1 >0))
            movimientos[IZQUIERDA_ABAJO] = new int [] {posicion[0]+1,posicion[1]-1};
        else
            movimientos[IZQUIERDA_ABAJO] = FUERA;
        
        if ((posicion[0] -1 > 0) && (posicion[1] +1 < MAX))
            movimientos[DERECHA_ARRIBA] = new int [] {posicion[0]-1,posicion[1]+1};
        else
            movimientos[DERECHA_ARRIBA] = FUERA;
        
        
    }*/
    
    public Pieza(String nombre,char color, char simbolo, int[] posicion)
    {
        if ((posicion.length!=2) || (posicion[0]>Tablero.MAX) || (posicion[1] > Tablero.MAX)||((color!=NEGRO) && (color!=BLANCO)))
            throw new UnsupportedOperationException("BAD CREATION (PIEZA)");
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.posicion = posicion;
        this.color = color;
        this.movida = false;
        movimientos = new HashMap<>();
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(simbolo);
    }
    
    public int[] obtener_posicion()
    {
        return posicion;
    }
    
    public HashMap <Integer,ArrayList<int[]>> obtener_movimientos()
    {        
        return movimientos;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public char getColor()
    {
        return color;
    }
    
    public void mover (int [] posicion)
    {
        this.movida = true;
        this.posicion = posicion;
    }
    
    public Boolean movimientoPermitido (int [] movimiento)
    {
        int [] place;
        for (ArrayList<int[]> array : movimientos.values())
        {
            for (int x = 0; x < array.size(); x++)
            {
                place = (int[])array.get(x);
                if (Arrays.equals(movimiento, place))
                    return true;
            }
        }
        return false;
    }
    
    public void mostrarMovimientos (Celda[][] celdas)
    {
        int [] place;
        for (ArrayList<int[]> array : movimientos.values())
        {
            for (int x = 0; x < array.size(); x++)
            {
                place = (int[])array.get(x);
                celdas[place[0]][place[1]].set_path(Pieza.this);
            }
        } 
    }
    protected Boolean fue_movida()
    {
        return movida;
    }
    
    public abstract void crear_movimientos(Celda[][] celdas);
}
