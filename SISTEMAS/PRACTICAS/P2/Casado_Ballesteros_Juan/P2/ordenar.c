/*
    ordenar.c
*/

#include <stdlib.h>
#include "ordenar.h"

// Ordenación por el método de la burbuja
//     Estable:               sí
//     Complejidad máx:       O(N*N)
//     Complejidad media:     O(N*N)
//     Complejidad mín:       O(N)
//     Otras consideraciones: Es el peor método de todos los que se
//                            suelen enseñar. Hay quien propone que
//                            no se enseñe nunca más, y que se
//                            utilice en su lugar la ordenación por
//                            inserción.
//                            Se puede reducir el número de
//                            escrituras encadenando los
//                            intercambios, pero no merece la pena
//                            (sigue siendo peor que el de
//                            inserción).

unsigned bubble_sort (void * p, unsigned tam,
                      funcion_menor * pmenor,
                      funcion_leer * pleer,
                      funcion_escribir * pescr)
{
    int fin;
    unsigned u, iter;
    cosa a, b;

    for (fin=0, iter=0; tam>1 && !fin; tam--)
    {
        a = pleer (p, 0);

        for (fin=1, u=0; u+1<tam; u++, iter++)
        {
            b = pleer (p, u+1);

            if (pmenor(p,b,a))
            {
                pescr (p, u, b);
                pescr (p, u+1, a);
                fin = 0;
            }
            else
                a = b;
        }
    }

    return iter;
}

// Ordenación por el método de inserción
//     Estable:               sí
//     Complejidad máx:       O(N*N)
//     Complejidad media:     O(N*N)
//     Complejidad mín:       O(N)
//     Otras consideraciones: En principio es igual de sencillo que
//                            la burbuja, pero se puede optimizar
//                            para hacerlo bastante más rápido
//                            (aunque siempre será O(N*N)).

unsigned insertion_sort (void * p, unsigned tam,
                         funcion_menor * pmenor,
                         funcion_leer * pleer,
                         funcion_escribir * pescr)
{
    unsigned u, v, iter;
    cosa a, b, c;

    a = pleer (p, 0);

    for (u=1, iter=0; u<tam; u++)
    {
        b = pleer (p, u);

        if (pmenor(p,b,a))
        {
            v = u;
            c = a;

            do
            {
                pescr (p, v, c);
                iter ++;

                if (--v==0)
                    break;

                c = pleer (p, v-1);
            }
            while (pmenor(p,b,c));

            pescr (p, v, b);
        }
        else
        {
            a = b;
            iter ++;
        }
    }

    return iter;
}

// Ordenación por el método de selección
//     Estable:               no
//     Complejidad máx:       O(N*N)
//     Complejidad media:     O(N*N)
//     Complejidad mín:       O(N*N)
//     Otras consideraciones: No pasa de 2*N escrituras. Siempre
//                            hace el mismo número de operaciones
//                            sobre el array a ordenar (salvo que
//                            ahorre escrituras). Las lecturas y
//                            la mitad de las escrituras siguen un
//                            patrón fijo de acceso a memoria,
//                            aunque la otra mitad de las
//                            escrituras no.

unsigned selection_sort (void * p, unsigned tam,
                         funcion_menor * pmenor,
                         funcion_leer * pleer,
                         funcion_escribir * pescr)
{
    unsigned u, v, min, iter;
    cosa a, b, c;

    for (u=iter=0; u<tam-1; u++)
    {
        a = b = pleer (p, min=u);

        for (v=u+1; v<tam; v++, iter++)
        {
            c = pleer (p, v);

            if (pmenor(p,c,b))
            {
                min = v;
                b = c;
            }
        }

        if (min!=u)
        {
            pescr (p, u, b);
            pescr (p, min, a);
        }
    }

    return iter;
}

// Ordenación por el método del montículo
//     Estable:               no
//     Complejidad máx:       O(N*log N)
//     Complejidad media:     O(N*log N)
//     Complejidad mín:       O(N*log N)
//     Otras consideraciones: Es relativamente lento (en proporción
//                            con el comportamiento _medio_ de
//                            quicksort) y es igual de lento cuando
//                            los datos ya están ordenados o casi
//                            ordenados. Por otro lado, es el único
//                            que, con sólo O(1) espacio adicional,
//                            tiene complejidad O(N*log N) en el
//                            peor caso.

static unsigned sift_in (void * p, unsigned tam,
                         unsigned hueco, cosa a,
                         funcion_menor * pmenor,
                         funcion_leer * pleer,
                         funcion_escribir * pescr);

unsigned heap_sort (void * p, unsigned tam,
                    funcion_menor * pmenor,
                    funcion_leer * pleer,
                    funcion_escribir * pescr)
{
    unsigned pos, iter;
    cosa a;

    if (tam<2)
        return 0;

    // Primero: amontonar

    for (pos=tam>>1, iter=0; pos; pos--)
        iter += sift_in (p, tam, pos, pleer(p,pos-1),
                         pmenor, pleer, pescr);

    // Segundo: ordenar extrayendo del montón

    while (tam>1)
    {
        a = pleer (p, tam-1);
        pescr (p, tam-1, pleer(p,0));
        tam --;
        iter += sift_in (p, tam, 1, a,
                         pmenor, pleer, pescr);
    }

    return iter;
}

