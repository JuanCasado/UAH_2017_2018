/*
 * Javier Abell�n. 1 de Mayo de 2004
 *
 * Ejemplo de funcion seno.
 */
package sw.applets;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.chuidiang.graficos.EscalaGraficaCartesiana;
import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.botones.VisorRaton;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorZoom;
import com.chuidiang.graficos.objetos_graficos.GraficoLineasNivel;
import com.chuidiang.graficos.objetos_graficos.Mano;
import com.chuidiang.graficos.objetos_graficos.RejillaFija;

/**
 * Ejemplo de uso de los Objetos gr�ficos Seno y RejillaFija.
 */
public class AppletLineasNivel extends JFrame
{
	public static void main(String[] args) {
		new AppletLineasNivel();
	}
    /**
	 * serial uid
	 */
	private static final long serialVersionUID = 3258129141797499953L;

	/**
     * Instancia un Lienzo y mete dentro un Seno y una RejillaFija con la
     * escala adecuada. Pone tambi�n una botoner�a con zoom de acercar y 
     * alejar
     */
    public  AppletLineasNivel() {
        // El lienzo
        Lienzo lienzo = new Lienzo();
        lienzo.setBackground(Color.black);
        
        // La escala grafica
        InterfaceEscalaGrafica escala = new EscalaGraficaCartesiana();
        
        // Una botoneria de zoom
        BotoneriaZoom zoom = new BotoneriaZoom (escala);
        
        // Lo metemos todo en el applet
        this.getContentPane().setLayout (new BorderLayout());
        this.getContentPane().add(lienzo, BorderLayout.CENTER);
        this.getContentPane().add(zoom, BorderLayout.SOUTH);
        
        // La escala grafica para inicializar el lienzo.
        escala.tomaExtremos (0.0, 0.0, 2.0, 2.0);
        lienzo.tomaEscala (escala);
        
        // Una rejill para el Lienzo
        RejillaFija rejilla = new RejillaFija (5, 10);
        lienzo.tomaObjetoGrafico(rejilla);
        
        // La funci�n Seno para el lienzo.
        GraficoLineasNivel lineasNivel = new GraficoLineasNivel();
        lienzo.tomaObjetoGrafico(lineasNivel);
        
        // Un editor de coordenada x y otro de y
        VisorRaton visorX = new VisorRaton(VisorRaton.VISOR_X);
        visorX.setColumns(20);
        zoom.add(visorX);
        lienzo.anhadeObservadorRaton(visorX);
        VisorRaton visorY = new VisorRaton(VisorRaton.VISOR_Y);
        visorY.setColumns(20);
        zoom.add(visorY);
        lienzo.anhadeObservadorRaton(visorY);
        
        // Un zoom con cursor de goma
        CursorZoom cursorZoom=new CursorZoom();
        lienzo.anhadeObservadorRaton(cursorZoom);
        lienzo.tomaObjetoGrafico(cursorZoom);
        
        // Una mano para poder arrastrar el gr�fico.
        Mano mano = new Mano(escala,lienzo);
        lienzo.anhadeObservadorRaton(mano);
        
        // Y finalmente un cursor vertical y horizontal
        /* 
         * Comentado para no liar con el uso del rat�n para la mano
         * y para el zoom de goma
         * 
        CursorHilo cursor = new CursorHilo(true,true);
        lienzo.anhadeObservadorRaton(cursor);
        lienzo.tomaObjetoGrafico(cursor);
        
        
        */
        
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    
}
