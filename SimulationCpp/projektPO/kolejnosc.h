#pragma once

class Element {
	private:
		int a, b,inicjatywa;
		char n;
		Element* nastepny=nullptr, * poprzedni=nullptr;
	public:
		Element(int a, int b, char n,int inicjatywa);
		void setA(int a);
		void setB(int b);
		void setN(char n);
		void setInicjatywa(int i);
		void setNas(Element* n);
		void setPop(Element* n);
		int getA()const;
		int getB()const;
		char getN()const;
		int getInicjatywa()const;
		Element* getNas()const;
		Element* getPop()const;
		~Element();
};