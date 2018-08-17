/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_graficos;

import java.awt.event.MouseEvent;

/**
 * Interface com�n para todos los observadores de evento del rat�n en un Lienzo.
 */
public interface ObservadorRaton
{
    /**
     * M�todo al que se llamar� cuando se produzca un evento de rat�n.
     * 
     * @param e
     *            El evento
     * @param tipoEvento
     *            Uno de los valores definidos en esta clase. Indica el tipo de
     *            evento.
     * @param x
     *            Posicion x en coordenadas de usuario donde se ha producido el
     *            evento de rat�n.
     * @param y
     *            Posicion y en coordenadas de usuario donde se ha producido el
     *            evento de rat�n.
     * @return true si el gr�fico necesita repintado despu�s de tratar el evento
     */
    public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y);

    /**
     * El rat�n se mueve sobre el gr�fico.
     */
    public final int MOVIMIENTO = 0;

    /**
     * Se rat�n se mueve sobre el gr�fico con el bot�n apretado.
     */
    public final int ARRASTRE = 1;

    /**
     * Se ha hecho click sobre el grafico.
     */
    public final int CLICK = 2;

    /**
     * Se ha pulsado el bot�n del rat�n con el gr�fico.
     */
    public final int PULSADO = 3;

    /**
     * Se ha soltado el bot�n del rat�n en el gr�fico.
     */
    public final int SOLTADO = 4;

    /**
     * El rat�n entra en el Lienzo.
     */
    public final int ENTRA = 5;

    /**
     * El rat�n sale del Lienzo
     */
    public final int SALE = 6;
}
