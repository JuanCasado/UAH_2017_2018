/**
 * Funcion que representa un polinomio.
 */
package com.chuidiang.matematicas.funciones;

/**
 * Un polinomio.
 * @author chuidiang
 *
 */
public class Polinomio implements IfzFuncionMatematica {
    /**
     * Coeficientes del polinomio, de forma que
     * y = sumatorio coeficientes[i] * x ^ i
     * es decir, el indice cero corresponde al termino independiente.
     */
    double[] coeficientes = null;

    /**
     * Construye un polinomio con los coeficientes que se le pasan.
     * coeficientes no puede ser null.
     * @param coeficientes
     */
    public Polinomio(double[] coeficientes) {
    	assert coeficientes != null;
        this.coeficientes = coeficientes;
    }

    /**
     * Devuelve el valor de y para la x que se le pasa, segun la
     * formula
     * y = sumatorio coeficientes[i] * x ^ i
     */
    public double getY(double x) {
        double y = 0.0;
        for (int i = 0; i < coeficientes.length; i++) {
            y = y + (coeficientes[i] * Math.pow(x, i));
        }
        return y;
    }

    /** getter coeficientes */
	public double[] getCoeficientes() {
		return coeficientes;
	}

	/** setter coeficientes. No puede ser null
	 * 
	 * @param coeficientes
	 */
	public void setCoeficientes(double[] coeficientes) {
		assert coeficientes != null;
		this.coeficientes = coeficientes;
	}
}
