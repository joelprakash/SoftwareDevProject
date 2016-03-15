/**
 * 
 * @author Joel Prakash 100910302
 * @author Varun 100869591       
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class LoadListener implements ActionListener {
	private Othello g;
	private JFrame frame;
	private SaveAndLoad s;
	public LoadListener(Game game, JFrame j){
		this.g = (Othello) game;
		this.frame=j;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Board b = s.reloadGame("savefile2.txt");
		g.load_Board(b);
		frame.setVisible(true);
	}


}