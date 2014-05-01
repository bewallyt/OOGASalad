package Data.world;

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

public class PlayerTransformer implements Transformer {
	Player myPlayer;
	PlayerData myPlayerData;
	WorldUtil myWorldUtil;
	WorldData myWorldData;

	public PlayerTransformer(GridObject g, WorldData w) {
		myPlayer = (Player) g;
		myWorldUtil = new WorldUtil();
		myWorldData = w;
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
			myWorldData.saveWeapon(weaponData.getMyName(), weaponData);
		}
		
		List<Item> pItems =  myPlayer.getItems();
		String [] pItemNames = myWorldUtil.getItemNames(pItems);
		
		for (Item item : myPlayer.getItems()) {
			ItemTransformer iTrans = new ItemTransformer(item);
			iTrans.transform();
			ItemData itemData = iTrans.getTransformedData();
			myWorldData.saveItem(itemData.getItemName(), itemData);
		} 

		PlayerData playerData = new PlayerData( myPlayer.getX()/Constants.TILE_SIZE,
				 myPlayer.getY()/Constants.TILE_SIZE, 
				 spriteName, pName, 
				 attributeValues,
				 pWeps, pItemNames);
		playerData.setImages(pAnimImages);
		playerData.setMyMoney(myPlayer.getMoney());
		playerData.setMyExperience(myPlayer.getExperience());
		
	}
	
	public PlayerData getTransformedData() {
		return myPlayerData;
	}

}
