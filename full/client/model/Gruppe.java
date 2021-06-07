package model;
public class Gruppe
{
    int nummer;
    String farbe;
    int[] rgb;
    int haeuser;

    public Gruppe(int n, int h, String f, int[] _rgb)
    {
        nummer = n;
        haeuser = h;
        farbe = f;
        rgb = _rgb;
    }

    int getNummer(){
        return nummer;
    }

    String getFarbName(){
        return farbe;
    }

    int[] getFarbe(){
        return rgb;
    }

    int getAnzahlHaeuser(){
        return haeuser;
    }
}
