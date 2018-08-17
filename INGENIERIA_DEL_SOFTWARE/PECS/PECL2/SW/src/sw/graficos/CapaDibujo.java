
/*
 * Javier Abell�n, 1 de Junio de 2004
 *
 * Lienzo de dibujo.
 */
package com.chuidiang.graficos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JComponent;

import com.chuidiang.graficos.objetos_graficos.IfzObjetoGrafico;
import com.chuidiang.graficos.objetos_graficos.ObservadorRepintado;

/**
 * Lienzo de dibujo.<br>
 * Hereda de Canvas y admite una EscalaGrafica y ObjetosGraficos. Cuando el
 * Canvas necesita repintado, hace que todos los ObjetosGraficos se redibujen
 * utilizando la EscalaGrafica que se le pasa.
 */
public class CapaDibujo extends JComponent implements ObservadorRepintado
{
    // ~ Variables/Inicializadores estaticos
    // -------------------------------------

    /** serial uid */
    private static final long serialVersionUID = 3977578112823080752L;

    // ~ Variables de instancia
    // --------------------------------------------------

    /** Lista de objetos a dibujar */
    LinkedList<IfzObjetoGrafico> objetos = new LinkedList<IfzObjetoGrafico>();

    /** Indica si necesitaq repintarse */
    boolean hayQueRepintar = false;

    /**
     * Observador de cambios de limites en la EscalaGrafica. Hace que se repinte
     * el Lienzo
     */
    ObservadorEscalaGrafica observador = new ObservadorEscalaGrafica()
    {
        /**
         * M�todo al que se avisar�
         * 
         * @param xMin
         *            x minima.<br>
         * @param yMin
         *            y minima.<br>
         * @param xMax
         *            x maxima.<br>
         * @param yMax
         *            y maxima.<br>
         */
        public void tomaNuevosExtremos(double xMin, double yMin, double xMax,
                double yMax)
        {
            hayQueRepintar = true;
            repaint();
        }
    };

    /** imagen que se va a dibujar en el componente */
    private Image imagen = null;

    /** EscalaGrafica sobre la que se dibujaran los ObjetosGraficos */
    private InterfaceEscalaGrafica escala;

	private int ultimoAncho;

	private int ultimoAlto;

    // ~ Constructores
    // -----------------------------------------------------------

    /**
     * Crea una instancia de Lienzo.
     */
    public CapaDibujo()
    {
        this.setSize(100, 100);
        ultimoAncho = getWidth();
		ultimoAlto = getWidth();
		imagen = new BufferedImage(ultimoAncho, ultimoAlto,
                BufferedImage.TYPE_3BYTE_BGR);

        this.addComponentListener(new ComponentListener()
        {
            public void componentHidden(ComponentEvent e)
            {
            }

            public void componentShown(ComponentEvent e)
            {
            }

            public void componentMoved(ComponentEvent e)
            {
            }

            public void componentResized(ComponentEvent e)
            {
                if ((getWidth() < 1) || (getHeight() < 1))
                {
                    return;
                }
                hayQueRepintar = true;
                repaint();
            }
        });
    }

    // ~ Metodos
    // -----------------------------------------------------------------

    /**
     * Llama al repaint() del componente.<br>
     */
    public void necesitoRepintado()
    {
        repaint();
    }

    /**
     * Redibuja todos los ObjetosGraficos que tiene en su lista.
     * 
     * @param g
     *            El graphics de este componente.<br>
     */
    public void paint(Graphics g)
    {
        int i;
        if (escala == null)
        {
            return;
        }
        for (i = 0; i < objetos.size(); i++)
        {
            if ((objetos.get(i)).necesitasRepintado())
            {
            	hayQueRepintar=true;
                break;
            }
        }

        if (hayQueRepintar)
        {
        	if ((ultimoAncho != getWidth()) || (ultimoAlto != getHeight()))
        	{
        		imagen = new BufferedImage(getWidth(), getHeight(),
                    BufferedImage.TYPE_3BYTE_BGR);
        	}
        	
            Graphics2D g2d = (Graphics2D) imagen.getGraphics();

            // Enable antialiasing for shapes
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Enable antialiasing for text
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());

            escala.tomaGraphics(g2d, getWidth(), getHeight());

            for (i = 0; i < objetos.size(); i++)
            {
                ((IfzObjetoGrafico) objetos.get(i)).dibujate(escala);
            }
        }

        g.drawImage(imagen, 0, 0, this);
    }

    /**
     * Se le pasa la EsclaGrafica que se utilizara para el dibujado. Esta clase
     * har� que los ObjetosGraficos se dibujen sobre esta EscalaGrafica, de
     * forma que el sistema de coordenadas y limites de dibujo ser�n los que
     * indique la EscalaGrafica.
     * 
     * @param escala
     *            La escala grafica de dibujo.<br>
     */
    public void tomaEscala(InterfaceEscalaGrafica escala)
    {
        if (this.escala != null)
        {
            this.escala.eliminaObservador(observador);
        }

        this.escala = escala;
        escala.anhadeObservador(observador);
        escala.tomaGraphics(imagen.getGraphics(), getWidth(), getHeight());
    }

    /**
     * A�ade el ObjetoGrafico que se le pasa a la lista de Objetos gr�ficos que
     * se pintar�n sobre el Lienzo.
     * 
     * @param unObjeto
     *            Un objeto grafico a pintar.<br>
     */
    public void tomaObjetoGrafico(IfzObjetoGrafico unObjeto)
    {
        objetos.add(unObjeto);
        unObjeto.addObservadorRepintado(this);
        hayQueRepintar = true;
        repaint();
    }
}
