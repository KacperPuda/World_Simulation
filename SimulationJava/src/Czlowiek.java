import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Czlowiek extends Zwierze {

    private JLabel obrazek2;

    Czlowiek(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=4;
        sila=5;
        this.s=s;
        if(s instanceof Hexagony){
            obrazek = new JLabel(new ImageIcon("czlowiekhex.png"));
            obrazek2 = new JLabel(new ImageIcon("czlowieumkhex.png"));
        }
        else
        {
            obrazek = new JLabel(new ImageIcon("czlowiek.png"));
            obrazek2 = new JLabel(new ImageIcon("czlowiek2.png")); 
        }
    }

    @Override
    public void akcja() {
        noweXY xy = new noweXY(x, y);
        int z;
        boolean hex=false;
        if(s instanceof Hexagony) hex= true;
        int[][] t = s.getSasiad(this);
        if(s.getGdzieCz()==lewo){
            if(hex)z=2;
            else z=1;
            if(t[z][xpos]!=BrakSasiada){
            xy.nX=t[z][xpos];
            xy.nY=t[z][ypos];
            }
        } else if(s.getGdzieCz()==gora){
            z=0;
            if(t[z][0]!=BrakSasiada && !hex){
            xy.nX=t[z][xpos];
            xy.nY=t[z][ypos];
            }
        } else if(s.getGdzieCz()==prawo){
            if(hex)z=3;
            else z=2;
            if(t[z][0]!=BrakSasiada){
            xy.nX=t[z][xpos];
            xy.nY=t[z][ypos];
            }
        } else if(s.getGdzieCz()==dol){
            z=3;
            if(t[z][xpos]!=BrakSasiada  && !hex){
            xy.nX=t[z][xpos];
            xy.nY=t[z][ypos];
            }
        }else if(s.getGdzieCz()==goraPrawo){
            z=1;
            if(t[z][0]!=-1){
            xy.nX=t[z][xpos];
            xy.nY=t[z][ypos];
            }
        }else if(s.getGdzieCz()==dolPrawo){
            z=5;
            if(t[z][0]!=-1){
            xy.nX=t[z][xpos];
            xy.nY=t[z][ypos];
            }
        }else if(s.getGdzieCz()==goraLewo){
            z=0;
            if(t[z][0]!=-1){
            xy.nX=t[z][xpos];
            xy.nY=t[z][ypos];
            }
        }else if(s.getGdzieCz()==dolLewo){
            z=4;
            if(t[z][xpos]!=BrakSasiada){
            xy.nX=t[z][xpos];
            xy.nY=t[z][ypos];
            }
        }
        
        if(s.getUmiejetnosc()&& s.getPole(xy.nX, xy.nY)!=null){
            if(s.getPole(xy.nX, xy.nY).getSila()>sila){
                xy.nX=x;
                xy.nY=y;
            }
        }
        tura(xy);
    }

    @Override
    protected void kolizja(int nX,int nY){
        makeLog(s.getPole(nX, nY).wypiszNazwe(),wypiszNazwe(), x, y, 3);
        if (s.getPole(nX, nY).getSila() >= sila) {
            if (!s.getUmiejetnosc()) {
                zycie = 0;
                makeLog(s.getPole(nX, nY).wypiszNazwe(), wypiszNazwe(), x,y, 2);
                s.setTab(x, y, s.getPole(nX, nY));
                s.setTab(nX, nY, null);
                s.getPole(x, y).setX(x);
                s.getPole(x, y).setY(y);
            }
            else {
                int Ux = x, Uy = y;
                boolean znaleziono = false;
                for (int i = -1; i < 2; i++) {
                    if (x + i < 0 || x + i >= s.getXM()) continue;
                    for (int j = -1; j < 2; j++) {
                        if (y + j < 0 || y + j >= s.getYM()) continue;
                        if ((i != 0 && j == 0) || (i == 0 && j != 0)) {
                            if (s.getPole(x+i, y+j)==null) {
                                znaleziono = true;
                                Ux = x + i;
                                Uy = y + j;
                                break;
                            }
                        }
                    }
                    if (znaleziono) break;
                }
                if (!znaleziono) {
                    Ux = nX;
                    Uy = nY;
                }
                s.setTab(x, y, s.getPole(nX, nY)); 
                s.setTab(nX, nY, null);
                s.getPole(x, y).setX(x);
                s.getPole(x, y).setY(y);
                s.setTab(Ux, Uy, this);
                x = Ux;
                y = Uy;
                makeLog(wypiszNazwe(), s.getPole(x, y).wypiszNazwe(), x,y,6);
            }
        }
        else {
            if (s.getPole(nX, nY).getSila() < sila) {
                makeLog(wypiszNazwe(), s.getPole(nX, nY).wypiszNazwe(), nX,nY,2);
                s.getPole(nX, nY).setZycie(martwy);
                s.setTab(nX, nY,null);
            }
        }
    }

    @Override
    public Organizm dziecko() {
        return new Czlowiek(x,y,s);
    }

    @Override
    public String wypiszNazwe() {
        return "czlowiek";
    }

    @Override
    public JLabel wypisz() {
        if(!s.getUmiejetnosc()) return obrazek;
        else return obrazek2;
    }
    
}
