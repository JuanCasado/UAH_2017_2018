#include "buscar_caracter.h"

int main (int argc, char *argv[])
{
	char buffer;
	int df, posicion;

	if (argc != 3)
	{
		printf ("Error\n Sintaxis: %s arhivo posicion \n", argv[0]);
		exit(1);
	}

	if ((df = open (argv[1], O_RDONLY)) == -1)
	{
		perror ("Error al abrir el arhivo"); exit(2);
	}

	posicion = atoi(argv[2]);

	buffer = busca_caracter (df, posicion);

	printf("El carater %d del archivo %s es:\"%c\"\n", posicion, argv[1], buffer);

	close(df);
	return (0);
}
