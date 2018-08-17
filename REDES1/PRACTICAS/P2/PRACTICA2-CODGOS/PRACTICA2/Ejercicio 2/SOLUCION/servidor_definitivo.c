/* servidor.c */

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#define STDOUT 1
#define SERV_ADDR (IPPORT_RESERVED+1)

int main()
{
int rval;
int sock,length,msgsock;
struct sockaddr_in server; //server contendrá la dirección del servidor
struct sockaddr_in cliente;
char buf[1024];
struct hostent *hp; //puntero para leer sin modificar

sock=socket(AF_INET, SOCK_STREAM,0);

if (sock<0)
  {
    perror("No hay socket de escucha");
    exit(1);
  }

server.sin_family=AF_INET;
server.sin_addr.s_addr=htonl(INADDR_ANY);	//contiene la dirección IP de la maquina	
server.sin_port = htons(SERV_ADDR);		//contiene el número de puerto

if (bind(sock,(struct sockaddr *)&server, sizeof(server))<0) //bind asigna una dirección 
  {							     //determinada al socket
    perror("Direccion no asignada");
    exit(1);
  }

listen(sock,1);	//queda a la escucha de conexiones
while (1)
 {
  socklen_t tam = sizeof(cliente);
  /*Estará bloqueado esperando petición de conexión*/
  msgsock = accept(sock, (struct sockaddr *) &cliente, &tam); //se rellena aqui
  hp=gethostbyaddr((char *)&cliente.sin_addr.s_addr, sizeof(cliente.sin_addr.s_addr), AF_INET);
	//AF_INET --> ambito TCP

  if (msgsock==-1)
     perror("Conexion no aceptada");
  else{
    printf("Numero de puerto: %d\n", server.sin_port);
    printf("IP: %s\n", inet_ntoa(cliente.sin_addr));
	//inet_ntoa convierte de datos de internet a datos de la máquina
	printf("Nombre: %s\n", hp->h_name);
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
  }
exit(0);
}
