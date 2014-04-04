package Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageManager {
	public static final String DEFAULT_SRC_FILE="src/";
	public static final String DEFAULT_IMAGE_PACKAGE="ImageFiles/";
	private String imagePath;
	
	public ImageManager() {
		String path = System.getProperty("user.dir")+"/"+DEFAULT_SRC_FILE+DEFAULT_IMAGE_PACKAGE; 
		imagePath = path.replaceAll("\\\\", "/");
	}
	/**
	 * Loads a specified image file into the project directory to be used in the authoring environment
	 * @param fileName Name of the image file that will be created in the project directory
	 * @param imageFile File that is to be copied into the project directory
	 * @throws IOException
	 */
	public void storeImage(String fileName, File imageFile) throws IOException{
		Path source=Paths.get(imageFile.getAbsolutePath());
		Path newDirectory=Paths.get(imagePath+fileName);
		Files.copy(source, newDirectory);	
	}
	public static void main(String[] args){
		ImageManager image=new ImageManager();
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("jpg", "JPG", "png", "PNG", "gif");
		chooser.setFileFilter(filter);
		int returnVal=chooser.showOpenDialog(null);
		File f=chooser.getSelectedFile();
		try {
			image.storeImage("TestImage.JPG", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
