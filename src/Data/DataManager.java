package Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.WorldData;

public class DataManager {
	private static DataManager instance = null;
	private Map<String, WorldData> myWorldDatas;
	private FileStorer myFileStorer;
	
	private DataManager() {
		myWorldDatas = new HashMap<String, WorldData>();
		myFileStorer = new FileStorer();
		init();
	}
	
	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}
	
	private void init() {
		List<String> gameList = myFileStorer.getSavedGameList();
		for (String gameName : gameList) {
			try {
				WorldData worldData = myFileStorer.getWorldData(gameName);
				myWorldDatas.put(gameName, worldData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public WorldData getWorldData(String worldDataName) {
		return myWorldDatas.get(worldDataName);
	}
	
	public void setWorldData(String worldDataName, WorldData worldData) {
		myWorldDatas.put(worldDataName, worldData);
	}
	
	public void saveWorldDataToFile(String worldDataName, WorldData worldData) {
		myFileStorer.storeWorldData(worldDataName, worldData);
	}
	
	public WorldData loadWorldDataFromFile(String worldDataName) {
		try {
			return myFileStorer.getWorldData(worldDataName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
