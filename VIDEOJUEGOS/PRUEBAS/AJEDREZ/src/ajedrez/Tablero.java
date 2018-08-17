/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import ajedrez.piezas.*;
import java.util.HashMap;

/**
 *
 * @author mr.blissfulgrin
 */
public final class Tablero 
{
    private static final char ESQUINA_SUPERIOR_IZQUIERDA = 0x250F;
    private static final char ESQUINA_SUPERIOR_DERECHA = 0x2513;
    private static final char ESQUINA_INFERIOR_IZQUIERDA = 0x2517;
    private static final char ESQUINA_INFERIOR_DERECHA = 0x251B;
    private static final char VERTICAL = 0x2502;
    private static final char HORIZONTAL = 0x2500;
    public static final int MAX = 7;
    
    private static final int MAX_PEONES = 8;
    private static final int MAX_ALFILES = 2;
    private static final int MAX_TORRES = 2;
    private static final int MAX_CABALLOS = 2;
    private static final int MAX_DAMAS = 1;
    private static final int MAX_REYES = 1;
    private static final int PEON = 0;
    private static final int ALFIL = 1;
    private static final int TORRE = 2;
    private static final int CABALLO = 3;
    private static final int DAMA = 4;
    private static final int REY = 5;
    int [][] cantidades;
    
    char [][] marco = new char [10][11];
    Celda [][] tablero = new  Celda[8][8];
    char [] indices = new char []{' ','0','1','2','3','4','5','6','7',' '};
       
    private final HashMap <String,Pieza> piezas;
    
    public Tablero ()
    {

            marco [0][0] = ESQUINA_SUPERIOR_IZQUIERDA;
            marco [9][0] = ESQUINA_INFERIOR_IZQUIERDA;
            marco [0][9] = ESQUINA_SUPERIOR_DERECHA;
            marco [9][9] = ESQUINA_INFERIOR_DERECHA;
            marco [0][10] = '\n';
            marco [9][10] = '\n';
            for (int y = 1; y < marco[0].length-2; y++)
            {
                marco [0] [y] = HORIZONTAL;
                marco [9][y] = HORIZONTAL;
                marco [y] [0] = VERTICAL;
                marco [y][9] = VERTICAL;
                marco [y][10] = '\n';
            }
            piezas = new HashMap();
            cantidades = new int[2][6];
            restaurar_tablero();
    }
    
    public void restaurar_tablero ()
    {
        piezas.clear();
        cantidades = new int [2][6];
        for (int x = 0; x < tablero.length; x++)
            for (int y = 0; y < tablero[0].length; y++) 
                if ((x+y)%2 == 0)
                    tablero[x][y] = new Celda (Celda.BLANCO);
                else
                    tablero[x][y] = new Celda (Celda.NEGRO);
    }
    
    public void draw()
    {
        System.out.println("");
        for (int x = 0; x < marco.length; x++)
        {
            System.out.print(indices[x]);
            for (int y = 0; y < marco[0].length; y++)
                if ((y < 1 || y > 8) || (x < 1 || x > 8))
                {
                    System.out.print(marco[x][y]);
                    if ((x == 0 || x == 9) && (y<9))
                      System.out.print(HORIZONTAL); 
                }
                else
                {
                    System.out.print(" "+tablero[x-1][y-1].toString());
                    if (y == 8)
                        System.out.print(" ");
                }
        }
        for (char i : indices)
            System.out.print(" "+i);
        System.out.println("");
        
        log();
        ocultar_movimientos();
    }
    
    private void log()
    {
        System.out.println("\n\t\tBlancas:\tNegras:");
        System.out.println("Peones:\t\t"+cantidades[0][PEON]+"\t\t"+cantidades[1][PEON]);
        System.out.println("Torres:\t\t"+cantidades[0][TORRE]+"\t\t"+cantidades[1][TORRE]);
        System.out.println("Caballos:\t"+cantidades[0][CABALLO]+"\t\t"+cantidades[1][CABALLO]);
        System.out.println("Alfiles:\t"+cantidades[0][ALFIL]+"\t\t"+cantidades[1][ALFIL]);
        System.out.println("Dama:\t\t"+cantidades[0][DAMA]+"\t\t"+cantidades[1][DAMA]);
        System.out.println("Rey:\t\t"+cantidades[0][REY]+"\t\t"+cantidades[1][REY]);
        
        System.out.printf("%-20s","\nNombre:");
        System.out.printf("%-10s","Tipo:");
        System.out.printf("%-10s","Color:");
        System.out.printf("%-14s","Posicion:");
        System.out.printf("%-10s%n","Estado:");
        piezas.values().forEach((p) -> {
            int x = p.obtener_posicion()[0];
            int y = p.obtener_posicion()[1];
            
            System.out.printf("%-20s",(p.getNombre()));
            System.out.printf("%-10s",(p.toString()));
            System.out.printf("%-10s",(p.getColor()));
            System.out.printf("%-13s",("["+x+","+y+"]"));
            System.out.printf("%-10s%n",(tablero[x][y].toString()));
        }); 
    }
    
