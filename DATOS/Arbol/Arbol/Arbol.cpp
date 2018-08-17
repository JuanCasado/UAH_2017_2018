#include "Arbol.hpp"

Arbol::Arbol(pnodoArbol r)
{
	raiz = r;
}

void Arbol::generar(int* lista, int longitud)
{
	pnodoArbol nuevoNodo = NULL;
	int nuevoInt;
	
	for (int x = 0; x < longitud; x++)
	{
		nuevoInt = lista[x];
		nuevoNodo = new NodoArbol (nuevoInt);
		
		insertar(nuevoNodo,nuevoInt,raiz);
	}
}

void Arbol::insertar(pnodoArbol nodo, int valor, pnodoArbol arbol)
{
	if (valor <= arbol->valor)
		if (arbol->izquierda)
			insertar(nodo,valor,arbol->izquierda);
		else
			arbol->izquierda = nodo;
	else 
		if (arbol->derecha)
			insertar(nodo,valor,arbol->derecha);
		else
			arbol->derecha = nodo;
}

bool Arbol::buscar(int dato)
{
	if (dato <= raiz->valor)
		if (raiz->izquierda)
			return buscar_recursivo(dato,raiz->izquierda);
		else 
			return 0;
	else
		if (raiz->derecha)
			return buscar_recursivo(dato,raiz->derecha);
		else 
			return 0;
}

bool Arbol::buscar_recursivo(int dato, pnodoArbol arbol)
{
	if (arbol == NULL)
		return false;
	else
		if (dato == arbol->valor)
			return true;
		else if (dato > arbol->valor)
			buscar_recursivo(dato,arbol->derecha);
		else
			buscar_recursivo(dato,arbol->izquierda);
	return false;
}

void Arbol::draw()
{
	draw_recursivo(raiz,"",true);
}

void Arbol::draw_recursivo(pnodoArbol arbol, string indent, bool is_tail) 
{
	if (arbol!=raiz)
	{
		
		cout << indent << (is_tail ? "╚═": "╠═");
		if (arbol == nullptr) 
		{
			cout << "*" << endl;
			return;
		}
		else
			cout << arbol->valor << endl;
	}
	else
		cout << endl;

	if (arbol->izquierda != nullptr or arbol->derecha != nullptr) 
	{
		if (arbol!= raiz)
			indent.append(is_tail ? "  " : "║  ");
		draw_recursivo(arbol->derecha, indent, false);
		if (arbol->izquierda)
			draw_recursivo(arbol->izquierda, indent, true);
		else
			draw_recursivo(arbol->izquierda, indent, false);
	}
}



Arbol::~Arbol()
{
}

