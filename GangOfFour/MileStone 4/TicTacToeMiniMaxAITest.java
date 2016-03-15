import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TicTacToeMiniMaxAITest {

	TicTacToe game;
	Player human;
	Player ai;
	
	@Before
	public void setUp() throws Exception {
		Board board = new TicTacToeBoard(3);
		game = new TicTacToe(board);
		
		human = new Human(new Scanner(System.in));
		game.addPlayer(human);
		ai = new TicTacToeMiniMaxAI();
		game.addPlayer(ai);
		((TicTacToeMiniMaxAI) ai).getModel(game);
	}
	
	public void setupBoard()
	{
		
		game.makeMove(1,1,human,game);
		game.makeMove(2,2,ai,game);
		game.makeMove(2,1,human,game);
		
	}
	
	public boolean checkBoard()
	{
		Board b = game.getBoard();
		if((b.getSquare(1, 1).checkPlayerOccupied() instanceof Human) && (b.getSquare(2, 1).checkPlayerOccupied() instanceof Human) && (b.getSquare(2, 2).checkPlayerOccupied() instanceof TicTacToeMiniMaxAI) && (b.getSquare(0, 1).checkPlayerOccupied() instanceof TicTacToeMiniMaxAI) )
		{
			return true;
		}
	
		return false;
	}

	@After
	public void tearDown() throws Exception {
		game=null;
		human=null;
		ai=null;
	}

	@Test
	public void testnextplayer() {
		Player a = game.getPlayerAt(1);
		assertTrue(a instanceof TicTacToeMiniMaxAI);
		Player h = ((TicTacToeMiniMaxAI) a).nextplayer(a,game);
		assertTrue(h instanceof Human);
	}
	
	@Test
	public void testtictactoeAI() {
		setupBoard();
		int row=ai.chooseRow();
		int col=ai.chooseCol();
		game.makeMove(row,col,ai,game);
		assertTrue(checkBoard());
	}
}
