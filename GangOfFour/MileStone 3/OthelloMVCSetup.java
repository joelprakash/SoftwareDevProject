

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class thats sets up the MVC structure of the GUI 
 * along with other aspects of the frame and Game.
 * @author Varun
 *
 */
public class OthelloMVCSetup {
	private Scanner sc = new Scanner(System.in);
	public OthelloMVCSetup(int boardSize){
		ScoreLabel score = new ScoreLabel();
		Scanner s = new Scanner(System.in);
		// TODO Auto-generated method stub
		//create a new Othello Grid
		OthelloGrid g = new OthelloGrid(boardSize,boardSize);
		//Our model will consists of a 2d Array of ModelCells
		//ModelCell[][] model = new ModelCell[BOARDSIZE][BOARDSIZE];
		Board b = new OthelloBoard(boardSize);
		Othello model = new Othello(b);
		Player human = new Human(s);
		model.addPlayer(human);
		Player aI = new OthelloMiniMaxAI();
		int AISelection;
		System.out.println("Please Enter your AI strategy (1 for MiniMax, 2 for Random, 3 for alternate AI ");
		AISelection = sc.nextInt();
		while(AISelection <1 || AISelection>3){
			System.out.println("Please Enter your AI strategy (1 for MiniMax, 2 for Random, 3 for alternate AI ");
			AISelection = sc.nextInt();
		}
		//If minimax
		if(AISelection == 1){
			aI = new OthelloMiniMaxAI();
		}
		//If random
		else if(AISelection == 2){
			aI = new AI(b);
		}
		//if alternate
		else if(AISelection ==3){
			aI= new Alt_OthelloAI(b);
		}
		
		model.addPlayer(aI);

		//Our View will consist of a 2d Array of OthelloPanels.
		OthelloPanel[][] view = g.getCells();
		//For Every Model and View we need a single individual controller.
		Controller[][] controllers = new Controller[boardSize][boardSize];
		//For each Cell in the board represented
		for(int i =0; i < boardSize ; i++){
			for(int j=0; j<boardSize ;j++){
				//Add a new modelcell to the 2d Array of our model
				//model[i][j]=new ModelCell(view[i][j].getXCoordinate(),view[i][j].getYCoordinate());
				//Add an observer(View) to that model 
				//model[i][j].addObserver(view[i][j]);
				model.getBoard().getBoard()[i][j].addObserver(view[i][j]);
				//Create a New controller and store it to our array of controllers.
				controllers[i][j] = new Controller(model,model.getBoard().getBoard()[i][j],view[i][j],score);
				//Add the new controller to the view.
				view[i][j].addController(controllers[i][j]);
			}
		}
		
		//Instantiate our frame and its properties.
		JFrame testFrame = new JFrame("TEST");
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setSize(400,600);
		testFrame.setResizable(true);
		//Add the created Panel to the Frame
		
		//Set up for other GUI elements
		JButton pass = new JButton("PASS");
		pass.addActionListener(new PassListener(model));
		JButton newGame = new JButton("NEW GAME");
		newGame.addActionListener(new NewGameListener(model));
		JButton help = new JButton("HELP");
		help.addActionListener(new HelpListener(model));
		JPanel gameOptionPanel = new JPanel(new GridBagLayout());
		JPanel scorePanel = new JPanel();
		
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy= 0;
		gameOptionPanel.add(newGame,constraints);
		constraints.gridx = 1;
		constraints.gridy= 0;
		gameOptionPanel.add(pass,constraints);
		constraints.gridx = 2;
		constraints.gridy= 0;
		gameOptionPanel.add(help,constraints);
		scorePanel.add(score);
		
		
		gameOptionPanel.setSize(new Dimension(400,25));
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout((LayoutManager) new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(gameOptionPanel);
		mainPanel.add(g);
		mainPanel.add(scorePanel);
		testFrame.add(mainPanel);
		model.setup();
		//testFrame.setVisible(true);
		if(aI instanceof OthelloMiniMaxAI)
			((OthelloMiniMaxAI) aI).getModel(model);
	}
}
