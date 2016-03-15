import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Alt_tictactoeAITest {
	TicTacToe game;
	Player human;
	Player ai;
	
	@Before
	public void setUp() throws Exception {
		Board board = new TicTacToeBoard(3);
		game = new TicTacToe(board);
		
		human = new Human(new Scanner(System.in));
		game.addPlayer(human);
		ai = new Alt_tictactoeAI(board);
		game.addPlayer(ai);
		
	}
	
	public void setupBoard()
	{
		
		game.makeMove(2,2,human,game);
		
	}
	
	public void setupBoard2()
	{
		
		game.makeMove(1,1,human,game);
		game.makeMove(0,2,ai,game);
		game.makeMove(2,0,human,game);
		game.makeMove(2,2,ai,game);
		
	}
	public boolean checkBoard()
	{
		Board b = game.getBoard();
		if((b.getSquare(1, 1).checkPlayerOccupied() instanceof Alt_tictactoeAI)  )
		{
			return true;
		}
	
		return false;
	}
	
	public boolean checkBoard2()
	{
		Board b = game.getBoard();
		if((b.getSquare(0, 0).checkPlayerOccupied() instanceof Alt_tictactoeAI)  )
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
		int row=ai.chooseRow();
		int col=ai.chooseCol();
		game.makeMove(row,col,ai,game);
		assertTrue(checkBoard());
		game.getBoard().resetSquare(1, 1);
		game.getBoard().resetSquare(2, 2);
		setupBoard2();
		int row2=ai.chooseRow();
		int col2=ai.chooseCol();
		game.makeMove(row2,col2,ai,game);
		assertTrue(checkBoard2());
	}

}