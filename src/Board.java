import java.util.Comparator;

/**
 * Defines a playing board for the n-queens problem
 * 
 * @author Cooper
 * 
 */
public class Board {

	public int size;
	public Queen[] queens;

	/**
	 * Constructor
	 * 
	 * @param size
	 */
	public Board(int size) {
		this.size = size;
		this.queens = new Queen[size];
	}

	/**
	 * Copy constructor
	 * 
	 * @param toCopy
	 */
	public Board(Board toCopy) {
		this(toCopy.size);
		for (int i = 0; i < toCopy.size; i++) {
			this.queens[i] = new Queen(toCopy.queens[i]);
		}
	}

	/**
	 * Constructor used when adding the children of the board
	 * 
	 * @param board
	 *            the current board
	 * @param whichQueen
	 *            which queen is being moved
	 * @param newRow
	 *            how far to move it
	 */
	Board(Board board, int whichQueen, int newRow) {
		this(board);
		this.queens[whichQueen] = new Queen(board.queens[whichQueen]);
		this.queens[whichQueen].row = (this.queens[whichQueen].row + newRow)
				% board.size;
	}

	/**
	 * Places the queens in random locations by column.
	 */
	public void populateBoard() {
		for (int i = 0; i < size; i++) {
			queens[i] = new Queen((int) (Math.random() * size), i, size);
		}
	}

	/**
	 * Prints out the board configuration
	 */
	public String toString() {
		String result = "";

		for (int i = 0; i < this.size * this.size; i++) {
			if (this.locationIs(i)) {
				result += "Q";
			} else {
				result += ".";
			}
		}

		String formattedResult = "";
		for (int i = 0; i < result.length(); i++) {
			formattedResult += result.charAt(i);
			if (i % this.size == this.size - 1)
				formattedResult += "\n";
		}

		return formattedResult;
	}

	/**
	 * Used for printing out the board as a string
	 * 
	 * @param location
	 *            to check if it is populated
	 * @return
	 */
	public boolean locationIs(int location) {
		for (Queen q : this.queens) {
			if (q.row * this.size + q.col == location)
				return true;
		}
		return false;
	}

	/**
	 * Calculates the score of the board's current configuration
	 * 
	 * @return
	 */
	public int calculateScore() {
		int conflictInRows = 0;
		int conflictInDiagCol = 0;
		int conflictInDiagRow = 0;

		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (queens[j].row == queens[i].row && i != j)
					conflictInRows++;
				if (queens[j].diagCol == queens[i].diagCol && i != j)
					conflictInDiagCol++;
				if (queens[j].diagRow == queens[i].diagRow && i != j)
					conflictInDiagRow++;
			}
		}
		return conflictInRows + conflictInDiagCol + conflictInDiagRow;
	}

	/**
	 * Used by the queue when selecting the best child to go to when climbing.
	 * 
	 * @author Cooper
	 * 
	 */
	public static class costComparator implements Comparator<Board> {

		@Override
		public int compare(Board arg0, Board arg1) {
			return arg0.calculateScore() - arg1.calculateScore();
		}

	}
}
