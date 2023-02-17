#include "cyberOwca.h"
#include <iostream>
using namespace std;

CyberOwca::CyberOwca(int x, int y, Swiat* s) {
	sila = 11;
	this->x = x;
	this->y = y;
	inicjatywa = 4;
	zycie = ZYWY;
	this->s = s;
	this->xb = x;
	this->yb = y;
}

void CyberOwca::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORCYBEROWCY);
	cout << "C";
}
//owca skanuje cala mape w poszukiwaniu barszczu sosnowskiego
//przy pierwszym znalezieniu ustawia swoj cel (xb,yb x/y docelowego barszczu)
//przy kolejnych porownuje odleglosc i zmienia swoj cel na najblizszy 
//jesli ma ustawiony cel podaza w jego strone jesli nie szuka nowego jesli nie ma nowego porusza sie losowo
void CyberOwca::akcja() {
	int nX = x, nY = y,odleglosc=NULL;
	bool wykonywanie = false;
	if (!s->tab[xb][yb]) wykonywanie = true;
	else {
		if (s->tab[xb][yb]->wypiszNazwe() != "barszcz sosnowskiego") wykonywanie = true;
	}
	if (wykonywanie) {
		xb = NULL;
		yb = NULL;
		for (int i = POCZATEKPLANSZY; i < s->getXM(); i++) {
			for (int j = POCZATEKPLANSZY; j < s->getYM(); j++) {
				if (s->tab[i][j] != nullptr) {
					if (s->tab[i][j]->wypiszNazwe() == "barszcz sosnowskiego") {
						if (!odleglosc) {
							odleglosc = sqrt(pow(x - i, 2) + pow(y - j, 2));
							xb = i;
							yb = j;
						}
						else {
							int tmp = sqrt(pow(x - i, 2) + pow(y - j, 2));
							if (odleglosc > tmp) {
								odleglosc = tmp;
								xb = i;
								yb = j;
							}
						}
					}
				}
			}
		}	
	}
	if (s->tab[xb][yb]) {
		if (s->tab[xb][yb]->wypiszNazwe() == "barszcz sosnowskiego") {
			int co = rand() % COZMIENIA;
			if (x == xb)co = ZMIENIAY;
			if (y == yb) co = ZMIENIAX;
			if (co == ZMIENIAX) {
				if (xb > x) nX = x + 1;
				if (xb < x) nX = x - 1;
			}
			if (co == ZMIENIAY) {
				if (yb > y) nY = y + 1;
				if (yb < y) nY = y - 1;
			}
		}
		else ruch(nX, nY);
	}
	else ruch(nX, nY);
	tura(nX, nY);

}

string CyberOwca::wypiszNazwe() const {
	return "cyber-owca";
}
Organizm* CyberOwca::dziecko() {
	return new CyberOwca(x, y, s);
}









