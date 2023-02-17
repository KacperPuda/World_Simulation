#pragma once
#include "organizm.h"


class Zwierze : public Organizm {
	public:
		virtual Organizm* dziecko()=0;
		void ruch( int& nX, int& nY);
		void tura( int nX, int nY);
		virtual void kolizja( int nX, int nY);
		virtual void akcja();
		void reprodukcja(int nX, int nY, Organizm*n);
		void makeLog(string z1, string z2, string& log, string x, string y, int akcja);
		virtual ~Zwierze();
};
