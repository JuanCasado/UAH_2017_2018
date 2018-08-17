#ifndef NODO_HPP
#define NODO_HPP

#include "Cliente.hpp"

class NodoCola
{
	public:
		NodoCola(Cliente c, NodoCola *sig = NULL);
		~NodoCola();
	private:
		Cliente cliente;
		NodoCola *siguiente;
		
		friend class Cola;

};

typedef NodoCola* pnodoCola;

#endif // NODO_HPP
