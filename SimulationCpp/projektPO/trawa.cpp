#include "trawa.h"
#include <iostream>
using namespace std;

Trawa::Trawa(int x, int y, Swiat* s) {
	sila = 0;
	this->x = x;
	this->y = y;
	zycie = ZYWY;
	this->s = s;
}

void Trawa::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORTRAWY);
	char a = SYMBOLTRAWY;
	cout << a;
}


string Trawa::wypiszNazwe() const {
	return "trawa";
}
Organizm* Trawa::dziecko() {
	return new Trawa(x, y, s);
}









