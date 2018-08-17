/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */


package com.chuidiang.graficos;

/**
 * Observador de cambio de coordenadas de usuario en una EscalaGrafica.
 */
public interface ObservadorEscalaGrafica
{
    
    /**
     * Se llamar� a este m�todo cuando cambien las coordenadas de usuario de una
     * EscalaGrafica.
     * 
     * @param xMin x minima en coordenadas de usuario para el gr�fico
     * @param yMin la y m�nima
     * @param xMax la x m�xima
     * @param yMax la y m�xima
     */
    public void tomaNuevosExtremos(double xMin, double yMin, double xMax,
            double yMax);
}
