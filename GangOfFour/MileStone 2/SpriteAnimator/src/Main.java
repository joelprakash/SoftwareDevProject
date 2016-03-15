import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;


/**
 * Main Class contains main method aswell as my main window.
 * @author Varun
 *
 */
public class Main extends JFrame {
	
	BufferedImage sprite;
	Animator coin;
	public Main(){
		//Set size of the window.
		setSize(528,354);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
	}
	/**
	 * void Init()
	 * Function to initialize sprite animator components.
	 */
	private void init(){
		// new Buffered image loader to load the image
		BufferedImageLoader loader = new BufferedImageLoader();
		//BufferedImage to hold our Loaded spriteSheet
		BufferedImage spriteSheet = null;
		//Checks to see if our spriteSheet is in a proper directory to be loaded from
		try {
			spriteSheet = loader.loadImage("WideSpriteSheet.png");
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Create a new spriteSheet with the one we loded.
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		
		//Create an Array list to hold each frame of the sprite sheet
		ArrayList <BufferedImage> sprites = new ArrayList<BufferedImage>();
		sprites.add(ss.grabSprite(0, 0, 528, 354));
		sprites.add(ss.grabSprite(528, 0, 528, 354));
		sprites.add(ss.grabSprite(2*528, 0, 528, 354));
		sprites.add(ss.grabSprite(3*528, 0, 528, 354));
		sprites.add(ss.grabSprite(4*528, 0, 528, 354));
		
		sprites.add(ss.grabSprite(5*528, 0, 528, 354));
		sprites.add(ss.grabSprite(6*528, 0, 528, 354));
		sprites.add(ss.grabSprite(7*528, 0, 528, 354));
		sprites.add(ss.grabSprite(8*528, 0, 528, 354));
		sprites.add(ss.grabSprite(9*528, 0, 528, 354));
		
		sprites.add(ss.grabSprite(10*528, 0, 528, 354));
		sprites.add(ss.grabSprite(11*528, 0, 528, 354));
		sprites.add(ss.grabSprite(12*528, 0, 528, 354));
		sprites.add(ss.grabSprite(13*528, 0, 528, 354));
		sprites.add(ss.grabSprite(14*528, 0, 528, 354));
		
		sprites.add(ss.grabSprite(15*528, 0, 528, 354));
		sprites.add(ss.grabSprite(16*528, 0, 528, 354));
		sprites.add(ss.grabSprite(17*528, 0, 528, 354));
		sprites.add(ss.grabSprite(18*528, 0, 528, 354));
		sprites.add(ss.grabSprite(19*528, 0, 528, 354));
		
		sprites.add(ss.grabSprite(20*528, 0, 528, 354));
		sprites.add(ss.grabSprite(21*528, 0, 528, 354));
		sprites.add(ss.grabSprite(22*528, 0, 528, 354));
		sprites.add(ss.grabSprite(23*528, 0, 528, 354));
		sprites.add(ss.grabSprite(24*528, 0, 528, 354));
		
		
	
		
		//Create a new Animator to animate the captured sprites
		coin = new Animator(sprites);
		//set the speed to 50ms/frame
		coin.setSpeed(50);
		//play the animation;
		coin.play();
		
	}
	//declare an image and Graphics for the paint and paintComponent methods.
	Image dbImage;
	Graphics dbg;
	
	@Override
	/**
	 * Paints graphics to the window.
	 */
	public void paint(Graphics g){
		dbImage = createImage(getWidth(),getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage,0,0,null);
		repaint();
	}
	/**
	 * updates the window and updates the sprite animator.
	 * @param g
	 */
	public void paintComponent(Graphics g){
		if(coin != null){
			coin.update(System.currentTimeMillis());
			g.drawImage(coin.sprite, 0,0,528,354,null);
			repaint();
		}
	}
	
	/**
	 * Main Method to start the program.
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		
	}

}
