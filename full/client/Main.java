import model.*;
public class Main
{
    model_main Spiel = new model_main(200,1500);
    public Main()
    {
        int[] black = {0,0,0};
        Spiel.spielerErstellen("Foof",black,0);
        Spiel.spielerErstellen("Siis",black,1);
        Spiel.spielerErstellen("Meem",black,2);
        Spiel.spielerErstellen("Daad",black,3);
        Spiel.spielStarten();
    }

    public Spieler getSpielerAmZug(){
        return Spiel.getSpieler()[Spiel.getAktuellerSpieler()];
    }

    public void kaufen(){
        Spiel.feldKaufen();
        zugBeenden();
    }

    public void zugBeenden(){
        Spiel.zugBeenden();
    }

    public void ziehen(){
        int[] ziehen = Spiel.wuerfeln();
        int[] miete = Spiel.mieteZahlen(ziehen[0] + ziehen[1]);
        String karte =  Spiel.karteAusfuehren();
        // System.out.println(ziehen);
        System.out.println("Spieler "+(miete[1]+1)+" zahlt "+miete[0]+" an Spieler "+(miete[2]+1));
        System.out.println(karte);
    }

    public int feldKaufen(){
        return Spiel.feldKaufen();
    }
}