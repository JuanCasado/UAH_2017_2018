#include <iostream>
#include <string.h>

using namespace std;

struct persona
{
	char nombre[30];
	int edad;
	long telefono;
};

void Escribir (persona p)
{
	cout << p.nombre << " tiene " << p.edad << " años y su teléfono es "<<p.telefono<<endl; 
}

void EscribirPuntero (persona* p)
{
	cout << p->nombre << " tiene " << p->edad << " años y su teléfono es "<<p->telefono<<endl; 
}

persona CrearPersona (char n[30], int e, long t)
{
	persona aux;
	strcpy(aux.nombre,n);
	aux.edad = e;
	aux.telefono = t;
	return aux;
}
int main(int argc, char **argv)
{
	persona ejemplo;
	
	ejemplo = CrearPersona("Jesus", 99, 123456789);
	cout << "Paso por valor: "<<endl;
	Escribir(ejemplo);
	
	cout << "Paso por puntero: "<<endl;
	EscribirPuntero(&ejemplo);
	
	
	return 0;
}
