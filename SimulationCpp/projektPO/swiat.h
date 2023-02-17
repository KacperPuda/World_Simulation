#pragma once
#include "organizm.h"
#include "define.h"
#include <string>
using namespace std;

class Organizm;

class Swiat {
	private:
		int xMaks, yMaks,czasUmiejetnosci=5,tura=0;
	public:
		bool umiejetnosc = false;
		Organizm*** tab;
		Organizm* Head = nullptr;
		string log="";
		int gdzieCzlowiek=-1;
		bool czlowiekZyje = false;
		Swiat(int a, int b);
		int getXM() const;
		int getYM()const;
		void ustawianie();
		void start();
		void generujZapis();
		void wczytajZapis();
		void dodaj(int i, int j,Organizm *nowe);
		void coRobiCzlowiek(char& znak);
		void organizmy();
		void rysuj() const;
		void symuluj();
		~Swiat();
};
