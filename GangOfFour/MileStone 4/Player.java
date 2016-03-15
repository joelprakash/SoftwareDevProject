

/**
 * Interface Player Version 1.0
 * Used to define methods for a Human and AI class for a board game. 
 * @author Varun 100869591
 *
 */
public interface Player extends java.io.Serializable  {
	/**
	 * method for player to choose a row index on the board
	 * @return integer 
	 */
	public int chooseRow();
	/**
	 * method for player to choose a column index on the board.
	 * @return integer
	 */
	public int chooseCol();
	
	/**
	 * method for displaying the name of the class as a String
	 * @return String
	 */
	public String toString();
	int[] Coordinates_Hounds();
}