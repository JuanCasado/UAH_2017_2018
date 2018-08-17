#include "Gestor.hpp"

Gestor::Gestor()
{
	arbol = NULL;
	vip = 0;
	no_registrados = 0;
}

Gestor::~Gestor()
{
}

void Gestor::generar_solicitudes_de_clientes_VIP()
{
	int tiempo_cliente_anterior = 0;
	if (recuento_VIP() == 0)
		vip+=VIP;
		
	borrar_clientes_VIP(false);
	
	for(int x = 0; x < VIP; x++)
	{
		VIP_en_espera.insertar(Cliente (tiempo_cliente_anterior,true));
		tiempo_cliente_anterior += rand()%6;
	}
	
	C.colorear(VERDE);
	cout << endl << "\tClientes VIP generados!" << endl << endl;
	C.blanco();
}

void Gestor::mostrar_clientes_VIP()
{
	if (VIP_en_espera.es_vacia())
	{
		C.colorear(VERDE);
        cout << endl<< "\tClientes VIP: " << "\t\t\t"<< VIP_en_espera.cantidad_de_clientes() << endl << endl;
		C.colorear(GRIS);
		C.colorear(GRIS);
		VIP_en_espera.mostrar();
		C.blanco();
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tNo hay Clientes VIP en espera!" << endl << endl;
		C.blanco();
    }
}

void Gestor::borrar_clientes_VIP(bool texto)
{
	if (recuento_VIP() == VIP)
		vip -= VIP;
		
	VIP_en_espera.vaciar();
		
	if (texto)
	{
		C.colorear(MORADO);
		cout << endl << "\tClientes VIP borrados!" << endl << endl;
		C.blanco();
	}
}

void Gestor::generar_solicitudes_de_clientes_no_registrados()
{
	int tiempo_cliente_anterior = 0;
	if (cantidad_no_registrados() == 0)
		no_registrados += NO_REGISTRADOS;
	
	borrar_clientes_no_registrados(false);
	
	for(int x = 0; x < NO_REGISTRADOS; x++)
	{
		no_registrado_en_espera.insertar(Cliente (tiempo_cliente_anterior,false));
		tiempo_cliente_anterior += rand()%6;
	}
	
	C.colorear(VERDE);
	cout << endl << "\tClientes NO REGISTRADOS generados!" << endl << endl;
	C.blanco();
}

void Gestor::mostrar_clientes_no_registrados()
{
	if (no_registrado_en_espera.es_vacia())
    {
		C.colorear(VERDE);
        cout << endl<< "\tClientes NO REGISTRADOS: " << "\t\t\t"<< no_registrado_en_espera.cantidad_de_clientes() << endl << endl;
		C.colorear(GRIS);
		no_registrado_en_espera.mostrar();
		C.blanco();
    }
	else
    {
		C.colorear(ROJO);
		cout << endl << "\tNo hay Clientes NO REGISTRADOS en espera!" << endl << endl;
		C.blanco();
    }
}

void Gestor::borrar_clientes_no_registrados(bool texto)
{
	if (cantidad_no_registrados() == NO_REGISTRADOS)
		no_registrados -= NO_REGISTRADOS;

	no_registrado_en_espera.vaciar();
		
	if (texto)
	{
		C.colorear(MORADO);
		cout << endl << "\tClientes NO REGISTRADOS borrados!" << endl << endl;
		C.blanco();
    }
}

void Gestor::simular_venta_a_clientes_VIP()
{
	int tiempo = -1;
	int tiempo_en_proceso = 0;
	
	if(VIP_en_espera.cantidad_de_clientes()>0)
	{
		Cliente c;
		
		do
		{
			if (servidor_VIP.cantidad_de_clientes()>0)
			{
				if (servidor_VIP.ver_primero().get_tiempo_de_compra() == (tiempo_en_proceso + 1))
				{
					tiempo_en_proceso = 0;
					c = servidor_VIP.eliminar();
					//c.generar_identificador_de_entrada();
					c.set_tiempo_de_procesamiento(tiempo);
					clientes_terminados.insertar(c);
				}
				else
					tiempo_en_proceso++;
			}
			
			while((VIP_en_espera.cantidad_de_clientes() > 0) and (VIP_en_espera.ver_primero().get_hora_de_inicio_de_compra() == tiempo))
				servidor_VIP.insertar(VIP_en_espera.eliminar());
				
			actualizar_pantalla_VIP(tiempo, tiempo_en_proceso);
			
			tiempo ++;
			
			#if WINDOWS
				Sleep(TIEMPO);
				system("cls");
			#else
				usleep(TIEMPO);
				cout << "\033c";
			#endif
			
		}while(servidor_VIP.cantidad_de_clientes() > 0 or VIP_en_espera.cantidad_de_clientes() > 0);
		
		C.colorear(VERDE);
		cout << "\t\tVENTA DE ENTRADAS VIP TERMINADA" << endl;
		actualizar_pantalla_VIP(tiempo, tiempo_en_proceso);
		C.blanco();
	}
	else
    {
		C.colorear(ROJO);
		cout << endl << "\tNo hay clientes VIP" << endl << endl;
		C.blanco();
    }
}

