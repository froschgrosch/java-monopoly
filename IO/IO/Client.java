package IO;

import java.net.*;
import java.io.*;

public class Client
{
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    public Client(String ip, int port)
    {
        try{
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clientWrite(String input) {
        out.println(input);
    }

    public String clientRead(){
        String output = "";
        try{
            output = in.readLine();
        }
        catch (Exception e){System.out.println(e);}
        return output;
    }
}
