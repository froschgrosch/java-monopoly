import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gefaengnisfenster extends JFrame implements ActionListener
{
    Controller c;

    JFrame f = new JFrame("Spieler erstellen");
    JPanel p1 = new JPanel();

    JButton gefaengniskarte = new JButton("Gefaengnisfreikarte setzen");
    JButton freikaufen = new JButton("Fuer 200€ freikaufen");
    JLabel text = new JLabel("Wenn nichts von beiden gewuenscht ist, kann das Fenster geschlossen werden!");
    JButton ok = new JButton("Schließen");

    public  Gefaengnisfenster(Controller co){
        c = co;
        main();
    }

    public void main(){
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setSize( 500, 300 );
        f.setResizable(true);
        f.setVisible(true );

        f.add(p1);
        p1.add(gefaengniskarte);
        p1.add(freikaufen);
        p1.add(text);
        gefaengniskarte.addActionListener(this);
        freikaufen.addActionListener(this);p1.add(ok);
        ok.addActionListener(this);

        f.repaint();
    }

    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.gefaengniskarte){
            c.gefaengniskarte();
            f.dispose();
        }
        if(ae.getSource() == this.freikaufen){
            c.freikaufen();
            f.dispose();
        }
        if(ae.getSource() == this.ok){
            f.dispose();
        }
    }
}