void Gestor::actualizar_pantalla_VIP(int tiempo, int tiempo_en_proceso)
{
	C.colorear(AMARILLO);
	cout << endl <<"\t\t******************************" << endl;
	C.colorear(ROJO);
	cout << "\t\t       Tiempo:";
	C.colorear(AMARILLO);
	show_hora(tiempo);
	C.colorear(AMARILLO);
	cout << endl;
	cout << "\t\t******************************" << endl<<endl;
	cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
	
	C.colorear(MORADO);
	cout << "\tClientes en espera: " << "\t\t\t";C.colorear(AMARILLO); cout << VIP_en_espera.cantidad_de_clientes() << endl << endl;
	
	C.colorear(GRIS);
	VIP_en_espera.mostrar();
	
	C.colorear(AMARILLO);
	cout << endl <<"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
	
	C.colorear(AZUL);
	cout << "\tServidor VIP: " << "\t\t\t"<< " Tiempo en proceso: ";C.colorear(AMARILLO);cout <<  tiempo_en_proceso << endl << endl;
	
	C.blanco();
	servidor_VIP.mostrar();
	
	C.colorear(AMARILLO);
	cout << "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
	
	C.colorear(VERDE);
	cout << "\tEntradas vendidas: "<< "\t\t\t";C.colorear(AMARILLO);cout << clientes_terminados.cantidad_de_clientes() << endl << endl;
	
	C.colorear(GRIS);
	clientes_terminados.mostrar();
	
	C.colorear(AMARILLO);
	cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
	C.blanco();
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
					if (servidor_no_registrado[x].ver_primero().get_tiempo_de_compra() == (tiempo_en_proceso[x] + 1))
					{
						tiempo_en_proceso[x] = 0;
						c = servidor_no_registrado[x].eliminar();
						c.generar_identificador_de_entrada();
						c.set_tiempo_de_procesamiento(tiempo);
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
            
			tiempo ++;
			
			#if WINDOWS
				Sleep(TIEMPO);
				system("cls");
			#else
				usleep(TIEMPO);
				cout << "\033c";
			#endif
            
		}while(recuento_no_registrados() > 0 or no_registrado_en_espera.cantidad_de_clientes() > 0);
		
		C.colorear(VERDE);
		cout << "\t\tVENTA DE ENTRADAS NO REGISTRADOS TERMINADA" << endl;
		C.blanco();
        actualizar_pantalla_no_registrado(tiempo, tiempo_en_proceso);
	}
	else
    {
		C.colorear(ROJO);
		cout << endl << "\tNo hay clientes NO_REGISTRADOS" << endl << endl;
		C.blanco();
    }
}

void Gestor::actualizar_pantalla_no_registrado(int tiempo, int * tiempo_en_proceso)
{
	C.colorear(AMARILLO);
	cout << endl <<"\t\t******************************" << endl;
	C.colorear(ROJO);
	cout << "\t\t       Tiempo:";
	C.colorear(AMARILLO);
	show_hora(tiempo);
	cout << endl;
	C.colorear(AMARILLO);
	cout << "\t\t******************************" << endl<<endl;
	cout << endl <<"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;

	C.colorear(MORADO);
	cout << "\tClientes en espera: " << "\t\t\t"; C.colorear(AMARILLO); cout << no_registrado_en_espera.cantidad_de_clientes() << endl << endl;

	C.colorear(GRIS);
	no_registrado_en_espera.mostrar();
    
	for (int x = 0; x < SERVIDORES_NO_REGISTRADOS; x++)
	{
		C.colorear(AMARILLO);
		cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
		C.colorear(AZUL);
		cout << "\tServidor: "<< x << "\t\t\tTiempo en proceso: "; C.colorear(AMARILLO); cout << tiempo_en_proceso[x] << endl << endl;
		
		C.blanco();
		servidor_no_registrado[x].mostrar();
    }
	C.colorear(AMARILLO);
	cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;

	C.colorear(VERDE);
	cout << "\tEntradas vendidas: "<< "\t\t\t"; C.colorear(AMARILLO); cout << clientes_terminados.cantidad_de_clientes() << endl << endl;

	C.colorear(GRIS);
	clientes_terminados.mostrar();
	C.colorear(AMARILLO);
	cout <<"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"<< endl;
}

