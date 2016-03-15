/**
 * 
 * @author Joel Prakash 100910302
 * @author Varun 100869591       
 * @author Himanish
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RedoListener implements ActionListener {
	private Game g;
	public RedoListener(Game game){
		this.g = game;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		g.redo();
	}

}