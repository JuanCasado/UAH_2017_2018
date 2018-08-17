/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.chuidiang.graficos.EscalaGraficaCartesiana;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorHilo;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorZoom;
import com.chuidiang.graficos.objetos_graficos.GraficoFuncionPorPuntos;
import com.chuidiang.graficos.objetos_graficos.Mano;
import com.chuidiang.graficos.objetos_graficos.RejillaFija;
import sw.applets.BotoneriaZoom;

public class SW extends JApplet
{
    /**
    * Método init() 
    del applet. Inicializa el lienzo con todos sus objetos
    * gráficos y lo mete a él y a la botonería de zoom en el Applet.
    */
    @Override
    public void init()
    {
        System.out.println("INICIO INIT");
        inicializaLienzo();
        botones = new BotoneriaZoom(eg);
        this.getContentPane().add(lienzo, BorderLayout.CENTER);
        this.getContentPane().add(botones, BorderLayout.SOUTH);
        muevePuntos();
        System.out.println("FIN INIT");
    }
    /** Lienzo de dibujo */
    private Lienzo lienzo;
    /** Escala de coordenadas de usuario para el lienzo */
    private EscalaGraficaCartesiana eg;
    /** Puntos que se van a dibujar en el gráfico */
    private double[] puntos;
    /** Gráfico de puntos */
    private GraficoFuncionPorPuntos gp;
    /** Panel con los botones de zoom más y menos */
    private BotoneriaZoom botones;
    /**
    * Crea el lienzo y mete en él el gráfico de puntos correctamente
    * inicializado, la mano para arrastrar el gráfico, una rejilla, etc.
    */
    private void inicializaLienzo()
    {
        System.out.println("INICIO INICIALIZA-LIENZO");
        lienzo = new Lienzo();
        lienzo.setBackground(Color.black);
        eg = new EscalaGraficaCartesiana();
        eg.tomaExtremos(-100.0,-100.0, 100.0, 100.0);
        puntos = new double[100];
        for (int i = 0; i < 100; i++)
            puntos[i] = Math.random() * 200 -100;
        gp = new GraficoFuncionPorPuntos(-100.0, 100.0, puntos);
        lienzo.tomaEscala(eg);
        lienzo.tomaObjetoGrafico(gp);
        Mano mano = new Mano(eg, lienzo);
        lienzo.anhadeObservadorRaton(mano);
        CursorZoom cursor = new CursorZoom();
        lienzo.anhadeObservadorRaton(cursor);
        CursorHilo cursor2 = new CursorHilo(true, false);
        lienzo.anhadeObservadorRaton(cursor2);
        RejillaFija rej = new RejillaFija(10, 10);
        lienzo.tomaObjetoGrafico(rej);
        System.out.println("FIN INICIALIZA-LIENZO");
    }

    public static void main(String[] args)
    {
        System.out.println("INICIO MAIN");
        SW applet = new SW();
        applet.visuliazaFrame();
        applet.muevePuntos();
        System.out.println("FIN MAIN");
    }
    /**
    * Constructor de la clase. Inicializa el linezo y lo mete en una ventana
    * que muestra.
    */
    public SW()
    {
        System.out.println("INICIO CONSTRUCTOR");
        System.out.println("FIN CONSTRUCTOR");
    }
    /** Visualiza un Frame con el gráfico dentro y la botonería de zoom */
    public void visuliazaFrame()
    {
        System.out.println("INICIO VISUALIZA-FRAME");
        inicializaLienzo();
        JFrame v = new JFrame();
        v.getContentPane().add(lienzo);
        botones = new BotoneriaZoom(eg);
        v.getContentPane().add(botones, BorderLayout.SOUTH);
        v.pack();
        v.setSize(500, 500);
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println("FIN VISUALIZA-FRAME");
    }
    /** Hace que los puntos del gráfico cambien una vez por segundo */
    public void muevePuntos()
    {
        System.out.println("INICIO MUEVE-PUNTOS");
        Thread hilo = new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("INICIO RUN");
                try
                {
                    while (true)
                    {
                        System.out.println("RUNNING...");
                        for (int i = 0; i < 100; i++)
                        {
                            puntos[i] = Math.random() * 200 -100;
                        }
                        gp.setPuntos(puntos);
                        Thread.sleep(1000);
                    }
                } catch (Exception e){}
                System.out.println("FIN RUN");
            }
        };
        hilo.start();
        System.out.println("FIN MUEVE-PUNTOS");
        }
}
