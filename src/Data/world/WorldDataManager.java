package Data.world;

import java.util.List;
import java.util.Map;

import util.Constants;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Healer;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.gridobject.person.ShopKeeper;
import engine.world.Tile;
import engine.world.WalkAroundWorld;
import engine.world.World;
import Data.DataManager;
import authoring.gameObjects.BarrierData;
import authoring.gameObjects.DoorData;
import authoring.gameObjects.EnemyData;
import authoring.gameObjects.HealerData;
import authoring.gameObjects.ItemData;
import authoring.gameObjects.MapData;
import authoring.gameObjects.NPCData;
import authoring.gameObjects.PlayerData;
import authoring.gameObjects.ShopkeeperData;
import authoring.gameObjects.WeaponData;
import authoring.gameObjects.WorldData;

/**
 * This class saves the state of a running game to a file. 
 * A game can be saved at multiple stages by invoking the 
 * 'Save' menu item. The game engine uses it own set of objects
 * like World, Player, Enemy etc, This module transforms the data
 * engine objects to authoring objects WorldData, PlayerData,
 * EnemyData etc and saves these to a file.
 * User can resume a game from any of the points he has saved
 * by loading the relevant file at runtime.
 * @author Sanmay Jain
 */
public class WorldDataManager {
	WorldData myWorldData;
	String myOrigFileName;
	String myCurrentMapName;
	DataManager myDM;
	MapData md;
	
	public WorldDataManager(String loadFileName) {
		myOrigFileName = loadFileName;
	}
		
	/**
	 * 
	 * @param w Engine world object
	 * @param fileName Name of file to save world
	 */
	public void saveWorld(World w, String fileName) {
		myDM = new DataManager();
		myWorldData = new WorldData();		
		if (w instanceof WalkAroundWorld) {
			String mapName = ((WalkAroundWorld) w).getID();
		   
			// setup map data
			int width = ((WalkAroundWorld) w).getTileGridWidth();
			int height = ((WalkAroundWorld) w).getTileGridHeight();
			md = new MapData(width, height);
			Tile[][] tileMatrix = ((WalkAroundWorld) w).getTileMatrix();

			// setup tile matrix
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Tile t = tileMatrix[j][i];
					String s = t.getBackgroundImageName();
					md.getTileData(i, j).setImageName(s);
				}
			}
			
			// store grid objects in map data
			List<GridObject> gridObjectList = ((WalkAroundWorld) w).getGridObjectList();
			for (GridObject g : gridObjectList) {
				if (g instanceof Barrier) {
					doBarrierTransform(g);
				} else if (g instanceof Door) {	
					doDoorTransform(g);
				} else if (g instanceof Enemy) {
					doEnemyTransform(g);
				} else if (g instanceof ShopKeeper) {
					doShopkeeperTransform(g);
				} else if (g instanceof Healer) {
					doHealerTransform(g);
				} else if (g instanceof NPC) {
					doNPCTransform(g);
				}
				
			}
			
			// load other maps
			loadOtherMaps();
			
			// store map in world data
			myCurrentMapName = mapName;
			myWorldData.addLevel(mapName, md);
			myWorldData.setMap(mapName);
			myWorldData.setPrimaryMap(mapName);			
			
		}
		
		// store player data
		doPlayerTransform(w.getPlayer());
				
		// finally save the file			
		myDM.setWorldData(fileName, myWorldData);
		myDM.saveWorldDataToFile(fileName);
	}
	
	private void doBarrierTransform(GridObject g) {
		BarrierTransformer tr = new BarrierTransformer(g);
		tr.transform();
		BarrierData barrierData = tr.getTransformedData();
		md.getTileData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(barrierData);
	}
	
	private void doDoorTransform(GridObject g) {
		DoorTransformer tr = new DoorTransformer(g);
		tr.transform();
		DoorData doorData = tr.getTransformedData();
		md.getTileData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(doorData);
	}
	
	private void doEnemyTransform(GridObject g) {
		EnemyTransformer tr = new EnemyTransformer(g);
		tr.transform();
		EnemyData enemyData = tr.getTransformedData();
		md.getTileData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(enemyData);
		saveWeaponDataList(tr.getWeaponDataList());
		
	}
	
	private void doShopkeeperTransform(GridObject g) {
		ShopkeeperTransformer tr = new ShopkeeperTransformer(g);
		tr.transform();
		ShopkeeperData shopkeeperData = tr.getTransformedData();
		md.getTileData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(shopkeeperData);
		saveItemDataList(tr.getItemDataList());
	}
	
	private void doHealerTransform(GridObject g) {
		HealerTransformer tr = new HealerTransformer(g);
		tr.transform();
		HealerData healerData = tr.getTransformedData();
		md.getTileData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(healerData);
	}
	
	private void doNPCTransform(GridObject g) {
		NPCTransformer tr = new NPCTransformer(g);
		tr.transform();
		NPCData npcData = tr.getTransformedData();
		md.getTileData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(npcData);
	}
	
	private void doPlayerTransform(Player p) {
		PlayerTransformer tr = new PlayerTransformer(p, myWorldData);
		tr.transform();
		PlayerData playerData = tr.getTransformedData();
		myWorldData.savePlayer(playerData);
		saveItemDataList(tr.getItemDataList());
		saveWeaponDataList(tr.getWeaponDataList());
	}			
	

	/**
	 * 
	 * Load other maps from original world
	 * 
	 */
	private void loadOtherMaps() {
		if (myOrigFileName == null) {
			return;
		}
		WorldData origWorldData = myDM.getWorldData(myOrigFileName);
		if (origWorldData == null) {
			return;
		}
		Map<String, MapData> levels = origWorldData.getMaps();
		if ((levels == null) || (levels.size() < 2)) {
			return;
		}
		for (String mapName : levels.keySet()) {
			if (!mapName.equals(myCurrentMapName)) {
				MapData mapData = origWorldData.getMap(mapName);
				myWorldData.addLevel(mapName, mapData);
			}
		}
	}	
	
	private void saveWeaponDataList(List<WeaponData> wdList) {
		if (wdList == null) {
			return;
		}
		for (WeaponData wd : wdList) {
			if (wd != null) {
				myWorldData.saveWeapon(wd.getMyName(), wd);
			}
		}
		
	}
	
	private void saveItemDataList(List<ItemData> itemList) {
		if (itemList == null) {
			return;
		}
		for (ItemData item : itemList) {
			if (item != null) {
				myWorldData.saveItem(item.getItemName(), item);
			}
		}
		
	}
	
}
