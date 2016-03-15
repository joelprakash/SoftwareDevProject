import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * Class Version 1.2: OthelloGrid
 * Description: The GUI representation of the othello board updated to support MVC design pattern.
 * DISCLAIMER: THE OTHELLOBOARD REFERENCE WILL PROBABLY GET REMOVED WHEN WE IMPLEMENT MVC ITS THERE TEMPOARILY TO TELL THIS PANEL 
 * WILL DISPLAY INFORMATION REGARDING THE BOARD.
 * 
 * @author Varun
 *
 */
public class OthelloGrid extends JPanel {
	//private 2d array of othello panels to keep track of them.
	private OthelloPanel[][] cells;
	
	public OthelloGrid(int w,int h){
		//Constructer is the same as a JPanel
		super(new GridBagLayout());
		cells = new OthelloPanel[w][h];
		//class specifies constraints for components that are laid out using the GridLayout
		GridBagConstraints constraints = new GridBagConstraints();
		//Place a new Othello Panel in a newly created cell of the grid
		for(int i = 0; i<w; i++){
			for(int j = 0; j<h; j++){
				//Specifies how to distribute extra horizontal space.
				constraints.weightx = 1.0;
				constraints.weighty = 1.0;
				
				//Determine how to fill if the component is smaller than the panel cell.
				constraints.fill = GridBagConstraints.BOTH;
				
				//Specifies the cell containing the leading edge of the component's display area,
				constraints.gridx = i;
				constraints.gridy = j;
				//Add a new othello panel to the array and to the panel.
				cells[i][j] = new OthelloPanel(i,j);
				add(cells[i][j],constraints);
				
			}
		}
		setBorder(BorderFactory.createLineBorder(Color.black)); 

	}
	/**
	 * Method to get the cells of the Grid.
	 * @return 2D array of othelloPanels
	 */
	public OthelloPanel[][] getCells(){
		return this.cells;
	}
}
