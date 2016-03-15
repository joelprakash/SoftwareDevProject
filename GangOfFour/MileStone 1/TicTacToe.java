/**
 * TicTacToe is a game that requires two players to
 * place/write Xs and Os on a n x n grid. The player who
 * successfully places three of their marks in a 
 * horizontal, vertical, or diagonal row wins the game.
 * If no player has been successful then the game is
 * considered a draw.
 * 
 * 
 * @author Jeton Sinoimeri
 * @studentNum 100875046
 * 
 * @author Varun
 * @studentNum 100869591
 * 
 * @version 1.7
 * @created October 17, 2014
 * @modified October 20, 2014
 * 
 */


import java.util.List;



public class TicTacToe extends Game
{
	
	// String constant representing help info
	private static final String HELP = "RULES OF TIC-TAC-TOE: \n\nX always goes first. \n\nPlayers "
			+ "alternate placing Xs and Os on the board until either (a) one player has three in "
			+ "a row, \nhorizontally, vertically or diagonally; or (b) all squares are filled. \n"
			+ "If a player is able to draw three Xs or three Os in a row, that player wins. If "
			+ "all the squares \nare filled and neither player has won, the game is considered a "
			+ "draw. \n\n\nHOW THE GAME LOOP WORKS: \n\nThe game loop will print the board, check "
			+ "if if there is a winner. It will ask the Human player \nto make a move, it will then "
			+ "check if the move is valid, if it isn't it will reprompt the Human \nplayer. Otherwise "
			+ "it will continue on, print the board, check if there is a winner. If there is \na winner, "
			+ "then the game will end, otherwise the AI will make a move. \n\nThe game loop will "
			+ "continue to run until there is a winner or there's a draw.";
	
	
	// String constants representing the draws and winners
	private static final String DRAWSTRING = "\n\nThe game has finished in a draw!\n\n",
	                            WINNERSTRING = "\n\nThe WINNER is: ",
	                            INVALIDOPTION = "Invalid Option";
	
	
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
		List<Player> playersList = this.getAllPlayers();
		
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
	 * Prints help
	 * 
	 */
	
	@Override
	protected void printHelp()
	{
		System.out.println(HELP);
	}

	
	/**
	 * Checks if a move is valid or not
	 * 
	 * @return move -> Boolean representing if the move is
	 *                 valid or not.
	 * 
	 */

	@Override
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
			this.printHelp();
			
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
			this.printHelp();
			
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
	

}
