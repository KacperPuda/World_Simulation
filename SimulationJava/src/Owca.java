import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Owca extends Zwierze {


    Owca(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=4;
        sila=4;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("owcahex.png"));
        else obrazek = new JLabel(new ImageIcon("owca.png"));
    }

    @Override
    public Organizm dziecko() {
        return new Owca(x,y,s);
    }

    @Override
    public String wypiszNazwe() {
        return "owca";
    }

    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}

