/**
 * Javier Abellan. 6 Abril 2006
 * 
 * Libreria grafica
 */

package com.chuidiang.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.util.LinkedList;

import com.chuidiang.matematicas.CartesianasPolares;


/**
 * Clase encargada de transformar coordenadas de usuario en pixels y viceversa
 * en gr�ficos con coordenadas polares.<br>
 */
public class EscalaGraficaPolar implements InterfaceEscalaGrafica
{
	// ~ Variables de instancia
	// --------------------------------------------------

	/** Los polares se convierten a cartesiana y se delega en esta clase */
	protected EscalaGraficaCartesiana escala = new EscalaGraficaCartesiana();

	/** Observadores de cambios en los extremos del gr�fico */
	private LinkedList<ObservadorEscalaGrafica> observadores = new LinkedList<ObservadorEscalaGrafica>();

	// ~ Constructores
	// -----------------------------------------------------------

	/**
	 * A�ade un observador a la lista d eobservadores para avisarle cuando haya
	 * cambio en los extremos del gr�fico.<br>
	 * 
	 * @param unObservador
	 *            El observador.<br>
	 */
	public void anhadeObservador(ObservadorEscalaGrafica unObservador)
	{
		observadores.add(unObservador);
	}

	/**
	 * Devuelve las coordenadas de usuario de un punto expresado en pixels.<br>
	 * �til para transformar coordenadas de un evento de rat�n en coordenadas de
	 * usuario.
	 * 
	 * @param punto
	 *            Punto con coordenadas en pixels.<br>
	 * @return Punto con coordenadas de usuario.<br>
	 */
	public Point2D getCoordenadaUsuario(Point2D punto)
	{
		Point2D pUsuario = escala.getCoordenadaUsuario(punto);
		Point2D pUsuarioPolar = new Point2D.Double();
		pUsuarioPolar.setLocation(CartesianasPolares.dameModulo(
				pUsuario.getX(), pUsuario.getY()), CartesianasPolares
				.dameAngulo(pUsuario.getX(), pUsuario.getY()));

		return pUsuarioPolar;
	}

	/**
	 * Devuelve el area de dibujo en coordenadas polares de usuario.<br>
	 * 
	 * @return Rectangulo de dibujo.<br>
	 */
	public Rectangle2D getExtremosCartesianos()
	{
		return escala.getExtremosCartesianos();
	}

	/**
	 * Elimina el observador que se le pasa de la lista de observadores.<br>
	 * Ya no le avisar� de cambios en los l�mites del dibujo
	 * 
	 * @param unObservador
	 *            El observador a eliminar.<br>
	 */
	public void eliminaObservador(ObservadorEscalaGrafica unObservador)
	{
		observadores.remove(unObservador);
	}

	/**
	 * Dibuja un arco.<br>
	 * El arco pertenece a una circunferencia que va inscrita en un rect�ngulo
	 * @param esquina
	 *            Esquina del rect�ngulo.<br>
	 * @param ancho
	 *            Ancho del rect�ngulo.<br>
	 * @param alto
	 *            Alto del rect�ngulo.<br>
	 * @param arcoInicial
	 *            Angulo inicial del arco, en radianes.<br>
	 * @param arcoFinal
	 *            Angulo final del arco, en radianes.<br>
	 * @param color
	 *            Color del arco.<br>
	 */
	public void pintaArco(Point2D centro, double ancho, double alto,
			double arcoInicial, double arcoFinal, Color color)
	{

		Point2D centroCartesiano = new Point2D.Double();
		centroCartesiano.setLocation(CartesianasPolares.dameX(centro.getX(),
				centro.getY()), CartesianasPolares.dameY(centro.getX(), centro
				.getY()));
		this.escala.pintaArco(centroCartesiano, ancho, alto, arcoInicial,
				arcoFinal, color);
	}

	/**
	 * Dibuja un arco relleno.<br>
	 * El arco pertenece a una circunferencia que va inscrita en un rect�ngulo
	 * @param esquina
	 *            Esquina del rect�ngulo.<br>
	 * @param ancho
	 *            Ancho del rect�ngulo.<br>
	 * @param alto
	 *            Alto del rect�ngulo.<br>
	 * @param arcoInicial
	 *            Angulo inicial del arco, en radianes.<br>
	 * @param arcoFinal
	 *            Angulo final del arco, en radianes.<br>
	 * @param color
	 *            Color del arco.<br>
	 */
	public void pintaArcoRelleno(Point2D centro, double ancho, double alto,
			double arcoInicial, double arcoFinal, Color color)
	{
		Point2D centroCartesiano = new Point2D.Double();
		centroCartesiano.setLocation(CartesianasPolares.dameX(centro.getX(),
				centro.getY()), CartesianasPolares.dameY(centro.getX(), centro
				.getY()));
		this.escala.pintaArcoRelleno(centroCartesiano, ancho, alto,
				arcoInicial, arcoFinal, color);
	}

