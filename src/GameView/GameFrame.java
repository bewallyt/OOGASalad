
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

import engine.gridobject.person.Enemy;

public class GameFrame extends RPGEngine {

	// temporary, will be removed when data adds this info into WorldData
	private final int DEFAULT_MOVEMENT_TYPE = 1;
	private final int DEFAULT_MOVEMENT_SPEED = 1;

	private WorldData myWorldData;
	// private DataManager myData;
	private FileStorer myData;

	private Player myPlayer;

	public GameFrame(String fileName) {
		// myData = new FileStorer();
		myData = new FileStorer();
		try {
			myWorldData = myData.getWorldData(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// myWorldData = myData.loadWorldDataFromFile(fileName);

		initializeGame();
	}

	@Override
	public void initializeGame() {
		initializeCanvas(400, 400);
		makeOutsideWorld();
	}

	// public static void main(String[] args) {
	// Main engine = new Main();
	// engine.initializeGame();
	// try {
	// myWorldData = myData.getWorldData(fileName);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // myWorldData = myData.loadWorldDataFromFile(fileName);
	//
	// initializeGame();
	// }

	/*
	 * Communication between Data and Engine test below: makeOutsideWorld()
	 * addPlayer() addEnemy()
	 */

	public void makeOutsideWorld() {
		createPlayer();
		List<GridObject> gridObjectList = createGridObjectList();

		WalkAroundWorld outsideWorld = new WalkAroundWorld(40, 1000, 1000,
				myPlayer, gridObjectList);

		setWorld(outsideWorld);

		// addEnemy();

		setGridObjects(outsideWorld, gridObjectList);
		outsideWorld.paintFullBackround("grassSmall.png");
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

	public void addEnemy() {
		Enemy bafm = new Enemy(new String[] { "rival.png", "rival.png",
				"rival.png", "rival.png" }, 1, 1, 1, 3, myPlayer);
		bafm.battleOnSight();
		// addGridObject(bafm, 10, 10);
		bafm.addDialogue("Hey, fight me!");
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