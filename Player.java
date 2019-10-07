import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class holds the information of each player such as its name, board, opponent
 * and mark (X or O)
 */
public class Player implements Constants{
    /**
     * The name of the player
     */
    private String name;

    /**
     * Object board that player is playing on
     */
    private Board board;

    /**
     * Object player which is the opponent of this player
     */
    private Player opponent;

    /**
     * character X or O for the player
     */
    private char mark;

    PrintWriter out;
    Socket aSocket;
    BufferedReader in;

    /**
     * Constructs a player object with the specified values for name and mark.
     * The values of the data field are supplied by the given parameters
     * @param ch character(X or O) assigned to player
     */
    public Player(Socket s, char ch){
        this.aSocket = s;
        this.mark = ch;

        try{
            in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            out = new PrintWriter(aSocket.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendString(String toSend){
        out.println(toSend);
        out.flush();
    }

    public void getPlayerName(){
        try{
            sendString("Please enter the name of '" + mark + "' player: \0");
            name = in.readLine();

            while(name == null){
                sendString("Please try again: \0");
                name = in.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * makes the next play
     * first checks for any wins or full board
     * then calls make move for next inputs
     */
    public void play(){

        sendString(this.board.display());

        this.makeMove();

        if(board.xWins()){
            if(this.mark == LETTER_X) {
                notifyPlayers(this.name + " wins!!!");
                return;
            }
            else{
                notifyPlayers(this.opponent.name + " wins!!!");
                return;
            }
        }

        if(board.oWins()){
            if(this.mark == LETTER_O) {
                notifyPlayers(this.name + " wins!!!");
                return;
            }
            else{
                notifyPlayers(this.opponent.name + " wins!!!");
                return;
            }
        }

        if(board.isFull()){
            notifyPlayers("The match has resulted in a DRAW!");
            return;
        }

        sendString("Waiting for opponent's turn!");
    }

    public void notifyPlayers(String s){
        out.println(s);
        opponent.out.println(s);
    }

    /**
     * asks player to make their next move by asking
     * for inputs row and column where their mark would
     * be added
     */
    public void makeMove(){
        try {
            sendString(this.name + ", please enter row where mark is to be added: \0");
            int row = Integer.parseInt(in.readLine());
            sendString(this.name + ", please enter column where mark is to be added: \0");
            int col = Integer.parseInt(in.readLine());


            while (board.getMark(row, col) == LETTER_X || board.getMark(row, col) == LETTER_O) {
                sendString(this.name + ", please enter row where a mark doesn't exist: \0");
                row = Integer.parseInt(in.readLine());
                sendString(this.name + ", please enter column where mark doesn't exist: \0");
                col = Integer.parseInt(in.readLine());
            }

            this.board.addMark(row, col, this.mark);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * sets opponent of this player
     * @param opponent object player which is opponent of this player
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    /**
     * sets board of this player
     * @param theBoard object board which player is playing on
     */
    public void setBoard(Board theBoard){
        this.board = theBoard;
    }

}
