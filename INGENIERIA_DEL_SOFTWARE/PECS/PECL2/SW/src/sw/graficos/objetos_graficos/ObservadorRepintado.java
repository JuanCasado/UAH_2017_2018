/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_graficos;

/**
 * Interface para observadores de necesidad de repintado de un objeto grafico.
 */
public interface ObservadorRepintado
{
    /**
     * M�todo al que se llama cuando el objeto necesita repintado
     */
    public void necesitoRepintado();
}
