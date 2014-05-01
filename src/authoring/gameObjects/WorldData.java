package authoring.gameObjects;

/**
 * Class that encapsulates all of the information needed to store a saved version of the game. 
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
    private PlayerData playerData;
	
	public WorldData(){
		currentMap = null;
		myLevels = new HashMap<String, MapData>();
		myImages = new HashMap<String, File>();
		mySongs = new HashMap<String, File>();
        myWeapons = new HashMap<String, WeaponData>();
        myItems = new HashMap<String, ItemData>();
        arenaLabels = null;
	}

	/**
	 * Adds a new level to the game
	 * @param s Name of the level
	 * @param md MapData of the added level
	 */
	public void addLevel(String s, MapData md) {
		myLevels.put(s, md);
	}

	/**
	 * Returns the current map (the map the user is currently viewing)
	 * @return MapData of the current map
	 */
	public MapData getCurrentMap(){
		return myLevels.get(currentMap);

	}
	/**
	 * Returns the arena labels currently being used
	 */
    public String[] getArenaLabels(){return arenaLabels;}
    /**
     * Returns the name of the current Map
     * @return String name of map
     */
    public String getCurrentMapName(){
        return currentMap;
    }
    /**
     * Returns the File corresponding to the specified image name
     * @param fileName Name of the image
     * @return File of image
     */
    public File getImage(String fileName) {
        return myImages.get(fileName);
    }
    /**
     * Returns a map of all current images being saved
     * @return Map which maps the string name of images to their file
     */
	protected Map<String, File> getImages() {
		return myImages;
	}
	/**
	 * Returns the song with the given filename
	 * @param fileName Name of the song
	 * @return File of the song
	 */
	public File getSong(String fileName) {
		return mySongs.get(fileName);
	}
	
	public String getSongString(String fileName) {
		return mySongs.get(fileName).getPath().replace("\\", "/").substring(3);		
	}
	/**
	 * Returns the map with the specified name
	 * @param s Name of the map
	 * @return MapData of the given map
	 */
	public MapData getMap(String s) {
		return myLevels.get(s);
	}
	/**
	 * Returns all maps saved in the game
	 * @return Mapping of map names to their MapData
	 */
	public Map<String, MapData> getMaps() {
		return myLevels;
	}
	/**
	 * Returns all items stored in the game
	 * @return Mapping of item names to their itemData
	 */
	public Map<String, ItemData> getMyItems() {
		return myItems;
	}
	/**
	 * Gets the random enemies stored in the game
	 * @return
	 */
	public List<RandomEnemy> getMyRandomEnemies(){
		return myLevels.get(currentMap).getMyRandomEnemies();
	}
	/**
	 * Gets the current player data
	 */
	public PlayerData getPlayData() {
		return playerData;
	}
	/**
	 * Returns the current primary map
	 */
	public String getPrimaryMap() {
		return primaryMap;
	}
	/**
	 * Gets the weapons currently saved
	 * @return Mapping of weapon names to their WeaponData
	 */
	public Map<String,WeaponData> getMyWeapons(){
		return myWeapons;
	}

	/**
	 * Saves a barrier
	 * @param barrier BarrierData to be saved
	 */
    protected void saveBarrier(BarrierData barrier){
    	myLevels.get(currentMap).saveBarrier(barrier);
    }
    /**
     * Saves a healer
     * @param myHealer HealerData to be saved
     */
	public void saveHealer(HealerData myHealer) {
		myLevels.get(currentMap).saveHealer(myHealer);
	}
	/**
	 * Saves a door
	 * @param door DoorData to be saved
	 */
    protected void saveDoor(DoorData door){
    	myLevels.get(currentMap).saveDoor(door);
    }
    /**
     * Saves an encounter
     * @param myEncounter EncounterData to be saved
     */
	protected void saveEncounter(EncounterData myEncounter) {
		myLevels.get(currentMap).saveEncounter(myEncounter);
	}
	/**
	 * Saves an image
	 * @param s String name of image
	 * @param f File of image
	 */
	protected void saveImage(String s, File f) {
		myImages.put(s, f);
	}
	/**
	 * Saves a song
	 * @param s Name of song
	 * @param f File of song
	 */
	public void saveSong(String s, File f) {
		mySongs.put(s, f);
	}
	/**
	 * Saves an item
	 * @param n Name of item
	 * @param it ItemData to be saved
	 */
	public void saveItem(String n, ItemData it){
		myItems.put(n,it);
	}
	/**
	 * Saves an NPC
	 * @param myNPC NPCData to be saved
	 */
	protected void saveNPC(NPCData myNPC) {
		myLevels.get(currentMap).saveNPC(myNPC);
	}
	/**
	 * Saves the player
	 * @param player PlayerData to be saved
	 */
	public void savePlayer(PlayerData player) {
		playerData=player;
		//myLevels.get(currentMap).savePlayer(player);
	}

	/**
	 * Saves a random enemy
	 * @param re RandomEnemy to be saved
	 */
	protected void saveRandomEnemy(RandomEnemy re){
		myLevels.get(currentMap).saveRandomEnemy(re);
	}
	/**
	 * Saves a weapon
	 * @param n Name of weapon
	 * @param wp WeaponData to be saved
	 */
	public void saveWeapon(String n, WeaponData wp){
		myWeapons.put(n,wp);
	}
	/**
	 * Sets the arena labels
	 * @param al String[] of arena label names
	 */
    protected void setArenaLabels(String[] al){ arenaLabels=al;}
    public void setCurrentMap(String s){
        currentMap = s;
    }
    /**
     * Sets the current map
     * @param map Name of the map to be made the current map
     */
    public void setMap(String map){
        currentMap=map;
    }
    /**
     * Sets the primary map
     * @param s Name of the map to make primary
     */
    public void setPrimaryMap(String s){
        primaryMap = s;
    }
    /**
	 * Saves an enemy to the current map
	 * @param enemy EnemyData to be saved
	 */
    public void saveEnemy(EnemyData enemy) {myLevels.get(currentMap).saveEnemy(enemy);;}

	public void saveShopkeeper(ShopkeeperData myShopkeeper) {
		myLevels.get(currentMap).saveShopkeeper(myShopkeeper);
	}


}
