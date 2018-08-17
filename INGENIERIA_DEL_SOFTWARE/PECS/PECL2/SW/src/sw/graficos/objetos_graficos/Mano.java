/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */


package com.chuidiang.graficos.objetos_graficos;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;



/**
 * Pulsando el tercer bot�n del rat�n y arrastrando, se puede mover el gr�fico.
 */
public class Mano
        implements ObservadorRaton
{
   //~ Variables de instancia --------------------------------------------------

   /** Lienzo de dibujo */
   private Component lienzo;

   /** Escala gr�fica sobre la que se va a arrastrar el gr�fico. */
   private InterfaceEscalaGrafica escala;

   /** Ultima posicion x,y del rat�n, en coordenadas de usuario. */
   private double x;

   /** Ultima posicion x,y del rat�n, en coordenadas de usuario. */
   private double y;

   //~ Constructores -----------------------------------------------------------

   /**
    * Construye una intancia de Mano.
    *
    * @param escala La escala sobre la que se debe arrastrar.
    * @param lienzo Lienzo de dibujo.<br>
    */
   public Mano( 
      InterfaceEscalaGrafica escala,
      Component lienzo )
   {
      this.escala = escala;
      this.lienzo = lienzo;
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * M�todo al que se llama cuando se producen eventos de rat�n. Modifica los
    * extremos de la escala gr�fica seg�n se mueve el rat�n con el tercer
    * bot�n pulsado.
    *
    * @param e Evento del rat�n.<br>
    * @param tipoEvento Tipo de evento del raton seg�n ObservadorRaton.<br>
    * @param x Valor x del evento de raton en coordenadas usuario.<br>
    * @param y Valor y del evento de raton en coordenadas usuario.<br>
    *
    * @return true si se ha producido arraste del rat�n, para que se redibuje
    * el gr�fico.<br>
    */
   public boolean eventoRaton( 
      MouseEvent e,
      int tipoEvento,
      double x,
      double y )
   {
      // Si se pulsa el bot�n 3, se inicia todo y se pone un cursor de mano
      if( ( tipoEvento == ObservadorRaton.PULSADO ) &&
         ( e.getButton(  ) == MouseEvent.BUTTON3 ) )
      {
         this.x = e.getX(  );
         this.y = e.getY(  );
         this.lienzo.setCursor( new java.awt.Cursor( Cursor.HAND_CURSOR ) );

         return false;
      }

      // Mientras se arrastra, se mueve el gr�fico.
      if( ( tipoEvento == ObservadorRaton.ARRASTRE ) &&
         ( ( e.getModifiersEx(  ) & InputEvent.BUTTON3_DOWN_MASK ) == InputEvent.BUTTON3_DOWN_MASK ) )
      {
         Point2D origen =
            this.escala.getCoordenadaUsuario( 
               new Point2D.Double( 
                  this.x,
                  this.y ) );
         origen=this.escala.getCoordenadasCartesianas(origen);
         Point2D destino =
            this.escala.getCoordenadaUsuario( 
               new Point2D.Double( 
                  e.getX(  ),
                  e.getY(  ) ) );
		 destino = this.escala.getCoordenadasCartesianas(destino);
         
         Rectangle2D extremos = escala.getExtremosCartesianos(  );

         escala.tomaExtremos( 
            extremos.getMinX(  ) - ( destino.getX() - origen.getX() ),
            extremos.getMinY(  ) - ( destino.getY() - origen.getY() ),
            ( extremos.getMinX(  ) + extremos.getWidth(  ) ) - ( destino.getX() - origen.getX() ),
            ( extremos.getMinY(  ) + extremos.getHeight(  ) ) - ( destino.getY() - origen.getY() ) );
         
         this.x = e.getX(  );
         this.y = e.getY(  );

         return true;
      }

      // Al soltar se quita el cursor de mano.
      if( ( tipoEvento == ObservadorRaton.SOLTADO ) &&
         ( e.getButton(  ) == MouseEvent.BUTTON3 ) )
      {
         this.lienzo.setCursor( new java.awt.Cursor( Cursor.DEFAULT_CURSOR ) );

         return false;
      }

      return false;
   }
}
