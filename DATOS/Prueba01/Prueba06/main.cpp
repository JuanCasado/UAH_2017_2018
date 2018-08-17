#include <iostream>

using namespace std;

int main(int argc, char** argv)
{
	int x, y, min, max;
	cout<<"Valor 1: ";
	cin>>x;
	cout<<"Valor 2: ";
	cin>>y;
	
	if (x < y)
		min = x;
	else
		min = y;
		
	max = (x > y ? x: y);
	
	cout<<"El minimo es: "<<min<<"\nEl maximo es:"<<max<<endl;
	
	return 0;
}