void Gestor::mostrar_entradas_vendidas()
{
	if(clientes_terminados.cantidad_de_clientes())
	{
		C.colorear(VERDE);
		cout << endl << "\tEntradas vendidas: " << endl << endl;
		C.colorear(GRIS);
		clientes_terminados.mostrar();
		C.blanco();
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tNo hay ENTRADAS vendidas" << endl;
		C.blanco();
	}
}

void Gestor::generar_arbol()
{
	if (clientes_terminados.es_vacia())
	{
		int numero_clientes = clientes_terminados.cantidad_de_clientes();
		arbol = new Arbol( new NodoArbol (Cliente(-1,0)));
		
		Cliente c [numero_clientes];
		clientes_terminados.duplicar(c, numero_clientes);
		arbol->generar(c,numero_clientes);
		
		C.colorear(VERDE);
		cout << endl << "\tArbol generado!" << endl << endl;
		C.blanco();
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tNO HAY ENTRADAS VENDIDAS" << endl << endl;
		C.blanco();
	}
}

void Gestor::buscar_cliente()
{
	if (arbol)
	{
		char DNI [LEN_DNI];
		pedir_DNI_validado(DNI);
		
		if (!strcmp(DNI,"xxxxxxxxxx"))
		{
			C.colorear(ROJO);
			cout << "\t\tDNI ERRONEO!" << endl << endl;
			C.blanco();
		}
		else
		{
			Cliente c = arbol->buscar(DNI);

			if (c.get_hora_de_inicio_de_compra() < 0)
			{
				C.colorear(MORADO);
				cout << endl<<endl << "\t\tCLIENTE NO EXISTE!" << endl << endl;
				C.blanco();
			}
			else
			{
				C.colorear(VERDE);
				cout << endl << "\t\tSu cliente es:" << endl;
				c.show_cliente_completo();
				C.blanco();
				cout << endl << endl;
			}
		}
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tARBOL VACIO" << endl << endl;
		C.blanco();
	}
}

void Gestor::mostrar_arbol_en_post_orden()
{
	if(arbol)
	{
		C.colorear(VERDE);
		cout << endl << "\t\tPOST_ORDEN:";
		C.colorear(GRIS);
		arbol->show_post_orden();
		C.blanco();
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tARBOL VACIO" << endl << endl;
		C.blanco();
	}
}

void Gestor::mostrar_arbol_en_pre_orden()
{
	if(arbol)
	{
		C.colorear(VERDE);
		cout << endl << "\t\tPRE_ORDEN:";
		C.colorear(GRIS);
		arbol->show_pre_orden();
		C.blanco();
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tARBOL VACIO" << endl << endl;
		C.blanco();
	}
}

void Gestor::mostrar_arbol_en_in_orden()
{
	if(arbol)
	{
		C.colorear(VERDE);
		cout << endl << "\t\tIN_ORDEN:";
		C.colorear(GRIS);
		arbol->show_in_orden();
		C.blanco();
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tARBOL VACIO" << endl << endl;
		C.blanco();
	}
}

void Gestor::dibujar_arbol()
{
	if(arbol)
	{
		C.colorear(VERDE);
		cout << "\n\t\tARBOL:";
		C.colorear(GRIS);
		arbol->draw();
		C.blanco();
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tARBOL VACIO" << endl << endl;
		C.blanco();
	}
}

