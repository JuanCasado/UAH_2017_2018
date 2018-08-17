/**
 * Chuidiang, 26/01/2008
 * FuncionParametrica.java
 */
package com.chuidiang.matematicas.funciones;

/**
 * @author Chuidiang
 *
 * Una función paramétrica compuesta por dos funciones
 * x = f(t)
 * y = f(t)
 */
public class FuncionParametrica implements IfzFuncionParametrica {
	
	/** Funcion x=f(t) */
	private IfzFuncionMatematica fx;
	
	/** Funcion y=f(t) */
	private IfzFuncionMatematica fy;

	/**
	 * Construye la clase. fx y fy no pueden ser null
	 * @param fx funcion x=f(t), no null
	 * @param fy funcion y=f(t), no null
	 */
	public FuncionParametrica (IfzFuncionMatematica fx, IfzFuncionMatematica fy)
	{
		assert fx!=null;
		assert fy!=null;
		this.fx = fx;
		this.fy = fy;
	}
	
	/* (non-Javadoc)
	 * @see com.chuidiang.matematicas.funciones.IfzFuncionParametrica#getX(double)
	 */
	public double getX(double t) {
		return fx.getY(t);
	}

	/* (non-Javadoc)
	 * @see com.chuidiang.matematicas.funciones.IfzFuncionParametrica#getY(double)
	 */
	public double getY(double t) {
		return fy.getY(t);
	}

}
