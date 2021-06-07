
public class Feld
{
    public  int typ;
    public  int miete;
    public int preis;
    public int inbesitz;
    public Spieler besitzer;

    public Feld(int t, int m, int p)
    {
        typ = t;
        miete = m;
        preis = p;
        inbesitz = 0;
        besitzer = null;
    }

    public void setbesitzer(Spieler s){
        besitzer = s;
    }

    public int gettyp(){
        return typ;
    }

    public int getmiete(){
        return miete;
    }

    public int getpreis(){
        return preis;
    }
    
    public Spieler getbesitzer(){
        return besitzer;
    }
}
