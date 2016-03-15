
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener Class to listen for the new game button to be pressed.
 * @author Varun
 *
 */
public class NewGameListener implements ActionListener {
	
	private Othello othello;
		public NewGameListener(Othello o){
		this.othello = o;
	}
//Resets the board when pressed.		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		othello.setup();
	}
	

}
