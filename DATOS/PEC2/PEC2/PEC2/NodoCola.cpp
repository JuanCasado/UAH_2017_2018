#include "NodoCola.hpp"

NodoCola::NodoCola(Cliente c, NodoCola* sig)
{
	cliente = c;
	siguiente = sig;
}

NodoCola::~NodoCola()
{
}
