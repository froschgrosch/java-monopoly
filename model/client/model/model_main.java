package model;
import IO.Reader;
import IO.Writer;
import java.util.Random; 

public class model_main
{
    Feld[] felder = new Feld[40];
    Spieler[] spieler = new Spieler[4];
    Gruppe[] gruppen;

    Karte ek[]; // Ereigniskarten
    Karte gk[]; // Gemeinschaftskarten
    int aktuelleEK = 0;
    int aktuelleGK = 0;

    int spielerRegistriert = 0;
    int aktuellerSpieler = 0;
    int losBetrag;
    int startGuthaben;
    int geldInderMitte = 0;

    Reader r = new Reader();
    public model_main(int lb, int sg) //LosBetrag, StartGuthaben, Gruppen
    {
        losBetrag = lb;
        startGuthaben = sg;

        gruppen = gruppenEinlesen("data\\Gruppen.txt");     // VOR Felder Einlesen   
        felder = felderEinlesen("data\\Felder.txt");
        gk = kartenEinlesen("data\\Gemeinschaft.txt");
        gk = mischen(gk);
        ek = kartenEinlesen("data\\Ereignis.txt");
        ek = mischen(ek);
    }

    public void spielStarten(){
        for (int i = 0; i < spielerRegistriert; i++){
            spieler[i].einzahlen(startGuthaben);  
        }
        aktuellerSpieler = (int)(Math.random()*spielerRegistriert-1);
    }

    public Spieler spielerErstellen(String n, int[] farbe, int figur){
        Spieler s = null;
        if (spielerRegistriert <= 4) {
            spieler[spielerRegistriert] = new Spieler(spielerRegistriert, n, gruppen.length, losBetrag, farbe, figur);
            s = spieler[spielerRegistriert];
            spielerRegistriert++;
        }
        return s;
    }

    public int[] mieteZahlen(int gewuerfelt){
        int[] ergebnis = new int[3];
        int aktuellerSpielerFeld = spieler[aktuellerSpieler].getFeld();
        /* 0 : Miete
         * 1 : zahlender Spieler
         * 2 : Besitzer */
        ergebnis[0] = felder[aktuellerSpielerFeld].mieteBerechnen(gewuerfelt);
        ergebnis[1] = aktuellerSpieler;
        ergebnis[2] = -1; // Feld ohne Besitzer
        if (felder[aktuellerSpielerFeld].getBesitzer() != null){
            ergebnis[2] = felder[aktuellerSpielerFeld].getBesitzer().getNummer();
        }
        return ergebnis;
    }

    public String gemeinschaftsKarteAusfuehren(){
        Karte k = gk[aktuelleGK];
        aktuelleGK++;
        if(aktuelleGK >= gk.length){
            aktuelleGK = 0;
        }
        handleKarte(k);
        return k.getText();
    }

    public String ereignisKarteAusfuehren(){
        Karte k = ek[aktuelleEK];
        aktuelleEK++;
        if(aktuelleEK >= ek.length){
            aktuelleEK = 0;
        }
        handleKarte(k);
        return k.getText();
    }

    public String karteAusfuehren(){
        String s;
        switch(felder[spieler[aktuellerSpieler].getFeld()].getAktion()) {
            case 1:
            s = gemeinschaftsKarteAusfuehren();
            break;
            case 2:
            s = ereignisKarteAusfuehren();
            break;
            default:
            s = "";
            break;
        }
        return s;
    }

    public int feldKaufen(){
        int ergebnis = 0;
        /* 0: Erfolg
         * 1: Feld bereits verkauft
         * 2: Feld unverkäuflich 
         * 3: Zu wenig Geld */
        int feld = spieler[aktuellerSpieler].getFeld();
        Spieler s = spieler[aktuellerSpieler];
        if (spieler[aktuellerSpieler].genugGeld(felder[feld].preiseAusgeben()[0])){
            s = felder[feld].kaufen(spieler[aktuellerSpieler]);
            if (s != null){
                spieler[aktuellerSpieler].feldBesitzen(feld);
                spieler[aktuellerSpieler] = s;
                ergebnis = 0; 
                boolean[] besitzt = spieler[aktuellerSpieler].getFelder();
                for (int i = 0; i < besitzt.length; i++){ // Aktualisieren der restlichen Felder (für Berechnung der Miete)
                    if (besitzt[i]){
                        felder[i].setBesitzer(spieler[aktuellerSpieler]);
                    }
                }
            } else {
                if(felder[feld].getTyp() == 0){
                    ergebnis = 2;
                } else {
                    ergebnis = 1;
                }
            }
        } else {
            ergebnis = 3;
        }
        return ergebnis;
    }

