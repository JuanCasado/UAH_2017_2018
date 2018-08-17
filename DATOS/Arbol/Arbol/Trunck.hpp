#ifndef TRUNCK_HPP
#define TRUNCK_HPP

#include <iostream>
#include <string>
using namespace std;

class Trunck
{
	public:
		Trunck *prev;
		string str;
		
		void showTruncks(Trunck *p);
		
		Trunck(Trunck * prevx, string strx);
		~Trunck();
		

};

#endif // TRUNCK_HPP
