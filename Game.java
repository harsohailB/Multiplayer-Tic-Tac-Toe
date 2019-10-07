
import java.io.*;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 

/**
 * This class holds main function and acts as a node for all other classes
 * as it monitors the referee class, used the constants interface, and holds
 * the board and plays the players class
 * @author Harsohail Brar
 * @version 1.0
 * @since January 31, 2019
 */
public class Game implements Constants, Runnable {

	private Board theBoard;
	private Referee theRef;

	/**
	 * default constructor for class Game
	 * which constructs an object of class Board
	 */
    public Game() {
    	theBoard  = new Board();
	}

	/**
	 * appoints a referee for the game
	 * @param r object of class referee being appointed to the game
	 * @throws IOException
	 */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
		theRef.setBoard(theBoard);
		theRef.getxPlayer().setBoard(theBoard);
		theRef.getoPlayer().setBoard(theBoard);
    }

	@Override
	public void run(){
    	try {
			theRef.runTheGame();
		}catch(IOException e){
    		e.printStackTrace();
		}
	}

}
