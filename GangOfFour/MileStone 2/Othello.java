

import java.util.ArrayList;

/**
 * 
 * @author Joel Prakash 100910302
 * @author Himanish Kaushal 100846382
 * This class contains all the logic behind the Othello game
 */
public class Othello extends Game implements java.io.Serializable {
	
	// HELP_NEEDED is an int which is entered by the user to request help
	// PASS is an int which is entered by the user to pass their turn
	// ZERO is an int which will be used to control which player gets a turn
	final static int HELP_NEEDED = -1,
					PASS = -2,
					ZERO = 0;
	
	// Booleans to indicate if the AI or Human has passed their turn
	private boolean humanPassed = false,
					AIPassed = false;
	
	private int boardSize = getBoard().getSize();
	
	// String constant representing help info
		private static final String HELP = "RULES OF OTHELLO: \n\nO always goes first. \n\nPlayers "
				+ "alternate placing X and O markers on the board on either a 4x4 or 8x8 board  \n"
				+ "There are always four markers always placed on the board at the start of each game \n"
				+ "Both players can place their markers horizontally,vetically or diagonally A Player \n"
				+ "has to always make a move such that the player traps any no of  opponent's maker \n"
				+ "between two of the player's markers. When this happens the player can flip all of \n " 
				+ "the opponent's marker that the player trapped and the opponent's marker shall become \n" 
		        + "the player's marker.In some cases when the player makes a move, the player will see \n"
				+ "that they can flip two different rows or more. Once the board becomes full, the winner \n "
		        + "is decided by which player has the majority of their markers placed on the board. It \n"
				+ "is a draw if both players have the sameno of markers on the board. If a player cannot \n"
		        + "place any where on the board so as the make a flip,the player must pass their turn to \n  "
				+ "the opponent. If the opponent also can't make a turn after theplayer passed the game \n"
		        + "ends and the winner is decided by the player with majority no of markers onthe board, \n"
				+ "with blank spaces on the board counting towards the winner's score."
		        + "\n\n\nHOW THE GAME LOOP WORKS:"
				+ "\n\nThe game loop will print the board, check if there is a winner. It will ask the \n"
				+ "Human player to make a move, it will then check if the move is valid, if it isn't it \n"
				+ "will reprompt the Human player. Otherwise it will continue on.print the board, check \n"
				+ "if there is a winner. If there is a winner,then the game will end,otherwise the AI \n "
				+ "will make a move. The game loop will continue to run until there is a winner or there \n"
				+ "is a draw.\n";
		
		
		// String constants representing the draws and winners
		private static final String DRAWSTRING = "\n\nThe game has finished in a draw!\n\n",
		                            WINNERSTRING = "\n\nThe WINNER is: ",
		                            INVALIDOPTION = "Invalid Option",
		                            PASS_STRING = "The turn was passed.";
	
	public Othello(Board board)
	{
		super(board);
	}
	/**
	 * This function checks the board for all possible empty squares that is valid to make a move to
	 * and returns these squares as a list. 
	 * @param player
	 * @return ArrayList<Square>
	 */
	public ArrayList<Square> validSquares(Player player) {
		
		// the opposing player, who ever it is 
		Player opponent;
		ArrayList<Square> validSquares = new ArrayList<Square>();
		
		
		//getPlayer hold a list of all possible players. So this part finds out 
		// which player has been passed in this function's argument and then it gets
		// that player's opponent
		//This function accomplishes that in the following step:
		//1. For any given square on the board, we assigned 8 directions that represent
		//   the 8 squares that surround any given square on the board.
		//2. Find all the surrounding squares that contain the opponent's piece
		//3. Traverse in that direction until we find a empty square or if we find one
		//   of our pieces before then come back and try a different direction
		if(player.equals(getPlayerAt(0)))
			opponent = getPlayerAt(1);
		else 
			opponent = getPlayerAt(0);
		
		// check rows
		for(int r = 0; r < getBoard().getSize(); r++) {
			//check col
			for(int c = 0; c < getBoard().getSize(); c++) {
				
				
				// finds all the squares on the board that contain the player passed in the argument of this function
				if(getBoard().isSquareOccupied(r, c) && ((OthelloBoard) getBoard()).getSquare(r, c).checkPlayerOccupied().equals(player)) {
					
					// direction represents the 8 squares in a list form, surrounding a give square on the board. 
					//this is true for all squares on the board, except the squares on the edges and corners,
					boolean[] direction = validDirections(r, c, opponent);
					
					Square sq;
					for(int j = 0; j < 8; j++) {
						
						// if that direction had the opponent's piece
						if(direction[j]) {
							
							// we traverse that direction to find the empty square if it has it. Otherwise  function returns null
							sq = traverseDir(r, c, j, player);
							// if there is an empty square
							if(sq != null) {
								
						      // if our list(validSquare) doesn't already contain this particular square then we add it.
								if(!validSquares.contains(sq)) {
									validSquares.add(sq);
									
								} // end if 
							} // end if
						} // end if
					} // end for
				
							
				} // end if
			} // end for
		} // end for
		
		return validSquares;
	}
	
