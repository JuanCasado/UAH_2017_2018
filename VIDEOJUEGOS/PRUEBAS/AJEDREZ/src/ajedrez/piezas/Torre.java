/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez.piezas;

import ajedrez.Celda;
import ajedrez.Tablero;
import static ajedrez.piezas.Pieza.IZQUIERDA;
import java.util.ArrayList;


/**
 *
 * @author mr.blissfulgrin
 */
public class Torre extends Pieza
{
    public Torre (String nombre, char color, int[] posicion)
    {
        super(nombre,color,Pieza.TORRE,posicion);
    }
    
    @Override
    public void crear_movimientos(Celda[][] celdas)
    {
        movimientos.clear();
        //IZQUIERDA
        int x = 1;
        Boolean continuar = true;
        ArrayList <int[]> faseI = new ArrayList <>();
        while (((posicion[1] - x) >= 0) && continuar)
        {
            if (celdas[posicion[0]][posicion[1]-x].hasPieza())
            {                
                if (celdas[posicion[0]][posicion[1]-x].getPieza().getColor()!=this.getColor())
                {
                    faseI.add(new int [] {posicion[0],posicion[1]-x});                      
                }
                continuar = false;
            }
            else
            {
                faseI.add(new int [] {posicion[0],posicion[1]-x});
            }
            x++;
        }
        movimientos.put(IZQUIERDA, faseI);

        
        //ARRIBA
        x = 1;
        continuar = true;
        ArrayList <int[]> faseA = new ArrayList <>();
        while (((posicion[0] -x) >= 0) && continuar)
        {
            if (celdas[posicion[0]-x][posicion[1]].hasPieza())
            {
                if (celdas[posicion[0]-x][posicion[1]].getPieza().getColor()!=this.getColor())
                {
                    faseA.add(new int [] {posicion[0]-x,posicion[1]});                      
                }
                continuar = false;
            }
            else
            {
                faseA.add(new int [] {posicion[0]-x,posicion[1]});
            }
            x++;
        }
        movimientos.put(ARRIBA, faseA);
        
        //DERECHA
        x = 1;
        continuar = true;
        ArrayList <int[]> faseD = new ArrayList <>();
        while (((posicion[1] +x) <= Tablero.MAX) && continuar)
        {
            if (celdas[posicion[0]][posicion[1]+x].hasPieza())
            {
                if (celdas[posicion[0]][posicion[1]+x].getPieza().getColor()!=this.getColor())
                {
                    faseD.add(new int [] {posicion[0],posicion[1]+x}); 
                }
                continuar = false;       
            }
            else
            {
                faseD.add(new int [] {posicion[0],posicion[1]+x});
            }
            x++;
        }
        movimientos.put(DERECHA, faseD);
        
        //ABAJO
        x = 1;
        continuar = true;
        ArrayList <int[]> faseC = new ArrayList <>();
        while (((posicion[0] +x) <= Tablero.MAX) && continuar)
        {
            if (celdas[posicion[0]+x][posicion[1]].hasPieza())
            {
                if (celdas[posicion[0]+x][posicion[1]].getPieza().getColor()!=this.getColor())
                {
                    faseC.add(new int [] {posicion[0]+x,posicion[1]});           
                }
                continuar = false;      
            }
            else
            {
                faseC.add(new int [] {posicion[0]+x,posicion[1]});
            }
            x++;
        }
        movimientos.put(ABAJO, faseC);
    }
}
