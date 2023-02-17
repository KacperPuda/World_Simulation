import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Jagody extends Roslina {

    Jagody(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=0;
        sila=99;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("jagodyhex.png"));
        else obrazek = new JLabel(new ImageIcon("wilczejagody.png"));
    }

    @Override
    protected void kolizja(int xx,int yy){
        zycie = 0;
        makeLog(s.getPole(xx, yy).wypiszNazwe(), wypiszNazwe(), 3, x, y);
        s.getPole(xx, yy).setZycie(0);
        s.setTab(x, y, null);
        s.setTab(xx, yy, null);
    }

    @Override
    public Organizm dziecko() {
        return new Jagody(x,y,s);
    }

    @Override
    public String wypiszNazwe() {
        return "wilcze jagody";
    }

    
    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}
