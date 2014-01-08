/**
 * Defines a queen for the playing board. diagRow and diagCol are calculated by
 * rotating the board CCW 45 degrees and having diagRow and diagCol defined as
 * the rows and cols of that diamond.
 * 
 * @author Cooper
 * 
 */
public class Queen {
	public int row;
	public int col;
	public int diagRow;
	public int diagCol;

	public int boardSize;

	/**
	 * Constructor
	 * 
	 * @param row
	 * @param col
	 * @param boardSize
	 */
	Queen(int row, int col, int boardSize) {
		this.boardSize = boardSize;
		this.row = row;
		this.col = col;
		this.diagRow = calcDiagRow(row, col);
		this.diagCol = calcDiagCol(row, col);

	}

	/**
	 * Copy constructor
	 * 
	 * @param queen
	 */
	public Queen(Queen queen) {
		this(queen.row, queen.col, queen.boardSize);
	}

	/**
	 * Calculates the 'diagonal row'. determined by rotating the board CCW 45
	 * degrees and viewing the rows and columns as horizontal and vertical.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public int calcDiagRow(int row, int col) {
		return row - col + boardSize - 1;
	}

	/**
	 * 
	 * Calculates the 'diagonal column' similarly to the row
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public int calcDiagCol(int row, int col) {
		return row - (boardSize - 1 - col) + boardSize - 1;
	}

}
