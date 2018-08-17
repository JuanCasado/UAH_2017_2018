
#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/types.h>
#include <dirent.h>
#include <string.h>
#include <unistd.h>

//PROGRAMA QUE GENERA DATOS DE MUESTRAS PARA CONTAR: (ej a,A=1; b,B=2; c,C=3; ... ; z,Z=26)

int main()
{
    char PATH [100] = "./DATOS.txt";
    int archivo = 0;
    char x;
    char n;
    char contador = 1;
    char X;
    
    char fin[6] = {':',' ',' ',' ','\n','\0'};

    //ESCRIBIR EN EL ARCHIVO
    archivo = open(PATH, O_RDWR | O_TRUNC | O_CREAT | S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
    if (archivo > 0)
    {
        for (x = 'a'; x <= 'z'; x++ )
        {
            for (n = 0; n < contador; n++)
                write(archivo,&x,sizeof(x));

	    X = x & 0xDF;	    
	    
	    for (n = 0; n < contador; n++)
		write(archivo,&X,sizeof(X));
                
            fin[2] = contador/10+'0';
            fin[3] = contador%10+'0';
            write(archivo,&fin,sizeof(fin));
            contador++;
        }
    }
    //TAMAÑO DEL ARCHIVO
    printf("%lldBYTES ESCRITOS\n",lseek(archivo,0,SEEK_END));
    close(archivo);
    return 0;
}

