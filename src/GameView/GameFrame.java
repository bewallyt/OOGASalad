package GameView;

import java.net.URL;
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
import Data.DataManager;
//import Data.DataManager;
import util.Constants;
import util.Music;

public class GameFrame extends RPGEngine {
	private WorldData myWorldData;
	private DataManager myData;
	private Player myPlayer;

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
			
			WalkAroundWorld currWorld = new WalkAroundWorld(
					map.getMapLength()*Constants.TILE_SIZE, 
					map.getMapWidth()*Constants.TILE_SIZE, myPlayer, 
					Constants.TILE_SIZE, gridObjectList);
			
			if(myWorldData.getPrimaryMap().equals(mapName))
				setWorld(currWorld); // this is only called for the initial world

			setTileImages(currWorld, TileImageList);
			setGridObjects(currWorld, gridObjectList);
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
				"PlayerImages/Ash/PlayerUp0.png",
				"PlayerImages/Ash/PlayerUp1.png",
				"PlayerImages/Ash/PlayerUp2.png",
				"PlayerImages/Ash/PlayerRight0.png",
				"PlayerImages/Ash/PlayerRight1.png",
				"PlayerImages/Ash/PlayerRight2.png",
				"PlayerImages/Ash/PlayerDown0.png",
				"PlayerImages/Ash/PlayerDown1.png",
				"PlayerImages/Ash/PlayerDown2.png",
				"PlayerImages/Ash/PlayerLeft0.png",
				"PlayerImages/Ash/PlayerLeft1.png",
				"PlayerImages/Ash/PlayerLeft2.png",
		};

		String[] items = new String[1];//myPlayerData.getMyItems();
		String[] weapons = new String[1];//myPlayerData.getMyWeapons();
		myPlayer = new Player(anim, "Brandon", 2, items, weapons);
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
		System.out.println("height: "+world.getTileGridHeight());
		System.out.println("width: "+world.getTileGridWidth());
		for (int i = 0; i < world.getTileGridHeight(); i++) {
			for (int j = 0; j < world.getTileGridWidth(); j++) {
//				System.out.println(list.get(n)+" i: "+i+" j: " + j + " n: " +n);
				world.setTileImage(j, i, list.get(n));
				n++;
			}
		}
	}
}