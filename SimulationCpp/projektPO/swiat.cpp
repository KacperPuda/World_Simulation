#include"swiat.h"
#include"wilk.h"
#include"owca.h"
#include"lis.h"
#include"zolw.h"
#include"antylopa.h"
#include"mlecz.h"
#include"trawa.h"
#include"guarana.h"
#include"jagody.h"
#include"barszcz.h"
#include"cyberOwca.h"
#include"czlowiek.h"
#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <fstream>
#include <conio.h>
using namespace std;

Swiat::Swiat(int a, int b):xMaks(a),yMaks(b) {
	tab = new Organizm**[a];
	for (int i = 0; i < a; i++) tab[i] = new Organizm*[b];

	for (int i = 0; i < a; i++) {
		for (int j = 0; j < b; j++) {
			tab[i][j] = nullptr;
		}
	}
}
//dodawanie do listy kolejnych organizmow tak aby zostaly ulozone zgodnie z inicjatywa i wiekiem
//element zostaje dodany albo na koniec listy albo przed organizmem o mniejszej inicjatywie
void Swiat::dodaj(int i, int j, Organizm* n) {
	tab[i][j] = n;
	if (!Head) {
		Head = n;
	}
	else {
		Organizm* obecny = Head;
		if (obecny->getInicjatywa() < n->getInicjatywa()) {
			n->setNas(obecny);
			obecny->setPop(n);
			Head = n;
		}
		else {
			while (obecny->getNas() != nullptr ) {
				if (obecny->getNas()->getInicjatywa() < n->getInicjatywa()) break;
				obecny = obecny->getNas();
			}
			n->setPop(obecny);
			if (obecny->getNas()) {
				n->setNas(obecny->getNas());
				obecny->getNas()->setPop(n);
			}
			obecny->setNas(n);
		}
	}
}
//losowanie na kazde miejsce organizmu
//czlowiek jest losowany wczesniej i ustawiany gdy indeksy tablicy sie zgadzaja (moze byc tylko jeden czlowiek)
void Swiat::ustawianie() {
	int czlowiekX, czlowiekY;
	czlowiekX = rand() % xMaks;
	czlowiekY = rand() % yMaks;
	for (int i = 0; i < xMaks; i++) {
		for (int j = 0; j < yMaks; j++) {
			if (tab[i][j] == nullptr) {
				int x = rand() % SZANSA;
				if (i == czlowiekX && j == czlowiekY) x = MIEJSCECZLOWIEKA;
				if (x == WYLOSOWANOWILKA) {
					Organizm* wilk = new Wilk(i, j, this);
					dodaj(i, j,wilk);
				}
				if (x == WYLOSOWANOOWCE) {
					Organizm* owca = new Owca(i, j, this);
					dodaj(i, j, owca);
				}
				if (x== WYLOSOWANOLISA) {
					Organizm* lis = new Lis(i, j,this);
					dodaj(i, j, lis);
				}
				if (x == WYLOSOWANOZOLWIA) {
					Organizm* zolw = new Zolw(i, j, this);
					dodaj(i, j, zolw);
				}
				if (x == WYLOSOWANOANTYLOPE) {
					Organizm* antylopa = new Antylopa(i, j, this);
					dodaj(i, j, antylopa);
				}	
				if (x == WYLOSOWANOMLECZA) {
					Organizm* mlecz = new Mlecz(i, j, this);
					dodaj(i, j, mlecz);
				}
				if (x == WYLOSOWANOTRAWE) {
					Organizm* trawa = new Trawa(i, j, this);
					dodaj(i, j, trawa);
				}
				if (x == WYLOSOWANOGUARANE) {
					Organizm* guarana = new Guarana(i, j, this);
					dodaj(i, j, guarana);
				}
				if (x == WYLOSOWANOWILCZEJAGODY) {
					Organizm* jagody = new Jagody(i, j, this);
					dodaj(i, j, jagody);
				}
				if (x == WYLOSOWANOBARSZCZ) {
					Organizm* barszcz = new Barszcz(i, j, this);
					dodaj(i, j, barszcz);
				}
				if (x == WYLOSOWANOCYBEROWCE) {
					Organizm* cyberOwca = new CyberOwca(i, j, this);
					dodaj(i, j, cyberOwca);
				}
				if (x == MIEJSCECZLOWIEKA && !czlowiekZyje) {
					Organizm* czlowiek = new Czlowiek(i, j, this);
					dodaj(i, j, czlowiek);
					czlowiekZyje = true;
				}
			}
		}
	}
}

