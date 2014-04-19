
package GameView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engine.Dialogue;
import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.SurroundingChecker;
import engine.world.WalkAroundWorld;
import engine.world.World;
import engine.main.Main;
import engine.main.RPGEngine;
import authoring.GridObjectData;
import authoring.MapData;
import authoring.PlayerData;
//import authoring.PlayerData;
import authoring.TileData;
import authoring.WorldData;
import Data.DataDummy;
import Data.DataManager;
import Data.FileStorer;

import javax.swing.JFrame;

import util.Constants;
import engine.gridobject.person.Enemy;

public class GameFrame extends RPGEngine {

	// temporary, will be removed when data adds this info into WorldData
	private final int DEFAULT_MOVEMENT_TYPE = 1;
	private final int DEFAULT_MOVEMENT_SPEED = 1;
	private WorldData myWorldData;
	// private DataManager myData;
	private FileStorer myData;

	private Player myPlayer;

	public GameFrame() {
		// myData = new FileStorer();
		myData = new FileStorer();

	}
	
	public void initialize(String fileName) {
		try {
			myWorldData = myData.getWorldData(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initializeGame();
	}

	@Override
	public void initializeGame() {
		setInit(true);
		initializeCanvas(Constants.CANVASWIDTH, Constants.CANVASHEIGHT);
		createWorlds();
	}

	/*
	 * Communication between Data and Engine test below: makeOutsideWorld()
	 * addPlayer() addEnemy()
	 */
	
	public void createWorlds() {
		createPlayer();

		for(MapData map : myWorldData.getMaps().values()) {
			
			MapDataParser parser = new MapDataParser(map, myPlayer);
			List<GridObject> gridObjectList = parser.getGridObjectList();
			gridObjectList.add(myPlayer);						
			List<String> TileImageList = parser.getTileImageList();

			// tile size is default. ask engine to take it out of constructor
			WalkAroundWorld currWorld = new WalkAroundWorld(map.getMapLength()*40, map.getMapWidth()*40, myPlayer, Constants.TILE_SIZE, gridObjectList);
			setWorld(currWorld);

			setTileImages(currWorld, TileImageList);
			setGridObjects(currWorld, gridObjectList);
		}
	}

	public void createPlayer() {
		
		PlayerData myPlayerData = myWorldData.getPlayData();
		
		String[] anim = new String[] { "PlayerUp0.png", "PlayerUp1.png",
				"PlayerUp2.png", "PlayerRight0.png", "PlayerRight1.png",
				"PlayerRight2.png", "PlayerDown0.png", "PlayerDown1.png",
				"PlayerDown2.png", "PlayerLeft0.png", "PlayerLeft1.png",
				"PlayerLeft2.png" };
		
		myPlayer = new Player(anim, 2);
	}

	public void setGridObjects(WalkAroundWorld world, List<GridObject> list) {
		for (GridObject g : list) {
			world.setTileObject(g, g.getX(), g.getY());
			System.out.println("x: "+g.getX()+" y: "+g.getY());
		}
	}
	
	public void setTileImages(WalkAroundWorld world, List<String> list) {
		int n = 0;
		for(int i = 0; i < world.getTileGridHeight(); i++) {
			for(int j = 0; j < world.getTileGridWidth(); j++) {
				world.setTileImage(j, i, list.get(n));
				n++;
			}
		}
	}
	
	public void run() {

	}
}