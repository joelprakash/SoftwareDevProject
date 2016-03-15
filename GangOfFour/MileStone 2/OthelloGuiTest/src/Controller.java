import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Controller implements MouseListener {
	private ModelCell model;
	private OthelloPanel view;
	public Controller(ModelCell m, OthelloPanel v){
		this.model = m;
		this.view = v;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Cell Has Been Clicked");
		model.switchCell();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
