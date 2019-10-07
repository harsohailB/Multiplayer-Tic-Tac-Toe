import java.io.IOException;

/**
 * this class looks over the player and the board
 * as it runs the game by calling the next move and
 * updating the display
 */
public class Referee implements Constants{
    /**
     * Player 1 of the game
     */
    private Player xPlayer;

    /**
     * Player 2 of the game
     */
    private Player oPlayer;

    /**
     * Board the game is being played on
     */
    private Board board;

    /**
     * Default constructor for class referee
     * which sets all member variables to null
     */
    public Referee(){
        xPlayer = null;
        oPlayer = null;
        board = null;
    }

    /**
     * displays updated board and calls players to play.
     * it also sets opponents of each player
     * @throws IOException
     */
    public void runTheGame() throws IOException {
        notifyPlayers("Welcome to a game of Tic Tac Toe!");
        xPlayer.sendString("Waiting for another player to connect!");

        oPlayer.setOpponent(xPlayer);
        xPlayer.setOpponent(oPlayer);

        oPlayer.getPlayerName();
        oPlayer.sendString("Waiting for X player's name!");
        xPlayer.getPlayerName();

        System.out.println("\nReferee started the game...");
        notifyPlayers("\nReferee started the game...");

        while(true) {
            xPlayer.play();
            if(board.xWins() || board.oWins() || board.isFull())
                break;

            oPlayer.play();
            if(board.xWins() || board.oWins() || board.isFull())
                break;
        }

        //xPlayer.play(); //outputs win of a player

        System.out.println("\nReferee ended the game...");
        notifyPlayers("\nReferee ended the game...");
    }


    public void notifyPlayers(String s){
        oPlayer.sendString(s);
        xPlayer.sendString(s);
    }

    /**
     * sets board
     * @param board object Board being played on
     */
    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * sets oPlayer
     * @param oPLayer Player O
     */
    public void setoPlayer(Player oPLayer){
        this.oPlayer = oPLayer;
    }

    /**
     * sets xPlayer
     * @param xPlayer Player X
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    public Player getxPlayer() {
        return xPlayer;
    }

    public Player getoPlayer() {
        return oPlayer;
    }

    public Board getBoard() {
        return board;
    }
}
