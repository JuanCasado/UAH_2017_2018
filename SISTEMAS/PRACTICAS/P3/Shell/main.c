/*
    main.c
*/

#include "parser.h"
#include "manejador_de_directorios.h"

void mostrar_orden (orden * O);

int main ()
{
    printf("\033c");
    //printf("\e[9;1t");
    orden O;
    int r;
    char *PATH_SHELL = NULL;

    crear_path_de_retorno(&PATH_SHELL);
    printf ("\033[35mIntroduzca órdenes (escriba miexit para terminar)\033[0m\n");
    
    do
    {
        inicializar_orden (&O);

        printf ("\033[93m$ \033[0m");
        r = leer_orden (&O, stdin);

        if (r == -4)
            printf("\033[91m...\033[0m\n");
        else if (r < 0)
            fprintf (stderr, "\033[91m\nError %d: %s\n\033[0m\n", -r, mensajes_err[-r]);
        else
        {
            if (O.segundo_plano)           //Ya que no utilizamos el segundo plano o las redirecciones
                mostrar_orden (&O);        //Usaré el flag de segundo plano para llamar a mostrar_orden();

            if (!strcmp(O.argv[0],"mipwd"))                         				   //PWD
                if (O.argc==1)
                    mostrar_path();
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"mils"))                                         //mils
            {
                if (O.argc < 4)
                {
                    int identificador = 0;
                    int detallado = 0;
                    
                    if (O.argc == 1)
                    {
                        identificador =1;
                        detallado = 0;
                    }
                    else if (O.argc == 2)
                    {
                        if (!strcmp(O.argv[1],"-l"))
                        {
                            detallado = 1;
                            identificador = 1;
                        }
                        else
                        {
                            identificador = 0;
                            detallado = 0;
                        }
                    }
                    else
                    {
                        detallado = 1;
                        identificador = 0;
                    }
                    
                    
                    if (identificador)
                        mostrar_directorio(ACTUAL,detallado);
                    else
                        mostrar_directorio(O.argv[1],detallado);
                }
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            }
            else if (!strcmp(O.argv[0],"mimkdir"))                                      //MKDIR
                if (O.argc == 2)
                    crear_directorio(O.argv[1]);
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"mirmdir"))                                      //RMDIR
                if (O.argc == 2)
                    eliminar_directorio(O.argv[1]);
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"micd"))                                         //CD
                if (O.argc==1)
                    cambiar_de_directorio(PATH_SHELL);
                else if(O.argc==2)
                    cambiar_de_directorio(O.argv[1]);
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"micat"))                                        //CAT
                if (O.argc == 2)
                    mostrar_archivo(O.argv[1]);
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"micp"))                                         //CP
                if (O.argc == 3)
                    copiar_archivo(O.argv[1],O.argv[2]);
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"mirm"))                                         //RM
                if (O.argc == 2)
                    eliminar_archivo(O.argv[1]);
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"micreat"))                                      //CREAT
                    if (O.argc == 2)
                        crear_archivo(O.argv[1]);
                    else
                        printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"whoami"))                                       //WHOAMI
                printf("\033[93m%s\n\033[0m",WHOAMI);
            else if (!strcmp(O.argv[0],"miwrite"))                                      //WRITE
                if (O.argc == 3)
                    escribir_archivo(O.argv[1],O.argv[2]);
                else if(O.argc == 2)
                    escribir_archivo(O.argv[1],WHOAMI);
                else
                    printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"miman"))                                        //MAN
                    if (O.argc==1)
                        man("");
                    else if (O.argc == 2)
                        man(O.argv[1]);
                    else
                        printf("\033[91mERROR!!! No DE ARGUMENTOS ERRONEO\033[0m\n");
            else if (!strcmp(O.argv[0],"miexit"))                                       //EXIT
            {
                printf("\033[35mSaliendo... \n\033[0m\n");
                return 0;
            }
            else
                printf("\033[91mORDEN NO RECONOCIDA\033[0m\n");
        }
        liberar_orden (&O);
    }
    while (1);   // Repetir hasta escribir miexit

    return 0;
}

void mostrar_orden (orden * O)
{
    int i;

    printf ("\033[93m\tOrden cruda: \"%s\"\n", O->orden_cruda);
    printf ("\tNúmero de argumentos: %d\n", O->argc);

    for (i=0; i<=O->argc; i++)
        if (O->argv[i] != NULL)
            printf ("\t\targv[%d]: \"%s\"\n", i, O->argv[i]);
        else
            printf ("\t\targv[%d]: NULL\n", i);
/*
    if (O->entrada)
        printf ("\tEntrada: \"%s\"\n", O->entrada);

    if (O->salida)
        printf ("\tSalida: \"%s\"\n", O->salida);

    if (O->salida_err)
        printf ("\tSalida de err.: \"%s\"\n", O->salida_err);
*/
    printf ("\tMostrar orden (&): %s\n",
            O->segundo_plano ? "Sí\033[0m" : "No\033[0m");
}