	/**
	 * Dibuja un eje de las x (radio) en la coordenada y (con angulo determinado).<br>
	 * 
	 * @param y
	 *            Coordenada y para dibujar el eje.<br>
	 * @param color
	 *            Color del eje.<br>
	 */
	public void pintaEjeX(double y, Color color)
	{
		Rectangle2D extremos = getExtremosCartesianos();
		Point2D.Double[] eje = new Point2D.Double[2];
		eje[0] = new Point2D.Double(0, y);
		eje[1] = new Point2D.Double(extremos.getWidth(), y);
		pintaPoliLinea(eje, color);
	}

	/**
	 * Pinta el eje de los angulos, es decir un circulo del radio x del centro
	 * que se pasa.<br>
	 * El color
	 * 
	 * @param x
	 *            Radio para dibujar el c�rculo.<br>
	 * @param color
	 *            Color del c�rculo.<br>
	 */
	public void pintaEjeY(double x, Color color)
	{
		Point2D centro = new Point2D.Double(
				CartesianasPolares.dameModulo(x, x), 5 * Math.PI / 4.0);
		pintaArco(centro, 2 * x, 2 * x, 0.0, 360.0, color);
	}

	/**
	 * Dibuja una pol�gono abierto, uniendo consecutivamente los puntos de
	 * puntos.<br>
	 * 
	 * @param puntos
	 *            Puntos del pol�gono.<br>
	 * @param color
	 *            Color para el pol�gono.<br>
	 */
	public void pintaPoliLinea(Point2D[] puntos, Color color)
	{
		Point2D[] pCartesianos = new Point2D.Double[puntos.length];

		for (int i = 0; i < puntos.length; i++)
		{
			pCartesianos[i] = new Point2D.Double(CartesianasPolares.dameX(
					puntos[i].getX(), puntos[i].getY()), CartesianasPolares
					.dameY(puntos[i].getX(), puntos[i].getY()));
		}

		escala.pintaPoliLinea(pCartesianos, color);
	}

	/**
	 * Dibuja los puntos que se le pasan.<br>
	 * 
	 * @param puntos
	 *            Los puntos.<br>
	 * @param color
	 *            Color de cada uno de los puntos.<br>
	 */
	public void pintaPuntos(Point2D[] puntos, Color[] color)
	{
		Point2D[] pCartesianos = new Point2D.Double[puntos.length];

		for (int i = 0; i < puntos.length; i++)
		{
			pCartesianos[i] = new Point2D.Double(CartesianasPolares.dameX(
					puntos[i].getX(), puntos[i].getY()), CartesianasPolares
					.dameY(puntos[i].getX(), puntos[i].getY()));
		}

		escala.pintaPuntos(pCartesianos, color);
	}

	/**
	 * Pinta puntos de un mismo color.<br>
	 * 
	 * @param puntos
	 *            Los puntos.<br>
	 * @param color
	 *            Color para los puntos.<br>
	 */
	public void pintaPuntos(Point2D[] puntos, Color color)
	{
		Point2D[] pCartesianos = new Point2D.Double[puntos.length];

		for (int i = 0; i < puntos.length; i++)
		{
			pCartesianos[i] = new Point2D.Double(CartesianasPolares.dameX(
					puntos[i].getX(), puntos[i].getY()), CartesianasPolares
					.dameY(puntos[i].getX(), puntos[i].getY()));
		}

		escala.pintaPuntos(pCartesianos, color);
	}

	/**
	 * Pinta un rect�ngulo relleno.<br>
	 * 
	 * @param esquina
	 *            esquina inferior izquierda del rect�ngulo.<br>
	 * @param ancho
	 *            Ancho del rect�ngulo.<br>
	 * @param alto
	 *            Alto del rect�ngulo.<br>
	 * @param color
	 *            Color del rect�ngulo.<br>
	 */
	public void pintaRectanguloRelleno(Point2D esquina, double ancho,
			double alto, Color color)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Pinta un rect�ngulo relleno en 3D.<br>
	 * 
	 * @param esquina
	 *            Esquina inferior izquierda del rect�ngulo.<br>
	 * @param ancho
	 *            Ancho del rect�ngulo.<br>
	 * @param alto
	 *            Alto del rect�ngulo.<br>
	 * @param color
	 *            Color del rect�ngulo.<br>
	 * @param elevado
	 *            COMENTARIO.<br>
	 */
	public void pintaRectanguloRelleno3D(Point2D esquina, double ancho,
			double alto, Color color, boolean elevado)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Dibuja un texto en la posici�n indicada.<br>
	 * 
	 * @param posicion
	 *            Posici�n del texto.<br>
	 * @param texto
	 *            El texto.<br>
	 * @param color
	 *            Color.<br>
	 */
	public void pintaTexto(Point2D posicion, String texto, Color color)
	{
		Point2D.Double posicionTexto = new Point2D.Double();
		posicionTexto.setLocation(CartesianasPolares.dameX(posicion.getX(),
				posicion.getY()), CartesianasPolares.dameY(posicion.getX(),
				posicion.getY()));
		escala.pintaTexto(posicionTexto, texto, color);
	}

