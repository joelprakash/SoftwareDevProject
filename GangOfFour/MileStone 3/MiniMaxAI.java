
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Varun 100869591
 *         Joel Prakash 100910302
 *
 */
public class MiniMaxAI implements Player,java.io.Serializable
{
	//error in AIsetboard function()
	private static final String NAME = "AI";
	private Othello game;
	private int finalRow;
	private int finalCol;
    private Othello trueModel;
	//Constructor
	public MiniMaxAI()
	{
		
	}
	
	public  void getModel(Othello model)
	{
		this.trueModel = model;
		
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
  int ctr=0;
	// this is a recursive function that build a tree given the root node
	public int[] MinimaxStrategy(Othello game2 ,Player player,int level,int alpha,int beta)
	{
        Player opponent = nextplayer(player);
		Othello tempGame;
	    int currentScore;
	    int[] currentScoreint = {-1,-1,0};
	    int bestRow = -1;
	    int bestCol = -1;
	    int currRow = -1;
	    int currCol = -1;
	    ArrayList<int[]> nextMoves = game2.getCoordinates(player);
	    if (nextMoves.isEmpty() || level == 0 || (!(game2).checkDraw() &&  game2.checkWinner()!=null)) {
	        currentScore = (game2).countScore2(); 
	        currentScoreint[0] =currRow;
		    currentScoreint[1] = currCol;
		    currentScoreint[2] = currentScore;
		     return currentScoreint ;
	    } 
	    else {
	         for (int[] move : nextMoves) {
	        	 
	        	currRow = move[0];
	        	currCol = move[1];
	        	tempGame = (Othello) deepClone(game2);
	        	tempGame.makeMove(currRow, currCol,player,game2 );
	        	
	        	if (player instanceof MiniMaxAI) { // since AI starts on 3 if the modulus is not even then it is AI's Turn
	        						//AI is maximizing player
	               currentScoreint = MinimaxStrategy(tempGame,opponent,level - 1,alpha,beta);
	               currentScore = currentScoreint[2];
	               if (currentScore > alpha) 
	               {
	                  alpha= currentScore;
	                  bestRow = currRow;
	  				  bestCol = currCol;
	  				  
	  				  
	                  
	               }
	        	}
	        	
	               else {  //If level is even it is  human's is minimizing player
	            	int[] store;
	            	store = (MinimaxStrategy(tempGame,player,level - 1,alpha,beta));
	            	currentScore = store[2];
	               if (currentScore < beta) {
	            	   beta = currentScore;
	            	   bestRow = currRow;
	            	   bestCol = currCol;
	               }
	            }  
	        	//tempGame = tempGame.restoregame();
	           if (alpha >= beta) break;
	         }
	     }
	         
	    
	    currentScoreint[0] =bestRow;
	    currentScoreint[1] = bestCol;
	    currentScoreint[2] =(player instanceof MiniMaxAI) ? alpha: beta;
	     return currentScoreint ;	
	     
	}
	//gets Player from the parent and assigns the opposing player to the child node
	public Player nextplayer(Player player)
	{
		
		
		if(player instanceof Human)
		{
			return game.getPlayerAt(1);
		}
		
		else if(player instanceof MiniMaxAI )
		{
			return  game.getPlayerAt(0);
		}
		
		return null;
	}
	public void chooseSquare()
	{
		this.game = (Othello)deepClone(trueModel);
		int[] RowCol = MinimaxStrategy(game,game.getPlayerAt(1),3,Integer.MIN_VALUE+1, Integer.MAX_VALUE-1);
		finalRow = RowCol[0];
		//System.out.println("\nFinal Row: \n" + finalRow);
		finalCol = RowCol[1];
		//System.out.println("\nFinal Col: \n" + finalCol);
	}
	
	@Override
	public int chooseRow() {
		// TODO Auto-generated method stub
	   // Random rand = new Random();
	  //  int randomNum = rand.nextInt((3 - 0) + 1) + 0;
        chooseSquare();
	   // return randomNum;
		return this.finalRow;
	}

	@Override
	public int chooseCol() {
		// TODO Auto-generated method stub
		//Random rand = new Random();
	   // int randomNum = rand.nextInt((3 - 0) + 1) + 0;
	  //  return randomNum;
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

}
