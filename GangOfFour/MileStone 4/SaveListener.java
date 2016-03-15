/**
 *
 * @author Joel Prakash 100910302
 * @author Varun 100869591       
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SaveListener implements ActionListener {
	private Game g;
	private Board board;
	private SaveAndLoad s;
	public SaveListener(Game game){
		this.g = game;
		this.board = game.getBoard();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	     s.saveGame(board,"savefile2.txt");
	}
	

}