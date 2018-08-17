#include "Punto.hpp"


Punto::Punto()
{

}

Punto::~Punto()
{

}

int Punto::LeerX()
{
	return x;
}
int Punto::LeerY()
{
	return y;
}
void Punto::FijarX(int valor)
{
	x = valor;
}
void Punto::FijarY(int valor)
{
	y = valor;
}
double Punto::Distancia()
{
	return sqrt(powl(LeerX(),2+powl(LeerY(),2),2)
}


