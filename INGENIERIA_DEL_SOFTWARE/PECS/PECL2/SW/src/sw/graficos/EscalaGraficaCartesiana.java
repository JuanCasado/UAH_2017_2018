/*
 * Javier Abell�n, 1 de Junio de 2004
 *
 * EscalaGraficaCartesiana.java
 */
package com.chuidiang.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.util.LinkedList;

/**
 * Implementaci�n de EscalaGrafica para proporcionar unas coordenadas de usuario
 * cartesianas a un Lienzo.
 */
public class EscalaGraficaCartesiana implements InterfaceEscalaGrafica
{
	// ~ Variables de instancia
	// --------------------------------------------------

	/** Clase encargada de transfomar coordenadas de usuario a pixels */
	private AffineTransform t = null;

	/** Graphics de la imagen sobre la que se dibuja */
	private Graphics g;

	/**
	 * Lista de observadores a cambios en los extremos de las coordenadas de
	 * usuario
	 */
	private LinkedList<ObservadorEscalaGrafica> observadores = new LinkedList<ObservadorEscalaGrafica>();

	/** Valor maximo de x que desea el usuario para el grafico */
	private double xMax = 10.0;

	/** Valor minimo de x que desea el usuario sobre el gr�fico */
	private double xMin = -10.0;

	/** Valor minimo de y que desea el usuario para el grafico */
	private double yMax = 10.0;

	/** Valor minimo de y que desea el usuario sobre el grafico */
	private double yMin = -10.0;

	/** Alto en pixels de la zona de dibujo. */
	private int alto;

	/** Ancho en pixels de la zona de dibujo */
	private int ancho;

	// ~ Constructores
	// -----------------------------------------------------------

	/**
	 * A�ade un observador a un cambio en los limites de usuario. Cuando se
	 * cambien losG limites de usuario con tomaExtremos(), se avisar� a los
	 * observadores.
	 * 
	 * @param unObservador
	 *            un observador de cambio de extremos en la escala grafica.<br>
	 */
	public void anhadeObservador(ObservadorEscalaGrafica unObservador)
	{
		observadores.add(unObservador);
	}

	/**
	 * Devuelve el alto en pixels de un alto en coordenadas de usuario. Un alto
	 * en coordenadas de usuario es la diferencia entre dos y en coordenadas de
	 * usuario.
	 * 
	 * @param alto
	 *            Alto en coordenadas de usuario.<br>
	 * @return alto en pixels.<br>
	 */
	public double dameAlto(double alto)
	{
		Point2D.Double radio = new Point2D.Double(0.0, yMax - alto);
		Point2D radioTransformado;
		radioTransformado = t.transform(radio, null);

		return radioTransformado.getY();
	}

	/**
	 * Devuelve el ancho en pixel de un ancho en coordenadas de usuario. Un
	 * ancho en coordenadas de usuario es la diferencia entre dos x en
	 * coordenadas de usuario.
	 * 
	 * @param ancho
	 *            Ancho en coordenadas de usuario.<br>
	 * @return ancho en pixels.<br>
	 */
	public double dameAncho(double ancho)
	{
		Point2D.Double radio = new Point2D.Double(xMin + ancho, 0.0);
		Point2D radioTransformado;
		radioTransformado = t.transform(radio, null);

		return radioTransformado.getX();
	}

	/**
	 * Devuelve las coordenadas de usuario correspondientes a unas coordenadas
	 * x,y en pixels.
	 * 
	 * @param punto
	 *            Un punto en pixels.<br>
	 * @return Un punto en coordenadas de usuario.<br>
	 */
	public Point2D getCoordenadaUsuario(Point2D punto)
	{
		Point2D resultado = new Point2D.Double();

		try
		{
			t.inverseTransform(punto, resultado);
		} catch (Exception e)
		{
			// e.printStackTrace();
		}

		return resultado;
	}

	/**
	 * Devuelve un Rectagle2D con los limites de las coordenadas de usuario.
	 * 
	 * @return Las dimensiones del area de dibujo.<br>
	 */
	public Rectangle2D getExtremosCartesianos()
	{
		Rectangle2D.Double extremos = new Rectangle2D.Double();
		extremos.x = xMin;
		extremos.y = yMin;
		extremos.width = xMax - xMin;
		extremos.height = yMax - yMin;

		return extremos;
	}

	/**
	 * Elimina un observador de cambio en los limites de usuario que se haya
	 * a�adido previamente con anhadeObservador()
	 * 
	 * @param unObservador
	 *            Un observador de la escala grafica.<br>
	 */
	public void eliminaObservador(ObservadorEscalaGrafica unObservador)
	{
		observadores.remove(unObservador);
	}

