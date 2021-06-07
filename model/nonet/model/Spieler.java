package model;
public class Spieler
{
    int kontostand = 0;

    int anzahlHaeuser[]; // index ist gruppe
    boolean besitztFeld[] = new boolean[40];
    private int anzahlBahnhoefe = 0;
    private int anzahlVWs = 0; // Versorgungswerke
    String name;
    int nummer;

    int feld = 0; // aktuelles Feld
    boolean imGefaengnis = false;
    boolean ausgeschieden = false;
    int gefaengnisRunden = 0;
    int losBetrag;
    Karte karte = null;

    int[] farbe;
    int figur;

    public Spieler(int nr ,String n, int gruppen, int lb, int[] fa, int fi){
        nummer = nr;
        name = n;
        anzahlHaeuser = new int[gruppen];
        for (int i = 0; i < gruppen; i++) {
            anzahlHaeuser[i] = 0;
        } 
        losBetrag = lb;
        farbe = fa;
        figur = fi;
    }

    public int[] ziehen(Feld[] felder){
        /* Ergebnisse: 
         * 0: nichts
         * 1: Pasch (kein Gefängnis)
         * 2: Aussetzen im Gefängnis
         * 3: DAS REICHT DU FLIEGST RAUS
         * 4: Gefängnis verlassen wegen Pasch
         */
        int[] ergebnis = new int[5];
        ergebnis[0] = wuerfeln();
        ergebnis[1] = wuerfeln();
        ergebnis[2] = 0; // nix
        ergebnis[3] = 0; // Karte Ziehen?
        ergebnis[4] = 0; // Miete
        if (!ausgeschieden){
            if (!imGefaengnis){ // normal
                gehen(ergebnis[0] + ergebnis[1]);
                if (istPasch(ergebnis[0], ergebnis[1])){
                    ergebnis[2] = 1; // Pasch (kein Gefängnis)
                }
                ergebnis[3] = felder[feld].getAktion();
                ergebnis[4] = felder[feld].mieteBerechnen(ergebnis[0] + ergebnis[1]);
            } else { // im Gefängnise
                if(istPasch(ergebnis[0], ergebnis[1])){ // Pasch
                    imGefaengnis = false;
                    gefaengnisRunden = 0;
                    gehen(ergebnis[0] + ergebnis[1]);
                    ergebnis[2] = 4; // Pasch
                } else if (gefaengnisRunden == 2) { // 3 Runden ohne Pasch
                    imGefaengnis = false;
                    gefaengnisRunden = 0;
                    gehen(ergebnis[0] + ergebnis[1]);
                    abheben(50); // 50 Kaution
                    ergebnis[2] = 3; // DAS REICHT DU FLIEGST RAUS
                } else { // kein Pasch
                    gefaengnisRunden++; 
                    ergebnis[2] = 2; // Gefängnis, kein Pasch
                }
            }
        } else { 
            ergebnis[0] = 0;
            ergebnis[1] = 0;
        }
        return ergebnis;
    }

    public void feldBesitzen(int feld){
        besitztFeld[feld] = true;
    }

    public void feldAbgeben(int feld){
        besitztFeld[feld] = false;
    }

    public boolean[] getFelder(){
        return besitztFeld;
    }

    public int getNummer(){
        return nummer;     
    }

    public void gehen(int felder){
        feld = feld + felder;
        if (feld >= 40){ // über Los laufen
            feld = feld - 40;
            einzahlen(losBetrag);
        }  else if (felder < 0){ // rückwärts über Los laufen
            feld = feld + 40;
            // einzahlen(losBetrag);
        }
    }

    public void ausscheiden(){
        ausgeschieden = true;
    }

    public boolean istAusgeschieden(){
        return ausgeschieden;
    }

    public void insGefaengnisGehen(){
        imGefaengnis = true;
        springen(10);
    }

    public Karte gefaengnisfreikarteSetzen(){
        Karte k = karte;
        if (karte != null){
            if (karte.getType() == 4 && imGefaengnis){
                imGefaengnis = false;
                gefaengnisRunden = 0;
                karte = null;
            }
        }
        return k;
    }

    public void springen(int wohin){
        feld = wohin;
    }

    public int getFeld(){
        return feld;
    }

    public String getName(){
        return name;
    }

    public int getAnzahlHaeuser(int gruppe){
        return anzahlHaeuser[gruppe];
    }

    public int getAnzahlBahnhoefe(){
        return anzahlBahnhoefe;   
    }

    public int getAnzahlVWs(){
        return anzahlVWs;
    }

    public boolean genugGeld(int preis){
        return preis <= kontostand;
    }

    public void abheben(int menge){ // vorher genugGeld() prüfen!
        kontostand = kontostand - menge;
    }

    public void einzahlen(int menge){
        kontostand = kontostand + menge;
    }

    public int getKontostand(){
        return kontostand;
    }

    public void strasseHinzufuegen(int gruppe){
        anzahlHaeuser[gruppe]++;
    }

    public void VWhinzufuegen(){
        anzahlVWs++;
    }

    public void bahnhofHinzufuegen(){
        anzahlBahnhoefe++;
    }

    private boolean istPasch(int zahl1, int zahl2){
        return zahl1 == zahl2;
    }

    private int wuerfeln(){
        return (int)(Math.random()*6)+1;
    }

    public void setKarte(Karte k){
        karte = k;
    }

    public Karte getKarte(){
        return karte;
    }
}
