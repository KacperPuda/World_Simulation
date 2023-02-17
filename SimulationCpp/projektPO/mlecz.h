#pragma once
#include "roslina.h"

class Mlecz : public Roslina{
public:
	Mlecz(int xS, int yS, Swiat* s);
	void wypisz() const override;
	string wypiszNazwe() const override;
	void akcja() override;
	Organizm* dziecko();
};

