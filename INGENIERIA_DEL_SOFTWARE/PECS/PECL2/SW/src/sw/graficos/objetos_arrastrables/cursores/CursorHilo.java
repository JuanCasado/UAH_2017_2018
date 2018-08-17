/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_arrastrables.cursores;

import java.awt.Color;

import com.chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Cursor sobre un gr�fico formado por una l�nea vertical. El cursor se mueve
 * con el rat�n. Al hacer click con el bot�n izquierdo, se marca un cursor fijo
 * en la poisic�n del rat�n. Otro con el bot�n derecho.
 */
public class CursorHilo extends Cursor
{
    /**
     * Se le pasa si se quiere cursor vertical y si se quiere cursor horizontal
     * @param vertical true si se quiere cursor vertical.
     * @param horizontal true si se quiere cursor horizontal.
     */
    public CursorHilo(boolean vertical, boolean horizontal)
    {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    /**
     * Redibuja los cursores fijos y movil.
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        if (pintar)
        {
            dibujaLineaVertical(escala, x, y, this.colorMovil);
            dibujaLineaHorizontal(escala, x, y, this.colorMovil);
        }
        dibujaLineaVertical(escala, x1, y1, this.colorFijo);
        dibujaLineaHorizontal(escala, x1, y1, this.colorFijo);
        dibujaLineaVertical(escala, x2, y2, this.colorFijo);
        dibujaLineaHorizontal(escala, x2, y2, this.colorFijo);
    }

    /**
     * Dibuja una linea vertical que es el cursor.
     * 
     * @param escala
     *            La EscalaGrafica que hay que usar para dibujar el cursor.
     * @param x
     *            Posicion x en la que hay que dibujar el cursor
     * @param y
     *            Posicion y en la que hay que dibujar el cursor. Se ignora.
     * @param color
     *            Color del cursor.
     */
    private void dibujaLineaVertical(InterfaceEscalaGrafica escala, double x, double y,
            Color color)
    {
        if (!this.vertical)
            return;
        escala.pintaEjeY(x,color);
    }

    /**
     * Dibuja una linea horizontal que es el cursor.
     * 
     * @param escala
     *            La EscalaGrafica que hay que usar para dibujar el cursor.
     * @param x
     *            Posicion x en la que hay que dibujar el cursor. Se ignora
     * @param y
     *            Posicion y en la que hay que dibujar el cursor.
     * @param color
     *            Color del cursor.
     */
    private void dibujaLineaHorizontal(InterfaceEscalaGrafica escala, double x,
            double y, Color color)
    {
        if (!this.horizontal)
            return;
        escala.pintaEjeX(y,color);
    }

    /**
     * Indica si se quiere un hilo horizontal para el cursor.
     */
    private boolean horizontal;

    /**
     * Indica si se quiere un hilo vertical para el cursor.
     */
    private boolean vertical;
}
