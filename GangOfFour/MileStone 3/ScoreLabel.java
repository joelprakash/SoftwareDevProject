

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
/**
 * Jlabel to display score on screen.
 * @author Varun
 *
 */

public class ScoreLabel extends JLabel{
	
	public ScoreLabel(){
		super("Human 2: AI: 2");
	}
	//Method to update the score.
	public void updateScore(int human, int ai){
		this.setText("Human "+human+" AI: "+ ai);
	}
	
	

}
