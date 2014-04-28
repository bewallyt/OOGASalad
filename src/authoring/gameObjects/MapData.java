package authoring.gameObjects;

import java.io.File;
import java.util.*;

/**
 * CLass that handles all information relevant to an individual map
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class MapData {

    private PlayerData playerData;
	private List<List<TileData>> myTiles;
	private List<RandomEnemy> myRandomEnemies;
	private List<EnemyData> enemyDatas;
	private String mySong;
	
	public MapData(int height, int width){
        myRandomEnemies = new ArrayList<RandomEnemy>();
        enemyDatas = new ArrayList<EnemyData>();

		myTiles = new ArrayList<List<TileData>>(height);
		for(int i = 0; i < height; i++){
			List<TileData> temp = new ArrayList<TileData>();
			for(int j = 0; j < width; j++){
				temp.add(new TileData(null));
			}
			myTiles.add(i, temp);
		}
	}
	/**
	 * Gets the TileData at a specified row and column
	 * @param row Row of the tile
	 * @param col Column of the tile
	 * @return TileData at the row and column
	 */
	public TileData getTileData(int row, int col){
		return myTiles.get(row).get(col);
	}
	/**
	 * Adds a TileData to a specified row and column
	 * @param row Row of the tile
	 * @param col Column of the tile
	 * @param td TileData to be placed
	 */
	public void addTileData(int row, int col, TileData td){
		myTiles.get(row).set(col, td);
	}
	/**
	 * Returns the width of the map
	 */
	public int getMapWidth(){
		return myTiles.get(0).size();
	}
	/**
	 * Returns the player data of the map
	 */
	public PlayerData getPlayData() {
		return playerData;
	}
	/**
	 * Saves the player to the map
	 * @param player PlayerData to be saved
	 */
	public void savePlayer(PlayerData player) {
		playerData = player;
	}
	/**
	 * Returns the length of the map
	 */
	public int getMapLength(){
		return myTiles.size();
	}

	/**
	 * Saves a random enemy to the map
	 * @param re RandomEnemy to be saved
	 */
    public void saveRandomEnemy(RandomEnemy re){myRandomEnemies.add(re);}

    /**
     * Gets the random enemies stored on the map
     * @return List of RandomEnemy on the map
     */
    public List<RandomEnemy> getMyRandomEnemies(){ return myRandomEnemies;}

    /**
     * Saves a barrier to the map
     * @param barrier BarrierData to be saved
     */
    public void saveBarrier(BarrierData barrier){myTiles.get(barrier.getX()).get(barrier.getY()).addGridObjectData(barrier);}
    
    /**
     * Saves a door to the map
     * @param door DoorData to be saved
     */
    public void saveDoor(DoorData door){myTiles.get(door.getX()).get(door.getY()).addGridObjectData(door);}
    
    /**
     * Saves a healer to the map
     * @param myHealer HealerData to be saved
     */
	public void saveHealer(HealerData myHealer) {myTiles.get(myHealer.getX()).get(myHealer.getY()).addGridObjectData(myHealer);}
	
	/**
	 * Saves an NPC to the map
	 * @param myNPC NPCData to be saved
	 */
	public void saveNPC(NPCData myNPC) {myTiles.get(myNPC.getX()).get(myNPC.getY()).addGridObjectData(myNPC);
	System.out.println("NPC Added");}
	
	
	/**
	 * Saves an enemy to the map
	 * @param enemy EnemyData to be saved
	 */
    public void saveEnemy(EnemyData enemy) {enemyDatas.add(enemy);}
    /**
     * Returns a list of enemies on the Map
     * @return List of EnemyData
     */
    public List<EnemyData> getEnemies() {return enemyDatas;}
    /**
     * Saves an encounter to the map
     * @param myEncounter EncounterData to be saved
     */
	public void saveEncounter(EncounterData myEncounter) {
		myTiles.get(myEncounter.getX()).get(myEncounter.getY()).addGridObjectData(myEncounter);
	}
	public void saveShopkeeper(ShopkeeperData myShopkeeper) {
		myTiles.get(myShopkeeper.getX()).get(myShopkeeper.getY()).addGridObjectData(myShopkeeper);
	}
	/**
	 * Adds a song to the map
	 * @param s Name of the song
	 */
    public void addSong(String s){
    	mySong = s;
    }
    /**
     * Returns the song associated with the map
     */
    public String getSong(){
    	return mySong;
    }

}
