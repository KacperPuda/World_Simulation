#include"owca.h"
#include <iostream>
using namespace std;

Owca::Owca(int x, int y, Swiat* s) {
	sila = 4;
	this->x = x;
	this->y = y;
	inicjatywa = 4;
	zycie = ZYWY;
	this->s = s;
}

Organizm* Owca::dziecko() {
	return new Owca(x, y,s);
}

string Owca::wypiszNazwe() const {
	return "owca";
}

void Owca::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLOROWCY);
	cout << "O";
}


