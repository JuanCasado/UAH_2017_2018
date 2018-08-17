#include <iostream>
using namespace std;

void machaca1(int x, int* y)
{
	*y=x;
	return;
}

void machaca2 (int x, int& y)
{
	y=x;
	return;
}

int main(int argc, char **argv)
{
	int a,b;
	cout << "Escribe dos datos enteros: \n";
	cin >> a >> b;
	int copia = b;
	
	cout << "Antes de machaca1: " << a <<"/" << b << endl;
	machaca1(a,&b);
	cout << "Después de machaca1: " << a <<"/" << b << endl;
	
	b=copia;
	cout << "Antes de machaca2: " << a  << "/" << b << endl;
	machaca2(a,b);
	cout << "Después de machaca2: " << a <<"/" << b << endl;
	
	return 0;
}
