#include <iostream>
using namespace std;

int main(int argc, char **argv)
{
	int x;
	char c;
	
	cout<<"Escriba un número: ";
	cin>>x;
	c=x++;
	
	cout<<"El valor del int es: "<<x<<endl;
	cout<<"El valor del char es: "<<c<<endl;
	
	return 0;
}
