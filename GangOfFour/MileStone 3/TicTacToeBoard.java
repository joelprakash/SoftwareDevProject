

/**
 * TicTacToeBoard is the board for the Tic-Tac-Toe
 * game. This class is a subclass of Board.
 * 
 * 
 * @author Varun 100869591
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since October 20, 2014
 * 
 */

public class TicTacToeBoard extends Board
{
	// board represented by a 2-d array of Squares
	private Square[][] board;
	
	// size representing the size of the board
	private int size;
	
	
	/**
	 * Constructor for the TicTacToeBoard class
	 * 
	 * @param size -> integer representing the size
	 *                of the board
	 *                
	 */
	
	public TicTacToeBoard(int size)
	{
		super(size);
		this.board = this.getBoard();
		this.size = this.getSize();
	}
	
	
	/**
	 * Added by Varun Sriram 100869591
	 * Method to check if a particular row on the board is filled with a paticular occupancy
	 * @param row the row index you wish to check
	 * @param p player you wish to check occupancy.
	 * @return if it is filled return true if not return false.
	 */
	public boolean isRowFilled(int row,Player p){
		
		for(int i = 0;i<this.size;i++){
			
			if(this.board[row][i].checkPlayerOccupied() == null ||!(this.board[row][i].checkPlayerOccupied().equals(p))){
				return false;
			}
		}
		return true;
	}
	/**
	 * Added by Varun Sriram 100869591
	 * Method to check if a particular column is filled by a player.
	 * @param col Column index of the board
	 * @param p player you checking the occupancy.
	 * @return if it is filled return true if not return false.
	 */
	public boolean isColFilled(int col,Player p){
		for(int i = 0;i<this.size;i++){
			
			if(this.board[i][col].checkPlayerOccupied() == null ||!(this.board[i][col].checkPlayerOccupied().equals(p))){
				return false;
			}
		}
		return true;
	}
	/**
	 * Added By Varun Sriram 100869591
	 * Method to check if the Left to Right Diagonal of the board is filled by particular player 
	 * @param p Player to check for occupancy.
	 * @return if its filled return true if not false.
	 */
	public boolean isDiagonalFilledLtoR(Player p){
		for(int i = 0; i<this.size;i++){
			if(this.board[i][i].checkPlayerOccupied() == null ||!(this.board[i][i].checkPlayerOccupied().equals(p))){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Added By Varun Sriram 100869591
	 * Method to check if the Right to Left Diagonal of the board is filled by particular player 
	 * @param p Player to check for occupancy.
	 * @return if its filled return true if not false.
	 */
	public boolean isDiagonalFilledRtoL(Player p){
		int c = this.size-1;
		for(int r = 0; r< this.size;r++ ){
		
			if(this.board[r][c].checkPlayerOccupied() == null || !(this.board[r][c].checkPlayerOccupied().equals(p))){
				return false;
			}

				c--;
			
		
		}
		return true;
	}

}