	/**
	 * Dibuja un arco llamando a la clase drawArc() de Graphcis. El archo forma
	 * parte de un circulo que a su vez se puede considerar inscrito en un
	 * rectangulo.
	 * 
	 * @param esquina
	 *            esquina inferior derecha del rectangulo que contiene al
	 *            circulo.<br>
	 * @param ancho
	 *            ancho del rectangulo.<br>
	 * @param alto
	 *            alto del rectangulo.<br>
	 * @param arcoInicial
	 *            angulo en el que empieza a dibujarse el arco, en radianes.<br>
	 * @param arcoFinal
	 *            angulo en el que termina de dibujarse el arco, en radianes.<br>
	 * @param color
	 *            Color del arco.<br>
	 */
	public void pintaArco(Point2D esquina, double ancho, double alto,
			double arcoInicial, double arcoFinal, Color color)
	{
		Point2D centroTransformado;
		Point2D centroAux = new Point2D.Double(esquina.getX(), esquina.getY()
				+ alto);
		centroTransformado = t.transform(centroAux, null);

		Point2D.Double radio = new Point2D.Double(xMin + ancho, yMax - alto);
		Point2D radioTransformado;
		radioTransformado = t.transform(radio, null);

		g.setColor(color);
		g.drawArc((int) centroTransformado.getX(), (int) centroTransformado
				.getY(), (int) radioTransformado.getX(),
				(int) radioTransformado.getY(), (int) arcoInicial,
				(int) arcoFinal);
	}

	/**
	 * Dibuja un arco llamando a la clase drawArc() de Graphcis. El archo forma
	 * parte de un circulo que a su vez se puede considerar inscrito en un
	 * rectangulo.
	 * 
	 * @param esquina
	 *            esquina inferior derecha del rectangulo que contiene al
	 *            circulo.<br>
	 * @param ancho
	 *            ancho del rectangulo.<br>
	 * @param alto
	 *            alto del rectangulo.<br>
	 * @param arcoInicial
	 *            angulo en el que empieza a dibujarse el arco, en radianes.<br>
	 * @param arcoFinal
	 *            angulo en el que termina de dibujarse el arco, en radianes.<br>
	 * @param color
	 *            Color del arco.<br>
	 */
	public void pintaArcoRelleno(Point2D centro, double ancho, double alto,
			double arcoInicial, double arcoFinal, Color color)
	{
		Point2D centroTransformado;
		Point2D centroAux = new Point2D.Double(centro.getX(), centro.getY()
				+ alto);
		centroTransformado = t.transform(centroAux, null);

		Point2D.Double radio = new Point2D.Double(xMin + ancho, yMax - alto);
		Point2D radioTransformado;
		radioTransformado = t.transform(radio, null);

		g.setColor(color);
		g.fillArc((int) centroTransformado.getX(), (int) centroTransformado
				.getY(), (int) radioTransformado.getX(),
				(int) radioTransformado.getY(), (int) arcoInicial,
				(int) arcoFinal);
	}

	/**
	 * Pinta un eje de x, desde x minima hasta x maxima.<br>
	 * 
	 * @param y
	 *            Altura a la que se quiere dibujar el x.<br>
	 * @param color
	 *            Color del eje.<br>
	 */
	public void pintaEjeX(double y, Color color)
	{
		Point2D.Double[] eje = new Point2D.Double[2];
		eje[0] = new Point2D.Double(xMin, y);
		eje[1] = new Point2D.Double(xMax, y);
		pintaPoliLinea(eje, color);
	}

	/**
	 * Dibuja un eje de las y, desde y minima a y maxima.<br>
	 * 
	 * @param x
	 *            Posicion del eje a dibujar.<br>
	 * @param color
	 *            Color del eje.<br>
	 */
	public void pintaEjeY(double x, Color color)
	{
		Point2D.Double[] eje = new Point2D.Double[2];
		eje[0] = new Point2D.Double(x, yMin);
		eje[1] = new Point2D.Double(x, yMax);
		pintaPoliLinea(eje, color);
	}

	/**
	 * Dibuja una imagen en el area grafica.<br>
	 * 
	 * @param posicion
	 *            Posicion de la esquina superior izquierda de la imagen.<br>
	 * @param imagen
	 *            La imgagen.<br>
	 */
	public void pintaImagen(Point2D posicion, Image imagen)
	{
		if (imagen == null)
			return;
		Point2D.Double posicionImagen = new Point2D.Double();
		t.transform(posicion, posicionImagen);

		g.drawImage(imagen, (int) posicionImagen.getX(), (int) posicionImagen
				.getY(), null);
	}

