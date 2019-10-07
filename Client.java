import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client implements Constants{
    private PrintWriter socketOut;
    private Socket aSocket;
    private BufferedReader stdIn;
    private BufferedReader socketIn;


    public Client(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter((aSocket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public void communicateServer() {
        Scanner input = new Scanner(System.in);
        try{
            while(true) {
                String read = "";

                while (true) {
                    read = socketIn.readLine();

                    if (read.contains("\0")) {
                        read.replace("\0", "");
                        System.out.println(read);
                        break;
                    }

                    if (read.equals("QUIT")) {
                        return;
                    }

                    System.out.println(read);
                }

                read = input.nextLine();
                socketOut.println(read);
                socketOut.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Client aClient = new Client("localhost", PORT);
        aClient.communicateServer();
    }
}
