#include <iostream>
#include"kolejnosc.h"
using namespace std;

Element::Element(int a, int b, char n,int inicjatywa) :a(a), b(b), n(n),inicjatywa(inicjatywa) {
}

void Element::setA(int a) {
	this->a = a;
}

void Element::setB(int b) {
	this->b = b;
}

void Element::setN(char n) {
	this->n = n;
}


void Element::setInicjatywa(int i) {
	this->inicjatywa = i;
}

void Element::setNas(Element* n) {
	this->nastepny = n;
}
void Element::setPop(Element* n) {
	this->poprzedni = n;
}

int Element::getA() const {
	return a;
}

int Element::getB()const {
	return b;
}

char Element::getN()const {
	return n;
}

int Element::getInicjatywa()const {
	return inicjatywa;
}

Element* Element::getNas()const {
	return nastepny;
}

Element* Element::getPop()const {
	return poprzedni;
}

Element::~Element() {

}