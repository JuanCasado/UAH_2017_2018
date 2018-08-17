#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <pwd.h>

int main (void)
{
  char *login;
  struct passwd *pentry;

  /*login de sesión*/
  if ((login = getlogin()) == NULL){
    perror("getlogin");
    exit(EXIT_FAILURE);
  }

  /*Contraseña de sesión*/
  if ((pentry = getpwnam(login)) == NULL){
    perror("getpwnam");
    exit(EXIT_FAILURE);
  }

  /*Se muestran los datos de la sesión en pantalla*/
  printf("user name: %sn", pentry ->pw_name);
  printf("UID      : %dn", pentry ->pw_uid);
  printf("GID      : %dn", pentry ->pw_gid);
  printf("gecos    : %sn", pentry ->pw_gecos);
  printf("home dir : %sn", pentry ->pw_dir);

  exit(EXIT_SUCCESS);    
}
