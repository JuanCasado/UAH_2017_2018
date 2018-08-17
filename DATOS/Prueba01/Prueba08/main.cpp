#include <iostream>

using namespace std;

int main(int argc, char **argv)
{
	char c;
	bool test;
	
	cout << "Pulsa techas numéricas. Acaba pulsando ESC\n";
	
	do 
	{
		c = getchar();
		test = ((c >= '0')&&(c<='9'));
		if (test)
			cout << c <<" pulsado. \n";
	}
	while(c!=27);
	
	cout << "\npulsa una tecla\n";
	
	getchar();
	
	return 0;
}
