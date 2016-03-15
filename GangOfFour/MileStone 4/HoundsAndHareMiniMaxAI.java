import java.util.ArrayList;
import java.util.Random;


public class HoundsAndHareMiniMaxAI  implements Player,java.io.Serializable
{
	private static final String NAME = "AI";
	private HoundsAndHare game;
	private int finalRow;
	private int finalCol;
	private int finalHound;
    private HoundsAndHare trueModel;
    private int ctr=0;
    private DeepClone clone;
    private  int rand;
   
    
   
    /**
     * Constructor
     */
	public HoundsAndHareMiniMaxAI()
	{
		
	}
	
	
	/**
	 * this function gets the game that is being played from the mainGame
	 * @param game2
	 */
	public  void getModel(Game game2)
	{
		this.trueModel = (HoundsAndHare) game2;
		
	}
	
	
	/**
	 * this function get's board from truelModel which is a reference to the main game
	 * @return Board
	 */
	public Board getBoard() {
		return game.getBoard();
	}

  
	/**
	 * Level indicates the depth of the game. This uses MiniMax algorithm and alpha beta pruning
	 * @param game2
	 * @param houndno 
	 * @param player
	 * @param level
	 * @param alpha
	 * @param beta
	 * @return int[]
	 */
	/* if hound no is 0 then its a rabbit
	 * if 1 then its hound 1
	 * if 2 then its hound 2
	 * if 3 then its hound 3
	 */
	
	
	
	public int[] MinimaxStrategy(HoundsAndHare game2,int houndno,Player player,int level,int alpa,int bete)
	{   
		//the opposite player than the parent
		 Player opponent = nextplayer(player,game2);
        HoundsAndHare tempGame;
        
	    int currentScore=0;
	   int[] currentScoreint = {-1,-1,0,-1};
	   // the row/col that it returns to the game in main or when it reaches a gameover or level zero senario
	    int bestRow = -1;
	    int bestCol = -1;
	   
	    //current row/col for each node in tree
	    int currRow = -1;
	    int currCol = -1;
	    ArrayList<int[]> nextMoves = new ArrayList<int[]>();
	    int[][] findHounds = game2.findHounds();
	    int[] findHare = game2.findHare();
	  
	    if(player instanceof Human)
	    {
	    // the parent sees all the valid moves and uses it to create child nodes in the recursive tree like structure
	    nextMoves = game2.getCoordinates(findHare[0],findHare[1],player);
	    }
	    else 
	    {
	    // the parent sees all the valid moves and uses it to create child nodes in the recursive tree like structure
	  
	    nextMoves = game2.getCoordinates(findHounds[houndno-1][0],findHounds[houndno-1][1],player);
	    }
	   
	    
	    // if level =0 or if the game is over
	    if ( level == 0 || game2.checkWinner()!=null) {
	    	// the score is determined by AIscore - HumanScore, each of AI or Human score is determined by how many
	    	//of each player tokens of that kind are on the board
	        currentScore = (game2).totalMinimaxScore(); 
	        currentScoreint[0] =bestRow;
		    currentScoreint[1] = bestCol;
		    currentScoreint[2] = currentScore;
		    currentScoreint[3] = houndno;
		     return currentScoreint ;
	    } 
	    else {
	    	 //if game is not over or not level zero
	         for (int[] move : nextMoves) {
	         
	        	currRow = move[0];
	        	currCol = move[1];
	        	// creates a clone so as to not disturb the game in the main class while the AI tries to play some moves to figure
	        	//out the move that it will make
	        	tempGame = (HoundsAndHare) clone.deepClone(game2);
	        	// makes the move and sets the clone board
	        	if(player instanceof Human)
	        	{
	        	tempGame.makeMoveForMinimax(0,currRow, currCol,player,game2 );
	        	}
	        	else
	        	{
	        		tempGame.makeMoveForMinimax(houndno,currRow, currCol,player,game2 );
	        	}
	        	
	        	if (player instanceof HoundsAndHareMiniMaxAI ) { // since AI starts on 3 if the modulus is not even then it is AI's Turn
	        						//AI is maximizing player
	        		//goes to the next level in the tree, makes child
	        		
	        		
	        		currentScoreint = MinimaxStrategy(tempGame,houndno,opponent,level-1,alpa,bete);
	        		
	        		
	        		
	               currentScore = currentScoreint[2];
	             //does the pruning
	               if (currentScore > alpa) 
	               {
	                  alpa= currentScore;
	                  bestRow = currRow;
	  				  bestCol = currCol;
	  				
	  				  
	  				  
	                  
	               }
	        	}
	        	
	               else if(player instanceof Human) {  //If level is even it is  human's is minimizing player
	            	int[] store;
	            	store = (MinimaxStrategy(tempGame,houndno,player,level - 1,alpa,bete));
	            	currentScore = store[2];
	            	//does pruninig
	               if (currentScore < bete) {
	            	   bete = currentScore;
	            	   bestRow = currRow;
	            	   bestCol = currCol;
	            	   
	               }
	             
	               }
	           if (alpa >= bete) break;
	               }  
	         
	 	   currentScoreint[0] =bestRow;
		   currentScoreint[1] = bestCol;
		   // Since AI's are maximising nodes and Humans are minimizing nodes
		   currentScoreint[2] =(player instanceof HoundsAndHareMiniMaxAI) ? alpa: bete;
		   currentScoreint[3] = houndno;
		     return currentScoreint ; 
	     
	    }
	     
	}
	
	/**
	 * gets Player from the parent and assigns the opposing player to the child node
	 * @param player
	 * @param game
	 * @return Player
	 */
	public Player nextplayer(Player player,Game game)
	{
		
		
		if(player instanceof Human)
		{
			return game.getPlayerAt(1);
		}
		
		else if(player instanceof TicTacToeMiniMaxAI )
		{
			return  game.getPlayerAt(0);
		}
		
		return null;
	}
	/**
	 * 
	 */
	public void chooseSquare()
	{
		  Random rand = new Random();

		   int houndno = rand.nextInt(3) + 1;
		
		
		this.game = (HoundsAndHare)clone.deepClone(trueModel);
	
	
	   
	    int[] st; 
	 
	   
		st = MinimaxStrategy(game,3,game.getPlayerAt(1),3,Integer.MIN_VALUE+1,Integer.MAX_VALUE-1 );
		finalRow = st[0];
		finalCol = st[1];
		finalHound = houndno;
	    
	}
	
	@Override
	/**
	 * chooses the row
	 */
	public int chooseRow() {
		
		 //only called once when chooseRow() is called and initializes both Row and Col to finalRow and finalCol
        chooseSquare();
	
		return this.finalRow;
	}

	@Override
	/**
	 * chooses the col
	 */
	public int chooseCol() {
	
		
	    return this.finalCol;
	
	}
	
	@Override
	public int[] Coordinates_Hounds()
	{
		
		return trueModel.getHoundCoordinate(finalHound);
	}
	/**
	 * Method to represent the player as a Name string.
	 */
	@Override
	public String toString()
	{
		return NAME;
	}


	
}
