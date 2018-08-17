/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez.piezas;

import ajedrez.Celda;
import ajedrez.Tablero;
import static ajedrez.piezas.Pieza.ABAJO_DERECHA;
import static ajedrez.piezas.Pieza.ARRIBA_IZQUIERDA;
import static ajedrez.piezas.Pieza.DERECHA_ARRIBA;
import static ajedrez.piezas.Pieza.IZQUIERDA_ABAJO;
import java.util.ArrayList;


/**
 *
 * @author mr.blissfulgrin
 */
public class Alfil extends Pieza
{
    public Alfil (String nombre, char color, int[] posicion)
    {
        super(nombre,color,Pieza.ALFIL,posicion);
    }
    
    @Override
    public void crear_movimientos(Celda[][] celdas)
    {
        movimientos.clear();
        //ARRIBA_IZQUIERDA
        int x = 1;
        Boolean continuar = true;
        ArrayList <int[]> faseI = new ArrayList <>();
        while ((((posicion[0] -x) >= 0) && ((posicion[1] -x) >= 0)&&continuar))
        {
            if (celdas[posicion[0]-x][posicion[1]-x].hasPieza())
            {
                if (celdas[posicion[0]-x][posicion[1]-x].getPieza().getColor()!=this.getColor())
                {
                    faseI.add(new int [] {posicion[0]-x,posicion[1]-x});   
                }
                continuar = false;
            }
            else
            {
                faseI.add(new int [] {posicion[0]-x,posicion[1]-x});
            }
            x++;
        }
        movimientos.put(ARRIBA_IZQUIERDA, faseI);
        
        //ABAJO_DERECHA
        x = 1;
        continuar = true;
        ArrayList <int[]> faseD = new ArrayList <>();
        while ((((posicion[0] +x) <= Tablero.MAX) && ((posicion[1] +x) <= Tablero.MAX)&&continuar))
        {
            if (celdas[posicion[0]+x][posicion[1]+x].hasPieza())
            {
                if (celdas[posicion[0]+x][posicion[1]+x].getPieza().getColor()!=this.getColor())
                {
                    faseD.add(new int [] {posicion[0]+x,posicion[1]+x}); 
                }
                continuar = false;
            } 
            else
            {
                faseD.add(new int [] {posicion[0]+x,posicion[1]+x});
            }
            x++;
        }
        movimientos.put(ABAJO_DERECHA, faseD);
        
        //IZQUIERDA_ABAJO
        x = 1;
        continuar = true;
        ArrayList <int[]> faseA = new ArrayList <>();
        while ((((posicion[0] +x) <= Tablero.MAX) && ((posicion[1] -x) >= 0)&&continuar))
        {
            if (celdas[posicion[0]+x][posicion[1]-x].hasPieza())
            {
                if (celdas[posicion[0]+x][posicion[1]-x].getPieza().getColor()!=this.getColor())
                {
                    faseA.add(new int [] {posicion[0]+x,posicion[1]-x}); 
                }
                continuar = false;
            } 
            else
            {
                faseA.add(new int [] {posicion[0]+x,posicion[1]-x});
            }
            x++;
        }
        movimientos.put(IZQUIERDA_ABAJO, faseA);
        
        //DERECHA_ARRIBA
        x = 1;
        continuar = true;    
        ArrayList <int[]> faseC = new ArrayList <>();
        while ((((posicion[0] -x) >= 0) && ((posicion[1] +x) <= Tablero.MAX)&&continuar))
        {
            if (celdas[posicion[0]-x][posicion[1]+x].hasPieza())
            {
                if (celdas[posicion[0]-x][posicion[1]+x].getPieza().getColor()!=this.getColor())
                {                    
                    faseC.add(new int [] {posicion[0]-x,posicion[1]+x});    
                }
                continuar = false;
            } 
            else                    
            {
                faseC.add(new int [] {posicion[0]-x,posicion[1]+x});                
            }
            x++;
        }
        movimientos.put(DERECHA_ARRIBA, faseC);  
    }
}
