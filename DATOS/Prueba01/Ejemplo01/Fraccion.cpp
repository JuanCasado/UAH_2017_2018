#include "Fraccion.hpp"

Fraccion::Fraccion()
{
	num = 0;
	den = 1;
}

Fraccion::Fraccion(int x, int y)
{
	if (y!=0)
	{
		num = x;
		den = y;
		Reducir();
	}
	else
	{
		num = 0;
		den = 1;
	}
}

Fraccion::~Fraccion()
{
}

void Fraccion::Escribir()
{
	cout << num << "/" << den;
}

void Fraccion::Crear()
{
	cout << "Numerador: ";
	cin >> num;
	cout << "Denominador: ";
	cin >> den;
}

int Fraccion::GetNum()
{
	return num;
}

int Fraccion::GetDen()
{
	return den;
}

void Fraccion::HazMulti(Fraccion a, Fraccion b)
{
	num = a.num*b.num;
	den = a.den*b.den;
	Reducir();
}

int Fraccion::MCD(int a, int b)
{
	int aux;
	while(a%b!=0)
	{
		aux = a%b;
		a=b;
		b=aux;
	}
	return b;
}

void Fraccion::Reducir()
{
	int aux = MCD(abs(num),abs(den));
	num/=aux;
	den/=aux;
	if(den <0)
	{
		num = -num;
		den = -den;
	}
}

Fraccion Fraccion::Sumar(Fraccion x, Fraccion y)
{
	int auxden = x.GetDen()*y.GetDen();
	int auxnum = (x.GetNum()*y.GetDen())+(x.GetDen()*y.GetNum());
	Fraccion f(auxnum,auxden);
	
	return f;
}