import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;

public class View extends JFrame implements ActionListener
{
    JLabel besitzanzeige;

    
    
    Controller c; 
    JFrame f = new JFrame("Monopoly");
    JLayeredPane p1 = new JLayeredPane();

    Icon spielfeldbild = new ImageIcon("Spielfeld.png");
    JLabel spielfeld = new JLabel(spielfeldbild);

    Icon auto = new ImageIcon("autofigur.png");
    JLabel autofigur = new JLabel(auto);

    Icon flugzeug = new ImageIcon("flugzeugfigur.png");
    JLabel flugzeugfigur = new JLabel(flugzeug);

    Icon schiff = new ImageIcon("schifffigur.png");
    JLabel schifffigur = new JLabel(schiff);

    Icon zug = new ImageIcon("zugfigur.png");
    JLabel zugfigur = new JLabel(zug);

    Icon geldmitteicon = new ImageIcon("geldmitte.jpg");
    JLabel geldmitte = new JLabel(geldmitteicon);

    Icon i1 = new ImageIcon("1.png");
    Icon i2 = new ImageIcon("2.png");
    Icon i3 = new ImageIcon("3.png");
    Icon i4 = new ImageIcon("4.png");
    Icon i5 = new ImageIcon("5.png");
    Icon i6 = new ImageIcon("6.png");

    JLabel w1 = new JLabel(i1);
    JLabel w2 = new JLabel(i2);

    JButton wuerfelbutton = new JButton("Wuerfeln");

    JLabel textanzeige = new JLabel(" ", SwingConstants.CENTER);
    JLabel zuganzeige = new JLabel("Niemand ist am Zug.", SwingConstants.CENTER);

    JLabel geldmitteanzeige;

    JButton naechsterspielerbutton = new JButton("Naechster Spieler");
    JButton grunstueckkaufenbutton = new JButton("Grundstueck kaufen");

    JButton spieler1button = new JButton();
    JButton spieler2button = new JButton();
    JButton spieler3button = new JButton();
    JButton spieler4button = new JButton();

    public  View(Controller co){
        c = co;
        main();
        f.repaint();
    }

    public void main()
    {

        
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setSize( 1500, 1000 );
        f.setResizable(true);
        f.setVisible( true );

        p1.setLayout(null);
        f.add(p1);

        
        spielfeld.setBounds(270, 60, 930,900);
        p1.add(spielfeld);

        geldmitte.setBounds(580, 300, 300,154);
        p1.add(geldmitte, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() +1));

        textanzeige.setBounds(350, 20,800, 35);
        textanzeige.setOpaque(true);
        textanzeige.setBackground(Color.ORANGE);
        p1.add(textanzeige);

        zuganzeige.setText(c.getaktuellerspieler() + " ist am Zug.");
        zuganzeige.setBounds(600, 940 ,300, 35);
        p1.add(zuganzeige);

