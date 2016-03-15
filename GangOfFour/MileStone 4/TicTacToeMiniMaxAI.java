import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Joel Prakash 100910302
 *         
 *
 */
public class TicTacToeMiniMaxAI  implements Player,java.io.Serializable{

	
		private static final String NAME = "AI";
		private TicTacToe game;
		private int finalRow;
		private int finalCol;
	    private TicTacToe trueModel;
	    private int ctr=0;
	    private DeepClone clone;
	
	   
	    
	   
	    /**
	     * Constructor
	     */
		public TicTacToeMiniMaxAI()
		{
			
		}
		
		
		/**
		 * this function gets the game that is being played from the mainGame
		 * @param game2
		 */
		public  void getModel(Game game2)
		{
			this.trueModel = (TicTacToe) game2;
			
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
		 * @param player
		 * @param level
		 * @param alpa
		 * @param bete
		 * @return int[]
		 */
		public int[] MinimaxStrategy(TicTacToe game2 ,Player player,int level,int alpa,int bete)
		{   
			//the opposite player than the parent
			 Player opponent = nextplayer(player,game2);
	        TicTacToe tempGame;
	        
		    int currentScore=0;
		   int[] currentScoreint = {-1,-1,0};
		   // the row/col that it returns to the game in main or when it reaches a gameover or level zero senario
		    int bestRow = 0;
		    int bestCol = 0;
		    //current row/col for each node in tree
		    int currRow = 0;
		    int currCol = 0;
		    
		    // the parent sees all the valid moves and uses it to create child nodes in the recursive tree like structure
		    ArrayList<int[]> nextMoves = game2.getCoordinates(player);
		    // if level =0 or if the game is over
		    if ( game2.getBoard().isFull() || level == 0 || (!(game2).checkDraw() &&  game2.checkWinner()!=null)) {
		    	// the score is determined by AIscore - HumanScore, each of AI or Human score is determined by how many
		    	//of each player tokens of that kind are on the board
		        currentScore = (game2).countScore2(); 
		        currentScoreint[0] =bestRow;
			    currentScoreint[1] = bestCol;
			    currentScoreint[2] = currentScore;
			     return currentScoreint ;
		    } 
		    else {
		    	 //if game is not over or not level zero
		         for (int[] move : nextMoves) {
		         
		        	currRow = move[0];
		        	currCol = move[1];
		        	// creates a clone so as to not disturb the game in the main class while the AI tries to play some moves to figure
		        	//out the move that it will make
		        	tempGame = (TicTacToe) clone.deepClone(game2);
		        	// makes the move and sets the clone board
		        	tempGame.makeMove(currRow, currCol,player,game2 );
		        	
		        	if (player instanceof TicTacToeMiniMaxAI) { // since AI starts on 3 if the modulus is not even then it is AI's Turn
		        						//AI is maximizing player
		        		//goes to the next level in the tree, makes child
		               currentScoreint = MinimaxStrategy(tempGame,opponent,level - 1,alpa,bete);
		               currentScore = currentScoreint[2];
		             //does the pruning
		               if (currentScore > alpa) 
		               {
		                  alpa= currentScore;
		                  bestRow = currRow;
		  				  bestCol = currCol;
		  				  
		  				  
		                  
		               }
		        	}
		        	
		               else {  //If level is even it is  human's is minimizing player
		            	int[] store;
		            	//goes to the next level in the tree, makes child
		            	store = (MinimaxStrategy(tempGame,player,level - 1,alpa,bete));
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
			   currentScoreint[2] =(player instanceof TicTacToeMiniMaxAI) ? alpa: bete;
			     return currentScoreint ; 
		     
		    }
		     
		}
		
		/**
		 * gets Player from the parent and assigns the opposing player to the child node
		 * @param player
		 * @param game
		 * @return player
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
		 * Calls the minimax function
		 */
		public void chooseSquare()
		{
			
			
			
			this.game = (TicTacToe)clone.deepClone(trueModel);
		
		
		   
		    int[] st; 
			st = MinimaxStrategy(game, game.getPlayerAt(1),2,Integer.MIN_VALUE+1,Integer.MAX_VALUE-1 );
			finalRow = st[0];
			finalCol = st[1];
		
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
		/**
		 * Method to represent the player as a Name string.
		 */
		@Override
		public String toString()
		{
			return NAME;
		}


		@Override
		public int[] Coordinates_Hounds() {
			// TODO Auto-generated method stub
			return null;
		}


}
