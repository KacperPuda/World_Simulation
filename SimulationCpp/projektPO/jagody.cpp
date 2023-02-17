#include "jagody.h"
#include <iostream>
using namespace std;

Jagody::Jagody(int x, int y, Swiat* s) {
	sila = 99;
	this->x = x;
	this->y = y;
	zycie = ZYWY;
	this->s = s;
}

bool Jagody::probaRozsiania() {
	int i = rand() % SZANSANAROZSIANIEJAGOD;
	if (i == UDANA)return true;
	return false;
}

void Jagody::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORJAGOD);
	char a = SYMBOLJAGOD;
	cout << a;
}

void Jagody::kolizja(int nX, int nY) {
	zycie = MARTWY;
	s->tab[nX][nY]->zycie = MARTWY;
	makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMr4);
	s->tab[nX][nY] = nullptr;
	s->tab[x][y] = nullptr;
}

string Jagody::wypiszNazwe() const {
	return "wilcze jagody";
}
Organizm* Jagody::dziecko() {
	return new Jagody(x, y, s);
}
