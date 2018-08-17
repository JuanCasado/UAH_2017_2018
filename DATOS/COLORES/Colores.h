#ifndef COLORES_HPP
#define COLORES_HPP

//Puede que necesites incluir:
//#include <stdio.h>
//#include <iostream>

#define WINDOWS 0
#if WINDOWS
#include "windows.h"
HANDLE hConsole;
#endif

#define ROJO 'R'
#define VERDE 'V'
#define AMARILLO 'A'
#define AZUL 'Z'
#define GRIS 'G'
#define MORADO 'M'
#define BLANCO 'B'

void colorear (char color);
void blanco (void);

#endif // COLORES_HPP