	/**
	 * Dibuja un linea uniendo consecutivamente todos los puntos que se le
	 * pasan. La l�nea ser� del color indicado.
	 * 
	 * @param puntos
	 *            COMENTARIO.<br>
	 * @param color
	 *            COMENTARIO.<br>
	 */
	public void pintaPoliLinea(Point2D[] puntos, Color color)
	{
		if ((color == null) || (puntos == null))
		{
			return;
		}

		if (puntos.length < 1)
		{
			return;
		}

		// Si hay un solo punto, se pinta un punto.
		if (puntos.length < 2)
		{
			pintaPuntos(puntos, color);

			return;
		}

		// Se obtienen los puntos en pixels.
		Point2D[] puntosTransformados;
		puntosTransformados = dameTransformados(puntos);

		if (puntosTransformados == null)
		{
			return;
		}

		// Se pone el color y se pintan.
		g.setColor(color);

		for (int i = 1; i < puntos.length; i++)
		{
			try
			{
				g.drawLine((int) puntosTransformados[i - 1].getX(),
						(int) puntosTransformados[i - 1].getY(),
						(int) puntosTransformados[i].getX(),
						(int) puntosTransformados[i].getY());
			} catch (Exception e)
			{

			}
		}
	}

	/**
	 * Dibuja los puntos del x[i], y[i] del color[i]
	 * 
	 * @param puntos
	 *            Array de puntos a pintar, en coordenadas de usuario.<br>
	 * @param color
	 *            Array de colores para los puntos.<br>
	 */
	public void pintaPuntos(Point2D[] puntos, Color[] color)
	{
		if ((color == null) || (puntos == null))
		{
			return;
		}

		// Se obtienen los puntos en pixels.
		Point2D[] puntosTransformados;
		puntosTransformados = dameTransformados(puntos);

		if (puntosTransformados == null)
		{
			return;
		}

		// Se pintan los puntos
		for (int i = 0; i < puntos.length; i++)
		{
			g.setColor(color[i]);
			g.drawLine((int) puntosTransformados[i].getX(),
					(int) puntosTransformados[i].getY(),
					(int) puntosTransformados[i].getX(),
					(int) puntosTransformados[i].getY());
		}
	}

	/**
	 * Dibuja los puntos x[i], y[i] de color
	 * 
	 * @param puntos
	 *            Array de puntos para pintar.<br>
	 * @param color
	 *            Color para todos los puntos.<br>
	 */
	public void pintaPuntos(Point2D[] puntos, Color color)
	{
		int i;
		Point2D[] puntosTransformados;

		if ((color == null) || (puntos == null))
		{
			return;
		}

		puntosTransformados = dameTransformados(puntos);

		if (puntosTransformados == null)
		{
			return;
		}

		g.setColor(color);

		for (i = 0; i < puntos.length; i++)
		{
			g.drawLine((int) puntosTransformados[i].getX(),
					(int) puntosTransformados[i].getY(),
					(int) puntosTransformados[i].getX(),
					(int) puntosTransformados[i].getY());
		}
	}

	/**
	 * Dibuja un rectangulo relleno llamando a fillRect() de la clase Graphics.
	 * Las coordenadas de esquina, ancho y alto van en coordenadas de usuario.
	 * 
	 * @param esquina
	 *            Esquina inferior izquierda del rect�ngulo.<br>
	 * @param ancho
	 *            Ancho del rect�ngulo, en coordenadas de usuario.<br>
	 * @param alto
	 *            Alot del rect�ngulo.<br>
	 * @param color
	 *            Color del rect�ngulo.<br>
	 */
	public void pintaRectanguloRelleno(Point2D esquina, double ancho,
			double alto, Color color)
	{
		Point2D esquinaTransformada = t.transform(esquina, null);

		g.setColor(color);
		g.fillRect((int) esquinaTransformada.getX(), (int) (esquinaTransformada
				.getY() - dameAlto(alto)), (int) dameAncho(ancho),
				(int) dameAlto(alto));
	}

