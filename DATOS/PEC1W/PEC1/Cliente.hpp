#ifndef CLIENTE_HPP
#define CLIENTE_HPP

#define HORA_DE_INICIO_NO_REGISTRADOS 120
#define TIEMPO_MAXIMO_DE_COMPRA 10

#include <iostream>
#include <ctime>
#include <math.h>
#include <stdlib.h>
#include <time.h>
#include "windows.h"

using namespace std;

class Cliente
{
	public:
	
		Cliente();
		Cliente(int t,bool r);
		~Cliente();
		
		void show_cliente();
		void show_cliente_completo();
		void generar_identificador_de_entrada();
		long get_identificador_de_entrada();
		int get_hora_de_inicio_de_compra();
		int get_tiempo_de_compra();
		
    private:
    
		char DNI [9];
		int hora_de_inicio_de_compra;
		int tiempo_de_compra;
		bool registrado;
		char identificador_de_entrada [4];
		
		//Identificador de entrada
		long numero_de_entrada;
		long transformar_identificador_de_entrada();
		void show_identificador_de_entrada();
		
		//DNI
		void generar_DNI();
		void show_DNI();
		
		//Hora de inicio de compra
		void generar_hora_de_inicio_de_compra();
		void show_hora_de_inicio_de_compra();
		
		//Tiempo de compra
		void generar_tiempo_de_compra();
		void show_tiempo_de_compra();
};

#endif // CLIENTE_HPP