package GameView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Main;
import engine.collision.EnterCollision;
import engine.gridobject.GridObject;
import engine.gridobject.Door;
import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.main.RPGEngine;
import authoring.MapData;
import authoring.PlayerData;
import authoring.WorldData;
import Data.DataManager;
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
//		createWorlds2();
//		createWorlds3();
		setDoors();
	}

	private void setDoors() {
		for (WalkAroundWorld map : myMaps.values()) {
			for (int i = 0; i < map.getGridObjectList().size(); i++) {
				GridObject g = map.getGridObjectList().get(i);
				if (g instanceof Door) {
					((Door) g).setWorld(myMaps.get(((Door) g).getToMap()));
					map.setCollisionHandler(new EnterCollision(myPlayer, ((Door) g)), i, map.getGridObjectList().size()-1);
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

			WalkAroundWorld currWorld = new WalkAroundWorld(mapName,
					map.getMapLength() * Constants.TILE_SIZE, map.getMapWidth()
							* Constants.TILE_SIZE, myPlayer,
					Constants.TILE_SIZE, gridObjectList);

			if (myWorldData.getPrimaryMap().equals(mapName))
				setWorld(currWorld); // this is only called for the initial
										// world

			setTileImages(currWorld, TileImageList);
			setGridObjects(currWorld, gridObjectList);
			myMaps.put(mapName, currWorld);
		}

	}
	
	private void createWorlds3() {
		createPlayer();
		
		Door door = new Door("ImageFiles/cabinets.jpg", 1, 1);
		Door door2 = new Door("ImageFiles/cabinets.jpg", 1, 1);
		
		List<GridObject> gridObjectList = new ArrayList<GridObject>();
		List<GridObject> gridObjectList2 = new ArrayList<GridObject>();
		
		gridObjectList.add(myPlayer);
		gridObjectList.add(door);
		gridObjectList2.add(myPlayer);
		gridObjectList2.add(door2);
		
		WalkAroundWorld outsideWorld = new WalkAroundWorld("outsideWorld", 1000, 1000, myPlayer, 40, gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world

		WalkAroundWorld buildingWorld = new WalkAroundWorld("buildingWorld",1000, 1000, myPlayer, 40, gridObjectList2);
		door.setWorld(buildingWorld);
		door2.setWorld(outsideWorld);
		
		outsideWorld.setTileObject(gridObjectList.get(0), 1, 6);
		outsideWorld.setTileObject(gridObjectList.get(1), 4, 5);
		outsideWorld.setCollisionHandler(new EnterCollision(myPlayer, 
				door),0,1);

		buildingWorld.setTileObject(gridObjectList2.get(0), 1, 6);
		buildingWorld.setTileObject(gridObjectList2.get(1), 2, 2);
		buildingWorld.setCollisionHandler(new EnterCollision(myPlayer, door2), 0, 1);

		
	}

	private void createWorlds2() {

		createPlayer();
		
		String mapName = "first";
		MapData map = myWorldData.getMap(mapName);
		MapDataParser parser = new MapDataParser(map, myPlayer);
		List<GridObject> gridObjectList = new ArrayList<GridObject>();		
		Door door = new Door("ImageFiles/cabinets.jpg", 1, 1);
		gridObjectList.add(door);		
		Door door2 = new Door("ImageFiles/cabinets.jpg", 1, 1);
		List<String> TileImageList = parser.getTileImageList();
		gridObjectList.add(myPlayer);

		WalkAroundWorld currWorld = new WalkAroundWorld(mapName,
				map.getMapLength() * Constants.TILE_SIZE, map.getMapWidth()
				* Constants.TILE_SIZE, myPlayer,
				Constants.TILE_SIZE, gridObjectList);

		setWorld(currWorld);
		setTileImages(currWorld, TileImageList);
//		setGridObjects(currWorld, gridObjectList);
		currWorld.setTileObject(gridObjectList.get(0), 3, 3);
		currWorld.setTileObject(gridObjectList.get(1), 1, 1);
		myMaps.put(mapName, currWorld);

		String mapName2 = "doorArea";
		MapData map2 = myWorldData.getMap(mapName2);
		MapDataParser parser2 = new MapDataParser(map2, myPlayer);
		List<GridObject> gridObjectList2 = new ArrayList<GridObject>();
		List<String> TileImageList2 = parser2.getTileImageList();
		gridObjectList2.add(door2);
		gridObjectList2.add(myPlayer);

		WalkAroundWorld currWorld2 = new WalkAroundWorld(mapName2,
				map2.getMapLength() * Constants.TILE_SIZE, map2.getMapWidth()
				* Constants.TILE_SIZE, myPlayer,
				Constants.TILE_SIZE, gridObjectList2);

		door.setWorld(currWorld2);
		door2.setWorld(currWorld);
		
//		setWorld(currWorld2);
		
		setTileImages(currWorld2, TileImageList2);
		currWorld2.setTileObject(gridObjectList2.get(0), 3, 3);
		currWorld2.setTileObject(gridObjectList2.get(1), 1, 1);
		myMaps.put(mapName2, currWorld2);

		
		
		
		
	}

	
	
	private void createPlayer() {
//		PlayerData myPlayerData = myWorldData.getPlayData();
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