package model;
public class Feld
{
    int[] miete;
    int aktion = 0;
    String name;
    /* 0: nichts
     * 1: Ereigniskarte ziehen
     * 2: Gemeinschaftskarte ziehen
     * 3: frei parken
     * 4: gehe ins Gefängnis
     */
    public Feld(int[] m, String n){
        miete = m;
        name = n;
    }

    public void setAktion(int a){
        aktion = a;
    }

    public int[] preiseAusgeben(){ // Länge 1
        int output[] = new int[1];
        output[0] = 0;
        return output;
    }

    public int getAktion(){
        return aktion;
    }

    public int getTyp(){
        return 0;
    }

    public int mieteBerechnen(int gewuerfelt){return miete[0];}

    public Spieler hausKaufen(Spieler s){return s;}

    public Spieler kaufen(Spieler s){return null;} // nur aufrufen nach s.genugGeld()
    public Spieler hypothekSetzen(Spieler s){return s;}

    public Spieler hypothekAuflosesen(Spieler s){return s;} // nur aufrufen nach s.genugGeld()
    public int getAnzahlHaeuser(){return 0;}

    public int getAnzahlHotels(){return 0;}

    public Spieler getBesitzer(){return null;}

    public void setBesitzer(Spieler s){};

    public String getName(){
        return name;
    }
}
