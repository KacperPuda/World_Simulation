import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Zolw extends Zwierze {
    
    static private int probaruchu=4;
    static private int udana=0;

    Zolw(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=1;
        sila=2;
        
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("zolwhex.png"));
        else obrazek = new JLabel(new ImageIcon("zolw.png"));
    }

    @Override
    public Organizm dziecko() {
        return new Lis(x,y,s);
    }

    @Override
    public void akcja() {
        int r;
        Random rand = new Random();
        r = rand.nextInt(probaruchu);
        if(r==udana){
            noweXY nowe = new noweXY(x,y);
            ruch(nowe);
            tura(nowe);
        }
    }

    @Override
    protected void kolizja(int nX, int nY) {
        makeLog(s.getPole(nX, nY).wypiszNazwe(), "", 3, nX, nY);
        if (s.getPole(nX, nY).getSila() < 5) {
            makeLog(wypiszNazwe(), s.getPole(nX, nY).wypiszNazwe(), 4, x, y);
            return;
        }
        if (s.getPole(nX, nY).getSila() > sila || s.getPole(nX, nY).getSila() == sila) {
            makeLog(s.getPole(nX, nY).wypiszNazwe(), wypiszNazwe(), 2, x, y);
            zycie = 0;
            s.setTab(x, y,s.getPole(nX,nY));
            s.setTab(nX, nY,null);
            s.getPole(x, y).setX(x);
            s.getPole(x, y).setY(y);
        }
        else {
            if (s.getPole(nX, nY).getSila() < sila) {
                makeLog(wypiszNazwe(), s.getPole(x, y).wypiszNazwe(), 2, x, y);
                s.getPole(nX, nY).zycie = martwy;
                s.setTab(nX, nY,null);
            }
        }
    }


    @Override
    public String wypiszNazwe() {
        return "zolw";
    }

    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}

