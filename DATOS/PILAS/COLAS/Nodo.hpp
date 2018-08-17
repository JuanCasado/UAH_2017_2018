#ifndef NODO_HPP
#define NODO_HPP

#include <iostream>
using namespace std;

class Nodo
{
	public:
		Nodo(int v, Nodo *sig = NULL);
		~Nodo();
	private:
		int valor;
		Nodo *siguiente;
		
		friend class Cola;

};

typedef Nodo* pnodo;

#endif // NODO_HPP
