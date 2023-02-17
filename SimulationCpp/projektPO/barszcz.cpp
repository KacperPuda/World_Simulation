#include "barszcz.h"
#include "cyberOwca.h"
#include <iostream>
using namespace std;

Barszcz::Barszcz(int x, int y, Swiat* s) {
	sila = 10;
	this->x = x;
	this->y = y;
	zycie = ZYWY;
	this->s = s;
}

bool Barszcz::probaRozsiania() {
	int i = rand() % SZANSANAROZSIANIEBARSZCZU;
	if (i == UDANA)return true;
	return false;
}

void Barszcz::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORBARSZCZU);
	char a = SYMBOLBARSZCZU;
	cout << a;
}

void Barszcz::akcja(){
	for (int i = SASIADGORA; i < SASIADDOL; i++) {
		if (x + i < POCZATEKPLANSZY || x + i >= s->getXM()) continue;
		for (int j = SASIADLEWO; j < SASIADPRAWO; j++) {
			if (y + j < POCZATEKPLANSZY || y + j >= s->getYM()) continue;
			if (s->tab[x + i][y + j] == this) continue;
			if (s->tab[x + i][y + j]) {
				if (s->tab[x + i][y + j]->getInicjatywa()>0 && !dynamic_cast<CyberOwca*>(s->tab[x+i][y+j])) {
					if (s->tab[x + i][y + j]->wypiszNazwe() == "czlowiek" && s->umiejetnosc) break;
					makeLog(wypiszNazwe(), s->tab[x + i][y + j]->wypiszNazwe(), s->log,to_string(x+i),to_string(y+j),5);
					s->tab[x + i][y + j]->zycie = MARTWY;
					s->tab[x + i][y + j] = nullptr;
				}	
			}
		}
	}
	if (probaRozsiania()) {
		Organizm* n = dziecko();
		rozsiej(n);
	}
}


void Barszcz::kolizja(int nX, int nY) {
	if (s->tab[nX][nY]->wypiszNazwe() != "cyber-owca") {
		zycie = MARTWY;
		s->tab[nX][nY]->zycie = MARTWY;
		makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMr4);
		s->tab[nX][nY] = nullptr;
		s->tab[x][y] = nullptr;
	}
	else {
		zycie = MARTWY;
		makeLog(s->tab[nX][nY]->wypiszNazwe(), wypiszNazwe(), s->log, to_string(x), to_string(y), KOMr2);
		s->tab[x][y] = s->tab[nX][nY];
		s->tab[nX][nY] = nullptr;
		s->tab[x][y]->setX(x);
		s->tab[x][y]->setY(y);
	}
}

string Barszcz::wypiszNazwe() const {
	return "barszcz sosnowskiego";
}
Organizm* Barszcz::dziecko() {
	return new Barszcz(x, y, s);
}
