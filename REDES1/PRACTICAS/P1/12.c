#include <stdio.h>
#include <string.h>

#define MAXIMO 333

int main (void)
{
    char direccion[MAXIMO], lectura[64];
    int contador = 0, iguales =0, checksum = 0;
    
    FILE *archivo;
    FILE *salida;
    
    
    puts("Introduzca la dirección del archivo: ");
    scanf("%s", direccion);
    
    if(((archivo = fopen(direccion,"r"))!=NULL)&&((salida = fopen(".//ej12.txt","a"))!=NULL))
    {
        while(!feof(archivo))
        {
            checksum = fread(lectura,1,64,archivo);
            for(int x = 0; x < sizeof(lectura); x++)
            {
                if (lectura[x] != 10)
                    fputc(lectura[x],salida);
                else
                    fputc('_',salida);
            }
            fprintf(salida,"\t==>>\t%d\n",checksum);
        }
    }
    
    fclose(archivo);
    fclose(salida);
    return 0;
}
