package Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.Weapon;
import engine.world.Tile;
import engine.world.WalkAroundWorld;
import engine.world.World;
import authoring.gameObjects.MapData;
import authoring.gameObjects.PlayerData;
import authoring.gameObjects.WorldData;

public class WorldDataManager {
	public WorldDataManager() {}
	
	public void saveMap(WorldData wd, String mapDataKey, Player player, String fileName) {
		WorldData worldData = wd;

        if (worldData == null) {
        	return;
        }
		PlayerData playerData = worldData.getMap(mapDataKey).getPlayData();		
		List<Weapon> wList = player.getWeaponList();
		String [] weaponListNames = new String[wList.size()];
		int wCounter = 0;
		for (Weapon wep : wList) {
			String wepName = wep.toString();
			weaponListNames[wCounter] = wepName;
			wCounter++;
		}
		List<Item> itemList = player.getItems();
		String [] itemListNames = new String[itemList.size()];
		int iCounter = 0;
		for (Item item : itemList) {
			String itemName = item.toString();
			itemListNames[iCounter] = itemName;
			iCounter++;
		}
		worldData.getMap(mapDataKey).getPlayData().setWeapons(weaponListNames);
		worldData.getMap(mapDataKey).getPlayData().setItems(itemListNames);
		DataManager dm = new DataManager();
		dm.setWorldData(fileName, worldData);
		dm.saveWorldDataToFile(fileName);
	}
	
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
			
		//	attributeValues.put("damage", p.getStatsMap().get("damage").getValue());
		//	attributeValues.put("speed", p.getStatsMap().get("speed").getValue());
		//	attributeValues.put("defense", p.getStatsMap().get("defense").getValue());
		//	attributeValues.put("health", p.getStatsMap().get("health").getValue());
		//	attributeValues.put("level", p.getStatsMap().get("level").getValue());
			
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

			PlayerData playerData = new PlayerData(pX, pY, pImageName, pName, attributeValues, pWeps, pItems);
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
