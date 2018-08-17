/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */


package com.chuidiang.matematicas;

import java.awt.geom.Rectangle2D;


/**
 * M�todos utiles para rect�ngulos.<br>
 */
public class UtilRectangulo
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Agranda un rect�ngulo, manteniendo el centro en su sitio.<br>
    *
    * @param original Rectangulo original.<br>
    * @param porcentaje Porcentaje en % que se quiere agrandar el rect�ngulo.<br>
    *
    * @return El rect�ngulo agrandado.<br>
    */
   public static Rectangle2D agrandar( 
      Rectangle2D original,
      double porcentaje )
   {
      Rectangle2D resultado = new Rectangle2D.Double(  );

      double incrementoX;
      incrementoX =
         ( (double)original.getWidth(  ) * (double)porcentaje ) / 100.0;

      double incrementoY;
      incrementoY =
         ( (double)original.getHeight(  ) * (double)porcentaje ) / 100.0;

      resultado.setRect( 
         original.getMinX(  ) + ( incrementoX / 2.0 ),
         original.getMinY(  ) + ( incrementoY / 2.0 ),
         original.getWidth(  ) - incrementoX,
         original.getHeight(  ) - incrementoY );

      return resultado;
   }

   /**
    * Reduce un rect�ngulo en el porcentaje que se le pasa, manteniendo el
    * centro en su sitio.<br>
    *
    * @param original Rect�ngulo original.<br>
    * @param porcentaje Porcentaje en % de reducci�n.<br>
    *
    * @return El rect�ngulo reducido.<br>
    */
   public static Rectangle2D reducir( 
      Rectangle2D original,
      double porcentaje )
   {
      return agrandar( 
         original,
         -porcentaje );
   }
}
