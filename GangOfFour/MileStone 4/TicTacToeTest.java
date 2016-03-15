import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for TicTacToe class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.1
 * @since Nov 7, 2014
 *
 */

public class TicTacToeTest
{
	
	private TicTacToe ttt;
	private DeepClone clone;

	@Before
	public void setUp() throws Exception
	{
		TicTacToeBoard tttb = new TicTacToeBoard(3);
		this.ttt = new TicTacToe(tttb);
	}


	@After
	public void tearDown() throws Exception
	{
		this.ttt = null;
	}


	@Test
	public void testCheckWinner()
	{
		assertEquals(null, this.ttt.checkWinner());
	}


	@Test
	public void testPrintHelp()
	{
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		this.ttt.printHelp("TicTacToeHelp.txt");
		
		
		assertTrue(outContent.toString().contains("RULES"));
		
		outContent = null;
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		 
		
	}


	@Test
	public void testCheckDraw()
	{
		assertFalse(this.ttt.checkDraw());
	}


	@Test
	public void testCheckMove()
	{
		Player player = new Human(new Scanner(System.in));
		
		int size = this.ttt.getBoard().getSize();
		
		System.out.println("Testing CheckMove. Type in the answers. Board is " + size + " by " + size + " \n");
		assertTrue(this.ttt.checkMove(player));
	}
	
	
	@Test
	public void testMakeMove()
	{
		Player player = new TicTacToeMiniMaxAI();
		
		for (int i = 0; i < this.ttt.getBoard().getSize(); i++)
		{
			for (int j = 0; j < this.ttt.getBoard().getSize(); j++)
			{
				this.ttt.makeMove(i, j, player, this.ttt);
				assertTrue(this.ttt.getBoard().isSquareOccupied(i, j));
			}
			
		}
		
	}
	
	@Test
	public void testGetCoordinates()
	{
		int size = 9;
		Player player = new TicTacToeMiniMaxAI();
		
		for (int i = 0; i < this.ttt.getBoard().getSize(); i++)
		{
			for (int j = 0; j < this.ttt.getBoard().getSize(); j++)
			{
				this.ttt.makeMove(i, j, player, this.ttt);
				assertEquals(this.ttt.getCoordinates(player).size(), --size);
			}
			
		}
		
	}
	
	@Test
	public void testCountScore2()
	{
		int score = this.ttt.countScore2();
		
		assertEquals(0, score);
		
		Player player = new TicTacToeMiniMaxAI();
		
		this.ttt.makeMove(0, 0, player, this.ttt);
		
		score = this.ttt.countScore2();
		
		assertEquals(3, score);
		
		this.ttt.makeMove(1, 1, player, this.ttt);
		
		score = this.ttt.countScore2();
		
		assertEquals(15, score);
	}
	
	@Test
	public void testDeepClone()
	{
		String str = (String)clone.deepClone("STRING");
		
		assertEquals("STRING", str);
		
	}

}
