import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for Square class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 7, 2014
 *
 */


public class SquareTest
{
	Square sq;

	@Before
	public void setUp() throws Exception
	{
		this.sq = new Square(0, 0);
	}


	@After
	public void tearDown() throws Exception
	{
		this.sq = null;
	}


	@Test
	public void testGetX()
	{
		assertEquals(this.sq.getX(), 0);
	}


	@Test
	public void testGetY()
	{
		assertEquals(this.sq.getY(), 0);
	}


	@Test
	public void testCheckOccupied()
	{
		assertFalse(this.sq.checkOccupied());
	}


	@Test
	public void testCheckPlayerOccupied()
	{	
		assertEquals(this.sq.checkPlayerOccupied(), null);
	}


	@Test
	public void testSetOccupied()
	{
		Player human = new Human(new Scanner(System.in));
		
		this.sq.setOccupied(human);
		
		assertEquals(this.sq.checkPlayerOccupied(), human);
	}


	@Test
	public void testUnOccupy()
	{
		Player human = new Human(new Scanner(System.in));
		
		this.sq.setOccupied(human);
		
		assertEquals(this.sq.checkPlayerOccupied(), human);
		
		this.sq.unOccupy();
		
		assertEquals(this.sq.checkPlayerOccupied(), null);
	}


	@Test
	public void testToString()
	{
		Player human = new Human(new Scanner(System.in));
		this.sq.setOccupied(human);
		
		assertEquals(this.sq.toString(), "X");
	}

}
