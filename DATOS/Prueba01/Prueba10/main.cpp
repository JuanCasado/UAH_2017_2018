#include <iostream>
using namespace std;

float cuadrado (int k)
{return k*k;}

float cuadrado (float x)
{return x*x;}

void cuadrado(char c)
{cout<<c<<c<<endl<<c<<c<<endl;}

int main(int argc, char **argv)
{
	int a = 7;
	float b = 1.2;
	char c = '*';
	
	cout<<"El cuadrado entero: "<<cuadrado(a)<<endl;
	cout <<"El cuadrado float: "<<cuadrado(b)<<endl;
	cuadrado(c);
	
}
