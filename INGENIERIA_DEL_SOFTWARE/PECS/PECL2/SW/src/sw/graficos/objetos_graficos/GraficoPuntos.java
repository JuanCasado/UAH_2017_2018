/**
 * Javier Abellan. 6 Abril 2006
 * 
 * Libreria grafica
 */


package com.chuidiang.graficos.objetos_graficos;


import java.awt.Color;
import java.awt.geom.Point2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Objeto grafico que dibuja un array de puntos x,y.<br>
 */
public class GraficoPuntos
        extends AbstractObjetoGrafico
{
   //~ Variables de instancia --------------------------------------------------

   /** Color para dibujar los puntos */
   private Color color = Color.YELLOW;

   /** Array de puntos a dibujar */
   private Point2D[] puntos = null;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase GraficoPuntos.<br>
    *
    * @param puntos El array de puntos que debe dibujar.<br>
    */
   public GraficoPuntos( Point2D[] puntos )
   {
      this.puntos = puntos;
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Se le pasa el color del que se quieren los puntos.<br>
    *
    * @param color Color.<br>
    */
   public void setColor( Color color )
   {
      this.color = color;
      this.setNecesitoRepintado( true );
   }

   /**
    * Devuelve el color en el que se est�n dibujando los puntos.<br>
    *
    * @return Color.<br>
    */
   public Color getColor(  )
   {
      return color;
   }

   /**
    * Dibuja los puntos en la escala grafica que se le pasa.<br>
    *
    * @param escala La escala grafica de dibujo.<br>
    */
   public void dibujate( InterfaceEscalaGrafica escala )
   {
      if( this.puntos == null )
      {
         return;
      }

      escala.pintaPuntos( 
         this.puntos,
         color );
   }

   /**
    * Se le pasan los puntos que se quieren dibujar.<br>
    *
    * @param puntos Los puntos.<br>
    */
   public void tomaPuntos( Point2D[] puntos )
   {
      this.puntos = puntos;
      this.setNecesitoRepintado( true );
   }
}
