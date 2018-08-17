#ifndef COLORES_HPP
#define COLORES_HPP

#include <iostream>
#define WINDOWS 0
#if WINDOWS
#include "windows.h"
#endif

#define ROJO 'R'
#define VERDE 'V'
#define AMARILLO 'A'
#define AZUL 'Z'
#define GRIS 'G'
#define MORADO 'M'
#define BLANCO 'B'

using namespace std;

class Colores
{
	public:
		Colores();
		~Colores();
		void colorear(char color);
		void blanco(void);
	private:
		#if WINDOWS
			HANDLE hConsole;
		#endif

};

#endif // COLORES_HPP
