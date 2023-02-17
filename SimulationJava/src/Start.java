import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Start implements ActionListener{

    private JFrame okno;
    private JPanel panel;
    private JLabel l1,l2;
    private JSlider sowakX, sowakY;
    private JButton generuj;
    private boolean kwadraty=true;

    Start(boolean hex){
        okno = new JFrame("Start");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        if(hex)kwadraty=false;
        l1 = new JLabel("wysokosc");
        l2 = new JLabel("szerokosc");
        generuj = new JButton("Generuj");
        if(!hex)sowakX = new JSlider(1,12,10);
        else sowakX = new JSlider(1,16,10);
        if(!hex)sowakY = new JSlider(1,20,10);
        else sowakY = new JSlider(1,19,10);
        sowakX.setPreferredSize(new Dimension(350, 100));
        sowakY.setPreferredSize(new Dimension(350, 100));
        sowakX.setPaintTicks(true);
        sowakX.setMajorTickSpacing(1);
        sowakX.setPaintTrack(true);
        sowakX.setPaintLabels(true);
        sowakY.setPaintTicks(true);
        sowakY.setMajorTickSpacing(1);
        sowakY.setPaintTrack(true);
        sowakY.setPaintLabels(true);
        generuj.addActionListener((ActionListener) this);
        panel.add(l1);
        panel.add(sowakX);
        panel.add(l2);
        panel.add(sowakY);
        panel.add(generuj);
        okno.add(panel);
        okno.setSize(400,400);
        okno.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==generuj){
            okno.dispose();
            new Pulpit(sowakX.getValue(), sowakY.getValue(),false,kwadraty);
        } 
    }
}
