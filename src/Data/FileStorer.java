package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import authoring.WorldData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileStorer {
	public static final String DEFAULT_SRC_FILE="src/";
	public static final String DEFAULT_SAVED_GAME_PACKAGE="SavedGames/";
	private String savedGamesPath;
	//private Gson gson=new Gson();
	public FileStorer() {
		String path = System.getProperty("user.dir")+"/"+DEFAULT_SRC_FILE+DEFAULT_SAVED_GAME_PACKAGE; 
		savedGamesPath = path.replaceAll("\\\\", "/");	
		init();
	}
	/**
	 * Method used to store an instance of the WorldData class into a JSON data file. This allows
	 * users to save games they have created. 
	 * @param name Name to save the game as
	 * @param world WorldData class that is to be saved
	 */
	public void storeWorldData(String name, WorldData world){
    	File folder = new File(savedGamesPath);
		File jsonFile=new File(folder, name);
		try(Writer writer=new FileWriter(jsonFile);){
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			Gson gson = gsonBuilder.create();
			gson.toJson(world, writer);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Writes a String to a file
	 * @param fileName String to name the file
	 * @param s String to be saved
	 */
	private void writeFile(String fileName, String s){
		BufferedWriter writer=null;
		try{
			File folder = new File(savedGamesPath);

			File jsonFile=new File(folder, fileName);
			writer=new BufferedWriter(new FileWriter(jsonFile));
			writer.write(s);
			writer.close();	
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}	
	/**
	 * Loads the saved game JSON file from the SavedGames folder. See "getSavedGameList" method to see a list of
	 * currently saved games. 
	 * @param fileName Name of the file that is to be loaded
	 * @return Returns the WorldData object that was originally stored
	 * @throws IOException
	 */
	public WorldData getWorldData(String fileName) throws IOException {
		FileReader fr=new FileReader(savedGamesPath+fileName);
		BufferedReader br=new BufferedReader(fr);
		try{
			StringBuilder sb=new StringBuilder();
			String line;
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			//System.out.println(sb.toString());
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			Gson gson = gsonBuilder.create();
			WorldData world=gson.fromJson(sb.toString(), WorldData.class);
			return world;
		} finally{
			br.close();
		}
	}
	/**
	 * Method used to access the string names of all saved game files currently in the 
	 * saved game directory. See "loadFile" method to actually load the WorldData class 
	 * corresponding to that file. 
	 * @return List of names of the saved game files. 
	 */
	public List<String> getSavedGameList(){
		return new FileLister().getFileList(savedGamesPath);
	}
	public static void main(String[] args){
		FileStorer f=new FileStorer();
		f.writeFile("test.txt", "Hi");
	}
	
	private void init() {
		File theDir = new File(savedGamesPath);

		  // if the directory does not exist, create it
		  if (!theDir.exists()) {
		    //System.out.println("creating directory: " + savedGamesPath);
		    boolean result = theDir.mkdir();  

		     if(result) {    
		      // System.out.println("DIR created");  
		     }
		  }
	}
}

