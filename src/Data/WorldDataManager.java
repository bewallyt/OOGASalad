package Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Constants;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.Weapon;
import engine.world.Tile;
import engine.world.WalkAroundWorld;
import engine.world.World;
import authoring.gameObjects.BarrierData;
import authoring.gameObjects.DoorData;
import authoring.gameObjects.EnemyData;
import authoring.gameObjects.MapData;
import authoring.gameObjects.PlayerData;
import authoring.gameObjects.WorldData;

/*
 * This class saves the state of a running game to a file. 
 * A game can be saved at multiple stages by invoking the 
 * 'Save' menu item. The game engine uses it own set of objects
 * like World, Player, Enemy etc, This module transforms the data
 * engine objects to authoring objects WorldData, PlayerData,
 * EnemyData etc and saves these to a file.
 * User can resume a game from any of the points he has saved
 * by loading the relevant file at runtime.
 */
public class WorldDataManager {
	public WorldDataManager() {}
		
	/**
	 * 
	 * @param w Engine world object
	 * @param fileName Name of file to save world
	 */
	public void saveWorld(World w, String fileName) {
		WorldData worldData = new WorldData();		
		if (w instanceof WalkAroundWorld) {
			String mapName = ((WalkAroundWorld) w).getID();
		   
			// setup map data
			int width = ((WalkAroundWorld) w).getTileGridWidth();
			int height = ((WalkAroundWorld) w).getTileGridHeight();
			MapData md = new MapData(width, height);
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
					BarrierData barrierData = transformBarrier(g);
					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(barrierData);
				} else if (g instanceof Door) {			
					DoorData doorData = transformDoor(g);
					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(doorData);
				} else if (g instanceof Enemy) {
					EnemyData enemyData = transformEnemy(g);
					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(enemyData);			
				}
				
			}

			// store player data
			PlayerData playerData = transformPlayer(w);
			md.savePlayer(playerData);
			
			// store map in world data
			worldData.addLevel(mapName, md);
			worldData.setMap(mapName);
			worldData.setPrimaryMap(mapName);
			
			// finally save the file
			DataManager dm = new DataManager();
			dm.setWorldData(fileName, worldData);
			dm.saveWorldDataToFile(fileName);
			
		}
	}
	
	/**
	 * 
	 * @param w Engine world object
	 * @return Transforms engine Player to authoring PlayerData
	 */
	private PlayerData transformPlayer(World w) {
		Player p = w.getPlayer();
		
		// Authoring limitation - player and enemy sprites have to be 
		// either Zelda or Ash
	//	String pImageName = p.getImageFile();
		String pImageName = "Ash";
		String pName = p.toString();		
		
		String[] pAnimImages = p.getAnimImages();		
		Map<String,Integer> attributeValues = new HashMap<String,Integer>();
		for (String key : p.getStatsMap().keySet()) {
			attributeValues.put(key,  p.getStatsMap().get(key).getValue());
		}
					
		List<Weapon> pWeapons = p.getWeaponList();
		String[] pWeps = getWeaponNames(pWeapons);
		
		List<Item> pItems = p.getItems();
		String [] pItemNames = getItemNames(pItems);	

		PlayerData playerData = new PlayerData(p.getStartX()/Constants.TILE_SIZE, p.getStartY()/Constants.TILE_SIZE, pImageName, pName, attributeValues, pWeps, pItemNames);
		playerData.setImages(pAnimImages);
		playerData.setMyMoney(p.getMoney());
		playerData.setMyExperience(p.getExperience());
		
		return playerData;
	}
	
	/**
	 * 
	 * @param weapons Weapon object list
	 * @return Returns list of weapon names
	 */
	private String[] getWeaponNames(List<Weapon> weapons) {
		if (weapons == null) {
			return new String[0];
		}
		String [] wepNames = new String[weapons.size()];
		int counter = 0;
		for (Weapon wep : weapons) {
			if (wep == null) {
				continue;
			}
			String wepName = wep.toString();
			wepNames[counter] = wepName;
			counter++;
		}
		return wepNames;		
	}
	
	/**
	 * 
	 * @param items Item object list
	 * @return Returns list of item names
	 */
	private String[] getItemNames(List<Item> items) {
		if (items == null) {
			return new String[0];
		}
		String [] itemNames = new String[items.size()];
		int counter = 0;
		for (Item item : items) {
			if (item == null) {
				continue;
			}
			String itemName = item.toString();
			itemNames[counter] = itemName;
			counter++;
		}
		return itemNames;		
	}	
	
	/**
	 * 
	 * @param g Engine GridObject object
	 * @return Transforms engine Barrier to authoring BarrierData
	 */
	private BarrierData transformBarrier(GridObject g) {
		BarrierData barrierData = new BarrierData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE,
				g.getWidth()/Constants.TILE_SIZE,
				g.getHeight()/Constants.TILE_SIZE,
				g.getImageFile());	
		return barrierData;
	}
	
	/**
	 * 
	 * @param g Engine GridObject object
	 * @return Transforms engine Door to authoring DoorData
	 */
	private DoorData transformDoor(GridObject g) {
		DoorData doorData = new DoorData(g.getX()/Constants.TILE_SIZE, 
				g.getY()/Constants.TILE_SIZE,
				g.getWidth()/Constants.TILE_SIZE,
				g.getHeight()/Constants.TILE_SIZE,
				g.getImageFile(), 
				g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE,
				((Door) g).getToMap()
                );
		return doorData;
	}
	
	/**
	 * 
	 * @param w Engine world object
	 * @return Transforms engine Enemy to authoring EnemyData
	 */
	private EnemyData transformEnemy(GridObject g) {
		Enemy e = (Enemy) g;
		
		String[] eAnimImages = e.getAnimImages();	
		Map<String,Integer> attributeValues1 = new HashMap<String,Integer>();
		for (String key : e.getStatsMap().keySet()) {
			attributeValues1.put(key,  e.getStatsMap().get(key).getValue());
		}
		
		List<Weapon> eWeapons = e.getWeaponList();
		String[] eWeps = getWeaponNames(eWeapons);
		
		// Authoring limitation - player and enemy sprites have to be 
		// either Zelda or Ash
	//	String img = g.getImageFile();
		String img = "Zelda";
		
		EnemyData enemyData = new EnemyData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE,
				img,
				g.toString(),
				attributeValues1,
				eWeps,
				1, 
	            ((Enemy) g).getMoney(),
	            ((Enemy) g).getExperience());
		
		enemyData.setImages(eAnimImages);
		enemyData.setMyMoney(e.getMoney());
		enemyData.setMyExperience(e.getExperience());
		
		return enemyData;
		
	}
}
