#include "Cola.hpp"

Cola::Cola()
{
	primero = NULL;
	ultimo = NULL;
	clientes_en_cola = 0;
}

void Cola::insertar(Cliente c)
{
	pnodoCola nuevo = new NodoCola(c);
	if (ultimo)
		ultimo -> siguiente = nuevo;
	ultimo = nuevo;
	if (!primero)
		primero = nuevo;
	clientes_en_cola ++;
}

bool Cola::es_vacia()
{
	return primero;
}

Cliente Cola::eliminar()
{
	pnodoCola nodo;
	Cliente c;
	nodo = primero;
		
	primero = nodo -> siguiente;
	c = nodo -> cliente;
	delete nodo;
	
	if (!primero)
		ultimo = NULL;
		
	clientes_en_cola --;
		
	return c;
}

void Cola::vaciar()
{
	while (primero)
	{
		eliminar();
	}
}

Cliente Cola::ver_primero()
{
	return primero->cliente;
}

void Cola::mostrar()
{
	pnodoCola aux = primero;
	while(aux)
	{
		aux -> cliente.show_cliente();
		aux = aux -> siguiente;
	}
}

int Cola::cantidad_de_clientes()
{
	return clientes_en_cola;
}

Cola::~Cola()
{
	vaciar();
}
