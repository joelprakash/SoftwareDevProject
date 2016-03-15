import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for HoundsAndHareBoard class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 19, 2014
 * 
 */

public class HoundsAndHareBoardTest
{

	HoundsAndHareBoard b;
	
	@Before
	public void setUp() throws Exception
	{
		this.b = new HoundsAndHareBoard();
	}


	@After
	public void tearDown() throws Exception
	{
		this.b = null;
	}


	@Test
	public void testHoundsAndHareBoard()
	{
		assertEquals(this.b.getHeight(), 5);
		assertEquals(this.b.getWidth(), 3);
	}


	@Test
	public void testGetRestrictedSquares()
	{
		assertEquals(this.b.getRestrictedSquares().size(), 4);
	}

}
