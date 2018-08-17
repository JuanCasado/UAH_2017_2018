
#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/types.h>
#include <dirent.h>
#include <string.h>
#include <unistd.h>

int main()
{
    DIR *dir;
    struct dirent *ent;
    int identificador = 0;
    int contador = 0;
    char PATH [100] = "./ARCHIVOS/";
    
    int control1 = 1;
    int archivo = 0;
    int modo_apertura = 0;
    char buff;
    
    int posicion = 0;
    off_t posicion_actual = 0;
    off_t posicion_anterior = 0;
    
    //MOSTRAR EL CONTENIDO DEL DIRECTORIO
    dir = opendir ("./ARCHIVOS/");
    if (dir != NULL)
        while ((ent = readdir (dir)) != NULL)
            if ( (strcmp(ent->d_name, ".")!=0) && (strcmp(ent->d_name, "..")!=0) )
                printf("%d. %s\n",identificador++,ent->d_name);
    rewinddir(dir);
    
    //ELEGIR QUE ARCHIVO ABRIR
    printf("\nPATH directorio: ");
    scanf("%d", &identificador);
    identificador += 2;
    while ((ent = readdir (dir)) != NULL && contador != identificador)
            contador++;
    strcat(PATH,ent->d_name);
    
    //ELEGIR COMO ABRIRLO
    while (control1)
    {
        puts("");
        puts("0. Leer");
        puts("1. Escribir");
        puts("2. Borrar");
        puts("3. FIN");
        printf("Elija el modo de apertura:");
        scanf("%d", &modo_apertura);
        switch (modo_apertura)
        {
            case 0:
                //LEER EL ARCHIVO
                archivo = open(PATH,O_RDONLY);
                if (archivo > 0)
                {
                    printf ("\n%s >\n\n\t",PATH);
                    while(read(archivo,&buff,sizeof(buff))!=0)
                        printf("%s",&buff);
                    puts("");
                }
                //TAMAÑO DEL ARCHIVO
                printf("TAMAÑO: %lldbytes\n",lseek(archivo,0,SEEK_END));
                break;
            case 1:
                //ESCRIBIR EN EL ARCHIVO
                archivo = open(PATH, O_RDWR | O_APPEND);
                if (archivo > 0)
                {
                    //TAMAÑO DEL ARCHIVO
                    printf("TAMAÑO: %lldbytes\n",lseek(archivo,0,SEEK_END));
                    //DONDE DEL ARCHIVO
                    puts("");
                    puts("0. AL FINAL");
                    puts("1. AL PRINCIPIO");
                    printf("Donde escribir? ");
                    scanf("%d", &posicion);
                    if (posicion)
                        lseek(archivo,0,SEEK_SET);
                    
                    buff = 'a';
                    printf("TAMAÑO: %lldbytes\n",lseek(archivo,0,SEEK_SET));
                    printf("TAMAÑO: %lldbytes\n",lseek(archivo,0,SEEK_CUR));
                    write(archivo,&buff,sizeof(buff));
                    printf("TAMAÑO: %lldbytes\n",lseek(archivo,0,SEEK_CUR));
                    do
                    {
                        printf("%lld>",lseek(archivo,0,SEEK_CUR));
                        scanf("%c",&buff);
                        posicion_anterior = posicion_actual;
                        posicion_actual = lseek(archivo,0,SEEK_CUR);
                        if (buff == 'c')
                            lseek(archivo,0,posicion_anterior);
                        write(archivo,&buff,sizeof(buff));
                    }
                    while(buff != '.');
                    //TAMAÑO DEL ARCHIVO
                    printf("TAMAÑO: %lldbytes\n",lseek(archivo,0,SEEK_END));
                }
                break;
            case 2:
                archivo = open(PATH,O_WRONLY | O_TRUNC);
                if (archivo > 0)
                {
                    puts("BORRADO!");
                    //TAMAÑO DEL ARCHIVO
                    printf("TAMAÑO: %lldbytes\n",lseek(archivo,0,SEEK_END));
                }
                break;
            case 3:
                control1 = 0;
                puts ("\nSaliendo... \n");
                break;
        }
        close(archivo);
    }
    closedir (dir);
    return 0;
}
