package IO;
import java.io.*;

public class Writer
{
    BufferedWriter bw = null;

    public Writer(){}

    public void write(String filename, String[] text){ // Vorsicht, überschreibt Zeug
        try{
            bw = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < text.length; i++){
                bw.write(text[i]);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
