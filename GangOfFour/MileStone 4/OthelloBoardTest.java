import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for OthelloBoard class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 7, 2014
 *
 */


public class OthelloBoardTest
{

	OthelloBoard ob;
	
	@Before
	public void setUp() throws Exception
	{
		this.ob = new OthelloBoard(4);
	}


	@After
	public void tearDown() throws Exception
	{
		this.ob = null;
	}


	@Test
	public void testFlipSquare()
	{
		Player p = new Human(new Scanner(System.in));
		Player p1 = new AI(this.ob);
		
		for(int row = 0; row < this.ob.getSize(); row ++)
			for (int col = 0; col < this.ob.getSize(); col ++)
			{
				assertFalse(this.ob.flipSquare(row, col, p));
				assertFalse(this.ob.flipSquare(row, col, p1));
			}
	}


	@Test
	public void testGetSquare()
	{
		Square sq = this.ob.getSquare(0, 0);
		Square sq1 = this.ob.getSquare(0, 0);
		assertEquals(sq, sq1);
	}

}
