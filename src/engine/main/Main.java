package engine.main;

import engine.Dialogue;
import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
import engine.gridobject.Building;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.SurroundingChecker;
import engine.world.Tile;
import engine.world.WalkAroundWorld;
import engine.world.World;

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

	public void makeOutsideWorld(){
		WalkAroundWorld outsideWorld = new WalkAroundWorld(40, 1000, 1000, "grass.jpg");
		addNewWorld(outsideWorld);
		addPlayer(new String[] {"PlayerUp.png","PlayerRight.png", "PlayerDown.png", "PlayerLeft.png"},2,1, 1);

		addGridObject(getPlayer(), 3, 3);
		Enemy bafm = new Enemy(new String[] {"rival.png","rival.png","rival.png","rival.png"},1,1,1, 3, getPlayer());
		bafm.battleOnSight();
		addGridObject(bafm,10,10);
		bafm.addDialogue("Hey fight me");
		Building pokeCenter = new Building("pokecenter.png",4, 4, 222, 278);
		addGridObject(pokeCenter, 4, 3);
		pokeCenter.setBuildingWorld(new WalkAroundWorld(40, 1000, 1000, "pokecenterfloor.jpg"));

		for(int i=0; i<outsideWorld.getTileGridWidth(); i++){
			addGridObject(new Barrier("tree.png",1,2), i, 0);
			addGridObject(new Barrier("tree.png",1,2), i, outsideWorld.getTileGridHeight()-1-1);
		}
		for(int i=0; i<outsideWorld.getTileGridHeight(); i++){
			addGridObject(new Barrier("tree.png",1,2), 0, i);
			addGridObject(new Barrier("tree.png",1,2), outsideWorld.getTileGridWidth()-1,i );
		}
	}
	
	public void makeBuildingWorld(){
		WalkAroundWorld buildingWorld = new WalkAroundWorld(40, 1000, 1000, "pokecenterfloor.jpg");
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