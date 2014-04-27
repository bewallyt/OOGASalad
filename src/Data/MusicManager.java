package Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class handles the storing and loading of music for the game. 
 * @author Davis Treybig
 *
 */
public class MusicManager extends AbstractFileManager{

	public static final String DEFAULT_MUSIC_EXTENSION="wav";
	public static final String[] VALID_MUSIC_EXTENSIONS={".mp3", ".wav"};
	public static final String DEFAULT_MUSIC_FOLDER=SRC+"Music/";
	public MusicManager() {
		super(VALID_MUSIC_EXTENSIONS, DEFAULT_MUSIC_EXTENSION);
	}
	/**
	 * 
	 * @param fileName Name of the song
	 * @param musicFile File from which to copy the song
	 * @return Returns the file within the project of the newly copied song
	 * @throws IOException
	 */
	public File storeMusicFile(String fileName, File musicFile) throws IOException{
		String extension=getFileExtension(musicFile);
		if(checkFileExtension(extension)){
			Path source=Paths.get(musicFile.getAbsolutePath());
			String newParentPath=DEFAULT_MUSIC_FOLDER;
			String newPath=newParentPath+fileName+extension;
			
			Path newDirectory=Paths.get(newPath);
			File newImageFile=new File(newPath);
			File parentFolderFile=new File(newParentPath);
			parentFolderFile.mkdirs();
			try{
				Files.copy(source, newDirectory);
				System.out.println("Tester");
			}
			catch(FileAlreadyExistsException e){
				return null;
			}
			return (newImageFile);
		}
		return null;
	}
	
	public File loadMusicFile(String songName){
		return new File(DEFAULT_MUSIC_FOLDER+songName);
	}

	public static void main(String[] args) throws IOException{
		MusicManager m=new MusicManager();
		//JFileChooser chooser=new JFileChooser();
		//int returnVal=chooser.showOpenDialog(null);
		//File f=chooser.getSelectedFile();
		//m.storeMusicFile("testSong", f);
		File fil=m.loadMusicFile("testSong");
		System.out.println(fil.getAbsolutePath());
	}

}
