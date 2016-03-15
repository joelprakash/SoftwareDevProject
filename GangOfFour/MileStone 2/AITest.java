import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for AI class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 7, 2014
 *
 */


public class AITest
{

	Player ai;
	@Before
	public void setUp() throws Exception
	{
		Board b = new Board(3);
		this.ai = new AI(b);
	}


	@After
	public void tearDown() throws Exception
	{
		this.ai = null;
	}


	@Test
	public void testChooseRow()
	{
		assertTrue(this.ai.chooseRow() < 4);
	}


	@Test
	public void testChooseCol()
	{
		assertTrue(this.ai.chooseCol() < 4);
	}


	@Test
	public void testToString()
	{
		assertEquals("AI", this.ai.toString());
	}

}
