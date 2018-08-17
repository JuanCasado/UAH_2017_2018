#include "Cliente.hpp"

Cliente::Cliente()
{
}

Cliente::Cliente(int t, bool r)
{
	this->hora_de_inicio_de_compra = t;
	this->registrado = r;
	
	generar_DNI();
	generar_hora_de_inicio_de_compra();
	generar_tiempo_de_compra();
}

//DNI
void Cliente::generar_DNI()
{
	char letra [23] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
	long dni = 0;
	
	
	for (int x = 0; x < 8; x++)
	{
		DNI[x]=rand()%10;
		dni+=DNI[x]*pow(10,x);
	}
	dni %=23;
	DNI[8] = letra [dni];
}
void Cliente::show_DNI()
{
	for (int x = 0; x < 8; x++)
		cout << (char)(DNI[x]+48);
	cout << '-' << DNI[8];
}

//HORA DE INICIO DE COMPRA
void Cliente::generar_hora_de_inicio_de_compra()
{
	if (!registrado)
		hora_de_inicio_de_compra += HORA_DE_INICIO_NO_REGISTRADOS;
}
void Cliente::show_hora_de_inicio_de_compra()
{
	int hora = hora_de_inicio_de_compra/60, minutos = hora_de_inicio_de_compra%60;
	if (hora < 10) 
		cout << '0';
	cout << hora << ':';
	if (minutos < 10)
		cout << '0';
	 cout << minutos;
}
int Cliente::get_hora_de_inicio_de_compra()
{
	return hora_de_inicio_de_compra;
}

//TIEMPO DE COMPRA
void Cliente::generar_tiempo_de_compra()
{
	tiempo_de_compra = 1 + rand()%TIEMPO_MAXIMO_DE_COMPRA;
}
void Cliente::show_tiempo_de_compra()
{
	cout << tiempo_de_compra;
	if (tiempo_de_compra < 10)
		cout << " ";
}
int Cliente::get_tiempo_de_compra()
{
	return tiempo_de_compra;
}

//CLIENTE
void Cliente::show_cliente()
{
	cout << "\t\tDNI: ";
	show_DNI();
	cout << "    -    ";
	cout << "Hora de inicio de compra: ";
	show_hora_de_inicio_de_compra();
	cout << "    -    ";
	cout << "Tiempo de compra: ";
	show_tiempo_de_compra();
	cout << '\n';
}

//IDENTIFICADOR DE ENTRADA
void Cliente::generar_identificador_de_entrada()
{
	for(int x = 0; x < 4; x++)
		identificador_de_entrada[x] = rand()%26 + 65;
		
	numero_de_entrada = transformar_identificador_de_entrada();
}
void Cliente::show_identificador_de_entrada()
{
	for(int x = 0; x < 4; x++)
		cout << identificador_de_entrada[x];
}
long Cliente::transformar_identificador_de_entrada()
{
	long acumulador = 0;
	for (int x = 0; x < 4; x++)
		acumulador += (identificador_de_entrada[x])*pow(100,(3-x));
	
	return acumulador;
}

long Cliente::get_identificador_de_entrada()
{
	return numero_de_entrada;
}

void Cliente::show_cliente_completo()
{
	cout << "\t\tDNI: ";
	show_DNI();
	cout << "    -    ";
	cout << "Hora de inicio de compra: ";
	show_hora_de_inicio_de_compra();
	cout << "    -    ";
	cout << "Tiempo de compra: ";
	show_tiempo_de_compra();
	cout << "    -    ";
	cout << "Identificador de entrada: ";
	show_identificador_de_entrada();
	cout << '\n';
}

Cliente::~Cliente()
{
	
}