package engine.main;

import engine.collision.CollisionMatrix;
import engine.gridobject.BackAndForthMover;
import engine.gridobject.Barrier;
import engine.gridobject.Player;
import engine.world.Canvas;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class Main extends RPGEngine {


	
	public void addObjects(World world){
		Player player = new Player("p.png",2,1);
		addGridObject(player, 3, 3);
		addGridObject(new BackAndForthMover("rival.png",1,1, 350, 550, 0, 0,player),10,10);
		addGridObject(new Barrier("pokecenter.png",4), 4, 3);
		for(int i=0; i<world.getTileSize()[0]; i++){
			addGridObject(new Barrier("tree.png",2), i, 0);
			addGridObject(new Barrier("tree.png",2), i, world.getTileSize()[1]-1-1);
		}
		for(int i=0; i<world.getTileSize()[1]; i++){
			addGridObject(new Barrier("tree.png",2), 0, i);
			addGridObject(new Barrier("tree.png",2), world.getTileSize()[0]-1,i );
		}
		
		
	}

	@Override
	public void initializeGame() {
		initializeCanvas(800, 800);
		addNewWalkAroundWorld(40);
		addObjects(getCurrentWorld());
		System.out.println("add");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

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

}