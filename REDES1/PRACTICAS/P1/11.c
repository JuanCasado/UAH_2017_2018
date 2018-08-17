#include <stdio.h>
#include <string.h>

#define MAXIMO 333

int main (void)
{
    char cadena[MAXIMO], direccion[MAXIMO], lectura[MAXIMO];
    int contador = 0, iguales =0;

    FILE *archivo;

    
    puts("Introduzca la cadena que desea buscar: ");
    scanf("%s", cadena);
    
    puts("Introduzca la dirección del archivo: ");
    scanf("%s", direccion);

    if((archivo = fopen(direccion,"r"))!=NULL)
    {
        while(!feof(archivo))
        {
            if(fgetc(archivo)==cadena[iguales])
            {
                iguales++;
                if (iguales == strlen(cadena))
                    contador++;
            }
            else
                iguales = 0;
        }
        printf("%d\n",contador);
    }
    
    fclose(archivo);
    return 0;
}
