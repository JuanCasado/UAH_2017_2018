/*
    gen_traza.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "ordenar.h"

// Funciones que preparan los datos a ordenar siguiendo
// diferentes criterios:

typedef void funcion_preparar_datos (cosa A[], unsigned tam);

funcion_preparar_datos orden_ascendente,
                       orden_descendente,
                       orden_aleatorio;

// Funciones que deberán ser utilizadas por los algoritmos
// de ordenación a la hora de acceder a los datos del array:

cosa leer (void *, unsigned pos);
void escribir (void *, unsigned pos, cosa valor);

// Funciones que deberán ser utilizadas por los algoritmos
// de ordenación a la hora de comparar valores del array:

int menor_al_derecho (void *, cosa a, cosa b);
int menor_al_reves (void *, cosa a, cosa b);

// Las funciones leer y escribir reciben como primer parámetro
// un puntero a una estructura de este tipo:

typedef struct
{
    cosa * pdatos;            // Array con los datos a ordenar
    unsigned nlecturas;       // Contador de lecturas
    unsigned nescrituras;     // Contador de escrituras
    unsigned ncomparaciones;  // Contador de comparaciones
    FILE * pf;                // Registro de las operaciones
}
scontrol;

// Estructura que contiene datos relacionados con los parámetros
// pasados por línea de órdenes (algoritmo a emplear etc.)

typedef struct
{
    funcion_preparar_datos * ppreparar;
    funcion_ordenar * pordenar;
    int tam;
}
sparametros;

// Función que interpreta los parámetros recibidos por línea de
// órdenes:

int interpretar_orden (int, char *[], sparametros *);

// Función principal

int main (int argc, char * argv[])
{
    cosa * A;          // Array dinámico de datos a ordenar
    scontrol C;        // Estr. de control de acceso al array
    sparametros P;     // Parámetros
    unsigned tamtotal; // Nº total de elementos (2*tam en MER)
    unsigned u;

    if (interpretar_orden(argc,argv,&P)<0)
        return -1;

    tamtotal = P.pordenar==merge_sort ? P.tam*2 : P.tam;
    A = (cosa*) malloc (tamtotal*sizeof(cosa));

    if (!A)
    {
        fprintf (stderr, "ERROR: no hay suficiente "
                         "memoria dinámica.\n");
        return -2;
    }

    C.pdatos = A;

    // Generar datos según estado inicial especificado
    P.ppreparar (A, P.tam);

    // Resetear contadores
    C.nlecturas = C.nescrituras = C.ncomparaciones = 0;
    C.pf = stdout;

    // Mostrar tamaño total
    printf (" T%u\n", tamtotal);

    // Ordenar datos con algoritmo especificado
    P.pordenar (&C,
                P.tam,
                menor_al_derecho,
                leer,
                escribir);

    C.pf = NULL;

    for (u=0; u<P.tam-1; u++)
        if (menor_al_derecho(&C,A[u+1],A[u]))
            break;

    printf (" %s\n", u<P.tam-1?"Desordenado :-(":"Ordenado ;-)");
    free (A);
    return 0;
}

// Funciones que deberán ser utilizadas por los algoritmos
// de ordenación a la hora de acceder a los datos del array:

cosa leer (void * p, unsigned pos)
{
    scontrol * pc = (scontrol*) p;

    pc->nlecturas ++;

    if (pc->pf)
    {
        fprintf (pc->pf, " L%u", pos);

        if (((pc->nlecturas+
              pc->nescrituras+
              pc->ncomparaciones) & 7) == 0)
            fputc ('\n', pc->pf);
    }

    return pc->pdatos[pos];
}

void escribir (void * p, unsigned pos, cosa valor)
{
    scontrol * pc = (scontrol*) p;

    pc->nescrituras ++;

    if (pc->pf)
    {
        fprintf (pc->pf, " E%u", pos);

        if (((pc->nlecturas+
              pc->nescrituras+
              pc->ncomparaciones) & 7) == 0)
            fputc ('\n', pc->pf);
    }

    pc->pdatos[pos] = valor;
}

// Funciones que deberán ser utilizadas por los algoritmos
// de ordenación a la hora de comparar valores del array:

int menor_al_derecho (void * p, cosa a, cosa b)
{
    scontrol * pc = (scontrol*) p;

    pc->ncomparaciones ++;

    if (pc->pf)
    {
        fprintf (pc->pf, " C");

        if (((pc->nlecturas+
              pc->nescrituras+
              pc->ncomparaciones) & 7) == 0)
            fputc ('\n', pc->pf);
    }

    return a < b;
}

int menor_al_reves (void * p, cosa a, cosa b)
{
    scontrol * pc = (scontrol*) p;

    pc->ncomparaciones ++;

    if (pc->pf)
    {
        fprintf (pc->pf, " C");

        if (((pc->nlecturas+
              pc->nescrituras+
              pc->ncomparaciones) & 7) == 0)
            fputc ('\n', pc->pf);
    }

    return a > b;
}

// Funciones que preparan los datos a ordenar siguiendo
// diferentes criterios:

void orden_ascendente (cosa A[], unsigned tam)
{
    unsigned u;

    for (u=0; u<tam; u++)
        A[u] = u;
}

void orden_descendente (cosa A[], unsigned tam)
{
    unsigned u;

    for (u=0; u<tam; u++)
        A[u] = tam-u-1;
}

void orden_aleatorio (cosa A[], unsigned tam)
{
    unsigned u, n;
    cosa tmp;

    srand (0);

    for (u=0; u<5; u++)
        rand ();

    orden_ascendente (A, tam);

    for (u=0; u<tam-1; u++)
    {
        n = 1 + u + (unsigned)(rand() * (tam-u-1.0) / RAND_MAX);

        if (n>tam-1)
            n = tam-1;

        if (n!=u)
        {
            tmp = A[n];
            A[n] = A[u];
            A[u] = tmp;
        }
    }
}

// Función que interpreta los parámetros recibidos por línea de
// órdenes:

int interpretar_orden (int argc, char * argv[],
                       sparametros * pPar)
{
    unsigned u;

    struct
    {
        funcion_preparar_datos * pfun;
        const char * nombre;
    }
    G[] = { { orden_ascendente, "ASC" },
            { orden_descendente, "DES" },
            { orden_aleatorio, "ALE" },
            { NULL, NULL } };

    struct
    {
        funcion_ordenar * pfun;
        const char * nombre;
    }
    O[] = { { bubble_sort, "BUB" },
            { insertion_sort, "INS" },
            { selection_sort, "SEL" },
            { heap_sort, "HEA" },
            { comb_sort, "COM" },
            { merge_sort, "MER" },
            { quick_sort, "QUI" },
            { quick_sort_pa, "QPA" },
            { NULL, NULL } }	;

    // Parámetros por defecto:
    pPar->ppreparar = orden_aleatorio;
    pPar->pordenar = merge_sort;
    pPar->tam = 4;

    if (argc>1)
    {
        for (u=0; O[u].pfun; u++)
            if (!strcmp(argv[1],O[u].nombre))
                break;

        if (O[u].pfun)
            pPar->pordenar = O[u].pfun;
        else
        {
            fprintf (stderr, "ERROR: No se reconoce el algoritmo "
                             "de ordenación \"%s\"\n", argv[1]);
            return -1;
        }
    }

    if (argc>2)
    {
        for (u=0; G[u].pfun; u++)
            if (!strcmp(argv[2],G[u].nombre))
                break;

        if (G[u].pfun)
            pPar->ppreparar = G[u].pfun;
        else
        {
            fprintf (stderr, "ERROR: No se reconoce el estado "
                             "inicial \"%s\"\n", argv[2]);
            return -1;
        }
    }

    if (argc>3)
    {
        u = sscanf (argv[3], "%d", &pPar->tam);

        if (u!=1 || pPar->tam<2 || pPar->tam>10000)
        {
            fprintf (stderr, "ERROR: Tamaño incorrecto (debe ser "
                             "un número comprendido "
                             "ente 2 y 10000\n");
            return -1;
        }
    }

    return 0;
}


