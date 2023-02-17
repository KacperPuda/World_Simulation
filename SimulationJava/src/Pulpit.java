
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pulpit extends JFrame implements ActionListener{

    private Swiat s;
    private Klatka k;
    private InfoCzlowiek infoCz;
    private Informacje info;
    private JButton nextRoundButton;
    private Klawisze z;
    private boolean hex=false;
    private boolean znaleziono=true;
    private int gdzieIdzieCzlowiek=10;
    private String[] organizmy = {"-","czlowiek","wilk","owca","lis","zolw","antylopa","cyber-owca","trawa","mlecz","guarana","wilcze jagody","barszcz sosnowskiego"};
    private JComboBox box;

    Pulpit(int xx,int yy,boolean zapis,boolean plansza){
        this.setTitle("Kacper Puda s188625 gr.4 Symulator zycia");
        if(zapis==false){
           if(plansza) s = new Kwadraty(xx, yy,false);
           else s = new Hexagony(xx, yy,false);
        }
        else s = wczytajSwiat();
        if(s instanceof Hexagony) plansza= false;
        int f=0,h=0;
        if(!plansza){
            hex=true;
            f=s.wymPolaY/2;
            h=(s.xMaks-1)*20;
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(s.getWymPolaY()*s.getYM()+320+f,s.getWymPolaX()*s.getXM()+45-h);
        this.setLayout(null);
        box=new JComboBox<>(organizmy);
        box.setBounds(s.getWymPolaY()*s.getYM()+10+f,s.getWymPolaX()*s.getXM()-28-h,290,30);
        nextRoundButton= new JButton();
        nextRoundButton.setText("next round");
        nextRoundButton.setSize(290,50);
        nextRoundButton.setLocation(s.getWymPolaY()*s.getYM()+10+f,20);
        nextRoundButton.addActionListener(this);
        this.setFocusable(true);
        infoCz = new InfoCzlowiek(s);
        info = new Informacje(s);
        z=new Klawisze(this);
        this.addKeyListener(z);
        k=new Klatka(s,this);
        this.add(infoCz);
        this.add(info);
        this.add(k);
        this.add(box);
        this.setVisible(true);
        this.add(nextRoundButton);
        if(!znaleziono)this.dispose();
    }

    public boolean getHex(){
        return hex;
    }

    public Swiat wczytajSwiat(){
        int nx=1,ny=1,g=0;
        String xx="",yy="",rodzaj="";
        try {
            Scanner odczyt = new Scanner(new File("swiat.txt"));
            while(odczyt.hasNextLine()){
                String z = odczyt.nextLine();
                for(int i=0;i<z.length();i++){
                    if(z.charAt(i)==',') g++;
                    else if(g==0) xx+=z.charAt(i);
                    else if(g==1) yy+=z.charAt(i);
                    else rodzaj+=z.charAt(i);
                }
            }
            nx=Integer.parseInt(xx);
            ny=Integer.parseInt(yy);
        } catch (FileNotFoundException e) {
            znaleziono=false;
            System.out.println("nie znaleziono pliku do odczytu");
            new Menu();
        }
        if(rodzaj.equals("K"))return new Kwadraty(nx,ny,true);
        else return new Hexagony(nx,ny,true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==nextRoundButton){
            s.setGdzieCz(gdzieIdzieCzlowiek);
            s.symuluj();
            gdzieIdzieCzlowiek=10;
            this.remove(k);
            k=new Klatka(s,this);
            this.remove(info);
            info=new Informacje(s);
            this.add(info);  
            this.add(k);
            this.remove(infoCz);
            infoCz=new InfoCzlowiek(s);  
            this.add(infoCz); 
            requestFocus();
            SwingUtilities.updateComponentTreeUI(this);
        }else{
            for(int i=0;i<s.getXM();i++){
                for(int j=0;j<s.getYM();j++){
                    if(k.getPrzyciski(i, j)!=null){
                        if(e.getSource()==k.getPrzyciski(i, j)){
                            Organizm n=null;
                            if(box.getSelectedItem().equals("czlowiek")){
                                if(!s.getCzlowiekZyje()){
                                    n=new Czlowiek(i, j, s);
                                    s.setCzlowiekZyje(true);
                                    s.setCzasUmiejetnosci(5);
                                    s.setCzlowiek(n);
                                }else System.out.println("nie mozna dodac czlowieka - czlowiek zyje");
                            }
                            if(box.getSelectedItem().equals("wilk")) n =new Wilk(i, j, s);
                            if(box.getSelectedItem().equals("owca")) n =new Owca(i, j, s);
                            if(box.getSelectedItem().equals("lis")) n =new Lis(i, j, s);
                            if(box.getSelectedItem().equals("antylopa")) n =new Antylopa(i, j, s);
                            if(box.getSelectedItem().equals("zolw")) n =new Zolw(i, j, s);
                            if(box.getSelectedItem().equals("cyber-owca")) n =new CyberOwca(i, j, s);
                            if(box.getSelectedItem().equals("trawa")) n =new Trawa(i, j, s);
                            if(box.getSelectedItem().equals("mlecz")) n =new Mlecz(i, j, s);
                            if(box.getSelectedItem().equals("guarana")) n =new Guarana(i, j, s);
                            if(box.getSelectedItem().equals("wilcze jagody")) n =new Jagody(i, j, s);
                            if(box.getSelectedItem().equals("barszcz sosnowskiego")) n =new Barszcz(i, j, s);
                            if(n!=null) s.dodaj(i, j, n);
                            this.remove(k);
                            k=new Klatka(s,this);
                            this.add(k);
                            SwingUtilities.updateComponentTreeUI(this);
                        }
                    }
                }
            }
        }
    }

    public class Klawisze extends KeyAdapter{
        private Pulpit p;
        static private int gora=1;
        static private int dol=3;
        static private int lewo=0;
        static private int prawo=2;
        static private int goraPrawo=4;
        static private int goraLewo=6;
        static private int dolPrawo=5;
        static private int dolLewo=7;
        Klawisze(Pulpit p){
            this.p=p;
        }
        @Override
        public void keyPressed(KeyEvent e){
            if(s.getCzlowiekZyje()){
                JLabel ii = new JLabel();  
                switch (e.getKeyCode()){
                    case 37:
                        ii.setText(" lewo"); 
                        if(s instanceof Hexagony){
                            if(gdzieIdzieCzlowiek==gora){
                                gdzieIdzieCzlowiek=goraLewo;
                                ii.setText(" gora - lewo"); 
                            } else if(gdzieIdzieCzlowiek==dol){
                                ii.setText(" dol - lewo");
                                gdzieIdzieCzlowiek=dolLewo;
                            }  else gdzieIdzieCzlowiek = lewo; 
                        } else gdzieIdzieCzlowiek = lewo;  
                        break;
                    case 38:
                        ii.setText(" gore"); 
                        gdzieIdzieCzlowiek = gora; 
                        break;
                    case 39:
                        ii.setText(" prawo"); 
                        if(s instanceof Hexagony){
                            if(gdzieIdzieCzlowiek==gora){
                                gdzieIdzieCzlowiek=goraPrawo;
                                ii.setText(" gora - prawo"); 
                            } else if(gdzieIdzieCzlowiek==dol){
                                ii.setText(" dol - prawo");
                                gdzieIdzieCzlowiek = dolPrawo; 
                            }  else gdzieIdzieCzlowiek = prawo; 
                        } else gdzieIdzieCzlowiek = prawo; 
                        break;
                    case 40:
                        ii.setText(" dol"); 
                        gdzieIdzieCzlowiek = dol;    
                        break;   
                    case 88:
                        if(s.getCzasUmiejetnosci()==5 && !s.getUmiejetnosc()){
                            s.setUmiejetnosc(true);  
                            ii.setText(" umiejetnosc"); 
                        }
                        break;  
                }         
                
                p.remove(infoCz);
                infoCz=new InfoCzlowiek(s);
                infoCz.add(ii);  
                p.add(infoCz); 
                SwingUtilities.updateComponentTreeUI(p); 
            }

            if(e.getKeyCode()==90) s.generujZapis();
        }
    }
    
}
