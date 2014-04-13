package Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class handles the loading and usage of images, specifically for the authoring environment so that
 * users can load in their own images and use them for specific objects or tiles. 
 * @author Davis Treybig
 *
 */
public class ImageManager {
	public static final String DEFAULT_SRC_FILE="src/";
	public static final String DEFAULT_IMAGE_PACKAGE="ImageFiles/";
	public static final String[] VALID_IMAGE_EXTENSIONS={".jpg", ".gif", ".JPG", ".GIF", ".png", ".PNG"};
	private String imagePath;
	
	public ImageManager() {
		String path = System.getProperty("user.dir")+"/"+DEFAULT_SRC_FILE+DEFAULT_IMAGE_PACKAGE; 
		imagePath = path.replaceAll("\\\\", "/");
	}
	/**
	 * Loads a specified image file into the project directory to be used in the authoring environment
	 * @param fileName Name of the image file that will be created in the project directory (without an extension)
	 * @param imageFile File that is to be copied into the project directory
	 * @throws IOException
	 * @return Returns the new File stored in the project
	 */
	public File storeImage(String fileName, File imageFile) throws IOException{
		String extension=getFileExtension(imageFile);
		if(checkImageExtension(extension)){
			Path source=Paths.get(imageFile.getAbsolutePath());
			Path newDirectory=Paths.get(imagePath+fileName+extension);
			Files.copy(source, newDirectory);	
			return (new File(imagePath+fileName+extension));
		}
		return null;
	}
	/**
	 * Returns the file extension of a given file. For instance, "tester.txt" would return ".txt"
	 * @param f File to be analyzed
	 * @return Extension of the file
	 */
	private String getFileExtension(File f){
		String extension="";
		int i=f.getName().lastIndexOf('.');
		if(i>0){
			extension=f.getName().substring(i);
		}
		return extension;
	}
	/**
	 * Checks the extension of the loaded image file to ensure it is an image. 
	 * @param s String version of the file extension
	 * @return Returns true if the extension is a valid image extension
	 */
	private boolean checkImageExtension(String s){
		for(String validExtension: VALID_IMAGE_EXTENSIONS){
			if(s.equals(validExtension)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Loads the image corresponding to the file name within the default Image resource package. 
	 * @param s String name of the image file
	 * @return File s with the path of the image
	 */
	public File loadImage(String s){
		return new File(imagePath+s);
	}
	/**
	 * Returns a list of all the names of currently saved images in the default image
	 * folder. 
	 * @return List of names of images
	 */
	public List<String> getSavedImageList(){
		return new FileLister().getFileList(imagePath);
	}
	public static void main(String[] args){
		ImageManager image=new ImageManager();
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("jpg", "JPG", "png", "PNG", "gif");
		chooser.setFileFilter(filter);
		int returnVal=chooser.showOpenDialog(null);
		File f=chooser.getSelectedFile();
		try {
			image.storeImage("TestImage2", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
