package GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.Statistic;
import engine.collision.EnterCollision;
import engine.gridobject.GridObject;
import engine.gridobject.Door;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.KeyItem;
import engine.item.StatBuffer;
import engine.item.Weapon;
import engine.world.ArenaWorld;
import engine.world.TitleWorld;
import engine.world.WalkAroundWorld;
import engine.main.RPGEngine;
import authoring.gameObjects.ItemData;
import authoring.gameObjects.MapData;
import authoring.gameObjects.PlayerData;
import authoring.gameObjects.WeaponData;
import authoring.gameObjects.WorldData;
import Data.DataManager;
import Data.WorldDataManager;
import util.Constants;

/**
 * The GameFrame class parses data from WorldData to initialize a new game.
 * 
 * @author Brandon Chao, Peter Yom
 * 
 */
public class GameFrame extends RPGEngine {

	private WorldData myWorldData;
	private DataManager myData;
	private Player myPlayer;
	private WalkAroundWorld outsideWorld;
	private Map<String, Weapon> myWeapons = new HashMap<String, Weapon>();
	private Map<String, ItemData> myItems = new HashMap<String, ItemData>();
	private WorldDataManager myWorldDataManager;
	private String loadFileName;
	private int xStart;
	private int yStart;;

	private Map<String, WalkAroundWorld> myMaps = new HashMap<String, WalkAroundWorld>();

	public GameFrame() {
		myData = new DataManager();
	}

	/**
	 * Initializes the game world from the data contained in WorldData
	 * 
	 * @param fileName
	 *            String that represents which save file to load
	 * 
	 */

	public void initialize(String fileName) {

		myWorldData = myData.getWorldData(fileName);
		myItems = makeItems();
		myWeapons = makeWeapons();
		loadFileName = fileName;
		createPlayer();
		createWorlds();
	}

	@Override
	public void initializeGame() {
		initializeCanvas(Constants.CANVASWIDTH, Constants.CANVASHEIGHT);
	}

	public void makeTitleScreen() {
		TitleWorld titleScreen = new TitleWorld(Constants.TITLEWIDTH,
				Constants.TITLEHEIGHT, new Player());

		titleScreen.setBackground(Constants.TITLE_BACKGROUND);
		setWorld(titleScreen);

		titleScreen.setMusic(Constants.TITLE_MUSIC);
	}

	/**
	 * Creates the player, all of the WalkAroundWorlds, and the GridObjects in
	 * each world
	 */

	private void createWorlds() {

		for (String mapName : myWorldData.getMaps().keySet()) {
			MapData map = myWorldData.getMap(mapName);
			MapDataParser parser = new MapDataParser(map, myPlayer, myWeapons,
					myItems);
			List<GridObject> gridObjectList = parser.getGridObjectList();
			List<String> TileImageList = parser.getTileImageList();

			gridObjectList.add(myPlayer);
			
			WalkAroundWorld currWorld = new WalkAroundWorld(mapName,
					map.getMapLength() * Constants.TILE_SIZE, map.getMapWidth()
							* Constants.TILE_SIZE, myPlayer,
					Constants.TILE_SIZE, gridObjectList);

			if (!map.getSong().equals(""))
				currWorld.setMusic(myWorldData.getSongString(map.getSong()));

			if (myWorldData.getPrimaryMap().equals(mapName))
				outsideWorld = currWorld;

			setTileImages(currWorld, TileImageList);
			setGridObjects(currWorld, gridObjectList);
			myMaps.put(mapName, currWorld);
		}

		setSpecialObjects();
	}

	/**
	 * Creates the player based on PlayerData
	 */
	private void createPlayer() {
		PlayerData pd = myWorldData.getPlayData();
		myPlayer = new Player(pd.getImages(), pd.getMyName(), 2,
				pd.getMyWeapons(), pd.getMyWeapons(), makeWeapons());
		setPlayerItems(pd);
//		myPlayer.setPosition(pd.getX(), pd.getY());
		
		xStart = pd.getX();
		yStart = pd.getY();
		
		myPlayer.addAllStatistics((Map<String, Double>) pd.getArguments().get(
				Constants.VALUES_CONST));
		myPlayer.setBattleImage(pd.getImages()[6]);
	}

