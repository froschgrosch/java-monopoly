package IO;
import java.io.*;

public class Reader{
    FileReader fr = null;
    BufferedReader br = null;
    public Reader(){}

    public String[] read(String fileName){
        String[] output = new String[1];
        output[0] = "";
        try
        {
            fr = new FileReader(new File(fileName));
            br = new BufferedReader(fr) ;
            String[] temp = new String[1000]; // sollte ausreichen für Monopoly
            String line;
            StringBuffer sb = new StringBuffer();

            int i = 0;
            while( (line=br.readLine()) != null ){
                temp[i] = line;
                i++;
            }
            output = new String[i+1];
            for (int j = 0; j < i; j++){
                output[j] = temp[j];
            }
            if(br!=null){br.close();}
            if(fr!=null){fr.close();}
        }catch(Exception e){e.printStackTrace();}
        return output;
    }
}