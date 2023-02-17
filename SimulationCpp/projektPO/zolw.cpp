#include"zolw.h"
#include <iostream>
using namespace std;

Zolw::Zolw(int x, int y, Swiat* s) {
	sila = 2;
	this->x = x;
	this->y = y;
	inicjatywa = 1;
	zycie = 1;
	this->s = s;
}

void Zolw::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORZOLWIA);
	cout << "8";
}

void Zolw::akcja() {
	int nX = x, nY = y;
	int i = rand() % SZANSANARUCH;
	if (i == UDANA) {
		ruch(nX, nY);
		tura(nX, nY);
	}
}

void Zolw::kolizja(int nX, int nY) {
	makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(nX), to_string(nY), KOMz7);
	if (s->tab[nX][nY]->getSila() < 5) {
		makeLog(wypiszNazwe(), s->tab[nX][nY]->wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz3);
		return;
	}
	if (s->tab[nX][nY]->getSila() > sila || s->tab[nX][nY]->getSila() == sila) {
		zycie = 0;
		makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz1);
		s->tab[x][y] = s->tab[nX][nY];
		s->tab[nX][nY] = nullptr;
		s->tab[x][y]->setX(x);
		s->tab[x][y]->setY(y);
	}
	else {
		if (s->tab[nX][nY]->getSila() < sila) {
			makeLog(wypiszNazwe(), s->tab[nX][nY]->wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz1);
			s->tab[nX][nY]->zycie = MARTWY;
			s->tab[nX][nY] = nullptr;
		}
	}
}

string Zolw::wypiszNazwe() const {
	return "zolw";
}
Organizm* Zolw::dziecko() {
	return new Zolw(x, y, s);
}









