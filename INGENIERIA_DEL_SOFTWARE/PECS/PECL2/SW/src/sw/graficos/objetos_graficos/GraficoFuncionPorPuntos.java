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
 * Dibuja una funcion que se define por medio de un array de puntos. Se le pasa
 * un valor de x minima, un x maxima y un array de valores de y, de forma que el
 * primer valor del array corresponde a x minima, el �ltimo valor del array a x
 * m�xima y los dem�s se suponen correspondiente a valores de x equiespaciados
 * entre x minima y x maxima.
 */
public class GraficoFuncionPorPuntos extends AbstractObjetoGrafico
{
    /**
     * Construye una instancia de GraficoPuntos.<br>
     * 
     * @param xMin
     *            Valor de x minima
     * @param xMax
     *            Valor de x maxima
     * @param puntos
     *            Valores de y
     */
    public GraficoFuncionPorPuntos(double xMin, double xMax, double[] puntos)
    {
        this.xMin = Math.min(xMin, xMax);
        this.xMax = Math.max(xMin, xMax);
        this.puntos = puntos;
        this.setNecesitoRepintado(true);
    }

    /**
     * Dibuja una linea uniendo los puntos conseguidos con xmin, xmax y el array
     * de y que se pasaron en el constructor o a trav�s del m�todo setPuntos(),
     * setXMin() y setXMax()
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        if (this.puntos == null)
            return;
        if (this.puntos.length == 0)
            return;
        Point2D p[] = new Point2D[this.puntos.length];
        double incremento = (xMax - xMin) / (this.puntos.length - 1);
        for (int i = 0; i < this.puntos.length; i++)
        {
            p[i] = new Point2D.Double(xMin + i * incremento, this.puntos[i]);
        }
        escala.pintaPoliLinea(p, color);
    }

    /**
     * Color para pintar el gr�fico
     */
    private Color color = Color.CYAN;

    /**
     * Valores de x minima y x maxima
     */
    private double xMin, xMax;

    /**
     * Valores de y
     */
    private double[] puntos;

    /**
     * Devuelve el array de valores de y
     * 
     * @return Array de valores y
     */
    public double[] getPuntos()
    {
        return puntos;
    }

    /**
     * Se le pasa el array de valores y
     * 
     * @param puntos
     *            Valores y
     */
    public void setPuntos(double[] puntos)
    {
        this.puntos = puntos;
        this.setNecesitoRepintado(true);
    }

    /**
     * Devuelve x maximo
     * 
     * @return x maximo
     */
    public double getXMax()
    {
        return xMax;
    }

    /**
     * Fija x maximo.<br>
     * Tiene en cuenta que sea mayor que x minimo, d�ndoles la vuelta si es
     * necesario.
     * 
     * @param max
     *            x maximo
     */
    public void setXMax(double max)
    {
        xMax = Math.max(max, xMin);
        xMin = Math.min(max, xMin);
    }

    /**
     * Devuelve el valor de x minimo
     * 
     * @return x minimo
     */
    public double getXMin()
    {
        return xMin;
    }

    /**
     * Fija el valor de x maximo.<br>
     * Tiene en cuenta que sea menor que x minimo, d�ndoles la vuelta si es
     * neceario.
     * 
     * @param min
     *            x minimo
     */
    public void setXMin(double min)
    {
        xMin = Math.min(min, xMax);
        xMax = Math.max(min, xMax);
    }

    /**
     * Devuelve el color con el que se va a pintar el gr�fico.
     * 
     * @return El color del gr�fico
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Fija el color con el que se va a pintar el gr�fico.
     * 
     * @param color
     *            El coor del gr�fico
     */
    public void setColor(Color color)
    {
        this.color = color;
    }
}
