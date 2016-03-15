import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author himanishkaushal
 *
 */

public class OthelloTest {

	private final int SIZE = 4;
	Othello othello;
	Player human;
	Player ai;
	@Before
	public void setUp() throws Exception {
		Board board = new OthelloBoard(SIZE);
		othello = new Othello(board);
		
		human = new Human(new Scanner(System.in));
		othello.addPlayer(human);
		ai = new AI(othello.getBoard());
		othello.addPlayer(ai);
	}

	@After
	public void tearDown() throws Exception {
		othello = null;
	}

	@Test
	public void testCheckWinner() {

		assertEquals(othello.checkWinner(), null);
	}

	@Test
	public void testPrintHelp() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		othello.printHelp();
		
		
		assertTrue(outContent.toString().contains("RULES"));
		
		outContent = null;
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));	}

	@Test
	public void testCheckDraw() {
		assertFalse(othello.checkDraw());
	}

	@Test
	public void testValidSquares() {
		ArrayList<Square> validSquares = othello.validSquares(human);
		assertTrue(validSquares.isEmpty());
	}

	@Test
	public void testTraverseDir() {
		
		assertEquals(othello.traverseDir(1000, 1000, 0, human), null);
	}

	@Test
	public void testTraverseDirForFlip() {
		
		assertFalse(othello.traverseDirForFlip(1000, 1000, 0, human));
	}

	@Test
	public void testCalcIncR() {
		assertEquals(othello.calcIncR(1000), 1);
	}

	@Test
	public void testCalcIncC() {
		assertEquals(othello.calcIncC(1000), 1);
	}

	@Test
	public void testIsInBounds() {
		assertFalse(othello.isInBounds(-1, -1));
	}

	@Test
	public void testCountScore() {
		assertEquals(othello.countScore(human), 0);
	}

	@Test
	public void testCheckMove() {
		assertFalse(othello.checkMove(-1, -1, human));
	}

	@Test
	public void testCanAIPass() {
		assertFalse(othello.canAIPass());
	}

}
