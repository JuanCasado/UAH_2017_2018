#include <stdio.h>
#include <stdlib.h>

#include "mem_dinamica.h" /*Se incluye el .h de este módulo para asegurar que las
			    declaraciones concuerdadn con las definiciones*/

//Variable global "prometida" en el .h con la declaración extern:

int k;

//Función que experimenta con memoria dinámica:

void mem_dinamica (void){
	int *p; //p es un puntero a entero, *p es un entero

	char *q, *r, *s; 
	
	printf("\n \tFunción mem_dinamica(): %p\n", &mem_dinamica);
	
	printf("\n \tVariables locales (dir., nombre, valor):\n");
	printf("\t \t%p  p  %p\n", &p, p);
	printf("\t \t%p  q  %p\n", &q, q);
	printf("\t \t%p  r  %p\n", &r, r);
	printf("\t \t%p  s  %p\n", &s, s);

	//Pedimos memoria dinámica (malloc=="allocate memory");

	p = (int*) malloc(7 * sizeof(int));	//7 enteros
	q = (char*) malloc(37 * sizeof(char));	//37 char
	r = (char*) malloc(5 * sizeof(char));	//5 char
	s = (char*) malloc(sizeof(char));	//1 char 
	
	/*(int*) y (char*) son conversiones de tipo necesarias porque malloc reserva bytes a granel, y devuelve un puntero genérico (void*), así que lo convertimos al tipo adecuado antes de hacer la asignación.*/

	if (p==NULL || q==NULL || r==NULL || s==NULL){
		if(p!=NULL) free (p);	//Si alguna reserva falló
		if(q!=NULL) free (q);	//deshacer las demás y 
		if(r!=NULL) free (r);	//salir de esta función
		if(s!=NULL) free (s);
		return;
	}

	printf("\n \tBloques de memoria reservados:\n");
	printf("\t \tp (7 enteros): %p\n", p);
	printf("\t \tq (37 enteros): %p\n", q);
	printf("\t \tr (5 char): %p\n", r);
	printf("\t \ts (1 char): %p\n", s);

	printf("\n \tAritmética de punteros:\n");
	printf("\t \tp = %p \t p+1 = %p\n", p, p+1);
	printf("\t \tp = %p \t q+1 = %p\n", q, q+1);

	*r= 'J';	//*r es lo mismo que r[0]
	r[1]='a';	//r[1] es lo mismo que *(r+1)
	r[2]= 'u';
	r[3]= '\0';	//marca el fin de cadena

	printf("\n\t ¡%s, rostro pálido!\n", r);

	printf("\n \tDualidad caracter/numero del tipo char:\n");
	printf("\t \t*r es un char, y vale %d\n", *r);
	printf("\t \tpero también vale %c\n", *r);
	
	free (p);
	free (q);
	free (r);
	free (s);
}




