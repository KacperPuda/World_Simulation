#pragma once
#include "zwierze.h"

class Antylopa : public Zwierze {
public:
	Antylopa(int xS, int yS, Swiat* s);
	void wypisz() const override;
	string wypiszNazwe() const override;
	void akcja() override;
	void kolizja(int nX, int nY) override;
	Organizm* dziecko();
};