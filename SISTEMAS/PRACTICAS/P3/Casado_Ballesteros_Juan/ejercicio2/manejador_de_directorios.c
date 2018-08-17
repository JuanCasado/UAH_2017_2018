
#include "manejador_de_directorios.h"

//FUNCIONES INTERNAS
int iniciar_dir(char *PATH_d, DIR **dir, struct dirent **ent);
int iniciar_file(char *PATH_f, struct stat *buff);
int get_path(char* PATH_d, char **PATH_f, DIR **dir, struct dirent **ent);
void formatear_PATH(char **PATH_f,char *PATH_d);

int iniciar_dir(char *PATH_d, DIR **dir, struct dirent **ent)
{                                                                               //ABRIMOS EL DIRECTORIO
    if ((*dir = opendir(PATH_d)) == NULL)                                       //INICIAMOS SU ESTRUCTURA dirent SI SE ABRIO CORRECTAMENTE
        return 0;                                                               //NOTIFICAMOS EL ESTADO DE SU APERTURA
    *ent = readdir(*dir);
    
    return 1;
}

int iniciar_file(char *PATH_f, struct stat *buff)
{                                                                               //ABRIMOS EL ARCHIVO EN MODO LECTURA
    int file1 = 0;                                                              //INICIAMOS SU ESTRUCTURA stat SI SE ABRIO CORRECTAMENTE
    if((file1 = open(PATH_f, O_RDONLY)) > 0)                                    //DEVOLVEMOS SU DESCRIPTOR
        fstat (file1 , buff);
    
    return file1;
}

int get_path(char* PATH_d, char **PATH_f, DIR **dir, struct dirent **ent)
{                                                                               //INICIA EL DIRECTORIO PASADO POR PATH_d
    int retorno = 1;                                                            //ESCRIBE EL PATH ACTUAL EN PATH_f
    retorno = iniciar_dir(PATH_d, dir, ent);                                    //DEVUELVE SI SE REALIZO CORRECTAMENTE LA ACCION
    *PATH_f = (char*) malloc(LEN_PATH);
    getcwd(*PATH_f,LEN_PATH);
    return retorno;
}

void formatear_PATH(char **PATH_f, char *PATH_d)
{                                                                               //PONE EN PATH_f EL CONTENIDO DE PATH_d MODIFICADO
    *PATH_f = (char*) malloc(LEN_PATH);                                         //SI LA DIRECCION PASADA NO EMPEZAMA POR . ~ /
                                                                                //LA CONVIERTE EN UNA DIRECCION RELATIVA AL DIRECTORIO ACTUAL ./
    if (PATH_d[0]=='.' || PATH_d[0]=='/' || PATH_d[0]=='~')
        memcpy(*PATH_f,PATH_d,sizeof(PATH_d)+1);
    else
    {
        memcpy(*PATH_f,ACTUAL,sizeof(ACTUAL)+1);
        strcat(*PATH_f,PATH_d);
    }
}

void mostrar_path()
{
    DIR *dir = NULL;                                                            //ABRE EL DIRECTORIO ACTUAL Y MUESTRA SU PATH
    struct dirent *ent = NULL;                                                  //UTILIZA get_path
    
    char *PATH_d = NULL;
    if(get_path(ACTUAL, &PATH_d, &dir, &ent))
        printf("\033[32m%s\033[0m\n",PATH_d);
    else
        printf("\033[91mERROR!!! NO SE PUDO ABRIR EL DIRECTORIO\033[0m\n");
    
    closedir(dir);
}

