#include "Pila.hpp"

Pila::Pila()
{
	ultimo = NULL;
}

void Pila::insertar(int v)
{
	pnodo nuevo;
	nuevo = new Nodo(v,ultimo);
	ultimo = nuevo;
}

int Pila::extraer()
{
	pnodo nodo;
	int v;
	
	if (!ultimo)
		return 0;
	
	nodo = ultimo;
	ultimo = nodo -> siguiente;
	v = nodo -> valor;
	
	delete nodo;
	return v;
}

void Pila::mostrar()
{
	pnodo aux = ultimo;
	cout << "Pila: ";
	while (aux)
	{
		cout << " > "<< aux->valor;
		aux = aux -> siguiente;
	}
	
	cout << endl;
}

Pila::~Pila()
{
	pnodo aux;
	while (ultimo)
	{
		aux = ultimo;
		ultimo = ultimo -> siguiente;
		delete aux;
	}
}

