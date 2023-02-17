#pragma once
#include "zwierze.h"

class Lis : public Zwierze {
public:
	Lis(int xS, int yS,Swiat *s);
	string wypiszNazwe() const override;
	void wypisz() const override;
	void akcja() override;
	Organizm* dziecko();
};