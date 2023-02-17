#pragma once
#include "zwierze.h"

class Zolw : public Zwierze {
public:
	Zolw(int xS, int yS, Swiat* s);
	void wypisz() const override;
	string wypiszNazwe() const override;
	void akcja() override;
	void kolizja(int nX, int nY) override;
	Organizm* dziecko();
};