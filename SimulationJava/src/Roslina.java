import java.util.Random;

public abstract class Roslina  extends Organizm {


    protected boolean probaRozsiania() {
        Random rand = new Random();
        int i = rand.nextInt(10);
        if (i == 0)return true;
        return false;
    }

    protected void rozsiej(Organizm n){
        int nX = x, nY = y,g= 0,r;
        int[] t2 = new int[s.getIleS()];
        int[][] t=s.getSasiad(this);
        for(int i=0;i<s.getIleS();i++){
            if(t[i][0]!=-1){
                t2[g]=i;
                g++;
            }
        }
        
        if (g != 0) {
            Random rand = new Random();
            r = rand.nextInt(g);
            nX = t[t2[r]][0];
            nY = t[t2[r]][1];
        }
        if(s.getPole(nX, nY)==null){
            n.setX(nX);
            n.setY(nY);
            n.setZycie(2);
            s.dodaj(nX, nY, n);
            makeLog(wypiszNazwe(), "", 1, nX, nY);
        }
    }

    public void makeLog(String A, String B, int co,int x,int y){
        if(co==1) s.setLog(s.getLog() + A +" rozsiewa sie na: (" + x + "," + y + ")" + "\n");
        if(co==2) s.setLog(s.getLog() + A +" zjada "+ B +" na: (" + x + "," + y + ")" + "\n");
        if(co==3) s.setLog(s.getLog() + A +" zjada "+ B +" i umiera na: (" + x + "," + y + ")" + "\n");
        if(co==4) s.setLog(s.getLog() + A +" zjada "+ B +" i zwieksza sile na: (" + x + "," + y + ")" + "\n");
        if(co==5) s.setLog(s.getLog() + A +" zabija "+ B +" na: (" + x + "," + y + ")" + "\n");
    }

    @Override
    protected void kolizja(int xx,int yy) {
        zycie = 0;
        s.setTab(x, y, s.getPole(xx, yy));
        s.setTab(xx,yy,null);
        s.getPole(x, y).setX(x);
        s.getPole(x, y).setY(y);
        makeLog(s.getPole(x, y).wypiszNazwe(), wypiszNazwe(), 2, x, y);
    }

    @Override
    public void akcja() {
       if(probaRozsiania()){
           Organizm n = dziecko();
           rozsiej(n);
       }
    }


     
}
