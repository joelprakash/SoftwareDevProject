import java.util.Scanner;

/**
 * Setup of Class for the HoundsAndHare Game
 * @author Varun
 *
 */
public class HoundsAndHareSetup {
	//Scanner for the Human Class
	Scanner sc = new Scanner(System.in);
	//Declare a human player
	private Player human = new Human(sc);
	//Declare AI for the game
	private Player ai;
	//Declare a board for the game
	private Board board;
	//Declare a general Game
	private Game game;
	public HoundsAndHareSetup(){
		sc = new Scanner(System.in);
	    human = new Human(sc);
	    System.out.println("Playing Hounds And Hare\n");
		//Declare a Hounds and Hare Board.
		board= new HoundsAndHareBoard();
		//Declare the AI
		int AISelection;
		System.out.println("Please Enter your AI strategy (1 for Random, 2 for alternate AI ,3 for MiniMaxAI ");
		AISelection = sc.nextInt();
		while(AISelection <1 || AISelection>3){
			System.out.println("Please Enter your AI strategy (1 for Random 2 for alternate AI ,3 for MiniMaxAI ");
			AISelection = sc.nextInt();
		}
		//If minimax
		
		//If random
		if(AISelection == 1){
			ai = new AI(board);
		}
		//if alternate
		else if(AISelection ==2){
			ai = new Alt_HoundsAndHareAI(board);
		}
		
		else if(AISelection == 3)
		{
			ai = new HoundsAndHareMiniMaxAI();
		}
		//Declare game as HoundsAndHair
		game = new HoundsAndHare(board);
		// add Players to the game
		game.addPlayer(human);
		game.addPlayer(ai);
		
		if(AISelection == 3)
		{
		((HoundsAndHareMiniMaxAI) ai).getModel(game);
		}
		//Play the Hounds and Hair.
		game.play();
	}
}
