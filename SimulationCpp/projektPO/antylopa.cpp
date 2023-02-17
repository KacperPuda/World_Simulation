#include"antylopa.h"
#include <iostream>
using namespace std;

Antylopa::Antylopa(int x, int y, Swiat* s) {
	sila = 4;
	this->x = x;
	this->y = y;
	inicjatywa = 4;
	zycie = ZYWY;
	this->s = s;
}

void Antylopa::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORANTYLOPY);
	cout << "A";
}

void Antylopa::akcja() {
	int nX = x, nY = y, sx = x, sy = y;
	ruch(nX, nY);
	if (s->tab[nX][nY]==nullptr) {
		tura(nX, nY);
		int tmpX = nX;
		int tmpY = nY;
		nX = nX - sx;
		nY = nY - sy;
		nX = sx + 2 * nX;
		nY = sy + 2 * nY;
		if (nX < POCZATEKPLANSZY || nY < POCZATEKPLANSZY || nY >= s->getYM() || nX >= s->getXM()) {
			nX = tmpX;
			nY = tmpY;
		}
	}
	if(s->tab[nX][nY]!=this)tura(nX, nY);
}

void Antylopa::kolizja(int nX, int nY) {
	makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(nX), to_string(nY), KOMz7);
	if (s->tab[nX][nY]->getSila() > sila || s->tab[nX][nY]->getSila() == sila) {
		int ucieczka = rand() % SZANSAUCIECZKI;
		if (ucieczka == NIEUDANA) {
			zycie = MARTWY;
			makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz1);
			s->tab[x][y] = s->tab[nX][nY];
			s->tab[nX][nY] = nullptr;
			s->tab[x][y]->setX(x);
			s->tab[x][y]->setY(y);
		}
		else {
			makeLog(wypiszNazwe(), s->tab[nX][nY]->wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz4);
			int Ux = x, Uy = y;
			bool znaleziono = false;
			for (int i = SASIADGORA; i < SASIADDOL; i++) {
				if (x + i < POCZATEKPLANSZY || x + i >= s->getXM()) continue;
				for (int j = SASIADLEWO; j < SASIADPRAWO; j++) {
					if (y + j < POCZATEKPLANSZY || y + j >= s->getYM()) continue;
					if ((i != SRODEK && j == SRODEK) || (i == SRODEK && j != SRODEK)) {
						if (!s->tab[x + i][y + j]) {
							znaleziono = true;
							Ux = x + i;
							Uy = y + j;
							break;
						}
					}
				}
				if (znaleziono) break;
			}
			if (!znaleziono) {
				Ux = nX;
				Uy = nY;
			}
			s->tab[x][y] = s->tab[nX][nY];
			s->tab[nX][nY] = nullptr;
			s->tab[x][y]->setX(x);
			s->tab[x][y]->setY(y);
			s->tab[Ux][Uy] = this;
			x = Ux;
			y = Uy;
		}
	}
	else {
		if (s->tab[nX][nY]->getSila() < sila) {
			makeLog(wypiszNazwe(), s->tab[nX][nY]->wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz1);
			s->tab[nX][nY]->zycie = MARTWY;
			s->tab[nX][nY] = nullptr;
		}
	}
}

string Antylopa::wypiszNazwe() const {
	return "antylopa";
}
Organizm* Antylopa::dziecko() {
	return new Antylopa(x, y, s);
}









