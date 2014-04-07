package Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileLister {
	public FileLister() {
	}
	public List<String> getFileList(String directoryPath){
		File folder=new File(directoryPath);
		File[] listOfFiles=folder.listFiles();
		List<String> fileNameList=new ArrayList<String>();
		if(listOfFiles == null) {
			return fileNameList;
		}
		for(File f: listOfFiles){
			fileNameList.add(f.getName());
		}
		return fileNameList;
		
	}

}