void mostrar_directorio(char *PATH_d, int modo)
{                                                                               //MUESTRA EL CONTENIDO DE UN DIRECTORIO
    int identificador = 0;
    int file1 = 0;
    char *PATH_f = NULL;
    DIR *dir = NULL;
    struct dirent *ent = NULL;
    PATH_f = (char*) malloc(LEN_PATH);
    memcpy(PATH_f,PATH_d,sizeof(PATH_d)+1);
    
    if (PATH_f[strlen(PATH_d)-1] != '/')                                        //SI LA DIRECCION NO ACABA POR / SE LA AÑADE
    {
        strcat(PATH_f,"/");
    }
    if(iniciar_dir(PATH_f, &dir, &ent))                                         //SE ABRE EL DIRECTORIO PASADO Y SE INIA SU ESTRUCTURA ent
    {
        if (modo)                                                                               //MODO CON -l
        {
            char *PATH_f_aux = NULL;
            struct stat buff;
            PATH_f_aux = (char*) malloc(LEN_PATH);
            memcpy(PATH_f_aux,PATH_f,sizeof(PATH_f)+1);
            printf("\033[32m%s\n\033[0m",PATH_f);
            while ((ent = readdir (dir)) != NULL)
            {
                strcat(PATH_f,ent->d_name);
                if ((strcmp(ent->d_name, ".")!=0) && (strcmp(ent->d_name, "..")!=0))
                {
                    if ((file1 = iniciar_file(PATH_f, &buff)) > 0)                              //SE INICA CADA ESTRUCTURA  stat  DE CADA ARCHIVO
                    {                                                                           //SE MUESTRAN LOS DATOS DE CADA ARCHIVO
                        printf("\033[35m%2d.\033[37m  TIPO: %2d UID:%4u  GID:%3u  PERMISOS: %6o  SIZE:%6lld(B)  \033[32m\t%-40s\033[37m   ULTIMA MODIFICACION: %s\033[0m",
                               identificador++,ent->d_type,buff.st_uid,buff.st_gid,buff.st_mode,buff.st_size,ent->d_name,ctime(&buff.st_mtime));
                    }
                    close(file1);
                }
                memcpy(PATH_f,PATH_f_aux,strlen(PATH_f_aux)+1);
            }
        }
        else                                                                                    //MODO SIN -l
            printf("\033[32m%s\033[0m\n",PATH_f);                                               //SE MUESTRA EL NOMBRE DE LOS ARCHIVOS EN EL DIRECTORIO
            while ((ent = readdir (dir)) != NULL)
                if ( (strcmp(ent->d_name, ".")!=0) && (strcmp(ent->d_name, "..")!=0) )
                    printf("\033[35m%d. \033[32m%s\033[0m\n",identificador++,ent->d_name);
        closedir(dir);
    }
    else
        printf("\033[91mERROR!!! NO SE PUDO ABRIR EL DIRECTORIO\033[0m\n");
}

void crear_directorio(char *PATH_d)
{                                                                               //SE CREA UN DIRECTORIO NUEVO
    char *PATH_f = NULL;
    formatear_PATH(&PATH_f,PATH_d);
    
    if (mkdir(PATH_f,0777))
        printf("\033[91mERROR!!! DIRECTORIO NO SE PUDO CREAR\033[0m\n");
    else
    {
        if (chmod(PATH_f, 07777))
            printf("\033[32m%s +\033[0m\n",PATH_f);
    }
}

void eliminar_directorio(char *PATH_d)
{                                                                               //SE BORRA UN DIRECTORIO YA EXISTENTE
    char *PATH_f = NULL;
    formatear_PATH(&PATH_f,PATH_d);
    
    if(rmdir(PATH_f))
        printf("\033[91mERROR!!! DIRECTORIO NO SE PUDO BORRAR\033[0m\n");
    else
        printf("\033[32m%s -\033[0m\n",PATH_f);
}

void crear_path_de_retorno(char **PATH_s)
{                                                                               //GUARDA EN PATH_s EL DIRECTORIO DE INICIO DE LA SHELL
    DIR *dir = NULL;                                                            //ESTA FUNCION DEBE LLAMARSE DESDE MAIN ANTES DE HACER NADA
    struct dirent *ent = NULL;
    
    get_path(ACTUAL, PATH_s, &dir, &ent);
    printf("\033[35m%s\033[0m\n",*PATH_s);
}

