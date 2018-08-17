#ifndef ARBOL_HPP
#define ARBOL_HPP

#include "NodoArbol.hpp"

class Arbol
{
	public:
		Arbol(pnodoArbol r = NULL);
		~Arbol();
		void generar(Cliente * c, int longitud);
		void vaciar();
		bool es_vacio();
		Cliente buscar(char* DNI);
		void show_pre_orden();
		void show_post_orden();
		void show_in_orden();
		void draw();
		long acumulador_tiempo_procesamiento;
		long acumulador_tiempo_estacia;
		void generar_estadisticas();
		long get_tiempo_de_procesamiento();
		long get_tiempo_de_estancia();
		
	private:
		pnodoArbol raiz;
		
		void generar_recursivo(pnodoArbol nuevoNodo, char* nuevoDNI, pnodoArbol arbol);
		void draw_recursivo(pnodoArbol arbol, string indent, bool is_tail);
		
		void eliminar_recursivo_post_orden(pnodoArbol nodo);
		
		Cliente buscar_recursivo(pnodoArbol arbol, char* DNI);
		void show_pre_orden_recursivo(pnodoArbol arbol);
		void show_post_orden_recursivo(pnodoArbol arbol);
		void show_in_orden_recursivo(pnodoArbol arbol);
		
		void generar_estadisticas_recursivo(pnodoArbol arbol);
};

#endif // ARBOL_HPP
