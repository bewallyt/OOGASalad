package authoring;

import java.io.File;
import java.util.*;

public class MapData {
	
    private Map<String,Item> myItems;
    private Map<String,Weapon> myWeapons;
    private List<BarrierData> myBarriers;
    private List<DoorData> myDoors;
    private PlayerData playerData;
	private List<List<TileData>> myTiles;
	private List<RandomEnemy> myRandomEnemies;

	private List<NPCData> myNPCs;
	

	public MapData(int height, int width){
        myWeapons = new HashMap<String, Weapon>();
        myItems = new HashMap<String, Item>();
        myBarriers = new ArrayList<BarrierData>();
        myDoors = new ArrayList<DoorData>();
        myRandomEnemies = new ArrayList<RandomEnemy>();
        myNPCs = new ArrayList<NPCData>();


		myTiles = new ArrayList<List<TileData>>(height);
		for(int i = 0; i < height; i++){
			List<TileData> temp = new ArrayList<TileData>();
			for(int j = 0; j < width; j++){
				temp.add(new TileData(null));
			}
			myTiles.add(i, temp);
		}
	}
	public TileData getTileData(int row, int col){
		return myTiles.get(row).get(col);
	}
	
	protected void addTileData(int row, int col, TileData td){
		myTiles.get(row).set(col, td);
	}
	
	public int getMapWidth(){
		return myTiles.get(0).size();
	}
	public Map<String, Item> getMyItems() {
		return myItems;
	}

    public void saveItem(String n, Item it){ myItems.put(n,it);}

	public PlayerData getPlayData() {
		return playerData;
	}

	public void savePlayer(PlayerData player) {
		playerData = player;
	}

	public int getMapLength(){
		return myTiles.size();
	}
	public void saveWeapons(String n, Weapon wp){ myWeapons.put(n,wp);}

    public Map<String,Weapon> getMyWeapons(){ return myWeapons;}

    public void saveRandomEnemy(RandomEnemy re){myRandomEnemies.add(re);}

    public List<RandomEnemy> getMyRandomEnemies(){ return myRandomEnemies;}

    public void saveBarrier(BarrierData barrier){myBarriers.add(barrier);}
    
    public List<BarrierData> getBarriers(){return myBarriers;}
    
    public void saveDoor(DoorData door){myDoors.add(door);}

    public List<DoorData> getDoors(){return myDoors;}
    
	public void saveNPC(NPCData myNPC) { myNPCs.add(myNPC); }
	
	public List<NPCData> getNPCs() { return myNPCs; }

}
