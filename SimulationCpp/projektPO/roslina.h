#pragma once
#include "organizm.h"


class Roslina : public Organizm {
public:
	virtual Organizm* dziecko() = 0;
	virtual bool probaRozsiania();
	virtual void kolizja(int nX, int nY);
	virtual void akcja();
	void rozsiej(Organizm* n);
	void makeLog(string z1, string z2, string& log, string x, string y, int akcja);
	virtual ~Roslina();
};
