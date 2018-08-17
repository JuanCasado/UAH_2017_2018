#include "Fraccion.hpp"

int main(int argc, char **argv)
{
	cout << "Ejemplos con constructor: " << endl;
	Fraccion f1;
	f1.Escribir();
	cout << endl;
	Fraccion f2(4,6);
	f2.Escribir();
	cout<<endl;
	Fraccion *f3 = new Fraccion(14,8);
	
	cout << "Forma 1: ";
	(*f3).Escribir();
	cout << endl << "Forma 2: ";
	f3->Escribir();
	cout << endl << "Introduccion de datos: " << endl << "Primero fraccion: " << endl;
	f1.Crear();
	cout << "Segunda fraccion: "<<endl;
	f2.Crear();
	cout << "Resultado de la suma: " << endl;
	
	(*f3) = f3->Sumar(f1,f2);
	(*f3).Escribir();
	
	cout << "Resultado de la multiplicacion: ";
	Fraccion f4;
	f4.HazMulti(f1,f2);
	f4.Escribir();
	
	return 0;
}
