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
    private ArrayList<Square> List3;
    private ArrayList<Square> List4;
    private ArrayList<Square>[] allLists;
    int times = 0;
    private int[] searchedList;
    
    /**
     * constructor
     * @param board
     */
    public Alt_HoundsAndHareAI(Board board)
	{
		this.board=board;
		this.createLists();
		searchedList = new int[4];
		for(int i =0; i < searchedList.length; i++)
			searchedList[i] = 0;
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
    	List3 = new ArrayList<Square>();
    	List4 = new ArrayList<Square>();
    	
    	List1.add(this.board.getBoard()[1][2]);
    	List2.add(this.board.getBoard()[1][1]);
    	List2.add(this.board.getBoard()[1][3]); 
    	List3.add(this.board.getBoard()[0][3]); 
    	List3.add(this.board.getBoard()[2][3]);
    	List3.add(this.board.getBoard()[0][1]);
    	List3.add(this.board.getBoard()[2][1]);
    	List4.add(this.board.getBoard()[0][2]);
    	List4.add(this.board.getBoard()[2][2]);
    }
    
    /**
     * randomly chooses which place to move to if can't move to any of the spaces in any of the list
     * @return int []
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
     * @return int[]
     */
    public int[]  AlternateStrategy()
	{
    	times++;
		
    	if(searchedList[0] < List1.size()) {
			for(Square s : List1)
			{
				searchedList[0]++;
				if(!board.getBoard()[s.getX()][s.getY()].checkOccupied())
				{
					
					int[] coordinates = {s.getX(),s.getY()};
					return coordinates;
				}
			}
			
    	}
		
    	if(searchedList[1] < List2.size()) {
			for(Square s : List2)
			{
				searchedList[1]++;
				if(!board.getBoard()[s.getX()][s.getY()].checkOccupied())
				{
					
					int[] coordinates = {s.getX(),s.getY()};
					return coordinates;
				}
			}
			
    	}
    	
    	if(searchedList[2] < List3.size()) {
			for(Square s : List3)
			{
				searchedList[2]++;
				if(!board.getBoard()[s.getX()][s.getY()].checkOccupied())
				{
					
					int[] coordinates = {s.getX(),s.getY()};
					return coordinates;
				}
			}
			
    	}
    	
    	if(searchedList[3]< List4.size()) {
			for(Square s : List4)
			{
				searchedList[3]++;
				if(!board.getBoard()[s.getX()][s.getY()].checkOccupied())
				{
					
					int[] coordinates = {s.getX(),s.getY()};
					return coordinates;
				}
			}
			
    	}
		if(times%10000 == 0)
			System.out.println("2");
		
		
		
		return findAvailSpace();
	    
		
	}
	
	public void resetSearchedFlags() {
		
		for(int x = 0; x < searchedList.length; x++)
			searchedList[x] = 0;
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
    

