package com.chuidiang.graficos.objetos_arrastrables;

import java.awt.event.MouseEvent;

import com.chuidiang.graficos.objetos_graficos.ObservadorRaton;

/**
 * Clase que se encarga de tratar los eventos de rat�n para decidir cuando
 * empiza o no un arrastre de rat�n, de forma que:
 * - Le pregunta al objeto arrastrable si se puede o no empezar un arrastre.
 * - Avisa al objeto arrastrable del arrastre.
 * - Avisa al objeto arrastrable de cuando termina el arrastre.
 * 
 * @author Chuidiang
 *
 */

public class ArrastradorObjetos implements ObservadorRaton
{
	/**
	 * Objeto que se arrastrar� con el movimiento del rat�n.
	 */
	private IfzObjetoArrastrable objetoArrastrable;

	public ArrastradorObjetos(IfzObjetoArrastrable objetoArrastrable)
	{
		this.objetoArrastrable = objetoArrastrable;
	}

	/**
	 * M�todo al que se llama cuando se produce un evento de rat�n en el
	 * pintado.
	 */
	public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y)
	{
		if (!arrastrando)
		{
            preparaPosibleArrastreSiProcede(e, tipoEvento, x, y);
            return comienzaElArrastre(tipoEvento, x, y);
		} 
		else
        {
            return arrastraOTerminaElArrastre(e, tipoEvento, x, y);
        }
	}

    private boolean arrastraOTerminaElArrastre(MouseEvent e, int tipoEvento, double x, double y)
    {
        if (tipoEvento == ObservadorRaton.ARRASTRE)
        {
        	return objetoArrastrable.tomaNuevoOrigen(x, y);
        } 
        else
        {
        	boolean retorno = objetoArrastrable.finalizaElArrastre(e, x, y);
            posibleArrastre=false;
        	arrastrando = false;
            numeroObjetosArrastrandose--;
        	return retorno;
        }
    }

    /**
     * Todav�a no se est� arrastrando ning�n objeto, pero llega un evento
     * de arrastre de rat�n. Si todo estaba preparado para el arrastre, se
     * comienza a arrastrar el objeto.
     * @param tipoEvento Tipo de evento seg�n ObservadorRaton
     * @param x x del evento en coordenadas de usuario
     * @param y y del evento en coordenadas de usuario
     * @return true si el objeto necesita repintado.
     */
    private boolean comienzaElArrastre(int tipoEvento, double x, double y)
    {
        if (posibleArrastre==true)
        {
        	if (tipoEvento == ObservadorRaton.ARRASTRE)
        	{
                posibleArrastre=false;
        		arrastrando = true;
        		return objetoArrastrable.tomaNuevoOrigen(x, y);
        	}
        	else if (tipoEvento != ObservadorRaton.PULSADO)
            {
                posibleArrastre=false;
                numeroObjetosArrastrandose--;
            }
        }
        return false;
    }

    /**
     * Si se acaba de pulsar el rat�n sobre el gr�fico y alg�n objeto 
     * arrastrable se siente "aludido" para arrastrarse, se preparan
     * internamente todas las variables para un posible arrastre de 
     * objeto gr�fico.
     * @param e El evento de rat�n.
     * @param tipoEvento El tipo de evento seg�n ObservadorRaton.
     * @param x x del evento en coordendadas de usuario.
     * @param y y del evento en coordenadas de usuario.
     */
    private void preparaPosibleArrastreSiProcede(MouseEvent e, int tipoEvento, double x, double y)
    {
        if (tipoEvento == ObservadorRaton.PULSADO)
            if (numeroObjetosArrastrandose<numeroMaximoObjetosArrastradosSimultaneamente)
        		if (objetoArrastrable.puedeComenzarArrastre(e,x,y))
        		{
        			posibleArrastre=true;
                    numeroObjetosArrastrandose++;
        		}
    }

	/** Si el arrastre est� en marcha. */
	private boolean arrastrando = false;
    
    /** Si se ha hecho click y el objeto arrastrable confirma que se puede
     * arrastrar, pero todav�a no ha comenzado el arrastre */
    private boolean posibleArrastre=false;
    
    /** Numero m�ximo de objetos que se puede arrastrar simultaneamente */
    private static int numeroMaximoObjetosArrastradosSimultaneamente=1;
    
    /** Numero objetos arrastrandose en ese momento */
    private static int numeroObjetosArrastrandose=0;

    /**
     * Devuelve el n�mero m�ximo de objetos que se pueden arrastrar a la vez.
     * 
     * @return n�mero m�ximo de objetos arrastrables simult�neamente.
     */
	public static int getNumeroMaximoObjetosArrastradosSimultaneamente()
	{
		return numeroMaximoObjetosArrastradosSimultaneamente;
	}

    /**
     * N�mero m�ximo de objetos que se pueden arrastrar simult�neamente.
     * Por defecto es 1. Si se cambia este valor por encima de 1, es posible
     * que dos objetos que est�n uno encima de otro sean imposibles de separar,
     * puesto que al arrastrar, ambos comenzar�n a arrastrarse.
     * 
     * @param numeroMaximoObjetosArrastradosSimultaneamente
     */
	public static void setNumeroMaximoObjetosArrastradosSimultaneamente(
			int numeroMaximoObjetosArrastradosSimultaneamente)
	{
		ArrastradorObjetos.numeroMaximoObjetosArrastradosSimultaneamente = numeroMaximoObjetosArrastradosSimultaneamente;
	}
}
