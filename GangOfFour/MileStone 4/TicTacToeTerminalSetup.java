import java.util.Scanner;


public class TicTacToeTerminalSetup {
	Scanner sc = new Scanner(System.in);
	//Declare a human player
	private Player human = new Human(sc);
	//Declare AI for the game
	private Player ai;
	//Declare a board for the game
	private Board board;
	//Declare a general Game
	private Game game;
	public TicTacToeTerminalSetup(){
		sc = new Scanner(System.in);
	    human = new Human(sc);
	    System.out.println("Playing TicTacToe\n");
		//Declare a board of size 3
		board= new TicTacToeBoard(3);
		//Declare the AI
		int AISelection;
		System.out.println("Please Enter your AI strategy (1 for MiniMax, 2 for Random, 3 for alternate AI ");
		AISelection = sc.nextInt();
		while(AISelection <1 || AISelection>3){
			System.out.println("Please Enter your AI strategy (1 for MiniMax, 2 for Random, 3 for alternate AI ");
			AISelection = sc.nextInt();
		}
		//If minimax
		if(AISelection == 1){
			ai = new TicTacToeMiniMaxAI();
		}
		//If random
		else if(AISelection == 2){
			ai = new AI(board);
		}
		//if alternate
		else if(AISelection ==3){
			ai = new Alt_tictactoeAI(board);
		}
		//Declare game as TicTacToe
		game = new TicTacToe(board);
		// add Players to the game
		game.addPlayer(human);
		game.addPlayer(ai);
		//Play the tictacToe
		if(ai instanceof TicTacToeMiniMaxAI)
			((TicTacToeMiniMaxAI) ai).getModel(game);
		game.play();
		
	}
}
