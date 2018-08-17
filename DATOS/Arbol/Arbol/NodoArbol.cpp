#include "NodoArbol.hpp"

NodoArbol::NodoArbol(int v, NodoArbol* der, NodoArbol* izq)
{
	valor = v;
	derecha = der;
	izquierda = izq;
}

NodoArbol::~NodoArbol()
{
}

