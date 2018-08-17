package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.geom.Point2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;

public class GraficoLineasNivel implements IfzObjetoGrafico {

	public void addObservadorRepintado(ObservadorRepintado o) {
	}

	public void dibujate(InterfaceEscalaGrafica escala) {
		Point2D [] puntos = new Point2D.Double[2];
		for (double z=zInicial; z<zFinal; z+=incrementoZ)
		{
			puntos[0] = null;
			for (double x=0.0; x<10.0;x+=0.01)
			{
				if ((z - Math.pow(x, 4.0))>0.0)
				{
					double y=Math.sqrt(z - Math.pow(x, 4.0));
					puntos[1]=new Point2D.Double(x,y);
					if (puntos[0]!=null)
						escala.pintaPoliLinea(puntos, Color.green);
					puntos[0] = puntos[1];
				}
			}
		}
	}

	public boolean necesitasRepintado() {
		return false;
	}

	public void removeObservadorRepintado(ObservadorRepintado o) {
	}

	private double zInicial=0.0, zFinal=40.0, incrementoZ=0.5;
}
