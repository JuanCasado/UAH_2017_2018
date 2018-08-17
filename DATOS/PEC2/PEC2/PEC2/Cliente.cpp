#include "Cliente.hpp"

Cliente::Cliente()
{
}

Cliente::Cliente(int t, bool r)
{
	this->hora_de_inicio_de_compra = t;
	this->registrado = r;
	
	
	
	if (t >= 0)
	{

		generar_DNI();
		tiempo_de_procesamiento = 0;
		generar_hora_de_inicio_de_compra();
		generar_tiempo_de_compra();
	}
	else
	{
		set_DNI();
		tiempo_de_procesamiento = 0;
		tiempo_de_compra = 0;
		hora_de_inicio_de_compra = 0;
	}
}

Cliente::Cliente(int t, bool r, char* dni, int inicio, int duracion, char* i)
{
	this->hora_de_inicio_de_compra = t;
	this->registrado = r;
	
	
	
	if (t >= 0)
	{
		set_DNI_2(dni);
		tiempo_de_procesamiento = 0;
		tiempo_de_compra = duracion;
		hora_de_inicio_de_compra = inicio;
		memcpy (identificador_de_entrada, i, sizeof(identificador_de_entrada));
	}
	else
	{
		set_DNI();
		tiempo_de_procesamiento = 0;
		tiempo_de_compra = 0;
		hora_de_inicio_de_compra = 0;
	}
}

//DNI
void Cliente::set_DNI_2(char *dni)
{
	memcpy (DNI, dni, sizeof(DNI));
}
void Cliente::set_DNI()
{
	char dni [LEN_DNI] = {'4','9','9','9','9','9','9','9','-','M'};
	for (int x = 0; x < LEN_DNI; x++)
		DNI[x] = dni [x];
}
void Cliente::generar_DNI()
{
	char letra [23] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
	long dni = 0;
	
	
	for (int x = 0; x < (DNI_GUION); x++)
	{
		DNI[DNI_GUION-1-x]=(rand()%10);
		dni+=DNI[DNI_GUION-1-x]*pow(10,x);
		DNI[DNI_GUION-1-x] += 48;
	}
	dni %=23;
	DNI[DNI_GUION] = '-';
	DNI[DNI_LETRA] = letra [dni];
	DNI[DNI_FIN] = '\0';
}
void Cliente::show_DNI()
{
	cout << DNI;
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

void Cliente::show_tipo()
{
	if (registrado)
		cout << "VIP";
	else
		cout << "NO REGISTRADO";
}

//TIEMPO DE PROCESAMIENTO
void Cliente::set_tiempo_de_procesamiento(int hora_de_salida)
{
	tiempo_de_procesamiento = hora_de_salida - hora_de_inicio_de_compra;
}
int Cliente::get_tiempo_de_procesamiento()
{
	return tiempo_de_procesamiento;
}
void Cliente::show_tiempo_de_procesamiento()
{
	cout << tiempo_de_procesamiento;
}

//CLIENTE
void Cliente::show_cliente()
{
	Colores C;
	C.colorear(GRIS);
	cout << "\t\tDNI: ";
	C.blanco();
	show_DNI();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(GRIS);
	cout << "Hora de inicio de compra: ";
	C.colorear(AMARILLO);
	show_hora_de_inicio_de_compra();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(GRIS);
	cout << "Tiempo de compra: ";
	C.colorear(AMARILLO);
	show_tiempo_de_compra();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(ROJO);
	show_tipo();
	C.blanco();
	cout << endl;
}

//IDENTIFICADOR DE ENTRADA
void Cliente::generar_identificador_de_entrada()
{
	for(int x = 0; x < (LEN_IDENTIFICADOR-1); x++)
		identificador_de_entrada[x] = rand()%26 + 65;
		
	identificador_de_entrada[LEN_IDENTIFICADOR-1] = '\0';
}
void Cliente::show_identificador_de_entrada()
{
	cout << identificador_de_entrada;
}

char* Cliente::get_identificador_de_entrada()
{
	return identificador_de_entrada;
}

void Cliente::show_cliente_completo()
{
	Colores C;
	C.colorear(GRIS);
	cout << "\t\tDNI: ";
	C.blanco();
	show_DNI();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(GRIS);
	cout << "Hora de inicio de compra: ";
	C.colorear(AMARILLO);
	show_hora_de_inicio_de_compra();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(GRIS);
	cout << "Tiempo de compra: ";
	C.colorear(AMARILLO);
	show_tiempo_de_compra();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(GRIS);
	cout << "Identificador de entrada: ";
	C.colorear(VERDE);
	show_identificador_de_entrada();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(ROJO);
	show_tipo();
	C.colorear(MORADO);
	cout << "    -    ";
	show_tiempo_de_procesamiento();
	C.blanco();
	cout << endl;
}

void Cliente::show_cliente_completo_formato_reducido()
{
	Colores C;
	C.colorear(AMARILLO);
	cout << "DNI: ";
	C.blanco();
	show_DNI();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(GRIS);
	cout << "Hora de inicio de compra: ";
	C.colorear(AMARILLO);
	show_hora_de_inicio_de_compra();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(GRIS);
	cout << "Tiempo de compra: ";
	C.colorear(AMARILLO);
	show_tiempo_de_compra();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(GRIS);
	cout << "Identificador de entrada: ";
	C.colorear(VERDE);
	show_identificador_de_entrada();
	C.colorear(MORADO);
	cout << "    -    ";
	C.colorear(ROJO);
	show_tipo();
	C.colorear(MORADO);
	cout << "    -    ";
	show_tiempo_de_procesamiento();
	C.blanco();
	C.blanco();
	cout << endl;
}

char* Cliente::get_DNI()
{
	return DNI;
}

Cliente::~Cliente()
{
	
}
