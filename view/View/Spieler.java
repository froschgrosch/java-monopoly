
public class Spieler
{
    public String name;
    public int geld;
    public int farbe;
    public int fahrzeug;
    public int position;
    public boolean gefaengnis;
    public int rundenimgefaengnis;
    public boolean gefaengniskarte;

    public Spieler(String n, int fb, int fz)
    {
        name = n;
        geld = 1500;
        farbe = fb;
        fahrzeug = fz;
        position = 0;
        gefaengnis = false;
        rundenimgefaengnis = 0;
        gefaengniskarte = false;

    }

    public int getposition(){
        return position;
    }

    public int getgeld(){
        return geld;
    }

    
    public void setgeld(int g){
        geld = g;
    }

    public String getname(){
        return name;
    }
}
