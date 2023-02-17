#include"lis.h"
#include <iostream>
using namespace std;

Lis::Lis(int x, int y, Swiat* s) {
	sila = 3;
	this->x = x;
	this->y = y;
	inicjatywa = 9;
	zycie = ZYWY;
	this->s = s;
}

string Lis::wypiszNazwe() const {
	return "lis";
}

void Lis::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORLISA);
	cout << "S";
}



void Lis::akcja() {
	int nX = x, nY = y,g= BRAKMIEJSC,r;
	int t2[4][2];
	for (int i = SASIADGORA; i < SASIADDOL; i++) {
		if (x + i<POCZATEKPLANSZY|| x + i>=s->getXM()) continue;
		for (int j = SASIADLEWO; j < SASIADPRAWO; j++) {
			if (y + j<POCZATEKPLANSZY || y + j>= s->getYM()) continue;

			if((i==SRODEK && j!= SRODEK)||(i != SRODEK && j == SRODEK))

			if (!s->tab[x + i][y + j]) {
				t2[g][XWOLNE] = x + i;
				t2[g][YWOLNE] = y + j;
				g++;
			}
			else {
				if (s->tab[x + i][y + j]->getSila() <= sila) {
					t2[g][XWOLNE] = x + i;
					t2[g][YWOLNE] = y + j;
					g++;
				}
			}
		}
	}
	if (g != BRAKMIEJSC) {
		r = rand() % g;
		nX = t2[r][XWOLNE];
		nY = t2[r][YWOLNE];
	}
	tura(nX, nY);
}



Organizm* Lis::dziecko() {
	return new Lis(x, y,s);
}









