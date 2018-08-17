CC = gcc
CF = -g -Wall -pedantic

all: Shell

Shell: main.o parser.o manejador_de_directorios.o
	$(CC) $(CF) main.o parser.o manejador_de_directorios.o -o Shell

main.o: main.c
	$(CC) $(CF) -c main.c

parser.o: parser.c parser.h
	$(CC) $(CF) -c parser.c

manejador_de_directorios.o: manejador_de_directorios.c manejador_de_directorios.h
	$(CC) $(CF) -c manejador_de_directorios.c

.PHONY: clean

clean:
	rm -f Shell *.o
