/**
 * Sutruk. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */
package com.chuidiang.graficos.botones;

import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.ObservadorEscalaGrafica;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * .<br>
 */
public class ZoomHistorico
        extends JPanel
        implements ObservadorEscalaGrafica,InterfaceZoom<Integer>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /**  */
   private static final long serialVersionUID = 1L;

   /**  */
   protected static final Integer ANTERIOR = 1;

   /**  */
   protected static final Integer POSTERIOR = 2;

   //~ Variables de instancia --------------------------------------------------

   /**  */
   protected InterfaceEscalaGrafica escalaGrafica;

   /**  */
   protected JButton jButtonZoomAnterior;

   /**  */
   protected JButton jButtonZoomPosterior;

   /**  */
   protected boolean ignorarSiguienteZoom = false;

   /**  */
   protected int cursor;

   /** */
   protected int tamanhoHistorico;

   /** */
/** */
   Stack<double[]> pilaHistoricoExtremos;

   //~ Constructores -----------------------------------------------------------

/**
    * Crea  un nuevo objeto de la clase ZoomHistorico.<br>
    *
    * @param escalaGrafica  .<br>
    * @param tamanhoHistorico  .<br>
    */
   public ZoomHistorico( 
      InterfaceEscalaGrafica escalaGrafica,
      int tamanhoHistorico )
   {
      //Escala Grafica
      this.escalaGrafica = escalaGrafica;
      this.escalaGrafica.anhadeObservador( this );

      //Pila para mantener el historico
      this.pilaHistoricoExtremos = new Stack<double[]>(  );
      this.tamanhoHistorico = tamanhoHistorico;

      //Botones para moverse
      this.jButtonZoomAnterior = new JButton( "<" );
      this.jButtonZoomPosterior = new JButton( ">" );

      this.setBorder( new EmptyBorder( 
            0,
            0,
            0,
            0 ) );
      this.setLayout( new FlowLayout(  ) );
      this.add( this.jButtonZoomAnterior );
      this.add( this.jButtonZoomPosterior );

      this.jButtonZoomAnterior.addActionListener( 
         new ActionListener(  )
         {
            public void actionPerformed( ActionEvent e )
            {
               efectuarZoom( ZoomHistorico.ANTERIOR );
            }
         } );
      this.jButtonZoomPosterior.addActionListener( 
         new ActionListener(  )
         {
            public void actionPerformed( ActionEvent e )
            {
               efectuarZoom( ZoomHistorico.POSTERIOR );
            }
         } );
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * .<br>
    *
    * @param xMin  .<br>
 *
    * @param yMin  .<br>
 *
    * @param xMax  .<br>
 *
    * @param yMax  .<br>
 *
    */
   public void tomaNuevosExtremos( 
      double xMin,
      double yMin,
      double xMax,
      double yMax )
   {
      if( this.ignorarSiguienteZoom )
      {
         this.ignorarSiguienteZoom = false;

         return;
      }

      if( this.pilaHistoricoExtremos.size(  ) == this.tamanhoHistorico )
      {
         this.pilaHistoricoExtremos.remove( 0 );
      }

      this.pilaHistoricoExtremos.push( new double[]
         {
            xMin,
            yMin,
            xMax,
            yMax
         } );

      this.cursor = this.pilaHistoricoExtremos.size(  ) - 1;
   }

   /**
    * .<br>
    *
    * @param sentidoZoom  .<br>
 *
    */
   public void efectuarZoom( Integer sentidoZoom )
   {
      double[] extremos = null;

      if( sentidoZoom.equals( ZoomHistorico.ANTERIOR ) )
      {
         if( cursor > 0 )
         {
            extremos = this.pilaHistoricoExtremos.get( --cursor );
         }
         else
         {
            return;
         }
      }
      else if( sentidoZoom.equals( ZoomHistorico.POSTERIOR ) )
      {
         if( this.cursor < ( this.pilaHistoricoExtremos.size(  ) - 1 ) )
         {
            extremos = this.pilaHistoricoExtremos.get( ++cursor );
         }
         else
         {
            return;
         }
      }

      this.ignorarSiguienteZoom = true;
      this.escalaGrafica.tomaExtremos( 
         extremos[0],
         extremos[1],
         extremos[2],
         extremos[3] );
   }
}
