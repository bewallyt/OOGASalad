package authoring;

/**
 * @ Pritam M.
 * @ Jacob L.
 * @ Richard Cao.
 * @ Davis T.
 * */

import java.io.File;
import java.util.*;

public class WorldData {

	private Map<String, MapData> myLevels;
	private Map<String, File> myImages;
	private String primaryMap;
	private String currentMap;
    private Map<String,Item> myItems;
    private Map<String,Weapon> myWeapons;
	
	public WorldData(){
        currentMap = null;
		myLevels = new HashMap<String, MapData>();
		myImages = new HashMap<String, File>();
        myWeapons = new HashMap<String, Weapon>();
        myItems = new HashMap<String, Item>();
	}

	public File getImage(String fileName) {
		return myImages.get(fileName);
	}

	public void saveImage(String s, File f) {
		myImages.put(s, f);
	}
	public void setMap(String map){
		currentMap=map;
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
	
	public void setPrimaryMap(String s){
		primaryMap = s;
	}
	
	public String getPrimaryMap() {
		return primaryMap;
	}
	
	public void setCurrentMap(String s){
		currentMap = s;
	}
	
	public String getCurrentMapName(){
		return currentMap;
	}
	public MapData getCurrentMap(){
		return myLevels.get(currentMap);

	}
	public Map<String, Item> getMyItems() {
		return myItems;
	}

    public void saveItem(String n, Item it){
    	myItems.put(n,it);
    }

	public PlayerData getPlayData() {
		return myLevels.get(currentMap).getPlayData();
	}

	public void savePlayer(PlayerData player) {
		myLevels.get(currentMap).savePlayer(player);
	}

	protected Map<String, File> getImages() {
		return myImages;
	}

    public void saveWeapons(String n, Weapon wp){
    	myWeapons.put(n,wp);
    }
    public Map<String,Weapon> getMyWeapons(){ 
    	return myWeapons;
    }

    public void saveRandomEnemy(RandomEnemy re){
    	myLevels.get(currentMap).saveRandomEnemy(re);
    }

    public List<RandomEnemy> getMyRandomEnemies(){
    	return myLevels.get(currentMap).getMyRandomEnemies();
    }

    public void saveBarrier(BarrierData barrier){
    	myLevels.get(currentMap).saveBarrier(barrier);
    }
	public void saveNPC(NPCData myNPC) {
		myLevels.get(currentMap).saveNPC(myNPC);
	}
    
    public void saveDoor(DoorData door){
    	myLevels.get(currentMap).saveDoor(door);
    }

	public void saveEncounter(EncounterData myEncounter) {
		myLevels.get(currentMap).saveEncounter(myEncounter);
	}


}
