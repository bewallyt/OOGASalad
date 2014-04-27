package Data;

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

public class WorldDataManager {
	public WorldDataManager() {}
		
	
	public void saveWorld(World w, String fileName) {
		WorldData worldData = new WorldData();		
		if (w instanceof WalkAroundWorld) {
			String mapName = ((WalkAroundWorld) w).getID();
		
			int width = ((WalkAroundWorld) w).getTileGridWidth();
			int height = ((WalkAroundWorld) w).getTileGridHeight();
			MapData md = new MapData(width, height);
			Tile[][] tileMatrix = ((WalkAroundWorld) w).getTileMatrix();

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Tile t = tileMatrix[j][i];
					String s = t.getBackgroundImageName();
					md.getTileData(i, j).setImageName(s);
				}
			}
			
			List<GridObject> gridObjectList = ((WalkAroundWorld) w).getGridObjectList();
			for (GridObject g : gridObjectList) {
				if (g instanceof Barrier) {
					BarrierData barrierData = new BarrierData(g.getX()/Constants.TILE_SIZE,
							g.getY()/Constants.TILE_SIZE,
							g.getWidth()/Constants.TILE_SIZE,
							g.getHeight()/Constants.TILE_SIZE,
							g.getImageFile());
					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(barrierData);
				} else if (g instanceof Door) {			
					DoorData doorData = new DoorData(g.getX()/Constants.TILE_SIZE, 
							g.getY()/Constants.TILE_SIZE,
							g.getWidth()/Constants.TILE_SIZE,
							g.getHeight()/Constants.TILE_SIZE,
							g.getImageFile(), 
							g.getX()/Constants.TILE_SIZE,
							g.getY()/Constants.TILE_SIZE,
							((Door) g).getToMap()
		                    );
					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(doorData);
				} else if (g instanceof Enemy) {
					// need to fix this when enemy authoring works
//					Enemy e = (Enemy) g;
//					
//					Map<String,Integer> attributeValues1 = new HashMap<String,Integer>();
//					for (String key : e.getStatsMap().keySet()) {
//						attributeValues1.put(key,  e.getStatsMap().get(key).getValue());
//					}
//					
//					List<Weapon> eWeaponNames = e.getWeaponList();
//					String [] eWeps = new String[eWeaponNames.size()];
//					int wCounter = 0;
//					for (Weapon wep : eWeaponNames) {
//						String wepName = wep.toString();
//						eWeps[wCounter] = wepName;
//						wCounter++;
//					}
//					
//					EnemyData enemyData = new EnemyData(g.getX()/Constants.TILE_SIZE,
//							g.getY()/Constants.TILE_SIZE,
//							g.getImageFile(),
//							g.toString(),
//							attributeValues1,
//							eWeps,
//							1, // move check this
//				            ((Enemy) g).getMoney(),
//				            ((Enemy) g).getExperience());

//					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(enemyData);
				}
				
			}
			
			Player p = w.getPlayer();
			int pX = p.getX();
			int pY = p.getY();
			String pImageName = "";
			String pName = "player1";
			
			String[] pAnimImages = p.getAnimImages();
			
			Map<String,Integer> attributeValues = new HashMap<String,Integer>();
			for (String key : p.getStatsMap().keySet()) {
				attributeValues.put(key,  p.getStatsMap().get(key).getValue());
			}
						
			List<Weapon> pWeaponNames = p.getWeaponList();
			String [] pWeps = new String[pWeaponNames.size()];
			int wCounter = 0;
			for (Weapon wep : pWeaponNames) {
				String wepName = wep.toString();
				pWeps[wCounter] = wepName;
				wCounter++;
			}
			List<Item> pItemNames = p.getItems();
			String [] pItems = new String[pItemNames.size()];
			int iCounter = 0;
			for (Item item : pItemNames) {
				String itemName = item.toString();
				pItems[iCounter] = itemName;
				iCounter++;
			}

			PlayerData playerData = new PlayerData(p.getStartX()/Constants.TILE_SIZE, p.getStartY()/Constants.TILE_SIZE, pImageName, pName, attributeValues, pWeps, pItems);
			playerData.setImages(pAnimImages);
			md.savePlayer(playerData);
			worldData.addLevel(mapName, md);
			worldData.setMap(mapName);
			worldData.setPrimaryMap(mapName);
			DataManager dm = new DataManager();
			dm.setWorldData(fileName, worldData);
			dm.saveWorldDataToFile(fileName);
			
		}
	}
	

}
