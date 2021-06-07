package IO;
public class SocketHandlerThread extends Thread
{
    String lastMessage = "";
    SocketHandler sh;
    public SocketHandlerThread(SocketHandler _sh)
    {
       sh = _sh;
        start();
    }

    public void run(){
        while(true){
            lastMessage = sh.read();
           // System.out.println(lastMessage);
        }
    }

    public String read(){
        return lastMessage;
    }

    public void write(String message){
        sh.write(message);
    }
}
