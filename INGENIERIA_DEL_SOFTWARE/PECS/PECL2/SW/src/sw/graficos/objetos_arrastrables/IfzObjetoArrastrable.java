/**
 * Javier Abell�n, 11 Junio 2006
 * 
 * Parte de la librer�a gr�fica
 */
package com.chuidiang.graficos.objetos_arrastrables;

import java.awt.event.MouseEvent;

/**
 * Interface que deben implementar aquellos objetos gr�ficos que sean
 * arrastables por el rat�n.
 * La clase ArrastradorObjetos admite objetos con esta interface y se encarga
 * de indicarles c�mo moverse en funci�n del arrastre del rat�n.
 * 
 * @author Chuidiang
 */
public interface IfzObjetoArrastrable 
{
    /**
     * El objeto gr�fico recibe la posici�n en la que debe dibujarse.
     * @param x La nueva x a partir de la que debe dibujarse
     * @param y La nueva y a partri de la que debe dibujarse
     * @return si el objeto necesita repintado
     */
    public boolean tomaNuevoOrigen(double x, double y);
    
    /**
     * El objeto gr�fico devuelve true si puede empezar a arrastrarse.
     * A esta clase se le pasar� el evento de rat�n que ha tenido lugar y la
     * clase indica si comienza a arrastrarse. Por ejemplo, porque se comienza
     * un arrastre en la posici�n x,y en la que est� ella.
     * @param event evento del rat�n
     * @param x La x del rat�n en coordenadas de usuario
     * @param y la y del rat�n en coordenadas de usuario
     * @return Si se debe comenzar el arrastre.
     */
    public boolean puedeComenzarArrastre(MouseEvent event, double x, double y);

    /**
     * Se avisa al objeto gr�fico arrastrable de que ha finalizado el arrastre
     * del rat�n.
     * @param e El evento de rat�n.
     * @param x La x donde finaliza el arrastre (coordenadas de usuario)
     * @param y La y donde finaliza el arrastre (coordenadas de usuario).
     * @return true si el objeto necesita repintado, false en caso contrario.
     */
	public boolean finalizaElArrastre(MouseEvent e, double x, double y);
}
