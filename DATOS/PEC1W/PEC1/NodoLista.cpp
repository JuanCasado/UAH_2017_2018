#include "NodoLista.hpp"

NodoLista::NodoLista(Cliente c, NodoLista* sig, NodoLista* ant)
{
	cliente = c;
	siguiente = sig;
	anterior = ant;
}

NodoLista::~NodoLista()
{
}

