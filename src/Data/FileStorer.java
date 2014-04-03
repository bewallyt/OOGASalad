package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import authoring.WorldData;

import com.google.gson.Gson;

public class FileStorer {
	public static final String DEFAULT_SRC_FILE="src/";
	public static final String DEFAULT_SAVED_GAME_PACKAGE="SavedGames/";
	public FileStorer() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Method used to store an instance of the WorldData class into a JSON data file. This allows
	 * users to save games they have created. 
	 * @param name Name to save the game as
	 * @param world WorldData class that is to be saved
	 */
	public void storeWorldData(String name, WorldData world){
		Gson gson=new Gson();
		String json=gson.toJson(world);
		
		
		
	}
	private void writeFile(String fileName, String jsonString){
		BufferedWriter writer=null;
		try{
			String path = System.getProperty("user.dir")+"/"+DEFAULT_SRC_FILE+DEFAULT_SAVED_GAME_PACKAGE; 
			path = path.replaceAll("\\\\", "/");
			File folder = new File(path);
			
			File jsonFile=new File(folder, fileName);
			writer=new BufferedWriter(new FileWriter(jsonFile));
			writer.write(jsonString);
			writer.close();	
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		FileStorer f=new FileStorer();
		f.writeFile("test.txt", "Hi");
	}
}

