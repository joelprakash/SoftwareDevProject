
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * 
 * @author Varun 100869591
 *         Joel Prakash 100910302
 *
 */
public class MiniMaxAI implements Player,java.io.Serializable
{
	//error in AIsetboard function()
	
	private Game game;
	private Board board;
	private Node root;
	//reference to each level of nodes in the tree
	private ArrayList<Node> tier1;
	private ArrayList<Node> tier2;
	private ArrayList<Node> tier3;
	// + infinity for a comp
	private  static final  int MAX_INT = 2147483647;
	// - infinity for a comp
	private  static final  int MIN_INT = -2147483647;
	
	
	
	public void tier1add(Node n)
	{
		this.tier1.add(n);
	}
	
	public void tier2add(Node n)
	{
		this.tier2.add(n);
	}
	
	public void tier3add(Node n)
	{
		this.tier3.add(n);
	}
	
	//Constructor
	public MiniMaxAI(Game game)
	{
		this.game = game;
		this.board = game.getBoard();
	
	}

	


	public Board getBoard() {
		return board;
	}

 // gets the max value for children and returns it to the parent 
	public int getmax(ArrayList<Node> nodelist)
	{
		
		int max= nodelist.get(0).getFavor();
		
		// for any sub child
		for(Node n : nodelist)
		{
			if(max < n.getFavor())
				max = n.getFavor();
			
		}
		return max;
	}
	
	// gets the min value for children and returns it to the parent
	public int getmin(ArrayList<Node> nodelist)
	{
		
		int min= nodelist.get(0).getFavor();
		
		for(Node n : nodelist)
		{
			if(min > n.getFavor())
				min = n.getFavor();
			
		}
		return min;
	}

	// level 2 function that check all the nodes in reference tier2 and gets max value to parent node
	public void Maximiser(Node n)
	{
		if(n.getLevel()==2)
		{
			
				n.setFavor(getmax(n.getChildren()));
			
		}
	}
	
	// level 1 function that check all the nodes in reference tier1 and gets min value to parent node
	public void Minimiser(Node n)
	{
		if(n.getLevel()==1)
		{
			
				n.setFavor(getmin(n.getChildren()));
			
		}
	}
	
