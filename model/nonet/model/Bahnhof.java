package model;
public class Bahnhof extends Feld
{
    private int preise[]; // Größe 4
    /* 0: Kaufpreis
     * 1: Miete
     * 2: Hypothekenwert
     * 3: Auflösen der Hypothek */

    String name;
    Spieler besitzer = null;
    boolean hypothek = false;
    int nummer;

    public Bahnhof(int[] p, String n, int nu)
    {
        super(p,n);
        name = n;
        nummer = nu;
    }

    public void setBesitzer(Spieler s){
        besitzer = s;
    }

    public Spieler getBesitzer(){
        return besitzer;
    }

    public String getName(){
        return name;
    }

    public int getAktion(){
        return 0;
    }

    public int getTyp(){
        return 2;
    }

    public int[] preiseAusgeben(){
        return preise;
    }

    public int ermittleMiete(int gewuerfelt) {
        int miete = 0;
        if (besitzer != null && !hypothek) {
            miete = preise[1] * (2 ^ (besitzer.getAnzahlBahnhoefe() - 1));
        }
        return miete;
    }

    public int kaufpreisAusgeben(){
        return preise[0];
    }

    public Spieler kaufen(Spieler s){ // nur nach s.genugGeld(preise[0]) aufrufen!
        if (besitzer != null){
            s.abheben(preise[0]);
            s.bahnhofHinzufuegen();
            s.feldBesitzen(nummer);
            besitzer = s;
        } else {
            s = null;
        }
        return s;
    }

    public Spieler hypothekSetzen(){
        if (besitzer != null){
            hypothek = true;
            besitzer.einzahlen(preise[2]);
        }
        return besitzer;
    }

    Spieler hypothekAufloesen(){ // nur nach besitzer.genugGeld() aufrufen!
        if (besitzer != null){
            hypothek = false;
            besitzer.abheben(preise[3]);
        }
        return besitzer;
    }

    public void saveLaden(boolean hypo, Spieler b){
        hypothek = hypo;
        besitzer = b;
    }
}
