#include <stdio.h>
#include <stdlib.h>

char *direccion;
FILE *archivo, *volcado;
char contenido;

int es_vocal(char letra)
{
    switch (letra) {
        case 97:
            return 1;
            break;
        case 101:
            return 1;
            break;
        case 105:
            return 1;
            break;
        case 111:
            return 1;
            break;
        case 117:
            return 1;
            break;
        case 65:
            return 1;
            break;
        case 69:
            return 1;
            break;
        case 73:
            return 1;
            break;
        case 79:
            return 1;
            break;
        case 85:
            return 1;
            break;
        default:
            return 0;
            break;
    }
}

int main (void)
{
    printf("Introduzca la dirección del archivo: ");
    direccion = (char*)malloc(100*sizeof(char));
    scanf("%s", direccion);
    
    archivo = fopen(direccion,"r");
    volcado = fopen(".//volcado_ej9.txt","w");

    while((contenido = fgetc(archivo))!=EOF)
    {
        if (es_vocal(contenido))
            fputc(32,volcado);
        else
            fputc(contenido,volcado);
    }
    
    fclose(archivo);
    fclose(volcado);
    return 0;
}