	/**
	 * Sets the GridObjects to their location in their respective worlds
	 * 
	 * @param world
	 *            WalkAroundWorld to set GridObjects in
	 * @param list
	 *            List of all GridObjects in a given world
	 */
	private void setGridObjects(WalkAroundWorld world, List<GridObject> list) {
		for (GridObject g : list) {
			world.setTileObject(g, g.getX(), g.getY());
		}
	}

	private void setPlayerItems(PlayerData pd) {
		String[] items = pd.getMyItems();
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
		myPlayer.setMyItems(itemList);
	}

	/**
	 * Set the images for the tiles in a world
	 * 
	 * @param world
	 *            WalkAroundWorld to set tile images in
	 * @param list
	 *            List of tile images to set
	 */
	private void setTileImages(WalkAroundWorld world, List<String> list) {
		int n = 0;
		for (int i = 0; i < world.getTileGridHeight(); i++) {
			for (int j = 0; j < world.getTileGridWidth(); j++) {
				world.setTileImage(j, i, list.get(n));
				n++;
			}
		}
	}

	/**
	 * Uses WeaponData to create a HashMap mapping weapon names to the actual
	 * weapon
	 * 
	 * @return HashMap of weapon name to weapon
	 */
	private HashMap<String, Weapon> makeWeapons() {
		HashMap<String, Weapon> wepRet = new HashMap<String, Weapon>();
		Map<String, WeaponData> myWeaponData = myWorldData.getMyWeapons();
		for (String wep : myWeaponData.keySet()) {
			WeaponData currWeaponData = myWeaponData.get(wep);
			Weapon currWeapon = currWeaponData.makeWeapon();
			wepRet.put(wep, currWeapon);
		}
		return wepRet;
	}

	/**
	 * Creates a copy of HashMap<String, ItemData>, used to avoid Gson
	 * LinkedTreeMap errors
	 * 
	 * @return Copy of myItems from WorldData
	 */
	private HashMap<String, ItemData> makeItems() {
		HashMap<String, ItemData> itemRet = new HashMap<String, ItemData>();
		Map<String, ItemData> myItemData = myWorldData.getMyItems();

		for (String itemdata : myItemData.keySet()) {
			ItemData currItemData = myItemData.get(itemdata);
			itemRet.put(itemdata, currItemData);
		}
		return itemRet;
	}

	/**
	 * Loops through all maps and grid objects to set doors to their
	 * corresponding map, also sets enemies to arena worlds
	 */
	private void setSpecialObjects() {
		for (WalkAroundWorld map : myMaps.values()) {
			for (int i = 0; i < map.getGridObjectList().size(); i++) {
				GridObject g = map.getGridObjectList().get(i);
				if (g instanceof Door) {
					((Door) g).setWorld(myMaps.get(((Door) g).getToMap()));
					map.setCollisionHandler(new EnterCollision(myPlayer,
							((Door) g)), i, map.getGridObjectList().size() - 1);
				}
				if (g instanceof Enemy) {
					ArenaWorld arenaWorld = new ArenaWorld(
							Constants.BATTLE_BACKGROUND, 800, 800, myPlayer,
							(Enemy) g, map, Constants.BATTLE_LABELS);
					arenaWorld.setMusic(Constants.BATTLE_MUSIC);
					((Enemy) g).setWorld(arenaWorld);
				}
			}
		}
	}
	
	public int getXStart() {
		return xStart;
	}

	public int getYStart() {
		return yStart;
	}
	
	public WalkAroundWorld getInitialWorld() {
		myWorldDataManager = new WorldDataManager(loadFileName);
		outsideWorld.setWorldDataManager(myWorldDataManager);
		return outsideWorld;
	}
}