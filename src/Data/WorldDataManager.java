package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Constants;
import engine.Statistic;
import engine.battle.Attack;
import engine.battle.Effect;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Healer;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.gridobject.person.ShopKeeper;
import engine.item.Item;
import engine.item.KeyItem;
import engine.item.StatBuffer;
import engine.item.Weapon;
import engine.world.Tile;
import engine.world.WalkAroundWorld;
import engine.world.World;
import authoring.UserQueryNodeData;
import authoring.gameObjects.AttacksData;
import authoring.gameObjects.BarrierData;
import authoring.gameObjects.DoorData;
import authoring.gameObjects.EnemyData;
import authoring.gameObjects.HealerData;
import authoring.gameObjects.ItemData;
import authoring.gameObjects.MapData;
import authoring.gameObjects.NPCData;
import authoring.gameObjects.NPCResponseNodeData;
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
				} else if (g instanceof ShopKeeper) {
					ShopkeeperData shopkeeperData = transformShopkeeper(g);
					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(shopkeeperData);			
				} else if (g instanceof Healer) {
					HealerData healerData = transformHealer(g);
					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(healerData);			
				} else if (g instanceof NPC) {
					NPCData npcData = transformNPC(g);
					md.getTileData(g.getX()/Constants.TILE_SIZE,g.getY()/Constants.TILE_SIZE).getGridObjectDatas().add(npcData);			
				}
				
			}

			// store player data
			PlayerData playerData = transformPlayer(w);
			md.savePlayer(playerData);
			
			// load other maps
			loadOtherMaps();
			
			// store map in world data
			myCurrentMapName = mapName;
			myWorldData.addLevel(mapName, md);
			myWorldData.setMap(mapName);
			myWorldData.setPrimaryMap(mapName);
			
			
			
			// finally save the file			
			myDM.setWorldData(fileName, myWorldData);
			myDM.saveWorldDataToFile(fileName);
			
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
		
		for (Weapon wep : p.getWeaponList()) {
			WeaponData weaponData = transformWeapon(wep);
			myWorldData.saveWeapon(weaponData.getMyName(), weaponData);
		}
		
		List<Item> pItems = p.getItems();
		String [] pItemNames = getItemNames(pItems);	

		PlayerData playerData = new PlayerData(p.getX()/Constants.TILE_SIZE, p.getY()/Constants.TILE_SIZE, pImageName, pName, attributeValues, pWeps, pItemNames);
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
	 * @param items Item object list
	 * @return Returns list of item names
	 */
	private List<String> getItemNamesList(Set<Item> items) {		
		List<String> itemNames = new ArrayList<String>();
		if (items == null) {
			return itemNames;
		}
		for (Item item : items) {
			if (item == null) {
				continue;
			}
			String itemName = item.toString();
			itemNames.add(itemName);
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
		
		for (Weapon w : e.getWeaponList()) {
			WeaponData weaponData = transformWeapon(w);
			myWorldData.saveWeapon(weaponData.getMyName(), weaponData);
		}
		
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
	
	/**
	 * 
	 * @param w Engine world object
	 * @return Transforms engine ShopKeeper to authoring shopkeeper
	 */
	private ShopkeeperData transformShopkeeper(GridObject g) {
		ShopKeeper s = (ShopKeeper) g;
		
		String[] sAnimImages = s.getAnimImages();	
		Map<String,Integer> attributeValues1 = new HashMap<String,Integer>();
		for (String key : s.getStatsMap().keySet()) {
			attributeValues1.put(key,  s.getStatsMap().get(key).getValue());
		}
		
		Set<Item> items = s.getItemSet();
		List<String> itemNames = getItemNamesList(items);
		
		// Authoring limitation - player and enemy sprites have to be 
		// either Zelda or Ash
	//	String img = g.getImageFile();
		String img = "Zelda";
		
		ShopkeeperData shopkeeperData = new ShopkeeperData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE,
				g.getWidth()/Constants.TILE_SIZE,
				g.getHeight()/Constants.TILE_SIZE,
				img,
				itemNames);
		
		for (Item i : items) {
			ItemData itemData = transformItem(i);
			myWorldData.saveItem(itemData.getItemName(), itemData);
			
		}		
		
		return shopkeeperData;
		
	}
	
	/**
	 * 
	 * @param item Transform Engine Item object
	 * @return Transforms engine Item to authoring ItemData
	 */
	private ItemData transformItem(Item item) {
		ItemData itemData = null;
		if (item instanceof KeyItem ) {
			itemData = new ItemData(item.toString(), item.getPrice(), "KeyItem");
			itemData.setItemImage(item.getImageName());
		} else if (item instanceof StatBuffer) {
			StatBuffer stbuffer = (StatBuffer) item;
			Statistic stats = stbuffer.getStatistic();
			Map<String, Integer> values = new HashMap<String, Integer>();
			if (stats != null) {			
				values.put(stats.getName(), stats.getValue());
			} else {
			}
			itemData = new ItemData(item.toString(),item.getImageName(), values, item.getPrice(), "StatBuffer");
		} else {

		}
		return itemData;
	}
	
	/**
	 * 
	 * @param weapon Transform Engine Weapon object
	 * @return Transforms engine Weapon to authoring WeaponData
	 */
	private WeaponData transformWeapon(Weapon weapon) {
		WeaponData weaponData = null;
		
		List<Attack> attackList = weapon.getAttackList();
		List<AttacksData> attacksDataList = new ArrayList<AttacksData>();
		for (Attack a : attackList) {
			if (a == null) {
				continue;
			}
			AttacksData attacksData = transformAttack(a);
			if (attacksData != null) {
				attacksDataList.add(attacksData);
			}
		}
		
		weaponData = new WeaponData(weapon.toString(), weapon.getImageName(), 
				weapon.getSpeed().getValue(), weapon.getDamage().getValue(), attacksDataList);
		return weaponData;
	}
	
	/**
	 * 
	 * @param attack Transform Engine Attack object
	 * @return Transforms engine Attack to authoring AttackData
	 */
	private AttacksData transformAttack(Attack attack) {
		AttacksData attacksData = null;
		if (attack == null) {
			return attacksData;
		}
		String stats = "";
		int changeAmount = 0;
		boolean affectsSelf = false;
		Effect effect = attack.getEffect();
		if (effect != null) {
			stats = effect.getEffectStatistic();
			changeAmount = effect.getAmountToChange();
			affectsSelf = effect.getAffectsSelf();
		}
		attacksData = new AttacksData(attack.toString(), attack.getSpeed().getValue(), attack.getDamage().getValue(),
				stats, changeAmount, affectsSelf);
		
		return attacksData;
	}
	
	/**
	 * 
	 * @param g Engine GridObject object
	 * @return Transforms engine Healer to authoring HealerData
	 */
	private HealerData transformHealer(GridObject g) {
		String img = "Zelda";
		HealerData healerData = new HealerData(g.getX()/Constants.TILE_SIZE,
				g.getY()/Constants.TILE_SIZE,
				g.getWidth()/Constants.TILE_SIZE,
				g.getHeight()/Constants.TILE_SIZE,
				img);

		return healerData;
	}
	
	/**
	 * 
	 * @param g Engine GridObject object
	 * @return Transforms engine NPC to authoring NPCData
	 */
	private NPCData transformNPC(GridObject g) {
		NPC npc = (NPC) g;
		String img = "Zelda";
		NPCResponseNodeData npcResponseNodeData = transformNPCResponse(npc.getResponseNode());

	    NPCData npcData = new NPCData(g.getX()/Constants.TILE_SIZE,
	    		g.getY()/Constants.TILE_SIZE,
				g.getWidth()/Constants.TILE_SIZE,
				g.getHeight()/Constants.TILE_SIZE,
				img,
				npcResponseNodeData);
		 
		return npcData;
	}
	
	/**
	 * 
	 * @param respNode Engine NPCResponseNode object
	 * @return Transforms engine NPCResponseNodeData to authoring NPCResponseNodeData
	 */
	private NPCResponseNodeData transformNPCResponse(NPCResponseNode respNode) {
		NPCResponseNodeData responseNodeData = null;
		responseNodeData = new NPCResponseNodeData(respNode.getDialogue());
		String item = null;
		if (respNode.getItem() != null) {
			item = respNode.getItem().toString();
		}
		responseNodeData.setItem(item);
		List<UserQueryNode> usqNodesList = respNode.getUserQueryNodes();
		for (UserQueryNode usqNode : usqNodesList) {
			UserQueryNodeData usqNodeData = new UserQueryNodeData();
			String itemName = null;
			if (usqNode.hasItem()) {
				itemName = usqNode.getItemName();
			} 
			usqNodeData.setItem(itemName);
			usqNodeData.setString(usqNode.toString());
			NPCResponseNode rNode = usqNode.getMyNPCResponseNode();
			NPCResponseNodeData rNodeData = new NPCResponseNodeData(rNode.getDialogue());
	        String itName = null;
	        if (rNode.getItem() != null) {
	        	itName = rNode.getItem().toString();
	        }
			rNodeData.setItem(itName);
			List<UserQueryNode> usqNodesList1 = rNode.getUserQueryNodes();
			for (UserQueryNode uNode : usqNodesList1) {
				UserQueryNodeData uNodeData = new UserQueryNodeData();
				String iName = null;
				if (uNode.hasItem()) {
					iName = uNode.getItemName();
				} 
				uNodeData.setItem(iName);	
				uNodeData.setString(uNode.toString());
				rNodeData.addChild(uNodeData);
			}
			usqNodeData.setChild(rNodeData);
			responseNodeData.addChild(usqNodeData);
		}				
		return responseNodeData;
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
	
}
