import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for Board class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 7, 2014
 *
 */


public class BoardTest
{

	Board b;
	
	@Before
	public void setUp() throws Exception
	{
		this.b = new Board(3);
	}


	@After
	public void tearDown() throws Exception
	{
		this.b = null;
	}


	@Test
	public void testGetSize()
	{
		assertEquals(this.b.getSize(), 3);
	}


	@Test
	public void testGetBoard()
	{
		assertEquals(this.b.getBoard().length, 3);
	}


	@Test
	public void testIsSquareOccupied()
	{
		for(int row = 0; row < this.b.getSize(); row ++)
			for(int col = 0; col < this.b.getSize(); col ++)
				assertFalse(this.b.isSquareOccupied(row, col));
	}


	@Test
	public void testSetSquare()
	{
		Player ai = new AI(this.b);
		
		this.b.setSquare(0, 0, ai);
		
		assertTrue(this.b.isSquareOccupied(0, 0));
	}


	@Test
	public void testResetSquare()
	{
		Player ai = new AI(this.b);
		
		for(int row = 0; row < this.b.getSize(); row ++)
			for(int col = 0; col < this.b.getSize(); col ++)
				this.b.setSquare(row, col, ai);
		
		assertTrue(this.b.isFull());
		
		for(int row = 0; row < this.b.getSize(); row ++)
			for(int col = 0; col < this.b.getSize(); col ++)
				this.b.resetSquare(row, col);
		
		assertFalse(this.b.isFull());
		
	}


	@Test
	public void testToString()
	{
		assertTrue(this.b.toString().contains("_"));
	}


	@Test
	public void testIsFull()
	{
		Player ai = new AI(this.b);
		
		assertFalse(this.b.isFull());
		
		for(int row = 0; row < this.b.getSize(); row ++)
			for(int col = 0; col < this.b.getSize(); col ++)
				this.b.setSquare(row, col, ai);
		
		assertTrue(this.b.isFull());
	}

}
