import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public abstract class Swiat{
    static private int Losowanie =40;
    static private int wylosowanoWilka =1;
    static private int wylosowanoOwce =2;
    static private int wylosowanoLisa =3;
    static private int wylosowanoZolwia =4;
    static private int wylosowanoAntylope =5;
    static private int wylosowanoCyberOwce =6;
    static private int wylosowanoTrawe =7;
    static private int wylosowanoMlecza =8;
    static private int wylosowanoGuarane =9;
    static private int wylosowanoJagody =10;
    static private int wylosowanoBarszcz =11;
    protected int xMaks;
    protected int yMaks;
    protected int ileS;
    protected int ileSB;
    protected int wymPolaX;
    protected int wymPolaY;
    private int gdzieCz;
    private int czasUmiejetnosci=5;
    protected Organizm[][] tab;
    private Organizm head=null;
    private boolean umiejetnosc = false;
    private boolean czlowiekZyje = false;
    private String log;
    Organizm czlowiek;

    //private String log;
    //private int tura;

  

    public abstract int[][] getSasiad(Organizm o);
    public abstract int[][] getSasiadBarszczu(Organizm o);

    public int getWymPolaX() {
        return wymPolaX;
    }

    public int getWymPolaY() {
        return wymPolaY;
    }

    public int getIleS() {
        return ileS;
    }

    public int getIleSB() {
        return ileSB;
    }

    public int getCzasUmiejetnosci() {
        return czasUmiejetnosci;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    public boolean getUmiejetnosc(){
        return umiejetnosc;
    }

    public void setUmiejetnosc(boolean umiejetnosc) {
        this.umiejetnosc = umiejetnosc;
    }

    public boolean getCzlowiekZyje(){
        return czlowiekZyje;
    }

    public int getGdzieCz() {
        return gdzieCz;
    }

    public void setGdzieCz(int gdzieCz) {
        this.gdzieCz = gdzieCz;
    }

    public void setCzlowiekZyje(boolean czlowiekZyje) {
        this.czlowiekZyje = czlowiekZyje;
    }

    public void setCzasUmiejetnosci(int czasUmiejetnosci) {
        this.czasUmiejetnosci = czasUmiejetnosci;
    }
    
    public void setCzlowiek(Organizm czlowiek) {
        this.czlowiek = czlowiek;
    }

    public void generujZapis(){
        String z="";
        Organizm obecny =head;
        while(obecny!=null){
            if(obecny.getZycie()!=0){
                z += obecny.wypiszNazwe()+","+obecny.getX()+","+obecny.getY()+","+obecny.getSila()+","+obecny.getPlodnosc();
                if(obecny==czlowiek) z+=","+czasUmiejetnosci+","+umiejetnosc;
                z+="\n";
            }
            obecny=obecny.getNas();
        }
        File zapis = new File("save.txt");
        if(!zapis.exists())
            try {
                zapis.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if(zapis.canWrite()){
             Formatter fm;
            try {
                fm = new Formatter(zapis);
             fm.format("%s", z);
             fm.close();
             System.out.println("wygenerowano zapis 1");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        z=xMaks+","+yMaks;
        if(this instanceof Hexagony) z+=",H";
        else z+=",K";
        File plansza = new File("swiat.txt");
        if(!plansza.exists())
             try {
                plansza.createNewFile();  
             } catch (IOException e) {
                 e.printStackTrace();
              }
        if(plansza.canWrite()){
            Formatter fm;
            try {
                fm = new Formatter(plansza);
                fm.format("%s", z);
                fm.close();
                System.out.println("wygenerowano zapis 2");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void wczytaj(){
        int x,y,sila,plodnosc,g;
        String xx,yy,silaa,plodnoscc,czass,um,napis,zwierze;
        try {
            Scanner odczyt = new Scanner(new File("save.txt"));
            while(odczyt.hasNextLine()){
                napis=odczyt.nextLine();
                zwierze="";
                g=0;
                xx="";
                yy="";
                silaa="";
                plodnoscc="";
                czass="";
                um="";
                for(int i=0;i<napis.length();i++){
                    if(napis.charAt(i)==',') g++;
                    else{
                        if(g==0) zwierze+=napis.charAt(i);
                        if(g==1) xx+=napis.charAt(i);
                        if(g==2) yy+=napis.charAt(i);
                        if(g==3) silaa+=napis.charAt(i);
                        if(g==4) plodnoscc+=napis.charAt(i);
                        if(g==5) czass+=napis.charAt(i);
                        if(g==6) um+=napis.charAt(i);
                    }
                }
                x=Integer.parseInt(xx);
                y=Integer.parseInt(yy);
                sila=Integer.parseInt(silaa);
                plodnosc=Integer.parseInt(plodnoscc);
                Organizm n=null;
                if("czlowiek".equals(zwierze)){
                    czasUmiejetnosci=Integer.parseInt(czass);
                    if("true".equals(um)) umiejetnosc=true;
                    n=new Czlowiek(x, y, this);
                    this.czlowiek=n;
                    czlowiekZyje=true;
                }
                if("wilk".equals(zwierze)) n=new Wilk(x, y, this);
                if("antylopa".equals(zwierze)) n=new Antylopa(x, y, this);
                if("lis".equals(zwierze)) n=new Lis(x, y, this);
                if("zolw".equals(zwierze)) n=new Zolw(x, y, this);
                if("cyber-owca".equals(zwierze)) n=new CyberOwca(x, y, this);
                if("owca".equals(zwierze)) n=new Owca(x, y, this);
                if("trawa".equals(zwierze)) n=new Trawa(x, y, this);
                if("mlecz".equals(zwierze)) n=new Mlecz(x, y, this);
                if("barszcz sosnowskiego".equals(zwierze)) n=new Barszcz(x, y, this);
                if("guarana".equals(zwierze)) n=new Guarana(x, y, this);
                if("wilcze jagody".equals(zwierze)) n=new Jagody(x, y, this);
                if(n!=null){
                    dodaj(x,y,n);
                    n.setPlodnosc(plodnosc);
                    n.setSila(sila);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dodaj(int i,int j, Organizm n){
        tab[i][j]=n;
        if(head==null){
            head=n;
        } else {
            Organizm obecny = head;
            if(obecny.getInicjatywa() < n.getInicjatywa()){
                n.setNas(obecny);
                obecny.setPop(n);
                head=n;
            } else {
                while(obecny.getNas()!=null){
                    if(obecny.getNas().getInicjatywa() < n.getInicjatywa()) break;
                    obecny=obecny.getNas();
                }
                n.setPop(obecny);
                if(obecny.getNas()!=null){
                    n.setNas(obecny.getNas());
                    obecny.getNas().setPop(n);
                }
                obecny.setNas(n);
            }
        }
    }

    protected void ustawianie(){
        int czlowiekX,czlowiekY;
        Random rand = new Random();
        czlowiekX = rand.nextInt(xMaks);
        czlowiekY = rand.nextInt(yMaks);
        for (int i = 0; i < xMaks; i++) {
            for (int j = 0; j < yMaks; j++) {
                int x = rand.nextInt(Losowanie);
                if(i==czlowiekX && j==czlowiekY){
                    dodaj(i,j,new Czlowiek(i, j,this));
                    czlowiekZyje = true;
                    czlowiek=getPole(i, j);
                    continue;
                }
                if(x==wylosowanoWilka) dodaj(i,j,new Wilk(i, j,this));
                if(x==wylosowanoOwce) dodaj(i,j,new Owca(i, j,this));
                if(x==wylosowanoLisa) dodaj(i,j,new Lis(i, j,this));
                if(x==wylosowanoZolwia) dodaj(i,j,new Zolw(i, j,this));
                if(x==wylosowanoAntylope) dodaj(i,j,new Antylopa(i, j,this));
                if(x==wylosowanoCyberOwce) dodaj(i,j,new CyberOwca(i, j,this));
                if(x==wylosowanoTrawe) dodaj(i,j,new Trawa(i, j,this));
                if(x==wylosowanoMlecza) dodaj(i,j,new Mlecz(i, j,this));
                if(x==wylosowanoGuarane) dodaj(i,j,new Guarana(i, j,this));
                if(x==wylosowanoJagody) dodaj(i,j,new Jagody(i, j,this));
                if(x==wylosowanoBarszcz) dodaj(i,j,new Barszcz(i, j,this));
            }
        }
    }


    
   
    private void organizmy(){
        Organizm obecny=head;
        while(obecny!=null){
            Organizm nas = obecny.getNas();
            if (obecny.plodnosc != 0)obecny.setPlodnosc(obecny.getPlodnosc()-1);;
            if(obecny.getZycie()==1){
                obecny.akcja();
            } 
            else {
                if(obecny.getZycie() == 0){
                    if(obecny.wypiszNazwe()=="czlowiek") czlowiekZyje = false;
                    obecny.smierc();
                }
            }

            obecny=nas;
        }
        if(czlowiekZyje)if(czlowiek.getZycie()==0)czlowiekZyje=false;
    }

    private void start(){
        Organizm obecny = head;
	while (obecny != null) {
		if(obecny.zycie != 0 )obecny.setZycie(1);
		else {
            if(obecny.wypiszNazwe()=="czlowiek") czlowiekZyje = false;
            obecny.smierc();
		}
		obecny = obecny.getNas();
	}
    }

    public void symuluj(){
        if(umiejetnosc){
            czasUmiejetnosci--;
            if(czasUmiejetnosci==0) umiejetnosc=false;
        } else if(czasUmiejetnosci!=5) czasUmiejetnosci++;
        this.log="";
        start();
        organizmy();
        gdzieCz=10;
    }

    public Organizm getPole(int xx,int yy){
        return tab[xx][yy];
    }


    public int getXM(){
        return this.xMaks;
    }

    public int getYM(){
        return this.yMaks;
    }

    public void setTab( int x,int y, Organizm n){
        this.tab[x][y]=n;
    }

    public void setHead(Organizm head) {
        this.head = head;
    }


}
