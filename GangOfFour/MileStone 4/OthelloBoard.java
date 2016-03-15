

/**
 * 
 * @author Himanish Kaushal 100846382
 * @author Joel Prakash     100910302
 * This is the board for playing othello. 
 * It is a sub class of board.
 */

public class OthelloBoard extends Board
{

	private Square [][] board;
	
	/**
	 * An Othello board constructor
	 * @param size
	 */
	public OthelloBoard(int size)
	{
		super(size);
		board = getBoard();
	}
	public OthelloBoard(Board b){
		super(b);
		this.board = this.getBoard();
	}
	
	/**
	 * This function is given a particular square where it flips that piece
	 * @param row
	 * @param col
	 * @param player
	 * @return whether the Othello piece is flipped or not.
	 */
	public boolean flipSquare(int row, int col, Player player) {
		
		if(isSquareOccupied(row, col)) {
			//changes the current Othello piece in this square to the one defined by player
			board[row][col].setOccupied(player);
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Given the cordinates on the board, this function returns that square on the board 
	 * @param row
	 * @param col
	 * @return Square
	 */
	public Square getSquare(int row, int col) {
		
		return board[row][col];
	}

}
