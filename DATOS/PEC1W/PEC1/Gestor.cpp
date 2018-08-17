#include "Gestor.hpp"

Gestor::Gestor()
{
}

Gestor::~Gestor()
{
}

void Gestor::generar_solicitudes_de_clientes_VIP()
{
	int tiempo_cliente_anterior = 0;
	
	borrar_clientes_VIP(false);
	
	VIP_en_espera.insertar(Cliente (0,true));
	
	for(int x = 1; x < VIP; x++)
	{
		tiempo_cliente_anterior += rand()%6;
		VIP_en_espera.insertar(Cliente (tiempo_cliente_anterior,true));
	}
	
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,10);
	cout << endl << "\tClientes VIP generados!" << endl << endl;
}

void Gestor::mostrar_clientes_VIP()
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	if (VIP_en_espera.es_vacia())
    {
        SetConsoleTextAttribute(hConsole,10);
        cout << endl <<"\tClientes VIP: " << "\t\t\t"<< VIP_en_espera.cantidad_de_clientes() << endl << endl;
        SetConsoleTextAttribute(hConsole,15);
		VIP_en_espera.mostrar();
    }
	else
    {
        SetConsoleTextAttribute(hConsole,12);
		cout << endl << "\tNo hay Clientes VIP en espera!" << endl << endl;
    }
}

void Gestor::borrar_clientes_VIP(bool texto)
{
	while(VIP_en_espera.es_vacia())
		VIP_en_espera.eliminar();
		
	if (texto)
    {
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole,13);
		cout << endl << "\tClientes VIP borrados!" << endl << endl;
        
    }
}

void Gestor::generar_solicitudes_de_clientes_no_registrados()
{
	int tiempo_cliente_anterior = 0;
	
	borrar_clientes_no_registrados(false);
	
	no_registrado_en_espera.insertar(Cliente (0,false));
	
	for(int x = 1; x < NO_REGISTRADOS; x++)
	{
		tiempo_cliente_anterior += rand()%6;
		no_registrado_en_espera.insertar(Cliente (tiempo_cliente_anterior,false));
	}
	
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,10);
	cout << endl << "\tClientes NO REGISTRADOS generados!" << endl << endl;
}

void Gestor::mostrar_clientes_no_registrados()
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	if (no_registrado_en_espera.es_vacia())
    {
        SetConsoleTextAttribute(hConsole,10);
        cout << endl<< "\tClientes NO REGISTRADOS: " << "\t\t\t"<< VIP_en_espera.cantidad_de_clientes() << endl << endl;
        SetConsoleTextAttribute(hConsole,15);
		no_registrado_en_espera.mostrar();
    }
	else
    {
        SetConsoleTextAttribute(hConsole,12);
		cout << endl << "\tNo hay Clientes NO REGISTRADOS en espera!" << endl << endl;
    }
}

void Gestor::borrar_clientes_no_registrados(bool texto)
{
	while(no_registrado_en_espera.es_vacia())
		no_registrado_en_espera.eliminar();
		
	if (texto)
    {
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole,13);
		cout << endl << "\tClientes NO REGISTRADOS borrados!" << endl << endl;
    }
}

void Gestor::simular_venta_a_clientes_VIP()
{
	int tiempo = -1;
	int tiempo_en_proceso = 0;
    
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	
	if(VIP_en_espera.cantidad_de_clientes()>0)
	{
		Cliente c;
		
		do
		{
			if (servidor_VIP.cantidad_de_clientes()>0)
			{
				if (servidor_VIP.ver_primero().get_tiempo_de_compra() == tiempo_en_proceso )
				{
					tiempo_en_proceso = 0;
					c = servidor_VIP.eliminar();
					c.generar_identificador_de_entrada();
					clientes_terminados.insertar(c);
				}
				else
					tiempo_en_proceso++;
			}
			
			while((VIP_en_espera.cantidad_de_clientes() > 0) and (VIP_en_espera.ver_primero().get_hora_de_inicio_de_compra() == tiempo))
				servidor_VIP.insertar(VIP_en_espera.eliminar());
				
			actualizar_pantalla_VIP(tiempo, tiempo_en_proceso);
			
			tiempo ++;
			usleep(TIEMPO);
			system("cls");
		}while(servidor_VIP.cantidad_de_clientes() > 0 or VIP_en_espera.cantidad_de_clientes() > 0);
        SetConsoleTextAttribute(hConsole,10);
		cout << "\t\tVENTA DE ENTRADAS VIP TERMINADA" << endl;
        actualizar_pantalla_VIP(tiempo, tiempo_en_proceso);
	}
	else
    {
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole,12);   
		cout << endl << "\tNo hay clientes VIP" << endl << endl;
    }
}

