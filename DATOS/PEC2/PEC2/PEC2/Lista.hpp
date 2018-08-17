#ifndef LISTA_HPP
#define LISTA_HPP

#include "NodoLista.hpp"

class Lista
{
	public:
		Lista();
		~Lista();
		
		void insertar (Cliente c);
		Cliente eliminar ();
		void mostrar ();
		bool es_vacia ();
		int cantidad_de_clientes();
		void vaciar();
		void duplicar(Cliente * c, int longitud);
		
	private:
		pnodoLista puntero, primero;
		int clientes_en_lista;
		void insertar_delante(pnodoLista n);
		void insertar_detras(pnodoLista n);
		void insertar_primero(pnodoLista n);

};

#endif // LISTA_HPP
