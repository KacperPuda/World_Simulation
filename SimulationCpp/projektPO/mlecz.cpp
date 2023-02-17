#include "mlecz.h"
#include <iostream>
using namespace std;

Mlecz::Mlecz(int x, int y, Swiat* s) {
	sila = 0;
	this->x = x;
	this->y = y;
	inicjatywa = 0;
	zycie = ZYWY;
	this->s = s;
}

void Mlecz::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORMLECZA);
	char a = SYMBOLMLECZA;
	cout << a;
}

void Mlecz::akcja() {
	for (int i = 0; i < 3; i++) {
		if (probaRozsiania()) {
			Organizm* n = dziecko();
			rozsiej(n);
		}
	}
}

string Mlecz::wypiszNazwe() const {
	return "mlecz";
}
Organizm* Mlecz::dziecko() {
	return new Mlecz(x, y, s);
}









