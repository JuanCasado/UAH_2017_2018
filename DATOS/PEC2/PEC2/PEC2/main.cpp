#include <iomanip>
#include "Gestor.hpp"


int main(int argc, char **argv)
{
	Gestor gestor;
	char opcion;
	Colores C;
	
	#if WINDOWS
			system("cls");
	#else
		cout << "\033c";
	#endif
	
	#if WINDOWS
		ShowWindow(GetConsoleWindow(),SW_MAXIMIZE);
	#else
	cout << "\e[9;1t";
	#endif
	
	C.colorear(ROJO);
	cout << "LOADING";
	#if WINDOWS
		Sleep(TIEMPO/2);
	#else
		usleep(TIEMPO/2);
	#endif
	cout << ".";
	#if WINDOWS
		Sleep(TIEMPO/2);
	#else
		usleep(TIEMPO/2);
	#endif
	cout << ".";
	#if WINDOWS
		Sleep(TIEMPO/2);
	#else
		usleep(TIEMPO/2);
	#endif
	cout << ".";
	#if WINDOWS
		Sleep(TIEMPO/2);
	#else
		usleep(TIEMPO/2);
	#endif
	
	srand(time(NULL));
	
	#if WINDOWS
			system("cls");
	#else
		cout << "\033c";
	#endif
	
	do
	{
		C.colorear(AMARILLO);
		cout << endl << endl;
		cout << "\t---------------------------------------------------------------------------------------------\t\t\t" << endl;
		cout << "\t                                          MENU                                               \t\t\t" << endl;
		cout << "\t---------------------------------------------------------------------------------------------\t\t\t" << endl;
		
		C.colorear(VERDE);
		
		cout << "\tNumero de clientes VIP: "; C.colorear(MORADO); cout << setw(4)<< gestor.recuento_VIP_total() << endl;C.colorear(VERDE);
		cout << "\tNumero de clientes VIP EN ESPERA: "; C.colorear(MORADO); cout << setw(4)<< gestor.recuento_VIP() << endl;C.colorear(VERDE);
		cout << "\tNumero de clientes no registrados: " ;C.colorear(MORADO); cout << setw(4)<< gestor.cantidad_no_registrados_total() << endl;C.colorear(VERDE);
		cout << "\tNumero de clientes no registrados EN ESPERA: " ;C.colorear(MORADO); cout << setw(4)<< gestor.cantidad_no_registrados() << endl;C.colorear(VERDE);
		cout << "\tEntradas vendidas: "; C.colorear(MORADO); cout << setw(4) << gestor.recuento_entradas() << endl; C.colorear(VERDE);
		cout << "\tLista: ";
		if (gestor.recuento_entradas())
			cout << "OK" << endl;
		else
		{
			C.colorear(ROJO);
			cout << "OFF" << endl;
			C.colorear(VERDE);
		}
		cout << "\tArbol: ";
		if (gestor.estado_arbol())
			cout << "OK" << endl << endl;
		else
		{
			C.colorear(ROJO);
			cout << "OFF" << endl << endl;
		}
		C.colorear(AMARILLO);
		
		cout << "\t\t A. ";C.colorear(GRIS); cout << "Generar solicitudes de entrada de los clientes VIP." << endl;C.colorear(AMARILLO);
		cout << "\t\t B. ";C.colorear(GRIS); cout << "Mostrar la cola de solicitudes en espera de los clientes VIP." << endl;C.colorear(AMARILLO);
		cout << "\t\t C. ";C.colorear(GRIS); cout << "Borrar la cola de solicitues en espera de los clientes VIP." << endl;C.colorear(AMARILLO);
		cout << "\t\t D. ";C.colorear(GRIS); cout << "Generar solicitudes de entrada de los clientes no registrados." << endl;C.colorear(AMARILLO);
		cout << "\t\t E. ";C.colorear(GRIS); cout << "Mostrar la cola de solicitudes en espera de los clientes no registrados." << endl;C.colorear(AMARILLO);
		cout << "\t\t F. ";C.colorear(GRIS); cout << "Borrar la cola de solicitues en espera de los clientes no registrados." << endl;C.colorear(AMARILLO);
		cout << "\t\t G. ";C.colorear(GRIS); cout << "Simular el proceso de compra de los clientes VIP." << endl;C.colorear(AMARILLO);
		cout << "\t\t H. ";C.colorear(GRIS); cout << "Simular el proceso de compra de los clientes no registrados." << endl;C.colorear(AMARILLO);
		cout << "\t\t I. ";C.colorear(GRIS); cout << "Mostrar la lista de entradas vendidas." << endl;C.colorear(AMARILLO);
		cout << "\t\t J. ";C.colorear(GRIS); cout << "Reiniciar el programa." << endl << endl;C.colorear(AMARILLO);
		
		cout << "\t\t K. ";C.colorear(GRIS); cout << "Crear un arbol binario de busqueda ordenado por el DNI." << endl;C.colorear(AMARILLO);
		cout << "\t\t L. ";C.colorear(GRIS); cout << "Buscar un cliente en el ABB, dado su DNI y mostrar sus datos." << endl;C.colorear(AMARILLO);
		cout << "\t\t M. ";C.colorear(GRIS); cout << "Mostrar el ABB en pre-orden." << endl;C.colorear(AMARILLO);
		cout << "\t\t N. ";C.colorear(GRIS); cout << "Mostrar el ABB en post-orden." << endl;C.colorear(AMARILLO);
		cout << "\t\t O. ";C.colorear(GRIS); cout << "Mostrar el ABB en in-orden." << endl;C.colorear(AMARILLO);
		cout << "\t\t P. ";C.colorear(GRIS); cout << "Dibujar el ABB." << endl << endl; C.colorear(AMARILLO);
				
		cout << "\t\t Q. ";C.colorear(GRIS); cout << "GENERAREXAMEN." << endl; C.colorear(AMARILLO);
		cout << "\t\t R. ";C.colorear(GRIS); cout << "ESTADISTICAS." << endl << endl;C.colorear(AMARILLO);
		
		
		cout << "\t\t S. ";C.colorear(ROJO); cout << "Salir del programa" << endl << endl;C.colorear(VERDE);
		
		cout << "\tIndique la opcion deseada: ";
		C.colorear(AMARILLO);
		
		cin >> opcion ;
		opcion = toupper(opcion);
		
		#if WINDOWS
			system("cls");
		#else
			cout << "\033c";
		#endif
		
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
			case 'Q':
				gestor.generar();
			break;
			case 'R':
				gestor.estadisticas();
			break;
			case 'S':
				C.colorear(MORADO);
				cout << "Saliendo del programa..." << endl << endl;
				C.blanco();
			break;
			default:
				C.colorear(ROJO);
				cout << "Opcion incorrecta!" << endl << endl;
			break;
		}
	}
	while(opcion != 'S');
	
	return 0;
}
