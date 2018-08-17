/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

/**
 *
 * @author mr.blissfulgrin
 */
public class AJEDREZ 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Tablero t = new Tablero();
        Listener l = new Listener();
        Boolean control = true;
        Boolean game = false;
        
        while (control)
        {
            t.draw();
            
            if (game)
            {
                switch(l.listen())
                {
                    case Listener.MOVE:
                        t.mover(l.getNombre(), l.getPosicion());
                        break;
                    case Listener.SELECT:
                        t.mostrar_movimientos(l.getNombre());
                        break;
                    case Listener.RESET:
                        t.restaurar_tablero();
                        game = false;
                        break;
                    case Listener.EXIT:
                        control = false;
                        break;
                    default:
                         System.out.println("Comando incorrecto in GAME");
                         break;
                }
            }
            else
            {
                switch(l.listen())
                {
                    case Listener.CREATE:
                        t.insertar_pieza(l.getPieza());
                        break;
                    case Listener.SELECT:
                        t.mostrar_movimientos(l.getNombre());
                        break;
                    case Listener.MOVE:
                        t.mover(l.getNombre(), l.getPosicion());
                        break;
                    case Listener.REMOVE:
                        t.eliminar_pieza(l.getNombre());
                        break;
                    case Listener.ERROR:
                        System.out.println("Comando incorrecto");
                        break;
                    case Listener.RESET:
                        t.restaurar_tablero();
                        break;
                    case Listener.GAME:
                        t.restaurar_tablero();
                        game = true;
                        t.game();
                        break;
                    case Listener.EXIT:
                        control = false;
                        break;
                }
            }
        }
    }
}
