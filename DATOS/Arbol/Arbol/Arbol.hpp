#ifndef ARBOL_HPP
#define ARBOL_HPP

#define RANGE_LISTA 500

#include <NodoArbol.hpp>
#include <Trunck.hpp>
#include <stdio.h>

class Arbol
{
	public:
		Arbol(pnodoArbol r = NULL);
		
		void generar(int * lista, int longitud);
		bool buscar (int dato);
		void show_in_order();
		void show_pre_order();
		void show_post_order();
		void draw();
		
		~Arbol();
	private:
		pnodoArbol raiz;
		void insertar (pnodoArbol nodo,int valor, pnodoArbol arbol);
		bool buscar_recursivo (int dato, pnodoArbol arbol);
		void show_in_order_recursivo(pnodoArbol arbol);
		void show_pre_order_recursivo(pnodoArbol arbol);
		void show_post_order_recursivo(pnodoArbol arbol);
		void draw_recursivo(pnodoArbol arbol, string indent, bool is_tail);

};

#endif // ARBOL_HPP
