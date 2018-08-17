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
public class Rey extends Pieza
{
    public Rey (String nombre,char color, int[] posicion)
    {
        super(nombre,color,Pieza.REY,posicion);
    }
    
    @Override
    public void crear_movimientos(Celda[][] celdas)
    {
        movimientos.clear();
        //IZQUIERDA
        if ((posicion[1] -1) >= 0)
        {
            if (celdas[posicion[0]][posicion[1]-1].hasPieza())
            {                
                if (celdas[posicion[0]][posicion[1]-1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0],posicion[1]-1});  
                    movimientos.put(IZQUIERDA, fase);
                }
                
            }
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0],posicion[1]-1});
                movimientos.put(IZQUIERDA, fase);
            }
        }

        //ARRIBA
        if ((posicion[0] -1) >= 0)
        {
            if (celdas[posicion[0]-1][posicion[1]].hasPieza())
            {
                if (celdas[posicion[0]-1][posicion[1]].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]-1,posicion[1]});  
                    movimientos.put(ARRIBA, fase);
                }
            }
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]-1,posicion[1]});
                movimientos.put(ARRIBA, fase);
            }
        }
        
        //DERECHA
        if ((posicion[1] +1) <= Tablero.MAX)
        {
            if (celdas[posicion[0]][posicion[1]+1].hasPieza())
            {
                if (celdas[posicion[0]][posicion[1]+1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0],posicion[1]+1});           
                    movimientos.put(DERECHA, fase);
                }       
            }
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0],posicion[1]+1});
                movimientos.put(DERECHA, fase);
            }
        }
        
        //ABAJO
        if ((posicion[0] +1) <= Tablero.MAX)
        {
            if (celdas[posicion[0]+1][posicion[1]].hasPieza())
            {
                if (celdas[posicion[0]+1][posicion[1]].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]+1,posicion[1]});           
                    movimientos.put(ABAJO, fase);
                }       
            }
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]+1,posicion[1]});
                movimientos.put(ABAJO, fase);
            }
        }
        
        //ARRIBA_IZQUIERDA
        if (((posicion[0] -1) >= 0) && ((posicion[1] -1) >= 0))
        {
            if (celdas[posicion[0]-1][posicion[1]-1].hasPieza())
            {
                if (celdas[posicion[0]-1][posicion[1]-1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]-1,posicion[1]-1});                  
                    movimientos.put(ARRIBA_IZQUIERDA, fase);
                }
            }
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]-1,posicion[1]-1});
                movimientos.put(ARRIBA_IZQUIERDA, fase);
            }
        }
        
        //ABAJO_DERECHA
        if (((posicion[0] +1) <= Tablero.MAX) && ((posicion[1] +1) <= Tablero.MAX))
        {
            if (celdas[posicion[0]+1][posicion[1]+1].hasPieza())
            {
                if (celdas[posicion[0]+1][posicion[1]+1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]+1,posicion[1]+1});                 
                    movimientos.put(ABAJO_DERECHA, fase);
                }
            } 
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]+1,posicion[1]+1});
                movimientos.put(ABAJO_DERECHA, fase);
            }
        }
        
        //IZQUIERDA_ABAJO
        if (((posicion[0] +1) <= Tablero.MAX) && ((posicion[1] -1) >= 0))
        {
            if (celdas[posicion[0]+1][posicion[1]-1].hasPieza())
            {
                if (celdas[posicion[0]+1][posicion[1]-1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]+1,posicion[1]-1});                 
                    movimientos.put(IZQUIERDA_ABAJO, fase);
                }
            } 
            else
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]+1,posicion[1]-1});
                movimientos.put(IZQUIERDA_ABAJO, fase);
            }
        }
        
        //DERECHA_ARRIBA
        if (((posicion[0] -1) >= 0) && ((posicion[1] +1) <= Tablero.MAX))
        {
            if (celdas[posicion[0]-1][posicion[1]+1].hasPieza())
            {
                if (celdas[posicion[0]-1][posicion[1]+1].getPieza().getColor()!=this.getColor())
                {
                    ArrayList <int[]> fase = new ArrayList <>();
                    fase.add(new int [] {posicion[0]-1,posicion[1]+1});                 
                    movimientos.put(DERECHA_ARRIBA, fase);                    
                }
            } 
            else                    
            {
                ArrayList <int[]> fase = new ArrayList <>();
                fase.add(new int [] {posicion[0]-1,posicion[1]+1});
                movimientos.put(DERECHA_ARRIBA, fase);
            }
        }
    }
}
