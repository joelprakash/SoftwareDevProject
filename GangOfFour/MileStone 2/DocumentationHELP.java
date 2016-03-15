/**
 * This class holds the help documentation for
 * TicTacToe and Othello games.
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 7, 2014
 *
 */



public class DocumentationHELP
{
	
	// String constant representing help info for TicTacToe
	public static final String TICTACTOEHELP = "RULES OF TIC-TAC-TOE: \n\nX always goes first. \n\nPlayers "
				+ "alternate placing Xs and Os on the board until either (a) one player has three in "
				+ "a row, \nhorizontally, vertically or diagonally; or (b) all squares are filled. \n"
				+ "If a player is able to draw three Xs or three Os in a row, that player wins. If "
				+ "all the squares \nare filled and neither player has won, the game is considered a "
				+ "draw. \n\n\nHOW THE GAME LOOP WORKS: \n\nThe game loop will print the board, check "
				+ "if if there is a winner. It will ask the Human player \nto make a move, it will then "
				+ "check if the move is valid, if it isn't it will reprompt the Human \nplayer. Otherwise "
				+ "it will continue on, print the board, check if there is a winner. If there is \na winner, "
				+ "then the game will end, otherwise the AI will make a move. \n\nThe game loop will "
				+ "continue to run until there is a winner or there's a draw.";
	

	// String constant representing help info for Othello
	public static final String OTHELLOHELP = "RULES OF OTHELLO: \n\nO always goes first. \n\nPlayers "
					+ "alternate placing X and O markers on the board on either a 4x4 or 8x8 board  \n"
					+ "There are always four markers always placed on the board at the start of each game \n"
					+ "Both players can place their markers horizontally,vetically or diagonally A Player \n"
					+ "has to always make a move such that the player traps any no of  opponent's maker \n"
					+ "between two of the player's markers. When this happens the player can flip all of \n " 
					+ "the opponent's marker that the player trapped and the opponent's marker shall become \n" 
			        + "the player's marker.In some cases when the player makes a move, the player will see \n"
					+ "that they can flip two different rows or more. Once the board becomes full, the winner \n "
			        + "is decided by which player has the majority of their markers placed on the board. It \n"
					+ "is a draw if both players have the sameno of markers on the board. If a player cannot \n"
			        + "place any where on the board so as the make a flip,the player must pass their turn to \n  "
					+ "the opponent. If the opponent also can't make a turn after theplayer passed the game \n"
			        + "ends and the winner is decided by the player with majority no of markers onthe board, \n"
					+ "with blank spaces on the board counting towards the winner's score."
			        + "\n\n\nHOW THE GAME LOOP WORKS:"
					+ "\n\nThe game loop will print the board, check if there is a winner. It will ask the \n"
					+ "Human player to make a move, it will then check if the move is valid, if it isn't it \n"
					+ "will reprompt the Human player. Otherwise it will continue on.print the board, check \n"
					+ "if there is a winner. If there is a winner,then the game will end,otherwise the AI \n "
					+ "will make a move. The game loop will continue to run until there is a winner or there \n"
					+ "is a draw.\n";

}
