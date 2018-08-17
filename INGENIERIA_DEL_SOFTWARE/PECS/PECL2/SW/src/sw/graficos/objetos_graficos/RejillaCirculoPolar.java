/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.geom.Point2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;

/**
 * Un circulo con escala, para gr�ficos polares.<br>
 */
public class RejillaCirculoPolar extends AbstractObjetoGrafico
{
	// ~ Variables de instancia
	// --------------------------------------------------

	/** Color del c�rculo */
	private Color color;

	/** Radio del c�rculo */
	private double radio;

	/** N�mero de divisiones que se desean en el c�rculo */
	private int numeroDivisiones;

	/** Cada cuanto hay que poner una division con texto. */
	private int divisionConTexto = 1;

	/** Si se va a poner texto o no */
	private boolean ponerTexto = true;

	/** Longitud de una division sin texto */
	private double longitudDivisionSinTexto = 5.0;

	/** Longitud de una division con texto */
	private double longitudDivisionConTexto = 10.0;

	// ~ Constructores
	// -----------------------------------------------------------

	/**
	 * Crea un nuevo objeto de la clase RejillaCirculoPolar.<br>
	 * 
	 * @param radio
	 *            Radio del c�rculo.<br>
	 * @param numeroDivisiones
	 *            N�mero de divisiones para el c�rculo.<br>
	 * @param color
	 *            Color del c�rculo.<br>
	 */
	public RejillaCirculoPolar(double radio, int numeroDivisiones, Color color)
	{
		this.radio = radio;
		this.numeroDivisiones = numeroDivisiones;
		this.color = color;
	}

	// ~ Metodos
	// -----------------------------------------------------------------

	/**
	 * Dibuja el c�rculo en la escala que se le pasa.<br>
	 * 
	 * @param escala
	 *            Escala sobre la que dibujar.<br>
	 */
	public void dibujate(InterfaceEscalaGrafica escala)
	{
		escala.pintaEjeY(radio, color);

		double angulo, longitud;
		for (int i = 0; i < numeroDivisiones; i++)
		{
			Point2D[] eje = new Point2D.Double[2];
			angulo = ((Math.PI * 2.0) / numeroDivisiones * i);
			eje[0] = new Point2D.Double(radio, angulo);
			if ((i % divisionConTexto) == 0)
				longitud = longitudDivisionConTexto;
			else
				longitud = longitudDivisionSinTexto;

			eje[1] = new Point2D.Double(radio + longitud, angulo);
			escala.pintaPoliLinea(eje, color);

			if (isPonerTexto())
				if ((i % divisionConTexto) == 0)
					escala.pintaTexto(
							eje[1],
							Integer.toString((int) (((angulo * 180.0) / Math.PI) + 0.5)),
							color);
		}
	}

	/**
	 * Devuelve el color que se usa para el c�rculo con escala
	 * 
	 * @return
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * Fija el color para el c�rculo con escala.
	 * 
	 * @param color
	 *            Color para el c�rculo
	 */
	public void setColor(Color color)
	{
		this.color = color;
		this.setNecesitoRepintado(true);
	}

	/**
	 * Devuelve cada cuantas divisiones se pone una etiqueta de texto
	 * 
	 * @return
	 */
	public int getDivisionConTexto()
	{
		return divisionConTexto;
	}

	/**
	 * Fija cada cuantas divisiones se pone una etiqueta de texto
	 * 
	 * @param divisionConTexto
	 *            N�mero de divisiones
	 */
	public void setDivisionConTexto(int divisionConTexto)
	{
		this.divisionConTexto = divisionConTexto;
		this.setNecesitoRepintado(true);
	}

	/**
	 * Devuelve la longitud (en unidades de usuario) para las divisiones que
	 * tienen etiquetas de texto
	 * 
	 * @return Longitud de la divisi�n
	 */
	public double getLongitudDivisionConTexto()
	{
		return longitudDivisionConTexto;
	}

	/**
	 * Fija las longitudes (en unidades de usuario) para las divisiones que
	 * tienen etiqueta de texto.
	 * @param longitudDivisionConTexto
	 */
	public void setLongitudDivisionConTexto(double longitudDivisionConTexto)
	{
		this.longitudDivisionConTexto = longitudDivisionConTexto;
		this.setNecesitoRepintado(true);
	}

    /**
     * Devuelve la longitud en unidades de usuario de las divisiones que no
     * tienen etiqueta de texto.
     * @return Longitud de la divisi�n
     */
	public double getLongitudDivisionSinTexto()
	{
		return longitudDivisionSinTexto;
	}

    /**
     * Fija la longitud de las divisiones que no tienen etiqueta de texto.
     * @param longitudDivisionSinTexto Longitud de la divisi�n.
     */
	public void setLongitudDivisionSinTexto(double longitudDivisionSinTexto)
	{
		this.longitudDivisionSinTexto = longitudDivisionSinTexto;
		this.setNecesitoRepintado(true);
	}

    /**
     * Devuelve el n�mero total de divisioes a pintar.
     * @return
     */
	public int getNumeroDivisiones()
	{
		return numeroDivisiones;
	}

    /** Fija el n�mero de divisiones a pintar. */
	public void setNumeroDivisiones(int numeroDivisiones)
	{
		this.numeroDivisiones = numeroDivisiones;
		this.setNecesitoRepintado(true);
	}

    /** Devuelve si hay que poner o no texto.
     * 
     * @return true si se pone texto
     */
	public boolean isPonerTexto()
	{
		return ponerTexto;
	}

    /**
     * Se le indica si debe poner texto en algunas divisiones.
     * @param ponerTexto true para que ponga el texto
     */
	public void setPonerTexto(boolean ponerTexto)
	{
		this.ponerTexto = ponerTexto;
		this.setNecesitoRepintado(true);
	}

    /**
     * Devuelve el radio en unidades de usuario del c�rculo con escala que se
     * va a dibujar.
     * @return Radio.
     */
	public double getRadio()
	{
		return radio;
	}

    /**
     * Fija el radio en coordenadas de usuario para el c�rculo con divisiones.
     * @param radio Radio
     */
	public void setRadio(double radio)
	{
		this.radio = radio;
		this.setNecesitoRepintado(true);
	}
}
