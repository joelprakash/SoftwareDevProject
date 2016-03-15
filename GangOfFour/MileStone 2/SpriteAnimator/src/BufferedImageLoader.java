import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


/**
 * Basic Class to act as an image loader
 * @author Varun
 *
 */

public class BufferedImageLoader {
	/**
	 * Method to load an image from file directory
	 * @param pathRelativeToThis
	 * @return BufferedImage
	 * @throws IOException
	 */
	public BufferedImage loadImage(String pathRelativeToThis) throws IOException{
		URL url = this.getClass().getResource(pathRelativeToThis);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
}
