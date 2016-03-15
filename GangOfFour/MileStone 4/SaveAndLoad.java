/**
 * 
 * @author Joel Prakash 100910302
 *       
 * 
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class  SaveAndLoad 
{

	public static void saveGame(Board board, String file)
	{
		 
	      try {

	         // create a new file with an ObjectOutputStream
	         FileOutputStream out = new FileOutputStream(file);
	         ObjectOutputStream oos = new ObjectOutputStream(out);
	         
		
	         // write something in the file
	         oos.writeObject(board);
	        
	         oos.flush();
	      } catch (Exception ex) {
		         ex.printStackTrace();
		      }
	}
	
	public static Board reloadGame (String file)
	{
		Board board = null;
		try
		{
	         // create an ObjectInputStream for the file we created before
	         ObjectInputStream ois =
	                 new ObjectInputStream(new FileInputStream(file));

	         // read and print an object and cast it as string
	         
	    
	        	board= (Board)ois.readObject();
	        	
	        	System.out.println("loaded board: " + board.toString());
	         

	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
		return board;

	}
}
