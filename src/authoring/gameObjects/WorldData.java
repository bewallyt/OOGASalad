package authoring.gameObjects;

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
	private Map<String, File> mySongs;
	private String primaryMap;
	private String currentMap;
    private Map<String,ItemData> myItems;
    private Map<String,WeaponData> myWeapons;
    private String[] arenaLabels;
	
	public WorldData(){
		currentMap = null;
		myLevels = new HashMap<String, MapData>();
		myImages = new HashMap<String, File>();
		mySongs = new HashMap<String, File>();
        myWeapons = new HashMap<String, WeaponData>();
        myItems = new HashMap<String, ItemData>();
        arenaLabels = null;
	}

	public void addLevel(String s, MapData md) {
		myLevels.put(s, md);
	}

	public MapData getCurrentMap(){
		return myLevels.get(currentMap);

	}

    public String[] getArenaLabels(){return arenaLabels;}
    public String getCurrentMapName(){
        return currentMap;
    }
    public File getImage(String fileName) {
        return myImages.get(fileName);
    }

	protected Map<String, File> getImages() {
		return myImages;
	}
	public File getSong(String fileName) {
		return myImages.get(fileName);
	}
	public MapData getMap(String s) {
		return myLevels.get(s);
	}
	public Map<String, MapData> getMaps() {
		return myLevels;
	}
	public Map<String, ItemData> getMyItems() {
		return myItems;
	}
	public List<RandomEnemy> getMyRandomEnemies(){
		return myLevels.get(currentMap).getMyRandomEnemies();
	}
	public PlayerData getPlayData() {
		return myLevels.get(currentMap).getPlayData();
	}
	public String getPrimaryMap() {
		return primaryMap;
	}
	public Map<String,WeaponData> getMyWeapons(){
		return myWeapons;
	}

    protected void saveBarrier(BarrierData barrier){
    	myLevels.get(currentMap).saveBarrier(barrier);
    }
	public void saveHealer(HealerData myHealer) {
		myLevels.get(currentMap).saveHealer(myHealer);
	}
    protected void saveDoor(DoorData door){
    	myLevels.get(currentMap).saveDoor(door);
    }
	protected void saveEncounter(EncounterData myEncounter) {
		myLevels.get(currentMap).saveEncounter(myEncounter);
	}
	protected void saveImage(String s, File f) {
		myImages.put(s, f);
	}
	public void saveSong(String s, File f) {
		mySongs.put(s, f);
	}
	public void saveItem(String n, ItemData it){
		myItems.put(n,it);
	}
	protected void saveNPC(NPCData myNPC) {
		myLevels.get(currentMap).saveNPC(myNPC);
	}
	protected void savePlayer(PlayerData player) {
		myLevels.get(currentMap).savePlayer(player);
	}
	protected void saveRandomEnemy(RandomEnemy re){
		myLevels.get(currentMap).saveRandomEnemy(re);
	}
	public void saveWeapons(String n, WeaponData wp){
		myWeapons.put(n,wp);
	}


    protected void setArenaLabels(String[] al){ arenaLabels=al;}
    public void setCurrentMap(String s){
        currentMap = s;
    }
    public void setMap(String map){
        currentMap=map;
    }
    public void setPrimaryMap(String s){
        primaryMap = s;
    }


}
