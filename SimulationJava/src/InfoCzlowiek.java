import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoCzlowiek extends JPanel {
    InfoCzlowiek(Swiat s){
        int f=0;
        if(s instanceof Hexagony) f=s.getWymPolaY()/2;
        this.setBounds(s.getWymPolaY()*s.getYM()+ 10+f,76,290,26);
        this.setBackground(new Color(4,125,188));
        if(s.getUmiejetnosc()) this.setBackground(new Color(130,60,188));
        if(!s.getUmiejetnosc()&&s.getCzasUmiejetnosci()<5) this.setBackground(new Color(130,130,188));
        if(s.getCzlowiekZyje()==false)this.setBackground(new Color(100,45,68));
        else this.add(new JLabel("Czlowiek idzie w:"));
    }
}
