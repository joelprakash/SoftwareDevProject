
/**
 * Human class implements the Player interface by
 * overriding the methods provided to allow the
 * Human player to input a move using the Scanner
 * instance provided.
 * 
 * 
 * @author Jeton Sinoimeri 100875046
 * 
 * @version 1.6
 * @since October 14, 2014
 * 
 */

import java.util.Scanner;


public class Human implements Player 
{
	
	// String constants for entering a row, col or help and invalid option
	private static final String NAME = "Human",
	                            INVALIDOPTION = "Invalid option. Please enter again.",
	                            SELECTROW = "Enter an integer for row or enter -1 for help: ",
			                    SELECTCOL = "Enter an integer for column or enter -1 for help: ";
	
	
	
	private Scanner scanner;                 // scanner instance for input
	
	
	/**
	 * Constructor for the Human player class
	 * 
	 * @param scanner -> Scanner reference to be used as input for
	 *                   the Human.
	 * 
	 */
	
	public Human(Scanner scanner)
	{
		this.scanner = scanner;
	}

	
	/**
	 * Human chooses a row by typing it
	 * 
	 * @return value -> integer representing the row chosen by
	 *                  the human.
	 * 
	 */
	
	@Override
	public int chooseRow() 
	{
		return this.chooseRowCol(SELECTROW);
	}

	
	/**
	 * Human chooses a column by typing it
	 * 
	 * @return value -> integer representing the column chosen by
	 *                  the human.
	 */
	
	@Override
	public int chooseCol() 
	{
		return this.chooseRowCol(SELECTCOL);
	}
	
	
	/**
	 * Human chooses either the row or the column depending on
	 * the message displayed.
	 * 
	 * @param s -> String representing the string to be displayed
	 *             telling the user to enter the row, column or -1
	 *             for help
	 *             
	 * @return value -> integer representing the value the user
	 *                  entered for either the row or column
	 *                  
	 */
	
	private int chooseRowCol(String s)
	{
		System.out.println(s);
		
		// loops until scanner has an integer value
		while(!this.scanner.hasNextInt())
		{
			System.out.println(INVALIDOPTION);
			System.out.println(s);
			
			this.scanner.next();
		}
		
		// gets the integer value from scanner
        int value = this.scanner.nextInt();
		
		return value;
	}
	
	
	/**
	 * Name of the class
	 * 
	 * @return NAME -> String constant representing the name  
	 *                 of the Player class
	 *                 
	 */
	
	@Override
	public String toString()
	{
		return NAME;
	}

}
