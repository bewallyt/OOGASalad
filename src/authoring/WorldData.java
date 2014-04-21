package authoring;

import java.io.File;
import java.util.*;

public class WorldData {

	private Map<String, MapData> myLevels;
	private Map<String, File> myImages;
	protected static final String DEFAULT_MAP = "defaultworldkey";
	protected static final int DEFAULT_MAP_WIDTH = 30;
	protected static final int DEFAULT_MAP_HEIGHT = 30;

	private String currentMap;
	
	public WorldData(){
        currentMap = null;
		myLevels = new HashMap<String, MapData>();
		myImages = new HashMap<String, File>();
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
	
	public void setCurrentMap(String s){
		currentMap = s;
	}
	
	public MapData getCurrentMap(){
		return myLevels.get(currentMap);

	}
	public Map<String, Item> getMyItems() {
		return myLevels.get(currentMap).getMyItems();
	}

    public void saveItem(String n, Item it){
    	myLevels.get(currentMap).saveItem(n, it);
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
    	myLevels.get(currentMap).saveWeapons(n, wp);
    }

    public Map<String,Weapon> getMyWeapons(){
    	return myLevels.get(currentMap).getMyWeapons();
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
    
    public List<BarrierData> getBarriers(){
    	return myLevels.get(currentMap).getBarriers();
    }
    
    public void saveDoor(DoorData door){
    	myLevels.get(currentMap).saveDoor(door);
    }
    
    public List<DoorData> getDoors(){
    	return myLevels.get(currentMap).getDoors();
    }

    public void saveEnemy(EnemyData enemy) {myLevels.get(currentMap).saveEnemy(enemy);}
    public List<EnemyData> getMyEnemyData(){ return myLevels.get(currentMap).getEnemies();}

}
