package authoring;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizer {
	
	private WorldData myWorldData;
	private Image scaledImage;
	private String fileName;
	
	public ImageResizer(){	
		myWorldData = new WorldData();		
	}

	protected void squareImage(String name, File file) throws IOException {
		fileName = name;
		BufferedImage bimg = ImageIO.read(file);
		scaledImage = bimg.getScaledInstance(48, 48, Image.SCALE_FAST);
		addToWorldData();
	}

	private void addToWorldData() {
		myWorldData.saveImage(fileName, scaledImage);
	}

}