    public void tauschen(int Spieler1, int feld1, int feld2){
        Feld f1 = felder[feld1]; // Das angebotene Feld
        Spieler s1 = spieler[Spieler1]; // Der Spieler der tauschen will
        Feld f2 = felder[feld2]; // Das zu ertauschende Feld
        Spieler s2 = f2.getBesitzer(); // Der Spieler dem das Feld gehört
        int diff = f2.preiseAusgeben()[0] - f1.preiseAusgeben()[0];

        s1.abheben(diff);
        s1.feldBesitzen(feld2);
        s1.feldAbgeben(feld1);

        s2.einzahlen(diff);
        s2.feldBesitzen(feld1);
        s2.feldAbgeben(feld2);

        boolean[] besitzt1 = s1.getFelder();
        boolean[] besitzt2 = s2.getFelder();
        for (int i = 0; i < felder.length; i++){ // Aktualisieren der restlichen Felder (für Berechnung der Miete)
            if (besitzt1[i]){
                felder[i].setBesitzer(s1);
            }
            if (besitzt2[i]){
                felder[i].setBesitzer(s2);
            }
        }

        spieler[Spieler1] = s1;
        spieler[s2.getNummer()] = s2;
    }

    public void verkaufen(int f, int _nb, int preis){
        Feld feld = felder[f];
        Spieler ab = feld.getBesitzer();
        Spieler nb = spieler[_nb];

        nb.abheben(preis);
        ab.einzahlen(preis);

        ab.feldAbgeben(f);
        nb.feldBesitzen(f);

        boolean[] besitztab = ab.getFelder();
        boolean[] besitztnb = nb.getFelder();
        for (int i = 0; i < felder.length; i++){ // Aktualisieren der restlichen Felder (für Berechnung der Miete)
            if (besitztab[i]){
                felder[i].setBesitzer(ab);
            }
            if (besitztnb[i]){
                felder[i].setBesitzer(nb);
            }
        }

        spieler[_nb] = nb;
        spieler[ab.getNummer()] = ab;
    }

    public int getGeldInderMitte(){
        return geldInderMitte;
    }

    public void ausscheiden(int nummer){
        spieler[nummer].ausscheiden();
    }

    public void freiParken(){
        spieler[aktuellerSpieler].einzahlen(geldInderMitte);
        geldInderMitte = 0;
    }

    public boolean gefaengnisfreikarteSetzen(){
        return spieler[aktuellerSpieler].gefaengnisfreikarteSetzen() != null;
    }

    public Spieler[] getSpieler(){ // 0 bis 3
        return spieler;
    }

    public Feld[] getFelder(){
        return felder;
    }

    public Gruppe[] getGruppen(){
        return gruppen;
    }

    public int getFeldSpieler(int nummer){
        return spieler[nummer].getFeld();
    }

    public int getKontostandSpieler(int nummer){
        return spieler[nummer].getKontostand();
    }

    public int getAktuellerSpieler(){
        return aktuellerSpieler;
    }

    public Feld getFeld(int nummer){ 
        Feld output = null;
        if (nummer < felder.length){
            output = felder[nummer];
        }
        return output;
    }

    public int[] wuerfeln(){ // Wenn ergebnis[2] == 1 muss der Controller nochmal würfeln
        return  spieler[aktuellerSpieler].ziehen(felder);
    }

    public int zugBeenden(){
        if (aktuellerSpieler == spielerRegistriert - 1){ // spieler 3 --> spieler 0
            aktuellerSpieler = 0;
        } else {
            aktuellerSpieler++;
        }
        return aktuellerSpieler;   
    }

    private void handleKarte(Karte k){
        if (k != null){
            int[] p = k.getParams();
            int feld = spieler[aktuellerSpieler].getFeld();
            switch(k.getType()){
                case 0: // Spieler erhält Geld
                spieler[aktuellerSpieler].einzahlen(p[0]);
                case 1: // Alle Spieler zahlen akt. Spieler 
                int tempindex = 0;
                for (int i = 0; i < spielerRegistriert; i++){
                    spieler[i].abheben(p[0]);
                    tempindex++;
                }
                spieler[aktuellerSpieler].einzahlen(p[0]*tempindex);
                break;
                case 2: // ins Gefängnis gehen
                spieler[aktuellerSpieler].insGefaengnisGehen();
                break;
                case 3: // renovieren
                boolean[] besitzt = spieler[aktuellerSpieler].getFelder();
                int anzahlHaeuser = 0;
                int anzahlHotels = 0;
                for (int i = 0; i < felder.length; i++){
                    if (besitzt[i]){
                        anzahlHaeuser = anzahlHaeuser + felder[i].getAnzahlHaeuser();
                        anzahlHotels = anzahlHotels + felder[i].getAnzahlHotels();
                    }
                }
                spieler[aktuellerSpieler].abheben(anzahlHaeuser * p[0] + anzahlHotels * p[1]);
                break;
                case 4: // Gefaengnisfreikarte
                spieler[aktuellerSpieler].setKarte(k);
                break;
                case 5: // Gehe p[0] Felder
                spieler[aktuellerSpieler].gehen(p[0]);
                break;
                case 6: // Rücke vor bis p[0]
                if (p[0] >= feld){ // Feld liegt zwischen Spieler und Los
                    spieler[aktuellerSpieler].springen(p[0]);
                } else { // Spieler muss über Los gehen
                    spieler[aktuellerSpieler].gehen(felder.length - (feld - p[0]));
                }
                break;
                case 7:  // Rücke vor bis zum nächsten Feld von Typ x
                int f = feld;
                boolean gefunden = false;
                for (int i = feld; i < feld + felder.length ; i++){
                    if (i < 40){
                        if (felder[i].getTyp() == p[0] && !gefunden && i != feld){
                            f = i;
                            gefunden = true;
                        }
                    } else {
                        if (felder[i-felder.length].getTyp() == p[0] && !gefunden){
                            f = i-felder.length;
                            gefunden = true;
                        }
                    }
                }
                handleKarte(new Karte(6,f,"",0)); // rekursiver Aufruf
                break;
            }
        }
    }

