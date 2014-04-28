package GameView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.collision.EnterCollision;
import engine.gridobject.GridObject;
import engine.gridobject.Door;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.item.Weapon;
import engine.world.ArenaWorld;
import engine.world.WalkAroundWorld;
import engine.main.RPGEngine;
import authoring.gameObjects.ItemData;
import authoring.gameObjects.MapData;
import authoring.gameObjects.PlayerData;
import authoring.gameObjects.WeaponData;
import authoring.gameObjects.WorldData;
import Data.DataManager;
import util.Constants;

/**
 * The GameFrame class parses data from WorldData to initialize a new game.
 * 
 * @author Brandon, Peter
 * 
 */
public class GameFrame extends RPGEngine {

	private WorldData myWorldData;
	private DataManager myData;
	private Player myPlayer;
	private WalkAroundWorld outsideWorld;
	private Map<String,WeaponData> myWeaponData = new HashMap<String, WeaponData>();
	private Map<String,ItemData> myItemData = new HashMap<String, ItemData>();
	private Map<String,Weapon> myWeapons = new HashMap<String, Weapon>();
	private Map<String, ItemData> myItems = new HashMap<String, ItemData>();

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
		createPlayer();
		createWorlds();
	}

	/**
	 * Loops through all maps and grid objects to set doors to their
	 * corresponding map
	 */
	private void setDoors() {
		for (WalkAroundWorld map : myMaps.values()) {
			for (int i = 0; i < map.getGridObjectList().size(); i++) {
				GridObject g = map.getGridObjectList().get(i);
				if (g instanceof Door) {
					((Door) g).setWorld(myMaps.get(((Door) g).getToMap()));
					map.setCollisionHandler(new EnterCollision(myPlayer,
							((Door) g)), i, map.getGridObjectList().size() - 1);
				}
				if (g instanceof Enemy) {
					((Enemy) g).setWorld(new ArenaWorld("ImageFiles/battlebackground.png", 800, 800, myPlayer, (Enemy) g, map, Constants.BATTLE_LABELS));
				}
			}
		}
	}

	@Override
	public void initializeGame() {
		initializeCanvas(Constants.CANVASWIDTH, Constants.CANVASHEIGHT);
	}

	/**
	 * Creates the player, all of the WalkAroundWorlds, and the GridObjects in
	 * each world
	 */

	private void createWorlds() {
		
		myItems = makeItems();
		myWeapons = makeWeapons();

		for (String mapName : myWorldData.getMaps().keySet()) {
			MapData map = myWorldData.getMap(mapName);
			MapDataParser parser = new MapDataParser(map, myPlayer, myWeapons, myItems);
			List<GridObject> gridObjectList = parser.getGridObjectList();
			List<String> TileImageList = parser.getTileImageList();
			gridObjectList.add(myPlayer);

			WalkAroundWorld currWorld = new WalkAroundWorld(mapName,
					map.getMapLength() * Constants.TILE_SIZE, map.getMapWidth()
					* Constants.TILE_SIZE, myPlayer,
					Constants.TILE_SIZE, gridObjectList);

			if (myWorldData.getPrimaryMap().equals(mapName)) {
				outsideWorld = currWorld;
			}

			setTileImages(currWorld, TileImageList);
			setGridObjects(currWorld, gridObjectList);
			myMaps.put(mapName, currWorld);
		}
		setDoors();
	}

	/**
	 * Creates the player based on PlayerData
	 */
	private void createPlayer() {

		PlayerData myPlayerData = myWorldData.getPlayData();
		String[] anim = myPlayerData.getImages();

		String[] items = myPlayerData.getMyItems();
		String[] weapons = myPlayerData.getMyWeapons();

		myPlayer = new Player(anim, myPlayerData.getMyName(), 2, items, weapons);
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
	
	private HashMap<String, Weapon> makeWeapons() {
		HashMap<String, Weapon> wepRet = new HashMap<String, Weapon>();
		myWeaponData = myWorldData.getMyWeapons();
		for(String wep : myWeaponData.keySet()){
			WeaponData currWeaponData = myWeaponData.get(wep);
			Weapon currWeapon = currWeaponData.makeWeapon();
			wepRet.put(wep, currWeapon);
		}
		return wepRet;
	}

	private HashMap<String, ItemData> makeItems() {
		HashMap<String, ItemData> itemRet = new HashMap<String, ItemData>();
		myItemData = myWorldData.getMyItems();
		for(String item : myItemData.keySet()){
			ItemData currItemData = myItemData.get(item);
			itemRet.put(item, currItemData);
		}
		return itemRet;
	}

	
	public WalkAroundWorld getInitialWorld() {
		outsideWorld.setMusic("/music/pokeTest.wav");
		return outsideWorld;

	}
}