/*
 * Javier Abell�n. 1 de Mayo de 2004
 *
 * Ejemplo de funcion seno.
 */
package sw.applets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.chuidiang.graficos.EscalaGraficaCartesiana;
import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.botones.VisorRaton;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorZoom;
import com.chuidiang.graficos.objetos_graficos.GraficoFuncionPorPuntos;
import com.chuidiang.graficos.objetos_graficos.Mano;
import com.chuidiang.graficos.objetos_graficos.RejillaFija;

/**
 * Ejemplo de uso de los Objetos gr�ficos Seno y RejillaFija.
 */
public class PruebaGraficoPuntosCursorZoom extends JFrame
{
    /**
	 * serial uid
	 */
	private static final long serialVersionUID = 3257003272006219313L;

	/**
     * Instancia un Lienzo y mete dentro un Seno y una RejillaFija con la
     * escala adecuada. Pone tambi�n una botoner�a con zoom de acercar y 
     * alejar
     */
    public static void main (String []args)
    {
        new PruebaGraficoPuntosCursorZoom();
    }
    
    public PruebaGraficoPuntosCursorZoom()
    {
        // El lienzo
        Lienzo lienzo = new Lienzo();
        lienzo.setPreferredSize(new Dimension(300,100));
        lienzo.setBackground(Color.BLACK);
        
        // La escala grafica
        InterfaceEscalaGrafica escala = new EscalaGraficaCartesiana();
        
        // Una botoneria de zoom
        BotoneriaZoom zoom = new BotoneriaZoom (escala);
        
        VisorRaton visorX = new VisorRaton (VisorRaton.VISOR_X);
        visorX.setColumns(20);
        VisorRaton visorY = new VisorRaton (VisorRaton.VISOR_Y);
        visorY.setColumns(20);
        zoom.add (visorX);
        zoom.add (visorY);
        
        // Lo metemos todo en el applet
        this.getContentPane().setLayout (new BorderLayout());
        this.getContentPane().add(lienzo, BorderLayout.CENTER);
        this.getContentPane().add(zoom, BorderLayout.SOUTH);
        
        // La escala grafica para inicializar el lienzo.
        escala.tomaExtremos (0.0, -1.0, 1.0, 1.0);
        lienzo.tomaEscala (escala);
        
        // Una rejill para el Lienzo
        RejillaFija rejilla = new RejillaFija (5, 10);
        lienzo.tomaObjetoGrafico(rejilla);
        
        // La funci�n Seno para el lienzo.
        double p[]=new double[4];
        GraficoFuncionPorPuntos puntos = new GraficoFuncionPorPuntos(0.0,1.0,p);
        lienzo.tomaObjetoGrafico(puntos);
        
        CursorZoom cursor = new CursorZoom();
        lienzo.anhadeObservadorRaton(cursor);

        lienzo.anhadeObservadorRaton(visorX);
        lienzo.anhadeObservadorRaton(visorY);
        
        Mano mano = new Mano(escala,lienzo);
        lienzo.anhadeObservadorRaton(mano);
        
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        while (true)
        {
            for (int i=0; i<p.length;i++)
                p[i] = Math.random()*2.0-1.0;
            puntos.setPuntos(p);
            try
            {
                Thread.sleep(500);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
}
