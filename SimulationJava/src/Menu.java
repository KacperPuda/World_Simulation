import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu implements ActionListener{

    private JFrame okno;
    private JPanel panel;
    private JButton przyciskk;
    private JButton przyciskh;
    private JButton generuj;

    Menu(){
        okno = new JFrame("Menu");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null); 
        przyciskk = new JButton("Kwadrotowa plansza");
        przyciskh = new JButton("Hexagonalna plansza");
        generuj = new JButton("Wczytaj");
        przyciskk.setBounds(20,20,350,40);
        przyciskh.setBounds(20,70,350,40);
        generuj.setBounds(20,120,350,40);
        przyciskk.addActionListener((ActionListener) this);
        przyciskh.addActionListener((ActionListener) this);
        generuj.addActionListener((ActionListener) this);
        panel.add(generuj);
        panel.add(przyciskk);
        panel.add(przyciskh);
        okno.add(panel);
        okno.setSize(400,220);
        okno.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==przyciskk){
            okno.dispose();
            new Start(false);
        }
        if(e.getSource()==przyciskh){
            okno.dispose();
            new Start(true);
        }
        if(e.getSource()==generuj){
            okno.dispose();
            new Pulpit(0, 0,true,true);
        }
        
    }
}