    private void log (Pieza p)
    {
        int x = p.obtener_posicion()[0];
        int y = p.obtener_posicion()[1];

        System.out.printf("%-20s",(p.getNombre()));
        System.out.printf("%-10s",(p.toString()));
        System.out.printf("%-10s",(p.getColor()));
        System.out.printf("%-13s",("["+x+","+y+"]"));
        System.out.printf("%-10s%n",(tablero[x][y].toString()));
    }
    
    public void insertar_pieza(Pieza pieza)
    {
        if (piezas.containsKey(pieza.getNombre()))
            System.out.println("PIEZA YA EXISTE");
        else
        {
            int [] posicion = pieza.obtener_posicion();
            if ((tablero [posicion[0]][posicion[1]].hasPieza()) && (pieza.getColor()==tablero [posicion[0]][posicion[1]].getPieza().getColor()))
            {
                System.out.println("NO SE PUEDE OCUPAR LA CASILLA"); 
            }
            else
            {
                if (tablero [posicion[0]][posicion[1]].hasPieza())
                {
                    System.out.print("Pieza comida: \n");
                    log(tablero [posicion[0]][posicion[1]].getPieza());
                    eliminar_pieza(tablero [posicion[0]][posicion[1]].getPieza().getNombre());
                }
                boolean OK = false;
                int color;
                if (pieza.getColor()==Pieza.BLANCO)
                    color = 0;
                else
                    color = 1;
                if(pieza.getClass().equals(Peon.class))
                    if (cantidades[color][PEON] < MAX_PEONES)
                    {cantidades[color][PEON]++;OK=true;}
                if(pieza.getClass().equals(Alfil.class))
                    if (cantidades[color][ALFIL] < MAX_ALFILES)
                    {cantidades[color][ALFIL]++;OK=true;}
                if(pieza.getClass().equals(Torre.class))
                    if (cantidades[color][TORRE] < MAX_TORRES)
                    {cantidades[color][TORRE]++;OK=true;}
                if(pieza.getClass().equals(Caballo.class))
                    if (cantidades[color][CABALLO] < MAX_CABALLOS)
                    {cantidades[color][CABALLO]++;OK=true;}
                if(pieza.getClass().equals(Rey.class))
                    if (cantidades[color][REY] < MAX_REYES)
                    {cantidades[color][REY]++;OK=true;}
                if(pieza.getClass().equals(Dama.class))
                    if (cantidades[color][DAMA] < MAX_DAMAS)
                    {cantidades[color][DAMA]++;OK=true;}

                if (OK)
                {
                    this.piezas.put(pieza.getNombre(), pieza);
                    tablero [posicion[0]][posicion[1]].añadir_pieza(this.piezas.get(pieza.getNombre()));
                    mostrar_movimientos(pieza.getNombre());
                }
                else
                    System.out.println("NO SE PUEDEN CREAR MAS PIEZAS");
            }
        }
    }
    
    public void eliminar_pieza(String nombre)
    {
        if (piezas.containsKey(nombre))
        {
            Pieza pieza = piezas.get(nombre);
            int color;
            int x;
            int y;
            
            x = pieza.obtener_posicion()[0];
            y = pieza.obtener_posicion()[1];
            
            if (pieza.getColor()==Pieza.BLANCO)
                color = 0;
            else
                color = 1;
            if(pieza.getClass().equals(Peon.class))
                if (cantidades[color][PEON] > 0)
                {cantidades[color][PEON]--;}
            if(pieza.getClass().equals(Alfil.class))
                if (cantidades[color][ALFIL] > 0)
                {cantidades[color][ALFIL]--;}
            if(pieza.getClass().equals(Torre.class))
                if (cantidades[color][TORRE] > 0)
                {cantidades[color][TORRE]--;}
            if(pieza.getClass().equals(Caballo.class))
                if (cantidades[color][CABALLO] > 0)
                {cantidades[color][CABALLO]--;}
            if(pieza.getClass().equals(Rey.class))
                if (cantidades[color][REY] > 0)
                {cantidades[color][REY]--;}
            if(pieza.getClass().equals(Dama.class))
                if (cantidades[color][DAMA] > 0)
                {cantidades[color][DAMA]--;}
            
            piezas.remove(nombre);
            tablero[x][y].quitar_pieza();
        }
        else
            System.out.println("No existe la pieza");
    }
    
    public void mover (String nombre, int[] posicion)
    {
        if (piezas.containsKey(nombre))
        {
            Pieza p = piezas.get(nombre);
            if (movimientoPermitido(nombre,posicion))
            {
                eliminar_pieza(nombre);
                p.mover(posicion);
                insertar_pieza(p);
            }
            else
                System.out.println("MOVIMIENTO NO PERMITIDO");
        }
        else
            System.out.println("PIEZA NO EXISTE");
    } 
    
