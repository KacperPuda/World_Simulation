#include"wilk.h"
#include <iostream>
using namespace std;

Wilk::Wilk(int x, int y, Swiat* s) {
	sila = 9;
	this->x = x;
	this->y = y;
	inicjatywa = 5;
	zycie = ZYWY;
	this->s = s;
}

void Wilk::wypisz()const {
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), KOLORWILKA);
	cout << "W";
}


string Wilk::wypiszNazwe() const {
	return "wilk";
}
Organizm* Wilk::dziecko() {
	return new Wilk(x,y,s);
}









