#include "Colores.h"

void colorear (char color)
{
	#if WINDOWS
	hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	switch(color)
	{
		case ROJO:
			SetConsoleTextAttribute(hConsole,12);
		break;
		case VERDE:
			SetConsoleTextAttribute(hConsole,10);
		break;
		case AZUL:
			SetConsoleTextAttribute(hConsole,9);
		break;
		case AMARILLO:
			SetConsoleTextAttribute(hConsole,14);
		break;
		case GRIS:
			SetConsoleTextAttribute(hConsole,8);
		break;
		case MORADO:
			SetConsoleTextAttribute(hConsole,5);
		break;
	}
	#else
	switch(color)
	{
		case ROJO:
			cout << "\033[91m";
		break;
		case VERDE:
			cout << "\033[92m";
		break;
		case AZUL:
			cout << "\033[94m";
		break;
		case AMARILLO:
			cout << "\033[93m";
		break;
		case GRIS:
			cout << "\033[37m";
		break;
		case MORADO:
			cout << "\033[35m";
		break;
	}
	#endif
}

void blanco ()
{
	#if WINDOWS
		hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
		SetConsoleTextAttribute(hConsole,15);
	#else
		cout << "\033[0m";
	#endif
}
