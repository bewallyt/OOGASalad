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
	private Creator myCreator;
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
		myCreator = new Creator(myWorldData);
		myItems = myCreator.makeItems();
		myWeapons = myCreator.makeWeapons();
		loadFileName = fileName;
		myPlayer = myCreator.createPlayer();
		setStarts();
		createWorlds();
	}

	@Override
	public void initializeGame() {
		initializeCanvas(Constants.CANVASWIDTH, Constants.CANVASHEIGHT);
	}

	public void makeTitleScreen() {
		GameChooserWorld titleScreen = new GameChooserWorld(Constants.TITLEWIDTH,
				Constants.TITLEHEIGHT, new Player(), Constants.TITLE_BACKGROUND);

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

	private void setStarts(){
		PlayerData pd = myCreator.getPlayerData();
		xStart = pd.getX();
		yStart = pd.getY();
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