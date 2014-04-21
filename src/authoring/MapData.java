package authoring;

import java.io.File;
import java.util.*;

public class MapData {

    private PlayerData playerData;
	private List<List<TileData>> myTiles;
	private List<RandomEnemy> myRandomEnemies;
	private List<EnemyData> enemyDatas;
	private List<NPCData> myNPCs;
	public MapData(int height, int width){
        myRandomEnemies = new ArrayList<RandomEnemy>();
        enemyDatas = new ArrayList<EnemyData>();
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

	public PlayerData getPlayData() {
		return playerData;
	}

	public void savePlayer(PlayerData player) {
		playerData = player;
	}

	public int getMapLength(){
		return myTiles.size();
	}

    public void saveRandomEnemy(RandomEnemy re){myRandomEnemies.add(re);}

    public List<RandomEnemy> getMyRandomEnemies(){ return myRandomEnemies;}

    public void saveBarrier(BarrierData barrier){myTiles.get(barrier.getX()).get(barrier.getY()).addGridObjectData(barrier);}
    
    public void saveDoor(DoorData door){myTiles.get(door.getX()).get(door.getY()).addGridObjectData(door);}

	public void saveNPC(NPCData myNPC) { myNPCs.add(myNPC); }
	
	public List<NPCData> getNPCs() { return myNPCs; }
	
    public void saveEnemy(EnemyData enemy) {enemyDatas.add(enemy);

    }

    public List<EnemyData> getEnemies() {return enemyDatas;
    }


}
