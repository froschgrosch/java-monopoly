
public class Controller{
    Model model = new Model();

    public Controller(){

    }
    public int[] wuerfeln(){
        return model.wuerfeln();

    }

    public Spieler getaktuellerspieler(){
        return model.getaktuellerspieler();
    }

    public String getspieler1name(){
        return model.spieler1name;
    }

    public void spielererstellen(String n, int fb, int fz){

        model.spielererstellen( n,  fb,  fz);
    }

    public void ziehen(int a){

        model.ziehen(a);
    }

    public String ereigniskarte(){
        return model.ereigniskarte();
    }

    public String gemeinschaftsskarte(){
        return model.gemeinschaftskarte();
    }

    public void freiparken(){

        model.freiparken();
    }

    public int gefaengnis(int w1, int w2){

        return  model.gefaengnis(w1, w2);
    }

    public void gehensiegefaengnis(){
        model.gehensiegefaengnis();
    }

    public int zahlfeld(){
        return model.zahlfeld();
    }

    public String mietezahlen(int m){
        return model.mietezahlen(m); 
    }

    public void freikaufen(){
        model.freikaufen();
    }

    public void gefaengniskarte(){
        model.gefaengniskarte();
    }

    public void naechsterspieler(){
        model.naechsterspieler();
    }

    public int getgeldindermitte(){
        return model.getgeldindermitte(); 
    }

    public String grundstueckkaufen(){
        return model.grundstueckkaufen(); 
    }

    public void setaktuellerspieler(Spieler s){
        model.setaktuellerspieler(s);

    }

    public Spieler getspieler1(){
        return model.getspieler1();
    }

    public Spieler getspieler2(){
        return model.getspieler2();
    }

    public Spieler getspieler3(){
        return model.getspieler3();
    }

    public Spieler getspieler4(){
        return model.getspieler4();
    }

    public int getfeldmieteaktuellerspieler(){

        return model.getfeldmieteaktuellerspieler();
    }
}