    public void game()
    {
        Peon pb1 = new Peon ("PB1",Pieza.BLANCO,new int[]{6,0});
        Peon pb2 = new Peon ("PB2",Pieza.BLANCO,new int[]{6,1});
        Peon pb3 = new Peon ("PB3",Pieza.BLANCO,new int[]{6,2});
        Peon pb4 = new Peon ("PB4",Pieza.BLANCO,new int[]{6,3});
        Peon pb5 = new Peon ("PB5",Pieza.BLANCO,new int[]{6,4});
        Peon pb6 = new Peon ("PB6",Pieza.BLANCO,new int[]{6,5});
        Peon pb7 = new Peon ("PB7",Pieza.BLANCO,new int[]{6,6});
        Peon pb8 = new Peon ("PB8",Pieza.BLANCO,new int[]{6,7});
        Peon pn1 = new Peon ("PN1",Pieza.NEGRO,new int[]{1,0});
        Peon pn2 = new Peon ("PN2",Pieza.NEGRO,new int[]{1,1});
        Peon pn3 = new Peon ("PN3",Pieza.NEGRO,new int[]{1,2});
        Peon pn4 = new Peon ("PN4",Pieza.NEGRO,new int[]{1,3});
        Peon pn5 = new Peon ("PN5",Pieza.NEGRO,new int[]{1,4});
        Peon pn6 = new Peon ("PN6",Pieza.NEGRO,new int[]{1,5});
        Peon pn7 = new Peon ("PN7",Pieza.NEGRO,new int[]{1,6});
        Peon pn8 = new Peon ("PN8",Pieza.NEGRO,new int[]{1,7});
        Torre tb1 = new Torre ("TB1",Pieza.BLANCO,new int[]{7,0});
        Torre tb2 = new Torre ("TB2",Pieza.BLANCO,new int[]{7,7});
        Torre tn1 = new Torre ("TN1",Pieza.NEGRO,new int[]{0,0});
        Torre tn2 = new Torre ("TN2",Pieza.NEGRO,new int[]{0,7});
        Caballo cb1 = new Caballo ("CB1",Pieza.BLANCO,new int[]{7,1});
        Caballo cb2 = new Caballo ("CB2",Pieza.BLANCO,new int[]{7,6});
        Caballo cn1 = new Caballo ("CN1",Pieza.NEGRO,new int[]{0,1});
        Caballo cn2 = new Caballo ("CN2",Pieza.NEGRO,new int[]{0,6});
        Alfil ab1 = new Alfil ("AB1",Pieza.BLANCO,new int[]{7,2});
        Alfil ab2 = new Alfil ("AB2",Pieza.BLANCO,new int[]{7,5});
        Alfil an1 = new Alfil ("AN1",Pieza.NEGRO,new int[]{0,2});
        Alfil an2 = new Alfil ("AN2",Pieza.NEGRO,new int[]{0,5});
        Rey rb = new Rey ("RB",Pieza.BLANCO,new int[]{0,4});
        Rey rn = new Rey ("RN",Pieza.NEGRO,new int[]{7,4});
        Dama db = new Dama ("DB",Pieza.BLANCO,new int[]{0,3});
        Dama dn = new Dama ("DN",Pieza.NEGRO,new int[]{7,3});
        
        insertar_pieza(pb1);
        insertar_pieza(pb2);
        insertar_pieza(pb3);
        insertar_pieza(pb4);
        insertar_pieza(pb5);
        insertar_pieza(pb6);
        insertar_pieza(pb7);
        insertar_pieza(pb8);
        insertar_pieza(pn1);
        insertar_pieza(pn2);
        insertar_pieza(pn3);
        insertar_pieza(pn4);
        insertar_pieza(pn5);
        insertar_pieza(pn6);
        insertar_pieza(pn7);
        insertar_pieza(pn8);
        insertar_pieza(tb1);
        insertar_pieza(tb2);
        insertar_pieza(tn1);
        insertar_pieza(tn2);
        insertar_pieza(cb1);
        insertar_pieza(cb2);
        insertar_pieza(cn1);
        insertar_pieza(cn2);
        insertar_pieza(ab1);
        insertar_pieza(ab2);
        insertar_pieza(an1);
        insertar_pieza(an2);
        insertar_pieza(dn);
        insertar_pieza(db);
        insertar_pieza(rn);
        insertar_pieza(rb);
        
        ocultar_movimientos();
    }
    
    private void crear_movimientos (String nombre)
    {
        if (piezas.containsKey(nombre))
        {
            Pieza pieza = piezas.get(nombre);
            pieza.crear_movimientos(tablero);
        }
        else
            System.out.println("PIEZA NO EXISTE");
    }
    
    public void mostrar_movimientos (String nombre)
    {
        if (piezas.containsKey(nombre))
        {
            crear_movimientos(nombre);
            Pieza pieza = piezas.get(nombre);
            pieza.mostrarMovimientos(tablero);
        }
        else 
            System.out.println("PIEZA NO EXISTE");
    }
    
    private void ocultar_movimientos()
    {
        for (Celda[] fila : tablero) {
            for (Celda celda : fila) {
                celda.reset();
            }
        }
    }
    
    private Boolean movimientoPermitido(String nombre, int [] posicion)
    {
        crear_movimientos(nombre);
        Pieza pieza = piezas.get(nombre);
        return pieza.movimientoPermitido(posicion);
    }
}
