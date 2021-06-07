import Sound.*;

public class Main
{
    VOXtranslator vt = new VOXtranslator();
    Player p = new Player();
    String numbers_path = "vox-files\\numbers\\";
    public Main(){
        /*
        sayWord("you.wav");
        sayWord("money\\get.wav");
        sayNumber(508);
        */
    }

    public void sayNumber(int number) {
        String[] s = vt.toStringArr(number);
        for (int i = 0; i < s.length; i++){
            p.play("data\\"+numbers_path+s[i],(long)0.2);
        }
    }

    public void sayWord(String filename){
        p.play("data\\"+"vox-files\\"+filename,(long)1.0);
    }
}
