package sw.applets;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.chuidiang.graficos.EscalaGraficaCartesiana;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.objetos_graficos.ObservadorRaton;

public class PruebaRaton
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame ventana = new JFrame();
        Lienzo lienzo = new Lienzo();
        EscalaGraficaCartesiana escala = new EscalaGraficaCartesiana();
        escala.tomaExtremos(-10.0,-10.0,10.0,10.0);
        lienzo.setSize(300,300);
        ventana.getContentPane().add(lienzo);
        lienzo.tomaEscala(escala);
        ventana.pack();
        ventana.setVisible(true);
        
        lienzo.anhadeObservadorRaton(new ObservadorRaton(){
            public boolean eventoRaton (MouseEvent e, int tipoEvento, double x, double y)
            {
                System.out.println(tipoEvento+":"+x+","+y);
                return false;
            }
        });

    }

}
