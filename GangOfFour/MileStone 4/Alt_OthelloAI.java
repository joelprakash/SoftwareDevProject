
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
public class Alt_OthelloAI implements Player {
	
	
			private static final String NAME = "AI";
			private int finalRow;
			private int finalCol;
			private Random random = new Random();;
		    private Board board;//leave
		    private ArrayList<Square> List1;
		    private ArrayList<Square> List2;
		
		   
		    /**
		     * Constructor
		     */
			public Alt_OthelloAI(Board board)
			{
				this.board=board;
				this.createLists();
			}
			
			 /**
		     * creates a bunch of array list each containing 
		     * a list of square to move to in the board and 
		     * each list has a priority order for the AI to decide where to move to 
		     */
		    public void createLists()
		    {
		    	List1 = new ArrayList<Square>();
		    	List2 = new ArrayList<Square>();
		    	
		    	List1.add(this.board.getBoard()[0][0]);
		    	List1.add(this.board.getBoard()[0][board.getWidth()-1]);
		    	List1.add(this.board.getBoard()[board.getHeight()-1][0]);
		    	List1.add(this.board.getBoard()[board.getHeight()-1][board.getWidth()-1]);
		    			    	
		    }
		 

		    /**
		     * new AI strategy other than MiniMax or random
		     * @return int[]
		     */
			public int[]  AlternateStrategy()
			{
				// compares the list of valid  squares to move to with the priority list everytime to see where it has to move to
				for(Square s : List1)
				{
					if(!board.getBoard()[s.getX()][s.getY()].checkOccupied())
					{
						List1.remove(s);
						int[] no1 = {s.getX(),s.getY()};
						return no1;
					}
				}
				for(Square x : List2)
				{
					if(!board.getBoard()[x.getX()][x.getY()].checkOccupied())
					{
						List2.remove(x);
						int[] no2 = {x.getX(),x.getY()};
						return no2;
					}
				}
				
				return findAvailSpace();
			    
				
			}
			
			/**
		     * randomly chooses which place to move to if can't move to any of the spaces in any of the list
		     * @return int[]
		     */
			private int [] findAvailSpace(){
				int [] occupied = new int[2];
				int size = this.board.getSize();
				
				int randRow = random.nextInt(size);
				int randCol = random.nextInt(size);
				
				if(!this.board.isSquareOccupied(randRow,randCol)){
					occupied[0] = randRow;
					occupied[1] = randCol;
					return occupied;
				}
				
				else{
					
					occupied = this.findAvailSpace();
					return occupied;
				}
				
			}
			
			
			public void chooseSquare()
			{
				
			    int[] st = AlternateStrategy(); 
				
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
