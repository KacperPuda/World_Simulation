#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <cstdio>
#include <conio.h>
#include "swiat.h"
using namespace std;

int main()
{
	srand(time(NULL));
	cout << "Kacper Puda s188625 gr.4" << endl << endl;
	int co;
	cout << "co chcesz zrobic?" << endl << "1 - nowa gra" << endl << "2 - wczytaj zapis" << endl;
	cin >> co;
	system("cls");
	int x=0, y=0;
	if (co == 1) {
		cout << "podaj wielkosc planszy (wysokosc,szerokosc)" << endl;
		cin >> x;
		cin >> y;
	}
	if (co == 2) {
		cout << "wczytywanie zapisu" << endl;
		fstream plik;
		plik.open("zapis-wielkosc.txt", ios::in);
		string napis;
		string liczba="";
		while (getline(plik,napis)) {
			for (int i = 0; i < napis.length(); i++){
				if (napis[i] == ',') {
					x = atoi(liczba.c_str());
					liczba = "";
				}
				else {
					liczba += napis[i];
				}
			}
			y = atoi(liczba.c_str());
		}
		cout << x << "," << y << endl;
	}
	Swiat swiat(x,y);
	if(co==1)swiat.ustawianie();
	if (co == 2)swiat.wczytajZapis();
	//return 0;
	swiat.rysuj();
	cout << endl;
	swiat.symuluj();
	return 0;
}
