/* servidor.c */

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#define STDOUT 1
#define SERV_ADDR (IPPORT_RESERVED+1)

int main()
{
int rval;
int sock,length,msgsock;
struct sockaddr_in server;
char buf[1024];

sock=socket(AF_INET, SOCK_STREAM,0);

if (sock<0)
  {
    perror("No hay socket de escucha");
    exit(1);
  }

server.sin_family=AF_INET;
server.sin_addr.s_addr=htonl(INADDR_ANY);
server.sin_port = htons(SERV_ADDR);

if (bind(sock,(struct sockaddr *)&server, sizeof(server))<0)
  {
    perror("Direccion no asignada");
    exit(1);
  }

listen(sock,1);
while (1)
 {
  /*Estar� bloqueado esperando petici�n de conexi�n*/
  msgsock = accept(sock, (struct sockaddr *)0, (int *) 0);

  if (msgsock==-1)
     perror("Conexion no aceptada");
  else
    do
     {
      /*Me dispongo a leer datos de la conexion*/
      memset(buf,0,sizeof(buf));
      rval=read(msgsock,buf,1024);

      if (rval<0)
        perror("Mensaje no le�do");
      else
        write(STDOUT,buf,rval);
      }
     while (rval>0);
  
   printf("\nConexion cerrada\n");
   close(msgsock);
  }
exit(0);
}
