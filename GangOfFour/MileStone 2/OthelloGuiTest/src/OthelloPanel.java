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
 * @author Varun
 *
 */
public class OthelloPanel extends JPanel implements Observer {
	private Dimension d = new Dimension(50,50);
	private int x,y;

	/*
	 * THERE IS MORE THAT NEEDS TO BE PLACED HERE:
	 * WHAT IS PLACED DEPENDS ON WHAT WE DECIDE TO REPRESENT
	 * THE PIECES ON THE GUI AS WHETHER IT IS A JICON
	 * OR BUFFERED IMAGE OR IF WE WANT TO USE THAT ANIMATOR CLASSES
	 * I MADE A WHILE BACK
	 */
	/**
	 * 
	 * @param x x coordinate on the board
	 * @param y y.coordinate on the board
	 * @param s square the panel is display info from.
	 */
	public OthelloPanel(int x, int y){
		super();
		this.x = x;
		this.y = y;
	
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		//Set perfered size of the panel component.
		setPreferredSize(this.d);
		
	}
	public void setPiece(boolean black){
		if(black){
			this.setBackground(Color.BLACK);
		}
		else{
			this.setBackground(Color.WHITE);
		}
		System.out.println("ModelCell State: "+this.x+" "+this.y+"Has Been Set to: "+black);
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
		this.setPiece((boolean)arg);
		
	}
	//Part of the MVC design pattern needs a mouselistener added.
	public void addController(MouseListener e){
		this.addMouseListener(e);
	}
	
	
	
}
