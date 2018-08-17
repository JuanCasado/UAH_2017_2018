/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_graficos;

import com.chuidiang.graficos.InterfaceEscalaGrafica;

/**
 * Interface para todos los objetos gr�ficos para que se puedan meter en una
 * clase Lienzo.
 */
public interface IfzObjetoGrafico
{
    /**
     * M�todo al que se llamar� cuando el objeto gr�fico tenga que dibujarse. Se
     * le pasa la escala (El Graphics con unidades de usuario en vez de pixels)
     * sobre el que tiene que dibujarse el objeto gr�fico.
     */
    public void dibujate(InterfaceEscalaGrafica escala);

    /** Devuelve si el objeto a modificado sus datos internos y por tanto
     * necesita que lo repinten
     * @return true si necesita repintado
     */
    public boolean necesitasRepintado();

    /**
     * A�ade observador para avisarle de cuando se necesita repintado de este
     * objeto grafico
     * @param o El observador
     */
    public void addObservadorRepintado(ObservadorRepintado o);

    /**
     * Borra el observador
     * @param o El observador
     */
    public void removeObservadorRepintado(ObservadorRepintado o);
}
