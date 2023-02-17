#include "zwierze.h"
#include <iostream>
using namespace std;

void Zwierze::tura( int nX,int nY) {
	if (!s->tab[nX][nY]) {
		s->tab[nX][nY] = s->tab[x][y];
		s->tab[x][y] = nullptr;
		x = nX;
		y = nY;
	}
	else {
		if (s->tab[nX][nY]->wypiszNazwe() == wypiszNazwe()) {
			if (plodnosc == PLODNY && s->tab[nX][nY]->plodnosc == PLODNY && this != s->tab[nX][nY]) {
				plodnosc = BEZPLODNY;
				s->tab[nX][nY]->plodnosc = BEZPLODNY;
				Organizm* n = dziecko();
				reprodukcja( nX, nY, n);
			}
		}
		else {
			s->tab[nX][nY]->kolizja(x, y);
		}
	}
}

void Zwierze::akcja() {
	int nX = x, nY = y;
	ruch( nX, nY);
	tura(nX, nY);
}

void Zwierze::ruch(int &nX,int &nY) {
	int  co, gdzie;
	co = rand() % COZMIENIA;
	gdzie = rand() % 2;
	if (gdzie == TYL) gdzie = DOTYLU;
	if (co == ZMIENIAX) {
		nX += gdzie;
	}
	else {
		nY += gdzie;
	}
	if (nY >= s->getYM() || nY < POCZATEKPLANSZY) nY -= WDRUGASTRONE * gdzie;
	if (nX >= s->getXM() || nX < POCZATEKPLANSZY) nX -= WDRUGASTRONE * gdzie;
	if (nY >= s->getYM() || nY < POCZATEKPLANSZY) nY +=  gdzie;
	if (nX >= s->getXM() || nX < POCZATEKPLANSZY) nX +=  gdzie;
}

void Zwierze::reprodukcja(int nX,int nY, Organizm* n) {
	int pX = x, pY = y;
	n->zycie = MLODY;
	n->plodnosc = NIEDOJRZALY;
	bool miejsce = false;
	for (int z = 0; z < ILOSCRODZICOW; z++) {
		if (!miejsce) {
			if (z == DRUGIRODZIC) {
				pX = nX;
				pY = nY;
			}
			for (int i =SASIADGORA; i < SASIADDOL; i++) {
			if (pX + i < POCZATEKPLANSZY || pX + i >= s->getXM())continue;
				for (int j =SASIADLEWO; j < SASIADPRAWO; j++) {
					if (pY + j <POCZATEKPLANSZY || pY + j >= s->getYM())continue;
					if (j == SRODEK && i != SRODEK || j != SRODEK && i == SRODEK) {
						if (!s->tab[pX + i][pY + j] && !miejsce) {
							miejsce = true;
							n->setX(pX + i);
							n->setY(pY + j);
							s->tab[pX + i][pY + j] = n;
							makeLog(wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz2);
							makeLog(wypiszNazwe(), wypiszNazwe(), s->log, to_string(pX + i), to_string(pY + j), KOMz6);
						}
					}
				}
			}
		}
	}
	if (miejsce) {
		Organizm* obecny = this;
		while (obecny->getNas() != nullptr) {
			if (obecny->getNas()->getInicjatywa() < n->getInicjatywa()) break;
			obecny = obecny->getNas();
		}
		n->setPop(obecny);
		if (obecny->getNas()) {
			n->setNas(obecny->getNas());
			obecny->getNas()->setPop(n);
		}
		obecny->setNas(n);
		s->tab[nX][nY]->zycie = MLODY;
	}
	else delete[]n;
}

void Zwierze::kolizja( int nX, int nY) {
	makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(nX), to_string(nY), KOMz7);


	if (s->tab[nX][nY]->getSila() > sila || s->tab[nX][nY]->getSila() == sila) {
		zycie=MARTWY;
		makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y),KOMz1);
		s->tab[x][y] = s->tab[nX][nY];
		s->tab[nX][nY] = nullptr;
		s->tab[x][y]->setX(x);
		s->tab[x][y]->setY(y);
	}
	else {
		if (s->tab[nX][nY]->getSila() < sila) {
			makeLog(wypiszNazwe(), s->tab[nX][nY]->wypiszNazwe(),  s->log, to_string(x), to_string(y),KOMz1);
			s->tab[nX][nY]->zycie =MARTWY;
			s->tab[nX][nY] = nullptr;
		}
	}
}

void Zwierze::makeLog(string z1, string z2, string& log,string x,string y, int akcja) {
	if (akcja == KOMz1)log += z1 + " zabija " + z2 + " na pozycji (" + x + "," + y + ")" + "\n";
	if (akcja == KOMz2)log += z1 + " reprodukuje " + z2 + " na pozycji (" + x + "," + y + ")" + "\n";
	if (akcja == KOMz6)log += z1 + " rodzi sie na pozycji (" + x + "," + y + ")" + "\n";
	if (akcja == KOMz3)log += z1 + " odbija " + z2 + " na pozycji (" + x + "," + y + ")" + "\n";
	if (akcja == KOMz4)log += z1 + " ucieka od " + z2 + " na pozycji (" + x + "," + y + ")" + "\n";
	if (akcja == KOMz5)log += z1 + " jest niesmiertelny nie walczy z " + z2 + " przenosi sie na pozycje (" + x + "," + y + ")" + "\n";
	if (akcja == KOMz7)log += z1 + " atakuje (" + x + "," + y + ")" + "\n";
}

Zwierze::~Zwierze() {
}