#include "leercar_R.h"

char LeerCaracter (int fd, int posicion)
{
    char c = 0;
    lseek(fd,posicion,SEEK_SET);    //SITUAMOS EL CURSOR SOBRE LA POSICION ESPECIFICA
    read (fd,&c,sizeof(c));         //LEEMOS EL CARACTER EN ESA POSICION
	return c;                       //EVOLVEMOS EL CARACTER
}
