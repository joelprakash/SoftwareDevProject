import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Game is an Abstract class that the subclasses will
 * extend in order to provide implementation of the
 * abstract methods provided.
 * 
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 2.0
 * @since October 14, 2014
 *
 */


public abstract class Game implements java.io.Serializable
{
	
	private Board board;                    // board instance
	
	private ArrayList<Player> allPlayers;   // List of all the Players in the game 
	
	

	/**
	 * Constructor for the Game class
	 * 
	 * @param board -> Board instance representing the game board
	 * 
	 */
	
	public Game(Board board)
	{
		this.board = board;
		this.allPlayers = new ArrayList<Player>();
	}
	

	/**
	 * Getter for the game board
	 * 
	 * @return the board -> Board instance representing the
	 *                      playing board.
	 *                      
	 */
	
	public Board getBoard()
	{
		return board;
	}

	public void makeMove(int currRow,int  currCol,Player player, Game game)
	{		
		this.getBoard().setSquare(currRow, currCol, player);
		
	}

	/**
	 * Mutator for the game board
	 * 
	 * @param board -> Board instance representing the
	 *                 playing board.
	 *                 
	 */
	
	public void setBoard(Board board)
	{
		this.board = board;
	}
	
	
	/**
	 * Add a Player instance to the game.
	 * 
	 * @param player -> Player instance to be added
	 *                  to the game.
	 *                  
	 */
	
	public void addPlayer(Player player)
	{
		this.allPlayers.add(player);
	}
	
	
	/**
	 * Gets a list of all the players in the game.
	 * 
	 * @return allPlayers -> List instance of all the players
	 *                       in the game
	 *                        
	 */
	
	public ArrayList<Player> getAllPlayers()
	{
		return this.allPlayers;
	}
	
	
	/**
	 * The total number of players in the game.
	 * 
	 * @return size -> Integer representing the total number
	 *                 of players in the game.
	 *                 
	 */
	
	public int totalNumPlayers()
	{
		return this.allPlayers.size();
	}
	
	
	/**
	 * Gets a Player instance at a specific index location
	 * 
	 * @param index -> Integer representing the index position of
	 *                 the Player in the List.
	 *                 
	 * @return player -> Player instance at that specific index in
	 *                   the list.
	 *                   
	 */
	
	public Player getPlayerAt(int index)
	{
		return this.allPlayers.get(index);
	}
	
	
	/**
	 * Plays the game
	 * 
	 */
	
	public abstract void play();
	
	
	/**
	 * Prints the board
	 * 
	 */
	
	protected void printBoard()
	{
		 System.out.println(this.board.toString());
	}
	
	
	/**
	 * Checks the winner of the game.
	 * 
	 * @return player -> Player instance representing the winner of 
	 *                   the game.
	 *                   
	 */
	
	protected abstract Player checkWinner();
	
	
	/**
	 * Prints help
	 * 
	 */
	
	protected void printHelp(String fileName)
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String help = "\n\n";
			
			for (String x = in.readLine(); x != null; x = in.readLine())
				help += x + "\n";
			
			help += "\n\n";
			
			System.out.println(help);
			in.close();
			
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Cannot find file.");
		}
		
		catch (IOException io)
		{
			System.out.println("Cannot close BufferedReader");
		}
		
	}
	
	
	/**
	 * Checks if there is a draw in the game
	 * 
	 * @return draw -> Boolean representing if there is 
	 *                 a draw in the game.
	 *                 
	 */
	
	protected abstract boolean checkDraw();


}
