package IO;

import java.net.*;
import java.io.*;

public class Server
{
    ServerSocket server;
    Socket client;
    PrintWriter out;
    BufferedReader in;
    public Server(int port)
    {
        try{
            server = new ServerSocket(port);
            client = server.accept();
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void serverWrite(String input) {
        out.println(input);
    }

    public String serverRead(){
        String output = "";
        try{
            output = in.readLine();
        }
        catch (Exception e){System.out.println(e);}
        return output;
    }
}
