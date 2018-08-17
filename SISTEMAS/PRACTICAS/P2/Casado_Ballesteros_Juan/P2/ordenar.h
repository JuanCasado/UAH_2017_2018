/*
    ordenar.h
*/

#ifndef _ORDENAR_H_
#define _ORDENAR_H_

#include <stdio.h>

// Tipo de los datos que manejarán nuestros algoritmos:

typedef double cosa;

// Tipo de función que comparará esos datos:

typedef int funcion_menor (void *, cosa, cosa);

// Tipos de función que deberán usar nuestros algoritmos para
// acceder a los datos:

typedef cosa funcion_leer (void *, unsigned pos);
typedef void funcion_escribir (void *, unsigned pos, cosa valor);

// Tipo de función que implementará nuestros algoritmos:

typedef unsigned funcion_ordenar (void *, unsigned tam,
                                  funcion_menor * pmenor,
                                  funcion_leer * pleer,
                                  funcion_escribir * pescr);

// Declaración de las distinas funciones "ordenar":

funcion_ordenar bubble_sort, insertion_sort, selection_sort,
                heap_sort, comb_sort, merge_sort,
                quick_sort, quick_sort_pa;

#endif

