/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_graficos;


import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Objeto gr�fico que dibuja un array de puntos x,y.<br>
 */
public class GraficoSimbolos
        extends AbstractObjetoGrafico
{
   //~ Variables de instancia --------------------------------------------------

   /** Color para dibujar los puntos */
   private Color color = Color.YELLOW;

   /** Array de puntos a dibujar */
   private Point2D[] puntos = null;

   /**
    * Array con las im�genes que se quieren dibujar
    */
   private Image[] imagenes;

   /**
    * Array con las etiquetas para cada imagen
    */
   private String[] etiquetas;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase GraficoPuntos.<br>
    *
    * @param puntos El array de puntos que debe dibujar.<br>
    */
   public GraficoSimbolos( Point2D[] puntos, Image[]imagenes, String [] etiquetas )
   {
      this.puntos = puntos;
      this.imagenes=imagenes;
      this.etiquetas=etiquetas;
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

      for (int i=0;i<puntos.length;i++)
      {
      escala.pintaImagen( 
         this.puntos[i],
         imagenes[i] );
      escala.pintaTexto(this.puntos[i],etiquetas[i],color);
      }
   }

   /**
    * Se le pasan los puntos que se quieren dibujar.<br>
    *
    * @param puntos Los puntos.<br>
    */
   public void tomaPuntos( Point2D[] puntos, Image [] imagenes , String [] etiquetas)
   {
      this.puntos = puntos;
      this.imagenes= imagenes;
      this.etiquetas=etiquetas;
      this.setNecesitoRepintado( true );
   }
}
