#ifndef GESTOR_HPP
#define GESTOR_HPP

#include <Arbol.hpp>
#include <ctime>

#define LEN_LISTA 10
#define RANGE_LISTA 500

class Gestor
{
	public:
		Gestor();
		
		void generar_arbol();
		void buscar();
		void dibujar();
		
		~Gestor();
		
	private:
		void generar_lista(void);
		void ordenar_lista(void);
		int menor (int inicio);
		int lista [LEN_LISTA];
		Arbol *arbol;
};

#endif // GESTOR_HPP
