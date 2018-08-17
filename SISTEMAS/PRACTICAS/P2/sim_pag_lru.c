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

	//LRU
	S->tdp[pagina].timestamp = S->reloj;			//Da a la página el valor del reloj del sistema
	S->reloj++;						//Al desbordarse el reloj se pone a 0 ya que es un unsigned
	if(S->reloj == 0)					//Si el reloj se desborda lo notificamos
		printf("@ ERROR: Desbordamiento de reloj!!!");

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
    int marco_definitivo, victima, menor;

	//LRU
    	menor = S->tdp[S->tdm[0].pagina].timestamp;					//Se reemplazará la página con menor timestamp
	for(int marco_prueba = 0; marco_prueba < S->nummarcos; marco_prueba++)		//la página con menor timestamp es la que hace más tiempo que no se referencia
	{
		if(S->tdp[S->tdm[marco_prueba].pagina].timestamp <= menor)
		{
			menor = S->tdp[S->tdm[marco_prueba].pagina].timestamp;
			marco_definitivo = marco_prueba;
		}
	}

    victima = S->tdm[marco_definitivo].pagina;

    if (S->detallado)
        printf ("@ Eligiendo (LRU) P%d de M%d para "
                "reemplazarla\n", victima, marco_definitivo);

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
    if (S->detallado)
        printf ("@ Alojando P%d en M%d\n", pagina, marco);

	//Parte 3
	S->tdp[pagina].marco = marco;
	S->tdp[pagina].presente = 1;
	S->tdm[marco].pagina = pagina;
	S->tdp[pagina].modificada = 0;
}

// Funciones que muestran resultados

void mostrar_tabla_de_paginas (ssistema * S)
{
    int p;

    printf ("%10s %10s %10s %10s   %s\n",
            "PÁGINA", "Presente", "Marco", "Modificada", "timestap");

    for (p=0; p<S->numpags; p++)
        if (S->tdp[p].presente)
            printf ("%8d   %6d     %8d   %6d    %6d\n", p,		//Mostramos el valor de timestamp de cada página
                    S->tdp[p].presente, S->tdp[p].marco,
                    S->tdp[p].modificada, S->tdp[p].timestamp);
        else
            printf ("%8d   %6d     %8s   %6s    %6s\n", p,
                    S->tdp[p].presente, "-", "-","-");
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
	//LRU
	unsigned int menor = S->tdp[S->tdm[0].pagina].timestamp, mayor =0;
        for(int i = 0; i < S->nummarcos; i++)					//Buscamos los valore mínimo y máximo de timestamp para mostrarlos
        {
                if(S->tdp[S->tdm[i].pagina].timestamp < menor)
                        menor = S->tdp[S->tdm[i].pagina].timestamp;
		else if (S->tdp[S->tdm[i].pagina].timestamp > mayor)
			mayor = S->tdp[S->tdm[i].pagina].timestamp;
        }

	printf("Valor del reloj: %6d\nValor maximo del reloj: %6d\nValor minimo del reloj: %6d\n",S->reloj,mayor,menor);

    printf ("Reemplazo lru "
            "(no hay info. específica)\n");      // <<--- lru
}


