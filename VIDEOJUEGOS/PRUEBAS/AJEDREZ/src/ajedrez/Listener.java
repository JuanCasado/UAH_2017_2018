/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import ajedrez.piezas.*;
import java.util.Scanner;

/**
 *
 * @author mr.blissfulgrin
 */
public class Listener
{
    public static final int ERROR = 0;
    public static final int CREATE = 1;
    public static final int REMOVE = 2;
    public static final int MOVE = 3;
    public static final int EXIT = 4;
    public static final int RESET = 5;
    public static final int GAME = 6;
    public static final int SELECT = 7;
    
    private Pieza pieza;
    private String nombre;
    private int[] posicion;
    
    //CREATE alfil A B 4 6
    //REMOVE alfil
    //MOVE alfil 5 2
    public int listen ()
    {
        Scanner listener = new Scanner(System.in);
        String cadena_bruta;
        String [] cadena_partida;
        
        char color;
        int x;
        int y;
        
        System.out.print("> ");
        cadena_bruta = listener.nextLine();
        
        cadena_partida = cadena_bruta.split(" ");
        cadena_partida[0] = cadena_partida[0].toUpperCase();
        
        switch (cadena_partida[0]) 
        {
            case "CREATE":                
                if(cadena_partida.length==6)
                {
                    nombre = cadena_partida[1];
                    x = Integer.parseInt(cadena_partida[4]);
                    y = Integer.parseInt(cadena_partida[5]);
                    if ((x <= Tablero.MAX) && (y <= Tablero.MAX))
                    {
                        cadena_partida[3] = cadena_partida[3].toUpperCase();
                        if (cadena_partida[3].equals(String.valueOf(Pieza.BLANCO))||cadena_partida[3].equals(String.valueOf(Pieza.NEGRO)))
                        {
                            if(cadena_partida[3].equals(String.valueOf(Pieza.BLANCO)))
                                color = Pieza.BLANCO;
                            else
                                color = Pieza.NEGRO;
                            cadena_partida[2] = cadena_partida[2].toUpperCase();
                            switch (cadena_partida[2])
                            {
                                case "A":
                                    pieza = new Alfil(nombre,color,new int[]{x,y});
                                    return CREATE;
                                case "P":
                                    pieza = new Peon(nombre,color,new int[]{x,y});
                                    return CREATE;
                                case "T":
                                    pieza = new Torre(nombre,color,new int[]{x,y});
                                    return CREATE;
                                case "C":
                                    pieza = new Caballo(nombre,color,new int[]{x,y});
                                    return CREATE;
                                case "R":
                                    pieza = new Rey(nombre,color,new int[]{x,y});
                                    return CREATE;
                                case "D":
                                    pieza = new Dama(nombre,color,new int[]{x,y});
                                    return CREATE;
                                default:
                                    return ERROR;
                            }
                        }
                    }
                }
                return ERROR;
            case "REMOVE":
                if(cadena_partida.length==2)
                {
                    nombre = cadena_partida[1];
                    return REMOVE;
                }
                return ERROR;
            case "SELECT":
                if(cadena_partida.length==2)
                {
                    nombre = cadena_partida[1];
                    return SELECT;
                }
                return ERROR;
            case "MOVE":
                if(cadena_partida.length==4)
                {
                    x = Integer.parseInt(cadena_partida[2]);
                    y = Integer.parseInt(cadena_partida[3]);
                    if ((x <= Tablero.MAX) && (y <= Tablero.MAX))
                    {
                        nombre = cadena_partida[1];
                        posicion = new int[]{x,y};
                        return MOVE;
                    }
                }
                return ERROR;
            case "EXIT":
                return EXIT;
            case "RESET":
                return RESET;
            case "GAME":
                return GAME;
        }
        
        return ERROR;       
    }
    
    
    public Pieza getPieza()
    {
        return pieza;
    }
    public String getNombre()
    {
        return nombre;
    }
    public int[] getPosicion()
    {
        return posicion;
    }
}
