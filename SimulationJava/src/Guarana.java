import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Guarana extends Roslina {

    Guarana(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=0;
        sila=0;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("guaranahex.png"));
        else obrazek = new JLabel(new ImageIcon("guarana.png"));
    }

    @Override
    protected void kolizja(int xx,int yy){
        zycie = 0;
        s.setTab(x, y, s.getPole(xx, yy));
        s.setTab(xx,yy,null);
        s.getPole(x, y).setX(x);
        s.getPole(x, y).setY(y);
        s.getPole(x, y).setSila(s.getPole(x, y).getSila()+3);
        makeLog(s.getPole(x, y).wypiszNazwe(), wypiszNazwe(), 4, x, y);
    }

    @Override
    public Organizm dziecko() {
        return new Guarana(x,y,s);
    }

    @Override
    public String wypiszNazwe() {
        return "guarana";
    }

    
    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}
