package engine.main;

import engine.Dialogue;
import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
import engine.gridobject.person.BackAndForthMover;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.SurroundingChecker;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class Main extends RPGEngine {

	private Player myPlayer;
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
		
		SurroundingChecker checker = new SurroundingChecker(world);
		Player player = myPlayer = new Player("player.png",2,1, 1, checker);
		addGridObject(player, 3, 3);
		NPC bafm = myNPC= new NPC("rival.png",1,1,1, 2, player);
		addGridObject(bafm,10,10);

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
		if(myPlayer.getAClick())
			myNPC.doNextDialogue();
	}



}