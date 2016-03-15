

import java.util.ArrayList ;

public class Node implements java.io.Serializable
{
	
	//Represents a node in a Minimax tree
	
	//arraylist contains a list of int which map all valid squares to move to as int x,y cordinate in int
	private ArrayList<int[]> arraylist;
	private Board boardcopy;
	//  List all subchildren which are generated depending on valid squares
	private ArrayList<Node> children;
	////reference to the parent node
	private Node parent;
	//each node level in the array tree could be a human player's turn
	private Player player;
	//array tree has 4 levels level (0-4)
	private int level;
	// favor is the minimax score assigned to each node
	int favor;
	// each node is assigned a cordinate from its parent based on the move it made to that coordinate
	int x;
	int y;
	
	
	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	//constructor
	public Node(Board b,Player p,int level,int x , int y)
	{
		this.boardcopy = b;
		this.player = p;
		this.level = level;
		//default value of favor for nodes
		this.favor = 9999;
		this.x=x;
		this.y=y;
		
	}
	
	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public ArrayList<int[]> getArraylist() {
		return arraylist;
	}


	public void setArraylist(ArrayList<int[]> arraylist) {
		this.arraylist = arraylist;
	}


	public Board getBoardcopy() {
		return boardcopy;
	}

	public void setBoardcopy(Board boardcopy) {
		this.boardcopy = boardcopy;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	public int getFavor() {
		return favor;
	}

	
	public void setFavor(int f)
	{
	this.favor = f;
	}
	

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void addchild(Node child)
	{
		child.setParent(this);
		children.add(child);
	}


	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	


}
