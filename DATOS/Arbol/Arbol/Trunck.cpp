#include "Trunck.hpp"

Trunck::Trunck(Trunck * prevx, string strx)
{
	str = strx;
	prev = prevx;
}

void Trunck::showTruncks(Trunck *p)
{
	if (p == nullptr)
		return;

	showTruncks(p->prev);

	cout << p->str;
}

Trunck::~Trunck()
{
}

