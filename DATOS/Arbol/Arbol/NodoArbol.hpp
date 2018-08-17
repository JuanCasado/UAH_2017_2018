#ifndef NODOARBOL_HPP
#define NODOARBOL_HPP

#include <iostream>



class NodoArbol
{
	public:
		NodoArbol(int v, NodoArbol* der = NULL, NodoArbol* izq = NULL);
		~NodoArbol();

	private:
		int valor;
		NodoArbol* derecha;
		NodoArbol* izquierda;
		
	friend class Arbol;
};

typedef NodoArbol* pnodoArbol;

#endif // NODOARBOL_HPP
