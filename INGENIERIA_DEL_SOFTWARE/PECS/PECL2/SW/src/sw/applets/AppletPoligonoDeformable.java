/*
 * Javier Abell�n. 1 de Mayo de 2004
 *
 * Ejemplo de funcion seno.
 */
package sw.applets;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.chuidiang.graficos.EscalaGraficaCartesiana;
import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.objetos_arrastrables.PoligonoDeformable;
import com.chuidiang.graficos.objetos_graficos.Mano;

/**
 * Ejemplo de uso de los Objetos gr�ficos Seno y RejillaFija.
 */
public class AppletPoligonoDeformable extends JApplet
{
	/**
	 * serial uid
	 */
	private static final long serialVersionUID = 4048795684322686518L;

    public static void main (String [] args)
    {
        JFrame v = new JFrame("Prueba pol�gnos deformables");
        creaComponentes(v.getContentPane());
        v.setSize(500,500);
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
	/**
	 * Instancia un Lienzo y mete dentro un Seno y una RejillaFija con la escala
	 * adecuada. Pone tambi�n una botoner�a con zoom de acercar y alejar
	 */
	public void init()
    {
        creaComponentes (this); 
    }
    
    /**
     * Mete e inicializa los componentes para la prueba en el contenedor que se
     * le pasa. Ser� un applet o un frame.
     * @param contenedor El contenedor.
     */
    private static void creaComponentes (java.awt.Container contenedor)
	{
		// El lienzo
		Lienzo lienzo = new Lienzo();
		lienzo.setBackground(Color.black);

		// La escala grafica
		InterfaceEscalaGrafica escala = new EscalaGraficaCartesiana();

		// Una botoneria de zoom
		BotoneriaZoom zoom = new BotoneriaZoom(escala);

		// Lo metemos todo en el applet
		contenedor.setLayout(new BorderLayout());
        contenedor.add(lienzo, BorderLayout.CENTER);
        contenedor.add(zoom, BorderLayout.SOUTH);

		// La escala grafica para inicializar el lienzo.
		escala.tomaExtremos(-10.0, -10.0, 10.0, 10.0);
		lienzo.tomaEscala(escala);

        // Una mano para poder arrastrar el gr�fico 
		Mano mano = new Mano(escala, lienzo);
		lienzo.anhadeObservadorRaton(mano);

        // Se a�aden cuatro pol�gonos de color al gr�fico 
		Color[] colores = { Color.red, Color.white, Color.yellow, Color.green };
		for (int i = 0; i < 4; i++)
		{
			double[] x = { -2.0 + i, -2.0 + i, 2.0 + i, 2.0 + i };
			double[] y = { -2.0 + i, 2.0 + i, 2.0 + i, -2.0 + i };
			PoligonoDeformable poligono = new PoligonoDeformable(x, y);
            
            // se hace que el pol�gono sea cerrado
			poligono.setCerrado(true);
            // Se pone el color del pol�gono
			poligono.setColor(colores[i]);
			lienzo.anhadeObservadorRaton(poligono);
		}
	}

}
