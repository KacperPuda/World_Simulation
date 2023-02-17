#pragma once
#include "roslina.h"
class Barszcz : public Roslina
{
public:
	Barszcz(int xS, int yS, Swiat* s);
	void wypisz() const override;
	bool probaRozsiania() override;
	void akcja() override;
	void kolizja(int nX, int nY) override;
	string wypiszNazwe() const override;
	Organizm* dziecko();
};