void Gestor::reiniciar()
{
	vip = 0;
	no_registrados = 0;
	borrar_clientes_VIP(true);
	borrar_clientes_no_registrados(true);
	
	C.colorear(MORADO);
	servidor_VIP.vaciar();
	
	for (int x = 0; x < SERVIDORES_NO_REGISTRADOS; x++)
		servidor_no_registrado[x].vaciar();
			
	clientes_terminados.vaciar();
		
	
	cout << endl << "\tClientes LISTA DE ENTRADAS borrada!" << endl << endl;
	
	arbol->vaciar();
	arbol = NULL;
	cout << endl << "\tClientes ARBOL borrado!" << endl << endl;
	
	C.colorear(VERDE);
	cout << endl << "\tPrograma reiniciado!" << endl << endl;
	C.blanco();
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

void Gestor::pedir_DNI_validado(char* DNI)
{
	cout << endl << endl << "\t\tIntroduce el DNI que quieres buscar: ";
	cin.ignore();
	cin.getline(DNI,LEN_DNI);
	
	if (!validar_formato(DNI))
		strcpy(DNI,"xxxxxxxxxx");
}

bool Gestor::validar_formato(char* DNI)
{
	return strlen(DNI) == DNI_FIN and validar_numeros(DNI) and validar_letra(DNI) and DNI[DNI_GUION] == '-';
}

bool Gestor::validar_letra(char* DNI)
{
	char letra [23] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
	long dni = 0;
	
	for (int x = 0; x < DNI_GUION; x++)
		dni+=(DNI[DNI_GUION-x-1] - 48)*pow(10,x);

	dni %=23;
	
	return letra[dni] == DNI[DNI_LETRA];
}

bool Gestor::validar_numeros(char* DNI)
{
	bool valido = true;
	
	for (int x = 0; x < DNI_GUION; x++)
	{
		if(DNI[x] - 48 > 9)
			valido = false;
	}
	
	return valido;
}

bool Gestor::estado_arbol()
{
	return arbol;
}

int Gestor::recuento_VIP_total()
{
	return vip;
}

int Gestor::cantidad_no_registrados_total()
{
	return no_registrados;
}

void Gestor::estadisticas()
{
	if(arbol)
	{
		arbol->generar_estadisticas();
		float media_procesamiento = 0.0;
		float media_estancia = 0.0;
		C.colorear(VERDE);
		cout << "\n\t\tESTADISTICAS:";
		C.colorear(MORADO);
		
		media_estancia = (float) arbol->get_tiempo_de_estancia()/(float) clientes_terminados.cantidad_de_clientes();
		media_procesamiento = (float)arbol->get_tiempo_de_procesamiento() / (float)clientes_terminados.cantidad_de_clientes();
		
		cout << "\t" << media_procesamiento << " segundos";
		cout << "\n\t\t\t" << media_estancia << " segundos";
		cout << "\n\t\tMEJORA: \t" << media_procesamiento-media_estancia << " segundos";
		C.blanco();
	}
	else
	{
		C.colorear(ROJO);
		cout << endl << "\tARBOL VACIO" << endl << endl;
		C.blanco();
	}
}

void Gestor::generar()
{
	int tiempo_cliente_anterior = 0;
	if (recuento_VIP() == 0)
		vip+=VIP;
		
	borrar_clientes_VIP(false);
	
	char dni[VIP][LEN_DNI];
	char i[VIP][LEN_IDENTIFICADOR];
	int inicio[VIP] = {0,5,8,12,15,18,18,19,24,26,30,33,34,35,40};
	int duracion[VIP] = {2,10,8,5,10,2,2,6,9,2,8,6,8,3,9};
	
	memcpy (dni[0], "77943364-K", sizeof(dni[0]));
	memcpy (dni[1], "11294153-A", sizeof(dni[0]));
	memcpy (dni[2], "64800905-S", sizeof(dni[0]));
	memcpy (dni[3], "16595686-J", sizeof(dni[0]));
	memcpy (dni[4], "56268767_A", sizeof(dni[0]));
	memcpy (dni[5], "45788556-H", sizeof(dni[0]));
	memcpy (dni[6], "08447839-P", sizeof(dni[0]));
	memcpy (dni[7], "40153000-Z", sizeof(dni[0]));
	memcpy (dni[8], "36983201-Y", sizeof(dni[0]));
	memcpy (dni[9], "69561270-R", sizeof(dni[0]));
	memcpy (dni[10], "67273567-Q", sizeof(dni[0]));
	memcpy (dni[11], "79606886-E", sizeof(dni[0]));
	memcpy (dni[12], "44538570-J", sizeof(dni[0]));
	memcpy (dni[13], "97221619-K", sizeof(dni[0]));
	memcpy (dni[14], "87595662-R", sizeof(dni[0]));
	
	memcpy (i[0], "CQZG", sizeof(i[0]));
	memcpy (i[1], "OZKF", sizeof(i[0]));
	memcpy (i[2], "VNAU", sizeof(i[0]));
	memcpy (i[3], "JSAY", sizeof(i[0]));
	memcpy (i[4], "JNXU", sizeof(i[0]));
	memcpy (i[5], "CMPJ", sizeof(i[0]));
	memcpy (i[6], "HAHI", sizeof(i[0]));
	memcpy (i[7], "REZJ", sizeof(i[0]));
	memcpy (i[8], "FOHF", sizeof(i[0]));
	memcpy (i[9], "GKJF", sizeof(i[0]));
	memcpy (i[10], "OVEQ", sizeof(i[0]));
	memcpy (i[11], "EGGO", sizeof(i[0]));
	memcpy (i[12], "LTMU", sizeof(i[0]));
	memcpy (i[13], "TXEC", sizeof(i[0]));
	memcpy (i[14], "BAAA", sizeof(i[0]));
	
	
	for(int x = 0; x < VIP; x++)
	{
		VIP_en_espera.insertar(Cliente (tiempo_cliente_anterior,true,dni[x],inicio[x],duracion[x],i[x]));
		tiempo_cliente_anterior += rand()%6;
	}
	
	C.colorear(VERDE);
	cout << endl << "\tClientes VIP generados!" << endl << endl;
	C.blanco();
}