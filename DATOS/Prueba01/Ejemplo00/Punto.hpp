#ifndef PUNTO_HPP
#define PUNTO_HPP

#include <iostream>
#include <math.h>

using namespace std;

class Punto
{
	public:
		Punto();
		~Punto();
		
		int LeerX();
		int LeerY();
		void FijarX(int valor);
		void FijarY(int valor);
		double Distancia();

	private:
		int x;
		int y;
};

#endif // PUNTO_HPP
