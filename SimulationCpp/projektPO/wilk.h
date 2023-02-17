#pragma once
#include "zwierze.h"

class Wilk : public Zwierze {
	public:
		Wilk(int xS, int yS, Swiat* s);
		void wypisz() const override;
		string wypiszNazwe() const override;
		Organizm* dziecko();
};