void Swiat::generujZapis() {
	fstream plik,plik2;
	plik.open("zapis.txt", ios::trunc);
	plik.close();
	plik.open("zapis.txt", ios::out);
	plik2.open("zapis-wielkosc.txt", ios::trunc);
	plik2.close();
	plik2.open("zapis-wielkosc.txt", ios::out);
	plik2 << xMaks << "," << yMaks;
	plik2.close();
	Organizm* obecny = Head;
	plik << tura << endl;
	while (obecny) {
		if(obecny->zycie!=0)plik << obecny->wypiszNazwe()<<","<<obecny->getX() << "," << obecny->getY() << "," << obecny->zycie << "," << obecny->getSila() << ",";
		if (obecny->wypiszNazwe() == "czlowiek")plik << umiejetnosc << "," << czasUmiejetnosci<<",";
		plik << endl;
		obecny = obecny->getNas();
	}
	plik.close();
}

void Swiat::wczytajZapis() {
	fstream plik;
	plik.open("zapis.txt", ios::in);
	int coSieZapisuje=10;
	string napis;
	string orgX="", orgY="", nazwa="",zycie="", sila = "",tu="", umiejetnoscs="", turyUmiejetnosci="";
	while (getline(plik, napis)) {
		orgX = "";
		orgY = "";
		nazwa = "";
		zycie = "";
		sila = "";
		umiejetnoscs = "";
		turyUmiejetnosci = "";
		for (int i = 0; i < napis.length(); i++) {
			if (napis[i] == ',') coSieZapisuje++;
			else {
				if (coSieZapisuje == 0) nazwa += napis[i];
				if (coSieZapisuje == 1) orgX += napis[i];
				if (coSieZapisuje == 2) orgY += napis[i];
				if (coSieZapisuje == 3) zycie += napis[i];
				if (coSieZapisuje == 4) sila += napis[i];
				if (coSieZapisuje == 5) umiejetnoscs += napis[i];
				if (coSieZapisuje == 6) turyUmiejetnosci += napis[i];
				if (coSieZapisuje == 10) tu += napis[i];
			}
		}
		if (coSieZapisuje == 10) {
			coSieZapisuje = 0;
			continue;
		}
		coSieZapisuje = 0;
		int ox= atoi(orgX.c_str()), oy= atoi(orgY.c_str()), oz= atoi(zycie.c_str()), os= atoi(sila.c_str());
		Organizm* n = nullptr;
		if (nazwa == "wilk") n = new Wilk(ox,oy,this);
		if (nazwa == "owca") n = new Owca(ox, oy, this);
		if (nazwa == "lis") n = new Lis(ox, oy, this);
		if (nazwa == "antylopa") n = new Antylopa(ox, oy, this);
		if (nazwa == "czlowiek") {
			int ile = 0;
			n = new Czlowiek(ox, oy, this);
			czlowiekZyje = true;
			if (umiejetnoscs == "1") {
				umiejetnosc = true;
				ile = 1;
			}
			else ile = -1;
			czasUmiejetnosci = atoi(turyUmiejetnosci.c_str()) + ile;
		}
		if (nazwa == "cyber-owca") n = new CyberOwca(ox, oy, this);
		if (nazwa == "zolw") n = new Zolw(ox, oy, this);
		if (nazwa == "trawa") n = new Trawa(ox, oy, this);
		if (nazwa == "mlecz") n = new Mlecz(ox, oy, this);
		if (nazwa == "guarana") n = new Guarana(ox, oy, this);
		if (nazwa == "barszcz sosnowskiego") n = new Barszcz(ox, oy, this);
		if (nazwa == "wilcze jagody") n = new Jagody(ox, oy, this);
		if (n) {
			n->setSila(os);
			n->zycie = oz;
			cout << n->wypiszNazwe() << ": x:" << n->getX() << ", y: " << n->getY() << endl;
			dodaj(ox, oy, n);
		}
	}
	tura = atoi(tu.c_str())-1;
}



