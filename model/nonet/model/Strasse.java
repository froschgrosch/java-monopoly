package model;
public class Strasse extends Feld
{
    int[] preise; // Größe 10
    /* 0: Kaufpreis
     * 1: unbebaute Miete
     * 2: Miete 1 Haus
     * 3: Miete 2 Häuser
     * 4: Miete 3 Häuser
     * 5: Miete 4 Häuser
     * 6: Miete Hotel (als 5 Häuser speichern!)
     * 7: Kaufpreis Haus
     * 8: Hypothekenwert
     * 9: Auflösen der Hypothek */

    String name;
    Gruppe gruppe;
    int gebauteHaeuser = 0;
    boolean hypothek = false;
    Spieler besitzer = null;

    public Strasse(int[] p, Gruppe g, String n)
    {
        super(p,n);
        name = n;
        gruppe = g;
        preise = p;
    }

    public int getAktion(){
        return 0;
    }

    public int getTyp(){
        return 1;
    }

    public Gruppe getGruppe(){
        return gruppe;
    }

    public int mieteBerechnen(int gewuerfelt){
        int miete = 0;
        if (!hypothek && besitzer != null){
            miete = preise[1+gebauteHaeuser];
            if (besitzer.anzahlHaeuser[gruppe.getNummer()] == gruppe.getAnzahlHaeuser()){
                miete = miete * 2;
            }
        }
        return miete;
    }

    public int[] preiseAusgeben(){
        return preise;
    }

    public Spieler getBesitzer(){
        return besitzer;
    }

    public void setBesitzer(Spieler s){
        besitzer = s;
    }

    public String getName(){
        return name;
    }

    public Spieler kaufen(Spieler s){ // nur nach s.genugGeld(preise[0]) aufrufen!
        if (besitzer == null){
            s.abheben(preise[0]);
            s.strasseHinzufuegen(gruppe.getNummer());
            besitzer = s;
        } else {
            s = null;
        }
        return s;
    }

    public Spieler hypothekSetzen(){
        if (besitzer != null){
            hypothek = true;
            besitzer.einzahlen(preise[8]);
        }
        return besitzer;
    }

    public Spieler hypothekAufloesen(){ // nur nach besitzer.genugGeld(preise[9]) aufrufen!
        if (besitzer != null){
            hypothek = false;
            besitzer.abheben(preise[9]);
        }
        return besitzer;
    }

    public void saveLaden(boolean hypo, Spieler b, int haeuser){
        gebauteHaeuser = haeuser;
        hypothek = hypo;
        besitzer = b;
    }

    public int getAnzahlHaeuser(){
        int output = gebauteHaeuser;
        if (gebauteHaeuser == 5){
            output = 0;
        }
        return output;
    }

    public int getAnzahlHotels(){
        int output = 0;
        if (gebauteHaeuser == 5){
            output = 1;
        }
        return output;
    }

}
