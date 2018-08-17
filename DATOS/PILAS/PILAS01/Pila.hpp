#ifndef PILA_HPP
#define PILA_HPP
#include "Nodo.hpp"

class Pila
{
	public:
		Pila();
		~Pila();
		void insertar (int v);
		int extraer();
		void mostrar();
	private:
		pnodo ultimo;

};

#endif // PILA_HPP
