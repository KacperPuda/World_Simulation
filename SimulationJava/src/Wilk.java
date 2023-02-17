import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Wilk extends Zwierze {


    Wilk(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=5;
        sila=9;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("wilkhex.png"));
        else obrazek = new JLabel(new ImageIcon("wilk.png"));
    }

    @Override
    public Organizm dziecko() {
        return new Wilk(x,y,s);
    }

    @Override
    public String wypiszNazwe() {
        return "wilk";
    }

    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}