	public ArrayList<int[]> getCoordinates(Player p)
	{
		ArrayList<int[]> array_int = new ArrayList<int[]>();
		ArrayList<Square> sq = this.validSquares(p);
		for(Square s : sq)
		{
			int x[] = {s.getX(),s.getY()};
			array_int.add(x);
		}
		
		return array_int;
	}
	
	
	
	private boolean[] validDirections(int r, int c, Player opponent) {
		
		boolean[] direction = new boolean[8];
		int dirIndex = 0;
		// initalises all the directions to be false. the first step taken by this function is to get
		// all the surrounding squares that have opposing pieces on them than the given square.
		// if that square which surrounds the given square contains no opposing piece then that direction is
		//set to false. otherwise it will be set to true
		for(int i = 0; i < 8; i++)
			direction[i] = false;
		
		
		// iterating through all the squares surrounding one given square on the board, including the given square
		for(int r2 = r-1; r2 < r + 2; r2++) {
			for(int c2 = c-1; c2 < c + 2; c2++) {
				
				
				// only if the square lies inside the board
				if(isInBounds(r2, c2)) {
					// if that square is not the given square.
					if(!(r2 == r && c2 == c)) {
					// if the surrounding places are not empty
						if(getBoard().isSquareOccupied(r2, c2)) { 
							// if the opponent's piece lies in one of them
							if(((OthelloBoard) getBoard()).getSquare(r2, c2).checkPlayerOccupied().equals(opponent)) {
							// set the direction to true, since we will be traversing in that direction
								direction[dirIndex] = true;
								
							} // end if
						} // end if
					} // end if
				} // end if
				
				// once we look at one of the surrounding squares, go to the next one
				if(!(r2 == r && c2 == c)) {
					
					dirIndex++;
				}
				
			} // end for
		} // end for
		
		return direction;
	}
	
	/**
	 * This function traverses in just one direction to find if there is an empty square to place the player's marker
	 * @param r
	 * @param c
	 * @param dirIndex
	 * @param player
	 * @return
	 */
	public Square traverseDir(int r , int c, int dirIndex, Player player) {
		
	//the no that always needs to be added to travel in a particular direction on the board, form one given square
	// to the other
		int incR = calcIncR(dirIndex);
		int incC = calcIncC(dirIndex);
		
		// is in the board
		while(isInBounds(r + incR, c + incC)) {
			// we will continue to travel in that direction as long as we find opposing marker's in that direction
			r = r + incR; c = c + incC;
			
			// if we come across any marker's similar to the player's one to stop. 
			if(getBoard().isSquareOccupied(r, c) && 
					((OthelloBoard) getBoard()).getSquare(r, c).checkPlayerOccupied().equals(player)) {
				
				break;
				
			// if we find an empty space then return that	
			} else if (!getBoard().isSquareOccupied(r, c))
				return ((OthelloBoard) getBoard()).getSquare(r ,c);
			
		} // end while
		
		return null;
	}
	
	/**
	 *This function traverses in a given direction and makes sure that the player's marker is placed in the end of the path
	 *this confirms that there is a sandwich in that path
	 * @param r
	 * @param c
	 * @param dirIndex
	 * @param player
	 * @return
	 */

	public boolean traverseDirForFlip(int r , int c, int dirIndex, Player player) {
		
		int incR = calcIncR(dirIndex);
		int incC = calcIncC(dirIndex);
		
		while(isInBounds(r + incR, c + incC)) {
			
			r = r + incR; c = c + incC;
			
			// at the end of the path if the player's marker is present then return true
			if(getBoard().isSquareOccupied(r, c) && 
					((OthelloBoard) getBoard()).getSquare(r, c).checkPlayerOccupied().equals(player)) {
				
				return true;
			}
			
		} // end while
		
		return false;
	}
	
