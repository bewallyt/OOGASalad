package Data.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Constants;
import authoring.gameObjects.ItemData;
import authoring.gameObjects.PlayerData;
import authoring.gameObjects.WeaponData;
import authoring.gameObjects.WorldData;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.Weapon;

/**
 * @author Sanmay Jain
 */
public class PlayerTransformer implements Transformer {
	Player myPlayer;
	PlayerData myPlayerData;
	WorldUtil myWorldUtil;
	WorldData myWorldData;
	List<ItemData> myItemDataList;
	List<WeaponData> myWeaponDataList;

	public PlayerTransformer(Player p, WorldData w) {
		myPlayer = p;
		myWorldUtil = new WorldUtil();
		myWorldData = w;
		myPlayerData = null;
		myItemDataList = new ArrayList<ItemData>();
		myWeaponDataList = new ArrayList<WeaponData>();
	}

	@Override
	public void transform() {
		String pImageName = myPlayer.getImageFile();
		String spriteName = myWorldUtil.getSpriteName(pImageName);
		String pName =  myPlayer.toString();		
		
		String[] pAnimImages =  myPlayer.getAnimImages();		
		Map<String,Integer> attributeValues = new HashMap<String,Integer>();
		for (String key :  myPlayer.getStatsMap().keySet()) {
			attributeValues.put(key,   myPlayer.getStatsMap().get(key).getValue());
		}
					
		List<Weapon> pWeapons =  myPlayer.getWeaponList();
		String[] pWeps = myWorldUtil.getWeaponNames(pWeapons);
		
		for (Weapon wep : myPlayer.getWeaponList()) {
			WeaponTransformer wt = new WeaponTransformer(wep);
			wt.transform();
			WeaponData weaponData = wt.getTransformedData();
			if (weaponData != null) {
				myWeaponDataList.add(weaponData);
			}
		}		
		
		List<Item> pItems =  myPlayer.getItems();
		String [] pItemNames = myWorldUtil.getItemNames(pItems);
		
		for (Item item : myPlayer.getItems()) {
			if (item == null) {
				continue;
			}
			ItemTransformer iTrans = new ItemTransformer(item);
			iTrans.transform();
			ItemData itemData = iTrans.getTransformedData();
			if (itemData != null) {			
				myItemDataList.add(itemData);
			}
		} 	

		myPlayerData = new PlayerData( myPlayer.getX()/Constants.TILE_SIZE,
				 myPlayer.getY()/Constants.TILE_SIZE, 
				 spriteName, pName, 
				 attributeValues,
				 pWeps, pItemNames);
		myPlayerData.setImages(pAnimImages);
		myPlayerData.setMyMoney(myPlayer.getMoney());
		myPlayerData.setMyExperience(myPlayer.getExperience());
		
	}
	
	public PlayerData getTransformedData() {
		return myPlayerData;
	}
	
	public List<ItemData> getItemDataList() {
		return myItemDataList;
	}
	
	public List<WeaponData> getWeaponDataList() {
		return myWeaponDataList;
	}

}
