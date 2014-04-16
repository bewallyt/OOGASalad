package authoring;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Data.ImageFile;
import Data.ImageManager;

 class ImageResizer {
	
	private ImageFile storedImage;
	private String fileName;
	private String fileId;

    private ImageManager myImageManager;
	
	public ImageResizer(){
        myImageManager = new ImageManager();
    }

	public void storeImage(String name, File file, String id) throws IOException {
		fileName = name;
		File imageFile = file;
        fileId = id;
        Image m=scaleImage(imageFile, fileName);
        storedImage=myImageManager.storeScaledImage(fileName, m, fileId);
		addToEditor(storedImage);
	}


	private void addToEditor(ImageFile imageFile) {
		if(imageFile.getType())
        FeatureManager.tileEditor.addImage(m, fileName);

	}
	
	private Image scaleImage(File fileName, String s){
		BufferedImage temp;
		try {
			temp = ImageIO.read(fileName);
		} catch (IOException e) {
			temp = null;
		}
		Image scaledImage = temp.getScaledInstance(36, 36, Image.SCALE_FAST);
		return scaledImage;
	}
}
