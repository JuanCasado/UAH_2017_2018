#include <iostream>

using namespace std;



int main(int argc, char **argv)
{
	int cuantos;
	cout<<"Cuantos datos va a introducir? ";
	cin >> cuantos;
	if (cuantos <= 0)
		cout<<"La cantidad no es valida";
	else
	{
		int llevo = 0;
		double suma = 0, dato, media;
		while(llevo<cuantos)
		{
			//llevo++;
			cout << "Introduce el dato "<< ++llevo <<"/"<<cuantos<<": ";
			cin>>dato;
			suma+=dato;
		}
	media = suma/cuantos;
	cout<<"La media es: "<< media <<endl;
	}
	
	return 0;
}
