/**
 * @author Himanish
 * @author Joel Prakash 100910302
 * @author Varun 100869591       
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;




public class UndoListener implements ActionListener {
	private Othello g;
	
	public UndoListener(Game game ,JFrame j){
		this.g = (Othello) game;
	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		g.undo();
		
		
		
	}

}