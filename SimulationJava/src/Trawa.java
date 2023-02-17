import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Trawa extends Roslina {

    Trawa(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=0;
        sila=0;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("trawahex.png"));
        else obrazek = new JLabel(new ImageIcon("trawa.png"));
    }

    @Override
    public Organizm dziecko() {
        return new Trawa(x,y,s);
    }

    @Override
    public String wypiszNazwe() {
        return "trawa";
    }

    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}
