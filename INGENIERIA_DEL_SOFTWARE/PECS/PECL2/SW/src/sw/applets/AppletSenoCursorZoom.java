/*
 * Javier Abell�n. 1 de Mayo de 2004
 *
 * Ejemplo de funcion seno.
 */
package sw.applets;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JApplet;

import com.chuidiang.graficos.EscalaGraficaCartesiana;
import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.botones.VisorRaton;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorHilo;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorZoom;
import com.chuidiang.graficos.objetos_graficos.Mano;
import com.chuidiang.graficos.objetos_graficos.RejillaFija;
import com.chuidiang.graficos.objetos_graficos.matematicos.ObjetoGraficoSeno;
import com.chuidiang.matematicas.funciones.Seno;

/**
 * Ejemplo de uso de los Objetos gr�ficos Seno y RejillaFija.
 */
public class AppletSenoCursorZoom extends JApplet
{
    /**
	 * serial uid
	 */
	private static final long serialVersionUID = 3616447921122652471L;

	/**
     * Instancia un Lienzo y mete dentro un Seno y una RejillaFija con la
     * escala adecuada. Pone tambi�n una botoner�a con zoom de acercar y 
     * alejar
     */
    public void init ()
    {
        // El lienzo
        Lienzo lienzo = new Lienzo();
        lienzo.setBackground(Color.black);
        lienzo.setSize(300,100);
        
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
        this.setLayout (new BorderLayout());
        this.add(lienzo, BorderLayout.CENTER);
        this.add(zoom, BorderLayout.SOUTH);
        
        // La escala grafica para inicializar el lienzo.
        escala.tomaExtremos (0.0, -1.0, 1.0, 1.0);
        lienzo.tomaEscala (escala);
        
        // Una rejill para el Lienzo
        RejillaFija rejilla = new RejillaFija (5, 10);
        lienzo.tomaObjetoGrafico(rejilla);
        
        // La funci�n Seno para el lienzo.
        ObjetoGraficoSeno seno = new ObjetoGraficoSeno(1.0, 1.0, 0.0, Color.GREEN);
      
        lienzo.tomaObjetoGrafico(seno);
        
        CursorZoom cursor = new CursorZoom();
        // lienzo.tomaObjetoGrafico(cursor);
        lienzo.anhadeObservadorRaton(cursor);
        
        CursorHilo cursorHilo = new CursorHilo(true,true);
        lienzo.anhadeObservadorRaton(cursorHilo);

        lienzo.anhadeObservadorRaton(visorX);
        lienzo.anhadeObservadorRaton(visorY);
        
        Mano mano = new Mano(escala,lienzo);
        lienzo.anhadeObservadorRaton(mano);
    }
    
}
