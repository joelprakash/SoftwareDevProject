import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for Game class
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.0
 * @since Nov 7, 2014
 *
 */


public class GameTest
{
	private static final int SIZE = 3;
	private Game g;

	@Before
	public void setUp() throws Exception
	{
		Board b = new Board(SIZE);
		g = new TicTacToe(b);
	}


	@After
	public void tearDown() throws Exception
	{
		g = null;
	}


	@Test
	public void testGetBoard()
	{
		assertEquals(SIZE, this.g.getBoard().getSize());
	}


	@Test
	public void testSetBoard()
	{
		Board b = new Board(SIZE + 1);
		g.setBoard(b);
		
		assertEquals(SIZE + 1, this.g.getBoard().getSize());
		
	}


	@Test
	public void testAddPlayer()
	{
		Player p = new AI(g.getBoard());
		g.addPlayer(p);
		assertEquals(1, this.g.totalNumPlayers());
	}


	@Test
	public void testGetAllPlayers()
	{
		Player p = new AI(g.getBoard());
		Player p1 = new AI(g.getBoard());
		Player p2 = new AI(g.getBoard());
		g.addPlayer(p);
		g.addPlayer(p1);
		g.addPlayer(p2);
		
		ArrayList<Player> ap = (ArrayList<Player>) this.g.getAllPlayers();
		
		assertEquals(ap.get(0), p);
		assertEquals(ap.get(1), p1);
		assertEquals(ap.get(2), p2);
	}


	@Test
	public void testTotalNumPlayers()
	{
		Player p = new AI(g.getBoard());
		g.addPlayer(p);
		assertEquals(1, this.g.totalNumPlayers());
		g.addPlayer(p);
		g.addPlayer(p);
		assertEquals(3, this.g.totalNumPlayers());
	}


	@Test
	public void testGetPlayerAt()
	{
		Player p = new AI(g.getBoard());
		Player p1 = new AI(g.getBoard());
		Player p2 = new AI(g.getBoard());
		
		g.addPlayer(p);
		g.addPlayer(p1);
		g.addPlayer(p2);
		
		assertEquals(this.g.getPlayerAt(0), p);
		assertEquals(this.g.getPlayerAt(1), p1);
		assertEquals(this.g.getPlayerAt(2), p2);
		
	}

}
