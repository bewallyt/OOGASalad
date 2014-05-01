package Data;

import java.io.File;

/**
 * Abstract class that enables easy creation of classes to handle the loading of storing of various
 * media types into the project filesystem. 
 * @author Davis Treybig
 *
 */
public abstract class AbstractFileManager {
	public static final String SRC="src/";
	protected String[] VALID_FILE_EXTENSIONS;
	protected String DEFAULT_FILE_EXTENSION;
	
	public AbstractFileManager(String[] valid, String defaul) {	
		VALID_FILE_EXTENSIONS=valid;
		DEFAULT_FILE_EXTENSION=defaul;
	}
	
	/**
	 * Checks the extension of the loaded image file to ensure it is an image. 
	 * @param s String version of the file extension
	 * @return Returns true if the extension is a valid image extension
	 */
	protected boolean checkFileExtension(String s){
		for(String validExtension: VALID_FILE_EXTENSIONS){
			if(s.equals(validExtension)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Returns the file extension of a given file. For instance, "tester.txt" would return ".txt"
	 * @param f File to be analyzed
	 * @return Extension of the file
	 */
	protected String getFileExtension(File f){
		String extension="";
		int i=f.getName().lastIndexOf('.');
		if(i>0){
			extension=f.getName().substring(i);
		}
		return extension;
	}
}
