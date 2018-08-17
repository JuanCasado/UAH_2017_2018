#ifndef CLIENTE_HPP
#define CLIENTE_HPP

#define HORA_DE_INICIO_NO_REGISTRADOS 120
#define TIEMPO_MAXIMO_DE_COMPRA 10
#define LEN_IDENTIFICADOR 5
#define LEN_DNI 11
#define DNI_FIN 10
#define DNI_LETRA 9
#define DNI_GUION 8

#include "Colores.hpp"
#include <ctime>
#include <math.h>
#include <stdlib.h>
#include <string>

class Cliente
{
	public:
	
		Cliente();
		Cliente(int t,bool r);
		Cliente(int t, bool r, char* dni, int inicio, int duracion, char* i);
		~Cliente();
		
		void show_cliente();
		void show_cliente_completo();
		void show_cliente_completo_formato_reducido();
		void generar_identificador_de_entrada();
		char* get_identificador_de_entrada();
		int get_hora_de_inicio_de_compra();
		int get_tiempo_de_compra();
		char* get_DNI();
		int get_tiempo_de_procesamiento();
		void set_tiempo_de_procesamiento(int hora_de_salida);
		
	private:
		char DNI [LEN_DNI];
		int hora_de_inicio_de_compra;
		int tiempo_de_compra;
		bool registrado;
		char identificador_de_entrada [LEN_IDENTIFICADOR];
		
		//Tiempo de procesamiento
		int tiempo_de_procesamiento;
		void show_tiempo_de_procesamiento();
		
		//Identificador de entrada
		void show_identificador_de_entrada();
		
		//DNI
		void generar_DNI();
		void show_DNI();
		void set_DNI();
		
		//Hora de inicio de compra
		void generar_hora_de_inicio_de_compra();
		void show_hora_de_inicio_de_compra();
		
		//Tiempo de compra
		void generar_tiempo_de_compra();
		void show_tiempo_de_compra();
		
		void show_tipo();
		
		void set_DNI_2(char *);
};

#endif // CLIENTE_HPP
