import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * JUnit testing for TicTacToeBoard class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 7, 2014
 *
 */


public class TicTacToeBoardTest
{

	TicTacToeBoard tttb;
	
	@Before
	public void setUp() throws Exception
	{
		this.tttb = new TicTacToeBoard(3);
	}


	@After
	public void tearDown() throws Exception
	{
		this.tttb = null;
	}


	@Test
	public void testIsRowFilled()
	{
		Player p = new Human(new Scanner(System.in));
		Player p1 = new AI(this.tttb);
		
		for (int row = 0; row < this.tttb.getSize(); row++)
		{
			assertFalse(this.tttb.isRowFilled(row, p));
			assertFalse(this.tttb.isRowFilled(row, p1));
		}
	}


	@Test
	public void testIsColFilled()
	{
		Player p = new Human(new Scanner(System.in));
		Player p1 = new AI(this.tttb);
		
		for (int col = 0; col < this.tttb.getSize(); col++)
		{
			assertFalse(this.tttb.isColFilled(col, p));
			assertFalse(this.tttb.isColFilled(col, p1));
		}
	}


	@Test
	public void testIsDiagonalFilledLtoR()
	{

		Player p = new Human(new Scanner(System.in));
		Player p1 = new AI(this.tttb);

		assertFalse(this.tttb.isDiagonalFilledLtoR(p));
		assertFalse(this.tttb.isDiagonalFilledLtoR(p1));


	}


	@Test
	public void testIsDiagonalFilledRtoL()
	{
		Player p = new Human(new Scanner(System.in));
		Player p1 = new AI(this.tttb);

		assertFalse(this.tttb.isDiagonalFilledRtoL(p));
		assertFalse(this.tttb.isDiagonalFilledRtoL(p1));
	}

}
