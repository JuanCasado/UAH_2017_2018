/**
 * Chuidiang, 26/01/2008
 * Lissajous.java
 */
package com.chuidiang.matematicas.funciones;

/**
 * @author Chuidiang
 * 
 */
public class Lissajous implements IfzFuncionParametrica {
	/**
	 * Parámetros por defecto.
	 */
	public Lissajous ()
	{
		
	}
	
	/**
	 * Curva de Lissajous.
	 * Los parámetros 1 corresponden a la x. 
	 * Los parámetros 2 corresponden a la y.
	 * El desfase va en radianes.
	 * La frecuencia en ciclos/segundo.
	 * 
	 * @param amplitud1
	 * @param frecuencia1
	 * @param desfase1
	 * @param amplitud2
	 * @param frecuencia2
	 * @param desfase2
	 */
	public Lissajous(double amplitud1, double frecuencia1, double desfase1,
			double amplitud2, double frecuencia2, double desfase2) {
		fx.setAmplitud(amplitud1);
		fx.setFrecuencia(frecuencia1);
		fx.setDesfase(desfase1);
		fy.setAmplitud(amplitud2);
		fy.setFrecuencia(frecuencia2);
		fy.setDesfase(desfase2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chuidiang.matematicas.funciones.IfzFuncionParametrica#getX(double)
	 */
	public double getX(double t) {
		return fx.getY(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chuidiang.matematicas.funciones.IfzFuncionParametrica#getY(double)
	 */
	public double getY(double t) {
		return fy.getY(t);
	}

	/** La funcion para x */
	private Seno fx = new Seno();
	
	/** La funcion para y */
	private Seno fy = new Seno();

	public double getAmplitud1() {
		return fx.getAmplitud();
	}

	public double getDesfase1() {
		return fx.getDesfase();
	}

	public double getFrecuencia1() {
		return fx.getFrecuencia();
	}

	public void setAmplitud1(double amplitud) {
		fx.setAmplitud(amplitud);
	}

	public void setDesfase1(double desfase) {
		fx.setDesfase(desfase);
	}

	public void setFrecuencia1(double frecuencia) {
		fx.setFrecuencia(frecuencia);
	}

	public double getAmplitud2() {
		return fy.getAmplitud();
	}

	public double getDesfase2() {
		return fy.getDesfase();
	}

	public double getFrecuencia2() {
		return fy.getFrecuencia();
	}

	public void setAmplitud2(double amplitud) {
		fy.setAmplitud(amplitud);
	}

	public void setDesfase2(double desfase) {
		fy.setDesfase(desfase);
	}

	public void setFrecuencia2(double frecuencia) {
		fy.setFrecuencia(frecuencia);
	}
}
