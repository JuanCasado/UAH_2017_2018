#ifndef GESTOR_HPP
#define GESTOR_HPP

#include "Cola.hpp"
#include "Lista.hpp"
#include "Arbol.hpp"

#define DIFERENCIA_LLEGADA 5
#define VIP 15
#define NO_REGISTRADOS 25
#define SERVIDORES_NO_REGISTRADOS 4

#if WINDOWS
	#define TIEMPO 800
#else
	#define TIEMPO 100000
	#include <unistd.h>
#endif

class Gestor
{
	public:
		Gestor();
		~Gestor();
		void generar_solicitudes_de_clientes_VIP(void);
		void mostrar_clientes_VIP(void);
		void borrar_clientes_VIP(bool texto);
		void generar_solicitudes_de_clientes_no_registrados(void);
		void mostrar_clientes_no_registrados(void);
		void borrar_clientes_no_registrados(bool texto);
		void simular_venta_a_clientes_VIP(void);
		void simular_venta_a_clientes_no_registrados(void);
		void mostrar_entradas_vendidas(void);
		void generar_arbol(void);
		void buscar_cliente(void);
		void mostrar_arbol_en_pre_orden(void);
		void mostrar_arbol_en_post_orden(void);
		void mostrar_arbol_en_in_orden(void);
		void dibujar_arbol(void);
		void reiniciar(void);
		
		int cantidad_no_registrados();
		int recuento_VIP();
		int cantidad_no_registrados_total();
		int recuento_VIP_total();
		int recuento_entradas();
		bool estado_arbol();
		
		void estadisticas();
		void generar();
		
	private:
		int vip;
		int no_registrados;
		Cola VIP_en_espera;
		Cola servidor_VIP;
		
		Cola no_registrado_en_espera;
		Cola servidor_no_registrado [SERVIDORES_NO_REGISTRADOS];
		
		Lista clientes_terminados;
		Arbol *arbol;
		
		void pedir_DNI_validado(char*);
		bool validar_formato(char*);
		bool validar_numeros(char*);
		bool validar_letra(char*);
		void show_hora(int t);
		int recuento_no_registrados();
		void actualizar_pantalla_VIP(int tiempo, int tiempo_en_proceso);
		void actualizar_pantalla_no_registrado(int tiempo, int * tiempo_en_proceso);
		
		Colores C;
};

#endif // GESTOR_HPP
