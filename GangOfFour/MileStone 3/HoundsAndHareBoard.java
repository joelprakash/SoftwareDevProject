import java.util.ArrayList;

/**
 * Class for the Special HarHound Board.
 * @author Varun
 * @author Himanish
 */
public class HoundsAndHareBoard extends Board implements java.io.Serializable {
	private static int WIDTH = 5;
	private static int HEIGHT = 3;
	private ArrayList<Square> restrictedSquares;
/**
 * Constructor for the Board.
 */
	public HoundsAndHareBoard() {
		
		super(WIDTH,HEIGHT);
		//Since this board is a hexagon and the representation is of a rectangular2D array the corners of the board must be restricted.
		restrictedSquares = new ArrayList<Square>();
		restrictedSquares.add(this.getBoard()[0][0]);
		restrictedSquares.add(this.getBoard()[0][this.getWidth()-1]);
		restrictedSquares.add(this.getBoard()[this.getHeight()-1][0]);
		restrictedSquares.add(this.getBoard()[this.getHeight()-1][this.getWidth()-1]);
	}
/**
 * Method to get list of restricted Squares
 * @return arraylist of restricted squares
 */
	public ArrayList<Square> getRestrictedSquares() {
		return restrictedSquares;
	}
	
	public void movePiece(int origRow, int origCol, int destRow, int destCol, Player player) {
		
		this.resetSquare(origRow, origCol);
		this.setSquare(destRow, destCol, player);
	}
	
	
	
	/**
	 * this function checks to see if the square lies inside the board
	 * @param row row of square in question
	 * @param col col of square in question.
	 * @return true if in bounds.
	 */
	
	public boolean isInBounds(int row, int col) {
	
		return (row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH);
	}

	
}