        geldmitteanzeige = new JLabel(""+c.getgeldindermitte());
        geldmitteanzeige.setBounds(720, 330, 100,100);
        geldmitteanzeige.setFont(new Font("Serif", Font.BOLD, 50));
        p1.add(geldmitteanzeige, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() +2));

        w1.setBounds(1230, 90, 100,100);
        p1.add(w1);

        w2.setBounds(1350, 90, 100,100);
        p1.add(w2);

        wuerfelbutton.setText("Wuerfeln (" + c.getaktuellerspieler() + ")");
        wuerfelbutton.setBounds(1230, 220, 220,100);
        p1.add(wuerfelbutton);
        wuerfelbutton.addActionListener(this);

        naechsterspielerbutton.setBounds(1230, 340, 220,100);
        p1.add(naechsterspielerbutton);
        naechsterspielerbutton.addActionListener(this);

        grunstueckkaufenbutton.setBounds(1230, 820, 220,100);
        p1.add(grunstueckkaufenbutton);
        grunstueckkaufenbutton.addActionListener(this);

        
        
        spieler1button.setBounds(20, 95, 200,70);        
        p1.add(spieler1button);
        spieler1button.addActionListener(this);
    
        
    
        spieler2button.setBounds(20, 175, 200,70);
        p1.add(spieler2button);
        spieler2button.addActionListener(this);
    
    
     
        spieler3button.setBounds(20, 255, 200,70);
        p1.add(spieler3button);
        spieler3button.addActionListener(this);
    
    
     
        spieler4button.setBounds(20, 335, 200,70);
        p1.add(spieler4button);
        spieler4button.addActionListener(this);
    
    
        f.repaint();
    }

    public void actionPerformed (ActionEvent ae){

        if(ae.getSource() == this.wuerfelbutton){
            wuerfeln();
            anzeige();
        }

        if(ae.getSource() == this.naechsterspielerbutton){
            c.naechsterspieler();
            anzeige();
        }

        if(ae.getSource() == this.grunstueckkaufenbutton){
            String a = c.grundstueckkaufen();
            textanzeigebeschreiben(a);
            if(a != "Dieses Feld kann nicht gekauft werden"){

                anzeige();

                JLabel b = new JLabel("" + c.getfeldmieteaktuellerspieler(), SwingConstants.CENTER);
                b.setForeground(Color.white);
                b.setBounds(besitzanzeigex(c.getaktuellerspieler().position),
                    besitzanzeigey(c.getaktuellerspieler().position), besitzanzeigexw(c.getaktuellerspieler().position),
                    besitzanzeigeyw(c.getaktuellerspieler().position));

                b.setOpaque(true);

                if(c.getaktuellerspieler().farbe == 0){
                    b.setBackground(Color.BLUE);
                }
                if(c.getaktuellerspieler().farbe == 1){
                    b.setBackground(Color.RED);
                }
                if(c.getaktuellerspieler().farbe == 2){
                    b.setBackground(Color.GREEN);
                }
                if(c.getaktuellerspieler().farbe == 3){
                    b.setBackground(Color.PINK);
                }

    
                p1.add(b);
            }
        }

    }
    public void anzeige(){
        zuganzeigeaktualisieren();
        spielerbuttonsloeschen();
        wuerfelbutton.setText("Wuerfeln (" + c.getaktuellerspieler().name + ")");

        spieler1button.setText(c.getspieler1().name + ": " + c.getspieler1().geld);
        spieler2button.setText(c.getspieler2().name + ": " + c.getspieler2().geld);
        spieler3button.setText(c.getspieler3().name + ": " + c.getspieler3().geld);
        spieler4button.setText(c.getspieler4().name + ": " + c.getspieler4().geld); 
        spielerbuttonsfarbe() ;

        if(c.getgeldindermitte()>= 100){        
        geldmitteanzeige.setBounds(695, 330, 100,100);    
        }
        
         if(c.getgeldindermitte()< 100){        
        geldmitteanzeige.setBounds(720, 330, 100,100);    
        }
        
        f.repaint();
    }
    
    public void spielerbuttonsloeschen(){
         if(c.getspieler1().name == "Nicht belegt"){
             spieler1button.setVisible(false);
            }
            
            if(c.getspieler2().name == "Nicht belegt"){
             spieler2button.setVisible(false);
            }
            
            if(c.getspieler3().name == "Nicht belegt"){
             spieler3button.setVisible(false);
            }
            
            if(c.getspieler4().name == "Nicht belegt"){
             spieler4button.setVisible(false);
            }
            
            if(c.getspieler1().name != "Nicht belegt"){
             spieler1button.setVisible(true);
            }
            
            if(c.getspieler2().name != "Nicht belegt"){
             spieler2button.setVisible(true);
            }
            
            if(c.getspieler3().name != "Nicht belegt"){
             spieler3button.setVisible(true);
            }
            
            if(c.getspieler4().name != "Nicht belegt"){
             spieler4button.setVisible(true);
            }
        }

    public void spielerbuttonsfarbe(){
        if(c.getspieler1().farbe == 0){
            spieler1button.setBackground(Color.BLUE);
        }
        if(c.getspieler1().farbe == 1){
            spieler1button.setBackground(Color.RED);
        }
        if(c.getspieler1().farbe == 2){
            spieler1button.setBackground(Color.GREEN);
        }
        if(c.getspieler1().farbe == 3){
            spieler1button.setBackground(Color.PINK);
        }

        if(c.getspieler2().farbe == 0){
            spieler2button.setBackground(Color.BLUE);
        }
        if(c.getspieler2().farbe == 1){
            spieler2button.setBackground(Color.RED);
        }
        if(c.getspieler2().farbe == 2){
            spieler2button.setBackground(Color.GREEN);
        }
        if(c.getspieler2().farbe == 3){
            spieler2button.setBackground(Color.PINK);
        }

        if(c.getspieler3().farbe == 0){
            spieler3button.setBackground(Color.BLUE);
        }
        if(c.getspieler3().farbe == 1){
            spieler3button.setBackground(Color.RED);
        }
        if(c.getspieler3().farbe == 2){
            spieler3button.setBackground(Color.GREEN);
        }
        if(c.getspieler3().farbe == 3){
            spieler3button.setBackground(Color.PINK);
        }

        if(c.getspieler4().farbe == 0){
            spieler4button.setBackground(Color.BLUE);
        }
        if(c.getspieler4().farbe == 1){
            spieler4button.setBackground(Color.RED);
        }
        if(c.getspieler4().farbe == 2){
            spieler4button.setBackground(Color.GREEN);
        }
        if(c.getspieler4().farbe == 3){
            spieler4button.setBackground(Color.PINK);
        }   
    }

    public void spielerfahrzeugerstellen(int fz){
        if(fz==0){
            autofigur.setBounds(1120, 850, 50 ,50);
            p1.add(autofigur, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() +1));
        }
        if(fz==1){
            flugzeugfigur.setBounds(1120, 850, 50 ,50);
            p1.add(flugzeugfigur, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() +1));
        }
        if(fz==2){
            schifffigur.setBounds(1120, 850, 50 ,50);
            p1.add(schifffigur, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() +1));
        }
        if(fz==3){
            zugfigur.setBounds(1120, 850, 50 ,50);
            p1.add(zugfigur, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() +1));
        }

        anzeige();
    }
    public void wuerfeln(){
        int[] ergebnis =  c.wuerfeln(); 

        int a1 = ergebnis[0];
        int a2 = ergebnis[1];

        w1.setIcon(getWuerfelicon(a1));
        w2.setIcon(getWuerfelicon(a2));
        f.repaint();

        if(ergebnis[2]==1){
            int a = c.gefaengnis(a1,a2);

            if (a==2){
                Dialogfenster d = new Dialogfenster("Sie kommen aus dem Gefaengnis frei, bitte wuerfeln Sie nochmals");
            }

            if (a==1){
                Dialogfenster d = new Dialogfenster("Sie sitzen im Gefaengnis, bitte naechsten Spieler aufrufen");
            }

        }
        else{
            ziehenanimation(c.getaktuellerspieler().position, (a1+a2) +1 );   
            c.ziehen(a1+a2);
            f.repaint();
            if(ergebnis[3]==1){
                String a = c.ereigniskarte();
                if(a  == "Gehen Sie in das Gefaengnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht ueber Los. Ziehen Sie nicht 200 ein."){
                    spielergefaengnissetzen();
                    Gefaengnisfenster g = new Gefaengnisfenster(c);
                }
                Dialogfenster d = new Dialogfenster(a);
                textanzeigebeschreiben(c.getaktuellerspieler().name + ": Ereigniskarte");
                geldmitteanzeige.setText(""+c.getgeldindermitte());
            }

            if(ergebnis[3]==2){
                String a = c.gemeinschaftsskarte();
                if(a  == "Gehen Sie in das Gefaengnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht ueber Los. Ziehen Sie nicht 200 ein."){
                    spielergefaengnissetzen();
                    Gefaengnisfenster g = new Gefaengnisfenster(c);
                }
                Dialogfenster d = new Dialogfenster(a); 
                textanzeigebeschreiben(c.getaktuellerspieler().name + ": Gemeinschaftskarte");
                geldmitteanzeige.setText(""+c.getgeldindermitte());
            }

            if(ergebnis[3]==3){
                c.freiparken();
                textanzeigebeschreiben(c.getaktuellerspieler().name + " erhaelt das Geld aus der Mitte");
                geldmitteanzeige.setText(""+c.getgeldindermitte());
            }

            if(ergebnis[3]==4){
                c.gehensiegefaengnis();
                spielergefaengnissetzen();
                textanzeigebeschreiben(c.getaktuellerspieler().name + " muss in das Gefaengnis");
                Gefaengnisfenster g = new Gefaengnisfenster(c);
            }

            if(ergebnis[3]==5){
                int a = c.zahlfeld(); 
                textanzeigebeschreiben(c.getaktuellerspieler().name+" zahlt "+ a + " €.");
                geldmitteanzeige.setText(""+c.getgeldindermitte());

            }

            if(ergebnis[4] > 0){
                textanzeigebeschreiben(c.mietezahlen(ergebnis[4])); 

            }

        } 
anzeige();
        f.repaint();
    }

    
    public void wuerfelanimation (){
        w1.setIcon(i1);
        w2.setIcon(i1);
        f.repaint();

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        w1.setIcon(i5);
        w2.setIcon(i3);
        f.repaint();

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        w1.setIcon(i1);
        w2.setIcon(i6);
        f.repaint();

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        w1.setIcon(i2);
        w2.setIcon(i4);
        f.repaint();

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        w1.setIcon(i5);
        w2.setIcon(i3);
        f.repaint();

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        w1.setIcon(i1);
        w2.setIcon(i6);
        f.repaint();

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        w1.setIcon(i2);
        w2.setIcon(i4);
        f.repaint();

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        w1.setIcon(i5);
        w2.setIcon(i3);
        f.repaint();

        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public Icon getWuerfelicon(int i){

        switch(i){
            case 1: return i1;
            case 2: return i2;
            case 3: return i3;
            case 4: return i4;
            case 5: return i5;
            case 6: return i6;
            default: return null;

        }
    }

    public int figurx(int feldnr){        
        switch(feldnr){
            case 0:  return 1120; 
            case 1:  return 1025;
            case 2:  return 945 ;
            case 3:  return 865 ;
            case 4:  return 790;
            case 5:  return 710;
            case 6:  return 635;
            case 7:  return 560; 
            case 8:  return 480;
            case 9:  return 395;
            case 10: return 300;
            case 11: return 300;
            case 12: return 300;
            case 13: return 300; 
            case 14: return 300;
            case 15: return 300;
            case 16: return 300;
            case 17: return 300;
            case 18: return 300;
            case 19: return 300; 
            case 20: return 300;
            case 21: return 395;
            case 22: return 480;
            case 23: return 560;
            case 24: return 635;
            case 25: return 710; 
            case 26: return 790;
            case 27: return 865;
            case 28: return 945;
            case 29: return 1025;
            case 30: return 1120;
            case 31: return 1120; 
            case 32: return 1120;
            case 33: return 1120;
            case 34: return 1120;
            case 35: return 1120;
            case 36: return 1120;
            case 37: return 1120; 
            case 38: return 1120;
            case 39: return 1120;
            case 40:  return 1120;

            default: return 1120;
        }
    }

    public int figury(int feldnr){        
        switch(feldnr){
            case 0:  return 850; 
            case 1:  return 850;
            case 2:  return 850 ;
            case 3:  return 850 ;
            case 4:  return 850;
            case 5:  return 850;
            case 6:  return 850;
            case 7:  return 850; 
            case 8:  return 850;
            case 9:  return 850;
            case 10: return 850;
            case 11: return 765;
            case 12: return 695;
            case 13: return 630; 
            case 14: return 560;
            case 15: return 480;
            case 16: return 420;
            case 17: return 345;
            case 18: return 275;
            case 19: return 200; 
            case 20: return 110;
            case 21: return 110;
            case 22: return 110;
            case 23: return 110;
            case 24: return 110;
            case 25: return 110; 
            case 26: return 110;
            case 27: return 110;
            case 28: return 110;
            case 29: return 110;
            case 30: return 110;
            case 31: return 200; 
            case 32: return 275;
            case 33: return 345;
            case 34: return 420;
            case 35: return 480;
            case 36: return 560;
            case 37: return 630; 
            case 38: return 695;
            case 39: return 765;
            case 40:  return 765;

            default: return 765;
        }
    }

    public void ziehenanimation(int aktuellesfeld, int wuerfelergebnis){
        int b= 0;
        if(( aktuellesfeld + wuerfelergebnis)<=39){
            b = aktuellesfeld + wuerfelergebnis -1;
        }
        else{
            int a = 39-aktuellesfeld;
            b = wuerfelergebnis - a -2;

        }
        int fz = c.getaktuellerspieler().fahrzeug;

        if(fz==0){     
            autofigur.setBounds(figurx(b), figury(b),50,50); 
            f.repaint();
        }

        if(fz==1){     
            flugzeugfigur.setBounds(figurx(b), figury(b),50,50); 
            f.repaint();
        }

        if(fz==2){     
            schifffigur.setBounds(figurx(b), figury(b),50,50); 
            f.repaint();
        }

        if(fz==3){     
            zugfigur.setBounds(figurx(b), figury(b),50,50); 
            f.repaint();
        }

        
    }

    public void spielergefaengnissetzen(){
        int fz = c.getaktuellerspieler().fahrzeug;

        if(fz==0){     
            autofigur.setBounds(figurx(10), figury(10),50,50); 
            f.repaint();
        }

        if(fz==1){     
            flugzeugfigur.setBounds(figurx(10), figury(10),50,50); 
            f.repaint();
        }

        if(fz==2){     
            schifffigur.setBounds(figurx(10), figury(10),50,50); 
            f.repaint();
        }

        if(fz==3){     
            zugfigur.setBounds(figurx(10), figury(10),50,50); 
            f.repaint();
        }
    }

    public void textanzeigebeschreiben(String text){

        textanzeige.setText(text);   
    }

    public void zuganzeigeaktualisieren(){
        zuganzeige.setText(c.getaktuellerspieler().name + " ist am Zug.");   
    }

    public int besitzanzeigex(int feldnr){        
        switch(feldnr){

            case 1:  return 1015;

            case 3:  return  860;

            case 5:  return 706;
            case 6:  return 625;

            case 8:  return 465;
            case 9:  return 385;

            case 11: return 250;
            case 12: return 250;
            case 13: return 250; 
            case 14: return 250;
            case 15: return 250;
            case 16: return 250;

            case 18: return 250;
            case 19: return 250; 

            case 21: return 385;

            case 23: return 545;
            case 24: return 625;
            case 25: return 700; 
            case 26: return 780;
            case 27: return 860;
            case 28: return 935;
            case 29: return 1015;

            case 31: return 1200; 
            case 32: return 1200;

            case 34: return 1200;
            case 35: return 1200;

            case 37: return 1200; 

            case 39: return 1200;

            default: return 0;
        }
    }

    public int besitzanzeigey(int feldnr){        
        switch(feldnr){

            case 1:  return 925;

            case 3:  return  925;

            case 5:  return 925;
            case 6:  return 925;

            case 8:  return 925;
            case 9:  return 925;

            case 11: return 755;
            case 12: return 685;
            case 13: return 615; 
            case 14: return 545;
            case 15: return 475;
            case 16: return 405;

            case 18: return 265;
            case 19: return 195; 

            case 21: return 75;

            case 23: return 75;
            case 24: return 75;
            case 25: return 75; 
            case 26: return 75;
            case 27: return 75;
            case 28: return 75;
            case 29: return 75;

            case 31: return 195; 
            case 32: return 265;

            case 34: return 405;
            case 35: return 775;

            case 37: return 615; 

            case 39: return 755;

            default: return 0;
        }
    }

    public int besitzanzeigexw(int feldnr){        
        switch(feldnr){

            case 1:  return 65;

            case 3:  return  65;

            case 5:  return 65;
            case 6:  return 65;

            case 8:  return 65;
            case 9:  return 65;

            case 11: return 20;
            case 12: return 20;
            case 13: return 20; 
            case 14: return 20;
            case 15: return 20;
            case 16: return 20;

            case 18: return 20;
            case 19: return 20; 

            case 21: return 65;

            case 23: return 65;
            case 24: return 65;
            case 25: return 65; 
            case 26: return 65;
            case 27: return 65;
            case 28: return 65;
            case 29: return 65;

            case 31: return 20; 
            case 32: return 20;

            case 34: return 20;
            case 35: return 20;

            case 37: return 20; 

            case 39: return 20;

            default: return 0;
        }
    }

    public int besitzanzeigeyw(int feldnr){        
        switch(feldnr){

            case 1:  return 20;

            case 3:  return  20;

            case 5:  return 20;
            case 6:  return 20;

            case 8:  return 20;
            case 9:  return 20;

            case 11: return 65;
            case 12: return 65;
            case 13: return 65; 
            case 14: return 65;
            case 15: return 65;
            case 16: return 65;

            case 18: return 65;
            case 19: return 65; 

            case 21: return 20;

            case 23: return 20;
            case 24: return 20;
            case 25: return 20; 
            case 26: return 20;
            case 27: return 20;
            case 28: return 20;
            case 29: return 20;

            case 31: return 65; 
            case 32: return 65;

            case 34: return 65;
            case 35: return 65;

            case 37: return 65; 

            case 39: return 65;

            default: return 0;
        }
    }

}