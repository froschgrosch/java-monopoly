
import java.util.Random;

public class Model
{
    public int losgeld;
    public int spielerzahl;

    public  Spieler aktuellerspieler;

    public Spieler spieler1;
    public Spieler spieler2;
    public Spieler spieler3;
    public Spieler spieler4;
    public Feld[] feld = new Feld[40];

    public int geldindermitte;

    public String spieler1name;

    public Model()
    {
        losgeld = 200;
        spielerzahl = 0;

        geldindermitte = 0;

        spieler1 = new Spieler("Nicht belegt", 0, 0);
        spieler2 = new Spieler("Nicht belegt", 0, 0);
        spieler3 = new Spieler("Nicht belegt", 0, 0);
        spieler4 = new Spieler("Nicht belegt", 0, 0);

        aktuellerspieler = spieler1;

        feld[0]  = new Feld(0, 0, 0);
        feld[1]  = new Feld(1, 2, 60);
        feld[2]  = new Feld(5, 0, 0);
        feld[3]  = new Feld(1, 4, 60);
        feld[4]  = new Feld(10, 0, 0);
        feld[5]  = new Feld(2, 0, 200);
        feld[6]  = new Feld(1, 6, 100);
        feld[7]  = new Feld(4, 0, 0);
        feld[8]  = new Feld(1, 6, 100);
        feld[9]  = new Feld(1, 8, 120);
        feld[10] = new Feld(7, 0, 0);
        feld[11] = new Feld(1, 10, 140);
        feld[12] = new Feld(3, 0, 150);
        feld[13] = new Feld(1, 10, 140);
        feld[14] = new Feld(1, 12, 160);
        feld[15] = new Feld(2, 0, 200);
        feld[16] = new Feld(1, 14, 180);
        feld[17] = new Feld(5, 0, 0);
        feld[18] = new Feld(1, 14, 180);
        feld[19] = new Feld(1, 16, 200);
        feld[20] = new Feld(9, 0, 0);
        feld[21] = new Feld(1, 18, 220);
        feld[22] = new Feld(4, 0, 0);
        feld[23] = new Feld(1, 18, 220);
        feld[24] = new Feld(1, 20, 240);
        feld[25] = new Feld(2, 0, 200);
        feld[26] = new Feld(1, 22, 260);
        feld[27] = new Feld(1, 22, 260);
        feld[28] = new Feld(3, 0, 150);
        feld[29] = new Feld(1, 24, 280);
        feld[30] = new Feld(6, 0, 0);
        feld[31] = new Feld(1, 26, 300);
        feld[32] = new Feld(1, 26, 200);
        feld[33] = new Feld(5, 0, 0);
        feld[34] = new Feld(1, 28, 320);
        feld[35] = new Feld(2, 0, 200);
        feld[36] = new Feld(4, 0, 0);
        feld[37] = new Feld(1, 25, 350);
        feld[38] = new Feld(10, 0, 0);
        feld[39] = new Feld(1, 50, 400);
    }

    public void spielererstellen(String n, int fb, int fz){
        if (spielerzahl == 0){

            spieler1 = new Spieler(n, fb, fz);           
            spielerzahl++;
            aktuellerspieler = spieler1;
        }

        else if(spielerzahl == 1){

            spieler2 = new Spieler(n, fb, fz);           
            spielerzahl++;

        }

        else if(spielerzahl == 2){

            spieler3 = new Spieler(n, fb, fz);           
            spielerzahl++;

        }

        else if(spielerzahl == 3){

            spieler4 = new Spieler(n, fb, fz);           
            spielerzahl++;

        }

        else if(spielerzahl == 4){

            System.out.println("Maximale Spielerzahl erreicht, bitte Spiel starten!");

        }

    }
    public int[] wuerfeln(){
        int[] ergebnis = new int[5];
        Random wuerfel = new Random();
        ergebnis[0] = 1 + wuerfel.nextInt(6);
        ergebnis[1] = 1 + wuerfel.nextInt(6);

        int b= 0;
        if(( aktuellerspieler.position + (ergebnis[0]+ergebnis[1]))<=39){
            b = aktuellerspieler.position + (ergebnis[0]+ergebnis[1]);

        }
        else{
            int a = 39-aktuellerspieler.position;
            b = (ergebnis[0]+ergebnis[1]) - a -1;

        }

        System.out.println(b);

        if(aktuellerspieler.gefaengnis){
            ergebnis[2]=1;
        }
        else{ ergebnis[2]= 0;}

        if(feld[b].typ == 4){
            ergebnis[3] = 1;
        }
        else if(feld[b].typ == 5){
            ergebnis[3] = 2;
        }
        else if(feld[b].typ == 9){
            ergebnis[3] = 3;
        }
        else if(feld[b].typ == 6){
            ergebnis[3] = 4;
        }   
        else if(feld[b].typ == 10){
            ergebnis[3] = 5;
        }   
        else{ergebnis[3] = 0;}

        if(feld[b].miete > 0){
            ergebnis[4] = feld[b].miete;
        }   
        else if(feld[b].miete > 0 
        && feld[b].typ == 2){
            ergebnis[4] = bahnhof(b);
        }     
        else if(feld[b].miete > 0 
        && feld[b].typ == 2){
            ergebnis[4] = werk(b, (ergebnis[0]+ergebnis[1]));
        }   
        else{ergebnis[4] = 0;}
        return ergebnis;

    }
    public void ziehen(int anzahl){
        if(( aktuellerspieler.position + anzahl)<=39){
            aktuellerspieler.position = aktuellerspieler.position + anzahl;
        }
        else{
            int a = 39-aktuellerspieler.position;
            aktuellerspieler.position = anzahl - a;
            aktuellerspieler.geld = aktuellerspieler.geld + 200;
        }
    }

