#include "contar_M.h"

int ContarCaracteres (int fd, char caracter)
{
	int veces = 0;
    off_t tam = lseek(fd,0,SEEK_END);                                           //AVERIGUAMOS EL TAMAÑO DEL ARCHIVO
    char *contenido = NULL;
    contenido = (char*) mmap(NULL, tam, PROT_READ, MAP_PRIVATE, fd, 0);         //ALMACENAMOS SU CONTENIDO CONTENIDO EN UN ARRAY
    for (off_t x = 0; x < tam; x++)
        if (contenido[x] == caracter)                                           //ITERAMOS SOBRE EL ARRAY CONTANDO EL NUMERO DE ACIERTOS CON EL CARACYER QUE BUSCABAMOS
            veces++;
    munmap (contenido, tam);
	return veces;
}
