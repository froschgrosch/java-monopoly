package IO;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
public class SocketHandler
{
    PrintWriter out;
    BufferedReader in;
    public SocketHandler(Socket socket){
        /* Client:  new Socket(ip, port);
         * Server:  server = new ServerSocket(port);
         *          server.accept();     */
        try{
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(Exception e){e.printStackTrace();}
    }

    public String read(){
        String output = "";
        try{
            output = in.readLine();
        }catch(Exception e){e.printStackTrace();}
        return output;
    }

    public void write(String message){
        try{
            out.println(message);
        } catch (Exception e){e.printStackTrace();}
    }
}
