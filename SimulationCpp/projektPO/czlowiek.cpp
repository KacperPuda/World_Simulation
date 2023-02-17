#include "czlowiek.h"
#include <iostream>
#include <conio.h>
using namespace std;

Czlowiek::Czlowiek(int x, int y, Swiat* s) {
	sila = 5;
	this->x = x;
	this->y = y;
	inicjatywa = 4;
	zycie = ZYWY;
	this->s = s;
}

void Czlowiek::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORCZLOWIEKA);
	char a = SYMBOLCZLOWIEKA;
	if (s->umiejetnosc) a = SYMBOLCZLOWIEKAZUMIEJETNOSCIA;
	cout << a;
}

void Czlowiek::akcja() {
	int nX = x, nY = y;
	char z=NIC, z2=NIC;
	if (s->gdzieCzlowiek == DOL) nX++;
	if (s->gdzieCzlowiek == GORA) nX--;
	if (s->gdzieCzlowiek == LEWO) nY--;
	if (s->gdzieCzlowiek == PRAWO) nY++;
	if (nX < POCZATEKPLANSZY || nX >= s->getXM()) nX = x;
	if (nY < POCZATEKPLANSZY || nY >= s->getYM()) nY = y;
	if (s->umiejetnosc) {
		if (s->tab[nX][nY]) {
			if (s->tab[nX][nY]->getSila() > sila) {
				nX = x;
				nY = y;
			}
		}
	}
	tura(nX, nY);
}

void Czlowiek::kolizja(int nX, int nY) {
	makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(nX), to_string(nY), KOMz7);
	if (s->tab[nX][nY]->getSila() > sila || s->tab[nX][nY]->getSila() == sila) {
		if (!s->umiejetnosc) {
			zycie = MARTWY;
			makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz1);
			s->tab[x][y] = s->tab[nX][nY];
			s->tab[nX][nY] = nullptr;
			s->tab[x][y]->setX(x);
			s->tab[x][y]->setY(y);
		}
		else {
			int Ux = x, Uy = y;
			bool znaleziono = false;
			for (int i = SASIADGORA; i < SASIADDOL; i++) {
				if (x + i < POCZATEKPLANSZY || x + i >= s->getXM()) continue;
				for (int j = SASIADLEWO; j < SASIADPRAWO; j++) {
					if (y + j < POCZATEKPLANSZY || y + j >= s->getYM()) continue;
					if ((i != SRODEK && j == SRODEK) || (i == SRODEK && j != SRODEK)) {
						if (s->tab[x + i][y + j]) {
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
			makeLog(wypiszNazwe(), s->tab[nX][nY]->wypiszNazwe(), s->log, to_string(x), to_string(y), KOMz5);
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

string Czlowiek::wypiszNazwe() const {
	return "czlowiek";
}
Organizm* Czlowiek::dziecko() {
	return new Czlowiek(x, y, s);
}









