
import java.util.Observable;

/**
 * Sample class used as a test before fully porting the game to MVC.
 * @author Varun
 *
 */
public class ModelCell extends Observable {
	private int x;
	private int y;
	private boolean state;
	
	public ModelCell(int w,int h){
		x = w;
		y = h;
		state = false;
	}
	
	public void switchCell(){
		if(state){
			state = false;
		}
		else{
			state = true;
		}
		setChanged();
		notifyObservers(state);
		
	}
	
	
}
