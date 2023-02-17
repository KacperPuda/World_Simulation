import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Klatka extends JPanel {

    private JButton[][] przyciski;

    Klatka(Swiat s,Pulpit p){
      this.setLayout(null); 
      int z=0,f=0;
      if(p.getHex()) f=s.getWymPolaY()/4;
      if(p.getHex())z=s.getWymPolaY()/2;
      this.setSize(s.getWymPolaY()*s.getYM()+z,s.getWymPolaX()*s.getXM()-f*(s.getXM()-1));
      this.setLocation(3,3);
      this.setBackground(new Color(4,125,188));
      JLabel tlohex;
      przyciski = new JButton[s.getXM()][];
     

      for(int i=0;i<s.getXM();i++) przyciski[i]=new JButton[s.getYM()];
        for(int i = 0 ; i < s.getXM() ; i++) {
         if(p.getHex()){
            if(i%2==0)z=0;
            else z=s.getWymPolaY()/2;
         }
            for(int j = 0 ; j< s.getYM() ; j++){
                if(s.getPole(i, j)!=null){
                    JLabel o = s.getPole(i,j).wypisz();
                    o.setBounds(s.getWymPolaY()*j+z,s.getWymPolaX()*i-i*f,s.getWymPolaY(),s.getWymPolaX());
                    this.add(o);
                    przyciski[i][j]=null;
                } else{
                    przyciski[i][j]=new JButton();
                    przyciski[i][j].addActionListener(p);
                    przyciski[i][j].setBounds(s.getWymPolaY()*j+z,s.getWymPolaX()*i-i*f+f,s.getWymPolaY(),s.getWymPolaX()-2*f);
                    przyciski[i][j].setBackground(new Color(166,224,238));
                    this.add(przyciski[i][j]);
                    if(s instanceof Hexagony){
                        tlohex = new JLabel(new ImageIcon("tlohex.png"));
                        tlohex.setBounds(s.getWymPolaY()*j+z,s.getWymPolaX()*i-i*f,s.getWymPolaY(),s.getWymPolaX());
                        this.add(tlohex);
                    }
                }
            }
      } 
    }

    public JButton getPrzyciski(int i,int j){
        return przyciski[i][j];
    }
}
