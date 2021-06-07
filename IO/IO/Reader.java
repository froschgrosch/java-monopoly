package IO;
import java.io.*;

public class Reader{
    FileReader fr = null;
    BufferedReader br = null;
    Reader(){}

    public String[] read(String fileName){
        String[] output = new String[1];
        output[0] = "";
        try
        {
            File file = new File(fileName) ;
            fr = new FileReader(file);
            br = new BufferedReader(fr) ;
            String[] temp = new String[1000];
            int i = 0;

            String line ;
            StringBuffer sb = new StringBuffer();
            // systemeigenes Zeilenumbruchszeichen ermitteln
            String sep = System.getProperty("line.separator");

            while( (line=br.readLine()) != null ){
                temp[i] = line;
                i++;
            }
            output = new String[i+1];
            for (int j = 0; j < i; j++){
                output[j] = temp[j];
            }

            System.out.println(sb);  //ganze Datei ausgegeben
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try
            {
                if(br!=null) br.close();
                if(fr!=null) fr.close();
            }
            catch(Exception ex)
            {
            }
        }
        return output;
    }
}