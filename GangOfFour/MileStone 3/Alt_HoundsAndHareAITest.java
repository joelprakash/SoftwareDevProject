import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Alt_HoundsAndHareAITest {

	HoundsAndHare game;
	Player human;
	Player ai;
	
	@Before
	public void setUp() throws Exception {
		Board board = new HoundsAndHareBoard();
		game = new HoundsAndHare(board);
		
		human = new Human(new Scanner(System.in));
		game.addPlayer(human);
		ai = new Alt_HoundsAndHareAI(board);
		game.addPlayer(ai);
		
	}
	
	public void setupBoard()
	{
		game.setup();
		
		
	}
	
	
	public boolean checkBoard()
	{
		Board b = game.getBoard();
		if((b.getSquare(1, 1).checkPlayerOccupied() instanceof Alt_HoundsAndHareAI) || (b.getSquare(1, 2).checkPlayerOccupied() instanceof Alt_HoundsAndHareAI) )
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
	public void testAlt_tictactoeAI() {
		setupBoard();
		System.out.println(game.getBoard().toString());
		int row=ai.chooseRow();
		int col=ai.chooseCol();
		System.out.println(row);
		System.out.println(col);
		game.makeMove(row,col,ai,game);
		assertTrue(checkBoard());
	}

}
