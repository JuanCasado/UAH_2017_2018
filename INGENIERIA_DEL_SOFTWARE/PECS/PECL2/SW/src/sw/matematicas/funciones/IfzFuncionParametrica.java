/**
 * Chuidiang, 26/01/2008
 * IfzFuncionParametrica.java
 */
package com.chuidiang.matematicas.funciones;

/**
 * @author Chuidiang
 * Una función paramétrica está definida por dos funciones del estilo
 * x = f(t)
 * y = f(t)
 * de forma que para un valor concreto de t obtenemos un punto x,y.
 */
public interface IfzFuncionParametrica {
	/**
	 * Devuelve valor x para un valor de t
	 * @param t
	 * @return
	 */
	public double getX(double t);
	
	/**
	 * Devuelve valor y para un valor de t
	 * @param t
	 * @return
	 */
	public double getY(double t);
}
