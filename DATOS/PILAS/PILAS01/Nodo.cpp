#include "Nodo.hpp"

Nodo::Nodo(int v, Nodo* sig)
{
	valor = v;
	siguiente = sig;
}

Nodo::~Nodo()
{
}

