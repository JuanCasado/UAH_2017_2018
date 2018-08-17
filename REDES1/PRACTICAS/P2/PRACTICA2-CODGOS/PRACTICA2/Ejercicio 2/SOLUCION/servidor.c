/* servidor.c */

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <unistd.h>
#define STDOUT 1
#define SERV_ADDR (IPPORT_RESERVED+1)

int main()
{
int rval;
int sock,length,msgsock;
struct sockaddr_in server, cliente;
char buf[1024];
struct hostent *hp;
u_long addr;
socklen_t longitud_cliente = sizeof(cliente);


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
  /*Estará bloqueado esperando petición de conexión*/
  msgsock = accept(sock, (struct sockaddr *) &cliente, (int*)0);

  if (msgsock==-1)
     perror("Conexion no aceptada");
  else
	//PRACTICA 2
        puts("CLIENTE CONECTADO");
        printf("\tSu nÃºmero de puerto es: %d\n", cliente.sin_port);
        printf("\tSu IP: %s\n", inet_ntoa(cliente.sin_addr));
        addr = cliente.sin_addr.s_addr;
        hp = gethostbyaddr((char*) &addr,sizeof(addr),AF_INET);
	printf("\tSu nombre: %s", hp->h_name);
    do
     {
      /*Me dispongo a leer datos de la conexion*/
      memset(buf,0,sizeof(buf));
      rval=read(msgsock,buf,1024);

      if (rval<0)
        perror("Mensaje no leído");
      else
        write(STDOUT,buf,rval);
      }
     while (rval>0);

   printf("\nConexion cerrada\n");
   close(msgsock);
  }
exit(0);
}
