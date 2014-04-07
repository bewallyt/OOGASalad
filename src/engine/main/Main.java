package engine.main;

import engine.Dialogue;
import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
import engine.gridobject.person.BackAndForthMover;
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
//		Canvas canvas = new Canvas (800,800);
//		WalkAroundWorld waWorld = new WalkAroundWorld(40, canvas.getWidth(), canvas.getHeight());
//		canvas.setWorld(waWorld);
//		engine.addObjects(waWorld);
//		CollisionMatrix cm = new CollisionMatrix(waWorld.getGridObjectList());
//		engine.doGameLoop(waWorld, cm);
	}
	
	public void addObjects(World world){
		
		// setting background image for tiles
		for (int i = 0; i < world.getTileGridWidth(); i++) {
			for (int j = 0; j < world.getTileGridHeight(); j++) {
				world.getTileMatrix()[i][j].setBackgroundImage("grass.jpg");
			}
		}
		addPlayer("player.png",2,1, 1);
		addGridObject(getPlayer(), 3, 3);
		NPC bafm = myNPC= new NPC("rival.png",1,1,1, 3, getPlayer());
		addGridObject(bafm,10,10);
		bafm.addDialogue("Hey bitch fight me");
		
		addGridObject(new Barrier("pokecenter.png",4, 4), 4, 3);
		
		for(int i=0; i<world.getTileGridWidth(); i++){
			addGridObject(new Barrier("tree.png",1,2), i, 0);
			addGridObject(new Barrier("tree.png",1,2), i, world.getTileGridHeight()-1-1);
		}
		for(int i=0; i<world.getTileGridHeight(); i++){
			addGridObject(new Barrier("tree.png",1,2), 0, i);
			addGridObject(new Barrier("tree.png",1,2), world.getTileGridWidth()-1,i );
		}

//		addGridObject(new Dialogue("Dialogue.png","hello"),2,15);
		
		
	}

	@Override
	public void initializeGame() {
		initializeCanvas(800, 800);
		addNewWalkAroundWorld(40,"grass.jpg");
		addObjects(getCurrentWorld());
	}

	@Override
	public void run() {
		
	}



}