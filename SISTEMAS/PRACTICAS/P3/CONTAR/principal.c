
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>

#include "leercar_R.h"
#include "contar_M.h"

int main (int argc, char *argv[])
{
    char caracter = 0;
    char modo = 0;
    char *PATH = NULL;
    int file = 0;
    int numero_de_caracteres = 0;
    
    //No DE ARGUMENTOS CORRECTO --> 1
    if (argc < 3 || argc > 4)
    {
        puts("\033[91mERROR!!!");
        puts("No DE ARGUMENTOS ERRONEO");
        puts("2. R/M ./DATOS CARACTER");
        puts("3. R/M   PATH  CARACTER\033[0m\n");
        return 1;
    }
    
    if (argc == 4)                                          //INDICAMOS UN PATH CONCRETO DE UN ARCHIVO DEL QUE QUERAMOS CONTAR LOS CARACTERES
    {
        PATH = (char*) malloc(sizeof(argv[2]));
        strcpy(PATH,argv[2]);
    }
    else                                                    //USAMOS EL PATH POR DEFECTO: UN ARCHIVO DATOS.txt QUE CONTIENE TANTAS LETRAS DE CADA TIPO COMO SU POSICION EN EL ALFABETO (ej: a,A = 1; b,B = 2; z,Z = 26)
    {
        PATH = (char*) malloc(sizeof("./DATOS.txt"));
        strcpy(PATH,"./DATOS.txt");
    }
    
    //ASIGNACION DE MODO --> 3
    modo = argv[1][0];
    modo &= 0xDF;                                           //CONVERTIMOS A MAYUSCULAS
    if (modo != 'R' && modo != 'M' )
    {
        puts("\033[91mERROR!!!");
        puts("MODO ERRONEO R/M\033[0m\n");
        return 3;
    }
    
    //ASIGNACION DE CARACTER --> 4                          //SOLO PODEMOS CONTAR CARACTERES ENTRE a,z y A,Z
    if (argc == 3)
        caracter = argv[2][0];
    else
        caracter = argv[3][0];
    if ((((caracter < 'A') || (caracter > 'z')) &&  ((caracter > 'Z') || (caracter <  'a'))))
    {
        puts("\033[91mERROR!!!");
        puts("CARACTER (a,z) && (A,Z)\033[0m\n");
        return 4;
    }
    
    if((file = open (PATH, O_RDONLY)) > 0)                  //ABRIMOS EL ARCHIVO
    {
        if(modo == 'R')
        {
            off_t bytes = lseek(file, 0, SEEK_END);                                 //MODO R
            for (off_t posicion = 0; posicion < bytes; posicion++)                  //ITERAMOS SOBRE CADA POSICION DEL ARCHIVO
                if (LeerCaracter (file, posicion) == caracter)                      //PEDIMOS EL CARACTER DE ESA POSCION
                    numero_de_caracteres ++;                                        //SI EL CARACTER COINCIDE AUMENTAMOS EN 1 EL NUMERO DE CARACTERES ENCONTRADOS
        }
        else                                                                        //MODO M
            numero_de_caracteres = ContarCaracteres(file,caracter);                 //LLAMAMOS A UNA FUNCION QUE NOS OBTIENE DIRECTAMENTE EL NUMERO DE CARACTERES
        
        printf("\033[32mLa letra %c aparece %d veces.\n\033[0m\n", caracter, numero_de_caracteres);
    }
    else
    {
        //PATH NO EXISTE --> 2
        puts("\033[91mERROR!!!");
        puts("ERROR AL ABRIL EL ARCHIVO");
        printf("PATH: %s NO EXISTE\033[0m\n",PATH);
        close (file);
        return 2;
    }
    
    close (file);
    return 0;
}
