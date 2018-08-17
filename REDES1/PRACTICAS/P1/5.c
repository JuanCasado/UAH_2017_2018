#include <stdio.h>
#include <stdlib.h>

#define N_ARRAYS 2
int dimension_pascal = 0;

int main (void)
{
	while(1)
	{
		puts("Introduzca la dimension de su triangulo: (0 para salir)");
		scanf("%d", &dimension_pascal);
		if (dimension_pascal)
		{
			int **pascal, array = 1, posicion = 0;
			pascal = (int**)(malloc(N_ARRAYS*sizeof(int*)));
			for(int x = 0; x < N_ARRAYS; x++)
				pascal[x] = (int*)(malloc(dimension_pascal*sizeof(int)));
			
			pascal[0][0]=1;			

			for (int posicion = 0; posicion <= dimension_pascal; posicion++)
			{
				pascal[array][0]=1;
				if ((posicion-1)>0)
					pascal[array][posicion-1] = 1;

				for (int x = 1; x < posicion-1; x++)
				{
						pascal[array][x] = pascal[!array][x-1]+pascal[!array][x];	 
				}

				for(int x = (int)posicion/2; x<dimension_pascal;x++)
					printf("\t");
								
				for (int x = 0; x< posicion; x++)
				{
					printf("%d\t", pascal[array][x]);
				}
				
				puts("");
				array = !array;
			}

                        for(int x = 0; x < N_ARRAYS; x++)
                                free(pascal[x]);
			free(pascal);
		}
		else
			exit(1);
	}
}
