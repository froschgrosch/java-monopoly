package model;
public class Versorgungswerk extends Feld
{
    int preise[]; // Größe 3
    /* 0: Kaufpreis
     * 1: Hypothekenwert
     * 2: Hypothek auflösen */
    String name;
    Spieler besitzer = null;
    boolean hypothek = false;
    public Versorgungswerk(int[] p, String n){
        super(p,n);
        name = n;
        preise = p;
    }

    public int[] preiseAusgeben(){
        return preise;
    }

    public String getName(){
        return name;
    }

    public void setBesitzer(Spieler s){
        besitzer = s;
    }

    public Spieler getBesitzer(){
        return besitzer;   
    }

    public int getAktion(){
        return 0;
    }

    public int getTyp(){
        return 3;
    }

    public int mieteBerechnen(int gewuerfelt){
        int miete = 0;
        if(besitzer != null){
            if(besitzer.getAnzahlVWs() == 1) {
                miete = gewuerfelt * 4;
            } else if(besitzer.getAnzahlVWs() == 2){
                miete = gewuerfelt * 10;
            }
        }
        return miete;
    }

    public Spieler kaufen(Spieler s){ // nur nach s.genugGeld(preise[0]) aufrufen!
        if (besitzer == null){
            s.abheben(preise[0]);
            s.VWhinzufuegen();
            besitzer = s;
        } else {
            s = null;
        }
        return s;
    }

    public Spieler hypothekSetzen(){
        if (besitzer != null){
            hypothek = true;
            besitzer.einzahlen(preise[1]);
        }
        return besitzer;
    }

    public Spieler hypothekAufloesen(){ // nur nach besitzer.genugGeld() aufrufen!
        if (besitzer != null){
            hypothek = false;
            besitzer.abheben(preise[2]);
        }
        return besitzer;
    }
}