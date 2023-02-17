#pragma once
#include<string>
#include<windows.h>
#include"swiat.h"
using namespace std;

class Swiat;

class Organizm {
protected:
	int x, y, inicjatywa=0, sila=1;
	Organizm* nastepny = nullptr, * poprzedni = nullptr;
public:
	Swiat* s;
	//zycie = 0 - musi zostac usuniety
	//zycie = 1 - zyje
	//zycie = 2 - zyje i wykonal ruchzz
	int plodnosc = 0, zycie = 1;
	Organizm* getNas()const;
	Organizm* getPop()const;
	void setNas(Organizm* n);
	void setPop(Organizm* n);
	int getX()const;
	int getY()const;
	void setX(int a);
	void setY(int a);
	int getSila() const;
	void setSila(int s);
	void smierc(Organizm*& Head);
	int getInicjatywa() const;
	virtual string wypiszNazwe() const = 0;
	virtual void wypisz() const = 0;
	virtual void kolizja(int nX, int nY)=0;
	virtual void akcja() = 0;
	virtual ~Organizm();
};