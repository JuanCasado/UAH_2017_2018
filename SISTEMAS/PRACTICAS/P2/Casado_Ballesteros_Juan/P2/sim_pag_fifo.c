/*
    sim_pag_aleatorio.c
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "sim_paginacion.h"

// Función que inicia las tablas

void iniciar_tablas (ssistema * S)
{
    int i;

    // Páginas a cero
    memset (S->tdp, 0, sizeof(spagina)*S->numpags);

    // Pila LRU vacía
    S->lru = -1;

    // Tiempo LRU(t) a cero
    S->reloj = 0;

    // Lista circular de marcos libres
    for (i=0; i<S->nummarcos-1; i++)
    {
        S->tdm[i].pagina = -1;
        S->tdm[i].sig = i+1;
    }

    S->tdm[i].pagina = -1;  // Ahora i == nummarcos-1
    S->tdm[i].sig = 0;      // Cerrar lista circular
    S->listalibres = i;     // Apuntar al último

    // Lista circular de marcos ocupados vacía
    S->listaocupados = -1;
}

// Funciones que simulan el hardware de la MMU

unsigned sim_mmu (ssistema * S, unsigned dir_virtual, char op)
{
    unsigned dir_fisica;
    int pagina, marco, desplazamiento;

	//PARTE 1

	pagina = dir_virtual / S->tampag; 		//Cociente
	desplazamiento = dir_virtual % S->tampag; 	//Resto

	if ((pagina < 0)||(pagina >= S->numpags))
	{
		S->numrefsilegales++;
		return ~0U;				//Devolver dir fisica FFF...F
	}

	if(!S->tdp[pagina].presente)			//NO prensente
		tratar_fallo_de_pagina(S, dir_virtual);

	//La página estará presente
	marco = S->tdp[pagina].marco;			//Cálculo de la dir fisica
	dir_fisica = marco*S->tampag+desplazamiento;
	referenciar_pagina(S,pagina,op);

	if(S->detallado)
		printf("\t%c %u==P%d(M%d)+%d\n",op,dir_virtual,pagina,marco,desplazamiento);

    return dir_fisica;
}

void referenciar_pagina (ssistema * S, int pagina, char op)
{
    if (op=='L')                         // Si es una lectura,
        S->numrefslectura ++;            // contarla
    else if (op=='E')
    {                                    // Si es una escritura,
        S->tdp[pagina].modificada = 1;   // contarla y marcar la
        S->numrefsescritura ++;          // página como modificada
    }
}

// Funciones que simulan el sistema operativo

void tratar_fallo_de_pagina (ssistema * S, unsigned dir_virtual)
{
    int pagina, victima, marco, ult;

	//Parte 2

	S->numfallospag++;
	pagina = dir_virtual / S-> tampag ;
	if (S-> detallado )
		printf ("@ !FALLO DE PÁGINA en P %d!\n", pagina );

	if (S-> listalibres != -1) 					// Si hay marcos libres :
	{
		ult = S->listalibres; 					// Último de la lista
		marco = S->tdm[ult].sig; 				// Tomar el sig (el 1º )

		if ( marco == ult ) 					// Si son el mismo, es que solo quedaba uno libre
			S->listalibres = -1;
		else
			S->tdm[ult].sig = S->tdm[marco].sig; 		// Si no, puentear

		ocupar_marco_libre (S, marco, pagina );
	}
	else								// Si no hay marcos libres
	{
		victima = elegir_pagina_para_reemplazo (S);
		reemplazar_pagina (S, victima , pagina );
	}

}


int elegir_pagina_para_reemplazo (ssistema * S)
{
    int marco, victima;

	//FIFO
	marco = S-> tdm[S->listaocupados].sig;		//la victima es el primer elemento de la lista (el siguiente al último)
	victima = S->tdm[marco].pagina;			//Tomo la página que se corresponde con el marco elegido
	S->listaocupados = marco;			//actualizo lista ocupados para que ahora el marco elegido sea el útimo (lista ocupados marca cual es el último)

    if (S->detallado)
        printf ("@ Eligiendo (fifo) P%d de M%d para "
                "reemplazarla\n", victima, marco);

    return victima;
}

void reemplazar_pagina (ssistema * S, int victima, int nueva)
{
    int marco;

    marco = S->tdp[victima].marco;

    if (S->tdp[victima].modificada)
    {
        if (S->detallado)
            printf ("@ Volcando P%d modificada a disco para "
                    "reemplazarla\n", victima);

        S->numescrpag ++;
    }

    if (S->detallado)
        printf ("@ Reemplazando víctima P%d por P%d en M%d\n",
                victima, nueva, marco);

    S->tdp[victima].presente = 0;

    S->tdp[nueva].presente = 1;
    S->tdp[nueva].marco = marco;
    S->tdp[nueva].modificada = 0;

    S->tdm[marco].pagina = nueva;
}

void ocupar_marco_libre (ssistema * S, int marco, int pagina)
{
	int marco_auxiliar = 0; //fifo
    if (S->detallado)
        printf ("@ Alojando P%d en M%d\n", pagina, marco);

	//Parte 3
	S->tdp[pagina].marco = marco;
	S->tdp[pagina].presente = 1;
	S->tdm[marco].pagina = pagina;
	S->tdp[pagina].modificada = 0;

	//FIFO
	if (S->listaocupados == -1)				//Caso en el que la lista está vacía
		S->tdm[marco].sig = marco;			//Añado el marco a la lista
	else							//Caso en el que la lista está ocupada
	{
		marco_auxiliar = S->tdm[S->listaocupados].sig;	//Utilizo un auxiliar para introducir el nuevo marco entre el primero y el último, es decir al final de la lista
		S->tdm[marco].sig = marco_auxiliar;		//Lista ocupados apunta siempre al último elemento añadido luego el siguiente al último es el primero pues la lista es circular
		S->tdm[S->listaocupados].sig = marco;
	}
	S->listaocupados = marco;				//Hago apuntar lista ocupados al nuevo elemento añadido
}

// Funciones que muestran resultados

void mostrar_tabla_de_paginas (ssistema * S)
{
    int p;

    printf ("%10s %10s %10s   %s\n",
            "PÁGINA", "Presente", "Marco", "Modificada");

    for (p=0; p<S->numpags; p++)
        if (S->tdp[p].presente)
            printf ("%8d   %6d     %8d   %6d\n", p,
                    S->tdp[p].presente, S->tdp[p].marco,
                    S->tdp[p].modificada);
        else
            printf ("%8d   %6d     %8s   %6s\n", p,
                    S->tdp[p].presente, "-", "-");
}

void mostrar_tabla_de_marcos (ssistema * S)
{
    int p, m;

    printf ("%10s %10s %10s   %s\n",
            "MARCO", "Página", "Presente", "Modificada");

    for (m=0; m<S->nummarcos; m++)
    {
        p = S->tdm[m].pagina;

        if (p==-1)
            printf ("%8d   %8s   %6s     %6s\n", m, "-", "-", "-");
        else if (S->tdp[p].presente)
            printf ("%8d   %8d   %6d     %6d\n", m, p,
                    S->tdp[p].presente, S->tdp[p].modificada);
        else
            printf ("%8d   %8d   %6d     %6s   ¡ERROR!\n", m, p,
                    S->tdp[p].presente, "-");
    }
}

void mostrar_informe_reemplazo (ssistema * S)
{
	//FIFO
	int presente;
	presente = S->tdm[S->listaocupados].sig;
	while(presente != S-> listaocupados)							//Muestro la lista de marcos ocupados en orden
	{											//Primero muestro todos menos el último
		printf("Pagina: %6d Marco: %6d \n",S->tdm[presente].pagina, presente);
		presente = S->tdm[presente].sig;
	}

	printf("Pagina: %6d Marco: %6d \n",S->tdm[presente].pagina, presente);			//Muestro el último marco que es el que me faltaba

    printf ("Reemplazo fifo "
            "(no hay info. específica)\n");      // <<--- fifo
}


