#ifndef COLA_HPP
#define COLA_HPP
#include "NodoCola.hpp"

class Cola
{
public:
	Cola();
	~Cola();

	void insertar (Cliente c);
	Cliente eliminar ();
	void mostrar ();
	bool es_vacia ();
	int cantidad_de_clientes();
	Cliente ver_primero (void);
	
private:
	pnodoCola primero, ultimo;
	int clientes_en_cola;
};

#endif // COLA_HPP