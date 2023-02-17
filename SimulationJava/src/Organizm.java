import javax.swing.JLabel;

public abstract class Organizm {

    static protected int BrakSasiada=-1;
    static protected int gora=1;
    static protected int dol=3;
    static protected int lewo=0;
    static protected int prawo=2;
    static protected int goraPrawo=4;
    static protected int goraLewo=6;
    static protected int dolPrawo=5;
    static protected int dolLewo=7;
    static protected int xpos=0;
    static protected int ypos=1;
    static protected int zyje=1;
    static protected int martwy=0;
    static protected int mlody=2;
    static protected int zdolnydoreprodukcji=0;
    static protected int plodny=3;
    static protected int nieplodny=5;


    protected int x;
    protected int y;
    protected int plodnosc = 0;
    protected int zycie = 1;
    protected int inicjatywa;
    protected int sila;
    private Organizm nas;
    private Organizm pop;
    protected JLabel obrazek;
    protected Swiat s;

    public int getPlodnosc() {
        return plodnosc;
    }

    public void setPlodnosc(int plodnosc) {
        this.plodnosc = plodnosc;
    }

    public int getZycie() {
        return zycie;
    }

    public void setZycie(int zycie) {
        this.zycie = zycie;
    }

    public Organizm getNas(){
        return this.nas;
    }

    public Organizm getPop(){
        return this.pop;
    }
    
    public void setNas(Organizm n){
        this.nas=n;
    }
    
    public void setPop(Organizm n){
        this.pop=n;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int n){
        this.x=n;
    }
    
    public void setY(int n){
        this.y=n;
    }

    public int getSila(){
        return this.sila;
    }

    public int getInicjatywa(){
        return this.inicjatywa;
    }

    public void setSila(int n){
        this.sila=n;
    }

    public abstract String wypiszNazwe();
    public abstract JLabel wypisz();
    protected abstract void kolizja(int xx,int yy);
    public abstract void akcja();
    public abstract Organizm dziecko();
    
    public void smierc(){
        Organizm pop = this.pop;
        Organizm nas = this.nas;
        if(pop!=null){
            if(nas!=null) pop.setNas(nas);
            else pop.setNas(null);
        } else{
            if(nas!=null) s.setHead(nas);
        }
        if(nas!=null){
            if(pop!=null) nas.setPop(pop);
            else nas.setPop(null);
        }
        if(nas==null && pop==null){
            s.setHead(null);
        }
    }
}
