import java.util.Scanner;

/**
 * GameMain contains out main method to run the game.
 * @author Varun
 *
 */
public class GameMain {
	//Various Display Strings for prompting the User
	private static final String WELCOMESTRING = "Welcome to Othello/TicTacToe\nPlease Enter 1 if you want TicTacToe or 2 if you want Othello. or -1 to quit";
	
	private static final int TTTSELECT = 1,
                             OSELECT = 2,
	                         QUIT = -1;
	
	private static final String GOODBYE = "Good Bye\n",
                                INVALID = "I'm Sorry that is Invalid please Try Again\n",
                                PLAYING = "You are now playing:";
	//private static final int GAME3SELECT = 3;

	/**
	 * Main Class to run our games.
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Declare Scanner for the game
		Scanner sc = new Scanner(System.in);
		
		//Declare a human player
		Player human = new Human(sc);
		
		//Declare AI for the game
		Player ai;
		
		//Declare a board for the game
		Board board;
		
		//Declare a general Game
		Game game;
		
		//int to store user selection
		int user;
		
		//boolean to check if user wants to quit main cycle
		boolean checkInvalid= true;
		
		//While user does not want to quit program
		while(checkInvalid == true)
		{
			//prompt a welcome
			System.out.println(WELCOMESTRING);
			
			//while user does not type an int
			while (!sc.hasNextInt()) 
			{
			    //Prompt user input as invalid
			    System.out.println(INVALID);
			    sc.nextLine();
			}
			
			//else store user selection
			user = sc.nextInt();
			
			//of user wants to play TicTacToe
			if(user== TTTSELECT)
			{
				//Prompt user he/she is playing TicTacToe
				System.out.println(PLAYING + " TicTacToe\n");
				
				//Declare a board of size 3
				board= new TicTacToeBoard(3);
				
				//Declare the AI
				ai = new AI(board);
				
				//Declare game as TicTacToe
				game = new TicTacToe(board);
				
				// add Players to the game
				game.addPlayer(human);
				game.addPlayer(ai);
				
				//Play the tictacToe
				game.play();
				
			}
			
			
			else if (user == OSELECT){
				System.out.println(PLAYING +" Othello\n");
				
				int size = 8;
				//Declare a board of size 4 or size 8
				board= new OthelloBoard(size);
				
				//Declare the AI
				ai = new AI(board);
				
				//Declare game as Othello
				game = new Othello(board);
				
				// add Players to the game
				game.addPlayer(human);
				game.addPlayer(ai);
				
				int i = size/2 - 1;
				board.setSquare(i, i, human);
				board.setSquare(i + 1, i + 1, human);
				
				board.setSquare(i + 1, i, ai);
				board.setSquare(i,  i + 1, ai);
				
				//Play Othello
				game.play();
				 
			}
			
			//if selection is to quit
			else if(user == QUIT)
			{
				//prompt a goodbye
				System.out.println(GOODBYE);
				
				//close the scanner
				sc.close();
				
				//exit while condition
				checkInvalid = false;
				
			}
			
			//if user selection is not an int 
			else{
				//prompt invalid
				System.out.println(INVALID);
			}
		}
		
		}
	}


