/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */


package com.chuidiang.matematicas;

/**
 * Transformaci�n de coordenadas polares a cartesianas y viceversa.
 */
public class CartesianasPolares {
    /**
     * Se le pasan las coordenadas x e y de un punto y devuelve el �ngulo
     * correspondiente a las coordenadas polares de dicho punto, en radianes.
     */ 
    static public double dameAngulo (double x, double y)
    {
        return Math.atan2 (x,y);
    }
    
    /**
     * Se le pasan las coordenadas x e y de un punto y devuelve el m�dulo
     * correspondiente a las coordenadas polares de dicho punto.
     */
    static public double dameModulo (double x, double y)
    {
        return Math.sqrt (x*x + y*y);
    }
    
    /**
     * Se le pasa un punto en coordenadas polares (angulo en radianes) y 
     * devuelve el valor de x de dicho punto en coordenadas cartesianas.
     */
    static public double dameX (double modulo, double angulo)
    {
        return modulo*Math.sin(angulo);
    }
    
    /**
     * Se le pasa un punto en coordenadas polares (angulo en radianes) y
     * devuelve el valor de y de dicho punto en coordenadas cartesianas.
     */
    static public double dameY (double modulo, double angulo)
    {
        return modulo*Math.cos (angulo);
    }
}
