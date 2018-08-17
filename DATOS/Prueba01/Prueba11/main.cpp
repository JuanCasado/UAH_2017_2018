#include <iostream>

using namespace std;

int main(int argc, char **argv)
{
	struct Hora
	{
		int hh;
		int mm;
		int ss;
	};
	
	Hora h, v[10], *p;
	
	h.hh = 8;
	p = &h;
	v[3].hh = 5;
	v[7].hh = (*p).hh;
	cout << p->hh << h.hh << v[7].hh << (*p).hh;
}


