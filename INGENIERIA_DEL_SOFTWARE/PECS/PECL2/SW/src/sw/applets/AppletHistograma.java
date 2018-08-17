/*
 * Javier Abell�n. 1 de Mayo de 2004
 *
 * Lienzo.java
 */
package sw.applets;

import java.awt.Color;

import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorHilo;
import com.chuidiang.graficos.objetos_graficos.Histograma;
/**
 * Ejemplo de un Histograma sobre un Lienzo.
 */
public class AppletHistograma extends AbstractAppletPruebaLienzo
{
    /**
	 * serial uid
	 */
	private static final long serialVersionUID = 3257286920219472948L;

	@Override
	protected boolean quieresBotoneria()
	{
		return true;
	}

    public static void main (String [] args)
    {
        AppletHistograma applet = new AppletHistograma();
        applet.visualizaFrame();
    }
	@Override
	protected void ponObjetosGraficos(Lienzo lienzo2)
	{
        // El histograma
        double [] valores = {1.0, 2.0, 3.0, 2.0, 1.0};
        Color [] colores = {Color.RED, Color.GREEN, Color.CYAN};
        Histograma histograma = new Histograma (valores, colores);
        lienzo.tomaObjetoGrafico(histograma);
        
        // Y finalmente un cursor vertical
        CursorHilo cursor = new CursorHilo(true,false);
        lienzo.anhadeObservadorRaton(cursor);

	}

	@Override
	protected void ponExtremos(InterfaceEscalaGrafica eg2)
	{
        eg2.tomaExtremos (-100.0, -0.1, 100.0, 4.0);
	}

	@Override
	protected void ponBotonesAdicionales(BotoneriaZoom botoneria)
	{
	}
    
}
