package authoring.features;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import Data.ImageFile;
import Data.ImageManager;

/**
 * Class that handles the resizing and storage of uploaded images
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
 public class ImageResizer {
	
	private ImageFile storedImage;
	private String fileName;
	private String fileId;

    private ImageManager myImageManager;
	
	public ImageResizer(){
        myImageManager = new ImageManager();
    }

	/**
	 * Stores the image into the project's image folders
	 * @param name Name to give the image
	 * @param file File the image came from
	 * @param id Type of image
	 */
	protected void storeImage(String name, File file, String id) throws IOException {

		fileName = name;
		File imageFile = file;
        fileId = id;
        Image m=scaleImage(imageFile, fileName);
        storedImage=myImageManager.storeScaledImage(fileName, m, fileId);
		addToEditor(storedImage);
	}

	/**
	 * Adds a newly uploaded image to the TileEditor
	 * @param imageFile ImageFile to place in the TileEditor
	 */
	private void addToEditor(ImageFile imageFile) {
		if(imageFile.getType().equalsIgnoreCase(FeatureManager.tileEditor.IMAGE_TYPE)){
			FeatureManager.tileEditor.addImage(imageFile.getImage(), storedImage.getName());
		}
	}
	
	/**
	 * Scales an uploaded image to fit within a Tile. Returns the scaled Image
	 * @param fileName File from which the image came from
	 * @param s Name of image
	 */
	private Image scaleImage(File fileName, String s){
		BufferedImage temp;
		try {
			temp = ImageIO.read(fileName);
		} catch (IOException e) {
			temp = null;
		}
		Image scaledImage = temp.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		return scaledImage;
	}
}
