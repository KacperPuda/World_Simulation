import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Lis extends Zwierze {


    Lis(int xx,int yy, Swiat s){
        x=xx;
        y=yy;
        inicjatywa=9;
        sila=3;
        this.s=s;
        if(s instanceof Hexagony) obrazek = new JLabel(new ImageIcon("lishex.png"));
        else obrazek = new JLabel(new ImageIcon("lis.png"));
    }

    @Override
    public Organizm dziecko() {
        return new Lis(x,y,s);
    }
    @Override
    public void akcja() {
        int nX = x, nY = y,g= 0,r;
        int[] t2 = new int[s.getIleS()];
        int[][]t=s.getSasiad(this);

        for(int i=0;i<s.getIleS();i++){
            if(t[i][0]!=-1){
                if(s.getPole(t[i][xpos], t[i][ypos])==null){
                    t2[g]=i;
                    g++;
                } else if(s.getPole(t[i][xpos], t[i][ypos]).getSila()<sila){
                    t2[g]=i;
                    g++;
                }
            }
        }

        if (g != 0) {
            Random rand = new Random();
            r = rand.nextInt(g);
            nX = t[t2[r]][xpos];
            nY = t[t2[r]][ypos];
        }
        noweXY nowe = new noweXY(nX, nY);
        tura(nowe);
    }

    @Override
    public String wypiszNazwe() {
        return "lis";
    }

    @Override
    public JLabel wypisz() {
        return obrazek;
    }
    
}

