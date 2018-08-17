#include <stdio.h>
#include <stdlib.h>

int numeros_a_ordenar = 0;
int seleccion = 0;

void ordenar(int[]);
void invertir(int[]);
void mostrar(int[]);
void mostrar_p(int[]);

int main (void)
{
	while(1)
	{
		puts("¿Cuántos números desea ordenar?");
		scanf("%d", &numeros_a_ordenar);
	
		puts("Elija el tipo de ordenación: ");
		puts("1. Mayor a menor");
		puts("2. Menos a mayor");
		puts("3. Mayor a menor con pares/impares");
		puts("4. Menor a mayor con pares/impares");
		scanf("%d", &seleccion);

		if ((numeros_a_ordenar < 1)||(seleccion>4)||(seleccion<1))
		{
			puts("Valor erroneo");
			exit(1);
		}

		int numeros[numeros_a_ordenar];

		for (int indice = 0; indice < numeros_a_ordenar; indice++)
		{
			printf("#%d==> ",(indice+1));
			scanf("%d", &numeros[indice]);
		}
		
		switch (seleccion)
		{
			case 1:
				ordenar(numeros);
				invertir(numeros);
				mostrar(numeros);
				break;
			case 2:
				ordenar(numeros);
				mostrar(numeros);
				break;
			case 3:
				ordenar(numeros);
				invertir(numeros);
				mostrar_p(numeros);
				break;	
			case 4:
				ordenar(numeros);
				mostrar_p(numeros);
				break;
		}
		
		puts("");
	}
}

void ordenar(int array[])
{
	int menor = array[0];
	int posicion = 0;
	int temporal = 0;

	for (int x = 0; x < numeros_a_ordenar; x++)
	{
		for (int indice = (0+x); indice < numeros_a_ordenar; indice++)
        	{
			if (menor >= array[indice])
			{
				posicion = indice;
				menor = array[indice];
			}
		}	
		temporal = array[x];
		array [x] = menor;
		array [posicion] = temporal;
		menor = array [(numeros_a_ordenar-1)];
	}
}

void invertir(int array[])
{
	int temporal = 0;
	for (int indice = 0; indice < (int)(numeros_a_ordenar/2); indice++)
	{
		temporal = array[indice];
		array[indice] = array[((numeros_a_ordenar-1)-indice)];
		array[((numeros_a_ordenar-1)-indice)] = temporal;
	}
}

void mostrar(int array[])
{
	for(int indice = 0; indice < numeros_a_ordenar; indice ++)
        	printf("%d\t", array[indice]);
}

void mostrar_p(int array[])
{
	for(int indice = 0; indice < numeros_a_ordenar; indice ++)
		if ((array [indice]%2)==0)
			printf("%d\t", array[indice]);
	
	puts("");

	for(int indice = 0; indice < numeros_a_ordenar; indice ++)
                if ((array [indice]%2)!=0)
                        printf("%d\t", array[indice]);
}
