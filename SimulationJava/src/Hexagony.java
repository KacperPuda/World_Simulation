public class Hexagony extends Swiat{

    Hexagony(int xx,int yy,boolean zapis){
        xMaks=xx;
        yMaks=yy;
        tab = new Organizm[xx][];
        for(int i=0;i<xx;i++){
          tab[i] = new Organizm[yy];
        }
        if(!zapis)ustawianie();
        else wczytaj();
        ileS=6;
        ileSB=6;
        wymPolaX=80;
        wymPolaY=80;
    }

    @Override
    public int[][] getSasiad(Organizm o) {
        int [][] t = new int[6][];
        for(int i=0;i<6;i++) t[i]=new int[2];
        int z=0,k;
        for (int i =-1; i < 2; i++) {
            if(o.getX()%2==0)k=-1;
            else k=0;
            if(i==0) k=-1;
            for(int j=k;j<k+2;j++){
                if(i==0 && j==0) j++;
                if(o.getX()+i<0||o.getX()+i>=xMaks ||o.getY()+j<0||o.getY()+j>=yMaks){
                    t[z][0]=-1;
                    t[z][1]=-1;
                }else{
                    t[z][0]=o.getX()+i;
                    t[z][1]=o.getY()+j;
                }
                z++;
            }
        }
        return t;
    }
    
    @Override
    public int[][] getSasiadBarszczu(Organizm o) {
        int [][] t = new int[6][];
        for(int i=0;i<6;i++) t[i]=new int[2];
        int z=0,k;
        for (int i =-1; i < 2; i++) {
            if(o.getX()%2==0)k=-1;
            else k=0;
            if(i==0) k=-1;
            for(int j=k;j<k+2;j++){
                if(i==0 && j==0) j++;
                if(o.getX()+i<0||o.getX()+i>=xMaks ||o.getY()+j<0||o.getY()+j>=yMaks){
                    t[z][0]=-1;
                    t[z][1]=-1;
                }else{
                    t[z][0]=o.getX()+i;
                    t[z][1]=o.getY()+j;
                }
                z++;
            }
        }
        return t;
    }
    
}
