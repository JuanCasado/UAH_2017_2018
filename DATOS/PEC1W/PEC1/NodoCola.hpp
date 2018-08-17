#ifndef NODO_HPP
#define NODO_HPP

#include <iostream>
#include "Cliente.hpp"
using namespace std;

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