	/**
	 *This function handles all the flipping in a given direction here the player is actually the opponent's marker in the game 
	 * @param r
	 * @param c
	 * @param dirIndex
	 * @param player
	 */
	
	public void flip(int r , int c, int dirIndex, Player player) {
		
		int incR = calcIncR(dirIndex);
		int incC = calcIncC(dirIndex);
		
		do {
			
			r = r + incR; c = c + incC;
		
			// calls flip square that changes a opponent's maerker residing in one square to that of the player's marker
			((OthelloBoard) getBoard()).flipSquare(r, c, player);
			// keep flipping as long as we encounter the opponent's markers
		} while (getBoard().isSquareOccupied(r, c) && 
				!((OthelloBoard) getBoard()).getSquare(r + incR, c + incC).checkPlayerOccupied().equals(player));
		
	}


	/**
	 * This function deals with all the flipping that get's done once a player places his marker on the board
	 * @param row
	 * @param col
	 * @param player
	 */
	// the starting point for this function is the square that the marker was places
	public void chainReaction(int row, int col, Player player) {
		//this part is similar to validSquare function
		Player opponent;
		
		System.out.println("Row: "+ row);
		System.out.println("Col: "+ col);
		
		if(player.equals(this.getPlayerAt(0)))
			opponent = getPlayerAt(1);
		else 
			opponent = getPlayerAt(0);
		
		boolean[] direction = validDirections(row, col, opponent);
		
		for(int j = 0; j < 8; j++) {
			
			if(direction[j]) {
				// traverses in a given direction flipping all opponet's markers, which then turn into the player's markers
				if(traverseDirForFlip(row, col, j, player)) {
					
					flip(row, col, j, player);
			
				} // end if
			} // end if
		} // end for
	}
	
	/**
	 * This function along with calcIncC is responsible for getting the values needed for getting from one square to the 
	 * other in a particular direction
	 * @param dirIndex
	 * @return
	 */
	
	public int calcIncR(int dirIndex) {
		
		if(dirIndex == 0 || dirIndex == 1 || dirIndex == 2)
			return -1;
		
		if(dirIndex == 3 || dirIndex == 4)
			return 0;
		
		return 1;
	}
	
	/**
	 *This function along with calcIncR is responsible for getting the values needed for getting from one square to the
	 *other in a particular direction
	 * @param dirIndex
	 * @return
	 */
	
    public int calcIncC(int dirIndex) {
		
    	if(dirIndex == 0 || dirIndex == 3 || dirIndex == 5)
    		return -1;
    	
    	if(dirIndex == 1 || dirIndex == 6)
    		return 0;
    	
    	return 1;
		
	}
    
	/**
	 * this function checks to see if the square lies inside the board
	 * @param row
	 * @param col
	 * @return
	 */
	
	public boolean isInBounds(int row, int col) {
		
		return (row >= 0 && row < getBoard().getSize() && col >= 0 && col < getBoard().getSize());
	}
	
	
	/**
	 * This function counts the score of any given player by counting the number of that player's marker's on the board
	 * @param player
	 * @return
	 */
	public int countScore(Player player) {
		
		int count = 0;
		for(int r = 0; r < boardSize; r++)
			for(int c = 0; c < boardSize; c++)
				// count all the player's marker's on the board
				if(((OthelloBoard) getBoard()).getSquare(r, c).checkOccupied() 
						&& ((OthelloBoard) getBoard()).getSquare(r, c).checkPlayerOccupied().equals(player))
					count++;
		
		return count;
	}
	
	/**
	 * Fuction handles the printing of the score of both players in the game
	 */
	public void printScore() {
		
		System.out.println("Score Board: \n"
				+ getPlayerAt(0).toString() + "(O)" + " = " + countScore(getPlayerAt(0)) + "\n"
				+ getPlayerAt(1).toString() + "(X)" + " = " + countScore(getPlayerAt(1))); 
							
	}
	/**
	 * Sets up the board when a new game is played.
	 */
	public void setup(){
		Board b = this.getBoard();
		for(int i =0; i<b.getSize();i++){
			for(int j=0; j<b.getSize();j++){
				
				b.resetSquare(i, j);
			}
		}
		Player human = this.getPlayerAt(0);
		Player ai = this.getPlayerAt(1);
		int i = this.boardSize/2 - 1;
		b.setSquare(i, i, human);
		b.setSquare(i + 1, i + 1,human );
		
		b.setSquare(i + 1, i,ai );
		b.setSquare(i,  i + 1, ai);
		
	}
	
