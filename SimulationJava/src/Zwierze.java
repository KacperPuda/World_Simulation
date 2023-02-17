import java.util.Random;

public abstract class Zwierze  extends Organizm {


    protected void ruch(noweXY nowe){
        Random rand = new Random();
        int g=0,gdzie;
        int[][]t=s.getSasiad(this);
        int[] t2 = new int[s.getIleS()];
        for(int i=0;i<s.getIleS();i++){
            if(t[i][xpos]!=-1){
                 t2[g]=i;
                 g++;
            }  
        }
        if(g!=0){
            gdzie = rand.nextInt(g);
            nowe.nX = t[t2[gdzie]][xpos];
            nowe.nY = t[t2[gdzie]][ypos];
        }
    }

    protected void tura(noweXY nowe){
        if(s.getPole(nowe.nX, nowe.nY)==null){
            s.setTab(nowe.nX, nowe.nY, this);
            s.setTab(x, y, null);
            this.x=nowe.nX;
            this.y=nowe.nY;
        } else {
            if(s.getPole(nowe.nX, nowe.nY).wypiszNazwe()==wypiszNazwe()){
                if(plodnosc==zdolnydoreprodukcji && s.getPole(nowe.nX, nowe.nY).getPlodnosc()== zdolnydoreprodukcji && this != s.getPole(nowe.nX, nowe.nY)){
                    s.getPole(nowe.nX, nowe.nY).setPlodnosc(nieplodny);
                    s.getPole(nowe.nX, nowe.nY).setZycie(mlody);
                    plodnosc = nieplodny;
                    Organizm n = dziecko();
                    reprodukcja(nowe.nX,nowe.nY, n);
                }
            } else {
                s.getPole(nowe.nX, nowe.nY).kolizja(x,y);
            } 
        }
    }

    public void reprodukcja (int xx, int yy, Organizm n){
        n.setZycie(mlody);
        n.setPlodnosc(plodny);
        boolean miejsce = false;
        for(int z =0 ; z < 2 ; z++){
            int[][] t=s.getSasiad(this);
            if(!miejsce){
                if (z == 1) t=s.getSasiad(s.getPole(xx, yy));
                for(int i=0;i<s.getIleS();i++){
                   if(t[i][xpos]!=BrakSasiada){
                       if(s.getPole(t[i][xpos], t[i][ypos])==null){
                           miejsce=true;
                           n.setX(t[i][xpos]);
                           n.setY(t[i][ypos]);
                           s.dodaj(t[i][xpos], t[i][ypos], n);
                           makeLog(wypiszNazwe(), wypiszNazwe(), 1, n.getX(), n.getY());
                           return;
                       }
                   }
                }
            }
        }
    }

    public void makeLog(String A, String B, int co,int x,int y){
        if(co==1) s.setLog(s.getLog() + A +" rodzi sie na: (" + x + "," + y + ")" + "\n");
        if(co==2) s.setLog(s.getLog() + A +" zabija "+ B +" na: (" + x + "," + y + ")" + "\n");
        if(co==3) s.setLog(s.getLog() + A +" atakuje " + "\n");
        if(co==4) s.setLog(s.getLog() + A +" odbija "+ B +" na: (" + x + "," + y + ")" + "\n");
        if(co==5) s.setLog(s.getLog() + A +" ucieka od "+ B +" na: (" + x + "," + y + ")" + "\n");
        if(co==6) s.setLog(s.getLog() + A +" nie walczy z "+ B +" na: (" + x + "," + y + ")" + "\n");
    }

    @Override
    protected void kolizja(int xx,int yy) {
        makeLog(s.getPole(xx, yy).wypiszNazwe(), "", 3, x, y);
        if(s.getPole(xx, yy).getSila() >= sila){
            zycie=0;
            s.setTab(x, y, s.getPole(xx, yy));
            s.setTab(xx, yy, null);
            s.getPole(x, y).setX(x);
            s.getPole(x, y).setY(y);
            makeLog(s.getPole(x, y).wypiszNazwe(), wypiszNazwe(), 2, x, y);
        } else {
            if(s.getPole(xx, yy).getSila() < sila){
                makeLog(wypiszNazwe(), s.getPole(xx, yy).wypiszNazwe(), 2, x, y);
                s.getPole(xx, yy).setZycie(martwy);
                s.setTab(xx, yy, null);
            }
        }
    }

    @Override
    public void akcja() {
        noweXY xy = new noweXY(x, y);
        ruch( xy);
        tura(xy);
    }


     
}
