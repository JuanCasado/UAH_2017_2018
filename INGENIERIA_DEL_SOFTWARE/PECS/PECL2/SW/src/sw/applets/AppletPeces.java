/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package sw.applets;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorHilo;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorZoom;
import com.chuidiang.graficos.objetos_graficos.GraficoSimbolos;
import com.chuidiang.graficos.objetos_graficos.Mano;

/**
 * Gr�fico x/y con im�genes. Se pintan unos peces que se van moviendo por
 * pantalla.
 * 
 * @author Chuidiang
 */
public class AppletPeces extends AbstractAppletPruebaLienzo
{
	/**
	 * serial uid
	 */
	private static final long serialVersionUID = 3257571685241991734L;



	/** Posiciones de los peces */
	private static Point2D.Double [] puntos;

	/** Etiqueta para cada pez */
	private static String[] etiquetas;

	/** Las im�genes de los peces */
	private static Image[] imagenes;

	/** Gr�fico de im�genes */
	private GraficoSimbolos gs;

    /** Numero de peces */
	private static final int numeroPeces = 5;


	/**
	 * Main por si no se quiere arrancar este programa como Applet
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		imagenes = new Image[numeroPeces];
		imagenes[0] = new ImageIcon("../imagenes/amberjack_1c.gif").getImage();
		imagenes[1] = new ImageIcon("../imagenes/bannerfish_1c.gif").getImage();
		imagenes[2] = new ImageIcon("../imagenes/bass_yellow_1c.gif")
				.getImage();
		imagenes[3] = new ImageIcon("../imagenes/butterflyfish2_2c.gif")
				.getImage();
		imagenes[4] = new ImageIcon("../imagenes/clownfish_2c.gif").getImage();
		AppletPeces peces = new AppletPeces();
        peces.visualizaFrame();
        peces.comienzaMovimientoImagenes();
        
	}


    /**
     * Arranca un hilo que mueve los peces cada 500 ms
     *
     */
	public void comienzaMovimientoImagenes()
	{
		Thread hilo = new Thread()
		{
			public void run()
			{
				try
				{
					while (true)
					{
						for (int i = 0; i < 5; i++)
						{
							puntos[i] = new Point2D.Double(puntos[i].x
									+ Math.random() * 4 - 2, puntos[i].y
									+ Math.random() * 4 - 2);
						}
						gs.tomaPuntos(puntos, imagenes, etiquetas);
						Thread.sleep(500);
					}
				} catch (Exception e)
				{

				}
			}
		};
		hilo.start();
	}

    /**
     * M�todo start() del Applet.<br>
     * Carga las im�genes de peces, inizializa el lienzo de dibujo y lo mete
     * todo en el Applet.
     * Luego llama al m�todo que arranca el hilo para mover los peces
     */
	public void init()
	{
		imagenes = new Image[numeroPeces];
		imagenes[0] = getImage(getCodeBase(), "imagenes/amberjack_1c.gif");
		imagenes[1] = getImage(getCodeBase(), "imagenes/bannerfish_1c.gif");
		imagenes[2] = getImage(getCodeBase(), "imagenes/bass_yellow_1c.gif");
		imagenes[3] = getImage(getCodeBase(), "imagenes/butterflyfish2_2c.gif");
		imagenes[4] = getImage(getCodeBase(), "imagenes/clownfish_2c.gif");
        super.init();
		comienzaMovimientoImagenes();
	}

	@Override
	protected void ponBotonesAdicionales(BotoneriaZoom botoneria)
	{
		
	}

	@Override
	protected boolean quieresBotoneria()
	{
		return true;
	}

	@Override
	protected void ponObjetosGraficos(Lienzo lienzo2)
	{
		lienzo.setColorMarco(Color.GRAY);
		lienzo.setPintarMarco(true);

		puntos = new Point2D.Double[numeroPeces];
		etiquetas = new String[numeroPeces];
		for (int i = 0; i < numeroPeces; i++)
		{
			puntos[i] = new Point2D.Double(Math.random() * 150 - 75, Math
					.random() * 150 - 75);
			etiquetas[i] = "Pez_" + i;
		}
		gs = new GraficoSimbolos(puntos, imagenes, etiquetas);
		lienzo.tomaObjetoGrafico(gs);

		Mano mano = new Mano(eg, lienzo);
		lienzo.anhadeObservadorRaton(mano);

		CursorZoom cursor = new CursorZoom();
		lienzo.anhadeObservadorRaton(cursor);

		CursorHilo cursor2 = new CursorHilo(true, true);
		lienzo.anhadeObservadorRaton(cursor2);
		
	}

	@Override
	protected void ponExtremos(InterfaceEscalaGrafica eg2)
	{
		eg.tomaExtremos(-100.0, -100.0, 100.0, 100.0);
		
	}
}
