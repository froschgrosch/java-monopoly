
import model.*;
import IO.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    int port = 12345;

    model_main Spiel;
    SocketHandlerThread[] sockenhaendler = new SocketHandlerThread[4];
    ServerSocket server;

    String newMessage[] = {"","","",""};
    String oldMessage[] = newMessage;

    public Main(int players){
        Spiel = new model_main(1500);
        try {
            server = new ServerSocket(port);
            for(int i = 0; i < sockenhaendler.length; i++){
                sockenhaendler[i] = new SocketHandlerThread(new SocketHandler(server.accept()));
                System.out.println("Client "+(i+1)+" connected");
            }
            server.close();
            // ab server.close sind alle Clients verbunden
        }catch(Exception e){e.printStackTrace();}
        brotkasten("sendnudes"); // Spieler-Daten anfordern
    }

    void brotkasten(String s){ // BROADCAST!
        for(int i = 0; i < sockenhaendler.length; i++){
            sockenhaendler[i].write(s);
        }
    }

    void allOk(){
        boolean[] ok = {false,false,false,false};
        while(!ok[0] && !ok[1] && !ok[2] && !ok[3]){
            for(int i = 0; i < sockenhaendler.length; i++){
                if ((newMessage[i] = sockenhaendler[i].read()) == "ok"){
                    ok[i] = true;
                    oldMessage[i] = newMessage[i];
                }
            }
        }
    }

    void isOk(int i){
        while((newMessage[i] = sockenhaendler[i].read()) != "ok"){
            // warten
        }
        newMessage[i] = oldMessage[i];
    }

    void hinrichtenNetzwerk(){
        for(int i = 0; i < sockenhaendler.length; i++){
            if ((newMessage[i] = sockenhaendler[i].read()) != oldMessage[i]){
                sockenhaendler[i].write(hendlNetzwerk(newMessage[i]));
                oldMessage[i] = newMessage[i];
            }
        }
    }

    private String hendlNetzwerk(String message){
        return "";
    }
}