/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Contiene m�todos de dibujado en unidades de usuario.
 */
public interface InterfaceEscalaGrafica
{
    /**
     * Extremos del gr�fico en coordenadas de usuario.
     */
    public void tomaExtremos(double xMin, double yMin, double xMax, double yMax);

    public void pintaImagen (Point2D posicion, Image imagen);
    /**
     * Dado un punto con coordenadas x,y en pixels, devuelve un punto con las
     * coordenadas x,y del usuario correspondientes.
     * 
     * @param punto
     *            Un punto con coordenadas x,y de raton.
     * @return Un punto con coordenadas x,y de usuario.
     */
    public Point2D getCoordenadaUsuario(Point2D punto);
    
    /**
     * Dadas una coordenadas de usuario propias del tipo de escala usada,
     * devuelve unas coordenadas de usuario cartesianas.
     * @param punto Un Punto en coordenadas de usuario en el tipo de escala
     * usada
     * @return Un punto en coordenadas de usuario cartesianas
     */
    public Point2D getCoordenadasCartesianas (Point2D punto);

    /**
     * Devuelve los extremos del gr�fico en coordenadas cartesianas de usuario.
     */
    public Rectangle2D getExtremosCartesianos();

    /**
     * Devuelve los extremos del gr�fico en las coordenadas de usuario propias
     * de la escala que se est� usando (cartesianas o polares)
     * @return Extremos
     */
    public Rectangle2D getExtremos();
    
    /**
     * Se len pasa el alto y el ancho en pixels de la zona de dibujo.
     */
    public void tomaGraphics(Graphics g, int ancho, int alto);


    /**
     * Dibuja los puntos del x[i], y[i] del color[i]
     */
    public void pintaPuntos(Point2D[] puntos, Color[] color);

    /**
     * Dibuja los puntos x[i], y[i] de color
     */
    public void pintaPuntos(Point2D[] puntos, Color color);

    /**
     * Dibuja una l�nea uniendo consecutivamente los puntos x[i], y[i] del color
     * indicado.
     */
    public void pintaPoliLinea(Point2D[] puntos, Color color);

    /**
     * Dibuja el texto que se le pasa en las coordenadas de usuario que se le
     * pasa y con el color indicado.
     */
    public void pintaTexto(Point2D posicion, String texto, Color color);

    /**
     * Dibuja un arco. La esquina es una de las esquinas del rect�ngulo
     * circunscrito a la circunferencia del arco. En ancho y alto son de este
     * rect�ngulo. El arcoInicial y arcoFinal son d�nde comienza y termina el
     * arco de circunferencia, ambas en grados. El color es el color del arco.
     */
    public void pintaArco(Point2D esquina, double ancho, double alto,
            double arcoInicial, double arcoFinal, Color color);

    /**
     * Dibuja un arco relleno. La esquina es una de las esquinas del rect�ngulo
     * circunscrito a la circunferencia del arco. En ancho y alto son de este
     * rect�ngulo. El arcoInicial y arcoFinal son d�nde comienza y termina el
     * arco de circunferencia, ambas en grados. El color es el color del arco.
     */
    public void pintaArcoRelleno(Point2D esquina, double ancho, double alto,
            double arcoInicial, double arcoFinal, Color color);

    /**
     * Dibuja un rect�ngulo relleno. Llama al m�todo correspondiente de la clase
     * Graphics.
     */
    public void pintaRectanguloRelleno(Point2D esquina, double ancho,
            double alto, Color color);

    /**
     * Dibuja un rect�ngulo relleno 3D. Llama al m�todo correspondiente de la
     * clase Graphics.
     */
    public void pintaRectanguloRelleno3D(Point2D esquina, double ancho,
            double alto, Color color, boolean elevado);

    /**
     * Recoge observadores a los que se avisar� cuando cambien los extremos del
     * gr�fico (valores minimos y m�ximos a dibujar).
     */
    public void anhadeObservador(ObservadorEscalaGrafica unObservador);

    /**
     * Elimina un observador a�adido con el m�todo anhadeObservador().
     */
    public void eliminaObservador(ObservadorEscalaGrafica unObservador);
    
    /**
     * Pinta un eje de las x en la coordenada y indicada
     * @param y Coordenada y donde se quiere el eje de las x
     * @param color Color del eje
     */
    public void pintaEjeX(double y, Color color);
    
    /**
     * Pinta un eje de las y en la coordenada x indicada
     * 
     * @param x Coordenada x donde se quiere el eje de las y
     * @param color Color del eje
     */
    public void pintaEjeY(double x, Color color);
}