void Swiat::rysuj()const {
	system("cls");
	cout << "Kacper Puda s188625 gr.4" << endl << endl;
	char podloga = SYMBOLPODLOGI,krawedz= SYMBOLKRAWEDZI,bok= SYMBOLBOKU;
	cout << "  "<<bok;
	for (int j = 0; j < yMaks; j++) cout << j / 10;
	cout << bok << "  ";
	cout << endl;
	cout << "  " << bok;
	for (int j = 0; j < yMaks; j++) cout << j % 10;
	cout << bok << tura;
	cout << endl;
	cout << podloga<<podloga<<krawedz;
	for (int j = 0; j < yMaks; j++) cout << podloga;
	cout << krawedz << podloga << podloga ;
	cout << endl;
	for (int i = 0; i < xMaks; i++) {
		cout << i / 10 << i % 10 << bok;
		for (int j = 0; j < yMaks; j++) {
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),170);
			if (tab[i][j] != nullptr) tab[i][j]->wypisz();
			else cout << " ";
		}
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_GREEN | FOREGROUND_BLUE | FOREGROUND_RED);
		cout << bok<< i / 10 << i % 10 << endl;
	}
	cout << podloga << podloga << krawedz;
	for (int j = 0; j < yMaks; j++) cout << podloga;
	cout << krawedz << podloga << podloga;
	cout << endl;
	cout << "  " << bok;
	for (int j = 0; j < yMaks; j++) cout << j / 10;
	cout << bok << "  ";
	cout << endl;
	cout << "  " << bok;
	for (int j = 0; j < yMaks; j++) cout << j % 10;
	cout << bok << "  ";
	cout << endl;
}
//wykonywanie akcji zwierzat ktore zyja i nie sa siwerzo narodzone oraz usuwanie martwych zwierzat
void Swiat::organizmy() {
	Organizm* obecny = Head;
	while (obecny) {
		Organizm* nas =obecny->getNas();
		if (obecny->plodnosc != PLODNY)obecny->plodnosc--;
		if (obecny->zycie != MARTWY && obecny->zycie != MLODY)obecny->akcja();
		else {
			if (obecny->zycie == MARTWY) {
				obecny->smierc(Head);
				obecny = nullptr;
			}
		}
		obecny = nas;
	}
}
//zmienianie swiezo narodzonych organizmow w zywe
void Swiat::start() {
	Organizm* obecny = Head;
	while (obecny) {
		if(obecny->zycie != MARTWY)obecny->zycie = ZYWY;
		else {
			if (obecny->wypiszNazwe() == "czlowiek") czlowiekZyje = false;
		}
		obecny = obecny->getNas();
	}
}

void Swiat::coRobiCzlowiek(char &znak) {
	char gc1 = STRZALKA, gc2=NIC;
	int i = PIERWSZAAKCAJA;
	if (umiejetnosc) {
		czasUmiejetnosci--;
		cout << "Umiejetnosc czlowieka aktywna. Ilosc pozotalych tur: " << czasUmiejetnosci << endl;
	}
	else {
		if (czasUmiejetnosci < 5) {
			czasUmiejetnosci++;
			if(czasUmiejetnosci !=  5)cout << "Ladowanie umiejetnosci. Ilosc pozotalych tur: " << 5 - czasUmiejetnosci << endl;
		}
		if (czasUmiejetnosci == 5) cout << "Umiejetnosc moze zostac aktywowana" << endl;
	}
	if (czasUmiejetnosci == 0) umiejetnosc = false;
	cout << "Co robi czlowiek:";
	gdzieCzlowiek = STOI;
			while ((gc1 != NASTEPNATURA && gc1 != KONIEC && gc1 != ZAPIS) || (gc2 != NASTEPNATURA && gc2 != KONIEC && gc2 != 'z')) {
				gc1 = _getch();
				if (gc1 == NASTEPNATURA || gc1 == KONIEC || gc1 == ZAPIS) {
					znak = gc1;
					break;
				}
				else {
					if (gc1 == UMIEJETNOSCCZ) {
						if (czasUmiejetnosci == 5 && !umiejetnosc) {
							umiejetnosc = true;
							cout << " aktywuje umiejetnosc";
							i++;
						}
					}
					if (gc1 != STRZALKA) continue;
				}
				gc2 = _getch();
				if (gc2 == NASTEPNATURA || gc2 == KONIEC) {
					znak = gc1;
					break;
				}
				if (i != 0) cout << ",";
				if (gc2 == STRZALKAWDOL) {
					cout << " idzie w dol ";
					gdzieCzlowiek = DOL;
				}
				if (gc2 == STRZALKAWGORE) {
					cout << " idzie w gore";
					gdzieCzlowiek = GORA;
				}
				if (gc2 == STRZALKAWLEWO) {
					cout << " idzie w lewo";
					gdzieCzlowiek = LEWO;
				}
				if (gc2 == STRZALKAWPRAWO) {
					cout << " idzie w prawo";
					gdzieCzlowiek = PRAWO;
				}
				if (i == PIERWSZAAKCAJA) i++;
			}
			cout << endl;
}

void Swiat::symuluj() {
	while (true) {
		char znak = NIC;
		tura++;
		start();
		cout << log << endl;
			if (czlowiekZyje) {
				coRobiCzlowiek(znak);
			}
			
				while (znak != KONIEC && znak != NASTEPNATURA) {
					
					if (znak == ZAPIS) {
						cout << "generowanie zapisu" << endl;
						generujZapis();
					}
					znak = _getch();
				}
				if (znak == KONIEC) break;
			
		
		log = "";
		this->organizmy();
		this->rysuj();
	}
}

int Swiat::getXM() const {
	return this->xMaks;
}

int Swiat::getYM()const {
	return this->yMaks;
}

Swiat::~Swiat() {
	for (int i = 0; i < xMaks; i++) {
		for (int j = 0; j < yMaks; j++) {
			if (tab[i][j]) {
				delete[]tab[i][j];
			}
		}
	}
	cout << "Koniec symulacji!" << endl;
}