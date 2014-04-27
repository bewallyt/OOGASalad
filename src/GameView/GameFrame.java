package GameView;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Main;
import engine.collision.EnterCollision;
import engine.gridobject.GridObject;
import engine.gridobject.Door;
import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.world.World;
import engine.main.RPGEngine;
import authoring.MapData;
import authoring.PlayerData;
import authoring.WorldData;
import Data.DataManager;
import util.Constants;
import util.Music;

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
		createWorlds();
		setDoors();
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

		createPlayer();

		for (String mapName : myWorldData.getMaps().keySet()) {
			MapData map = myWorldData.getMap(mapName);
			MapDataParser parser = new MapDataParser(map, myPlayer);
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
			System.out.println("x: " + g.getX() + " y: " + g.getY());
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
		System.out.println("height: " + world.getTileGridHeight());
		System.out.println("width: " + world.getTileGridWidth());
		for (int i = 0; i < world.getTileGridHeight(); i++) {
			for (int j = 0; j < world.getTileGridWidth(); j++) {
				world.setTileImage(j, i, list.get(n));
				n++;
			}
		}
	}

	public WalkAroundWorld getInitialWorld() {
		outsideWorld.setMusic("/music/pokeTest.wav");
		return outsideWorld;

	}
}