void Gestor::actualizar_pantalla_VIP(int tiempo, int tiempo_en_proceso)
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,1115);

    cout << endl <<"\t\t******************************" << endl;
    cout << "\t\t       Tiempo:";
    show_hora(tiempo);
    cout << "           "<< endl;
    cout << endl;
    cout << "\t\t******************************" << endl<<endl;
    SetConsoleTextAttribute(hConsole,15);
    cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
    SetConsoleTextAttribute(hConsole,11);
    cout << "\tClientes en espera: " << "\t\t\t"<< VIP_en_espera.cantidad_de_clientes() << endl << endl;
    SetConsoleTextAttribute(hConsole,7);
    VIP_en_espera.mostrar();
    SetConsoleTextAttribute(hConsole,15);
    cout << endl <<"----------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
    SetConsoleTextAttribute(hConsole,14);
    cout << "\tServidor VIP: " << "\t\t\t"<< " Tiempo en proceso: " <<  tiempo_en_proceso << endl << endl;
    SetConsoleTextAttribute(hConsole,7);
    servidor_VIP.mostrar();
    SetConsoleTextAttribute(hConsole,15);
    cout << "----------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
    SetConsoleTextAttribute(hConsole,2);
    cout << "\tEntradas vendidas: "<< "\t\t\t"<< clientes_terminados.cantidad_de_clientes() << endl << endl;
    SetConsoleTextAttribute(hConsole,7);
    clientes_terminados.mostrar();
    SetConsoleTextAttribute(hConsole,15);
    cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
}

void Gestor::simular_venta_a_clientes_no_registrados()
{
	int tiempo = 119;
	int tiempo_en_proceso[SERVIDORES_NO_REGISTRADOS];
	
	for (int x = 0; x < SERVIDORES_NO_REGISTRADOS; x++)
		tiempo_en_proceso[x] = 0;
	
	if(no_registrado_en_espera.cantidad_de_clientes()>0)
	{
		Cliente c;
		
		do
		{
			for (int x = 0; x < SERVIDORES_NO_REGISTRADOS; x++)
			{
				if (servidor_no_registrado[x].cantidad_de_clientes() > 0)
				{
					if (servidor_no_registrado[x].ver_primero().get_tiempo_de_compra() == tiempo_en_proceso[x])
					{
						tiempo_en_proceso[x] = 0;
						c = servidor_no_registrado[x].eliminar();
						c.generar_identificador_de_entrada();
						clientes_terminados.insertar(c);
					}
					else
						tiempo_en_proceso[x]++;
				}
			}
			
			while((no_registrado_en_espera.cantidad_de_clientes() > 0) and (no_registrado_en_espera.ver_primero().get_hora_de_inicio_de_compra() == tiempo))
			{
				int mejor_servidor;
				int menor = NO_REGISTRADOS+1;
				
				for(int x = 0; x < SERVIDORES_NO_REGISTRADOS; x ++)
				{
					if (servidor_no_registrado[x].cantidad_de_clientes() < menor)
					{
						menor = servidor_no_registrado[x].cantidad_de_clientes();
						mejor_servidor = x;
					}
				}
				
				servidor_no_registrado[mejor_servidor].insertar(no_registrado_en_espera.eliminar());
			}
			
			actualizar_pantalla_no_registrado(tiempo, tiempo_en_proceso);
			system("cls");
            
			tiempo ++;
			usleep(TIEMPO);
            
		}while(recuento_no_registrados() > 0 or no_registrado_en_espera.cantidad_de_clientes() > 0);
		cout << "\t\tVENTA DE ENTRADAS NO REGISTRADOS TERMINADA" << endl;
        actualizar_pantalla_no_registrado(tiempo, tiempo_en_proceso);
	}
	else
    {
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole,12);
		cout << endl << "\tNo hay clientes NO_REGISTRADOS" << endl << endl;
    }
}

