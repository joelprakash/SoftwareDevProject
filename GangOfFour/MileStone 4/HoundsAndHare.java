import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * VARUN:
 * OK SO I ADDED ALL THE FUNCTIONALITY TO ALLOW LOADING AND RELOADING UNDOING AND REDOING AROUND LINE 230
 * MIGHT WANT TO USE A SWITCH IF NEEDED.
 * All THATS NEEDED IS TO CODE THE METHODS.
 * 
 * 
 * Game Class for Hounds and Hares
 * @author Varun
 * @author Himanish
 */
public class HoundsAndHare extends Game implements java.io.Serializable {
	
	private int finishLine; // The minimum column value the human player has to cross on the board to win
	private boolean helpRequired = false; // boolean to tell if the human player needs assistance
	private boolean undoRequired = false;
	private boolean redoRequired = false;
	private boolean saveRequired = false;
	private boolean loadRequired = false;
	private int harePosX;//XPosition of the hare (human on the board)
	private int harePosY;//Y Position of the hare (human on the board)
	private static final int HELPNEEDED = -1,
            				 REDO = -2,
            				 UNDO = -3,
            				 SAVE = -4,
            				 LOAD = -5;
	
	//Various constant strings for prompting the user (self explainitory)
	private static final String DRAWSTRING = "\n\nThe game has finished in a draw!\n\n",
								WINNERSTRING = "\n\nThe WINNER is: ",
								INVALIDOPTION = "Invalid Option",
								SAVEFILE = "HoundsAndHareFile.txt";
								
	protected Stack<HoundsAndHareBoard> undo;
	protected Stack<HoundsAndHareBoard> redo;
	

	private Stack<int[]> undoHarePos,
	                     redoHarePos;
	/**
	 * Constructor for the HoundsAndHare game.
	 * @param board //the gameBoard for this game
	 */
	public HoundsAndHare(Board board) {
		//Call the super constructor of class Game.
				super(board);
				this.undo = new Stack<HoundsAndHareBoard>();
				this.redo = new Stack<HoundsAndHareBoard>();
				this.undoHarePos = new Stack<int[]>();
				this.redoHarePos = new Stack<int[]>();
				// TODO Auto-generated constructor stub
	}
	
	
	
	
	@Override
	/**
	 * Play method for the game implementing the abstract method from class Game
	 */
	public void play() {
		//Sets up the Board.
				setup();
				//No winner by default.
				Player winner = null;
				//While no winner is declared.
				while(winner == null){
				
					if(winner == null){
						this.getUndo().push(new HoundsAndHareBoard(this.getBoard()));
						this.undoHarePos.push(new int[]{this.harePosX, this.harePosY});
						//print the board
						this.printBoard();
						//AI make a move.
						if(getPlayerAt(1) instanceof Alt_HoundsAndHareAI)
							this.makeHoundMoveWithAltAI();
						
						else if(getPlayerAt(1) instanceof HoundsAndHareMiniMaxAI)
							this.useMiniMax();
						
						else 
							makeHoundMove();
						//update the finish line
						this.findFinishLine();
						//check for a winner.
						winner = this.checkWinner();
					//if no winner is declared
					if(winner == null){
						//print the board
						this.printBoard();
						//wait for the human player to make a move.
						this.waitHumanMove();
						//See fi there is a winner
						winner = this.checkWinner();
						//if no winner is declared
						
						}
					}
					
				}
				//print the board
				this.printBoard();
				//Display the winner.
				System.out.println(WINNERSTRING+winner.toString());
				
	}
	
	/**
	 * Method to check the winner implement from an abstract method in Class Game.
	 * @return the winning Player.
	 */
	@Override
	protected Player checkWinner() {
		// TODO Auto-generated method stub
		//If human has no valid moves AI wins
		if(this.getValidMoves(harePosX, harePosY, this.getPlayerAt(0)).isEmpty()){
			return this.getPlayerAt(1);
		}
		//if human went passed all hounds the human wins.
		else if(harePosY==finishLine){
			return this.getPlayerAt(0);
		}
		
		else{
			return null;
		}
	}
	
