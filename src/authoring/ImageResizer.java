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

    private ImageManager myImageManager;
	
	public ImageResizer(){
        myImageManager = new ImageManager();
    }

	public void storeImage(String name, File file, String id) throws IOException {
		fileName = name;
		File imageFile = file;
        String fileId = id;
        Image m=scaleImage(imageFile, fileName);
        storedImage=myImageManager.storeScaledImage(fileName, m, fileId);
		addToEditor(m);
	}

	private void addToEditor(Image m) {
       // FeatureManager.getWorldData().saveImage(fileName, storedImage);
        FeatureManager.imageEditor.addImage(m, fileName);
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
