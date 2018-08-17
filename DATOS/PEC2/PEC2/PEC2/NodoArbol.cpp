#include "NodoArbol.hpp"

NodoArbol::NodoArbol(Cliente c, NodoArbol* izq, NodoArbol* der)
{
	cliente = c;
	izquierda = izq;
	derecha = der;
}

NodoArbol::~NodoArbol()
{
}