    public void feldkaufen(){
        if(feld[aktuellerspieler.getposition()].getbesitzer() == null){
        int feldnr = aktuellerspieler.getposition();
        aktuellerspieler.setgeld(aktuellerspieler.getgeld()-feld[feldnr].getpreis()); 
        feld[feldnr].setbesitzer(aktuellerspieler);
    }
    }

    public int bahnhof(int b){
        int anz = 0; 
        if(feld[b].besitzer == feld[5].besitzer){
            anz++;
        }

        if(feld[b].besitzer == feld[15].besitzer){
            anz++;
        }

        if(feld[b].besitzer == feld[25].besitzer){
            anz++;
        }

        if(feld[b].besitzer == feld[45].besitzer){
            anz++;
        }

        switch(anz){
            case 0: return 0;
            case 1: return 25;
            case 2: return 50;
            case 3: return 100;
            case 4: return 200;
            default: return 0;

        }

    }

    public int werk(int b, int z){
        int anz = 0; 
        if(feld[b].besitzer == feld[12].besitzer){
            anz++;
        }

        if(feld[b].besitzer == feld[28].besitzer){
            anz++;
        }

        switch(anz){
            case 0: return 0;
            case 1: return z*4;
            case 2: return z*10;    
            default: return 0;

        }

    }

    public void gehensiegefaengnis(){
        aktuellerspieler.gefaengnis = true;
        aktuellerspieler.rundenimgefaengnis = 0;

        aktuellerspieler.position = 10;
    }

    public int gefaengnis(int w1, int w2){

        if(!aktuellerspieler.gefaengnis){
            return 0;}

        if(w1 == w2){
            aktuellerspieler.gefaengnis = false;
            return 2;
        }

        if(aktuellerspieler.rundenimgefaengnis == 3){
            aktuellerspieler.gefaengnis = false;
            return 2;}

        else{
            aktuellerspieler.rundenimgefaengnis++;
            return 1;
        }

    }

    public String ereigniskarte(){
        Random generator = new Random();
        int i = 1 + generator.nextInt(6);

        switch(i){

            case 1: 
            gehensiegefaengnis();
            return("Gehen Sie in das Gefaengnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht ueber Los. Ziehen Sie nicht 200 ein.");

            case 2: 
            aktuellerspieler.gefaengniskarte = true;
            return("Sie kommen aus dem Gefaengnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen.");

            case 3: 
            aktuellerspieler.geld = aktuellerspieler.geld + 200;
            return("Ihr Bausparvertrag wird faellig. Sie erhalten 200.");

            case 4: 
            aktuellerspieler.geld = aktuellerspieler.geld + 50;
            return("Die Bank zahlt Ihnen eine Dividende von 50.");

            case 5:
            aktuellerspieler.geld = aktuellerspieler.geld - 15;
            geldindermitte = geldindermitte + 15;
            return("Strafzettel! Zahlen Sie 15.");

            
            

            default: return null;

        }

    }