void Gestor::actualizar_pantalla_no_registrado(int tiempo, int * tiempo_en_proceso)
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,1115);

    cout << endl <<"\t\t******************************" << endl;
    cout << "\t\t       Tiempo:";
    show_hora(tiempo);
    cout << "           "<< endl;
    cout << "\t\t******************************" << endl<<endl;
    SetConsoleTextAttribute(hConsole,15);
    cout << endl <<"----------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
    SetConsoleTextAttribute(hConsole,11);
    cout << "\tClientes en espera: " << "\t\t\t"<< no_registrado_en_espera.cantidad_de_clientes() << endl << endl;
    SetConsoleTextAttribute(hConsole,7);
    no_registrado_en_espera.mostrar();
    
    for (int x = 0; x < SERVIDORES_NO_REGISTRADOS; x++)
    {
        SetConsoleTextAttribute(hConsole,15);
        cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
        SetConsoleTextAttribute(hConsole,14);
        cout << "\tServidor: "<< x << "\t\t\tTiempo en proceso: "<< tiempo_en_proceso[x] << endl << endl;
        SetConsoleTextAttribute(hConsole,7);
        servidor_no_registrado[x].mostrar();
    }
    SetConsoleTextAttribute(hConsole,15);
    cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
    SetConsoleTextAttribute(hConsole,10);
    cout << "\tEntradas vendidas: "<< "\t\t\t"<< clientes_terminados.cantidad_de_clientes() << endl << endl;
    SetConsoleTextAttribute(hConsole,7);
    clientes_terminados.mostrar();
    SetConsoleTextAttribute(hConsole,15);
    cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
}

void Gestor::mostrar_entradas_vendidas()
{
	if(clientes_terminados.cantidad_de_clientes())
	{
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole,10);
		cout << endl << "\tEntradas vendidas: " << endl << endl;
        SetConsoleTextAttribute(hConsole,15);
		clientes_terminados.mostrar();
	}
	else
    {
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole,12);
		cout << endl << "\tNo hay ENTRADAS vendidas" << endl;
    }
}

void Gestor::generar_arbol()
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,14);
	cout << endl << "\tOpcion por implementar" << endl;
}

void Gestor::buscar_cliente()
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,14);
	cout << endl << "\tOpcion por implementar" << endl;
}

void Gestor::mostrar_arbol_en_post_orden()
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,14);
	cout << endl << "\tOpcion por implementar" << endl;
}

void Gestor::mostrar_arbol_en_pre_orden()
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,14);
	cout << endl << "\tOpcion por implementar" << endl;
}

void Gestor::mostrar_arbol_en_in_orden()
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,14);
	cout << "\tOpcion por implementar" << endl;
}

void Gestor::dibujar_arbol()
{
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,14);
	cout << "\tOpcion por implementar" << endl;
}

void Gestor::reiniciar()
{
	borrar_clientes_VIP(true);
	borrar_clientes_no_registrados(true);
	
	while (servidor_VIP.es_vacia())
		servidor_VIP.eliminar();
	
	for (int x = 0; x < SERVIDORES_NO_REGISTRADOS; x++)
		while(servidor_no_registrado[x].es_vacia())
			servidor_no_registrado[x].eliminar();
			
	while (clientes_terminados.es_vacia())
		clientes_terminados.eliminar();
    
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole,13);
	cout << endl << "\tClientes LISTA DE ENTRADAS borrada!" << endl << endl;
    SetConsoleTextAttribute(hConsole,10);
	cout << endl << "\tPrograma reiniciado!" << endl << endl;
}

int Gestor::recuento_no_registrados()
{
	int acumulador = 0;
	for (int x = 0; x < SERVIDORES_NO_REGISTRADOS; x++)
		acumulador += servidor_no_registrado[x].cantidad_de_clientes();
		
	return acumulador;
}

void Gestor::show_hora(int t)
{
	int hora = t/60, minutos = t%60;
	if (hora < 10) 
		cout << '0';
	cout << hora << ':';
	if (minutos < 10)
		cout << '0';
	 cout << minutos;
}

int Gestor::recuento_VIP()
{
	return VIP_en_espera.cantidad_de_clientes();
}

int Gestor::recuento_entradas()
{
	return clientes_terminados.cantidad_de_clientes();
}

int Gestor::cantidad_no_registrados()
{
	return no_registrado_en_espera.cantidad_de_clientes();
}