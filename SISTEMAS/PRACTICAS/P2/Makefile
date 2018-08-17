all: gen_traza contar_ops calcular_cdt sim_pag_aleatorio sim_pag_lru sim_pag_fifo sim_pag_fifo2op

# Ir añadiendo a all: sim_pag_aleatorio sim_pag_lru sim_pag_fifo sim_pag_fifo2op

gen_traza: gen_traza.o ordenar.o ordenar.h
	gcc -g -Wall -o gen_traza gen_traza.o ordenar.o

gen_traza.o: gen_traza.c ordenar.h
	gcc -g -Wall -c -o gen_traza.o gen_traza.c

ordenar.o: ordenar.c ordenar.h
	gcc -g -Wall -c -o ordenar.o ordenar.c

contar_ops: contar_ops.c
	gcc -g -Wall -o contar_ops contar_ops.c

calcular_cdt: calcular_cdt.c
	gcc -g -Wall -o calcular_cdt calcular_cdt.c

sim_pag_aleatorio: sim_pag_aleatorio.o sim_pag_main.o
	gcc -g -Wall -o sim_pag_aleatorio sim_pag_aleatorio.o sim_pag_main.o

sim_pag_aleatorio.o: sim_pag_aleatorio.c sim_paginacion.h
	gcc -g -Wall -c -o sim_pag_aleatorio.o sim_pag_aleatorio.c

sim_pag_lru: sim_pag_lru.o sim_pag_main.o
	gcc -g -Wall -o sim_pag_lru sim_pag_lru.o sim_pag_main.o

sim_pag_lru.o: sim_pag_lru.c sim_paginacion.h
	gcc -g -Wall -c -o sim_pag_lru.o sim_pag_lru.c

sim_pag_fifo: sim_pag_fifo.o sim_pag_main.o
	gcc -g -Wall -o sim_pag_fifo sim_pag_fifo.o sim_pag_main.o

sim_pag_fifo.o: sim_pag_fifo.c sim_paginacion.h
	gcc -g -Wall -c -o sim_pag_fifo.o sim_pag_fifo.c

sim_pag_fifo2op: sim_pag_fifo2op.o sim_pag_main.o
	gcc -g -Wall -o sim_pag_fifo2op sim_pag_fifo2op.o sim_pag_main.o

sim_pag_fifo2op.o: sim_pag_fifo2op.c sim_paginacion.h
	gcc -g -Wall -c -o sim_pag_fifo2op.o sim_pag_fifo2op.c

sim_pag_main.o: sim_pag_main.c sim_paginacion.h
	gcc -g -Wall -c -o sim_pag_main.o sim_pag_main.c

clean:
	rm -f gen_traza.o ordenar.o gen_traza
	rm -f contar_ops
	rm -f calcular_cdt
	rm -f sim_pag_main.o
	rm -f sim_pag_aleatorio.o sim_pag_aleatorio
	rm -f sim_pag_lru.o sim_pag_lru
	rm -f sim_pag_fifo.o sim_pag_fifo
	rm -f sim_pag_fifo2op.o sim_pag_fifo2op

