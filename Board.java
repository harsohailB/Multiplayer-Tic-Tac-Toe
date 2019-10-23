
/**
 * This class keeps adds plays, deletes plays, clears and displays the
 * board as well as monitors it for wins
 */
public class Board implements Constants {
	private char theBoard[][];
	private int markCount;

	/**
	 * Default constructor for class Board which sets up the board array
	 * and sets markCount to 0
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * getter member method which return what the mark is in a specific cell
	 * @param row row of the board array requested
	 * @param col col of the board array requested
	 * @return the mark in the cell requested
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * checks if board is full with marks
	 * @return true if board is filled and false otherwise
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * checks if Player X has won
	 * @return true if player X wins, false otherwise
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * checks if Player O has won
	 * @return true if player O wins, false otherwise
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * displays the board onto the console
	 */
	public String display() {
		String s = displayColumnHeaders();
		s += addHyphens();


		for (int row = 0; row < 3; row++) {
			s += addSpaces();
			s += "row " + row + ' ';
			for (int col = 0; col < 3; col++)
				s += "|  " + getMark(row, col) + "  ";
			s += "|\n" ;

			s += addSpaces();
			s += addHyphens();
		}

		return s;
	}

	/**
	 * adds the mark requested to the cell requested
	 * @param row the row mark being added to
	 * @param col the column mark being added to
	 * @param mark mark being added (X or O)
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * checks if either player has won
	 * @param mark mark being checked for a win (X or O)
	 * @return returns 1 if mark requested has won,  0 otherwise
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * displays column headers on board to console
	 */
	String displayColumnHeaders() {
		String s = "      ";
		for (int j = 0; j < 3; j++)
			s += "|col " + j;

		return s + "\n";
	}

	/**
	 * adds and displays hyphens on board to console
	 */
	String addHyphens() {
		String s = "      ";

		for (int j = 0; j < 3; j++)
			s += "+-----";
		s += "+";

		return s + "\n";
	}

	/**
	 * adds and displays spaces on board to console
	 */
	String addSpaces() {
		String s = "      ";
		for (int j = 0; j < 3; j++)
			s += "|     ";
		s += "|";

		return s + "\n";
	}
}
