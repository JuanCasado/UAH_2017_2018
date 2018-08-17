/*
    sim_paginacion.h
*/

#ifndef _SIM_PAGINACION_H_
#define _SIM_PAGINACION_H_

// Estructura que mantiene el estado de una página,
// simulando una entrada de la tabla de páginas

typedef struct
{
    char presente;      // 1 = cargada en un marco
    int marco;          // Marco en el que está cargada
    char modificada;    // 1 = habrá que grabarla si se desaloja

    // Para FIFO 2ª op.
    char referenciada;  // 1 = pág. referenciada recientemente

    // Para LRU
    unsigned timestamp; // Marca de tiempo de últ. referencia

    // NOTA: Los dos campos anteriores se encuentran en esta
    //       estructura (y no en smarco) porque simulan un
    //       mecanismo que, en la realidad, estaría mantenido
    //       principalmente por el hardware.
}
spagina;

// Estructura que mantiene el estado de un marco
// (el hardware no sabe nada de esta estructura)

typedef struct
{
    int pagina;         // Página que tiene cargada (si la tiene)

    // Para gestión de marcos libres, y para FIFO y FIFO 2ª op.
    int sig;            // Siguiente marco en la lista
}
smarco;

// Estructura que mantiene el estado de todo el sistema

typedef struct
{
    // Tabla de páginas (mantenida por HW y SO)
    int tampag;
    int numpags;
    spagina * tdp;
    int lru;               // Sólo para reemplazo LRU
    unsigned reloj;        // Sólo para reemplazo LRU(t)

    // Tabla de marcos (mantenida sólo por SO)
    int nummarcos;
    smarco * tdm;
    int listalibres;
    int listaocupados;     // Sólo para reemplazo FIFO (y 2ª op.)

    // Datos de seguimiento
    int numrefslectura;    // Contador de operaciones de lectura
    int numrefsescritura;  // Contador de operaciones de escritura
    int numfallospag;      // Contador de fallos de página
    int numescrpag;        // Contador de escr. de página a disco
    int numrefsilegales;   // Referencias fuera de rango
    char detallado;        // 1 = mostrar información a cada paso
}
ssistema;

// Función que inicia las tablas

void iniciar_tablas (ssistema * S);

// Funciones que simulan el hardware de la MMU

unsigned sim_mmu (ssistema * S, unsigned dir_virtual, char op);
void referenciar_pagina (ssistema * S, int pagina, char op);

// Funciones que simulan el sistema operativo

void tratar_fallo_de_pagina (ssistema * S, unsigned dir_virtual);
int elegir_pagina_para_reemplazo (ssistema * S);
void reemplazar_pagina (ssistema * S, int victima, int nueva);
void ocupar_marco_libre (ssistema * S, int marco, int pagina);

// Funciones que muestran resultados

void mostrar_informe (ssistema * S);
void mostrar_tabla_de_paginas (ssistema * S);
void mostrar_tabla_de_marcos (ssistema * S);
void mostrar_informe_reemplazo (ssistema * S);

#endif // _SIM_PAGINACION_H_

