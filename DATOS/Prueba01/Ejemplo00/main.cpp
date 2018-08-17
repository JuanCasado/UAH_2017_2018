#include "Punto.hpp"


int main(int argc, char **argv)
{
	int a,b;
	Punto p;
	
	cout << "Dame las coordenadas del punto: \n";
	cin >> a >> b;
	p.FijarX(a);
	p.FijarY(b);
	cout << "El valor del punto p es: (" << LeerX() <<","<< LeerY() << ")"<< endl;
	cout << "La distancia al origen es: " << p.Distancia() << endl;
	
	return 0;
}
