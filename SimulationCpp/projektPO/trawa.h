#pragma once
#include "roslina.h"

class Trawa : public Roslina {
public:
	Trawa(int xS, int yS, Swiat* s);
	void wypisz() const override;
	string wypiszNazwe() const override;
	Organizm* dziecko();
};

