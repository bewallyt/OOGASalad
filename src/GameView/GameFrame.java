package GameView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.main.RPGEngine;
import authoring.MapData;
import authoring.PlayerData;
//import authoring.PlayerData;
import authoring.WorldData;
//import Data.DataManager;
import Data.FileStorer;
import util.Constants;
import util.Music;

public class GameFrame extends RPGEngine {
	private WorldData myWorldData;
	private FileStorer myData;
	private Player myPlayer;

	public GameFrame() {
		myData = new FileStorer();
		initializeGame();
	}

	/**
	 * 
	 * @param fileName
	 *            defines which saved game to load or re load
	 */

	public void initialize(String fileName) {
		try {
			myWorldData = myData.getWorldData(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		initMusicTest();
		setInit(true);
//		createWorlds();
		createWorlds2();
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

		for (MapData map : myWorldData.getMaps().values()) {

			MapDataParser parser = new MapDataParser(map, myPlayer);
			List<GridObject> gridObjectList = parser.getGridObjectList();
			gridObjectList.add(myPlayer);
			List<String> TileImageList = parser.getTileImageList();

			// tile size is default. ask engine to take it out of constructor
			WalkAroundWorld currWorld = new WalkAroundWorld(
					map.getMapLength() * 40, map.getMapWidth() * 40, myPlayer,
					Constants.TILE_SIZE, gridObjectList);
			setWorld(currWorld);

			setTileImages(currWorld, TileImageList);
			setGridObjects(currWorld, gridObjectList);
		}
	}
	
	private void createWorlds2() {
		List<GridObject> gridObjectList = new ArrayList<GridObject>();

		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
				"PlayerLeft1.png", "PlayerLeft2.png"};
		myPlayer = new Player(anim, 2);

		MapData map = myWorldData.getMap("defaultworldkey");
		MapDataParser parser = new MapDataParser(map, myPlayer);
		List<GridObject> gridObjectList = parser.getGridObjectList();
		gridObjectList.add(myPlayer);
		
		gridObjectList.add(myPlayer);
	
		WalkAroundWorld outsideWorld = new WalkAroundWorld(1000, 1000, myPlayer, 40, gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world
		
		outsideWorld.setTileObject(gridObjectList.get(0), 1, 6);
	}

	private void createPlayer() {
		// PlayerData myPlayerData = myWorldData.getPlayData();

		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
				"PlayerLeft1.png", "PlayerLeft2.png"};
		myPlayer = new Player(anim, 2);
		// myPlayer = new Player(myPlayerData.getMyAnimImages(), myPlayerData.getSpeed());
	}

	private void setGridObjects(WalkAroundWorld world, List<GridObject> list) {
		for (GridObject g : list) {
			world.setTileObject(g, g.getX(), g.getY());
			System.out.println("x: " + g.getX() + " y: " + g.getY());
		}
	}

	private void setTileImages(WalkAroundWorld world, List<String> list) {
		int n = 0;
		for (int i = 0; i < world.getTileGridHeight(); i++) {
			for (int j = 0; j < world.getTileGridWidth(); j++) {
				world.setTileImage(j, i, list.get(n));
				n++;
			}
		}
	}
}