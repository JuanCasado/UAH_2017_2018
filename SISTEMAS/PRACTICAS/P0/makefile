
mostrar_caracter: mostrar_caracter.o buscar_caracter.o
	gcc mostrar_caracter.o buscar_caracter.o -g -Wall -o mostrar_caracter


mostrar_caracter.o: mostrar_caracter.c buscar_caracter.h
	gcc mostrar_caracter.c -g -Wall -c -o mostrar_caracter.o

buscar_caracter.o: buscar_caracter.c buscar_caracter.h
	gcc buscar_caracter.c -g -Wall -c -o buscar_caracter.o