static unsigned sift_in (void * p, unsigned tam,
                         unsigned hueco, cosa nuevo,
                         funcion_menor * pmenor,
                         funcion_leer * pleer,
                         funcion_escribir * pescr)
{
    unsigned u, h, iter;
    cosa a, b;

    h = hueco;
    iter = 0;

    for (u=h<<1; u<tam; u<<=1, iter++)
    {
        a = pleer (p, u-1);
        b = pleer (p, u);

        if (pmenor(p,a,b))
        {
            u ++;
            a = b;
        }

        pescr (p, h-1, a);
        h = u;
    }

    if (u==tam)
    {
        pescr (p, h-1, pleer(p, u-1));
        h = u;
    }

    while ((u=h>>1) >= hueco)
    {
        a = pleer (p, u-1);
        iter ++;

        if (!pmenor(p,a,nuevo))
            break;

        pescr (p, h-1, a);
        h = u;
    }

    pescr (p, h-1, nuevo);

    return iter;
}

// Ordenación por el método del "peine"
//     Estable:               no
//     Complejidad máx:       O(N*N) (puede que sea O(N*log N),
//                                    pero no está demostrado)
//     Complejidad media:     O(N*log N)
//     Complejidad mín:       O(N*log N)
//     Otras consideraciones: Patrón fijo de acceso a memoria
//                            (si es O(N*log N))

static const unsigned long combs[];

unsigned comb_sort (void * p, unsigned tam,
                    funcion_menor * pmenor,
                    funcion_leer * pleer,
                    funcion_escribir * pescr)
{
    unsigned u, v, n, comb, iter;
    cosa a, b;

    iter = n = 0;

    while (tam>combs[n])
        n ++;

    while (n>0)
    {
        comb = combs[--n];

        for (u=0, v=comb; v<tam; u++, v++, iter++)
        {
            a = pleer (p, u);
            b = pleer (p, v);

            if (pmenor(p,b,a))
            {
                pescr (p, u, b);
                pescr (p, v, a);
            }
        }

        if (n==0)
            break;

        comb = combs[--n];

        for (u=tam-comb-1, v=tam-1; v>=comb; u--, v--, iter++)
        {
            a = pleer (p, u);
            b = pleer (p, v);

            if (pmenor(p,b,a))
            {
                pescr (p, u, b);
                pescr (p, v, a);
            }
        }
    }

    return iter + insertion_sort (p, tam, pmenor, pleer, pescr);
}

static const unsigned long combs[] =
{
    // Esta tabla usa números primos entre sí. Cada número
    // guarda con el anterior una relación menor que la
    // raíz cuadrada de 2.

    // Ojo: los valores más pequeños se han omitido, por lo
    //      que la etapa final de ordenación por inserción
    //      siempre es necesaria (otra cosa es que tarde
    //      O(N*N), o que tarde O(N) como es habitual)
    5UL, 7UL,
    9UL, // Ojo: 9 no es primo
    11UL, 13UL, 17UL, 23UL, 31UL, 43UL, 59UL, 83UL, 113UL,
    157UL, 211UL, 293UL, 409UL, 577UL, 811UL, 1129UL,
    1583UL, 2237UL, 3163UL, 4463UL, 6311UL, 8923UL, 12619UL,
    17839UL, 25219UL, 35617UL, 50363UL, 71209UL, 100703UL,
    142403UL, 201359UL, 284759UL, 402697UL, 569497UL,
    805381UL, 1138979UL, 1610753UL, 2277941UL, 3221473UL,
    4555843UL, 6442897UL, 9111629UL, 12885751UL, 18223193UL,
    25771469UL, 36446357UL, 51542927UL, 72892669UL,
    103085789UL, 145785317UL, 206171569UL, 291570607UL,
    412343081UL, 583141177UL, 824686151UL, 1166282329UL,
    1649372281UL, 2332564607UL, 3298744483UL,
    ~0UL
};

// Ordenación por el método de mezcla de listas ordenadas
//     Estable:               sí
//     Complejidad máx:       O(N*log N)
//     Complejidad media:     O(N*log N)
//     Complejidad mín:       O(N*log N)
//     Otras consideraciones: Necesita O(N) memoria adicional.
//                            Hay una versión (natural mergesort)
//                            que es O(N) mín, pero esa versión
//                            aprovecha peor la caché en el caso
//                            medio.

static unsigned merge_sort_r (void * p, unsigned tam,
                              unsigned dest, unsigned temp,
                              funcion_menor * pmenor,
                              funcion_leer * pleer,
                              funcion_escribir * pescr);

unsigned merge_sort (void * p, unsigned tam,
                     funcion_menor * pmenor,
                     funcion_leer * pleer,
                     funcion_escribir * pescr)
{
    unsigned u;

    for (u=0; u<tam; u++)
        pescr (p, u+tam, pleer(p,u));

    return tam + merge_sort_r (p, tam, 0, tam, pmenor, pleer, pescr);
}

