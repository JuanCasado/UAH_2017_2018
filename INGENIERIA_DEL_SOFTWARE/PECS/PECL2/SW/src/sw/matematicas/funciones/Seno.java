/*
 * 
 */
package com.chuidiang.matematicas.funciones;

/**
 * Funcion sin(x).
 * Permite modificar amplitud y frecuencia, de forma que
 * y = amplitud*sin(2*PI*frecuencia*x+desfase)
 * @author chuidiang
 *
 */
public class Seno implements IfzFuncionMatematica {
    /**
     * Amplitud
     */
    double amplitud = 1.0;
    /**
     * Frecuencia en ciclos/segundo
     */
    double frecuencia = 1.0;
    
    /** desfase en radianes */
    double desfase=0.0;

    /**
     * Devuelve la amplitud
     * @return
     */
    public double getAmplitud() {
        return amplitud;
    }

    /**
     * Fija la amplitud
     * @param amplitud
     */
    public void setAmplitud(double amplitud) {
        this.amplitud = amplitud;
    }

    /**
     * Devuelve la frecuencia
     * @return
     */
    public double getFrecuencia() {
        return frecuencia;
    }

    /**
     * Fija la frecuencia
     * @param frecuencia
     */
    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

    
    /**
     * Devuelve amplitud*sin(frecuencia*x) con la x en radianes.
     */
    public double getY(double x) {
        return amplitud * Math.sin(2.0 * Math.PI * frecuencia * x + desfase);
    }

    /** Devuelve el desfase
     * 
     * @return
     */
	public double getDesfase() {
		return desfase;
	}

	/**
	 * Se le pasa el desfase, en radianes.
	 * @param desfase
	 */
	public void setDesfase(double desfase) {
		this.desfase = desfase;
	}

}
