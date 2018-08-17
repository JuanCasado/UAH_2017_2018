/*
 * Fichero: AbstractAppletPruebaLienzo.java
 * Autor: Chuidiang
 * Fecha: 3/12/06 21:32
 */
package sw.applets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.chuidiang.graficos.EscalaGraficaCartesiana;
import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.Lienzo;


/**
 * Clase padre para los ejemplos.
 *
 * @author Chuidiang
 *
  */
public abstract class AbstractAppletPruebaLienzo extends JApplet
{
    /** Serial uid  */
    private static final long serialVersionUID = 3257854285449738041L;

    /** Lienzo de dibujo */
    protected Lienzo lienzo;

    /** Escala de coordenadas de usuario para el lienzo */
    protected InterfaceEscalaGrafica eg;

    /** Panel con los botones de zoom m�s y menos */
    protected BotoneriaZoom botones;

    /**
     * Inicializa el Applet
     */
    public void init()
    {
        inicializaLienzo(this);
    }

    /**
     * Crea el lienzo y mete en �l el gr�fico de puntos correctamente
     * inicializado, la mano para arrastrar el gr�fico, una rejilla, etc.
     */
    protected void inicializaLienzo(Container contenedor)
    {
        lienzo = new Lienzo();
        lienzo.setBackground(Color.black);
        eg = dameEscalaGrafica();
        ponExtremos(eg);

        lienzo.tomaEscala(eg);
        ponObjetosGraficos(lienzo);

        if (quieresBotoneria())
        {
            botones = new BotoneriaZoom(eg);
            ponBotonesAdicionales(botones);

            contenedor.add(lienzo, BorderLayout.CENTER);
            contenedor.add(botones, BorderLayout.SOUTH);
        }
        else
        {
            contenedor.add(lienzo);
        }
    }

    /**
     * Devuelve la escala grafica
     *
     * @return escala grafica
     */
    protected InterfaceEscalaGrafica dameEscalaGrafica()
    {
        return new EscalaGraficaCartesiana();
    }

    /**
     * Las clases hijas deben rellenar en BotoneriaZoom los botones adicionales que quieran
     *
     * @param botoneria Sitio en el que a�adir los botones.
     */
    protected abstract void ponBotonesAdicionales(BotoneriaZoom botoneria);

    /**
     * Las clases hijas deben decir si quieren o no una botoner�a adicional de zoom o
     * similar.
     *
     * @return true si quieren botoneria adicional.
     */
    protected abstract boolean quieresBotoneria();

    /**
     * Las clases hijas deben a�adir los objetos gr�ficos que deseen al lienzo.
     *
     * @param lienzo2 Lienzo al que a�adir los objetos graficos.
     */
    protected abstract void ponObjetosGraficos(Lienzo lienzo2);

    /**
     * Las clases hijas deben poner los extremos iniciales en la escala grafica que se le
     * pasan.
     *
     * @param eg2 Escala grafica a la que poner extremos iniciales.
     */
    protected abstract void ponExtremos(InterfaceEscalaGrafica eg2);

    /** Visualiza un Frame con el gr�fico dentro y la botoner�a de zoom */
    public void visualizaFrame()
    {
        JFrame v = new JFrame();
        inicializaLienzo(v.getContentPane());
        v.pack();
        v.setSize(500, 500);
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
