import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Antylopa extends Zwierze {

    static private int probaucieczki=2;

    Antylopa(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=4;
        sila=4;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("antylopahex.png"));
        else obrazek = new JLabel(new ImageIcon("antylopa.png"));
    }

    @Override
    public Organizm dziecko() {
        return new Antylopa(x,y,s);
    }

    @Override
    public void akcja() {
        noweXY nowe = new noweXY(x,y);
        int sx=x,sy=y;
        ruch(nowe);
        if(s.getPole(nowe.nX, nowe.nY)==null){
            tura(nowe);
            int tmpX = nowe.nX;
            int tmpY = nowe.nY;
            nowe.nX = nowe.nX - sx;
		    nowe.nY = nowe.nY - sy;
		    nowe.nX = sx + 2 * nowe.nX;
	    	nowe.nY = sy + 2 * nowe.nY;
		    if (nowe.nX < 0 || nowe.nY < 0 || nowe.nY >= s.getYM() || nowe.nX >= s.getXM()) {
		    	nowe.nX = tmpX;
		        nowe.nY = tmpY;
		    }   
        }
        if(s.getPole(nowe.nX, nowe.nY)!=this && plodnosc==0)tura(nowe);
    }

    @Override
    protected void kolizja(int nX, int nY) {
        makeLog(s.getPole(nX, nY).wypiszNazwe(), "", 3, nX, nY);
        if (s.getPole(nX, nY).getSila() >= sila) {
            Random rand = new Random();
            int ucieczka = rand.nextInt(probaucieczki);
            if (ucieczka == 0) {
                makeLog(s.getPole(nX, nY).wypiszNazwe(), wypiszNazwe(), 2, x, y);
                zycie = 0;
                s.setTab(x, y, s.getPole(nX, nY));
                s.setTab(nX, nY,null);
                s.getPole(x, y).setX(x);
                s.getPole(x, y).setY(y);
            }
            else {
                int Ux = x, Uy = y,g= 0,r;
                int[] t2 = new int[s.getIleS()];
                int[][]t=s.getSasiad(this);

                for(int i=0;i<s.getIleS();i++){
                    if(t[i][0]!=-1){
                        if(s.getPole(t[i][xpos], t[i][ypos])==null){
                            t2[g]=i;
                            g++;
                        }
                    }
                }
                if (g != 0) {
                    r = rand.nextInt(g);
                    Ux = t[t2[r]][xpos];
                    Uy = t[t2[r]][ypos];
                } else {
                    Ux = nX;
                    Uy = nY;
                }
                s.setTab(x,y,s.getPole(nX, nY));
                s.setTab(nX, nY,null);
                s.getPole(x, y).setX(x);
                s.getPole(x, y).setY(y);
                s.setTab(Ux, Uy, this);
                makeLog(wypiszNazwe(), s.getPole(x, y).wypiszNazwe(), 5, Ux, Uy);
                x = Ux;
                y = Uy;
                zycie=mlody;
            }
        }
        else {
            if (s.getPole(nX, nY).getSila() < sila) {
                makeLog(wypiszNazwe(), s.getPole(nX, nY).wypiszNazwe(), 2, x, y);
                s.getPole(nX, nY).setZycie(martwy);
                s.setTab(nX,nY,null);
            }
        }
    }


    @Override
    public String wypiszNazwe() {
        return "antylopa";
    }

    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}
