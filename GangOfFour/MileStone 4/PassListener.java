

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to listen for the pass button to be pressed
 * 
 * @author Varun 100869591       
 * @author Himanish 100846382
 *
 *
 */
public class PassListener implements ActionListener {
	private Othello othello;
	public  PassListener(Othello o){
		this.othello = o;
	}
	//If the button is pressed the AI will make its move if it is allowed to.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!othello.canAIPass() && othello.checkWinner() == null) {
			
			othello.makeAIMove();
		} else {
			System.out.println("AI passed its turn");
		}
		//Check for a winner	
		if(othello.checkWinner() != null) {
			System.out.println(othello.checkWinner().toString() + " won!");
		}
		
		
	}

}
