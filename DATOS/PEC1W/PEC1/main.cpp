
#include <iostream>
#include <ctime>
#include <math.h>
#include <iomanip>
#include "windows.h"

#include <Gestor.hpp>

using namespace std;

int main(int argc, char **argv)
{
	Gestor gestor;
	char opcion;
	ShowWindow(GetConsoleWindow(),SW_MAXIMIZE);
	srand(time(NULL));
	
	do
	{
		HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
		SetConsoleTextAttribute(hConsole,74);
		
        cout << endl << endl;
		cout << "\t---------------------------------------------------------------------------------------------\t\t\t" << endl;
		cout << "\t                                          MENU                                               \t\t\t" << endl;
		cout << "\t---------------------------------------------------------------------------------------------\t\t\t" << endl;
		
		
		SetConsoleTextAttribute(hConsole,47);
		
		cout << "\tNumero de clientes VIP: " << setw(4)<< gestor.recuento_VIP() << "                                                                 \t\t\t"<< endl;
		cout << "\tNumero de clientes no registrados: " << setw(4)<< gestor.cantidad_no_registrados() << "                                                      \t\t\t" << endl;
		cout << "\tEntradas vendidas: "<< setw(4) << gestor.recuento_entradas() << "                                                                      \t\t\t"<< endl << endl;
		
		SetConsoleTextAttribute(hConsole,7);
		
		cout << "\t\t A. Generar solicitudes de entrada de los clientes VIP." << endl;
		cout << "\t\t B. Mostrar la cola de solicitudes en espera de los clientes VIP." << endl;
		cout << "\t\t C. Borrar la cola de solicitues en espera de los clientes VIP." << endl;
		cout << "\t\t D. Generar solicitudes de entrada de los clientes no registrados." << endl;
		cout << "\t\t E. Mostrar la cola de solicitudes en espera de los clientes no registrados." << endl;
		cout << "\t\t F. Borrar la cola de solicitues en espera de los clientes no registrados." << endl;
		cout << "\t\t G. Simular el proceso de compra de los clientes VIP." << endl;
		cout << "\t\t H. Simular el proceso de compra de los clientes no registrados." << endl;
		cout << "\t\t I. Mostrar la lista de entradas vendidas." << endl;
		cout << "\t\t J. Reiniciar el programa." << endl << endl;
		
		cout << "\t\t K. Crear un arbol binario de busqueda ordenado por el DNI." << endl;
		cout << "\t\t L. Buscar un cliente en el ABB, dado su DNI y mostrar sus datos." << endl;
		cout << "\t\t M. Mostrar el ABB en pre-orden." << endl;
		cout << "\t\t N. Mostrar el ABB en post-orden." << endl;
		cout << "\t\t O. Mostrar el ABB en in-orden." << endl;
		cout << "\t\t P. Dibujar el ABB." << endl << endl;
		
		cout << "\t\t S. Salir del programa" << endl << endl;
		
        SetConsoleTextAttribute(hConsole,15);
		cout << "\tIndique la opcion deseada: ";
		
		
		cin >> opcion ;
		opcion = toupper(opcion);
		system("cls");
		
		switch(opcion)
		{
			case 'A':
				gestor.generar_solicitudes_de_clientes_VIP();
			break;
			case 'B':
				gestor.mostrar_clientes_VIP();
			break;
			case 'C':
				gestor.borrar_clientes_VIP(true);
			break;
			case 'D':
				gestor.generar_solicitudes_de_clientes_no_registrados();
			break;
			case 'E':
				gestor.mostrar_clientes_no_registrados();
			break;
			case 'F':
				gestor.borrar_clientes_no_registrados(true);
			break;
			case 'G':
				gestor.simular_venta_a_clientes_VIP();
			break;
			case 'H':
				gestor.simular_venta_a_clientes_no_registrados();
			break;
			case 'I':
				gestor.mostrar_entradas_vendidas();
			break;
			case 'J':
				gestor.reiniciar();
			break;
			case 'K':
				gestor.generar_arbol();
			break;
			case 'L':
				gestor.buscar_cliente();
			break;
			case 'M':
				gestor.mostrar_arbol_en_pre_orden();
			break;
			case 'N':
				gestor.mostrar_arbol_en_post_orden();
			break;
			case 'O':
				gestor.mostrar_arbol_en_in_orden();
			break;
			case 'P':
				gestor.dibujar_arbol();
			break;
			case 'S':
                SetConsoleTextAttribute(hConsole,13);
				cout << endl<< endl <<"\tSaliendo del programa..." << endl << endl << endl;
			break;
			default:
                SetConsoleTextAttribute(hConsole,12);
				cout << endl  <<"\tOpcion incorrecta!" << endl << endl;
			break;
		}
	}
	while(opcion != 'S');
	
	return 0;
}