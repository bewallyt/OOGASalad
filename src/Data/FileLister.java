package Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Filelisting class that allows easy access to a list of all files within a directory. 
 * Used for image and saved game management in both the authoring environment and the data
 * package
 * @author Davis Treybig
 *
 */
public class FileLister {
	public FileLister() {
	}
	/**
	 * 
	 * @param directoryPath Directory (such as src/Images) of the files you want to list
	 * @return Returns a list of filenames within the given directory
	 */
	public List<String> getFileList(String directoryPath){
		File folder=new File(directoryPath);
		File[] listOfFiles=folder.listFiles();
		List<String> fileNameList=new ArrayList<String>();
		if(listOfFiles == null) {
			return fileNameList;
		}
		for(File f: listOfFiles){
			if (!f.isHidden()) {
				fileNameList.add(f.getName());
			}
		}
		return fileNameList;
		
	}

}
