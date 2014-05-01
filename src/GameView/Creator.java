package GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Constants;
import engine.Statistic;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.KeyItem;
import engine.item.StatBuffer;
import engine.item.Weapon;
import authoring.gameObjects.ItemData;
import authoring.gameObjects.PlayerData;
import authoring.gameObjects.WeaponData;
import authoring.gameObjects.WorldData;

public class Creator {
	private WorldData myWorldData;
	private PlayerData pd;
	private Map<String, ItemData> myItems;
	
	public Creator(WorldData worldData) {
		myWorldData = worldData;
		pd = myWorldData.getPlayData();
	}
	
	private List<Item> setPlayerItems() {
		String[] items = pd.getMyItems();
		myItems = makeItems();
		List<Item> itemList = new ArrayList<Item>();
		for (String i : items) {
			if (i != null) {

				ItemData id = myItems.get(i);
				if (id.getMyIdentity().equals("KeyItem")) {
					itemList.add(new KeyItem(id.getItemImage(), id
							.getItemName()));
				} else if (id.getMyIdentity().equals("StatBuffer")) {
					Map<String, Integer> valuesMap = id.getMyItemValues();
					String key = "health";
					Integer value = 10;
					Statistic stats = null;
					if ((valuesMap != null) && (valuesMap.size() > 0)) {
						for (String k : valuesMap.keySet()) {
							stats = new Statistic(k, valuesMap.get(k), 100);
							break;
						}
					} else {
						stats = new Statistic(key, value, 100);
					}
					itemList.add(new StatBuffer(id.getItemImage(), id
							.getItemName(), stats, 10));
				}
			}
		}
		return itemList;
	}
	
	protected PlayerData getPlayerData(){
		return pd;
	}
	
	/**
	 * Creates the player based on PlayerData
	 */
	protected Player createPlayer() {
		Player myPlayer = new Player(pd.getImages(), pd.getMyName(), 2,
				pd.getMyWeapons(), pd.getMyWeapons(), makeWeapons());
		myPlayer.setMyItems(setPlayerItems());

		myPlayer.addAllStatistics((Map<String, Double>) pd.getArguments().get(
				Constants.VALUES_CONST));
		myPlayer.setBattleImage(pd.getImages()[6]);
		return myPlayer;
	}
	
	/**
	 * Creates a copy of HashMap<String, ItemData>, used to avoid Gson
	 * LinkedTreeMap errors
	 * 
	 * @return Copy of myItems from WorldData
	 */
	protected HashMap<String, ItemData> makeItems() {
		HashMap<String, ItemData> itemRet = new HashMap<String, ItemData>();
		Map<String, ItemData> myItemData = myWorldData.getMyItems();

		for (String itemdata : myItemData.keySet()) {
			ItemData currItemData = myItemData.get(itemdata);
			itemRet.put(itemdata, currItemData);
		}
		return itemRet;
	}
	
	/**
	 * Uses WeaponData to create a HashMap mapping weapon names to the actual
	 * weapon
	 * 
	 * @return HashMap of weapon name to weapon
	 */
	protected HashMap<String, Weapon> makeWeapons() {
		HashMap<String, Weapon> wepRet = new HashMap<String, Weapon>();
		Map<String, WeaponData> myWeaponData = myWorldData.getMyWeapons();
		for (String wep : myWeaponData.keySet()) {
			WeaponData currWeaponData = myWeaponData.get(wep);
			Weapon currWeapon = currWeaponData.makeWeapon();
			wepRet.put(wep, currWeapon);
		}
		return wepRet;
	}
	
}