	// level 0 function that checks all nodes by using root reference and gets max value to parent node
	public void minimax(Node n)
	{
		if(n.getLevel() ==0 && !(n.getArraylist() == null))
		{
			
				n.setFavor(getmax(n.getChildren()));
		}
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
	
	// if AI can't make any moves AI passes
	public boolean AIpassed(Node n)
	{
		Othello o = new Othello(n.getBoardcopy());
		o.addPlayer(this.game.getPlayerAt(0));
		o.addPlayer(this.game.getPlayerAt(1));
		boolean check = false;
		if(o.validSquares(o.getPlayerAt(1)).isEmpty())
			check = true;
		
		return check;
	}

	// if Humanpasses can't make any moves AI passes
	public boolean Humanpassed(Node n)
	{
		Othello o = new Othello(n.getBoardcopy());
		o.addPlayer(this.game.getPlayerAt(0));
		o.addPlayer(this.game.getPlayerAt(1));
		boolean check = false;
		if(o.validSquares(o.getPlayerAt(0)).isEmpty())
			check = true;
		
		return check;
	}

	//Only if human passes and AI passes in a row then we check victory condition
	public Player checkwin(Node n)
	{
		Othello o = new Othello(n.getBoardcopy());
		o.addPlayer(this.game.getPlayerAt(0));
		o.addPlayer(this.game.getPlayerAt(1));
		
		if(n.getBoardcopy().isFull() || Humanpassed(n) && AIpassed(n))
		{
			int humanScore = o.countScore(o.getPlayerAt(0));
			int AIscore = o.countScore(o.getPlayerAt(1));
			
			if(humanScore > AIscore)
				return o.getPlayerAt(0);
			else
				return o.getPlayerAt(1);
		}
		return null;
	}

	// if not checkWin but neither can make a move then it is a draw
	public boolean checkDraw(Node n)
	{
		boolean check = true;
		Player p;
		if((p= checkwin(n)) == null)
			check = true;
		else 
			check = false;
		return check;
	}
	
	
	public void AIsetboard(Node child,int[] v, Node parent)//makes the child's board how the parent forsaw it
	{
		// gets a copy of  child's parents board 
		OthelloBoard child_board = (OthelloBoard) deepClone(parent.getBoardcopy());
		
			child_board.setSquare(v[0], v[1],child.getPlayer() );//set piece where parent wants it
			
			Game game = new Othello(child_board);
			
//********Generates a error of null pointer exception here where V is null**********************			
			// uses chain reaction to set the board using the Player of the parent
			((Othello) game).chainReaction(v[0], v[1],parent.getPlayer());//propogates chain reaction
			
			
		child.setBoardcopy(((Othello) game).getBoard());//add board to child's board
		
	}
	
	// just builds the root node of the tree and assigns all valid moves it can make into ArrayyList<int[]>
	public Node buildroot()
	{
		OthelloBoard a = (OthelloBoard)getBoard();
		
		OthelloBoard b = (OthelloBoard)deepClone(a);
		
		//creates new game which gets the player list from the original game that the minmaxAI gets
		Game copy = new Othello(b);
		
		copy.addPlayer(this.game.getPlayerAt(0));
		copy.addPlayer(this.game.getPlayerAt(1));
		//get all valid moves in int[] form
		ArrayList<int[]> get_valid = ((Othello) game).getCoordinates(game.getPlayerAt(1));
		int x= 0;
		int y= 0;
		Node rootnode = new Node(b,copy.getPlayerAt(1),0,x,y);
		
		rootnode.setArraylist(get_valid);
		rootnode.setParent(null);
		
		this.root = rootnode;
		
		return root;
	}
	
	
	// this is a recursive function that build a tree given the root node
	public void buildchild(Node parent)
	{
		// the tree should stop in level 3 of the tree , this is the base case
		while(!parent.getArraylist().isEmpty() && parent.getLevel() <3)
		{
		
			ArrayList<int[]> valid_list = parent.getArraylist();
			
			for(int[] v : valid_list)
			{
				//child will be 1 level higher than the parent
				int level = parent.getLevel() + 1;
			
				//gets the child node's board exactly as the parent
				Node child_node = new Node(parent.getBoardcopy(),nextplayer(parent), level ,v[0],v[1]);
				
				//sets the child's board so that it can be different than the parent
				AIsetboard(child_node,v,parent);
				//parents gets reference to child
				parent.addchild(child_node);
				
				
				Othello o = new Othello(child_node.getBoardcopy());
				//sets all the valid moves in child
				ArrayList<int[]> get_valid = o.getCoordinates(child_node.getPlayer());
				child_node.setArraylist(get_valid);
				tierReference(child_node);
				
				//calls again
				buildchild(child_node);
				
				
			}
			
		}
		
	}
	
	//sets up references accesing each node in the tree
	public void tierReference(Node childnode)
	{
		if(childnode.getLevel() == 1)
			tier1.add(childnode);
		
		else if(childnode.getLevel() == 2)
			tier2.add(childnode);
		
		else if(childnode.getLevel() == 3)
			tier3.add(childnode);
			
	}
	
	
	//this nodes assigns a score to all level 3 nodes 
	public void terminal(Node n)
	{
		// if node is in level 3 and is unitialised(9999)
		if(n.getLevel() == 3 && n.getFavor()== 9999)
		{
			Othello o = new Othello(n.getBoardcopy());
			o.addPlayer(this.game.getPlayerAt(0));
			o.addPlayer(this.game.getPlayerAt(1));
			//score in minimax is given by AI score - HumanScore
			int humanScore = o.countScore(o.getPlayerAt(0));
			int AIScore = o.countScore(o.getPlayerAt(1));
			n.setFavor(humanScore - AIScore);
		}
	}
	
	//gets Player from the parent and assigns the opposing player to the child node
	public Player nextplayer(Node node)
	{
		Othello gameclone = (Othello)deepClone(this.game);
		gameclone.addPlayer(this.game.getPlayerAt(0));
		gameclone.addPlayer(this.game.getPlayerAt(1));
		ArrayList<Player> player_list = gameclone.getAllPlayers();
		if(node.getPlayer() instanceof Human)
		{
			return player_list.get(1);
		}
		
		else if(node.getPlayer() instanceof MiniMaxAI)
		{
			return player_list.get(0);
		}
		
		return null;
	}
	
	
		
	public void Assignscore()
	//assumes that the tree is fully fininshed and assigns a scores to all the nodes including the root node
	{
		
		if(!tier3.isEmpty())
		{
		
			for( Node n : tier3)
			{
				
				// if there is no special score set don't override
				if(!n.getChildren().isEmpty() && !specialscore(n))
				{
					terminal(n);
				}
				else
					//cases where nodes other than the level 3 nodes end abbruptly in the tree due to conditions such as:
					//AIwinning, Humanwinning, Ai passed, Humanpaseed, Drwa etc
				specialscore(n);
					
			}
				
		}
		
		if(!tier2.isEmpty())
		{
		
			for( Node n : tier2)
			{
				
				
				if(!n.getChildren().isEmpty() && !specialscore(n))
				{
				     Maximiser(n);
				}
				else
				specialscore(n);
					
			}
				
		}
		
		if(!tier1.isEmpty())
		{
		
			for( Node n : tier1)
			{
				
				
				if(!n.getChildren().isEmpty() && !specialscore(n))
				{
				     Minimiser(n);
				}
				else
				specialscore(n);
					
			}
				
		}
		
		//chooses the max from direct subnodes and assigns that value to root node
		if(root.getChildren().isEmpty())
		{
			minimax(root);
		}
		
		
		
	}
		//covers all special cases where AI favors one way highly over the normal ones or hates it
		public boolean specialscore(Node n)
		{
			boolean check = false;
			
			 Othello o =  new Othello(n.getBoardcopy());
			    o.addPlayer(this.game.getPlayerAt(0));
				o.addPlayer(this.game.getPlayerAt(1));
			 Player p;
			 if(AIpassed(n) && Humanpassed(n))//victory condition
			 {
				 if((p = checkwin(n)).equals(o.getPlayerAt(1)))
				 {
					 n.setFavor(MAX_INT);
					 check = true;
				 }
				 
				 
				 else if((p= checkwin(n)).equals(o.getPlayerAt(0)))
				 {
					 n.setFavor(MIN_INT);
					 check = true;
					 
				 }
				 	 
			 }
			 else if(AIpassed(n))
			 {
				 n.setFavor(-999);
				 check = true;
			 }
			 
			 else if(Humanpassed(n))
			 {
				 n.setFavor(999);
				 check = true;
			 }
			 
			 else if(checkDraw(n))
			 {
				 n.setFavor(-99);
				 check = true;
			 }
			 
			 return check;
		}
		
		
	// returns an array containing the int of the move that the AI has choosen to make
		public int[] chooseSquare()
		{
			
			int[] arr = new int[2];
			buildtree();
			
			ArrayList<Node> list = root.getChildren();
			for(Node n : list )
			{
				if(n.getFavor() == root.getFavor())
				{
				  arr[0] = n.getX(); 
				  arr[1] = n.getY();
				}
			}
			
			return arr;
		
			
		}	
		
		// build everything and assigns score
		public void buildtree()
		{
			Node rootnode = buildroot();
			this.root = rootnode;
			if(!root.getArraylist().isEmpty())
			{
				buildchild(root);
				Assignscore();
			}
		}
	
	
	@Override
	public int chooseRow() {
		// TODO Auto-generated method stub
		return this.chooseSquare()[0];
	}

	@Override
	public int chooseCol() {
		// TODO Auto-generated method stub
	return this.chooseSquare()[1];
	
	}

}
