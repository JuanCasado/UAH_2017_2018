#include <stdio.h>
#include <Gestor.hpp>

int main(void)
{
	srand(time(NULL));
	char caso = 0;
	Gestor gestor;
	
	while (true)
	{
		cout << "--------------------------------------------------------" << endl;
		cout << "                         ARBOL" << endl;
		cout << "--------------------------------------------------------" << endl << endl;
	
		cout << "\tA. Gerenerar árbol." << endl;
		cout << "\tB. Buscar en árbol." << endl;
		cout << "\tC. Mostrar in-orden." << endl;
		cout << "\tD. Mostrar post-orden." << endl;
		cout << "\tE. Mostrar pre-orden." << endl;
		cout << "\tF. Dibujar." << endl << endl;
		cout << "\tS. Salir" << endl << endl;
		
		cout << "\tELIJA UNA OPCION: ";
		cin >> caso;
		caso = toupper(caso);
		
		
		switch(caso)
		{
			case 'A':
				gestor.generar_arbol();
			break;
			case 'B':
				gestor.buscar();
			break;
			case 'C':
			
			break;
			case'D':
			
			break;
			case'E':
			
			break;
			case 'F':
				gestor.dibujar();
			break;
			case 'S':
				cout << endl << "Saliendo..." << endl;
				exit(0);
			break;
			default:
				cout << endl << "OPCIÓN NO VÁLIDA!" << endl << endl;
			break;
		}
	}
	
	return 0;
}
