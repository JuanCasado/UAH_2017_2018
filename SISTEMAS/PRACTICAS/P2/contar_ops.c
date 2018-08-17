/*
    contar_ops.c
*/

#include <stdio.h>
#include <stdlib.h>

#define NUM_ALG 8
#define NUM_INI 3
#define NUM_TAM 3

int main ()
{
    // Estados iniciales del array: orden ASCendente,
    // orden DEScendente y orden (o más bien desorden) ALEatorio
    const char * inicial[NUM_INI] = { "ASC", "DES", "ALE" };

    // Tamaños de array con los que experimentar
    unsigned tamanyos[NUM_TAM] = { 10, 100, 1000 };

    // Algoritos de ordenación: burbuja, inserción, selección,
    // montículo (heapsort), peine (combsort), mezcla (mergesort),
    // rápido (quicksort), y rápido con pivote aleatorio
    const char * algoritmos[NUM_ALG] = { "BUB", "INS", "SEL",
                                         "HEA", "COM", "MER",
                                         "QUI", "QPA" };

    char orden[100];  // Orden para ejecutar el programa gen_traza
    FILE * tuberia;   // Canal de comunicación con gen_traza
    int a, i, t, ok;  // Índices en arrays y bandera
    char op;          // Operación elemental ('L'ectura, 'E'scr...)
    unsigned u;       // Número del elemento leído/escrito
    unsigned tam;     // Tamaño del array a ordenar

    unsigned lecturas, escrituras, comparaciones;   // Contadores
    unsigned resultados[NUM_ALG][NUM_INI][NUM_TAM]; // Tablas

    // Realizar experimentos y recopilar tablas

    for (t=0; t<NUM_TAM; t++)
        for (a=0; a<NUM_ALG; a++)
            for (i=0; i<NUM_INI; i++)
            {
                tam = tamanyos[t];
                lecturas = escrituras = comparaciones = 0;

                // Confeccionar orden para invocar a gen_traza
                // (sprintf "imprime" en una cadena)
                sprintf (orden, "./gen_traza %s %s %u",
                                algoritmos[a], inicial[i], tam);

                printf ("Ejecutando orden: %s\n", orden);

                // Invocar al programa gen_traza y abrir tubería
                // para leer su salida estándar ("r" de read)
                tuberia = popen (orden, "r");

                if (!tuberia)
                {
                    perror ("ERROR al lanzar gen_traza");
                    return -1;
                }

                // Leer (e ignorar) tamaño
                ok = fscanf (tuberia," T %u", &u) == 1;

                while (ok)
                {
                    // Ignorar espacios y leer un carácter
                    if (fscanf(tuberia," %c",&op)!=1)
                    {
                        ok = 0;
                        break;
                    }

                    if (op=='L' || op=='E')  // Si es una lectura
                    {                        // o una escritura,
                        if (op=='L')         // contarla y leer el
                            lecturas ++;     // número del elemento
                        else                 // del array al que
                            escrituras ++;   // se refiere (u)

                        if (fscanf(tuberia,"%u",&u)!=1)
                            ok = 0;
                    }
                    else if (op=='C')        // 'C'omparación
                        comparaciones ++;
                    else if (op=='O')        // 'O'rdenado (fin)
                        break;
                    else                     // 'D'esordenado (u
                        ok = 0;              // otra cosa) -> error
                }

                // Esperar a que gen_traza termine y cerrar
                if (pclose(tuberia)==-1)
                    ok = 0;

                // Guardar nº de operaciones en la tabla
                // (0 si ocurrió algún error)
                resultados[a][i][t] = ok ? lecturas +
                                           escrituras +
                                           comparaciones : 0;
            }

    // Imprimir tablas

    for (i=0; i<NUM_INI; i++)
    {
        printf ("\n\nEstado inicial: %s\n", inicial[i]);
        printf ("===================\nTamaño");

        for (a=0; a<NUM_ALG; a++)
            printf ("%8s", algoritmos[a]);

        printf ("\n\n");

        for (t=0; t<NUM_TAM; t++)
        {
            printf ("%6u", tamanyos[t]);

            for (a=0; a<NUM_ALG; a++)
                if (resultados[a][i][t]<1000000)
                    printf (" %7u", resultados[a][i][t]);
                else
                    printf (" %7.1e", (float)resultados[a][i][t]);

            printf ("\n");
        }
    }

    printf ("\n");

    return 0;
}

