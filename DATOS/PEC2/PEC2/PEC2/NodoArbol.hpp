#ifndef NODOARBOL_HPP
#define NODOARBOL_HPP

#include "Cliente.hpp"

class NodoArbol
{
	public:
		NodoArbol(Cliente c, NodoArbol* izq = NULL, NodoArbol* der = NULL);
		~NodoArbol();
		
	private:
		Cliente cliente;
		NodoArbol *izquierda;
		NodoArbol *derecha;
		
		friend class Arbol;

};

typedef NodoArbol* pnodoArbol;

#endif // NODOARBOL_HPP
