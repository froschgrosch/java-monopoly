
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spielererstellen extends JFrame implements ActionListener
{
    Controller c = new Controller();
    View v = new View(c);
    JFrame f = new JFrame("Spieler erstellen");
    JPanel p1 = new JPanel();

    JLabel nametext = new JLabel("Name des Spielers:");
    JTextField namefeld = new JTextField();

    
    Icon kreisblau = new ImageIcon("blauerkreis.png");
    JButton kreisblaubutton = new JButton(kreisblau); 

    Icon kreisrot = new ImageIcon("roterkreis.png");
    JButton kreisrotbutton = new JButton(kreisrot);

    Icon kreisgrün = new ImageIcon("grünerkreis.png");
    JButton kreisgrünbutton = new JButton(kreisgrün);

    Icon kreisrosa = new ImageIcon("rosakreis.png");
    JButton kreisrosabutton = new JButton(kreisrosa);

    Icon auto = new ImageIcon("auto.png");
    JButton autobutton = new JButton(auto);

    Icon flugzeug = new ImageIcon("flugzeug.png");
    JButton flugzeugbutton = new JButton(flugzeug);

    Icon schiff = new ImageIcon("schiff.png");
    JButton schiffbutton = new JButton(schiff);

    Icon zug = new ImageIcon("zug.png");
    JButton zugbutton = new JButton(zug);

    Icon kontrollbalkenfarbe = new ImageIcon("kontrollbalken.png");
    JLabel kontrollbalkenfarbelabel = new JLabel(kontrollbalkenfarbe);

    Icon kontrollbalkenfahrzeug = new ImageIcon("kontrollbalken.png");
    JLabel kontrollbalkenfahrzeuglabel = new JLabel(kontrollbalkenfahrzeug);

    JButton nocheinspielerbutton = new JButton("Weiteren Spieler hinzufügen");
    JButton spielstartbutton = new JButton("Spiel starten");

    String spielername = null;
    int spielerfarbe = 100;
    int spielerfahrzeug = 100;

    public  Spielererstellen(){
        main();
    }

    public void main()
    {
 
 
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setSize( 800, 600 );
        f.setResizable(true);
        f.setVisible( true );

        p1.setLayout(null);
        f.add(p1);

        nametext.setBounds(200, 20, 170,40);
        p1.add(nametext);

        namefeld.setBounds(400, 20, 200,40);
        p1.add(namefeld);

        kreisblaubutton.setBounds(295, 150, 50,50);
        p1.add(kreisblaubutton);
        kreisblaubutton.addActionListener(this);

        kreisrotbutton.setBounds(365, 150, 50,50);
        p1.add(kreisrotbutton);
        kreisrotbutton.addActionListener(this);

        kreisgrünbutton.setBounds(435, 150, 50,50);
        p1.add(kreisgrünbutton);
        kreisgrünbutton.addActionListener(this);

        kreisrosabutton.setBounds(505, 150, 50,50);
        p1.add(kreisrosabutton);
        kreisrosabutton.addActionListener(this);

    
        autobutton.setBounds(100, 300, 130,120);
        p1.add(autobutton);
        autobutton.addActionListener(this);

        flugzeugbutton.setBounds(240, 300, 130,120);
        p1.add(flugzeugbutton);
        flugzeugbutton.addActionListener(this);

        schiffbutton.setBounds(380, 300, 130,120);
        p1.add(schiffbutton);
        schiffbutton.addActionListener(this);

        zugbutton.setBounds(520, 300, 130,120);
        p1.add(zugbutton);
        zugbutton.addActionListener(this);

        spielstartbutton.setBounds(190, 500, 200,40);
        p1.add(spielstartbutton);
        spielstartbutton.addActionListener(this);

        nocheinspielerbutton.setBounds(410, 500, 200,40);
        p1.add(nocheinspielerbutton);
        nocheinspielerbutton.addActionListener(this);

    
    }
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.kreisblaubutton){
            spielerfarbe = 0;
        }

        if(ae.getSource() == this.kreisrotbutton){
            spielerfarbe = 1;
        }

        if(ae.getSource() == this.kreisgrünbutton){
            spielerfarbe = 2;
        }

        if(ae.getSource() == this.kreisrosabutton){
            spielerfarbe = 3;
        }

        if(ae.getSource() == this.autobutton){
            spielerfahrzeug = 0;
        }

        if(ae.getSource() == this.flugzeugbutton){
            spielerfahrzeug = 1;
        }

        if(ae.getSource() == this.schiffbutton){
            spielerfahrzeug = 2;
        }

        if(ae.getSource() == this.zugbutton){
            spielerfahrzeug = 3;
        }

        if(ae.getSource() == this.spielstartbutton){
            spielername = namefeld.getText();
            f.dispose();
            
            c.spielererstellen("" + spielername, spielerfarbe, spielerfahrzeug);
            
            v.spielerfahrzeugerstellen(spielerfahrzeug);
            
        }

        if(ae.getSource() == this.nocheinspielerbutton){
            spielername = namefeld.getText();
            
            
            
           c.spielererstellen(spielername, spielerfarbe, spielerfahrzeug);
            
           v.spielerfahrzeugerstellen(spielerfahrzeug);
            
        }

    }
}
