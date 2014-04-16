package engine.main;

import java.util.ArrayList;
import java.util.List;

import engine.collision.EnterCollision;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;

public class Main extends RPGEngine {

//	private Player myPlayer;
	private NPC myNPC;


	public static void main(String[] args) {
		Main engine = new Main();
		engine.initializeGame();
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void makeOutsideWorld(){
		List<GridObject> gridObjectList = new ArrayList<GridObject>();
		List<GridObject> gridObjectList2 = new ArrayList<GridObject>();
		
		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
				"PlayerLeft1.png", "PlayerLeft2.png"};
		Player player = new Player(anim, 2, 1, 1);
		Door door = new Door("cabinets.jpg", 1, 1);
		Door door2 = new Door("cabinets.jpg", 1, 1);
		Enemy enemy = new Enemy(anim,2,1,1,1, player);
		enemy.doBattleOnSight();
		
		gridObjectList.add(player);
		gridObjectList.add(new Barrier("pokecenter.png",4, 4));
		gridObjectList.add(door);
		gridObjectList.add(enemy);
		
		gridObjectList2.add(player);
		gridObjectList2.add(new Barrier("pokecenter.png",4, 4));
		gridObjectList2.add(door2);
	
		
		
		
		WalkAroundWorld outsideWorld = new WalkAroundWorld(1000, 1000, player, 40,gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world
		
		WalkAroundWorld buildingWorld = new WalkAroundWorld(1000, 1000, player, 40, gridObjectList2);
		door.setBuildingWorld(buildingWorld);
		door2.setBuildingWorld(outsideWorld);
		
		outsideWorld.setTileObject(gridObjectList.get(0), 1, 6);
		outsideWorld.setTileObject(gridObjectList.get(1), 2, 2);
		outsideWorld.setTileObject(gridObjectList.get(2), 4, 5);
		outsideWorld.setTileObject(gridObjectList.get(3), 10, 10);
		outsideWorld.paintFullBackround("grassSmall.png");
		outsideWorld.setCollisionHandler(new EnterCollision(gridObjectList.get(0), 
															gridObjectList.get(2)),0,2);
		
		buildingWorld.setTileObject(gridObjectList2.get(0), 4, 13);
		buildingWorld.setTileObject(gridObjectList2.get(1), 2, 2);
		buildingWorld.setTileObject(gridObjectList2.get(2), 4, 14);
		buildingWorld.paintFullBackround("pokecenterfloor.png");
		buildingWorld.setCollisionHandler(new EnterCollision(gridObjectList2.get(0), 
				gridObjectList2.get(2)),0,2);
		




		
//		addPlayer(anim,2,1, 1);


//		addGridObject(getPlayer(), 3, 3);
//		Enemy bafm = new Enemy(new String[] {"rival.png","rival.png","rival.png","rival.png"},1,1,1, 3, getPlayer());
//		bafm.battleOnSight();
//		addGridObject(bafm,10,10);
//		bafm.addDialogue("Hey fight me");
//		Barrier pokeCenter = new Barrier("pokecenter.png",4, 4);
//		addGridObject(pokeCenter, 4, 3);
//		
//		for(int i=0; i<outsideWorld.getTileGridWidth(); i++){
//			addGridObject(new Barrier("tree.png",1,2), i, 0);
//			addGridObject(new Barrier("tree.png",1,2), i, outsideWorld.getTileGridHeight()-1-1);
//		}
//		for(int i=0; i<outsideWorld.getTileGridHeight(); i++){
//			addGridObject(new Barrier("tree.png",1,2), 0, i);
//			addGridObject(new Barrier("tree.png",1,2), outsideWorld.getTileGridWidth()-1,i );
//		}
//		
//
//		


//		Barrier pokeCenter2 = new Barrier("pokecenter.png",4, 4);
//		buildingWorld.setTileObject(pokeCenter2, 4, 3);
		
//		Door door = new Door("cabinets.jpg", 1, 1);
//		door.setBuildingWorld(buildingWorld);
//		addGridObject(door, 6, 6);


	}

	@Override
	public void initializeGame() {
		initializeCanvas(800, 800);
		makeOutsideWorld();

	}

	@Override
	public void run() {

	}



}