
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Class: Version 1.1 OthelloPanel
 * Description: A Panel to represent a single Cell in the othello board.
 * Modified to support MVC
 * DISCLAIMER: I think the square will get removed later when we implement MVC its just there to keep in account that 
 * this panel will display data concerning a sqaure.
 * @author Varun 100869591
 *
 */
public class OthelloPanel extends JPanel implements Observer {
	private Dimension d = new Dimension(50,50);
	private int x,y;

	/*
	 * 
	 */
	/**
	 * 
	 * @param x x coordinate on the board
	 * @param y y.coordinate on the board
	 * 
	 */
	public OthelloPanel(int x, int y){
		super();
		this.x = x;
		this.y = y;
	
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		//Set perfered size of the panel component.
		setPreferredSize(this.d);
		this.setBackground(Color.GREEN);
	}
	public void setPiece(Player p){
		if(p instanceof Human){
			this.setBackground(Color.BLACK);
		}
		else if(p instanceof AI || p instanceof OthelloMiniMaxAI||p instanceof Alt_OthelloAI){
			this.setBackground(Color.WHITE);
		}
		else{
			this.setBackground(Color.GREEN);
		}
		if(p != null){
		System.out.println("ModelCell State: "+this.x+" "+this.y+"Has Been Set to: "+p.toString());
		}
	}
	
	public int getXCoordinate(){
		return this.x;
	}
	public int getYCoordinate(){
		return this.y;
	}
	//When Observable call update set the piece on the board.
	@Override
	public void update(Observable o, Object arg) {
		this.setPiece((Player)arg);
		
	}
	//Part of the MVC design pattern needs a mouselistener added.
	public void addController(MouseListener e){
		this.addMouseListener(e);
	}
	
	
	
}
