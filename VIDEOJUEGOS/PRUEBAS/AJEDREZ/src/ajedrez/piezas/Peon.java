/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez.piezas;

import ajedrez.Celda;
import ajedrez.Tablero;
import static ajedrez.piezas.Pieza.ARRIBA;
import java.util.ArrayList;


/**
 *
 * @author mr.blissfulgrin
 */
public class Peon extends Pieza
{
    public Peon (String nombre,char color, int[] posicion)
    {
        super(nombre,color,Pieza.PEON,posicion);
    }
    
    @Override
    public void crear_movimientos(Celda[][] celdas)
    {
        movimientos.clear();
        if (this.getColor()==Pieza.BLANCO)
        {
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

                    Boolean casilla_correcta = false;
                    if (posicion[0] == (Tablero.MAX-1))
                        casilla_correcta = true;

                    if (((posicion[0] -2) >= 0) && casilla_correcta)
                    {
                        if (celdas[posicion[0]-2][posicion[1]].hasPieza())
                        {
                            if (celdas[posicion[0]-2][posicion[1]].getPieza().getColor()!=this.getColor())
                            {
                                fase.add(new int [] {posicion[0]-2,posicion[1]});  
                            }
                        }
                        else
                        {
                            fase.add(new int [] {posicion[0]-2,posicion[1]});
                        }
                    }
                    movimientos.put(ARRIBA, fase);
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
            }
        }
        else
        {
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
                    
                    Boolean casilla_correcta = false;
                    if (posicion[0] == 1)
                        casilla_correcta = true;

                    if (((posicion[0] +2) >= 0) && casilla_correcta)
                    {
                        if (celdas[posicion[0]+2][posicion[1]].hasPieza())
                        {
                            if (celdas[posicion[0]+2][posicion[1]].getPieza().getColor()!=this.getColor())
                            {
                                fase.add(new int [] {posicion[0]+2,posicion[1]});  
                            }
                        }
                        else
                        {
                            fase.add(new int [] {posicion[0]+2,posicion[1]});
                        }
                    }
                    movimientos.put(ABAJO, fase);
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
            }
            //ABAJO_DERECHA
            if (((posicion[0] + 1) <= Tablero.MAX) && ((posicion[1] + 1) <= Tablero.MAX))
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
            }
        }  
    }
}
