#define WHOAMI "#Mr.BlissfulGrin\nJuan Casado Ballesteros"

# include <sys/types.h>
# include <dirent.h>
# include <sys/stat.h>
# include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <time.h>
#include <sys/mman.h>
#include <stdio.h>

#define ACTUAL "./"
#define INICIO "~/"
#define RAIZ "/"
#define LEN_PATH 1000

void mostrar_path(void);
void mostrar_directorio(char *PATH_d, int modo);
void crear_directorio(char *PATH_d);
void eliminar_directorio(char *PATH_d);
void crear_path_de_retorno(char **PATH_s);
void cambiar_de_directorio(char *PATH_d);
void mostrar_archivo(char *PATH_f);
void copiar_archivo(char *PATH_d1, char *PATH_d2);
void eliminar_archivo(char *PATH_d);
void crear_archivo(char *PATH_d);
void escribir_archivo(char *PAHT_d, char *data);
void man(char *data);
