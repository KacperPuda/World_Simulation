#include "organizm.h"
#include <iostream>


int Organizm::getX() const{
	return x;
}

int Organizm::getY()const {
	return y;
}

void Organizm::setX(int a) {
	this->x = a;
}

void Organizm::setY(int a) {
	this->y = a;
}

int Organizm::getSila() const {
	return sila;
}

void Organizm::setSila(int s) {
	this->sila = this->sila + s;
}

int Organizm::getInicjatywa() const {
	return inicjatywa;
}

void Organizm::setNas(Organizm* n) {
	this->nastepny = n;
}
void Organizm::setPop(Organizm* n) {
	this->poprzedni = n;
}


Organizm* Organizm::getNas()const {
	return nastepny;
}

Organizm* Organizm::getPop()const {
	return poprzedni;
}


void Organizm::smierc(Organizm*& Head) {
	Organizm* pop = this->getPop();
	Organizm* nas = this->getNas();
	if (wypiszNazwe() == "czlowiek") s->czlowiekZyje = false;

	if (pop) {
		if (nas) pop->setNas(nas);
		else pop->setNas(nullptr);
	}
	else {
		if (nas) Head = nas;
	}
	if (nas) {
		if (pop)nas->setPop(pop);
		else nas->setPop(nullptr);
	}

	if (!nas && !pop) {
		Head = nullptr;
	}
	delete[]this;
}


Organizm::~Organizm() {

}