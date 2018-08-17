#include <stdio.h>
#include <stdlib.h>

int dimension = 0;
int seleccion = 0;
int **matriz1 = NULL;
int **matriz2 = NULL;
int **resultado = NULL;
char simbolo = ' ';

void suma(void);
void resta(void);
void multiplicacion(void);

int main (void)
{
	while (1)
	{
		puts("Introduzca el tamaño de la matriz: ");
		scanf("%d", &dimension);		

		puts("Elija una operación");
		puts("1. Suma");
		puts("2. Resta");
		puts("3. Multiplicación");
		scanf("%d", &seleccion);
		
		if ((dimension<1)||(seleccion<1)||(seleccion>3))
		{
			puts("Valor incorrecto");
			exit(1);
		}

		matriz1 = (int **) malloc(dimension*sizeof(int*));
		for (int x = 0; x<dimension; x++)
                        matriz1[x] = (int*) malloc(dimension*sizeof(int));
                matriz2 = (int **) malloc(dimension*sizeof(int*));
                for (int x = 0; x<dimension; x++)
                        matriz2[x] = (int*) malloc(dimension*sizeof(int));
		resultado = (int **) malloc(dimension*sizeof(int*));
		for (int x = 0; x<dimension; x++)
			resultado[x] = (int*) malloc(dimension*sizeof(int));

		for (int j = 1; j<3; j++)
		{
			for (int x = 0; x<dimension; x++)
			{
				for(int y = 0; y<dimension; y++)
				{
					printf("Introduzca el numero (%d,%d) de la matriz %d: ",x,y,j);
					if (j==1)
						scanf("%d",&matriz1[x][y]);
					else
						scanf("%d",&matriz2[x][y]);
				}
			}
		}

		

		switch(seleccion)
		{
			case 1:
				suma();
				simbolo = '+';
				break;
			case 2:
				resta();
				simbolo = '-';
				break;
			case 3:
				multiplicacion();
				simbolo = 'x';
				break;
		}

		for (int x = 0; x<dimension; x++)
        	{
			printf("|");
                	for(int y = 0; y<dimension; y++)
                	{
                        	printf("%d|",matriz1[x][y]);
                	}
			printf("\t");
			
			if(x==((int)dimension/2))
				printf("%c",simbolo);
			printf("\t");
			printf("|");
                        for(int y = 0; y<dimension; y++)
                        {
                                printf("%d|",matriz2[x][y]);
                        }
			printf("\t");

                        if(x==((int)dimension/2))
                                printf("=");
                        printf("\t");
			printf("|");
                        for(int y = 0; y<dimension; y++)
                        {
                                printf("%d|",resultado[x][y]);
                        }
			puts("");
        	}	
		
		for (int x = 0; x<dimension; x++)
			free(matriz1[x]);
		free(matriz1);
                for (int x = 0; x<dimension; x++)
                        free(matriz2[x]);
		free(matriz2);
                for (int x = 0; x<dimension; x++)
                        free(resultado[x]);
		free(resultado);
	}
}

void suma ()
{
	for (int x = 0; x<dimension; x++)
        {
        	for(int y = 0; y<dimension; y++)
                {
                	resultado[x][y]=matriz1[x][y]+matriz2[x][y];
		}
	}
}

void resta ()
{
        for (int x = 0; x<dimension; x++)
        {
                for(int y = 0; y<dimension; y++)
                {
                        resultado[x][y]=matriz1[x][y]-matriz2[x][y];
                }
        }
}

void multiplicacion ()
{
	int acumula = 0;
	for (int x = 0; x<dimension; x++)
        {
                for(int y = 0; y<dimension; y++)
                {	
                        for (int j = 0; j<dimension; j++)
				acumula+=matriz1[x][j]*matriz2[j][y];
			resultado[x][y] = acumula;
			acumula = 0;
                }
        }
}