    public String gemeinschaftskarte(){
        Random generator = new Random();
        int i = 1 + generator.nextInt(6);

        switch(i){

            case 1: 
            gehensiegefaengnis();
            return("Gehen Sie in das Gefaengnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht ueber Los. Ziehen Sie nicht 200 ein.");

            case 2: 
            aktuellerspieler.gefaengniskarte = true;
            return("Sie kommen aus dem Gefaengnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen.");

            case 3: 
            aktuellerspieler.position = 0;
            return("Ruecken Sie vor bis auf Los. (Ziehe 200 ein).");

            case 4: 
            aktuellerspieler.geld = aktuellerspieler.geld - 100;
            geldindermitte = geldindermitte + 100;
            return("Krankenhausgebuehren. Zahlen Sie 100.");

            case 5:
            aktuellerspieler.geld = aktuellerspieler.geld + 10;
            return("Zweiter Preis im Schönheitswettbewerb. Sie erhalten 10.");

            case 6:
            aktuellerspieler.geld = aktuellerspieler.geld + 50;
            return("Aus Lagerverkaeufen erhalten Sie 50");

            default: return null;

        }

    }

    public void freiparken(){
        aktuellerspieler.geld = aktuellerspieler.geld + geldindermitte;
        geldindermitte = 0;       
    }

    public int zahlfeld(){
        int a = 200;

        if(aktuellerspieler.position == 38){
            a = 100;

        }

        aktuellerspieler.geld = aktuellerspieler.geld - a;
        geldindermitte = geldindermitte + a;
        return a;
    }

    public String mietezahlen(int m){
        if(feld[aktuellerspieler.position].inbesitz == 1){
            if(feld[aktuellerspieler.position].getbesitzer() != aktuellerspieler){
            
            
            aktuellerspieler.geld = aktuellerspieler.geld - m;
            feld[aktuellerspieler.position].besitzer.geld = feld[aktuellerspieler.position].besitzer.geld + m;

            return aktuellerspieler.name + " zahlt " + m + "€ Miete an " + feld[aktuellerspieler.position].besitzer.name;
        }
        else{ return "Dieses Feld gehört dem Spieler selbst";
        }
        }
        else{return "Dieses Feld gehört niemandem";}
    }

    public void gefaengniskarte(){
        if(aktuellerspieler.gefaengniskarte){
            aktuellerspieler.gefaengniskarte = false;
            aktuellerspieler.gefaengnis = false;
        }
        else{
            Dialogfenster d =  new Dialogfenster("Sie besitzen keine Gefaengniskarte");

        }
    }

    public void freikaufen(){
        aktuellerspieler.geld = aktuellerspieler.geld - 200;
        aktuellerspieler.gefaengnis = false;
    }

    public void naechsterspieler(){
        if(spielerzahl==1){
            if(aktuellerspieler == spieler1){
                aktuellerspieler = spieler1;
            }
        }

        else if(spielerzahl==2){
            if(aktuellerspieler == spieler1){
                aktuellerspieler = spieler2;
            }
            else if(aktuellerspieler == spieler2){
                aktuellerspieler = spieler1;
            }   
        }

        else if(spielerzahl==3){
            if(aktuellerspieler == spieler1){
                aktuellerspieler = spieler2;
            }
            else if(aktuellerspieler == spieler2){
                aktuellerspieler = spieler3;
            }
            else if(aktuellerspieler == spieler3){
                aktuellerspieler = spieler1;
            }  
        }

        else if(spielerzahl==4){
            if(aktuellerspieler == spieler1){
                aktuellerspieler = spieler2;
            }else if(aktuellerspieler == spieler2){
                aktuellerspieler = spieler3;
            }
            else if(aktuellerspieler == spieler3){
                aktuellerspieler = spieler4;
            }  
            else if(aktuellerspieler == spieler4){
                aktuellerspieler = spieler1;
            } 
        }

    }

    public String grundstueckkaufen(){
        if(feld[aktuellerspieler.position].typ == 1 || feld[aktuellerspieler.position].typ == 2
        || feld[aktuellerspieler.position].typ == 3){

            feld[aktuellerspieler.position].besitzer = aktuellerspieler;
            feld[aktuellerspieler.position].inbesitz = 1;
            aktuellerspieler.geld = aktuellerspieler.geld - feld[aktuellerspieler.position].preis;
            return aktuellerspieler.name + " kauft ein Grundstueck fuer " + feld[aktuellerspieler.position].preis + "M";
        }
        else{ return "Dieses Feld kann nicht gekauft werden";}
    }

    public Spieler getaktuellerspieler(){
        return aktuellerspieler;
    }

    public void setaktuellerspieler(Spieler sp){
        aktuellerspieler = sp;
    }

    public int getgeldindermitte(){
        return geldindermitte;
    }

    public int getfeldmieteaktuellerspieler(){

        return feld[aktuellerspieler.position].miete;
    }

    public Spieler getspieler1(){
        return spieler1;
    }

    public Spieler getspieler2(){
        return spieler2;
    }

    public Spieler getspieler3(){
        return spieler3;
    }

    public Spieler getspieler4(){
        return spieler4;
    }

}
