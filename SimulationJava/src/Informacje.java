import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Informacje extends JPanel {
    Informacje(Swiat s){
        int f=0,h=0;
        if(s instanceof Hexagony){
             f=s.getWymPolaY()/2;
             h=(s.xMaks-1)*s.getWymPolaX()/4;
        }
        this.setBounds(s.getWymPolaY()*s.getYM()+ 10+f,107,290,80*s.getXM()-140-h);
        this.setBackground(new Color(210,210,220));
        this.add(new JLabel("          Informacje o swiecie          "));
        JTextArea info = new JTextArea(s.getLog());
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setBackground(new Color(210,210,220));
        info.setBounds(80*s.getYM()+ 20+f,127,270,80*s.getXM()-120-h);
        this.add(info);
        System.out.println(s.getLog());
    }
}
