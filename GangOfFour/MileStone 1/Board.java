
/**
 * 
 * @author Himanish Kaushal 100846382
 * 
 * Class: Board
 * Description: This class contains information about the behaviour
 * of the board that the game is going to be played on. 
 */

public class Board {
	
	// the size of the rows/columns of the board
	private int size;
	// the board is a 2-D array of 'squares'
	private Square[][] board;
	
	// the constructor takes in the size and initializes each square
	// of the board
	public Board(int size) {
		this.board = new Square[size][size];
		this.size = size;
		for(int r = 0; r < this.size; r++) {
			
			for(int c = 0; c < this.size; c++) {
				
				board[r][c] = new Square();
			} // end for
		} // end for
	}
	
	public int getSize() {
		
		return size;
	}
	
	protected Square [][] getBoard()
	{
		return this.board;
	}
	
	/**
	 * returns:
	 * true - if square is occupied
	 * false - otherwise
	 * 
	 * NOTE - takes help from the 'checkOccupied()' method in the Square class
	 */
	public boolean isSquareOccupied(int row, int col) {
		
		return board[row][col].checkOccupied();
	}
	
	/**
	 * assigns the specified square to the specified player
	 * 
	 * returns:
	 * true - if the player successfully occupies the square
	 * false - otherwise
	 * 
	 * NOTE - takes help from the 'isSquareOccupied()' method above and
	 * 		  'setOccupied()' method from the Square class
	 */
	public boolean setSquare(int row, int col, Player player) {
		
		if(!isSquareOccupied(row, col)) {
			
			board[row][col].setOccupied(player);
			return true;
		}
		
		return false;
	}
	
	/**
	 * returns the state of the game as a string, visually rendered in a board format 
	 */
	public String toString() {
		
		char unOccupied = ' ';
		// board representation
		String boardRep = "";
		int k=0;
		int c=0;
		
		for(int r = 0; r < this.size; r++) {
			
			if(r ==0)
		   for(int i = 0; i < this.size; i++) boardRep += "   " + i;
			boardRep += " \n";
			
			
			for(int i = 0; i < this.size; i++) boardRep += " ___";
			
			boardRep += " \n";
			
			for( c = 0; c < this.size; c++) {
				
				boardRep += "|";
				if(isSquareOccupied(r,c))
					boardRep += " " + board[r][c].toString() + " ";
				else 
					boardRep += " " + unOccupied + " ";
				
			} // end for
			
			boardRep += "|" + k + "\n";
			k++;
		} // end for 
		
		for(int i = 0; i < this.size; i++) boardRep += " ___";
		
		return boardRep;
	}
	
	/**
	 * Added by Varun Sriram 100869591
	 * Method to check if all squares in the board is full
	 * @return returns true if its full and false if it is not.
	 */
	public boolean isFull(){
		for(int i = 0; i<this.size;i++){
			for(int q = 0; q<this.size;q++){
				if(this.board[i][q].checkOccupied() == false){
					return false;
				}
			}
		}
		return true;
	}

}
