public class Kwadraty extends Swiat{

    Kwadraty(int xx,int yy,boolean zapis){
        xMaks=xx;
        yMaks=yy;
        tab = new Organizm[xx][];
        for(int i=0;i<xx;i++){
          tab[i] = new Organizm[yy];
        }
        if(!zapis)ustawianie();
        else wczytaj();
        ileS=4;
        ileSB=8;
        wymPolaX=80;
        wymPolaY=80;
    }

    @Override
    public int[][] getSasiad(Organizm o) {
        int [][] t = new int[4][];
        for(int i=0;i<4;i++) t[i]=new int[2];
        int z=0;
        for (int i = -1; i < 2; i++) {
           for(int j=-1;j < 2;j++){
                if((i==0 && j!= 0)||(i != 0 && j == 0)){
                    if(o.getX()+i < 0 || o.getY()+j < 0 || o.getX()+i>=xMaks || o.getY()+j>=yMaks){
                        t[z][0]=-1;
                        t[z][1]=-1;
                    } else {
                        t[z][0]=o.getX()+i;
                        t[z][1]=o.getY()+j;
                    }
                    z++;
                }
           }
        }
        return t;
    }
    
    @Override
    public int[][] getSasiadBarszczu(Organizm o) {
        int [][] t = new int[8][];
        for(int i=0;i<8;i++) t[i]=new int[2];
        int z=0;
        for (int i = -1; i < 2; i++) {
           for(int j=-1;j < 2;j++){
                if(i==0 && i==j) continue;
                if(o.getX()+i < 0 || o.getY()+j < 0 || o.getX()+i>=xMaks || o.getY()+j>=yMaks){
                    t[z][0]=-1;
                    t[z][1]=-1;
                } else {
                    t[z][0]=o.getX()+i;
                    t[z][1]=o.getY()+j;
                }
                z++;
                
           }
        }
        return t;
    }
    
}
