import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Joel Prakash 100910302
 *         
 *
 */
public class Alt_HoundsAndHareAI implements Player {
	private static final String NAME = "AI";
	private int finalRow;
	private int finalCol;
	private Random random = new Random();;
    private Board board;
    private ArrayList<Square> List1;
    private ArrayList<Square> List2;
    
 
  
    /**
     * constructor
     * @param board
     */
    public Alt_HoundsAndHareAI(Board board)
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
    	
    	List1.add(this.board.getBoard()[1][2]);
    	List2.add(this.board.getBoard()[1][1]);
    	List2.add(this.board.getBoard()[1][3]);
    
    	

    	
    
    }
    /**
     * randomly chooses which place to move to if can't move to any of the spaces in any of the list
     * @return
     */
    public int [] findAvailSpace(){
		int [] occupied = new int[2];
		int size = this.board.getSize();
		
		int randRow = random.nextInt(this.board.getHeight());
		int randCol = random.nextInt(this.board.getWidth());
		
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
	
    
    /**
     * new AI strategy other than MiniMax or random
     * @return
     */
    public int[]  AlternateStrategy()
	{
	
		for(Square s : List1)
		{
			if(!board.getBoard()[s.getX()][s.getY()].checkOccupied())
			{
				
				int[] no1 = {s.getX(),s.getY()};
				return no1;
			}
		}
		for(Square x : List2)
		{
			if(!board.getBoard()[x.getX()][x.getY()].checkOccupied())
			{
				int[] no2 = {x.getX(),x.getY()};
				return no2;
			}
		}
		
		
		
		
		
		return findAvailSpace();
	    
		
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

}
    

