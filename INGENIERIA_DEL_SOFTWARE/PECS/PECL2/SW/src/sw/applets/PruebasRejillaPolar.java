/*==============================================================================
*
* $RCSfile: cvsmkhd,v $
*
* $Revision: 1.2 $ $Date: 2003/09/22 07:05:39 $
*
* $State: Exp $
*
* AUTOR: sutruk
*
==============================================================================*/
package sw.applets;

import com.chuidiang.graficos.EscalaGraficaPolar;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.botones.VisorRaton;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorHilo;
import com.chuidiang.graficos.objetos_graficos.Mano;
import com.chuidiang.graficos.objetos_graficos.RejillaCirculoPolar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;


/**
 * .<br>
 */
public class PruebasRejillaPolar
        extends JFrame
{
   //~ Constructores -----------------------------------------------------------

/**
    * Main para probar el cursor elastico y el cursor de zoom
    *
    * @param args
    */
   public PruebasRejillaPolar(  )
   {
      //Contenedor del lienzo
      JFrame jframe = new JFrame(  );

      jframe.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

      jframe.setSize( new Dimension( 
            400,
            400 ) );

      //Lienzo
      Lienzo lienzo = new Lienzo(  );
      lienzo.setBackground( Color.BLACK );

      //Escala grafica
      EscalaGraficaPolar escalaGraficaPolar = new EscalaGraficaPolar(  );

      escalaGraficaPolar.tomaExtremos( 
         -100,
         -100,
         100,
         100 );

      lienzo.tomaEscala( escalaGraficaPolar );

      //Objetos graficos de interes
      CursorHilo cursorHilo = new CursorHilo( true,true );
      cursorHilo.setColorFijo( Color.GREEN );
      cursorHilo.setColorMovil( Color.WHITE );
      lienzo.anhadeObservadorRaton( cursorHilo );

//      RejillaPolar rejillaPolar = new RejillaPolar( 2,10,Color.YELLOW );
      RejillaCirculoPolar rejillaPolar =
         new RejillaCirculoPolar( 10,6,Color.BLUE );
      lienzo.tomaObjetoGrafico( rejillaPolar );

      Mano mano = new Mano( escalaGraficaPolar,lienzo );
      lienzo.anhadeObservadorRaton( mano );

      // Una botoneria de zoom
      BotoneriaZoom zoom = new BotoneriaZoom( escalaGraficaPolar );

      VisorRaton visorX = new VisorRaton( VisorRaton.VISOR_X );
      visorX.setColumns( 20 );

      VisorRaton visorY = new VisorRaton( VisorRaton.VISOR_Y );
      visorY.setColumns( 20 );
      zoom.add( visorX );
      zoom.add( visorY );

      // Lo metemos todo en el applet
      jframe.getContentPane(  ).setLayout( new BorderLayout(  ) );
      jframe.getContentPane(  ).add( 
         lienzo,
         BorderLayout.CENTER );
      jframe.getContentPane(  ).add( 
         zoom,
         BorderLayout.SOUTH );
      jframe.setVisible( true );
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * .<br>
    *
    * @param argumentos  .<br>
 *
    */
   public static void main( String[] argumentos )
   {
      new PruebasRejillaPolar(  );
   }
}
