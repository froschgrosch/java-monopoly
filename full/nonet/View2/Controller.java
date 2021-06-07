package View2;

import model.*;
public class Controller{
    model_main model = new model_main(200);

    public Controller(){}

    public int[] wuerfeln(){
        return model.wuerfeln();
    }

    public Spieler getaktuellerspieler(){
        return model.getSpieler()[model.getAktuellerSpieler()];
    }

    public void spielererstellen(String n, int fb, int fz){
        model.spielerErstellen(n,fb,fz);
    }

    public void naechsterspieler(){ // Zug Beenden
        model.zugBeenden();
    }

    public int getgeldindermitte(){
        return model.getGeldInderMitte(); 
    }

    public int grundstueckkaufen(){
        return model.feldKaufen(); 
    }

    public Spieler getspieler1(){
        return model.getSpieler()[0];
    }

    public Spieler getspieler2(){
        return model.getSpieler()[1];
    }

    public Spieler getspieler3(){
        return model.getSpieler()[2];
    }

    public Spieler getspieler4(){
        return model.getSpieler()[3];
    }

    public int getfeldmieteaktuellerspieler(){
        return model.getFeldAktuellerSpieler().mieteBerechnen(0);
    }

    public String ziehen(){
        model.wuerfeln();
        return model.karteAusfuehren().getText();
    }
}