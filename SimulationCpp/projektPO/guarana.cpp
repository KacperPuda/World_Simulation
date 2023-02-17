#include "guarana.h"
#include <iostream>
using namespace std;

Guarana::Guarana(int x, int y, Swiat* s) {
	sila = 0;
	this->x = x;
	this->y = y;
	zycie = ZYWY;
	this->s = s;
}

bool Guarana::probaRozsiania() {
	int i = rand() % SZANSANAROZSIANIEGUARANY;
	if (i == UDANA)return true;
	return false;
}

void Guarana::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORGUARANY);
	char a = SYMBOLGUARANY;
	cout << a;
}

void Guarana::kolizja(int nX, int nY) {
	zycie = MARTWY;
	makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMr3);
	s->tab[x][y] = s->tab[nX][nY];
	s->tab[nX][nY] = nullptr;
	s->tab[x][y]->setX(x);
	s->tab[x][y]->setY(y);
	s->tab[x][y]->setSila(ZWIEKSZo3);
}

string Guarana::wypiszNazwe() const {
	return "guarana";
}
Organizm* Guarana::dziecko() {
	return new Guarana(x, y, s);
}
