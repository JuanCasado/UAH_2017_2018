#include <stdio.h>
#include <stdlib.h>

#define TIME_OUT 100

int control = 1;
int *almacen = NULL;

int main (void)
{
	while (control)
	{
        int primera_vez = 1;
        int max = 0;
        int min = 0;
        int buffer = 0;
        int numeros = 0;
        
		printf("Escriba datos: ");
        do
        {
            scanf("%d",&buffer);

            if(buffer == 0)
            {
                if (!numeros)
                    puts("No fueron introducidos números");
                else
                {
                    FILE *archivo;
                    archivo = fopen(".//datos_ej88.txt","w");
                    
                    fprintf(archivo,"Números introducidos: %d\n", numeros);
                    fprintf(archivo,"Máximo: %d\n", max);
                    fprintf(archivo,"Mínimo: %d\n", min);
                    for (int x =0; x <= max; x++)
                        if (almacen[x]!=0)
                            fprintf(archivo,"Número: %d ==> %d veces\n", x, almacen[x]);
                    
                    fclose(archivo);
                    free(almacen);
                }
                control = 0;
            }
            
            else if (primera_vez)
            {
                primera_vez = 0;
                max = buffer;
                min = buffer;
                almacen = (int*) calloc ((max+1),sizeof(int));
                almacen[buffer] += 1;
            }
            
            else
            {
                if (buffer > max)
                {
                    almacen = (int*) realloc (almacen,(buffer+1)*sizeof(int));
                    for (int x = (max+1);x<=buffer;x++)
                        almacen[x]=0;
                    max = buffer;
                }
                if (buffer < min)
                    min = buffer;
                
                almacen [buffer] += 1;
            }
            numeros++;
        }
        while(buffer != 0);
		
	}
	return 0;
}
