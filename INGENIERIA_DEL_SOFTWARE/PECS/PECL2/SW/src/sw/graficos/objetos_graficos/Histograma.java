
/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Histograma de una serie de valores. Dibuja unas rectangulos verticales,
 * rellenos de color, en el que la altura de cada uno de ellos corresponde a uno
 * de los valores. Ajusta el ancho de las columnas para que ocupen el ancho del
 * gr�fico, pero no hace nada con el eje vertical, de forma que debe estar ya
 * escalado para los valores que se pasen (desde 0.0 hasta el valor).
 */
public class Histograma extends AbstractObjetoGrafico
{
    /**
     * Crea un objeto histograma. Se le pasan las alturas de cada una de las
     * barras y los colores que se quieren para cada barra. Las barras
     * comenzaran en y=0.0 y llegar�n hasta valores[i]. El Lienzo debe estar
     * adecuadamente escalado en este eje.
     */
    public Histograma(double[] valores, Color[] colores)
    {
        this.valores = valores;
        this.colores = colores;
    }

    /**
     * Dibuja el histrograma utilizando la EscalaGrafica que se le pasa.
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        extremos = escala.getExtremosCartesianos();
        Point2D esquina = new Point2D.Double();

        int indiceColor = 0;

        for (int i = 0; i < valores.length; i++)
        {
            esquina.setLocation(dameX(i), 0.0);

            escala.pintaRectanguloRelleno3D(esquina, dameAncho(), valores[i],
                    colores[indiceColor], true);

            indiceColor++;
            if (indiceColor >= colores.length)
                indiceColor = 0;
        }
    }

    /**
     * Devuelve el ancho de la columna de cada histograma, de forma que ocupe un
     * 90% de su ancho m�ximo, para dejar un hueco entre unas y otras y con los
     * bordes del gr�fico.
     */
    private double dameAncho()
    {
        if (valores == null)
            return 0.0;
        if (valores.length == 0)
            return 0.0;

        double ancho = extremos.getWidth() / valores.length;
        ancho = ancho * 0.9;

        return ancho;
    }

    /**
     * Devuelve la x inicial correspondiente a la barra i
     */
    private double dameX(int i)
    {
        if (valores == null)
            return 0.0;
        if (valores.length == 0)
            return 0.0;

        double ancho = extremos.getWidth() / valores.length;
        double x = extremos.getMinX() + ancho * i;
        x = x + ancho * 0.05;

        return x;
    }

    /** Coordenadas m�ximas y m�nimas del Lienzo */
    private Rectangle2D extremos;

    /** Valores del histograma */
    private double[] valores;

    /** Colores para cada barra del histograma */
    private Color[] colores;
}
