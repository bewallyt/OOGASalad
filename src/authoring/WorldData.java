package authoring;

import java.io.File;
import java.util.*;

public class WorldData {

	private Map<String, MapData> myLevels;
	private Map<String, File> myImages;
    private Map<String,Item> myItems;
    private Map<String,Weapon> myWeapons;
    private List<BarrierObject> myBarriers;
    private List<DoorObject> myDoors;
    private PlayerData playData;
	protected static final String DEFAULT_MAP = "defaultworldkey";
	protected static final int DEFAULT_MAP_WIDTH = 30;
	protected static final int DEFAULT_MAP_HEIGHT = 30;
    private List<RandomEnemy> myRandomEnemies;

	private String currentMapName;
	
	public WorldData(){

		myLevels = new HashMap<String, MapData>();
		// myLevels.put(DEFAULT_MAP, new MapData(10, 10));
		myImages = new HashMap<String, File>();
		myItems = new HashMap<String, Item>();
		myWeapons = new HashMap<String, Weapon>();
        myRandomEnemies = new ArrayList<RandomEnemy>();
        myBarriers=new ArrayList<BarrierObject>();
        myDoors=new ArrayList<DoorObject>();
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

    public void saveRandomEnemy(RandomEnemy re){myRandomEnemies.add(re);}

    public List<RandomEnemy> getMyRandomEnemies(){ return myRandomEnemies;}

    public void saveBarrier(BarrierObject barrier){myBarriers.add(barrier);}
    
    public List<BarrierObject> getBarriers(){return myBarriers;}
    
    public void saveDoor(DoorObject door){myDoors.add(door);}
    
    public List<DoorObject> getDoors(){return myDoors;}
    
    
}
