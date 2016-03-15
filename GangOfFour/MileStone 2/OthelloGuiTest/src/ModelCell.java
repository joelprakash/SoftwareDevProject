import java.util.Observable;


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
