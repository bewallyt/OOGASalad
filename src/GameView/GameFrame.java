package GameView;

import java.util.ArrayList;
import java.util.List;

import engine.Dialogue;
import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
import engine.gridobject.GridObject;
import engine.gridobject.person.BackAndForthMover;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.SurroundingChecker;
import engine.world.WalkAroundWorld;
import engine.world.World;
import engine.main.RPGEngine;
import authoring.GridObjectData;
//import authoring.PlayerData;
import authoring.TileData;
import authoring.WorldData;
import Data.DataDummy;

public class GameFrame extends RPGEngine {

	private int spriteWidth = 1;
	private int spriteHeight = 1;
	private WorldData myWorldData;
	private DataDummy myData;

	Player myPlayer;
	NPC myNPC;
	BackAndForthMover myEnemy;

	public GameFrame() {
		myData = new DataDummy();
		myWorldData = myData.getWorldData();

		initializeGame();
	}

	public void addObjects(World world){
		myPlayer = initPlayer();
//		hard coded
		NPC bafm = myNPC = new NPC("rival.png", 1, 1, 1, 2, myPlayer);
		addGridObject(bafm, 10, 10);
		addGridObject(new Barrier("pokecenter.png",4, 4), 4, 3);

//		TileData currTile;
//		List<GridObjectData> currGridObjectDatas = new ArrayList<GridObjectData>();
//		
//		for(int i = 0; i < myWorldData.getMap("defaultworldkey").getMapLength(); i++){
//			for(int j = 0; j < myWorldData.getMap("defaultworldkey").getMapWidth(); j++){
//				currTile = myWorldData.getMap("defaultworldkey").getTileData(i, j);
//				currGridObjectDatas = currTile.getGridObjectDatas();
//
//				for(int k = 0; k < currGridObjectDatas.size(); k++){
////				need to figure out stuff here
//					addGridObject(new GridObject("", 0, 0));
//				}
//			}
//		} 


		for(int i=0; i<world.getTileGridHeight(); i++){
			addGridObject(new Barrier("tree.png",1,2), i, 0);
			addGridObject(new Barrier("tree.png",1,2), i, world.getTileGridHeight()-1-1);
		}
		for(int i=0; i<world.getTileGridWidth(); i++){
			addGridObject(new Barrier("tree.png",1,2), 0, i);
			addGridObject(new Barrier("tree.png",1,2), world.getTileGridWidth()-1,i );
		}
		

		
//		work in progress till Data and authoring are ready


//	public void addObjects(World world) {
//
//		TileData currTile;
//		List<GridObjectData> currGridObjectDatas = new ArrayList<GridObjectData>();
//
//		for (int i = 0; i < myWorldData.getMap("defaultworldkey")
//				.getMapLength(); i++) {
//			for (int j = 0; j < myWorldData.getMap("defaultworldkey")
//					.getMapWidth(); j++) {
//				currTile = myWorldData.getMap("defaultworldkey").getTileData(i,
//						j);
//				currGridObjectDatas = currTile.getGridObjectDatas();
//
//				for (int k = 0; k < currGridObjectDatas.size(); k++) {
//					// need to figure out stuff here
//					addGridObject(new GridObject("", 0, 0));
//				}
//			}
//		}

	}

	@Override
	public void initializeGame() {
		initializeCanvas(800, 800);
		// initializeCanvas(myWorldData.getWorldSize()[0],
		// myWorldData.getWorldSize()[1]);
		addNewWalkAroundWorld(40, "grass.jpg");
		addObjects(getCurrentWorld());
	}

	@Override
	public void run() {
		if (myPlayer.getAClick())
			myEnemy.doNextDialogue();
	}

	private Player initPlayer() {

		// hard coded
		String[] animImages = new String[12];
		animImages[0] = "fs.png";
		//Test
		animImages[1] = "fw1.png";
		animImages[2] = "fw2.png";
		animImages[3] = "ls.png";
		animImages[6] = "bs.png";
		animImages[9] = "rs.png";
		SurroundingChecker checker = new SurroundingChecker(getCurrentWorld());
		Player player = new Player("player.png",2,spriteWidth, spriteHeight, checker);
		player.getAnimImages(animImages);
		
		addGridObject(player, 3, 3);
		return player;

		// work in progress till data and authoring are ready
		/*
		 * PlayerData playerData = myWorldData.getPlayerData();
		 * 
		 * Player player = new Player(playerData.getMyImage(),2,spriteWidth,
		 * spriteHeight); if(playerData.isAnimated())
		 * player.getAnimImages(playerData.getMyAnimImages()); return player;
		 */
	}


}