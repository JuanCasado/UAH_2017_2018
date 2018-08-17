#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <stdlib.h>

int main (int argc, const char *argv[])
{
	struct hostent *hp;
	char **p;

	if (argc != 2)
	{
		printf ("Uso %s direccion URL \n", argv[0]);
		exit (1);
	}

	hp=gethostbyname( argv[1] );
	if (hp == NULL)
	{
		printf("No se pude encontar la informacion sobre el equipo %s\n", argv[1]);
		exit (3);
	}

	for (p=hp->h_addr_list; *p != 0; p++)
	{
		struct in_addr in;
		memcpy(&in.s_addr, *p, sizeof(in.s_addr));
		printf("La dirección IP (%s) corresponde a %s \n", inet_ntoa(in),hp->h_name);
	}

	puts("Los alias son :");

	for (p=hp->h_aliases; *p != NULL; p++)
	{
		printf("%s\n",*p);
	}
	exit (0);
}
