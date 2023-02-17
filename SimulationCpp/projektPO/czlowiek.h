#pragma once
#include "zwierze.h"
class Czlowiek :public Zwierze{
public:
	Czlowiek(int xS, int yS, Swiat* s);
	void wypisz() const override;
	string wypiszNazwe() const override;
	void kolizja(int nX,int nY) override;
	void akcja() override;
	Organizm* dziecko();

};

