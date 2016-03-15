
import java.util.Random;
/**
 * This Class acts as the AI for both tic-tac-toe game and othello game
 * The AI will make moves by randomly choosing a row and column of the board
 * It will then check to see if its an available space. 
 * @author Varun 100869591
 *
 */
	


public class AI implements Player
{
	private Random random = new Random();
	private static final String NAME = "AI";
	
	//AI needs access to the board to make moves on its own.
	private Board board;
	
	public AI(Board board){
		this.board = board;
	}
	/**
	 * Finds available space on the board for the AI to make a move.
	 * @return int[] which will contain the row and column index of the availble space on the board it found.
	 */
	public int [] findAvailSpace(){
		int [] occupied = new int[2];
		int size = this.board.getSize();
		
		int randRow = random.nextInt(this.board.getHeight());
		int randCol = random.nextInt(this.board.getWidth());
		
		if(!this.board.isSquareOccupied(randRow,randCol)){
			occupied[0] = randRow;
			occupied[1] = randCol;
			return occupied;
		}
		
		else{
			
			occupied = this.findAvailSpace();
			return occupied;
		}
		
	}
	/**
	 * Method for the AI to choose the row index it wants to make a move on the board.
	 * @return int row index for AI to make move on the board
	 */
	public int chooseRow(){
		return this.findAvailSpace()[0];
	}
	/**
	 * method for the AI to choose column index it wants to make a move on board.
	 * @return int col index for AI to make move on the board
	 */
	public int chooseCol(){
		return this.findAvailSpace()[1];
	}
	
	/**
	 * Method to represent the player as a Name string.
	 */
	public String toString()
	{
		return NAME;
	}
	
	
}


