/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez.piezas;

import ajedrez.Celda;
import ajedrez.Tablero;
import java.util.ArrayList;


/**
 *
 * @author mr.blissfulgrin
 */
public class Caballo extends Pieza
{
    public Caballo (String nombre, char color, int[] posicion)
    {
        super(nombre,color,Pieza.CABALLO,posicion);
    }
    
    @Override
    public void crear_movimientos(Celda[][] celdas)
    {
        movimientos.clear();
        //L1
        if (((posicion[0] -1) >= 0) && ((posicion[1] -2) >= 0))
        {
            if (celdas[posicion[0]-1][posicion[1]-2].hasPieza())
            {
                if (celdas[posicion[0]-1][posicion[1]-2].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]-1,posicion[1]-2});                  
                    movimientos.put(L1, fase);
                }
            }
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]-1,posicion[1]-2});
                movimientos.put(L1, fase);
            }
        }
        
        //L2
        if (((posicion[0] +1) <= Tablero.MAX) && ((posicion[1] +2) <= Tablero.MAX))
        {
            if (celdas[posicion[0]+1][posicion[1]+2].hasPieza())
            {
                if (celdas[posicion[0]+1][posicion[1]+2].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]+1,posicion[1]+2});                 
                    movimientos.put(L2, fase);
                }
            } 
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]+1,posicion[1]+2});
                movimientos.put(L2, fase);
            }
        }
        
        //L3
        if (((posicion[0] +1) <= Tablero.MAX) && ((posicion[1] -2) >= 0))
        {
            if (celdas[posicion[0]+1][posicion[1]-2].hasPieza())
            {
                if (celdas[posicion[0]+1][posicion[1]-2].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]+1,posicion[1]-2});                 
                    movimientos.put(L3, fase);
                }
            } 
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]+1,posicion[1]-2});
                movimientos.put(L3, fase);
            }
        }
        
        //L4
        if (((posicion[0] -1) >= 0) && ((posicion[1] +2) <= Tablero.MAX))
        {
            if (celdas[posicion[0]-1][posicion[1]+2].hasPieza())
            {
                if (celdas[posicion[0]-1][posicion[1]+2].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]-1,posicion[1]+2});                 
                    movimientos.put(L4, fase);                    
                }
            } 
            else                    
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]-1,posicion[1]+2});
                movimientos.put(L4, fase);
            }
        }
        
        //L5
        if (((posicion[0] -2) >= 0) && ((posicion[1] -1) >= 0))
        {
            if (celdas[posicion[0]-2][posicion[1]-1].hasPieza())
            {
                if (celdas[posicion[0]-2][posicion[1]-1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]-2,posicion[1]-1});                  
                    movimientos.put(L5, fase);
                }
            }
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]-2,posicion[1]-1});
                movimientos.put(L5, fase);
            }
        }
        
        //L6
        if (((posicion[0] +2) <= Tablero.MAX) && ((posicion[1] +1) <= Tablero.MAX))
        {
            if (celdas[posicion[0]+2][posicion[1]+1].hasPieza())
            {
                if (celdas[posicion[0]+2][posicion[1]+1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]+2,posicion[1]+1});                 
                    movimientos.put(L6, fase);
                }
            } 
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]+2,posicion[1]+1});
                movimientos.put(L6, fase);
            }
        }
        
        //L7
        if (((posicion[0] +2) <= Tablero.MAX) && ((posicion[1] -1) >= 0))
        {
            if (celdas[posicion[0]+2][posicion[1]-1].hasPieza())
            {
                if (celdas[posicion[0]+2][posicion[1]-1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]+2,posicion[1]-1});                 
                    movimientos.put(L7, fase);
                }
            } 
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]+2,posicion[1]-1});
                movimientos.put(L7, fase);
            }
        }
        
        //L8
        if (((posicion[0] -2) >= 0) && ((posicion[1] +1) <= Tablero.MAX))
        {
            if (celdas[posicion[0]-2][posicion[1]+1].hasPieza())
            {
                if (celdas[posicion[0]-2][posicion[1]+1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]-2,posicion[1]+1});                 
                    movimientos.put(L8, fase);                    
                }
            } 
            else                    
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]-2,posicion[1]+1});
                movimientos.put(L8, fase);
            }
        }
    }
}