void cambiar_de_directorio(char *PATH_d)
{                                                                               //CAMBIA EL DIRECTORIO EN EL QUE ESTAMOS
    if(chdir(PATH_d))
        printf("\033[91mERROR!!! NO SE PUDO CAMBIAR EL DIRECTORIO\033[0m\n");
    else
        mostrar_path();
}

void mostrar_archivo(char *PATH_d)
{                                                                               //MUESTRA CON PROYECCION EN MEMORIA EL CONTENIDO DE UN ARCHIVO
    int file1 = 0;
    char *PATH_f = NULL;
    struct stat buff;
    formatear_PATH(&PATH_f,PATH_d);
    
    if((file1 = iniciar_file(PATH_f, &buff)) > 0)
    {
        off_t tam = lseek(file1,0,SEEK_END);
        char *contenido = NULL;
        contenido = (char*) mmap(NULL, tam, PROT_READ, MAP_PRIVATE, file1, 0);
        for (off_t x = 0; x < tam; x++)
            printf("\033[37m%c\033[0m",contenido[x]);
        printf("\033[32m(%lld Bytes escritos)\n\033[0m",tam);
        munmap (contenido, tam);
    }
    else
        printf("\033[91mERROR!!! NO SE PUDO MOSTRAR EL ARCHIVO\033[0m\n");
    close(file1);
}

void copiar_archivo (char *PATH_d1, char *PATH_d2)
{                                                                               //COPIA CON LLAMADAS A read write EL CONTENIDO DE EL PRIMER ARCHIVO EN EL SEGUNDO
    int file1 = 0;                                                              //SI EL SEGUNDO NO EXISTE LO CREA Y PONE EL CONTENIDO DEL PRIMERO EN EL
    int file2 = 0;                                                              //ESCRIBIMOS LOS DAYTOS BYTE A BYTE
    char buff = 0;
    char *PATH_f1 = NULL;
    char *PATH_f2 = NULL;
    formatear_PATH(&PATH_f1,PATH_d1);
    formatear_PATH(&PATH_f2,PATH_d2);
    if((file1 = open(PATH_f1, O_RDONLY)) > 0)
    {
        if((file2 = open(PATH_f2, O_WRONLY | O_TRUNC | O_CREAT | S_IRUSR | S_IROTH | S_IRGRP | S_IWUSR)) > 0)
        {
            off_t tam = lseek(file1,0,SEEK_END);
            lseek(file1,0,SEEK_SET);
            
            for(off_t x = 0; x < tam; x++)
            {
                read(file1, &buff, sizeof(buff));
                write(file2, &buff, sizeof(buff));
            }
            printf("\033[32m(%lld Bytes copiados)\n\033[0m",tam);
        }
        close(file2);
    }
    close(file1);
    
}

void eliminar_archivo (char *PATH_d)
{                                                                               //BORRA UN ARCHIVO EXISTENTE
    char *PATH_f = NULL;
    formatear_PATH(&PATH_f,PATH_d);
    
    if(unlink (PATH_f))
        printf("\033[91mERROR!!! NO SE PUDO ELIMINAR EL ARCHIVO\033[0m\n");
    else
        printf("\033[32m%s -\033[0m\n",PATH_f);
}

void crear_archivo (char *PATH_d)
{                                                                               //CREA UN ARCHIVO NUEVO CON PERMISOS U:RW G:R O:R
    char *PATH_f = NULL;
    formatear_PATH(&PATH_f,PATH_d);
    int fd = 0;
    
    if((fd = creat (PATH_f, S_IRUSR | S_IROTH | S_IRGRP | S_IWUSR)) < 0)
        printf("\033[91mERROR!!! NO SE PUDO CREAR EL ARCHIVO\033[0m\n");
    else
    {
        if (chmod(PATH_f, 07751))
            printf("\033[32m%s +\033[0m\n",PATH_f);
    }
    
    close (fd);
}

