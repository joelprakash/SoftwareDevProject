
import java.util.Observable;


/**
 *Class that represents each cell on the board. 
 *
 * For MileStone 2 all the changes made to Sqaure were allow for MVC.
 * 
 * @author Varun
 * @author JoelPrakash 100910302
 *
 */
public class Square extends Observable implements java.io.Serializable 
{
	/**
	 * player instance variable represents the marker on the board.
	 * isOccupied would indicate if that cell is occupied by a marker or not.
	 */

	private Player player;
	private boolean isOccupied;
	private String humanChar = "X", AIChar = "O";
	int x;
	int y;
	
	
	public Square(int x, int y)
	{
		/**
		 * initially the cell would be empty
		 */
		this.x = x;
		this.y = y;
	
		this.isOccupied = false;
	}
	
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public boolean checkOccupied()
	{
		/**
		 * the cell currently has a marker
		 */
		return this.isOccupied;
	}
	
	public Player checkPlayerOccupied()
	{
		/**
		 * returns the marker currently residing in the cell.
		 */
		return this.player;
	}
	/**
	 * changed to support mvc by notifying observing views.
	 * @param player
	 */
	public void setOccupied(Player player)
	{
		/**
		 * assigns a marker to a cell
		 */
		this.player = player;
		this.isOccupied = true;
		setChanged();
		notifyObservers(this.player);
	}
	
	public void unOccupy(){
		this.isOccupied = false;
		this.player = null;
		setChanged();
		notifyObservers(this.player);
	}
	
	@Override
	public String toString() {
		
		if(player instanceof Human)
			return humanChar;
		else 
			return AIChar;
	}

}
