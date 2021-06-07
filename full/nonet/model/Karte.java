package model;
public class Karte
{
    int type;
    int[] params;
    int nummer;
    String text;
    public Karte(int t, int p1, int p2, String te, int n){
        type = t;
        params = new int[2];
        params[0] = p1;
        params[1] = p2;
        text = te;
        nummer = n;
    }

    public Karte(int t, int p1, String te, int n){
        type = t;
        params = new int[1];
        params[0] = p1;
        text = te;
        nummer = n;
    }

    public Karte(int t, String te, int n){
        type = t;
        params = new int[0];   
        text = te;
        nummer = n;
    }

    public int getType(){
        return type;
    }

    public int[] getParams(){
        return params;
    }

    public String getText(){
        return text;
    }
}
