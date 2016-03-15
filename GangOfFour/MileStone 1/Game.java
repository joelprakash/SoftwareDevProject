/**
 * Game is an Abstract class that the subclasses will
 * extend in order to provide implementation of the
 * abstract methods provided.
 * 
 * 
 * @author Jeton Sinoimeri
 * @studentNum 100875046
 * 
 * @version 1.7
 * @created October 14, 2014
 * @modified October 18, 2014
 *
 */


import java.util.ArrayList;
import java.util.List;


public abstract class Game
{
	
	private Board board;                // board instance
	
	private List<Player> allPlayers;   // List of all the Players in the game 
	
	

	/**
	 * Constructor for the Game class
	 * 
	 * @param board -> Board instance representing the game board
	 * @param scanner -> Scanner reference to be used as input
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
	
	public List<Player> getAllPlayers()
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
	
	protected abstract void printHelp();
	
	
	/**
	 * Checks if a move is valid or not
	 * 
	 * @param player -> Player instance representing whose
	 *                  move it is to check.
	 * 
	 * @return move -> Boolean representing if the move is
	 *                 valid or not.
	 * 
	 */
	
	protected abstract boolean checkMove(Player player);
	
	
	/**
	 * Checks if there is a draw in the game
	 * 
	 * @return draw -> Boolean representing if there is 
	 *                 a draw in the game.
	 *                 
	 */
	
	protected abstract boolean checkDraw();

}
