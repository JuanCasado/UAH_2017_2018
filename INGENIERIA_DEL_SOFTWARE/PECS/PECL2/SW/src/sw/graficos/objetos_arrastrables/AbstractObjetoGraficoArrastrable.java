/**
 * Javier Abell�n, 3 Oct 2006
 * 
 * Objeto gr�fico arrastrable abstracto.
 */
package com.chuidiang.graficos.objetos_arrastrables;

import java.awt.event.MouseEvent;

import com.chuidiang.graficos.objetos_graficos.AbstractObjetoGrafico;
import com.chuidiang.graficos.objetos_graficos.ObservadorRaton;


/**
 * Es un objeto gr�fico abstracto con el c�digo necesario para que se pueda
 * arrastar con el rat�n.
 * Las clases hijas deben definir los m�todos:
 * 
 * public boolean tomaNuevoOrigen(double x, double y)
 * 
 * En este m�todo se le pasan las nuevas coordenadas donde debe dibujarse el
 * objeto gr�fico. La clase hija �nicamente deber�a en este m�todo guardarse
 * las nuevas coordenadas, en espera que se llame a su m�todo dibujate(). 
 * Debe devolver true si desp�s de la llamada a este m�todo el objeto gr�fico
 * necesita repintarse. Lo habitual es que devuelva false.
 * 
 * public boolean puedeComenzarArrastre(MouseEvent event, double x, double y)
 * 
 * M�todo al que se llama cuando se produce una pulsaci�n del bot�n de rat�n
 * sobre el gr�fico. El objeto gr�fico hijo debe decidir si esas coordenadas
 * caen sobre �l para devolver true en caso de que considere que s� y que
 * puede comenzar a arrastrarse. false si considera que esas coordenadas
 * no caen sobre �l y no debe arrastrarse.
 * 
 * public boolean finalizaElArrastre(MouseEvent e, double x, double y)
 * 
 * M�todo al que se llama cuando finaliza el arrastre. Normalmente los objetos
 * gr�ficos hijos no necesitan hacer nada en este m�todo. Los objetos gr�ficos
 * hijos deben devolver true si necesitan repintado despu�s de llamar a este
 * m�todo. Lo habitual es que devuelvan false.
 * 
 * @author Chuidiang
 */
public abstract class AbstractObjetoGraficoArrastrable extends
		AbstractObjetoGrafico implements ObservadorRaton, IfzObjetoArrastrable
{
    /**
     * Clase con el c�digo de arrastre de objetos.
     */
	private ArrastradorObjetos arrastradorObjetos;

    
    /**
     * Construye una instancia de esta clase.
     */
	public AbstractObjetoGraficoArrastrable()
	{
		arrastradorObjetos = new ArrastradorObjetos(this);
	}

    /**
     * Trata los eventos de rat�n, decidiendo si el evento de rat�n es de
     * inicio de arrastre, un arrastre o fin de arrastre, para llamar a los
     * m�todos abstractos correspondientes.
     */
	public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y)
	{
		return arrastradorObjetos.eventoRaton(e, tipoEvento, x, y);
	}
}
