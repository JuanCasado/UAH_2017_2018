/**
 * Javier Abell�n, 3 Junio 2006
 * 
 * Poligono para pintar en un lienzo gr�fico en el que se pueden arrastrar los
 * v�rtices con el rat�n.
 */
package com.chuidiang.graficos.objetos_arrastrables;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Poligono para pintar en un lienzo gr�fico en el que se pueden arrastrar los
 * v�rtices con el rat�n.
 * 
 * @author Chuidiang
 *
 */
public class PoligonoDeformable extends AbstractObjetoGraficoArrastrable
{
    /**
     * Construye un poligono abierto cuyos v�rtices son los puntos que se le
     * pasan. Los puntos no pueden ser null y debe haber al menos 1, si no
     * saltar�n excepciones.
     * @param puntos Los v�rtices
     */
	public PoligonoDeformable(Point2D[] puntos)
	{
		setPuntos(puntos);
	}

    /**
     * Construye un pol�gono abierto cuyos v�rtices son las x,y que se pasan.
     * Los arrays no pueden ser null, deben tener al menos un elemento y deben
     * ser del mismo tama�o. Si no es as�, saltar�n excepciones.
     * @param x Las x de los v�rtices
     * @param y Las y de los v�rtices.
     */
	public PoligonoDeformable(double[] x, double[] y)
	{
		setPuntos(x, y);
	}

    /** Indice del punto que se est� arrastrando */
	private int indicePuntoArrastrado = 0;

    /**
     * Devuelve una pseudo-distancia entre una x,y del rat�n y el punto de posicion
     * indicePunto en el array.
     * @param x del rat�n
     * @param y del rat�n.
     * @param indicePunto Indice en el array de puntos
     * @return Una pseudodistancia
     */
	private double dameDistancia(double x, double y, int indicePunto)
	{
		return (Math.abs(x - puntos[indicePunto].getX()) + Math.abs(y
				- puntos[indicePunto].getY()));
	}

    /**
     * Devuelve el indice del v�rtice m�s cercano a la x,y que se le pasa.
     * @param x La x,
     * @param y la y
     * @return El indice en el array del v�rtice m�s cercano.
     */
	private int dameIndicePuntoMasCercano(double x, double y)
	{
		double distancia = dameDistancia(x, y, 0);
		int indiceMasCercano = 0;
		for (int i = 1; i < puntos.length; i++)
		{
			double distanciaAux = dameDistancia(x, y, i);
			if (distanciaAux < distancia)
			{
				distancia = distanciaAux;
				indiceMasCercano = i;
			}
		}
		return indiceMasCercano;
	}

    /**
     * Dibuja el pol�gono.
     */
	public void dibujate(InterfaceEscalaGrafica escala)
	{
		escala.pintaPoliLinea(puntos, color);
		if (cerrado)
		{
			Point2D[] aux = new Point2D[2];
			aux[0] = puntos[0];
			aux[1] = puntos[puntos.length - 1];
			escala.pintaPoliLinea(aux, color);
		}

	}
    
	/**
	 * Devuelve el array de v�rtices.
	 * @return v�rtices
	 */
	public Point2D[] getPuntos()
	{
		return puntos;
	}

    /**
     * Guarda los puntos que se le pasan y marca que necesita repintado
     * @param puntos v�rtices del pol�gono.
     */
	public void setPuntos(Point2D[] puntos)
	{
		this.puntos = puntos;
        this.setNecesitoRepintado(true);
	}

	/**
	 * Reemplaza los puntos guardados por los que se le pasan. Los arrays no
	 * pueden ser null y deben ser del mismo tama�o.
	 * 
	 * @param x Las x de los v�rtices
	 * @param y Las y de los v�rtices.
	 */
	public void setPuntos(double[] x, double[] y)
	{
		Point2D[] aux = new Point2D[x.length];
		for (int i = 0; i < x.length; i++)
			aux[i] = new Point2D.Double(x[i], y[i]);
		setPuntos(aux);
	}

    /**
     * Devuelve si el pol�gono es cerrado.
     * @return true si el pol�gono es cerrado.
     */
	public boolean isCerrado()
	{
		return cerrado;
	}

    /**
     * Hace que se cierre el poligono. A la hora de dibujarlo, se dibujar� una
     * l�nea que una el �ltimo v�rtice con el primero.
     * @param cerrado
     */
	public void setCerrado(boolean cerrado)
	{
		this.cerrado = cerrado;
	}

    /**
     * Devuelve el color del pol�gono
     * @return color del pol�gono
     */
	public Color getColor()
	{
		return color;
	}

    /**
     * Fija el color del pol�gono
     * @param color
     */
	public void setColor(Color color)
	{
		this.color = color;
        setNecesitoRepintado(true);
	}

    /**
     * Devuelve si el pol�gono se puede deformar arrastrando los v�rtices con
     * el rat�n.
     * @return Si se puede deformar arrastrando.
     */
	public boolean isDeformable()
	{
		return deformable;
	}

    /**
     * hace que el pol�gono se pueda deformar arrastrando los v�rtices con el
     * rat�n.
     * @param deformable true si se quiere que sea deformable.
     */
	public void setDeformable(boolean deformable)
	{
		this.deformable = deformable;
	}

    /**
     * Devuelve la pseudo-distancia m�nima para considerar que el rat�n pincha
     * sobre un v�rtice.
     * @return La pseudo-distancia m�nima.
     */
	public double getDistanciaMinimaArrastre()
	{
		return distanciaMinimaArrastre;
	}

    /**
     * Fija la pseudo-distancia m�nima para considerar que el rat�n pincha sobre
     * un v�rtice.
     * @param distanciaMinimaArrastre Distancia m�nima.
     */
	public void setDistanciaMinimaArrastre(double distanciaMinimaArrastre)
	{
		this.distanciaMinimaArrastre = distanciaMinimaArrastre;
	}

    /** Si el pol�gono es cerrado */
	private boolean cerrado = false;

    /** Si se puede deformar con el rat�n */
	private boolean deformable = false;

    /** Color del pol�gono */
	private Color color = Color.YELLOW;

    /** V�rtices del pol�gono */
	private Point2D[] puntos = null;

    /** Pseudo-distancia para considerar que el rat�n pincha sobre el v�rtice */
	private double distanciaMinimaArrastre = 0.5;

	public boolean tomaNuevoOrigen(double x, double y)
	{
        puntos[indicePuntoArrastrado].setLocation(x,y);
		return true;
	}

	public boolean puedeComenzarArrastre(MouseEvent event, double x, double y)
	{
		indicePuntoArrastrado = dameIndicePuntoMasCercano(x, y);
		double distancia = dameDistancia(x, y, indicePuntoArrastrado);
		if (distancia < distanciaMinimaArrastre)
        {
            return true;
        }
		return false;
	}

	public boolean finalizaElArrastre(MouseEvent e, double x, double y)
	{
        return false;
	}
}
