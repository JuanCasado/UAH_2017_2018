/*
    calcular_cdt.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Estructura que contiene datos relacionados con los parámetros
// pasados por línea de órdenes (algoritmo a emplear etc.)

typedef struct
{
    int tampag, intervalo;
    const char * algoritmo, * ordeninicial;
    int numelem;
}
sparametros;

// Función que interpreta los parámetros recibidos por línea de
// órdenes:

int interpretar_orden (int, char*[], sparametros*);

// Estructura que mantiene el conjunto de páginas referenciadas

#define NUM_BYTES(BITS) ((BITS+7)>>3)
#define SET_BIT(P,NBIT) ((P)[(NBIT)>>3] |= 1<<((NBIT)&7))
#define GET_BIT(P,NBIT) ((P)[(NBIT)>>3] & (1<<((NBIT)&7)))

typedef struct
{
    char * prefbits;      // Bits de referencia de las páginas
    int numbytes;         // Tamaño en bytes
    unsigned numpaginas;  // Nº de páginas (y de bits de ref.)
    unsigned numrefs;     // Nº de referencias en intervalo actual
    unsigned totalrefs;   // Nº de refs. desde el principio
    unsigned numilegales; // Nº de referencias ilegales
}
sestadopags;

// Funciones que manipulan el conjunto de páginas referenciadas

int reservar_bits (sestadopags *, int numpaginas);
void liberar_bits (sestadopags *);

void anotar_referencia (const sparametros *,
                        sestadopags *,
                        unsigned elemento);

void volcar_num_refs (sestadopags *);
void mostrar_cabecera (void);

// Función principal

int main (int argc, char * argv[])
{
    sparametros P;    // Parámetros recibidos por línea de órdenes
    char orden[100];  // Orden para ejecutar el programa gen_traza
    FILE * tuberia;   // Canal de comunicación con gen_traza
    int ok;           // Bandera
    char op;          // Operación elemental ('L'ectura, 'E'scr...)
    unsigned u;       // Número del elemento leído/escrito
    sestadopags E;    // Estado de referencia de las páginas
    unsigned numpags; // Número total de páginas
    unsigned totelem; // Número total de elementos (doble en MER)

    E.prefbits = NULL;

    if (interpretar_orden(argc,argv,&P)<0)  // Poner parám. en P
        return -1;

    printf ("# Parámetros:  %s %i %i %s %s %i\n",
            argv[0], P.tampag, P.intervalo,
            P.algoritmo, P.ordeninicial, P.numelem);

    // Confeccionar orden para invocar a gen_traza
    // (sprintf "imprime" en una cadena)
    sprintf (orden, "./gen_traza %s %s %u",
                    P.algoritmo, P.ordeninicial, P.numelem);

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

        // Reservar espacio para los bits de referencia
        if (reservar_bits(&E,numpags)<0)
        {
            fprintf (stderr,
                     "ERROR: no hay suficiente "
                            "memoria dinámica\n");
            ok = 0;
        }
    }

    if (ok)
        mostrar_cabecera ();

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
                anotar_referencia (&P, &E, u);
        }
        else if (op=='O')        // 'O'rdenado -> fin
            break;               // 'C'omparación -> seguir
        else if (op!='C')        // 'D'esordenado (u otra
            ok = 0;              // cosa) -> error
    }

    if (ok)
    {
        volcar_num_refs (&E);

        if (E.numilegales)
            printf ("AVISO: Hubo %u referencias a páginas "
                           "que no existen\n", E.numilegales);
    }

    // Esperar a que gen_traza termine y cerrar
    if (pclose(tuberia)==-1)
        ok = 0;

    liberar_bits (&E);

    return ok ? 0 : -1;
}

// Funciones que manipulan el conjunto de páginas referenciadas

int reservar_bits (sestadopags * pE, int numpaginas)
{
    pE->numpaginas = numpaginas;
    pE->numbytes = NUM_BYTES (numpaginas);
    pE->numrefs = pE->totalrefs = pE->numilegales = 0;
    pE->prefbits = (char*) malloc (pE->numbytes);

    if (pE->prefbits)
    {
        memset (pE->prefbits, 0, pE->numbytes);
        return 0;
    }

    return -1;
}

void liberar_bits (sestadopags * pE)
{
    free (pE->prefbits);
    pE->prefbits = NULL;
}

void anotar_referencia (const sparametros * pPar,
                        sestadopags * pE,
                        unsigned elemento)
{
    unsigned pagina;

    pagina = elemento / pPar->tampag;

    if (pagina < pE->numpaginas)
    {
        SET_BIT (pE->prefbits, pagina);

        if (++pE->numrefs >= pPar->intervalo)
            volcar_num_refs (pE);
    }
    else
        pE->numilegales ++;
}

void mostrar_cabecera (void)
{
    printf ("#\n#%18s %15s %15s %15s\n#\n",
            "Posición", "Intervalo", "Páginas", "Págs/op.");
}

void volcar_num_refs (sestadopags * pE)
{
    unsigned u, refs;

    if (!pE->numrefs)
        return;

    for (u=refs=0; u<pE->numpaginas; u++)
        if (GET_BIT(pE->prefbits, u))
            refs ++;

    printf (" %15u %15u %15u %15f\n",
            pE->totalrefs, pE->numrefs,
            refs, refs/(float)pE->numrefs);

    memset (pE->prefbits, 0, pE->numbytes);
    pE->totalrefs += pE->numrefs;
    pE->numrefs = 0;
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
    p->intervalo = 2000;
    p->algoritmo = "MER";
    p->ordeninicial = "ALE";
    p->numelem = 1000;

    if (argc>6)
        ok = 0;
    else
    {
        ok = 1;

        if (argc>1 && (sscanf(argv[1],"%d",&p->tampag)!=1 ||
                       p->tampag<1))
        {
            fprintf (stderr,
                     "\n    ERROR: tamaño de página incorrecto\n");
            ok = 0;
        }

        if (argc>2 && (sscanf(argv[2],"%d",&p->intervalo)!=1 ||
                       p->intervalo<2))
        {
            fprintf (stderr,
                     "\n    ERROR: intervalo incorrecto\n");
            ok = 0;
        }

        if (argc>3)
            p->algoritmo = argv[3];

        if (strlen(p->algoritmo)!=3 ||
            strchr(p->algoritmo,'/') ||
            !strstr(ALGORITMOS_VALIDOS,p->algoritmo))
        {
            fprintf (stderr,
                     "\n    ERROR: algoritmo incorrecto\n");
            ok = 0;
        }

        if (argc>4)
            p->ordeninicial = argv[4];

        if (strlen(p->ordeninicial)!=3 ||
            strchr(p->ordeninicial,'/') ||
            !strstr(ORD_INIC_VALIDOS,p->ordeninicial))
        {
            fprintf (stderr,
                     "\n    ERROR: orden inicial incorrecto\n");
            ok = 0;
        }

        if (argc>5 && (sscanf(argv[5],"%d",&p->numelem)!=1 ||
                       p->numelem<2))
        {
            fprintf (stderr,
                     "\n    ERROR: número de elementos "
                                  "incorrecto\n");
            ok = 0;
        }
    }

    if (ok)
        return 0;

    fprintf (stderr,
             "\n    USO:\n\t%s tampag intervalo algoritmo "
                        "ordeninicial numelem\n\n", argv[0]);

    fprintf (stderr,
             "\ttampag: nº de elementos que caben "
                       "en una página\n"
             "\tintervalo: nº de operaciones por intervalo\n"
             "\talgoritmo: alg. de ordenación (%s)\n"
             "\tordeninicial: orden inicial del array (%s)\n"
             "\tnumelem: nº de elementos del array a ordenar\n"
             "\n",
             ALGORITMOS_VALIDOS, ORD_INIC_VALIDOS);

    fprintf (stderr,
             "    EJEMPLO:\n"
             "\t%s 16 2000 MER ALE 1000\n"
             "\n",
             argv[0]);

    return -1;
}

