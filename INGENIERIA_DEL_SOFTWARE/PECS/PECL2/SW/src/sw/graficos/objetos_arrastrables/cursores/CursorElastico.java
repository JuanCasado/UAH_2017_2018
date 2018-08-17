/**
 * Javier Abellï¿½n. 6 Abril 2006
 * 
 * Librerï¿½a grï¿½fica
 */
package com.chuidiang.graficos.objetos_arrastrables.cursores;

import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.objetos_graficos.ObservadorRaton;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;


/**
 * Cursor para hacer zoom. Se hace click y se arrastra el ratï¿½n. Sale un
 * rectï¿½ngulo que va creciendo con el ratï¿½n. Al sotar, se hace zoom.
 */
public class CursorElastico
        extends Cursor
{
   //~ Variables de instancia --------------------------------------------------

   /** Escala sobre la que se va a hacer zoom. */
   protected InterfaceEscalaGrafica escala = null;

   /* Atributo que almacena informacion sobre el estado del proceso de definir un
    * rectangulo con el cursor:pulsar, arrastrar y soltar
    */
   /** */
   protected int estadoProcesoPulsarYArrastar;

   //~ Metodos -----------------------------------------------------------------

   /**
    * Mï¿½todo al que se llama con los eventos de ratï¿½n. Al pulsar el
    * ratï¿½n comienza el proceso. Mientras se arrastra, se va redibujando el
    * rectï¿½ngulo. al sotar se hace el zoom.
    *
    * @param e  .<br>
    * @param tipoEvento  .<br>
    * @param x  .<br>
    * @param y  .<br>
    *
    * @return  .<br>
    */
   public boolean eventoRaton( 
      MouseEvent e,
      int tipoEvento,
      double x,
      double y )
   {
      // Si se arrastra el ratï¿½n, se va rehaciendo el rectï¿½ngulo.
      if( tipoEvento == ObservadorRaton.ARRASTRE )
      {
         //Solo se pinta el rectangulo-cursor al realizar algun tipo de arrastre,
         //de otro modo pulsar y soltar sobre el mismo punto produciría 
         //que se pintase un punto.
         if( this.estadoProcesoPulsarYArrastar == ObservadorRaton.PULSADO )
         {
            this.estadoProcesoPulsarYArrastar = ObservadorRaton.ARRASTRE;
            this.pintar = true;
         }

         if( !pintar )
         {
            return false;
         }

         this.x2 = x;
         this.y2 = y;

         return true;
      }

      // Al pulsar, comienza el proceso y se inicializan las esquinas del
      // rectangulo a la posicion actual del raton.
      if( ( tipoEvento == ObservadorRaton.PULSADO ) &&
         ( e.getButton(  ) == MouseEvent.BUTTON1 ) )
      {
         this.estadoProcesoPulsarYArrastar = ObservadorRaton.PULSADO;
         this.pintar = false;
         this.x1 = x;
         this.y1 = y;
         this.x2 = x;
         this.y2 = y;

         return true;
      }

      return false;
   }

   /**
    * Dibuja el rectangulo segï¿½n se va moviendo el ratï¿½n. Se guarda
    * la escala que se le pasa para poder hacerle zoom
    *
    * @param escala  .<br>
    */
   public void dibujate( InterfaceEscalaGrafica escala )
   {
      this.escala = escala;

      if( !pintar )
      {
         return;
      }

      Point2D[] rectangulo = new Point2D.Double[5];
      rectangulo[0] = new Point2D.Double( 
            x1,
            y1 );
      rectangulo[1] = new Point2D.Double( 
            x1,
            y2 );
      rectangulo[2] = new Point2D.Double( 
            x2,
            y2 );
      rectangulo[3] = new Point2D.Double( 
            x2,
            y1 );
      rectangulo[4] = new Point2D.Double( 
            x1,
            y1 );
      escala.pintaPoliLinea( 
         rectangulo,
         this.getColorFijo(  ) );
   }
}
