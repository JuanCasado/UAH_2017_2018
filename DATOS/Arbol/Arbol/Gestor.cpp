#include "Gestor.hpp"

Gestor::Gestor()
{
	arbol = new Arbol( new NodoArbol (RANGE_LISTA/2));
}

void Gestor::generar_lista()
{
	for (int x = 0; x < LEN_LISTA; x++)
		lista [x] = rand() % RANGE_LISTA;
		
	ordenar_lista();
}

void Gestor::ordenar_lista()
{
	int min = 0;
	int temp = 0;
	for (int x=0; x < LEN_LISTA - 1; x++)
	{
		min = menor(x);
		temp = lista[x];
		lista[x] = lista [min];
		lista [min] = temp;
	}
}

int Gestor::menor(int inicio)
{
	int min = lista[inicio];
	for (int x = inicio; x < LEN_LISTA; x++)
	{
		if (lista[x] < min)
		min = lista[x];
	}
	return min;
}

void Gestor::generar_arbol()
{
	generar_lista();
	arbol->generar(lista, LEN_LISTA);
	
	cout << endl << "\tARBOL GENERADO!"<< endl << endl;
}

void Gestor::buscar()
{
	int dato = 0;
	cout << endl <<"\tIntroduzca un dato: ";
	cin >> dato;
	
	if (arbol->buscar(dato))
		cout << endl << "\t" << dato << " PRESENTE" << endl;
	else 
		cout << endl << "\t" << dato << " AUSENTE" << endl;
}

void Gestor::dibujar()
{
	arbol->draw();
}


Gestor::~Gestor()
{
}

