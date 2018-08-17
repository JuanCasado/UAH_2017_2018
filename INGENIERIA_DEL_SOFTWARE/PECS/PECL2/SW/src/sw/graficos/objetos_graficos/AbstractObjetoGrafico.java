/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_graficos;

import java.util.LinkedList;

/**
 * Clase abstracta para facilicitar la implementaci�n de los objetos graficos
 */
public abstract class AbstractObjetoGrafico implements IfzObjetoGrafico
{
    /** Lista de observadores de este objeto */
    private LinkedList<ObservadorRepintado> observadores = 
        new LinkedList<ObservadorRepintado>();

    /** Indica si el objeto necesita repintado */
    private boolean necesitoRepintado = false;

    /**
     * Devuelve true si se necesita repintado y false en caso contrario.
     * Supone que si alguien le pregunta es porque lo van a repintar y pone
     * a false el flag.
     */
    public boolean necesitasRepintado()
    {
        if (necesitoRepintado)
        {
            necesitoRepintado = false;
            return true;
        }
        return false;
    }

    /**
     * A�ade un observador para avisarle cuando se necesite repintado.
     */
    public void addObservadorRepintado(ObservadorRepintado o)
    {
        this.observadores.add(o);

    }

    /**
     * Borra el observador de la lista de observadores
     */
    public void removeObservadorRepintado(ObservadorRepintado o)
    {
        this.observadores.remove(o);
    }

    /**
     * Marca que necesita repintado y avisa a sus observadores si es as�
     * @param necesitoRepintado true si necesita repintado
     */
    public void setNecesitoRepintado(boolean necesitoRepintado)
    {
        this.necesitoRepintado = necesitoRepintado;
        if (necesitoRepintado)
            for (int i = 0; i < this.observadores.size(); i++)
                ((ObservadorRepintado)this.observadores.get(i)).necesitoRepintado();
    }
}
