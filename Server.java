import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Server implements Constants{
    ServerSocket serverSocket;
    private ExecutorService pool;

    public Server(){ // throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            pool = Executors.newFixedThreadPool(10);

        } catch (IOException e) {
            System.out.println("Create new socket error");
            System.out.println(e.getMessage());
        }
        System.out.println("Server is running");
    }

    public void communicateWithClient() throws IOException{
        try{
            while(true){
                Player xPlayer = new Player(serverSocket.accept(), Constants.LETTER_X);
                Player oPlayer = new Player(serverSocket.accept(), Constants.LETTER_O);

                Referee theRef = new Referee();
                theRef.setoPlayer(oPlayer);
                theRef.setxPlayer(xPlayer);

                Game theGame = new Game();
                theGame.appointReferee(theRef);

                System.out.println("Started a game!");

                pool.execute(theGame);
            }
        }catch (Exception e){
            e.printStackTrace();
            pool.shutdown();
        }

    }

    public static void main(String[] args) throws IOException {
        Server myserver = new Server();
        myserver.communicateWithClient();
    }

}