static unsigned merge_sort_r (void * p, unsigned tam,
                              unsigned dest, unsigned temp,
                              funcion_menor * pmenor,
                              funcion_leer * pleer,
                              funcion_escribir * pescr)
{
    unsigned u, v, w, izq, der, iter;
    cosa a, b;

    izq = tam / 2;
    der = tam - izq;
    iter = 0;

    if (izq>1)
        iter += merge_sort_r (p, izq, temp, dest,
                              pmenor, pleer, pescr);

    if (der>1)
        iter += merge_sort_r (p, der, temp+izq, dest+izq,
                              pmenor, pleer, pescr);

    if (izq)
        a = pleer (p, temp);

    if (der)
        b = pleer (p, temp+izq);

    for (u=v=w=0; u<izq && v<der; w++, iter++)
        if (pmenor(p,b,a))
        {
            pescr (p, dest+w, b);

            if (++v<der)
                b = pleer (p, temp+izq+v);
        }
        else
        {
            pescr (p, dest+w, a);

            if (++u<izq)
                a = pleer (p, temp+u);
        }

    if (u<izq)
        for (;;)
        {
            iter ++;
            pescr (p, dest+w++, a);

            if (++u==izq)
                return iter;

            a = pleer (p, temp+u);
        }

    if (v<der)
        for (;;)
        {
            iter ++;
            pescr (p, dest+w++, b);

            if (++v==der)
                return iter;

            b = pleer (p, temp+izq+v);
        }

    return iter;
}

// Ordenación por el método "rápido"
//     Estable:               no
//     Complejidad máx:       O(N*N)      <<-- (eso es malo)
//     Complejidad media:     O(N*log N)
//     Complejidad mín:       O(N*log N)
//     Otras consideraciones: Es el más popular. El problema
//                            de la complejidad O(N*N) en el
//                            peor caso se solventa cambiando a
//                            heapsort cuando las cosas se ponen
//                            feas. También es habitual pasar
//                            al método de inserción cuando el nº
//                            de elementos es pequeño (menor que
//                            8, por ejemplo)

static unsigned quick_sort_r (void * p,
                              unsigned desde, unsigned tam,
                              unsigned pa,
                              funcion_menor * pmenor,
                              funcion_leer * pleer,
                              funcion_escribir * pescr);

unsigned quick_sort (void * p, unsigned tam,
                     funcion_menor * pmenor,
                     funcion_leer * pleer,
                     funcion_escribir * pescr)
{
    return quick_sort_r (p, 0, tam, 0, pmenor, pleer, pescr);
}

unsigned quick_sort_pa (void * p, unsigned tam,
                        funcion_menor * pmenor,
                        funcion_leer * pleer,
                        funcion_escribir * pescr)
{
    return quick_sort_r (p, 0, tam, 1, pmenor, pleer, pescr);
}

static unsigned aleatorio (unsigned desde, unsigned tam);

static unsigned quick_sort_r (void * p,
                              unsigned desde, unsigned tam,
                              unsigned pa,
                              funcion_menor * pmenor,
                              funcion_leer * pleer,
                              funcion_escribir * pescr)
{
    unsigned izq, der, hueco, iter;
    cosa a, pivote;

    for (iter=0; tam>1; )
    {
        iter += tam;

        if (pa)  // Si hace falta, elegir el pivote aleatoriamente
        {
            hueco = aleatorio (desde, tam);
            pivote = pleer (p, hueco);
            pescr (p, hueco, pleer(p,desde));
            iter ++;
        }
        else                             // Si no, elegimos el
            pivote = pleer (p, desde);   // primero como pivote

        hueco = desde;
        izq = desde + 1;
        der = desde + tam - 1;

        for (;;)
        {
            do
                a = pleer (p, der);
            while (pmenor(p,pivote,a) && der-->izq);

            if (der<izq)
                break;

            pescr (p, hueco, a);
            hueco = der--;

            do
                a = pleer (p, izq);
            while (pmenor(p,a,pivote) && izq++<der);

            if (izq>der)
                break;

            pescr (p, hueco, a);
            hueco = izq++;
        }

        pescr (p, hueco, pivote);

        izq = hueco - desde;
        der = desde + tam - hueco - 1;

        if (izq>der)
        {
            if (der>1)
                iter += quick_sort_r (p, hueco+1, der, pa,
                                      pmenor, pleer, pescr);
            tam = izq;
        }
        else
        {
            if (izq>1)
                iter += quick_sort_r (p, desde, izq, pa,
                                      pmenor, pleer, pescr);
            tam = der;
            desde = hueco + 1;
        }
    }

    return iter;
}

static unsigned aleatorio (unsigned desde, unsigned tam)
{
    unsigned n;

    n = desde + (unsigned)(rand()/(RAND_MAX+1.0)*tam);

    if (n>desde+tam-1)
        n = desde+tam-1;
    else if (n<desde)
        n = desde;

    return n;
}


