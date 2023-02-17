import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Barszcz extends Roslina {

    Barszcz(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=0;
        sila=10;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("barszczhex.png"));
        else obrazek = new JLabel(new ImageIcon("barszsosnowskiego.png"));
    }

    @Override
    protected void kolizja(int xx,int yy){
        if(s.getPole(xx, yy).wypiszNazwe() != "cyber-owca"){
            zycie=0;
            makeLog(s.getPole(xx, yy).wypiszNazwe(), wypiszNazwe(), 3, x, y);
            s.getPole(xx, yy).setZycie(martwy);
            s.setTab(x, y, null);
            s.setTab(xx, yy, null);
        } else {
            zycie=0;
            makeLog(s.getPole(xx, yy).wypiszNazwe(), wypiszNazwe(), 2, x, y);
            s.setTab(x, y, s.getPole(xx, yy));
            s.setTab(xx, yy, null);
            s.getPole(x, y).setX(x);
            s.getPole(x, y).setY(y);
        }
    }

    @Override
    public void akcja() {
        int[][] t=s.getSasiadBarszczu(this);

        for(int i=0;i<s.getIleSB();i++){
            if(t[i][xpos]!=-1){
                if(s.getPole(t[i][xpos], t[i][ypos])!=null){
                    if(s.getPole(t[i][xpos], t[i][ypos]).wypiszNazwe()!="cyber-owca" && s.getPole(t[i][xpos], t[i][ypos]).getInicjatywa()>0){
                        if(s.getPole(t[i][xpos], t[i][ypos]).wypiszNazwe()=="czlowiek" && s.getUmiejetnosc()) continue;
                        s.getPole(t[i][xpos], t[i][ypos]).setZycie(0);
                        makeLog(wypiszNazwe(), s.getPole(t[i][xpos], t[i][ypos]).wypiszNazwe(), 5, t[i][xpos], t[i][ypos]);
                        s.setTab(t[i][xpos], t[i][ypos],null);
                    }
                }
            }
        }

       if(probaRozsiania()){
           Organizm n = dziecko();
           rozsiej(n);
       }
    }

    @Override
    public Organizm dziecko() {
        return new Barszcz(x,y,s);
    }

    @Override
    public String wypiszNazwe() {
        return "barszcz sosnowskiego";
    }

    
    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}
