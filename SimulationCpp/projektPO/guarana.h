#pragma once
#include "roslina.h"
class Guarana : public Roslina
{
public:
	Guarana(int xS, int yS, Swiat* s);
	void wypisz() const override;
	bool probaRozsiania() override;
	void kolizja(int nX, int nY) override;
	string wypiszNazwe() const override;
	Organizm* dziecko();
};

