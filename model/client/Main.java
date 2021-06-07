// import model.*;
import IO.*;
import java.util.Scanner;
import java.net.Socket;
public class Main
{
    Scanner scanner = new Scanner("System.in");
    int port;
    String ip;

    SocketHandler sockenhaendler;
    String oldMessage = "";
    String newMessage = "";

    String name = "test-fist"; // KEINE DOPPELPUNKTE!
    int farbe;

    Main(){
        setAddress();
        connect();
    }

    private void setAddress(){
        System.out.println("Please input the IP: ");
        ip = scanner.nextLine();
        System.out.println("Please input the port: ");
        port = scanner.nextInt();
        while (port < 1 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = scanner.nextInt();
        }
    }

    private void connect(){
        try{
            new SocketHandlerThread(new SocketHandler(new Socket(ip,port)));
            System.out.println("Connected to "+ip+":"+port);
        }catch(Exception e){e.printStackTrace();}
    }

    private void hinrichtenNetzwerk(){
        if ((newMessage = sockenhaendler.read()) != oldMessage){
            sockenhaendler.write(hendlNetzwerk(newMessage));
            oldMessage = newMessage;
        }
    }

    private String hendlNetzwerk(String message){
        String antwort = "";
        switch(message){
            case "sendnudes":
            antwort = "sendnudes:"+name
            break;
        }
        return antwort;
    }
}