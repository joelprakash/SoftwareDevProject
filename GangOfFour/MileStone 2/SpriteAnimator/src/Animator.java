import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Animator Class to animate the sprites.
 * @author Varun
 *
 */
public class Animator {
	//ArrayList that will contain each frame of the sprite;
	ArrayList<BufferedImage> frames;
	//Buffered Image to hold a single sprite
	BufferedImage sprite;
	//boolean to tell if the animator is running.
	private volatile boolean running = false;
	//longs for the speed of the animation and the time allotted in the previous frame
	private long previousTime , speed;
	//int to hold the frame when paused and the current frame the animator is on
	private int frameAtPause, currentFrame;
	
	/**
	 * Constructor 
	 * @param frames list of buffered images of frames
	 */
	public Animator(ArrayList<BufferedImage> frames){
		this.frames = frames;
	}
	/**
	 * method to update the animator as time passes
	 * @param time
	 */
	
	public void update(long time){
		if(running){
			
			if(time - previousTime >= speed){
				//update the animation
				currentFrame++;
				try{
					sprite = frames.get(currentFrame);
				}
				catch(IndexOutOfBoundsException e){
					currentFrame = 0;
					sprite = frames.get(currentFrame);
				}
				
				previousTime = time;	
			}
			
		}
		
	}
	/**
	 * method to set the speed of the animator
	 * @param speed
	 */
	public void setSpeed(long speed){
		this.speed = speed;
	}
	
	/**
	 * method to play the animator
	 */
	public void play(){
		running = true;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}
	
	/**
	 * method to stop the animator
	 */
	public void stop(){
		running = false;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
		
	}
	
	/**
	 * method to pause the animator
	 */
	public void pause(){
		frameAtPause = currentFrame;
		running = false;
	}
	
	/**
	 * method to resume the animator when paused.
	 */
	public void resume(){
		currentFrame = frameAtPause;
		running = true;
	}
}