	/**
	 * Method to check for a draw implemented from the Abstract Class Game
	 * @return a boolean if there is a draw return true.
	 */
	@Override
	protected boolean checkDraw() {
		// TODO Auto-generated method stub
		return false; //impossible to draw in this game always return false.
	}
	/**
	 * Method to setup the board for play.
	 */
	public void setup(){
		//Get the Human and AI players
		Player human = getPlayerAt(0);
		Player ai = getPlayerAt(1);
		
		//The Hare is always starts on the right most part of the board.
		this.getBoard().setSquare(1, this.getBoard().getWidth()-1, human);
		this.harePosX = 1;
		this.harePosY = this.getBoard().getWidth()-1;
		
		//The three hounds always start at these exact locations on the board in at {[0][1], [1][0],[bottom of board][1] 
		this.getBoard().setSquare(0, 1, ai);
		this.getBoard().setSquare(this.getBoard().getHeight()-1,1, ai);
		this.getBoard().setSquare(1, 0, ai);
		//finish line initially set to 0.
		this.finishLine = 0;
	}
	

	
	/**
	 * Checks move of a player for validity.
	 * @param moveX x position of where player wants to move
	 * @param moveY y position of where player wants to move
	 * @param posX  X position of the players current position
	 * @param posY  y position of the players current position
	 * @param p the player in question
	 * @return true if valid false if not.
	 */
	public boolean checkMove(int moveX, int moveY, int posX, int posY, Player p){
		if(moveX > this.getBoard().getHeight() || moveY > this.getBoard().getWidth())
		{
			return false;
		}
		return this.getValidMoves(posX,posY,p).contains(this.getBoard().getBoard()[moveX][moveY]);
	}
	
	/**
	 * Gets the valid possible moves a player can make.
	 * @param posX current X position of the player
	 * @param posY current Y position of the player
	 * @param p player in question
	 * @return array list of Squares of all the possible moves for the player.
	 */
	public ArrayList<Square> getValidMoves(int posX, int posY, Player p){
		if(p == this.getPlayerAt(0)){
			return this.hareMoves(posX, posY);
		}
		
		return this.houndMoves(posX, posY);
	}
	
