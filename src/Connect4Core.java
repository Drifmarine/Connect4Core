/**
 * @author Jiangda
 *
 */
public class Connect4Core {

	private int[][] chessBoard;
	private int[] chessInColoum;
	private int[] lastMove;
	private int totalChess;
	private Player human = new Player("human");
	private Player computer = new Player("computer");
	private Player currentPlayer;

	public Connect4Core() {
		this.reset();
	}

	/**
	 * This method may add chess to the board.
	 * 
	 * @param coloum
	 *            where the chess would be add
	 * @return -1 when coloum is full
	 * @return 1 when successfully added the chess to board
	 * @throws ColoumFullException
	 */
	public void addChess(int coloum) throws ColoumFullException {
		if (coloum >= 6)
			throw new ColoumFullException();
		int row = 5 - chessInColoum[coloum]++;
		chessBoard[row][coloum] = currentPlayer.getID();
		recordLastMove(row, coloum, currentPlayer.getID());
		totalChess++;
	}

	/**
	 * Change the current player
	 */
	public void changePlayer() {
		if (currentPlayer == human)
			currentPlayer = computer;
		else
			currentPlayer = human;
	}

	/**
	 * @return the winner. return null if nobody wins.
	 */
	public Player checkWins() {
		int chessInRow, chessInColoum, slideFromLToR, slideFromRToL;
		chessInRow = partialCheck(lastMove, 0, 1) + partialCheck(lastMove, 0, -1);
		chessInColoum = partialCheck(lastMove, 1, 0);
		slideFromLToR = partialCheck(lastMove, 1, -1) + partialCheck(lastMove, -1, 1);
		slideFromRToL = partialCheck(lastMove, -1, -1) + partialCheck(lastMove, 1, 1);
		if (chessInRow >= 3 || chessInColoum >= 3 || slideFromLToR >= 3 || slideFromRToL >= 3) {
			return currentPlayer;
		}
		return null;
	}

	/**
	 * @return if the game becomes draw
	 */
	public boolean checkDraws() {
		if (totalChess >= 42) {
			return true;
		}
		return false;
	}

	/**
	 * Reset everything include the board and set default player
	 */
	public void reset() {
		chessBoard = new int[6][7];
		chessInColoum = new int[7];
		totalChess = 0;
		lastMove = new int[3];
		currentPlayer = human;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = "";
		for (int[] i : chessBoard) {
			for (int j : i) {
				str += j;
				str += " ";
			}
			str += "\n";
		}
		return str;
	}

	/**
	 * Record last move for easy of check winning status BEFORE changing current
	 * player
	 * 
	 * @param row
	 * @param coloum
	 */
	private void recordLastMove(int row, int coloum, int id) {
		lastMove[0] = row;
		lastMove[1] = coloum;
		lastMove[2] = id;
	}

	/**
	 * @param ptr
	 *            the pointer
	 * @param toDown
	 *            decide where the pointer goes each time
	 * @param toLeft
	 *            decide where the pointer goes each time
	 * @param id
	 *            the current player's id
	 * @return
	 */
	private int partialCheck(int[] ptr, int toDown, int toLeft) {
		int[] newPtr = { ptr[0] + toDown, ptr[1] + toLeft, ptr[2] };

		// let the pointer not go out of the board
		if (newPtr[0] < 0 || newPtr[1] < 0 || newPtr[0] >= 6 || newPtr[1] >= 7) {
			return 0;
		}

		// stop checking if met the different chess
		else if (chessBoard[newPtr[0]][newPtr[1]] != newPtr[2]) {
			return 0;
		}

		// if the current chess is the same as the last-move chess, continue
		else {
			return 1 + partialCheck(newPtr, toDown, toLeft);
		}
	}

}