	/**
	 * Dibuja un rectangulo relleno en 3D llamando al metodo fill3DRect() de la
	 * clase Graphics. La esquina, ancho y alto que se len pasan van en
	 * coordenadas de usuario.
	 * 
	 * @param esquina
	 *            Esquina inferior izquierda del rect�ngulo, en coordenadas
	 *            de usuario.<br>
	 * @param ancho
	 *            Ancho del rect�ngulo, en coordenadas de usuario.<br>
	 * @param alto
	 *            Alto del rect�ngulo.<br>
	 * @param color
	 *            Color del rect�ngulo.<br>
	 * @param elevado
	 *            Si es o no elevado.<br>
	 */
	public void pintaRectanguloRelleno3D(Point2D esquina, double ancho,
			double alto, Color color, boolean elevado)
	{
		Point2D esquinaTransformada = t.transform(esquina, null);

		g.setColor(color);
		g.fill3DRect((int) esquinaTransformada.getX(),
				(int) (esquinaTransformada.getY() - dameAlto(alto)),
				(int) dameAncho(ancho), (int) dameAlto(alto), elevado);
	}

	/**
	 * Dibuja un texto en la posici�n y del color indicado. La posici�n va en
	 * coordenadas de usuario.
	 * 
	 * @param posicion
	 *            COMENTARIO.<br>
	 * @param texto
	 *            COMENTARIO.<br>
	 * @param color
	 *            COMENTARIO.<br>
	 */
	public void pintaTexto(Point2D posicion, String texto, Color color)
	{
		Point2D.Double posicionTexto = new Point2D.Double();
		t.transform(posicion, posicionTexto);
		g.setColor(color);
		g.drawString(texto, (int) posicionTexto.getX(), (int) posicionTexto
				.getY());
	}

	/**
	 * Se le pasan los valores minimos y maximos que se van a dibujar en el
	 * Lienzo. No comprueba que los valores minimos sean mas peque�os que los
	 * m�ximos.
	 * 
	 * @param xMin
	 *            COMENTARIO.<br>
	 * @param yMin
	 *            COMENTARIO.<br>
	 * @param xMax
	 *            COMENTARIO.<br>
	 * @param yMax
	 *            COMENTARIO.<br>
	 */
	public void tomaExtremos(double xMin, double yMin, double xMax, double yMax)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		calculaGraphics();
		avisaObservadores();
	}

	/**
	 * Guarda el ancho y alto del �rea de dibujo. Crea una imagen para dibujar
	 * del ancho y alto que se le indican y calcula la AffineTransform encargada
	 * de transformar coordenadas de usuario en pixels. Pone unos valores
	 * minimos y maximos del grafico de -10.0 y 10.0 como valores por defecto.
	 * 
	 * @param g
	 *            COMENTARIO.<br>
	 * @param ancho
	 *            COMENTARIO.<br>
	 * @param alto
	 *            COMENTARIO.<br>
	 */
	public void tomaGraphics(Graphics g, int ancho, int alto)
	{
		if ((ancho < 1) || (alto < 1))
		{
			return;
		}

		this.ancho = ancho;
		this.alto = alto;
		this.g = g;

		calculaGraphics();
	}

	/**
	 * Avisa a los obseravadores de l�mites de usuario que estos han cambiado.
	 */
	protected void avisaObservadores()
	{
		int i;

		for (i = 0; i < observadores.size(); i++)
		{
			((ObservadorEscalaGrafica) (observadores.get(i)))
					.tomaNuevosExtremos(xMin, yMin, xMax, yMax);
		}
	}

	/**
	 * Calcula la clase AffineTransform que transforma coordenadas de usuario en
	 * pixels. Se llama a este m�todo despu�s de darle a esta clase el ancho y
	 * alto del area de dibujo (tomaGraphics()) o de cambiar los limites de
	 * usuario (tomaExtremos())
	 */
	private void calculaGraphics()
	{
		t = new AffineTransform();
		t.translate(0.0, alto);
		t.scale(ancho / (xMax - xMin), -alto / (yMax - yMin));
		t.translate(-xMin, -yMin);
	}

	/**
	 * Se le pasa un array de puntos en coordenadas de usuario y lo devuelve en
	 * pixels.
	 * 
	 * @param puntos
	 *            COMENTARIO.<br>
	 * @return COMENTARIO.<br>
	 */
	private Point2D[] dameTransformados(Point2D[] puntos)
	{
		int i;
		Point2D[] puntosTransformados;

		if (puntos == null)
		{
			return null;
		}

		puntosTransformados = new Point2D[puntos.length];

		for (i = 0; i < puntos.length; i++)
		{
			puntosTransformados[i] = new Point2D.Double();
		}

		t.transform(puntos, 0, puntosTransformados, 0, puntos.length);

		return puntosTransformados;
	}

    /**
     * Devuelve el punto que se le pasa.
     */
	public Point2D getCoordenadasCartesianas(Point2D punto)
	{
		return punto;
	}

    /**
     * Devuelve los extremos cartesianos
     */
	public Rectangle2D getExtremos()
	{
        return getExtremosCartesianos();
	}
}
