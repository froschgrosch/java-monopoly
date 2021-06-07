package Sound;
import java.io.*;
import javax.sound.sampled.*;
import java.util.concurrent.TimeUnit;

public class Player
{
long durationInSeconds;
    public Player(){
    }

    public void play(String filename, long delay){
        try
        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

            TimeUnit.MICROSECONDS.sleep(delay + clip.getMicrosecondLength());
        } catch (Exception e) {e.printStackTrace();}
    }
}
