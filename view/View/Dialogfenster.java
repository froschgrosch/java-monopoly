import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialogfenster extends JFrame implements ActionListener
{
    JFrame f = new JFrame("Meldung");
    JPanel p1 = new JPanel();

    JLabel nametext;
    JButton ok = new JButton("Schließen");

    public Dialogfenster(String text){
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setSize( 800, 400 );
        f.setResizable(true);
        f.setVisible( true );

        f.add(p1);
        nametext = new JLabel(text, SwingConstants.CENTER);
        p1.add(nametext);
        p1.add(ok);
        ok.addActionListener(this);

    }

    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.ok){
            f.dispose();
        }

    }
}