	@Override
	/**
	 * This function call all the other functions in the class inorder to play the game
	 */
	
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
			printScore();
			
			if(AIPassed)
				System.out.println(PASS_STRING);
		   
			// check winner
			winner = this.checkWinner();
			
			// check draw
			draw = this.checkDraw();
			
			if(humanPassed) {
				humanPassed  = false;
			}
			
			// if there is no winner and there is no draw then
			if (winner == null && !draw)
			{
				// reset the index if greater than or equal to total numbers of players
				if(index == this.totalNumPlayers())
					index = ZERO;
				
				System.out.println("*** Human's turn ***");
				// loop until Player makes valid move
				while (true)
				{
					if(humanPassed)
						break;
					// display message
					System.out.println(INVALIDOPTION);
					
					// print board
				
				}
				
				this.printBoard();
				printScore();
				
				if(humanPassed)
					System.out.println(PASS_STRING);
				// increase index to signify next players turn
				
				index++;
			   
				
			    // check winner
				winner = this.checkWinner();
				
				// check draw
				draw = this.checkDraw();
				
				if(AIPassed) {
					AIPassed  = false;
				}
				
				
		// ************* THIS PART IS FOR THE AI
				
				// if there is no winner and there is no draw then
				if (winner == null && !draw)
				{
				    // print board
					//this.printBoard();
					
					System.out.println("*** AI's turn ***");
					
					// loop until Player makes valid move
					while (true)
					{
						/*
						// display message
						System.out.println(INVALIDOPTION);
						
						// print board
						this.printBoard();
						*/
						if(validSquares(getPlayerAt(index)).isEmpty()) {
							
							AIPassed = true;
							break;
						}
							
					}
					
					
					// increase index to signify next players turn
					index++;
				}
			}
		}
		
		// print the final board
		this.printBoard();
		printScore();
		
		
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
		Player human = getPlayerAt(0);
		Player ai = getPlayerAt(1);
		// once both the players pass in a row then the game ends and the winner is found
		if(getBoard().isFull() || (validSquares(human) == null && validSquares(ai) == null)
				|| countScore(human) == 0 || countScore(ai) == 0) {
			
			int humanScore = countScore(human),
				AIScore = countScore(ai);
			
			if(humanScore > AIScore)
				return human;
			else
				return ai;
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
		// print to screen help
		System.out.println(HELP);
	}

	
	/**
	 * Checks if a move is valid or not
	 * 
	 * @return move -> Boolean representing if the move is
	 *                 valid or not.
	 * 
	 */
	
	protected boolean checkMove(int move_row, int move_col,Player player)
	{	
		ArrayList<Square> validSquares = validSquares(player); 
		
	
		
		
		// if the row and column are less then board size and the square isn't occupied, then
		if (isInBounds(move_row, move_col) && validSquares.contains(((OthelloBoard) getBoard()).getSquare(move_row, move_col)))
		{
			// set the appropriate square to be occupied by the player
			this.getBoard().setSquare(move_row, move_col, player);
			//System.out.println("square has been set");
			chainReaction(move_row, move_col, player);
			return true;
		}
		
		
		//System.out.println(move_row + " " + move_col);
		return false;
	}
	
	public void makeAIMove(){
		
		int row, col;
		do {
			
			row = this.getPlayerAt(1).chooseRow();
			col = this.getPlayerAt(1).chooseCol();
		} while (!this.checkMove(row,col,this.getPlayerAt(1)));
	
		this.getBoard().setSquare(row, col, this.getPlayerAt(1));
	}
	
	public boolean canAIPass(){
		if(validSquares(getPlayerAt(1)) == null){
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
		
		int humanScore = countScore(getPlayerAt(0)),
				AIScore = countScore(getPlayerAt(1));
		
		// check if the board is full and there is no winners
		if (b.isFull() && humanScore == AIScore || (humanPassed && AIPassed && humanScore == AIScore && this.checkWinner() == null))
			return true;
		
		return false;
	}

}
