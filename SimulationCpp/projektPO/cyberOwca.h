#pragma once
#include "zwierze.h"

class CyberOwca : public Zwierze{
private:
	int xb, yb;
public:
	CyberOwca(int xS, int yS, Swiat* s);
	void wypisz() const override;
	string wypiszNazwe() const override;
	void akcja() override;
	Organizm* dziecko();
};

