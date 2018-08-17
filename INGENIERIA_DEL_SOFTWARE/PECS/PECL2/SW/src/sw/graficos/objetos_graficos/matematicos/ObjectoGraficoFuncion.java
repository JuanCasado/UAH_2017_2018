/*
 * Javier Abellan. 25 Mayo 2004
 * 
 * Objeto grafico abstracto para pintar funciones matematicas y = f(x)
 */
package com.chuidiang.graficos.objetos_graficos.matematicos;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.objetos_graficos.AbstractObjetoGrafico;
import com.chuidiang.matematicas.funciones.IfzFuncionMatematica;


/**
 * Clase abstracta para dibujar funciones matemï¿½ticas y = f(x) sobre una clase
 * Lienzo. Debe heredarse de esta clase y redefinir el mï¿½todo funcion(). La
 * clase se encarga del dibujado de la funciï¿½n sobre el Lienzo.
 */
public class ObjectoGraficoFuncion extends AbstractObjetoGrafico implements IfzFuncionMatematica
{

    private IfzFuncionMatematica funcion;

	/**
     * Recoge el color que se le pasa y dibujarï¿½ la funciï¿½n matemï¿½tica con ese
     * color.
     * @funcion La función matemática, no puede ser null.
     */
    public ObjectoGraficoFuncion(IfzFuncionMatematica funcion)
    {
    	assert funcion!=null;
    	this.funcion = funcion;
    }

    /**
     * Recoge el color que se le pasa para dibujar la funciï¿½n con ese color. El
     * dibujo cambiarï¿½ de color en el siguiente refresco del grï¿½fico. Si el
     * color es null, la funciï¿½n dejarï¿½ de dibujarse.
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * Devuelve el color con el que se estï¿½ dibujando la funciï¿½n. Devuelve null
     * si previamente se le pasï¿½ a la clase un color null.
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Se le pasa el numero de puntos a dibujar de la funcion. Se dibujaran esos
     * puntos entre la x minima y la x maxima de la escala que se pase en el
     * metodo dibujate. Si el numero de puntos es menor de 2, se ignora el
     * parametro se deja el numero de puntos anterior.
     */
    public void setNumeroPuntos(int numeroPuntos)
    {
        if (numeroPuntos < 2)
            return;
        this.numeroPuntos = numeroPuntos;
    }

    /**
     * Devuelve el numero de puntos que se esta utilizando para dibujar la
     * funcion.
     */
    public int getNumeroPuntos()
    {
        return numeroPuntos;
    }

    /**
     * Dibuja la funcion matematica sobre la escala. Obtiene la x minima y
     * maxima de la escala para hacer un bucle de 500 puntos. Calcula los 500
     * puntos de la funcion para todas esas x y dibuja segementos que unen
     * consecutivamente todos esos puntos, haciendo que la funcion quede
     * dibujada sobre una linea.
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        if (color == null)
            return;

        Rectangle2D extremos = escala.getExtremosCartesianos();
        Point2D puntos[] = new Point2D[numeroPuntos];
        double x;

        for (int i = 0; i < numeroPuntos; i++)
        {
            x = extremos.getMinX() + i * extremos.getWidth() / numeroPuntos;
            puntos[i] = new Point2D.Double(x, getY(x));
        }

        escala.pintaPoliLinea(puntos, color);
    }

    /** Color con el que se dibuja la funciï¿½n */
    private Color color;

    /** Nï¿½mero de puntos que se van a dibujar de la funciï¿½n. */
    private int numeroPuntos = 500;

	public double getY(double x) {
		return funcion.getY(x);
	}

	public IfzFuncionMatematica getFuncion() {
		return funcion;
	}

	public void setFuncion(IfzFuncionMatematica funcion) {
		this.funcion = funcion;
	}
}
