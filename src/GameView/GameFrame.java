package GameView;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Main;
import engine.gridobject.GridObject;
import engine.gridobject.Door;
import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.main.RPGEngine;
import authoring.MapData;
import authoring.PlayerData;
//import authoring.PlayerData;
import authoring.WorldData;
import Data.DataManager;
//import Data.DataManager;
import util.Constants;
import util.Music;

public class GameFrame extends RPGEngine {

	private WorldData myWorldData;
	private DataManager myData;
	private Player myPlayer;

	private Map<String, WalkAroundWorld> myMaps = new HashMap<String, WalkAroundWorld>();

	public GameFrame() {
		myData = new DataManager();
		initializeGame();
	}

	/**
	 * 
	 * @param fileName
	 *            defines which saved game to load or re load
	 */

	public void initialize(String fileName) {

		myWorldData = myData.getWorldData(fileName);
		initMusicTest();
		setInit(true);
		createWorlds();
		setDoors();
	}

	private void setDoors() {
		for (WalkAroundWorld map : myMaps.values()) {
			for (GridObject g : map.getGridObjectList()) {
				if (g instanceof Door) {
					((Door) g).setWorld(myMaps.get(((Door) g).getToMap()));
				}
			}
		}
	}

	@Override
	public void initializeGame() {
		initializeCanvas(Constants.CANVASWIDTH, Constants.CANVASHEIGHT);
	}

	private void initMusicTest() {
		URL mainURL = Main.class.getResource("/music/pokeTest.wav");
		Music music = new Music(mainURL);
		music.start();
	}

	/**
	 * Communication between Data and Engine
	 */

	private void createWorlds() {

		createPlayer();

		for (String mapName : myWorldData.getMaps().keySet()) {
			MapData map = myWorldData.getMap(mapName);
			MapDataParser parser = new MapDataParser(map, myPlayer);
			List<GridObject> gridObjectList = parser.getGridObjectList();
			List<String> TileImageList = parser.getTileImageList();
			gridObjectList.add(myPlayer);

			WalkAroundWorld currWorld = new WalkAroundWorld("mapName",
					map.getMapLength() * Constants.TILE_SIZE, map.getMapWidth()
							* Constants.TILE_SIZE, myPlayer,
					Constants.TILE_SIZE, gridObjectList);
			// currWorld.setID(mapName);

//			if (myWorldData.getPrimaryMap().equals(mapName))
				setWorld(currWorld); // this is only called for the initial
										// world

			setTileImages(currWorld, TileImageList);
			setGridObjects(currWorld, gridObjectList);
			myMaps.put(mapName, currWorld);
		}

	}

	private void createPlayer() {
		PlayerData myPlayerData = myWorldData.getPlayData();
//		System.out.println(myPlayerData.getImages().toString());
		
//		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
//				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
//				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
//				"PlayerLeft1.png", "PlayerLeft2.png"};
		String[] anim = new String[]{
				Constants.PLAYERASHPATH+"PlayerUp0.png",
				Constants.PLAYERASHPATH+"PlayerUp1.png",
				Constants.PLAYERASHPATH+"PlayerUp2.png",
				Constants.PLAYERASHPATH+"PlayerRight0.png",
				Constants.PLAYERASHPATH+"PlayerRight1.png",
				Constants.PLAYERASHPATH+"PlayerRight2.png",
				Constants.PLAYERASHPATH+"PlayerDown0.png",
				Constants.PLAYERASHPATH+"PlayerDown1.png",
				Constants.PLAYERASHPATH+"PlayerDown2.png",
				Constants.PLAYERASHPATH+"PlayerLeft0.png",
				Constants.PLAYERASHPATH+"PlayerLeft1.png",
				Constants.PLAYERASHPATH+"PlayerLeft2.png",
		};

		String[] items = new String[1];//myPlayerData.getMyItems();
		String[] weapons = new String[1];//myPlayerData.getMyWeapons();

		myPlayer = new Player(anim, "Brandon", 2, items, weapons);
		// myPlayer = new Player(myPlayerData.getMyAnimImages(),
		// myPlayerData.getSpeed());
	}

	private void setGridObjects(WalkAroundWorld world, List<GridObject> list) {
		for (GridObject g : list) {
			world.setTileObject(g, g.getX(), g.getY());
			System.out.println("x: " + g.getX() + " y: " + g.getY());
		}
	}

	private void setTileImages(WalkAroundWorld world, List<String> list) {
		int n = 0;
		System.out.println("height: " + world.getTileGridHeight());
		System.out.println("width: " + world.getTileGridWidth());
		for (int i = 0; i < world.getTileGridHeight(); i++) {
			for (int j = 0; j < world.getTileGridWidth(); j++) {
				// System.out.println(list.get(n)+" i: "+i+" j: " + j + " n: "
				// +n);
				world.setTileImage(j, i, list.get(n));
				n++;
			}
		}
	}
}