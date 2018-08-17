#include "Lista.hpp"

Lista::Lista()
{
	primero = NULL;
	puntero = NULL;
	clientes_en_lista = 0;
}

void Lista::insertar(Cliente c)
{
	pnodoLista nuevo = new NodoLista(c);
	char* identificador_cliente = c.get_identificador_de_entrada();
	puntero = primero;
	
	if (!primero)
		primero = nuevo;
	else
	{
		while(puntero->siguiente and (strcmp(puntero->siguiente->cliente.get_identificador_de_entrada(), identificador_cliente) < 0))
			puntero = puntero->siguiente;
		
		if(!puntero->siguiente)
			if(strcmp(puntero->cliente.get_identificador_de_entrada(), identificador_cliente) > 0)
				insertar_detras(nuevo);
			else
				insertar_primero(nuevo);
				
		else 
			if(strcmp(puntero->cliente.get_identificador_de_entrada(), identificador_cliente) > 0)
				insertar_detras(nuevo);
			else
				insertar_delante(nuevo);
	}
	
	clientes_en_lista ++;
}

void Lista::insertar_delante(pnodoLista n)
{
	pnodoLista aux;
	
	aux = puntero->siguiente;
	puntero->siguiente = n;
	n->siguiente = aux;
	n->anterior = puntero;
}

void Lista::insertar_detras(pnodoLista n)
{
	pnodoLista aux;
	
	aux = puntero->anterior;
	puntero->anterior = n;
	n->anterior = aux;
	n->siguiente = puntero;
	primero = n;
}

void Lista::insertar_primero(pnodoLista n)
{
	puntero->siguiente = n;
	n->anterior = puntero;
}

Cliente Lista::eliminar()
{
	pnodoLista nodo;
	Cliente c;
	nodo = primero;
		
	primero = nodo -> siguiente;
	c = nodo -> cliente;
	delete nodo;
	
	clientes_en_lista --;
		
	return c;
}

void Lista::vaciar()
{
	while (primero)
	{
		eliminar();
	}
}

bool Lista::es_vacia()
{
	return primero;
}

void Lista::mostrar()
{
	pnodoLista aux = primero;
	while(aux)
	{
		aux -> cliente.show_cliente_completo();
		aux = aux -> siguiente;
	}
}

int Lista::cantidad_de_clientes()
{
	return clientes_en_lista;
}

void Lista::duplicar(Cliente* c, int longitud)
{
	puntero = primero;
	
	for (int x = 0; x < longitud; x++)
	{
		c[x] = puntero->cliente;
		puntero = puntero->siguiente;
	}
}

Lista::~Lista()
{
	vaciar();
}

