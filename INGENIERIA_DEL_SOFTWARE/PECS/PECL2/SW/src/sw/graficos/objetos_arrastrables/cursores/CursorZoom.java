/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */
package com.chuidiang.graficos.objetos_arrastrables.cursores;

import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.objetos_graficos.ObservadorRaton;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


/**
 * Cursor para hacer zoom. Se hace click y se arrastra el rat�n. Sale un
 * rect�ngulo que va creciendo con el rat�n. Al sotar, se hace zoom.
 */
public class CursorZoom
        extends CursorElastico
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * M�todo al que se llama con los eventos de rat�n. Al pulsar el
    * rat�n comienza el proceso. Mientras se arrastra, se va redibujando el
    * rect�ngulo. al sotar se hace el zoom.
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
      boolean tratadosEventosComunes = super.eventoRaton( 
            e,
            tipoEvento,
            x,
            y );

      if( tratadosEventosComunes )
      {
         return true;
      }

      // Al soltar, se hace el zoom.
      if( ( tipoEvento == ObservadorRaton.SOLTADO ) &&
         ( e.getButton(  ) == MouseEvent.BUTTON1 ) )
      {
         if( this.escala != null )
         {
            double xMin = Math.min( 
                  x1,
                  x2 );
            double xMax = Math.max( 
                  x1,
                  x2 );
            double yMin = Math.min( 
                  y1,
                  y2 );
            double yMax = Math.max( 
                  y1,
                  y2 );

            // Antes de hacer el zoom, se comprueba que el rectangulo no
            // es demasiado peque�o
            Rectangle2D extremos = escala.getExtremosCartesianos(  );

            if( ( ( xMax - xMin ) > ( extremos.getWidth(  ) / 20.0 ) ) &&
               ( ( yMax - yMin ) > ( extremos.getHeight(  ) / 20.0 ) ) )
            {
               escala.tomaExtremos( 
                  xMin,
                  yMin,
                  xMax,
                  yMax );
            }
         }

         pintar = false;
      }

      return false;
   }
}