void escribir_archivo(char *PATH_d, char *data)
{                                                                               //AÑADE CONTENIDO A UN ARCHIVO YA EXISTENTE
    char buff = 0;
    char *PATH_f = NULL;
    formatear_PATH(&PATH_f,PATH_d);
    
    int fd = 0;
    
    if((fd = open (PATH_f, O_WRONLY | O_APPEND )) < 0)
        printf("\033[91mERROR!!! NO SE PUDO ESCRIBIR EL ARCHIVO\033[0m\n");
    else
    {
        for (int x = 0; x < strlen(data); x++)
        {
            buff = data[x];
            write(fd, &buff, sizeof(buff));
        }
        printf("\033[32m(%lu Bytes escritos)\n\033[0m",strlen(data));
    }
    close(fd);
}

void man (char *data)
{                                                                               //MANUAL DE LA nSHELL
    if (!strcmp(data,"miman"))
    {
        printf("\n\033[93m0.\033[0m Listado de ordenes\n");
        printf("\033[93m1.\033[0m Manual de uso de la consola, introduzca el comando sobre el que quiere obtener información\n");
        printf("\033[93mEscriba & para ver el contenido de la orden introducida\n\n\033[0m");
    }
    else if (!strcmp(data,"mipwd"))
    {
        printf("\n\033[93m0.\033[0m Muestra el PATH actual\n\n");
    }
    else if (!strcmp(data,"mipwd"))
    {
        printf("\n\033[93m0.\033[0m Muestra el contenido del directorio actual\n\n");
        printf("\n\033[93m1.\033[0m Muestra el contenido del directorio\n\n");
        printf("\n\033[93m0 -l.\033[0m Muestra el contenido detallado del directorio actual\n\n");
        printf("\n\033[93m1 -l.\033[0m Muestra el contenido detallado del directorio\n\n");
    }
    else if (!strcmp(data,"mimkdir"))
    {
        printf("\n\033[93m1.\033[0m Crea un directorio\n\n");
    }
    else if (!strcmp(data,"mimkdir"))
    {
        printf("\n\033[93m1.\033[0m Crea un directorio\n\n");
    }
    else if (!strcmp(data,"mirmdir"))
    {
        printf("\n\033[93m1.\033[0m Elimina un directorio\n\n");
    }
    else if (!strcmp(data,"micd"))
    {
        printf("\n\033[93m0.\033[0m Vuelve al directorio de inicio de la Shell\n");
        printf("\033[93m1.\033[0m Cambia de directorio\n\n");
    }
    else if (!strcmp(data,"micat"))
    {
        printf("\n\033[93m1.\033[0m Muestra un archivo\n\n");
    }
    else if (!strcmp(data,"micp"))
    {
        printf("\n\033[93m2.\033[0m Copia el archivo 1 en el archivo 2 borrando el contenido anterior de este o creándolo si no existiera\n\n");
    }
    else if (!strcmp(data,"mirm"))
    {
        printf("\n\033[93m1.\033[0m Elimina un archivo\n\n");
    }
    else if (!strcmp(data,"micreat"))
    {
        printf("\n\033[93m1.\033[0m Crea un archivo\n\n");
    }
    else if (!strcmp(data,"miwrite"))
    {
        printf("\n\033[93m1.\033[0m Añade datos a un archivo\n\n");
    }
    else if (!strcmp(data,"whoami"))
    {
        printf("\n\033[93mAutor de la nShell\033[0m\n\n");
    }
    else if (!strcmp(data,"miexit"))
    {
        printf("\n\033[93m1.\033[0m Cierra la nShell\n\n");
    }
    else
    {
        printf("\n\033[93mListado de comandos: \033[0m\n");
        printf(" miman\n");
        printf(" mipwd\n");
        printf(" mils\n");
        printf(" mimkdir\n");
        printf(" mirmdir\n");
        printf(" micd\n");
        printf(" micat\n");
        printf(" micp\n");
        printf(" mirm\n");
        printf(" micreat\n");
        printf(" miwrite\n");
        printf(" whoami\n");
        printf(" miexit\n\n");
    }
}
