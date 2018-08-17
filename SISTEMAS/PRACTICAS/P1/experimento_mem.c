#include <stdio.h>
#include "mem_dinamica.h"

void hola(void);		//no recibe nada y no devuelve nada
void cadenas ( void );		
int tres ( void );		//no recibe nada y devuelve un entero
int factorial ( int );		//recibe un entero y devuelve otro

int triple ( int x )
{
	printf ( " Función triple (): %p\n \tValor de x : %d\n \tDirección de x : %p" , &triple , x , &x );
	return 3 * x ; 
}

int a = 34;
int b;
int c = -5;
float d;
int D;

int main ( int argc , char * argv [])
{
	int x = 3, y;
	hola();
	printf ( " Función printf (): %p\n " , & printf );
	printf ( " \nFunción main (): %p\n " , & main );
	printf ( " \n \tVariables globales ( dir . , nombre , valor ):\n " );
	printf ( " \t \t %p a %d\n " , &a , a );
	printf ( " \t \t %p b %d\n " , &b , b );
	printf ( " \t \t %p c %d\n " , &c , c );
	printf ( " \t \t %p d %f\n " , &d , d );
	printf ( " \t \t %p D %d\n " , &D , D );
	printf ( " \t \t %p k %d\n " , &k , k );
	printf ( " \n \tVariables locales ( dir . , nombre , valor ):\n " );
	printf ( " \t \t %p x %d\n " , &x , x );
	printf ( " \t \t %p y %d\n " , &y , y );
	printf ( " \n \tCalculando 3! ...\n " );
	
	x = factorial ( tres ());
	printf ( " \t3 ! == %d\n " , x );

	mem_dinamica ();		//funcion del otro .c
	cadenas ();

	while(1);

	return 0;
}

void hola ( void )
{
	printf ( "¡Hola mundo!\n " );
}


int tres (void)
{
	return 3;
}

int factorial (int n)
{
	int f;

	printf ( " \tFunción factorial ( %d ): %p\n " , n , & factorial );
	printf ( " \t \tParámetro n ( dir . , valor ): %p %d\n " , &n , n );
	printf ( " \t \tVariable f ( dir . , valor ): %p %d\n " , &f , f );
	f = n <2 ? 1 : n * factorial (n -1);
	printf ( " \t \tfactorial ( %d ) devolviendo %d\n " , n , f );

	return f;
}

void cadenas ( void )
{
	char a [] = " hola " ;
	char b [] = { 'h' , 'o', 'l', 'a', '\0'};

	//Esto no es un array, sino un puntero:
	char * c = " hasta luego " ;

	/*En este momento, el puntero c está apuntando a una cadena literal (constante, que
	se puede leer , pero no escribir). Una instrucción como *c='H' provocaría un error 
	en tiempo de ejecución*/

	printf ( " \n \tFunción cadenas (): %p\n " , & cadenas );
	printf ( " \t \ta : %p \" %s \"\n" , a , a );
	printf ( " \t \tb : %p \" %s \"\n" , b , b );
	printf ( " \t \tc : %p \" %s \"\n" , c , c );
	printf ( " \t \t & c : %p\n " , & c );
	printf ( " \t \tJugando un poco con c ...\n " );

	c=a;		//Ahora c apuntará al comienzo del array a
	*c='H';		//Ya si puede modificar *c porque ahora c apunta a una zona de 
			//memoria en la que se puede escribir

	printf ( " \t \ta : %p \" %s \"\n" , a , a );
	printf ( " \t \tb : %p \" %s \"\n" , b , b );
	printf ( " \t \tc : %p \" %s \"\n" , c , c );
	printf ( " \t \t & c : %p\n " , & c );
}
