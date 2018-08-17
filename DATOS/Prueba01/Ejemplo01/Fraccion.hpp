#ifndef FRACCION_HPP
#define FRACCION_HPP
#include <iostream>
#include <math.h>
using namespace std;

class Fraccion
{
public:
	Fraccion();
	~Fraccion();
	Fraccion(int x, int y);
	
	void Escribir();
	void Crear();
	int GetNum();
	int GetDen();
	void HazMulti(Fraccion a, Fraccion b);
	Fraccion Sumar (Fraccion x, Fraccion y);
	
private:
	int num,den;
	int MCD (int a, int b);
	void Reducir();

};

#endif // FRACCION_HPP
