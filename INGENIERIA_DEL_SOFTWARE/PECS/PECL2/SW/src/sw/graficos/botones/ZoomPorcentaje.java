/*
 * Javier Abell�n, 30 de mayo de 2004
 * ZoomPorcentaje.java
 */

package com.chuidiang.graficos.botones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.matematicas.UtilRectangulo;



/**
 * Bot�n de zoom gr�fico para acercar o alejar el gr�fico. Se crea el bot�n
 * pas�ndole la EscalaGr�fica sobre la que debe actuar y el porcentaje de
 * acercamiento o alejamiento que se quiere cada vez que se pulse el bot�n.
 */
public class ZoomPorcentaje extends JButton implements InterfaceZoom<Integer>
{
    /**
	 * Serial uid
	 */
	private static final long serialVersionUID = -9044127362988998605L;

	/**
     * Constructor del bot�n de zoom de acercar o alejar. Se le pasa la escala
     * gr�fica sobre la que se quiere que actue el bot�n. El porcentaje de
     * acercamiento o alejamiento va entre -100 y 100, excluidos ambos valores.
     * Un porcentaje positivo hace que el gr�fico se acerque. Un valor negativo
     * hace que se aleje.
     */
    public ZoomPorcentaje(InterfaceEscalaGrafica escala, int porcentaje)
    {
        this.escala = escala;
        tomaPorcentaje(porcentaje);

        addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                efectuarZoom(ZoomPorcentaje.this.damePorcentaje());
            }
        });
    }

    /**
     * Se le pasa el porcentaje de acercamiento del gr�fico cada vez que se
     * pulse el bot�n. El rango v�lido para porcentaje es de -99 a 99. Cualquier
     * otro valor se ignora. Un porcentaje negativo har� que el zoom sea de
     * alejar y uno positivo de acercar.
     */
    public void tomaPorcentaje(int porcentaje)
    {
        if ((porcentaje > -100) && (porcentaje < 100))
            this.porcentaje = porcentaje;
    }

    /**
     * Devuelve el porcentaje de acercamiento del bot�n
     */
    public int damePorcentaje()
    {
        return porcentaje;
    }

    /**
     * Realiza el zoom con el porcentaje interno de la clase. El porcentaje va
     * de -99 a 99. Un porcentaje positivo acerca, uno negativo aleja.
     */
    public void efectuarZoom(Integer unPorcentaje)
    {
        Rectangle2D extremos = escala.getExtremosCartesianos(  );

        Rectangle2D nuevo = UtilRectangulo.agrandar( 
              extremos,
              unPorcentaje.intValue() );
        escala.tomaExtremos( 
           nuevo.getMinX(  ),
           nuevo.getMinY(  ),
           nuevo.getMinX(  ) + nuevo.getWidth(  ),
           nuevo.getMinY(  ) + nuevo.getHeight(  ) );
    }

    /** Escala gr�fica sobre la que actua el bot�n de acercar. */
    protected InterfaceEscalaGrafica escala;

    /** Porcentaje de acercamiento cada vez que se pulsa el bot�n */
    protected int porcentaje = 10;

}
