/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package sw.applets;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.chuidiang.graficos.EscalaGraficaPolar;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorHilo;
import com.chuidiang.graficos.objetos_graficos.GraficoSimbolos;
import com.chuidiang.graficos.objetos_graficos.Mano;
import com.chuidiang.graficos.objetos_graficos.RejillaCirculoPolar;



public class PruebaLienzoPolaresSimbolos
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame v = new JFrame();
        final Lienzo lienzo = new Lienzo();
        lienzo.setPreferredSize(new Dimension(500,500));
        EscalaGraficaPolar eg = new EscalaGraficaPolar();
        eg.tomaExtremos(-100.0,-100.0,100.0,100.0);
        lienzo.tomaEscala(eg);
        lienzo.setBackground(Color.BLACK);
        lienzo.setMargenes(50,30,40,22);
        lienzo.setPintarMarco(true);

        int numeroPuntos=5;
        Point2D.Double []puntos = new Point2D.Double[numeroPuntos];
        String [] etiquetas = new String[numeroPuntos];
        for (int i=0;i<numeroPuntos;i++)
        {
        	puntos[i] = new Point2D.Double(i*3.0+20,i*Math.PI/2.5);
        	etiquetas[i]="Pez_"+i;
        }
        Image [] imagenes = new Image[numeroPuntos];
        imagenes[0] = new ImageIcon("D:/Users/Javier/JAVA/GRAFICOS/imagenes/amberjack_1c.gif").getImage();
        imagenes[1] = new ImageIcon("D:/Users/Javier/JAVA/GRAFICOS/imagenes/bannerfish_1c.gif").getImage();
        imagenes[2] = new ImageIcon("D:/Users/Javier/JAVA/GRAFICOS/imagenes/bass_yellow_1c.gif").getImage();
        imagenes[3] = new ImageIcon("D:/Users/Javier/JAVA/GRAFICOS/imagenes/butterflyfish2_2c.gif").getImage();
        imagenes[4] = new ImageIcon("D:/Users/Javier/JAVA/GRAFICOS/imagenes/clownfish_2c.gif").getImage();
        for (int j=5;j<numeroPuntos;j++)
        	imagenes[j]=imagenes[j%5];
        GraficoSimbolos gs = new GraficoSimbolos(puntos,imagenes,etiquetas);
        lienzo.tomaObjetoGrafico(gs);
        
        
        
        Mano mano = new Mano(eg,lienzo);
        lienzo.anhadeObservadorRaton(mano);
        
        CursorHilo cursor = new CursorHilo(true,true);
        // lienzo.tomaObjetoGrafico(cursor);
        lienzo.anhadeObservadorRaton(cursor);
        

        /*
        RejillaFija2 rej = new RejillaFija2(
        		new double[]{0.0,10.0,20.0,30.0,40.0,50.0},
        		new double[]{0,Math.PI/4, Math.PI/2,3*Math.PI/4,Math.PI,
        				5*Math.PI/4, 3*Math.PI/2+7*Math.PI/4});
        				*/
        RejillaCirculoPolar rej=new RejillaCirculoPolar(50.0,12*3,Color.GRAY);
        rej.setDivisionConTexto(3);
        rej.setLongitudDivisionConTexto(5);
        rej.setLongitudDivisionSinTexto(5);
        lienzo.tomaObjetoGrafico(rej);
        
        RejillaCirculoPolar rej2=new RejillaCirculoPolar(35.0,4,Color.GRAY);
        rej2.setPonerTexto(false);
        rej2.setLongitudDivisionConTexto(2);
        rej2.setLongitudDivisionSinTexto(2);
        lienzo.tomaObjetoGrafico(rej2);

        RejillaCirculoPolar rej3=new RejillaCirculoPolar(15.0,4,Color.GRAY);
        rej3.setPonerTexto(false);
        rej3.setLongitudDivisionConTexto(2);
        rej3.setLongitudDivisionSinTexto(2);
        lienzo.tomaObjetoGrafico(rej3);
        
        v.getContentPane().add(lienzo);
        
        BotoneriaZoom botones = new BotoneriaZoom(eg);
        v.getContentPane().add(botones,BorderLayout.SOUTH);
        
        v.pack();
        // v.setSize(500,500);
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try
        {
            while (true)
            {
                for (int i=0;i<numeroPuntos;i++)
                {
                	Point2D aux = puntos[i];
                    puntos[i]=new Point2D.Double(
                    		aux.getX()+Math.random()*1.0-.5,
                    		aux.getY()+Math.random()*0.05-0.025);
                }
                gs.tomaPuntos(puntos,imagenes,etiquetas);
                Thread.sleep(200);
            }
        }
        catch (Exception e)
        {
            
        }
        
    }

    public PruebaLienzoPolaresSimbolos()
    {
        
    }
}

