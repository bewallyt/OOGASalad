package Data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class that encapsulates information relevant to a given image. 
 * @Author Davis treybig
 */
public class ImageFile {
	private String imageType;
	private String name;
	public static final String SRC="src/";
	
	public ImageFile(String name, String imageType) {
		this.name=name;
		this.imageType=imageType;
	}

	/**
	 * Returns the name of the image
	 */
	public String getName(){
		return name;
	}
	public void changeName(String s){
		name=s;
	}
	/**
	 * Returns the image type of the image. IE, "TileImage"
	 */
	public String getType(){
		return imageType;
	}
	/**
	 * Returns the Image version of the image
	 */
	public Image getImage(){
		File file=getFile();
		BufferedImage temp;
		try {
			temp = ImageIO.read(file);
		} catch (IOException e) {
			temp = null;
		}
		return (Image)temp;
		
	}
	/**
	 * Gets the File version of the image
	 */
	public File getFile(){
		return new File(SRC+imageType+"/"+name);
	}
}
