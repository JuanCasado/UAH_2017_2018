#include "buscar_caracter.h"

char busca_caracter (int fd, int posicion)
{
	char bufer;
	caddr_t memoria;

	memoria = mmap(0, sizeof(bufer)*posicion , PROT_READ, MAP_PRIVATE, fd, posicion);

       if(memoria <0)
        {
                puts("ERROR EN MMAP");
                exit (1);
        }


	bufer = (char) *memoria;

	if(munmap(memoria, sizeof(bufer))<0)
	{
		puts("ERROR EN MUNMAP");
		exit(2);
	}

	return bufer;
}

