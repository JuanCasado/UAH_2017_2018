CC = gcc
CF = -g -Wall -pedantic
LC = ar
LF = -r

all: contar DATOS.txt

DATOS.txt: generar_datos 
	./generar_datos

generar_datos: generar_datos.o
	$(CC) $(CF) generar_datos.o  -o generar_datos

generar_datos.o: generar_datos.c
	$(CC) $(CF) -c generar_datos.c

contar: principal.o libcontar.a
	$(CC) $(CF) principal.o -L./ -l contar -o contar

principal.o: principal.c leercar_R.h contar_M.h
	$(CC) $(CF) -c principal.c

libcontar.a: contar_M.o leercar_R.o
	$(LC) $(LF) libcontar.a contar_M.o leercar_R.o

contar_M.o: contar_M.c contar_M.h
	$(CC) $(CF) -c contar_M.c

leer_R.o: leercar_R.c leercar_R.h
	$(CC) $(CF) -c leer_R.c

.PHONY: clean

clean:
	rm -f generar_datos contar DATOS.txt *.o libcontar.a
