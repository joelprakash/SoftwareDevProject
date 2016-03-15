import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;


public class tictactoeAI  implements Player,java.io.Serializable{

	//error in AIsetboard function()
		private static final String NAME = "AI";
		private TicTacToe game;
		private int finalRow;
		private int finalCol;
	    private TicTacToe trueModel;
	    private int ctr=0;
	  
	
	   
	    
	   
		//Constructor
		public tictactoeAI()
		{
			
		}
		
		
		public  void getModel(Game game2)
		{
			this.trueModel = (TicTacToe) game2;
			
		}
		
		

		public Board getBoard() {
			return game.getBoard();
		}
	//creates a true copy of another object and all data structure classes in the object it copies must implement seriable
		public static Object deepClone(Object object)
		{
			
			//converts an object and breaks it down into array bytes
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				//writes the object into a memory or file
				oos.writeObject(object);
				// shell object reconstructs all the pieces together and you get new instance
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(bais);
				
				try {
					//read from a file or memory
					return ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
			
		}
	  
		// this is a recursive function that build a tree given the root node
		public int[] MinimaxStrategy(TicTacToe game2 ,Player player,int level,int alpa,int bete)
		{   
			
			 Player opponent = nextplayer(player);
	        TicTacToe tempGame;
	        
		    int currentScore=0;
		   int[] currentScoreint = {-1,-1,0};
		    int bestRow = 0;
		    int bestCol = 0;
		    int currRow = 0;
		    int currCol = 0;
		    
		  
		    ArrayList<int[]> nextMoves = game2.getCoordinates(player);
		    
		    if ( game2.getBoard().isFull() || level == 0 || (!(game2).checkDraw() &&  game2.checkWinner()!=null)) {
		        currentScore = (game2).countScore2(); 
		        currentScoreint[0] =bestRow;
			    currentScoreint[1] = bestCol;
			    currentScoreint[2] = currentScore;
			     return currentScoreint ;
		    } 
		    else {
		         for (int[] move : nextMoves) {
		         
		        	currRow = move[0];
		        	currCol = move[1];
		        	tempGame = (TicTacToe) deepClone(game2);
		        	tempGame.makeMove(currRow, currCol,player,game2 );
		        	
		        	if (player instanceof tictactoeAI) { // since AI starts on 3 if the modulus is not even then it is AI's Turn
		        						//AI is maximizing player
		               currentScoreint = MinimaxStrategy(tempGame,opponent,level - 1,alpa,bete);
		               currentScore = currentScoreint[2];
		               if (currentScore > alpa) 
		               {
		                  alpa= currentScore;
		                  bestRow = currRow;
		  				  bestCol = currCol;
		  				  
		  				  
		                  
		               }
		        	}
		        	
		               else {  //If level is even it is  human's is minimizing player
		            	int[] store;
		            	store = (MinimaxStrategy(tempGame,player,level - 1,alpa,bete));
		            	currentScore = store[2];
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
			   currentScoreint[2] =(player instanceof tictactoeAI) ? alpa: bete;
			     return currentScoreint ; 
		     
		    }
		     
		}
		//gets Player from the parent and assigns the opposing player to the child node
		public Player nextplayer(Player player)
		{
			
			
			if(player instanceof Human)
			{
				return game.getPlayerAt(1);
			}
			
			else if(player instanceof tictactoeAI )
			{
				return  game.getPlayerAt(0);
			}
			
			return null;
		}
		public void chooseSquare()
		{
			
			
			
			this.game = (TicTacToe)deepClone(trueModel);
		
		
		   
		    int[] st; 
			st = MinimaxStrategy(game, game.getPlayerAt(1),2,Integer.MIN_VALUE+1,Integer.MAX_VALUE-1 );
			finalRow = st[0];
			finalCol = st[1];
		
		}
		
		@Override
		public int chooseRow() {
			// TODO Auto-generated method stub
		
	        chooseSquare();
		
			return this.finalRow;
		}

		@Override
		public int chooseCol() {
			// TODO Auto-generated method stub
			
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
