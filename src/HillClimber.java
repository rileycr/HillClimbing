import java.util.*;

/**
 * A.I. HW #3
 * 
 * Instructor: Dr. Zmuda
 * 
 * @author Cooper
 * 
 */
public class HillClimber {

	public static PriorityQueue<Board> queue;
	public static Board[] bestStates;
	public final static int restarts = 5;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int size = -1;

		do {
			System.out.print("Enter int for board size: ");
			size = keyboard.nextInt();
		} while (size <= 0);

		keyboard.close();

		bestStates = new Board[restarts];

		for (int i = 0; i < restarts; i++) {
			Board board = new Board(size);
			board.populateBoard();
			climb(board, i);
		}

		System.out.println(size + "-Queens:");
		for (Board board : bestStates) {

			if (board.calculateScore() == 0) {
				System.out.println("Found a Solution!!");
				printOutBoard(board);
				return;
			}
		}

		System.out.println("No solution this time :(");

	}

	/**
	 * The hill climbing algorithm
	 */
	public static boolean climb(Board board, int restartNum) {
		queue = new PriorityQueue<Board>(board.size * board.size,
				new Board.costComparator());
		queue.add(board);

		do {
			Board currentBest = new Board(queue.remove());

			// Checks the possible locations of each queen
			for (int j = 0; j < board.size; j++) {
				for (int i = 1; i < board.size; i++) {
					queue.add(new Board(currentBest, j, i));
				}
			}

			if (queue.peek().calculateScore() <= currentBest.calculateScore()) {
				currentBest = new Board(queue.remove());
			} else {
				bestStates[restartNum] = new Board(currentBest);
				return true;
			}
			queue.clear();
			queue.add(new Board(currentBest));

		} while (true);
	}

	/**
	 * Prints out the board in a string
	 * 
	 * @param board
	 */
	public static void printOutBoard(Board board) {
		System.out.print(board.toString());
	}

}