	public ArrayList<int[]> getCoordinates(int posX,int posY,Player p)
	{
		ArrayList<int[]> array_int = new ArrayList<int[]>();
		ArrayList<Square> sq = this.getValidMoves(posX,posY,p);

		for(Square s : sq)
		{
			int x[] = {s.getX(),s.getY()};
			array_int.add(x);
		}
		
		return array_int;
	}
	
	
	/**
	 * Returns all the possible moves for a hound (aka the AI).
	 * @param x hound's x position
	 * @param y houds's y position
	 * @return ArrayList of Squares of the hounds possible moves. 
	 */
	public ArrayList<Square> houndMoves(int x ,int y){
		ArrayList<Square> movePackage = new ArrayList<Square>();
		/**
		 * The Hound can only move forward meaning it cant go to a previous column on the board.
		 * There are several Special cases Square on the board that restricts players moves.
		 * The cases are position [0,2],[2,2] 
		 */
		if(x == 0 && y == 2){
			
			if(!this.getBoard().isSquareOccupied(1, 2)){
				movePackage.add(this.getBoard().getBoard()[1][2]);
			}
			
			if(!this.getBoard().isSquareOccupied(0,3)){
				movePackage.add(this.getBoard().getBoard()[0][3]);
			}
		}
		//Special Case [2,2]
		else if(x==2 && y==2){
			if(!this.getBoard().isSquareOccupied(1, 2)){
				movePackage.add(this.getBoard().getBoard()[1][2]);
			}
			
			if(!this.getBoard().isSquareOccupied(2,3)){
				movePackage.add(this.getBoard().getBoard()[2][3]);
			}
		}
		
		else if(x == 1 && y == 1) {
		if(!this.getBoard().isSquareOccupied(0, 1)){
			movePackage.add(this.getBoard().getBoard()[0][1]);
		}
		
		if(!this.getBoard().isSquareOccupied(2,1)){
			movePackage.add(this.getBoard().getBoard()[2][1]);
		}
		
		if(!this.getBoard().isSquareOccupied(1,2)){
			movePackage.add(this.getBoard().getBoard()[1][2]);
		}
		
		} 
		else if (x == 1 & y == 3) {
		
		if(!this.getBoard().isSquareOccupied(0, 3)){
			movePackage.add(this.getBoard().getBoard()[0][3]);
		}
		
		if(!this.getBoard().isSquareOccupied(2,3)){
			movePackage.add(this.getBoard().getBoard()[2][3]);
		}
				
		if(!this.getBoard().isSquareOccupied(1, 4)) {
			movePackage.add(this.getBoard().getBoard()[1][4]);
		}
		
		
		}
		//get all posiible forward positions the hound can move to.
		else{
			if(this.isValidMove(x+1, y)){
				movePackage.add(this.getBoard().getBoard()[x+1][y]);
			}
			if(this.isValidMove(x-1, y)){
				movePackage.add(this.getBoard().getBoard()[x-1][y]);
			}
			if(this.isValidMove(x, y+1)){
				movePackage.add(this.getBoard().getBoard()[x][y+1]);
			}
			if(this.isValidMove(x+1, y+1)){
				movePackage.add(this.getBoard().getBoard()[x+1][y+1]);
			}
			if(this.isValidMove(x-1, y+1)){
				movePackage.add(this.getBoard().getBoard()[x-1][y+1]);
			}
		}
		//return results from search.
		return movePackage;
	}
	/**
	 * Checks all the possible squares for the Hare (aka the Human player).
	 * @param x position of the Hare.
	 * @param y position of the hare.
	 * @return list of possible squares the hare can move to.
	 */
	public ArrayList<Square> hareMoves(int x, int y){
		/**
		 * The Hare can only move in all directions.
		 * There are several Special cases Square on the board that restricts players moves.
		 * The cases are position [0,2],[2,2],[1,1], and [1,3]
		 */
		ArrayList<Square> movePackage = new ArrayList<Square>();
		//Special Case
		if(x == 0 && y == 2){
			
			if(!this.getBoard().isSquareOccupied(1, 2)){
				movePackage.add(this.getBoard().getBoard()[1][2]);
			}
			
			if(!this.getBoard().isSquareOccupied(0,3)){
				movePackage.add(this.getBoard().getBoard()[0][3]);
			}
			
			if(!this.getBoard().isSquareOccupied(0,1)){
				movePackage.add(this.getBoard().getBoard()[0][1]);
			}
		//Special Case
		} else if(x==2 && y==2){
			if(!this.getBoard().isSquareOccupied(1, 2)){
				movePackage.add(this.getBoard().getBoard()[1][2]);
			}
			
			if(!this.getBoard().isSquareOccupied(2,3)){
				movePackage.add(this.getBoard().getBoard()[2][3]);
			}
			
			if(!this.getBoard().isSquareOccupied(2,1)){
				movePackage.add(this.getBoard().getBoard()[2][1]);
			}
		//Special Case
		} else if(x == 1 && y == 1) {
			if(!this.getBoard().isSquareOccupied(0, 1)){
				movePackage.add(this.getBoard().getBoard()[0][1]);
			}
			
			if(!this.getBoard().isSquareOccupied(2,1)){
				movePackage.add(this.getBoard().getBoard()[2][1]);
			}
			
			if(!this.getBoard().isSquareOccupied(1,2)){
				movePackage.add(this.getBoard().getBoard()[1][2]);
			}
			
			if(!this.getBoard().isSquareOccupied(1, 0)) {
				movePackage.add(this.getBoard().getBoard()[1][0]);
			}
		//Special	
		} else if (x == 1 & y == 3) {
			
			if(!this.getBoard().isSquareOccupied(0, 3)){
				movePackage.add(this.getBoard().getBoard()[0][3]);
			}
			
			if(!this.getBoard().isSquareOccupied(2,3)){
				movePackage.add(this.getBoard().getBoard()[2][3]);
			}
			
			if(!this.getBoard().isSquareOccupied(1,2)){
				movePackage.add(this.getBoard().getBoard()[1][2]);
			}
			
			if(!this.getBoard().isSquareOccupied(1, 4)) {
				movePackage.add(this.getBoard().getBoard()[1][4]);
			}
		//If its not special case it freeto move to any available square around it.	
		} else{
			if(this.isValidMove(x+1, y)){
				movePackage.add(this.getBoard().getBoard()[x+1][y]);
			}
			if(this.isValidMove(x-1, y)){
				movePackage.add(this.getBoard().getBoard()[x-1][y]);
			}
			if(this.isValidMove(x, y+1)){
				movePackage.add(this.getBoard().getBoard()[x][y+1]);
			}
			if(this.isValidMove(x, y-1)){
				movePackage.add(this.getBoard().getBoard()[x][y-1]);
			}
			if(this.isValidMove(x+1, y+1)){
				movePackage.add(this.getBoard().getBoard()[x+1][y+1]);
			}
			if(this.isValidMove(x-1, y+1)){
				movePackage.add(this.getBoard().getBoard()[x-1][y+1]);
			}
			if(this.isValidMove(x+1, y-1)){
				movePackage.add(this.getBoard().getBoard()[x+1][y-1]);
			}
			if(this.isValidMove(x-1, y-1)){
				movePackage.add(this.getBoard().getBoard()[x-1][y-1]);
			}
		}
		return movePackage;
		
	}
	
	
	/**
	 * Checks if a Square is available to move to.
	 * @param x position you want to move
	 * @param y position you wanr to move
	 * @return true if valid.
	 */
	public boolean isValidMove(int x,int y){
		//Down cast board to HareHoundBoard.
		HoundsAndHareBoard check = (HoundsAndHareBoard) this.getBoard();
		//Check if it is in bounds of the board.
		if(check.isInBounds(x, y)){
			//check if square is a restricted square.
			if(!(check.getRestrictedSquares().contains(this.getBoard().getBoard()[x][y]))){
				//check if the square is occupied.
				if(!this.getBoard().isSquareOccupied(x, y)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Method to update the finish line.
	 */
	public void findFinishLine(){
		
		for(int w=0;w<this.getBoard().getWidth();w++){
			for(int h=0;h<this.getBoard().getHeight();h++){
				if(this.getBoard().getBoard()[h][w].checkPlayerOccupied() == this.getPlayerAt(1)){
					this.finishLine = w;
					return;
				}
			}
		}
		
		
	}
	/**
	 * Makes the humans move.
	 * @return true if sucessful.
	 */
	public boolean makeHumanMove(){
		Human human =(Human) this.getPlayerAt(0);
		if (this.helpRequired)
			this.helpRequired = false;
		if (this.redoRequired)
			this.redoRequired = false;
		if (this.undoRequired)
			this.undoRequired = false;
		if (this.saveRequired)
			this.saveRequired = false;
		if (this.loadRequired)
			this.loadRequired = false;
		
		int move_row = human.chooseRow();
		
		if (move_row == HELPNEEDED)
		{
			// print help
			this.printHelp("HoundAndHareHelp.txt");
			
			// set help required to true
			this.helpRequired = true;
			
			return false;
		}
		else if(move_row == SAVE){
			SaveAndLoad.saveGame(getBoard(), SAVEFILE);
			this.saveRequired = true;
			return false;
			
		}
		else if(move_row == LOAD){
			load_Board(SaveAndLoad.reloadGame(SAVEFILE));
			this.harePosX = this.findLoadedHarePos(getBoard())[0];
			this.harePosY = this.findLoadedHarePos(getBoard())[1];
			this.loadRequired = true;
			return false;
			
		}
		else if(move_row == UNDO){
			this.undo();
			this.undoRequired = true;
			return false;
			
		}
		else if(move_row == REDO){
			this.redo();
			this.redoRequired = true;
			return false;
		}
		int move_col = human.chooseCol();
		
		
		// check if Human needs help
		if (move_col == HELPNEEDED)
		{
			// print help
			this.printHelp("HoundAndHareHelp.txt");
			
			// set help required to true
			this.helpRequired = true;
			
			return false;
		}
		
		else if(move_col == SAVE){
			SaveAndLoad.saveGame(getBoard(), SAVEFILE);
			this.saveRequired = true;
			return false;
			
		}
		else if(move_col == LOAD){
			load_Board(SaveAndLoad.reloadGame(SAVEFILE));
			this.harePosX = this.findLoadedHarePos(getBoard())[0];
			this.harePosY = this.findLoadedHarePos(getBoard())[1];
			this.loadRequired = true;
			return false;
			
		}
		else if(move_col == UNDO){
			this.undo();
			this.undoRequired = true;
			return false;
			
		}
		else if(move_col == REDO){
			this.redo();
			this.redoRequired = true;
			return false;
			
		}
		
		if(this.checkMove(move_row, move_col, this.harePosX, this.harePosY, human)){
			HoundsAndHareBoard b = (HoundsAndHareBoard) this.getBoard(); 
			b.movePiece(this.harePosX, this.harePosY, move_row, move_col, human);
			this.harePosX = move_row;
			this.harePosY = move_col;
			
			return true;
		}
		
		return false;
	}
	/**
	 * Wait for human to make a valid move.
	 */
	private void waitHumanMove()
	{
		// loop until Player makes valid move
				while (!this.makeHumanMove())
				{
					// display message if no help is needed
					if (!this.helpRequired && !this.loadRequired && !this.saveRequired && !this.undoRequired && !this.redoRequired)
					    System.out.println(INVALIDOPTION);
					
					// print board
					this.printBoard();
				}
	}
	/**
	 * Finds all Hound pieces on the board.
	 * @return returns a 2d array of the coordinate.
	 */
	public int[][] findHounds() {
		
		int[][] houndCoordinates = new int[3][2];
		int i = 0;
		for(int c = 0; c < this.getBoard().getWidth(); c++) {
			for(int r = 0; r < this.getBoard().getHeight(); r++) {
				if(this.getBoard().getBoard()[r][c].checkPlayerOccupied() != null){
				if(this.getBoard().getBoard()[r][c].checkPlayerOccupied().equals(this.getPlayerAt(1))) {
					houndCoordinates[i][0] = r;
					houndCoordinates[i][1] = c;
					i += 1;
				}
				}
			}
		}
		
		return houndCoordinates;
	}
	
	public int[] findHare() {
		
		int[] hareCoordinates = new int[2];
		for(int c = 0; c < this.getBoard().getWidth(); c++) {
			for(int r = 0; r < this.getBoard().getHeight(); r++) {
				if(this.getBoard().getBoard()[r][c].checkPlayerOccupied() != null){
					if(this.getBoard().getBoard()[r][c].checkPlayerOccupied().equals(this.getPlayerAt(0))) {
						hareCoordinates[0] = r;
						hareCoordinates[1] = c;
						
						return hareCoordinates;
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Makes the move for a hound piece.
	 */
	public void makeHoundMove() {
		
		Random rand = new Random();
		boolean noMoves = false;
		int randInt;
		int houndX, houndY, potX, potY;
		
		do {
			randInt = rand.nextInt(3);
			houndX = findHounds()[randInt][0];
			houndY = findHounds()[randInt][1];
		} while (getValidMoves(houndX, houndY, getPlayerAt(1)).isEmpty());
		
		System.out.println(houndX + " " + houndY);
		do {
			
			
			// potential coordinates
			potX = this.getPlayerAt(1).chooseRow();
			potY = this.getPlayerAt(1).chooseCol();
			
			// while the move is not valid
		} while (!checkMove(potX, potY, houndX, houndY, getPlayerAt(1))) ;
			
		System.out.println("3");
		((HoundsAndHareBoard) this.getBoard()).movePiece(houndX, houndY, potX, potY, getPlayerAt(1));
		
	}

	/**
	 * Makes the move for a hound piece.
	 */
	public void makeHoundMoveWithAltAI() {
		
		Random rand = new Random();
		boolean noMoves = false;
		int randInt;
		int houndX, houndY, potX, potY;
		
		do {
			randInt = rand.nextInt(3);
			houndX = findHounds()[randInt][0];
			houndY = findHounds()[randInt][1];
		} while (getValidMoves(houndX, houndY, getPlayerAt(1)).isEmpty());
		
		System.out.println(houndX + " " + houndY);
		do {
			
			
			// potential coordinates
			potX = this.getPlayerAt(1).chooseRow();
			potY = this.getPlayerAt(1).chooseCol();
			
			// while the move is not valid
		} while (!checkMove(potX, potY, houndX, houndY, getPlayerAt(1)));
			
		Player alternateAI =  getPlayerAt(1);
		((Alt_HoundsAndHareAI) alternateAI).resetSearchedFlags();
		
		((HoundsAndHareBoard) this.getBoard()).movePiece(houndX, houndY, potX, potY, getPlayerAt(1));
		
	}
	
public void useMiniMax() {
		
		

	int houndX, houndY, potX, potY;
	
	
		
		

		
		
		// potential coordinates
		potX = this.getPlayerAt(1).chooseRow();
		potY = this.getPlayerAt(1).chooseCol();
		
	
		houndX = this.getPlayerAt(1).Coordinates_Hounds()[0];
		houndY =this.getPlayerAt(1).Coordinates_Hounds()[1];
		
	

	Player MiniMaxAI =  getPlayerAt(1);
	
	
	((HoundsAndHareBoard) this.getBoard()).movePiece(houndX, houndY, potX, potY, MiniMaxAI);
		
	}
	
	public void makeMoveForMinimax(int houndno,int x, int y, Player player,Game game) {
		if(houndno == 0)
		{
			int[] hareCoordinates = new int[2];
			hareCoordinates = findHare();
			((HoundsAndHareBoard) game.getBoard()).movePiece(hareCoordinates[0], hareCoordinates[1], x, y, player);
		}
		
		else if(houndno == 1 || houndno == 2 || houndno == 3)
		{
			int[][] houndCoordinates = findHounds();
			((HoundsAndHareBoard) game.getBoard()).movePiece(houndCoordinates[houndno-1][0], houndCoordinates[houndno-1][1], x, y, player);
		}
	
	}
    
	public int[] getHoundCoordinate(int houndno)
	{
		int[] Coordinate = new int[2];
		System.out.println( "real value :" + houndno);
		if(houndno == 1 || houndno == 2 || houndno == 3)
		{
		
			
			Coordinate[0] = findHounds()[houndno-1][0];
			Coordinate[1] = findHounds()[houndno-1][1];
		}
		return Coordinate;
	}
	
	@Override
	public void undo() {
		if(this.canUndo()){
			
			this.getRedo().push(new HoundsAndHareBoard(this.getBoard()));
			this.redoHarePos.push(new int[]{this.harePosX, this.harePosY});
			
			
			Board b = this.undo.pop();
			if(this.canUndo())
			{
				b = this.undo.pop();
				this.harePosX = this.getHareUndo().pop()[0];
				this.harePosY = this.getHareUndo().pop()[1];
			}
		
			
			
			
			this.load_Board(b);
		}
	}


	@Override
	public void redo() {
		if(this.canRedo()){
			this.getUndo().push(new HoundsAndHareBoard(this.getBoard()));
			this.undoHarePos.push(new int[]{this.harePosX, this.harePosY});
			this.load_Board(this.getRedo().pop());
			this.harePosX = this.getHareRedo().pop()[0];
			this.harePosY = this.getHareRedo().pop()[1];
		}
	}
	
	public Stack<HoundsAndHareBoard> getUndo() {
		return undo;
	}


	public Stack<HoundsAndHareBoard> getRedo() {
		return redo;
	}
	
	public Stack<int[]> getHareUndo()
	{
		return this.undoHarePos;
	}
	
	public Stack<int []> getHareRedo()
	{
		return this.redoHarePos;
	}

	private boolean canUndo(){
		return !this.undo.isEmpty() && !this.undoHarePos.isEmpty();
	}
	
	private boolean canRedo(){
		return !this.redo.isEmpty() && !this.redoHarePos.isEmpty();
	}
	
	
	public void load_Board(Board board)
	{
		Board b = this.getBoard();
		Player human = this.getPlayerAt(0);
		Player ai = this.getPlayerAt(1);
		
		for(int i =0; i<b.getHeight();i++){
			for(int j=0; j<b.getWidth();j++){
				b.resetSquare(i, j);
				
				if(board.getSquare(i, j).checkOccupied() == true)
				{
				Player p =board.getSquare(i, j).checkPlayerOccupied();
				if(p instanceof Human)
				{
				b.setSquare(i, j, human);
				}
				
				else if(p instanceof OthelloMiniMaxAI || p instanceof AI || p instanceof Alt_OthelloAI)
				{
					b.setSquare(i, j, ai);
				}
				
				}
				
				
			
			}
		}
	}
	
	
	private int[] findLoadedHarePos(Board b)
	{
		Player p = null;
		for (int i = 0; i < b.getHeight(); i++)
		{
			for(int j = 0; j < b.getWidth(); j++)
			{
				p = b.getSquare(i, j).checkPlayerOccupied();
				if (p instanceof Human)
					return new int[]{i, j};
			}
		}
		return null;
	}
	
	public int totalMinimaxScore() {
		
		int score = 0;
		int[][] houndCoordinates = findHounds();
		int[] hareCoordinates = findHare();
		
		score += evaluateHoundsPerRow(houndCoordinates);
		score += evaluateHoundsPerColumn(houndCoordinates);
		score += evaluateHoundsInColumn(houndCoordinates);
		score += evaluateHareInColumn(hareCoordinates);
		score += evaluateHareInRow(hareCoordinates);
		
		return score;
	}

	
	
	public int evaluateHoundsPerRow(int[][] houndCoordinates) {
		
		int score = 0;
		
		if(houndCoordinates[0][0] == houndCoordinates[1][0]
				&& houndCoordinates[1][0] == houndCoordinates[2][0]
						&& houndCoordinates[0][0] == houndCoordinates[2][0])
			score -= 100;
		
		else if(houndCoordinates[0][0] == houndCoordinates[1][0]
				|| houndCoordinates[1][0] == houndCoordinates[2][0]
						|| houndCoordinates[0][0] == houndCoordinates[2][0])
			score -= 10;
		
		else
			score += 30;
		
		return score;
	}
	
	public int evaluateHoundsPerColumn(int[][] houndCoordinates) {
		
		int score = 0;
		
		if(houndCoordinates[0][1] == houndCoordinates[1][1]
				&& houndCoordinates[1][1] == houndCoordinates[2][1]
						&& houndCoordinates[0][1] == houndCoordinates[2][1])
			score += 500;
		
		else if(houndCoordinates[0][1] == houndCoordinates[1][1]
				|| houndCoordinates[1][1] == houndCoordinates[2][1]
						|| houndCoordinates[0][1] == houndCoordinates[2][1])
			score += 200;
		
		else
			score += 30;
		
		return score;
		
	}
	
	public int evaluateHoundsInColumn(int[][] houndCoordinates) {
		
		int score = 0;
		for(int houndNum = 0; houndNum < 3; houndNum++) {
			
			int column = houndCoordinates[houndNum][1];
			switch(column) {
			case 1:
				score += 1;
				break;
			case 2:
				score += 10;
				break;
			case 3:
				score += 100;
				break;
			case 4:
				score -= 100;
				break;
			default:
				score += 0;
			}
		}
		return score;
	}
	
	public int evaluateHareInColumn(int[] hareCoordinates) {
		
		int column = hareCoordinates[1];
		int score = 0;
		switch(column) {
		case 1:
			score -= 1;
			break;
		case 2:
			score -= 10;
			break;
		case 3:
			score -= 100;
			break;
		default:
			score += 0;
		}
		return score;
	}
	
	public int evaluateHareInRow(int[] hareCoordinates) {
		
		int row = hareCoordinates[0];
		int score = 0;
		switch(row) {
		case 1:
			score -= 2;
			break;
		case 2:
			score -= 30;
			break;
		case 3:
			score -= 2;
			break;
		default:
			score += 0;
		}
		return score;
	}
 }
