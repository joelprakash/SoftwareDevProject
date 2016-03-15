import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * TicTacToe is a game that requires two players to
 * place/write Xs and Os on a n x n grid. The player who
 * successfully places three of their marks in a 
 * horizontal, vertical, or diagonal row wins the game.
 * If no player has been successful then the game is
 * considered a draw.
 * 
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @author Varun 100869591
 * 
 * @version 2.0
 * @since October 17, 2014
 * 
 */


public class TicTacToe extends Game implements java.io.Serializable
{

	private static final long serialVersionUID = -2077895358012546643L;


	// String constants representing the draws and winners
	private static final String DRAWSTRING = "\n\nThe game has finished in a draw!\n\n",
	                            WINNERSTRING = "\n\nThe WINNER is: ",
	                            INVALIDOPTION = "Invalid Option",
	                            FILENAME = "TicTacToeHelp.txt";
	
	
	// integer constant representing zero, and help needed by human
	private static final int ZERO = 0,
	                         HELPNEEDED = -1;
	
	
	// boolean value for help required by human
	private boolean helpRequired;


	
	/**
	 * Constructor for the TicTacToe class
	 * 
	 * @param board -> Board instance representing the game board
	 * 
	 */

	public TicTacToe(Board board)
	{
		super(board);
		this.helpRequired = false;
	}


	/**
	 * Plays the game
	 * 
	 */

	@Override
	public void play()
	{
		// Player instance for the winner
		Player winner = null;
		
		// boolean instance for draw
		boolean draw = false;
		
		// integer value representing whose turn is it in the game
		int index = ZERO;
		
		
		// main game loop
		while(winner == null && !draw)
		{
			// print board
			this.printBoard();
		   
			// check winner
			winner = this.checkWinner();
			
			// check draw
			draw = this.checkDraw();
			
			
			// if there is no winner and there is no draw then
			if (winner == null && !draw)
			{
				// reset the index if greater than or equal to total numbers of players
				if(index == this.totalNumPlayers())
					index = ZERO;
				
				
				// Player makes a move
				this.makeMove(this.getPlayerAt(index));
				
				
				// increase index to signify next players turn
				index++;
			   
				
			    // check winner
				winner = this.checkWinner();
				
				// check draw
				draw = this.checkDraw();
				
				
				// if there is no winner and there is no draw then
				if (winner == null && !draw)
				{
				    // print board
					this.printBoard();
					
					// Player makes a move
					this.makeMove(this.getPlayerAt(index));
					
					
					// increase index to signify next players turn
					index++;
				}
			}
		}
		
		// print the final board
		this.printBoard();
		
		
		// if there is no draw then output the winner to screen
		if (!draw)
		    System.out.println(WINNERSTRING + winner.toString() + "\n\n");
		
		
		// otherwise output the draw message to screen
		else
			System.out.println(DRAWSTRING);

	}

	
	/**
	 * Checks the winner of the game.
	 * 
	 * @return player -> Player instance representing the winner of 
	 *                   the game or null if no winner.
	 *                   
	 */

	@Override
	protected Player checkWinner()
	{
		// get a list of all the players
		ArrayList<Player> playersList = this.getAllPlayers();
		
		// get the TicTacToeBoard
		TicTacToeBoard b = (TicTacToeBoard)this.getBoard();
		
		// get the board size
		int size = b.getSize();
		
		
		// for each row and col, check who is the winner
		for (int i = 0; i < size; i++)
		{
			
			// for each player check if they are the winner
			for (Player player: playersList)
			{
				if (b.isDiagonalFilledLtoR(player) || b.isDiagonalFilledRtoL(player) || 
					b.isColFilled(i, player) || b.isRowFilled(i, player) )
				    return player;
			}
		}
		
		return null;
	}

	
	/**
	 * Checks if a move is valid or not
	 * 
	 * @return move -> Boolean representing if the move is
	 *                 valid or not.
	 * 
	 */

	
	protected boolean checkMove(Player player)
	{
		
		// set help required to false if it is true
		if (this.helpRequired)
			this.helpRequired = false;
		
		
		// get the size of the board
		int size = this.getBoard().getSize();
		
		
		// get row from Player
		int move_row = player.chooseRow();
		
		
		// check if Human needs help
		if (move_row == HELPNEEDED)
		{
			// print help
			this.printHelp(FILENAME);
			
			// set help required to true
			this.helpRequired = true;
			
			return false;
		}
		
		
		// get column from Player
		int move_col = player.chooseCol();
		
		
		// check if Human needs help
		if (move_col == HELPNEEDED)
		{
			// print help
			this.printHelp(FILENAME);
			
			// set help required to true
			this.helpRequired = true;
			
			return false;
		}
		
		
		// if the row and column are less then board size and the square isn't occupied, then
		else if (move_row < size && move_col < size && !this.getBoard().isSquareOccupied(move_row, move_col))
		{
			// set the appropriate square to be occupied by the player
			this.getBoard().setSquare(move_row, move_col, player);
			
			return true;
		}
		
		
		
		return false;
	}

	
	/**
	 * Checks if there is a draw in the game
	 * 
	 * @return draw -> Boolean representing if there is 
	 *                 a draw in the game.
	 *                 
	 */

