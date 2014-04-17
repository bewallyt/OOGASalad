
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
import engine.gridobject.person.RuleFollower;
import engine.world.Canvas;
import engine.world.SurroundingChecker;
import engine.world.WalkAroundWorld;
import engine.world.World;
import engine.main.Main;
import engine.main.RPGEngine;
import authoring.GridObjectData;
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

public class GameDummy extends RPGEngine {

	// temporary, will be removed when data adds this info into WorldData
	private final int DEFAULT_MOVEMENT_TYPE = 1;
	private final int DEFAULT_MOVEMENT_SPEED = 1;

	private Boolean musicOn;
	private WorldData myWorldData;
	// private DataManager myData;
	private FileStorer myData;

	private Player myPlayer;

	public GameDummy(String fileName) {
		// myData = new FileStorer();
		myData = new FileStorer();
		
		try {
			myWorldData = myData.getWorldData(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		initializeGame();
	}

	@Override
	public void initializeGame() {
		initializeCanvas(Constants.CANVASWIDTH, Constants.CANVASHEIGHT);
		makeOutsideWorld();
	}
	
	
	private void makeOutsideWorld(){
		List<GridObject> gridObjectList = new ArrayList<GridObject>();
		List<Integer> xList = new ArrayList<Integer>();
		List<Integer> yList = new ArrayList<Integer>();
		
		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
				"PlayerLeft1.png", "PlayerLeft2.png"};
		Player player = new Player(anim, 2, 1, 1);
		
		gridObjectList.add(player);
		xList.add(6);
		yList.add(6);

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				gridObjectList.add(new Barrier(myWorldData.getMap("defaultworldkey").getTileData(i, j).getImageName() + ".png", 1, 1));
				xList.add(i);
				yList.add(j);
			}
		}

		gridObjectList.add(new Barrier(myWorldData.getMap("defaultworldkey").getTileData(1, 1).getImageName()+".png", 1, 1));
//		gridObjectList.add(new Barrier("pokecenter.png",4, 4));		
		
		WalkAroundWorld outsideWorld = new WalkAroundWorld(40, 1000, 1000, player, gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world
		
		for(int i = 0; i < 25; i++) {
			System.out.println(gridObjectList.get(i).getImageFile() + " " + i);
			outsideWorld.setTileObject(gridObjectList.get(i), xList.get(i), yList.get(i));
		}
//		outsideWorld.setTileObject(gridObjectList.get(0), 4, 4);
//		outsideWorld.setTileObject(gridObjectList.get(1), 1, 1);
		

		
		
		
//		outsideWorld.paintFullBackround("grassSmall.png");

	}
	
	

	public void createPlayer() {
		
		PlayerData myPlayerData = myWorldData.getPlayData();
		
		/*String[] anim = new String[] { "PlayerUp0.png", "PlayerUp1.png",
				"PlayerUp2.png", "PlayerRight0.png", "PlayerRight1.png",
				"PlayerRight2.png", "PlayerDown0.png", "PlayerDown1.png",
				"PlayerDown2.png", "PlayerLeft0.png", "PlayerLeft1.png",
				"PlayerLeft2.png" };*/
		
		String[] anim = myPlayerData.getMyAnimImages();
		// int speed = myPlayerData.getSpeed();
		// int width = myPlayerData.getWidth();
		// int height = myPlayerData.getHeight();
		myPlayer = new Player(anim, 2, 1, 1);
	}

	public void setGridObjects(World world, List<GridObject> list) {
		for (GridObject g : list) {
			world.setTileObject(g, g.getX(), g.getY());
		}
	}

	public List<GridObject> createGridObjectList() {

		TileData currTile;
		List<GridObjectData> currGridObjectDatas = new ArrayList<GridObjectData>();
		List<GridObject> myGridObjectList = new ArrayList<GridObject>();

		for (int i = 0; i < myWorldData.getMap("defaultworldkey")
				.getMapLength(); i++) {
			for (int j = 0; j < myWorldData.getMap("defaultworldkey")
					.getMapWidth(); j++) {
				currTile = myWorldData.getMap("defaultworldkey").getTileData(i,
						j);

				currGridObjectDatas = currTile.getGridObjectDatas();

				for (GridObjectData gridObjectData : currGridObjectDatas) {
					GridObject gridobject = null;
					if (gridObjectData.getID().equals("Barrier")) {
						gridobject = new Barrier(gridObjectData.getImageName(),
								gridObjectData.getWidth(),
								gridObjectData.getHeight());
					} else if (gridObjectData.getID().equals("Door")) {
						gridobject = new Door(gridObjectData.getImageName(),
								gridObjectData.getWidth(),
								gridObjectData.getHeight());
					} else if (gridObjectData.getID().equals("NPC")) {
						gridobject = new NPC(
								new String[] { gridObjectData.getImageName() },
								DEFAULT_MOVEMENT_SPEED,
								gridObjectData.getWidth(),
								gridObjectData.getHeight(),
								DEFAULT_MOVEMENT_TYPE, myPlayer);
					}
					myGridObjectList.add(gridobject);
				}
			}
		}
		return myGridObjectList;
	}

	/*
	 * public void addGridObjects() {
	 * 
	 * TileData currTile; List<GridObjectData> currGridObjectDatas = new
	 * ArrayList<GridObjectData>();
	 * 
	 * // addPlayer(); // addEnemy();
	 * 
	 * for (int i = 0; i < myWorldData.getMap("defaultworldkey")
	 * .getMapLength(); i++) { for (int j = 0; j <
	 * myWorldData.getMap("defaultworldkey") .getMapWidth(); j++) { currTile =
	 * myWorldData.getMap("defaultworldkey").getTileData(i, j);
	 * 
	 * currGridObjectDatas = currTile.getGridObjectDatas();
	 * 
	 * for (GridObjectData gridObjectData : currGridObjectDatas) { // Defaulted
	 * at Barrier for now. // if (gridObjectData instanceOf Barrier) or
	 * something like // that?
	 * 
	 * GridObject gridobject = (Barrier) Reflection .createInstance("Barrier",
	 * gridObjectData.getImageName(), gridObjectData.getWidth(),
	 * gridObjectData.getHeight());
	 * 
	 * // elseif (gridObjectData instanceOf RuleFollower) or // something like
	 * that?
	 * 
	 * // Using 1 as default speed since no getSpeed() method yet GridObject
	 * gridobject2 = (RuleFollower) Reflection .createInstance("RuleFollower",
	 * gridObjectData.getImageName(), 1, gridObjectData.getWidth(),
	 * gridObjectData.getHeight());
	 * 
	 * addGridObject(gridobject, gridObjectData.getX(), gridObjectData.getY());
	 * 
	 * } } } }
	 */

	@Override
	public void run() {

	}
}