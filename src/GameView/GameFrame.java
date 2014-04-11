package GameView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engine.Dialogue;
import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
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
//import authoring.PlayerData;
import authoring.TileData;
import authoring.WorldData;
import Data.DataDummy;
import Data.DataManager;
import Data.FileStorer;

import javax.swing.JFrame;

import engine.gridobject.person.Enemy;

public class GameFrame extends RPGEngine {

	// private int spriteWidth = 1;
	// private int spriteHeight = 1;
	private WorldData myWorldData;
	// private DataDummy myData;
	// private JFrame myFrame;
	private DataManager myData;

	Player myPlayer;
	NPC myNPC;

	public GameFrame(String fileName) {
//		myData = new FileStorer();
		myData = new DataManager();
//			myWorldData = myData.getWorldData(fileName);
		myWorldData = myData.loadWorldDataFromFile(fileName);
		

		initializeGame();
	}

	public static void main(String[] args) {
		Main engine = new Main();
		engine.initializeGame();
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	public void makeOutsideWorld() {
//		WalkAroundWorld outsideWorld = new WalkAroundWorld(40, 1000, 1000);
//
//		addNewWorld(outsideWorld);
//		outsideWorld.paintFullBackround("grass.jpg");
//
//		String[] anim = new String[] { "PlayerUp0.png", "PlayerUp1.png",
//				"PlayerUp2.png", "PlayerRight0.png", "PlayerRight1.png",
//				"PlayerRight2.png", "PlayerDown0.png", "PlayerDown1.png",
//				"PlayerDown2.png", "PlayerLeft0.png", "PlayerLeft1.png",
//				"PlayerLeft2.png" };
//		addPlayer(anim, 2, 1, 1);
//
//		addGridObject(getPlayer(), 3, 3);
//		Enemy bafm = new Enemy(new String[] { "rival.png", "rival.png",
//				"rival.png", "rival.png" }, 1, 1, 1, 3, getPlayer());
//		bafm.battleOnSight();
//		addGridObject(bafm, 10, 10);
//		bafm.addDialogue("Hey fight me");
//		Barrier pokeCenter = new Barrier("pokecenter.png", 4, 4);
//		pokeCenter.setDoor(222, 278);
//		addGridObject(pokeCenter, 4, 3);
//		WalkAroundWorld buildingWorld = new WalkAroundWorld(40, 1000, 1000);
//		buildingWorld.paintFullBackround("pokecenterfloor.png");
//		buildingWorld.setTileObject(new Barrier("cabinets.jpg", 3, 1),
//				getCurrentWorld().getTileGridWidth() / 2, getCurrentWorld()
//						.getTileGridHeight() - 2);
//		pokeCenter.getDoor().setBuildingWorld(buildingWorld);
//
//		for (int i = 0; i < outsideWorld.getTileGridWidth(); i++) {
//			addGridObject(new Barrier("tree.png", 1, 2), i, 0);
//			addGridObject(new Barrier("tree.png", 1, 2), i,
//					outsideWorld.getTileGridHeight() - 1 - 1);
//		}
//		for (int i = 0; i < outsideWorld.getTileGridHeight(); i++) {
//			addGridObject(new Barrier("tree.png", 1, 2), 0, i);
//			addGridObject(new Barrier("tree.png", 1, 2),
//					outsideWorld.getTileGridWidth() - 1, i);
//		}
//	}
//	
	/*
	 * Communication between Data and Engine test below:
	 * makeOutsideWorld()
	 * addPlayer()
	 * addEnemy()
	 */

	public void makeOutsideWorld() {
		// Fit authoring data
		WalkAroundWorld outsideWorld = new WalkAroundWorld(40, 1000, 1000);

		addNewWorld(outsideWorld);

		TileData currTile;
		List<GridObjectData> currGridObjectDatas = new ArrayList<GridObjectData>();
		
		addPlayer();
		addEnemy();

		for (int i = 0; i < myWorldData.getMap("defaultworldkey")
				.getMapLength(); i++) {
			for (int j = 0; j < myWorldData.getMap("defaultworldkey")
					.getMapWidth(); j++) {
				currTile = myWorldData.getMap("defaultworldkey").getTileData(i,
						j);
				currGridObjectDatas = currTile.getGridObjectDatas();

				for (GridObjectData gridObjectData : currGridObjectDatas) {
					// Defaulted at Barrier for now.
					Barrier tempBarrier = (Barrier) Reflection.createInstance("Barrier",
							gridObjectData.getImageName(),
							gridObjectData.getWidth(),
							gridObjectData.getHeight());
					
					addGridObject(tempBarrier, gridObjectData.getX(), gridObjectData.getY());

				}
			}
		}
	}
	
	public void addPlayer(){


		String[] anim = new String[] { "PlayerUp0.png", "PlayerUp1.png",
				"PlayerUp2.png", "PlayerRight0.png", "PlayerRight1.png",
				"PlayerRight2.png", "PlayerDown0.png", "PlayerDown1.png",
				"PlayerDown2.png", "PlayerLeft0.png", "PlayerLeft1.png",
				"PlayerLeft2.png" };
		addPlayer(anim, 2, 1, 1);

		addGridObject(getPlayer(), 3, 3);
	}
	
	public void addEnemy(){
		Enemy bafm = new Enemy(new String[] { "rival.png", "rival.png",
				"rival.png", "rival.png" }, 1, 1, 1, 3, getPlayer());
		bafm.battleOnSight();
		addGridObject(bafm, 10, 10);
		bafm.addDialogue("Hey fight me");
	}

	@Override
	public void initializeGame() {
		initializeCanvas(400, 400);
		makeOutsideWorld();

	}

	@Override
	public void run() {

	}

}