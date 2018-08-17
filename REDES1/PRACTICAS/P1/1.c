#include <stdio.h>
#include <stdlib.h>

int numero_introducido = 0;
int numero_escrito = 1;
int posicion_escritura = 0;
int fila = 0;

int main (void)
{
	while(1)
	{
		puts("Introduzca un numero: (0 para salir)");
		scanf("%d",&numero_introducido);
	
		if (numero_introducido == 0)
		{
			puts("Valor Incorrecto");
			exit(1);
		}
	
		for (fila = 0; fila < numero_introducido; fila++)
		{
			for (posicion_escritura = 0; posicion_escritura <= numero_introducido; posicion_escritura++)
			{	
				if (((posicion_escritura+fila)-numero_introducido)>=0)
				{
					printf("%d", numero_escrito);
					numero_escrito++;
				}
				printf("\t");
			}
			for (numero_escrito-=1; numero_escrito > 1; numero_escrito--)
			{
				printf("%d\t", (numero_escrito-1));
			}
			puts("");
		}
	}
}
