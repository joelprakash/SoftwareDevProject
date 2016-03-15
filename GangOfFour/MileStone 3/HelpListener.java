import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HelpListener implements ActionListener {
	private Game g;
	public HelpListener(Game game){
		this.g = game;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		g.printHelp("OthelloHelp.txt");
	}

}
