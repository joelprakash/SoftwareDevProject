import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class OthelloMiniMaxAITest {

	private final int SIZE = 4;
	Othello game;
	Player human;
	Player ai;
	
	
	public void setupBoard()
	{
		game.setup();
		game.makeMove(3,1,human,game);
		
	}
	
	@Before
	public void setUp() throws Exception {
		Board board = new OthelloBoard(SIZE);
		game = new Othello(board);
		
		human = new Human(new Scanner(System.in));
		game.addPlayer(human);
		ai = new OthelloMiniMaxAI();
		game.addPlayer(ai);
		((OthelloMiniMaxAI) ai).getModel(game);
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
		assertTrue(a instanceof OthelloMiniMaxAI);
		Player h = ((OthelloMiniMaxAI) a).nextplayer(a,game);
		assertTrue(h instanceof Human);
	}
	
	public boolean checkBoard()
	{
		Board b = game.getBoard();
		for(int i=1;i<b.getHeight();i++)
		{
		if((b.getSquare(i, 1).checkPlayerOccupied() instanceof Human) && (b.getSquare(i, 2).checkPlayerOccupied() instanceof OthelloMiniMaxAI))
		{
			return true;
		}
		}
		return false;
	}
	@Test
	public void testMiniMaxAI() {
		setupBoard();
		int row=ai.chooseRow();
		int col=ai.chooseCol();
		game.makeMove(row,col,ai,game);
		assertTrue(checkBoard());
	}
	


}
