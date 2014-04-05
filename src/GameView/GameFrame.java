package GameView;

import engine.Dialogue;
import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
import engine.gridobject.GridObject;
import engine.gridobject.person.BackAndForthMover;
import engine.gridobject.person.Player;
import engine.world.Canvas;
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
	BackAndForthMover myEnemy;
	
	public GameFrame(){
		myData = new DataDummy();
		myWorldData = myData.getWorldData();
		
		initializeGame();
	}
	
	public void addObjects(World world){
		addGridObject(myPlayer = initPlayer(), 3, 3);
		
//		hard coded		
		BackAndForthMover bafm = myEnemy= new BackAndForthMover("rival.png",1,1,1, 350, 550, 0, 0, myPlayer);
		addGridObject(bafm,10,10);
		bafm.addDialogue("Hey Bitch. Fight Me!");
		addGridObject(new Barrier("pokecenter.png",4, 4), 4, 3);

		for(int i=0; i<world.getTileGridHeight(); i++){
			addGridObject(new Barrier("tree.png",1,2), i, 0);
			addGridObject(new Barrier("tree.png",1,2), i, world.getTileGridHeight()-1-1);
		}
		for(int i=0; i<world.getTileGridWidth(); i++){
			addGridObject(new Barrier("tree.png",1,2), 0, i);
			addGridObject(new Barrier("tree.png",1,2), world.getTileGridWidth()-1,i );
		}
		
//		work in progress till Data and authoring are ready

		/*
		TileData currTile;
		GridObjectData[] currGridObjectDatas;
		
		for(int i = 0; i < myWorldData.getWorldSize()[0]; i++){
			for(int j = 0; j < myWorldData.getWorldSize()[1]; j++){
				currTile = myWorldData.getTileData(i, j);
				currGridObjectDatas = currTile.getGridObjectDatas();

				for(int k = 0; k < currGridObjectDatas.length; k++){
//				need to figure out stuff here
					addGridObject(new GridObject("", 0, 0));
				}
			}
		} 
		*/

	}

	@Override
	public void initializeGame() {
		initializeCanvas(800, 800);
//		initializeCanvas(myWorldData.getWorldSize()[0], myWorldData.getWorldSize()[1]);
		addNewWalkAroundWorld(40,"grass.jpg");
		addObjects(getCurrentWorld());
	}

	@Override
	public void run() {
		if(myPlayer.getAClick())
			myEnemy.doNextDialogue();
	}
	
	private Player initPlayer() {

//		hard coded
		String[] animImages = new String[12];
		animImages[0] = "fs.png";
		animImages[3] = "ls.png";
		animImages[6] = "bs.png";
		animImages[9] = "rs.png";
		Player player = new Player("player.png",2,spriteWidth, spriteHeight);
		player.getAnimImages(animImages);
		return player;

//		work in progress till data and authoring are ready
		/*	
		PlayerData playerData = myWorldData.getPlayerData();
		
		Player player = new Player(playerData.getMyImage(),2,spriteWidth, spriteHeight);
		if(playerData.isAnimated())
			player.getAnimImages(playerData.getMyAnimImages());
		return player;
		*/
	}

	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//		Canvas canvas = new Canvas (800,800);
		//		WalkAroundWorld waWorld = new WalkAroundWorld(40, canvas.getWidth(), canvas.getHeight());
		//		canvas.setWorld(waWorld);
		//		engine.addObjects(waWorld);
		//		CollisionMatrix cm = new CollisionMatrix(waWorld.getGridObjectList());
		//		engine.doGameLoop(waWorld, cm);
	}

}