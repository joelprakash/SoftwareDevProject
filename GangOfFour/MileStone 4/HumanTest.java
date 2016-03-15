import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for Human class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 7, 2014
 *
 */


public class HumanTest
{
	
	private Human human;

	@Before
	public void setUp() throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		human = new Human(scanner);
	}


	@After
	public void tearDown() throws Exception
	{
		human = null;
	}


	@Test
	public void testChooseRow()
	{
		assertEquals(human.chooseRow(), 0);
		assertEquals(human.chooseRow(), 1);
		assertEquals(human.chooseRow(), 2);
	}


	@Test
	public void testChooseCol()
	{
		assertEquals(human.chooseCol(), 0);
		assertEquals(human.chooseCol(), 1);
		assertEquals(human.chooseCol(), 2);
	}

	
	@Test
	public void testToString()
	{
		assertTrue(human.toString().equals("Human"));
	}

}