    private Karte[] mischen(Karte[] k){
        boolean[] schonbelegt = new boolean[k.length];
        Karte[] output = new Karte[k.length];
        int i = 0;
        int random;
        while(i > k.length){
            random = (int)(Math.random() * (k.length));
            if(!schonbelegt[random]){
                output[random] = k[i];
                i++;
            }
        }
        return output;
    }

    private Karte[] kartenEinlesen(String dateiname){
        String[] t = r.read(dateiname);
        Karte[] k = new Karte[t.length-1];
        for (int i = 0; i < k.length; i++){
            if (t[i] != ""){
                String[] split = t[i].split(":");
                int split1 = Integer.valueOf(split[0]); // Typ
                int split2 = Integer.valueOf(split[1]); // Parameter 1
                int split3 = Integer.valueOf(split[2]); // Parameter 2
                String text = split[3]; // Text

                switch(split1){
                    case 3: // renovieren
                    k[i] = new Karte(3,split2,split3,text,i);
                    break;
                    case 2: // Gefängniskarte
                    k[i] = new Karte(2,text,i);
                    break;
                    case 4: // Gefängnisfreikarte
                    k[i] = new Karte(4,text,i);
                    break;
                    default:
                    if (split1 <= 7){
                        k[i] = new Karte(split1,split2,text,i);
                    } else {
                        k[i] = null;
                    }
                    break;
                }
            } else {
                k[i] = null;
            }
        }
        return k;
    }

    private Gruppe[] gruppenEinlesen(String dateiname){
        String[] t = r.read(dateiname);
        Gruppe[] g = new Gruppe[t.length-1];
        for (int i = 0; i < g.length; i++){
            if (t[i] != null){
                String[] split = t[i].split(":");

                int nummer = Integer.valueOf(split[0]); 
                int anzHaeuser = Integer.valueOf(split[1]);

                int[] rgb = new int[3];
                String farbe = split[2];
                rgb[0] = Integer.valueOf(split[3]);
                rgb[1] = Integer.valueOf(split[4]); 
                rgb[2] = Integer.valueOf(split[5]); 

                g[i] = new Gruppe(nummer,anzHaeuser,farbe,rgb);
            } else {
                g[i] = null;
            }
        }
        return g;
    }

    private Feld[] felderEinlesen(String dateiname){
        String[] t = r.read(dateiname);
        Feld[] f = new Feld[t.length-1];
        for (int i = 0; i < f.length; i++){
            if (t[i] != null){
                String[] split = t[i].split(":");
                int[] p;
                switch (Integer.valueOf(split[0])){
                    case 0: // Feld
                    p = new int[1];
                    p[0] = Integer.valueOf(split[1]);
                    f[i] = new Feld(p,split[3]);
                    f[i].setAktion(Integer.valueOf(split[2]));
                    break;

                    case 1: // Straße
                    p = new int[10];
                    for (int j = 0; j < 10; j++){
                        p[j] = Integer.valueOf(split[j+1]);
                    }
                    f[i] = new Strasse(p,gruppen[Integer.valueOf(split[12])],split[11]);
                    break;

                    case 2: // Bahnhof
                    p = new int[4];
                    for (int j = 0; j < 4; j++){
                        p[j] = Integer.valueOf(split[j+1]);
                    }
                    f[i] = new Bahnhof(p,split[5],Integer.valueOf(split[6]));
                    break;

                    case 3: // Versorgungswerk
                    p = new int[3];
                    for (int j = 0; j < 3; j++){
                        p[j] = Integer.valueOf(split[j+1]);
                    }
                    f[i] = new Versorgungswerk(p,split[4]);
                    break;

                    default:
                    f[i] = null;
                    break;
                }
            } else {
                t[i] = null;
            }
        }
        return f;
    }
}