	@Override
	protected boolean checkDraw()
	{
		// get the board
		Board b = this.getBoard();
		
		// check if the board is full and there is no winners
		if (b.isFull() && this.checkWinner() == null)
			return true;
		
		return false;
	}
	
	
	/**
	 * Player makes a move and checks if the move is valid
	 * 
	 * @param player -> Player instance to indicate who is playing
	 * 
	 */
	
	private void makeMove(Player player)
	{
		// loop until Player makes valid move
		while (!this.checkMove(player))
		{
			// display message if no help is needed
			if (!this.helpRequired)
			    System.out.println(INVALIDOPTION);
			
			// print board
			this.printBoard();
		}
	}
	

	/**
	 * Responsible for making the move for the MiniMax AI.
	 * This is used for MiniMax AI only.
	 * 
	 * @param currRow -> integer representing the current row
	 * @param currCol -> integer representing the current column
	 * @param player -> Player instance representing the MiniMax AI
	 * @param game -> TicTacToe instance representing the game
	 * 
	 */

	@Override
	public void makeMove(int currRow,int  currCol,Player player, Game game)
	{
		super.makeMove(currRow, currCol, player,game);
	}
	
	
	/**
	 * Finds all the available moves that the MiniMax AI can make.
	 * This is used for MiniMax AI only.
	 * 
	 * @param p -> Player instance representing the player whose
	 *             turn it is.
	 *             
	 * @return array_int -> ArrayList of integer arrays representing
	 *                      all the possible moves the AI can make
	 * 
	 */
	
	public ArrayList<int[]> getCoordinates(Player p)
	{

		ArrayList <int[]> array_int = new ArrayList<int[]>();
		
		// get the board
		Board b = this.getBoard();
	  
		// finds the available moves by checking if the square at row and col is free
		for(int row = 0; row < b.getSize(); row++)
		{
			for(int col = 0; col < b.getSize(); col++)
			{
				if(!b.isSquareOccupied(row, col))
				{
					int [] coordinates = {row, col};
					array_int.add(coordinates);
				}
			}
		}
				
		return array_int;
	}
	

	
	/**
	 * Calculates the heuristic score.
	 * This is used for MiniMax AI only.
	 * 
	 * @return score -> integer representing the heuristic score
	 * 
	 */

	public int countScore2() 
	{
	  int score = 0;
	  
	  // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
	  score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
	  score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
	  score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
	  score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
	  score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
	  score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
	  score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
	  score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
	  
	  return score;
	}

	
	/**
	 * The heuristic evaluation function for the given line of 3 cells.
	 * This is used for MiniMax AI only.
	 * 
	 * 
	 * Possible values are:
	 *                  	AI: +100 for 3 in a line
	 *                  		+10 for 2 in a line
	 *                  		+1 for 1 in a line 
	 *                  	
	 *                  	Human: -100 for 3 in a line
	 *                  		   -10 for 2 in a line
	 *                             -1 for 1 in a line
	 *
	 * 						Default: 0
	 * 
	 * 
	 * @param row1 -> integer representing the first row
	 * @param col1 -> integer representing the first column
	 * @param row2 -> integer representing the second row
	 * @param col2 -> integer representing the second column
	 * @param row3 -> integer representing the third row
	 * @param col3 -> integer representing the third column
	 * 
	 * @return score -> integer representing the heuristic evaluation
	 *                  of the game.
	 *                  
	 */                  
	
	private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) 
	{
		int score = 0;
	  
		Board board = this.getBoard();
	  
		// First cell
		if (board.getSquare(row1, col1).checkPlayerOccupied() instanceof TicTacToeMiniMaxAI)
			score = 1;
		
		else if (board.getSquare(row1, col1).checkPlayerOccupied() instanceof Human)
			score = -1;
	  
	
		// Second cell
		if (board.getSquare(row2, col2).checkPlayerOccupied() instanceof TicTacToeMiniMaxAI) 
		{
			// cell1 is mySeed
			if (score == 1)  
				score = 10;
			
			 // cell1 is oppSeed
			else if (score == -1) 
				return 0;
			
			// cell1 is empty
			else  
				score = 1;
		}
	  
		else if (board.getSquare(row2, col2).checkPlayerOccupied() instanceof Human) 
		{
			// cell1 is oppSeed
			if (score == -1) 
				score = -10;
	     
			// cell1 is mySeed
			else if (score == 1)  
				return 0;
	     
			// cell1 is empty
			else   
				score = -1;
		}
	  
		// Third cell
		if (board.getSquare(row3, col3).checkPlayerOccupied() instanceof TicTacToeMiniMaxAI) 
		{
			// cell1 and/or cell2 is mySeed
			if (score > 0)  
				score *= 11;
	     
			// cell1 and/or cell2 is oppSeed
			else if (score < 0)  
				return 0;
	     
			// cell1 and cell2 are empty
			else
				score = 1;
		}
	  
	  
		else if (board.getSquare(row3, col3).checkPlayerOccupied() instanceof Human) 
		{
			// cell1 and/or cell2 is oppSeed
			if (score < 0)   
				score *= 10;
	     
			// cell1 and/or cell2 is mySeed
			else if (score > 1)
				return 0;
	     
			// both cell1 and cell2 are empty
			else 
				score = -1;
		}
	  
		return score;
	  
	}

	
	
}
