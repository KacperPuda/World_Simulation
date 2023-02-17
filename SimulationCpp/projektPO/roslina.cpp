#include "roslina.h"
#include <iostream>
using namespace std;

void Roslina::akcja() {
	if (probaRozsiania()) {
		Organizm* n = dziecko();
		rozsiej(n);
	}
}

void Roslina::rozsiej( Organizm* n) {
	int nX = x, nY = y, g = BRAKMIEJSC, r;
	int t2[4][2];
	for (int i = SASIADGORA; i < SASIADDOL; i++) {
		if (x + i < POCZATEKPLANSZY || x + i >= s->getXM()) continue;
		for (int j = SASIADLEWO; j < SASIADPRAWO; j++) {
			if (y + j < POCZATEKPLANSZY || y + j >= s->getYM()) continue;

			if ((i == SRODEK && j != SRODEK) || (i != SRODEK && j == SRODEK))

				if (!s->tab[x + i][y + j]) {
					t2[g][XWOLNE] = x + i;
					t2[g][YWOLNE] = y + j;
					g++;
				}
		}
	}
	if (g != BRAKMIEJSC) {
		r = rand() % g;
		nX = t2[r][XWOLNE];
		nY = t2[r][YWOLNE];
	}

	if (s->tab[nX][nY] == nullptr) {
		n->setX(nX);
		n->setY(nY);
		Organizm* obecny = this;
		while (obecny->getNas() != nullptr) {
			obecny = obecny->getNas();
		}
		n->zycie = MLODY;
		n->setPop(obecny);
		obecny->setNas(n);
		s->tab[nX][nY] = n;
		
	}
	else delete[] n;
}

bool Roslina::probaRozsiania() {
	int i = rand() % SZANSANAROZSIA;
	if (i == UDANA)return true;
	return false;
}

void Roslina::kolizja(int nX, int nY) {
	zycie = MARTWY;
	makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMr2);
	s->tab[x][y] = s->tab[nX][nY];
	s->tab[nX][nY] = nullptr;
	s->tab[x][y]->setX(x);
	s->tab[x][y]->setY(y);
}

void Roslina::makeLog(string z1, string z2, string& log, string x, string y, int akcja) {
	if (akcja == KOMr2)log += z1 + " zjada " + z2 +" na pozycji (" + x + ", " + y + ")" + "\n";
	if (akcja == KOMr3)log += z1 + " zjada " + z2 + " i zwieksza swoja sile o 3 na pozycji (" + x + ", " + y + ")" + "\n";
	if (akcja == KOMr4)log += z1 + " zjada " + z2 + " i umiera na pozycji (" + x + ", " + y + ")" + "\n"; 
	if (akcja == KOMr5)log += z1 + " zabija " + z2 + " na pozycji (" + x + ", " + y + ")" + "\n";
}

Roslina::~Roslina() {
}