#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXIMO 200
#define LINEA 50

char *nombre_archivo = NULL;
FILE *archivo = NULL;
long numero_bytes = 0;
int control = 1;
int opcion = 0;

int main (void)
{	
	while (control)
	{
		puts("Introduzca la ruta del archivo: (\"exit\" para salir)");
		nombre_archivo = (char*) malloc (MAXIMO*sizeof(char));
		scanf("%s", nombre_archivo);
		fflush (stdin);
		if (strcmp(nombre_archivo,"exit") == 0)
		{
			free (nombre_archivo);
			return 0;
		}
		if((archivo = fopen(nombre_archivo,"r")) == NULL)
		{
			puts("No se pudo abrir el archivo");
			exit(1);
		}
		else
		{
			puts("El archivo se abrió correctamente");
			
			puts("Que desea hacer?");
			puts("1. Obtener el numero de bytes");
			puts("2. Leer un número de líneas");

			scanf("%d", &opcion);
			
			if (opcion == 1)
			{		
				while (fgetc(archivo) != EOF)
					numero_bytes++;	
		
				printf("El archivo contiene %ld bytes\n", numero_bytes);		
			}
			else if (opcion == 2)
			{
				int numero_lineas = 0;
				char *linea = NULL;
				char parada;				

				linea = (char*) malloc (LINEA*sizeof(char));				

				printf("Introduzca el número de líneas a leer: ");
                                scanf("%d", &numero_lineas);
				
				do
				{
					for (int x = 1; (x <= numero_lineas)&&((linea = fgets (linea, LINEA-1, archivo))!=NULL); x++)
						printf("%d. %s\n",x,linea);
					puts ("Escriba 'x' para parar");
					parada = getchar();
				}
				while(parada != 'x');	
			}

			if (fclose(archivo) == EOF)
			{
				puts("El archivo se cerró de forma erronea");
				exit(2);
			}
			else
			{
				puts("El archivo se cerró correctamente");
				free(nombre_archivo);
			}
		}
	}
}

