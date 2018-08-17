/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */


package com.chuidiang.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLayeredPane;

import com.chuidiang.graficos.objetos_graficos.IfzObjetoGrafico;
import com.chuidiang.graficos.objetos_graficos.ObservadorRaton;



/**
 * Lienzo de dibujo.<br>
 * Es un JLayeredPane con dos capas, una para el dibujo y otra para cursores,
 * que est� encima y es la que captura eventos de rat�n
 */
public class Lienzo
        extends JLayeredPane
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** serial uid */
   private static final long serialVersionUID = 6802228160205858450L;

   //~ Variables de instancia --------------------------------------------------

   /** capa del cursor */
   private CapaCursor capaCursor = new CapaCursor(  );

   /** capa para dibujo */
   private CapaDibujo capaDibujo = new CapaDibujo(  );

   /** color para el marco del borde */
   private Color colorMarco = Color.WHITE;

   /** si se quiere o no pintar marco */
   private boolean pintarMarco;

   /** Margen derecho en pixels */
   private int margenDerecho = 0;

   /** Margen inferior en pixels */
   private int margenInferior = 0;

   /** Margen izquierdo en pixels */
   private int margenIzquierdo = 0;

   /** Margen superior en pixels */
   private int margenSuperior = 0;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase Lienzo.<br>
    */
   public Lienzo(  )
   {
      this.addComponentListener( 
         new ComponentListener(  )
         {
            public void componentHidden( ComponentEvent e )
            {
               // TODO Auto-generated method stub
            }

            public void componentShown( ComponentEvent e )
            {
               // TODO Auto-generated method stub
            }

            public void componentMoved( ComponentEvent e )
            {
               // TODO Auto-generated method stub
            }

            public void componentResized( ComponentEvent e )
            {
               capaDibujo.setBounds( getAreaEfectivaDibujo(  ) );
               capaCursor.setBounds( getAreaEfectivaDibujo(  ) );
            }
         } );

      this.add( 
         capaDibujo,
         new Integer( 1 ) );
      this.add( 
         capaCursor,
         new Integer( 2 ) );
      capaDibujo.setBounds( getAreaEfectivaDibujo(  ) );
      capaCursor.setBounds( getAreaEfectivaDibujo(  ) );
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Pasa el background a la capa de dibujo.<br>
    *
    * @param background Color de background.<br>
    */
   public void setBackground( Color background )
   {
      this.setOpaque( true );
      super.setBackground( background );
      capaDibujo.setBackground( background );
   }

   /**
    * color para el marco.<br>
    *
    * @param colorMarco color.<br>
    */
   public void setColorMarco( Color colorMarco )
   {
      this.colorMarco = colorMarco;
   }

   /**
    * Fija los m�rgenes en pixels para el gr�fico
    *
    * @param margenIzquierdo margen izquierdo
    * @param margenDerecho margen derecho
    * @param margenSuperior margen superior
    * @param margenInferior margen inferior
    */
   public void setMargenes( 
      int margenIzquierdo,
      int margenDerecho,
      int margenSuperior,
      int margenInferior )
   {
      this.margenIzquierdo = margenIzquierdo;
      this.margenDerecho = margenDerecho;
      this.margenSuperior = margenSuperior;
      this.margenInferior = margenInferior;
   }

   /**
    * Si se queire o no pintar el marco.<br>
    *
    * @param pintarMarco true para que se pinte el marco.<br>
    */
   public void setPintarMarco( boolean pintarMarco )
   {
      this.pintarMarco = pintarMarco;
   }

   /**
    * Pasa el observador de eventos de raton a la capa de cursores.<br>
    *
    * @param observador Un observador de eventos de raton.<br>
    */
   public void anhadeObservadorRaton( ObservadorRaton observador )
   {
      capaCursor.anhadeObservadorRaton( observador );
   }

   /**
    * LLama al m�todo de la clase padre y dibuja el marco si es necesario.<br>
    *
    * @param g Graphics.<br>
    */
   public void paint( Graphics g )
   {
      super.paint( g );

      if( pintarMarco )
      {
         g.setColor( colorMarco );

         Rectangle area = getAreaEfectivaDibujo(  );
         g.drawRect( 
            area.x - 1,
            area.y - 1,
            area.width + 1,
            area.height + 1 );
      }
   }

   /**
    * Pasa la escala de dibujo a las capas de raton y de cursor.<br>
    *
    * @param escala La escala de dibujo.<br>
    */
   public void tomaEscala( InterfaceEscalaGrafica escala )
   {
      capaDibujo.tomaEscala( escala );
      capaCursor.setEscala( escala );
   }

   /**
    * A�ade el ObjetoGrafico que se le pasa a la lista de Objetos gr�ficos que
    * se pintar�n sobre el Lienzo.
    *
    * @param unObjeto El objeto grafico.<br>
    */
   public void tomaObjetoGrafico( IfzObjetoGrafico unObjeto )
   {
      capaDibujo.tomaObjetoGrafico( unObjeto );
   }

   /**
    * Obtiene el area efectiva de dibujo, descontando  los marcos.<br>
    *
    * @return El area de dibujo en pixels.<br>
    */
   private Rectangle getAreaEfectivaDibujo(  )
   {
      Rectangle area = new Rectangle(  );
      area.x = ( ( margenIzquierdo < getWidth(  ) ) ? margenIzquierdo : 0 );
      area.y = ( ( margenSuperior < getHeight(  ) ) ? margenSuperior : 0 );
      area.width =
         ( ( getWidth(  ) - area.x - margenDerecho ) > 0 )
         ? ( getWidth(  ) - area.x - margenDerecho ) : ( getWidth(  ) - area.x );
      area.height =
         ( ( getHeight(  ) - area.y - margenInferior ) > 0 )
         ? ( getHeight(  ) - area.y - margenInferior ) : ( getHeight(  ) -
         area.y );

      return area;
   }
}
