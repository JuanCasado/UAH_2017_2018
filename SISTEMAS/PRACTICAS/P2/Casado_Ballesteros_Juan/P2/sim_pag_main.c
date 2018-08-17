/*
    sim_pag_main.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "sim_paginacion.h"

// Estructura que contiene datos relacionados con los parámetros
// pasados por línea de órdenes (algoritmo a emplear etc.)

typedef struct
{
    int tampag, nummarcos;
    const char * algoritmo, * estadoinicial;
    int numelem;
    char detallado;
}
sparametros;

// Función que interpreta los parámetros recibidos por línea de
// órdenes:

int interpretar_orden (int, char*[], sparametros*);

// Función principal

int main (int argc, char * argv[])
{
    sparametros P;    // Parámetros recibidos por línea de órdenes
    char orden[100];  // Orden para ejecutar el programa gen_traza
    FILE * tuberia;   // Canal de comunicación con gen_traza
    int ok;           // Bandera
    char op;          // Operación elemental ('L'ectura, 'E'scr...)
    unsigned u;       // Número del elemento leído/escrito
    unsigned numpags; // Número total de páginas
    unsigned totelem; // Número total de elementos (doble en MER)
    ssistema S;       // Estado del sistema simulado (tablas etc.)

    memset (&S, 0, sizeof(S));  // Poner sistema a cero

    if (interpretar_orden(argc,argv,&P)<0)  // Poner parám. en P
        return -1;

    printf ("# Parámetros:  %s %i %i %s %s %i %c\n",
            argv[0], P.tampag, P.nummarcos,
            P.algoritmo, P.estadoinicial, P.numelem,
            P.detallado?'D':'N');

    // Confeccionar orden para invocar a gen_traza
    // (sprintf "imprime" en una cadena)
    sprintf (orden, "./gen_traza %s %s %u",
                    P.algoritmo, P.estadoinicial, P.numelem);

    printf ("# Ejecutando orden:  %s\n", orden);

    // Invocar al programa gen_traza y abrir tubería
    // para leer su salida estándar ("r" de read)
    tuberia = popen (orden, "r");

    if (!tuberia)
    {
        perror ("ERROR al lanzar gen_traza");
        return -1;
    }

    // Leer nº total de elementos a ordenar
    ok = fscanf (tuberia," T %u", &totelem) == 1;

    if (ok)
    {
        // Calcular número total de páginas
        numpags = (totelem+P.tampag-1) / P.tampag; 

        S.tdp = (spagina*) malloc (numpags*sizeof(spagina));
        S.tdm = (smarco*) malloc (P.nummarcos*sizeof(smarco));

        if (!S.tdp || !S.tdm)
        {
            fprintf (stderr,
                     "ERROR: no hay suficiente "
                            "memoria dinámica\n");
            ok = 0;
        }
    }

    if (ok)
    {
        S.tampag = P.tampag;
        S.numpags = numpags;
        S.nummarcos = P.nummarcos;
        S.detallado = P.detallado;

        iniciar_tablas (&S);
    }

    while (ok)
    {
        // Ignorar espacios y leer un carácter
        if (fscanf(tuberia," %c",&op)!=1)
        {
            ok = 0;
            break;
        }

        if (op=='L' || op=='E')              // Si L/E,
        {                                    // tomar nº de
            if (fscanf(tuberia,"%u",&u)!=1)  // elemento
                ok = 0;                      // y anotar
            else
                sim_mmu (&S, u, op);  // Simular acceso a memoria
        }
        else if (op=='O')        // 'O'rdenado -> fin
            break;               // 'C'omparación -> seguir
        else if (op!='C')        // 'D'esordenado (u otra
            ok = 0;              // cosa) -> error
    }

    if (ok)
        mostrar_informe (&S);

    // Esperar a que gen_traza termine y cerrar
    if (pclose(tuberia)==-1)
        ok = 0;

    // Liberar memoria
    free (S.tdp);
    free (S.tdm);

    return ok ? 0 : -1;
}

// Función que muestra los resultados

void mostrar_informe (ssistema * S)
{
    printf ("\n---------- INFORME GENERAL ----------\n\n");

    printf ("Referencias de lectura:   %d\n", S->numrefslectura);
    printf ("Referencias de escritura: %d\n", S->numrefsescritura);
    printf ("Fallos de página:         %d\n", S->numfallospag);
    printf ("Volcados de pág. a disco: %d\n", S->numescrpag);

    if (S->numrefsilegales)
        printf ("\nAVISO: %d REFERENCIAS FUERA DE RANGO\n",
                S->numrefsilegales);
                         
    printf ("\n---------- TABLA DE PÁGINAS ---------\n\n");

    mostrar_tabla_de_paginas (S);

    printf ("\n---------- TABLA DE MARCOS ----------\n\n");

    mostrar_tabla_de_marcos (S);

    printf ("\n--------- INFORME REEMPLAZO ---------\n\n");

    mostrar_informe_reemplazo (S);

    printf ("\n-------------------------------------\n\n");
    printf ("FALLOS DE PÁGINA: --->> %d <<---\n\n",
            S->numfallospag);
}

// Función que interpreta los parámetros recibidos por línea de
// órdenes:

#define ALGORITMOS_VALIDOS "BUB/INS/SEL/HEA/COM/MER/QUI/QPA"
#define ORD_INIC_VALIDOS "ASC/DES/ALE"

int interpretar_orden (int argc, char * argv[], sparametros * p)
{
    int ok;

    // Parámetros por defecto
    p->tampag = 16;
    p->nummarcos = 32;
    p->algoritmo = "MER";
    p->estadoinicial = "ALE";
    p->numelem = 1000;
    p->detallado = 0;

    if (argc>7)
    {
        fprintf (stderr,
                 "\n    ERROR: demasiados parámetros");
        ok = 0;
    }
    else
    {
        ok = 1;

        if (argc>1 && (sscanf(argv[1],"%d",&p->tampag)!=1 ||
                       p->tampag<1))
        {
            fprintf (stderr,
                     "\n    ERROR: tamaño de página incorrecto");
            ok = 0;
        }

        if (argc>2 && (sscanf(argv[2],"%d",&p->nummarcos)!=1 ||
                       p->nummarcos<1))
        {
            fprintf (stderr,
                     "\n    ERROR: nº de marcos incorrecto");
            ok = 0;
        }

        if (argc>3)
            p->algoritmo = argv[3];

        if (strlen(p->algoritmo)!=3 ||
            strchr(p->algoritmo,'/') ||
            !strstr(ALGORITMOS_VALIDOS,p->algoritmo))
        {
            fprintf (stderr,
                     "\n    ERROR: algoritmo incorrecto");
            ok = 0;
        }

        if (argc>4)
            p->estadoinicial = argv[4];

        if (strlen(p->estadoinicial)!=3 ||
            strchr(p->estadoinicial,'/') ||
            !strstr(ORD_INIC_VALIDOS,p->estadoinicial))
        {
            fprintf (stderr,
                     "\n    ERROR: estado inicial incorrecto");
            ok = 0;
        }

        if (argc>5 && (sscanf(argv[5],"%d",&p->numelem)!=1 ||
                       p->numelem<2))
        {
            fprintf (stderr,
                     "\n    ERROR: número de elementos "
                                  "incorrecto");
            ok = 0;
        }

        if (argc>6)
        {
            if (strcmp(argv[6],"N") && strcmp(argv[6],"D"))
            {
                fprintf (stderr,
                         "\n    ERROR: modo incorrecto");
                ok = 0;
            }

            p->detallado = !strcmp(argv[6],"D");
        }
    }

    if (ok)
        return 0;

    fprintf (stderr,
             "\n\n    USO:\n\t%s tampag nummarcos alg "
                          "ordinic numelem modo\n\n", argv[0]);

    fprintf (stderr,
             "\ttampag: nº de elementos que caben "
                       "en una página\n"
             "\tnummarcos: nº de marcos de pág. (mem física)\n"
             "\talg: algoritmo de ordenación (%s)\n"
             "\tordinic: estado inicial del array (%s)\n"
             "\tnumelem: nº de elementos del array a ordenar\n"
             "\tmodo: normal(N) o detallado(D)\n"
             "\n",
             ALGORITMOS_VALIDOS, ORD_INIC_VALIDOS);

    fprintf (stderr,
             "    EJEMPLOS:\n"
             "\t%s 16 32 MER ALE 1000\n"
             "\t%s 1 3 HEA DES 4 D\n"
             "\n",
             argv[0], argv[0]);

    return -1;
}

