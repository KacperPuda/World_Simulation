import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Mlecz extends Roslina {

    Mlecz(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=0;
        sila=0;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("mleczhex.png"));
        else obrazek = new JLabel(new ImageIcon("mlecz.png"));
    }


    @Override
    public void akcja(){
        for(int i=0;i<3;i++){
            if(probaRozsiania()){
                Organizm n = dziecko();
                rozsiej(n);
            }
        }
    }

    @Override
    public Organizm dziecko() {
        return new Mlecz(x,y,s);
    }

    @Override
    public String wypiszNazwe() {
        return "mlecz";
    }

    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}
