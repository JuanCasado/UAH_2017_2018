CC = gcc
CF = -g -Wall -pedantic

all:open

open: open.o
	$(CC) $(CF)  open.o -o open

open.o: open.c
	$(CC) $(CF) -c open.c

.PHONY: clean
clean:
	rm -f open *.o
