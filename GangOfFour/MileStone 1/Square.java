
/**
 * 
 * @author JoelPrakash 100910302
 *Class that represents each cell on the board. 
 */

public class Square 
{
	/**
	 * player instance variable represents the marker on the board.
	 * isOccupied would indicate if that cell is occupied by a marker or not.
	 */

	private Player player;
	private boolean isOccupied;
	private String humanChar = "X", AIChar = "O";
	
	
	public Square()
	{
		/**
		 * initially the cell would be empty
		 */
	
		this.isOccupied = false;
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
	
	public void setOccupied(Player player)
	{
		/**
		 * assigns a marker to a cell
		 */
		this.player = player;
		this.isOccupied = true;
	}
	
	
	@Override
	public String toString() {
		
		if(player instanceof Human)
			return humanChar;
		else 
			return AIChar;
	}

}
