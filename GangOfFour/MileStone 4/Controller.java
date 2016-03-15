
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

/**
 * Controller class to handle user input and other game data changes and manipulations during run time.
 * @author Varun
 * @author Joel
 */
public class Controller implements MouseListener {
	//Controller needs all aspects of our model and view.
	private Square model;
	private OthelloPanel view;
	private ScoreLabel score;
	private Othello othello;
	private DeepClone s;
	public Controller(Othello o,Square m, OthelloPanel v,ScoreLabel l){
		this.model = m;
		this.view = v;
		this.othello = o;
		this.score = l;
	}
	@Override
	//When a mouse is clicked
	public void mouseClicked(MouseEvent e) {
		
		//get the players of the game
		Player human = othello.getPlayerAt(0);
		Player aI = othello.getPlayerAt(1);
	
		//Game log to show a cell has been clicked
		System.out.println("Cell Has Been Clicked "+ view.getXCoordinate()+view.getYCoordinate());
		//if move is valid
		if(othello.correctMove(view.getXCoordinate(), view.getYCoordinate(), human)){
		//Human makes a move
			othello.getUndo().push(new OthelloBoard(othello.getBoard()));
		
			
			othello.checkMove(view.getXCoordinate(), view.getYCoordinate(), human);
			
		
		//then if AI can make a move and not pass it will make a move
		if(!othello.canAIPass()&& othello.checkWinner() == null) {
			othello.makeAIMove();
			
			System.out.println("AI Made his move");
			
		} else {
			System.out.println("AI passed its turn");
			
		}
		score.updateScore(othello.countScore(human), othello.countScore(aI));
		
		//Then check the winner.
		if(othello.checkWinner() != null) {
			System.out.println(othello.checkWinner().toString() + " won!");
		}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
