#ifndef NODOLISTA_HPP
#define NODOLISTA_HPP

#include "Cliente.hpp"

class NodoLista
{
	public:
		NodoLista(Cliente c, NodoLista *sig = NULL, NodoLista *ant = NULL);
		~NodoLista();
	private:
		Cliente cliente;
		NodoLista *siguiente;
		NodoLista *anterior;
		
		friend class Lista;
};

typedef NodoLista* pnodoLista;

#endif // NODOLISTA_HPP