	/**
	 * Se le pasan los l�mites para el gr�fico polar.<br>
	 * 
	 * @param rMin
	 *            Radio m�nimo que se quiere dibujar.<br>
	 * @param tMin
	 *            Angulo m�nimo en radianes.<br>
	 * @param rMax
	 *            Radio m�ximo.<br>
	 * @param tMax
	 *            Angulo m�ximo en radianes.<br>
	 */
	public void tomaExtremos(double xMin, double yMin, double xMax, double yMax)
	{
		this.escala.tomaExtremos(xMin, yMin, xMax, yMax);
		this.avisaObseradores(xMin, yMin, xMax, yMax);
	}

	/**
	 * Se le pasa el Graphics sobre el que va a dibujar, as� como el
	 * alto y ancho en pixels del �rea de dibujo.<br>
	 * 
	 * @param g
	 *            Graphics para dibujar.<br>
	 * @param ancho
	 *            Ancho en pixels del �rea de dibujo.<br>
	 * @param alto
	 *            Alto en pixels del �rea de dibujo.<br>
	 */
	public void tomaGraphics(Graphics g, int ancho, int alto)
	{
		escala.tomaGraphics(g, ancho, alto);
	}

	/**
	 * Avisa a los observadores de cambio en los l�mites del gr�fico de que
	 * se ha producido un cambio en dichos l�mites.<br>
	 * 
	 * @param xMin
	 *            Nueva x min.<br>
	 * @param yMin
	 *            Nueva y min.<br>
	 * @param xMax
	 *            Nueva x max.<br>
	 * @param yMax
	 *            Nueva y max.<br>
	 */
	private void avisaObseradores(double xMin, double yMin, double xMax,
			double yMax)
	{
		for (int i = 0; i < observadores.size(); i++)
		{
			((ObservadorEscalaGrafica) observadores.get(i)).tomaNuevosExtremos(
					xMin, yMin, xMax, yMax);
		}
	}

    /**
     * Dibuja la imagen que se le pasa en la posici�n indicada.
     * 
     * @param posicion La posici�n para dibujar la im�gen
     * @param imagen La imagen a dibujar
     */
	public void pintaImagen(Point2D posicion, Image imagen)
	{
		if (posicion == null)
			return;
		Point2D.Double posicionImagen = new Point2D.Double();
		posicionImagen.setLocation(CartesianasPolares.dameX(posicion.getX(),
				posicion.getY()), CartesianasPolares.dameY(posicion.getX(),
				posicion.getY()));
		this.escala.pintaImagen(posicionImagen, imagen);
	}

    /**
     * Se le pasan unas coordenadas polares y devuelve las cartesianas adecudas
     */
	public Point2D getCoordenadasCartesianas(Point2D puntoPolar)
	{
        Point2D puntoCartesiano = new Point2D.Double();
        puntoCartesiano.setLocation(
                CartesianasPolares.dameX(puntoPolar.getX(),puntoPolar.getY()),
                CartesianasPolares.dameY(puntoPolar.getX(),puntoPolar.getY()));
        return puntoCartesiano;
	}

    /**
     * Devuelve los extremos polares del dibujo.
     * La xmin es el radio m�s peque�o (que puede ser cero o m�s). La ymin
     * siempre es 0.0 y la m�s grande 2*PI.
     */
	public Rectangle2D getExtremos()
	{
		Rectangle2D.Double resultado = new Rectangle2D.Double();
		Rectangle2D cartesianos = this.getExtremosCartesianos();
		if ((cartesianos.getX()<0) && 
				((cartesianos.getX()+cartesianos.getWidth())>0) &&
				(cartesianos.getY()<0) &&
				((cartesianos.getY()+cartesianos.getHeight()>0)))
		{
			// El centro est� dentro del gr�fico
			resultado.x=0.0;
			resultado.width=Math.max(cartesianos.getWidth(),cartesianos.getHeight());
			resultado.y=0.0;
			resultado.height=2.0*Math.PI;
		}
		else
		{
			// Esta fuera
			double min = Math.abs(cartesianos.getX());
			min = Math.min(min, Math.abs(cartesianos.getX()+cartesianos.getWidth()));
			min = Math.min(min, Math.abs(cartesianos.getY()));
			min = Math.min(min, Math.abs(cartesianos.getY()+cartesianos.getHeight()));
			double max = Math.abs(cartesianos.getX());
			max = Math.max(max, Math.abs(cartesianos.getX()+cartesianos.getWidth()));
			max = Math.max(max, Math.abs(cartesianos.getY()));
			max = Math.max(max, Math.abs(cartesianos.getY()+cartesianos.getHeight()));
			resultado.x=0.0;
			resultado.width=max-min;
			resultado.y=0.0;
			resultado.height=2*0*Math.PI;
		}
		return resultado;	}
}
