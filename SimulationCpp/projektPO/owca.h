#pragma once
#include "zwierze.h"

class Owca : public Zwierze {
public:
	Owca(int x, int y, Swiat* s);
	void wypisz() const override;
	string wypiszNazwe() const override;
	Organizm* dziecko();
};