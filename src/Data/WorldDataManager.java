package Data;

import java.util.List;

import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.Weapon;
import authoring.PlayerData;
import authoring.WorldData;

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
	

}
