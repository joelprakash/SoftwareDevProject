import java.io.IOException;

import javax.swing.JFrame;

/**
 * Class: Main
 * Description: construct our MVC design. 
 * @author Varun
 *
 */
public class Main {
	// int to store board size
	private static int BOARDSIZE = 4;
	public static void main(String[] args) throws IOException {
	
		// TODO Auto-generated method stub
		//create a new Othello Grid
		OthelloGrid g = new OthelloGrid(BOARDSIZE,BOARDSIZE);
		//Our model will consists of a 2d Array of ModelCells
		ModelCell[][] model = new ModelCell[BOARDSIZE][BOARDSIZE];
		//Our View will consist of a 2d Array of OthelloPanels.
		OthelloPanel[][] view = g.getCells();
		//For Every Model and View we need a single individual controller.
		Controller[][] controllers = new Controller[BOARDSIZE][BOARDSIZE];
		//For each Cell in the board represented
		for(int i =0; i < BOARDSIZE ; i++){
			for(int j=0; j<BOARDSIZE ;j++){
				//Add a new modelcell to the 2d Array of our model
				model[i][j]=new ModelCell(view[i][j].getXCoordinate(),view[i][j].getYCoordinate());
				//Add an observer(View) to that model 
				model[i][j].addObserver(view[i][j]);
				//Create a New controller and store it to our array of controllers.
				controllers[i][j] = new Controller(model[i][j],view[i][j]);
				//Add the new controller to the view.
				view[i][j].addController(controllers[i][j]);
			}
		}
		//Instantiate our frame and its properties.
		JFrame testFrame = new JFrame("TEST");
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setSize(400,400);
		testFrame.setResizable(true);
		//Add the created Panel to the Frame
		testFrame.add(g);
		
	}

}
