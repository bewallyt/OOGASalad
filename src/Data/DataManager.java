package Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.WorldData;



/** 
 * Manages WorldData objects. Keeps a map of WorldData objects associated with
 * each WorldData file.
 */

public class DataManager {
	private Map<String, WorldData> myWorldDatas;
	private FileStorer myFileStorer;
	
	public DataManager() {
		myWorldDatas = new HashMap<String, WorldData>();
		myFileStorer = new FileStorer();
		init();
	}
	
	private void init() {
		List<String> gameList = myFileStorer.getSavedGameList();
		for (String gameName : gameList) {
			try {
			//	System.out.println("gameName : " + gameName);
				WorldData worldData = myFileStorer.getWorldData(gameName);
				myWorldDatas.put(gameName, worldData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method used to retrieve a WorldData object from map. If object is not
	 * in map, it tries to retrieve from the file. If the file isn't found, it
	 * returns null.
	 * @param worldDataName
	 * @return WorldData
	 */
	public WorldData getWorldData(String worldDataName) {
		WorldData worldData = null;
		if (myWorldDatas.containsKey(worldDataName)) {
			worldData = myWorldDatas.get(worldDataName);
		}
		else {
			worldData = loadWorldDataFromFile(worldDataName);
		}
		return worldData;
	}
	

	/**
	 * Method used to add WorldData object to map.
	 * @param worldDataName
	 * @param worldData
	 */
	public void setWorldData(String worldDataName, WorldData worldData) {
		myWorldDatas.put(worldDataName, worldData);
	}
	
	/**
	 * Method used to save WorldData to file.
	 * @param worldDataName
	 */
	public void saveWorldDataToFile(String worldDataName) {
		myFileStorer.storeWorldData(worldDataName, myWorldDatas.get(worldDataName));
	}
	
	/**
	 * Method used to load worldData from file. It also updates the map.
	 * @param worldDataName
	 * @return WorldData
	 */
	public WorldData loadWorldDataFromFile(String worldDataName) {
		try {
			WorldData worldData = myFileStorer.getWorldData(worldDataName);
			myWorldDatas.put(worldDataName, worldData);
			return worldData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
