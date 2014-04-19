package authoring;

import java.io.File;
import java.util.*;

public class WorldData {

	private Map<String, MapData> myLevels;
	private Map<String, File> myImages;
    private Map<String,Item> myItems;
    private Map<String,Weapon> myWeapons;
    private PlayerData playData;

	private String currentMapName;
	
	public WorldData(){

		myLevels = new HashMap<String, MapData>();
		// myLevels.put(DEFAULT_MAP, new MapData(10, 10));
		myImages = new HashMap<String, File>();
		myItems = new HashMap<String, Item>();
		myWeapons = new HashMap<String, Weapon>();
	}

	public File getImage(String fileName) {
		return myImages.get(fileName);
	}

	public void saveImage(String s, File f) {
		myImages.put(s, f);
	}

	public Map<String, Item> getMyItems() {
		return myItems;
	}

    public void saveItem(String n, Item it){ myItems.put(n,it);}

	public PlayerData getPlayData() {
		return playData;
	}

	public void savePlayer(PlayerData player) {
		playData = player;
	}

	public void addLevel(String s, MapData md) {
		myLevels.put(s, md);
		System.out.println(myLevels.keySet());
	}

	public MapData getMap(String s) {
		return myLevels.get(s);
	}

	public Map<String, MapData> getMaps() {
		return myLevels;
	}
	
	public void setCurrentMap(String s){
		currentMapName = s;
	}
	
	public MapData getCurrentMap(){
		return myLevels.get(currentMapName);

	}

	protected Map<String, File> getImages() {
		return myImages;
	}

    public void saveWeapons(String n, Weapon wp){ myWeapons.put(n,wp);}

    public Map<String,Weapon> getMyWeapons(){ return myWeapons;}


}
