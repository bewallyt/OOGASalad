package Data;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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
	//public static final String DEFAULT_IMAGE_PACKAGE="TestImageFiles/";
	public static final String[] VALID_IMAGE_EXTENSIONS={".jpg", ".gif", ".JPG", ".GIF", ".png", ".PNG"};
	public static final String DEFAULT_IMAGE_EXTENSION="jpg";
	public static final String[] IMAGE_FOLDER_OPTIONS={"gridobject/", "TileImage/"};
	private String imagePath;
	
	public ImageManager() {
		//String path = System.getProperty("user.dir")+"/"+DEFAULT_SRC_FILE+DEFAULT_IMAGE_PACKAGE; 
		String path = System.getProperty("user.dir")+"/"+DEFAULT_SRC_FILE;
		imagePath = path.replaceAll("\\\\", "/");
	}
	public Map<Image, String> getSavedImageMap(){
		Map<Image, String> imageMap=new HashMap<Image, String>();
		for(String s: IMAGE_FOLDER_OPTIONS){
			for(String image: getSavedImageList(imagePath+s)){
				//System.out.println(image);
				File file=loadImage(image, s);
				BufferedImage temp;
				try {
					temp = ImageIO.read(file);
				} catch (IOException e) {
					temp = null;
				}
				imageMap.put((Image)temp, image);
			}
		}
		return imageMap;
	}
	/**
	 * Loads a specified image file into the project directory to be used in the authoring environment
	 * @param fileName Name of the image file that will be created in the project directory (without an extension)
	 * @param imageFile File that is to be copied into the project directory
	 * @param imageType indicates what the image will be used for (and thus what folder it will be saved in)
	 * @throws IOException
	 * @return Returns the new File stored in the project
	 */
	public File storeImage(String fileName, File imageFile, String imageType) throws IOException{
		String extension=getFileExtension(imageFile);
		if(checkImageExtension(extension)){
			Path source=Paths.get(imageFile.getAbsolutePath());
			String newParentPath=imagePath+imageType;
			String fullPath=newParentPath+"/"+fileName+extension;
			
			Path newDirectory=Paths.get(fullPath);
			File newImageFile=new File(fullPath);
			File parentFolderFile=new File(newParentPath);
			parentFolderFile.mkdirs();
			try{
				Files.copy(source, newDirectory);
			}
			catch(FileAlreadyExistsException e){
				return null;
			}
			return (newImageFile);
		}
		return null;
	}
	public File storeScaledImage(String filename, Image image, String imageType) throws IOException{
		String newParentPath=imagePath+imageType;
		String fullPath=newParentPath+"/"+filename+"."+DEFAULT_IMAGE_EXTENSION;
		System.out.println("Full Path :"+ fullPath);
		
		File newImageFile=new File(fullPath);
		File parentFolderFile=new File(newParentPath);
		parentFolderFile.mkdirs();

		RenderedImage rendered=imageToRenderedImage(image);
		ImageIO.write(rendered, DEFAULT_IMAGE_EXTENSION, newImageFile);
		return newImageFile;
	}
	public RenderedImage imageToRenderedImage(Image image){
		BufferedImage bImage= new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D bImageGraphics = bImage.createGraphics();
		bImageGraphics.drawImage(image, null, null);
		return (RenderedImage)bImage;
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
	 * @param imageType Type of the image which is to be loaded. Includes gridObject or tileImage
	 * @return File s with the path of the image
	 */
	public File loadImage(String name, String imageType){
		return new File(imagePath+imageType+name);
	}
	/**
	 * Returns a list of all the names of currently saved images in the default image
	 * folder. 
	 * @return List of names of images
	 */
	private List<String> getSavedImageList(String path){
		return new FileLister().getFileList(path);
	}
	public static void main(String[] args){
		ImageManager image=new ImageManager();
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("jpg", "JPG", "png", "PNG", "gif");
		chooser.setFileFilter(filter);
		int returnVal=chooser.showOpenDialog(null);
		File f=chooser.getSelectedFile();
		try {
			image.storeImage("TestImage2", f, "TileImage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
