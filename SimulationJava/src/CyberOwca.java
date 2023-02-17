import java.util.Random;
import java.lang.Math;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CyberOwca extends Zwierze {
    private int xb=x,yb=y;
    static public int kierunek=2;

    CyberOwca(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=4;
        sila=11;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("cbyerhex.png"));
        else obrazek = new JLabel(new ImageIcon("cyberowca.png"));
    }

    @Override
    public Organizm dziecko() {
        return new CyberOwca(x,y,s);
    }

    @Override
    public void akcja() {
        int odleglosc = -1;
        noweXY nowe = new noweXY(x, y);
        boolean wykonywanie = false;
        if (s.getPole(xb, yb)==null) wykonywanie = true;
        else {
            if (s.getPole(xb, yb).wypiszNazwe() != "barszcz sosnowskiego") wykonywanie = true;
        }
        if (wykonywanie) {
            xb = x;
            yb = y;
            for (int i = 0; i < s.getXM(); i++) {
                for (int j = 0; j < s.getYM(); j++) {
                    if (s.getPole(i, j) != null) {
                        if (s.getPole(i, j).wypiszNazwe() == "barszcz sosnowskiego") {
                            if (odleglosc == -1) {
                                odleglosc = (int) Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2));
                                xb = i;
                                yb = j;
                            }
                            else {
                                int tmp = (int) Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2));
                                if (odleglosc > tmp) {
                                    odleglosc = tmp;
                                    xb = i;
                                    yb = j;
                                }
                            }
                        }
                    }
                }
            }	
        }
        if (s.getPole(xb, yb)!=null) {
            if (s.getPole(xb, yb).wypiszNazwe() == "barszcz sosnowskiego") {
                Random rand = new Random();
                int co = rand.nextInt(kierunek);
                if (x == xb)co = 1;
                if (y == yb) co = 0;
                if (co == 0) {
                    if (xb > x) nowe.nX = x + 1;
                    if (xb < x) nowe.nX = x - 1;
                }
                if (co == 1) {
                    if (yb > y) nowe.nY = y + 1;
                    if (yb < y) nowe.nY = y - 1;
                }
            }
            else ruch(nowe);
        }
        else ruch(nowe);
        tura(nowe);
    }



    @Override
    public String wypiszNazwe() {
        return "cyber-owca";
    }

    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}
