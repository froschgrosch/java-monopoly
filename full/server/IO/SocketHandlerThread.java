package IO;
public class SocketHandlerThread extends Thread
{
    String lastMessage = "";
    SocketHandler sockenhaendler;
    public SocketHandlerThread(SocketHandler sh)
    {
       sockenhaendler = sh;
       start();
    }

    public void run(){
        while(true){
            lastMessage = sockenhaendler.read();
           // System.out.println(lastMessage);
        }
    }

    public String read(){
        return lastMessage;
    }

    public void write(String message){
        sockenhaendler.write(message);
    }
}
