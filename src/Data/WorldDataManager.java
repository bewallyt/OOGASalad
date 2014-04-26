package Data;

import java.util.List;

import engine.gridobject.person.Player;
import engine.item.Weapon;
import authoring.PlayerData;
import authoring.WorldData;

public class WorldDataManager {
	public WorldDataManager() {}
	
	public void saveMap(WorldData wd, String mapDataKey, Player player, String fileName) {
		WorldData worldData = wd;

        if (worldData == null)	 {
        	System.out.println("worldData is null");
        }
		PlayerData playerData = worldData.getMap(mapDataKey).getPlayData();		
		List<Weapon> wList = player.getWeaponList();
		System.out.println("weaponlist.size" + wList.size());
		String [] weaponListName = new String[wList.size()];
		int wCounter = 0;
		for (Weapon wep : wList) {
			String wepName = wep.toString();
			weaponListName[wCounter] = wepName;
			wCounter++;
		}
		
		worldData.getMap(mapDataKey).getPlayData().setWeapons(weaponListName);
		DataManager dm = new DataManager();
		dm.setWorldData(fileName, worldData);
		dm.saveWorldDataToFile(fileName);
	}
	

}
