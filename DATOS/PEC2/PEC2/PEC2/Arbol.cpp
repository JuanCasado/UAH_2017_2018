#include "Arbol.hpp"

Arbol::Arbol(pnodoArbol r)
{
	raiz = r;
	acumulador_tiempo_estacia = 0;
	acumulador_tiempo_procesamiento = 0;
}

void Arbol::generar(Cliente * c, int longitud)
{
	pnodoArbol nuevoNodo = NULL;
	char* nuevoDNI;
	for (int x = 0; x < longitud; x++)
	{
		nuevoNodo = new NodoArbol(c[x]);
		nuevoDNI = c[x].get_DNI();

		generar_recursivo(nuevoNodo,nuevoDNI,raiz);
	}
	
}

void Arbol::generar_recursivo(pnodoArbol nuevoNodo, char* nuevoDNI, pnodoArbol arbol)
{
	if (strcmp(nuevoDNI, arbol->cliente.get_DNI()) <= 0)
		if (arbol->izquierda)
			generar_recursivo(nuevoNodo,nuevoDNI,arbol->izquierda);
		else
			arbol->izquierda = nuevoNodo;
	else 
		if (arbol->derecha)
			generar_recursivo(nuevoNodo,nuevoDNI,arbol->derecha);
		else
			arbol->derecha = nuevoNodo;
}

Cliente Arbol::buscar(char* DNI)
{
	return buscar_recursivo(raiz,DNI);
}

Cliente Arbol::buscar_recursivo(pnodoArbol arbol, char* DNI)
{
	if (arbol == NULL)
		return Cliente(-1,1);
	else
	{
		if (!strcmp(arbol->cliente.get_DNI(),DNI))
		{
			return arbol->cliente;
		}
		else if (strcmp(arbol->cliente.get_DNI(),DNI) > 0)
			return buscar_recursivo(arbol->izquierda,DNI);
		else
			return buscar_recursivo(arbol->derecha,DNI);
	}
	return Cliente(-1,1);
}

void Arbol::draw()
{
	cout << endl << endl;
	draw_recursivo(raiz,"\t\t",true);
	cout << endl << endl;
}

void Arbol::draw_recursivo(pnodoArbol arbol, string indent, bool is_tail)
{
	Colores C;
	if (arbol == raiz)
	{
		C.colorear(ROJO);
		#if WINDOWS
		cout << "\t(DNI: 49999999-M)"<< endl << indent << "\xBA  "<< endl;
		#else
		cout << "\t(DNI: 49999999-M)"<< endl << indent << "║  "<< endl;
		#endif
		C.blanco();
	}
	else
	{
		C.colorear(AMARILLO);
		#if WINDOWS
		cout << indent << (is_tail ? "\xC8\xCD": "\xCC\xCD");
		#else
		cout << indent << (is_tail ? "╚═": "╠═");
		#endif
		C.blanco();
		if (arbol == NULL) 
		{
			C.colorear(ROJO);
			cout << "*" << endl;
			C.blanco();
			return;
		}
		else
		{
			C.colorear(GRIS);
			arbol->cliente.show_cliente_completo_formato_reducido();
			C.blanco();
		}
	}

	if (arbol->izquierda != NULL or arbol->derecha != NULL) 
	{
		if (arbol!= raiz)
		{
			#if WINDOWS
			indent.append(is_tail ? "  " : "\xBA  ");
			#else
			indent.append(is_tail ? "  " : "║  ");
			#endif
		}
		draw_recursivo(arbol->derecha, indent, false);
		if (arbol->izquierda)
			draw_recursivo(arbol->izquierda, indent, true);
		else
			draw_recursivo(arbol->izquierda, indent, false);
	}
}

void Arbol::show_pre_orden()
{
	cout << endl << endl;
	show_pre_orden_recursivo(raiz);
	cout << endl << endl;
}

void Arbol::show_pre_orden_recursivo(pnodoArbol arbol)
{
	if (arbol != NULL)
	{
		if (arbol == raiz)
		{
			Colores C;
			C.colorear(MORADO);
			cout << "\t\tRAIZ_SIMBOLICA" << endl;
			C.colorear(GRIS);
		}
		else
			arbol->cliente.show_cliente_completo();
		show_pre_orden_recursivo(arbol->izquierda);
		show_pre_orden_recursivo(arbol->derecha);
	}
}

void Arbol::show_post_orden()
{
	cout << endl << endl;
	show_post_orden_recursivo(raiz);
	cout << endl << endl;
}

void Arbol::show_post_orden_recursivo(pnodoArbol arbol)
{
	if (arbol != NULL)
	{
		show_post_orden_recursivo(arbol->izquierda);
		show_post_orden_recursivo(arbol->derecha);
		if (arbol == raiz)
		{
			Colores C;
			C.colorear(MORADO);
			cout << "\t\tRAIZ_SIMBOLICA" << endl;
			C.colorear(GRIS);
		}
		else
			arbol->cliente.show_cliente_completo();
	}
}

void Arbol::show_in_orden()
{
	cout << endl << endl;
	show_in_orden_recursivo(raiz);
	cout << endl << endl;
}

void Arbol::show_in_orden_recursivo(pnodoArbol arbol)
{
	if (arbol != NULL)
	{
		show_in_orden_recursivo(arbol->izquierda);
		if (arbol == raiz)
		{
			Colores C;
			C.colorear(MORADO);
			cout << "\t\tRAIZ_SIMBOLICA" << endl;
			C.colorear(GRIS);
		}
		else
			arbol->cliente.show_cliente_completo();
		show_in_orden_recursivo(arbol->derecha);
	}
}

void Arbol::vaciar()
{
	eliminar_recursivo_post_orden(raiz);
}

void Arbol::eliminar_recursivo_post_orden(pnodoArbol nodo)
{
	if (nodo)
	{
		eliminar_recursivo_post_orden(nodo->izquierda);
		eliminar_recursivo_post_orden(nodo->derecha);
		delete nodo;
		nodo = NULL;
	}
}

void Arbol::generar_estadisticas()
{
	generar_estadisticas_recursivo(raiz);
}

void Arbol::generar_estadisticas_recursivo(pnodoArbol arbol)
{
	if (arbol)
	{
		generar_estadisticas_recursivo(arbol->izquierda);
		generar_estadisticas_recursivo(arbol->derecha);
		acumulador_tiempo_procesamiento += arbol->cliente.get_tiempo_de_procesamiento();
		acumulador_tiempo_estacia += arbol->cliente.get_tiempo_de_compra();
	}
}

long Arbol::get_tiempo_de_procesamiento()
{
	return acumulador_tiempo_procesamiento;
}
long Arbol::get_tiempo_de_estancia()
{
	return acumulador_tiempo_estacia;
}


Arbol::~Arbol()
{
	vaciar();
}

