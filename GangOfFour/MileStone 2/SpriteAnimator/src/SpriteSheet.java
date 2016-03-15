import java.awt.image.BufferedImage;

/**
 * Class to define SpriteSheets
 * @author Varun
 *
 */
public class SpriteSheet {
	//a spritesheet consists of a buffered image file.
	public BufferedImage spriteSheet;
	
	public SpriteSheet(BufferedImage ss){
		this.spriteSheet = ss;
		
	}
	/**
	 * Method to grab a subImage of the spriteSheet;
	 * @param x X location by pixel
	 * @param y Y location by pixel
	 * @param width width of the subImage by pixel
	 * @param height height of the subImage by pixel
	 * @return a sprite Frame in the form of BufferedImage.
	 */
	public BufferedImage grabSprite(int x , int y ,int width,int height){
		BufferedImage sprite = spriteSheet.getSubimage(x,y,width,height);
		return sprite;
	}
}
