package engine.main;

import javax.swing.JPanel;

import engine.collision.CollisionMatrix;
import engine.gridobject.BackAndForthMover;
import engine.gridobject.Barrier;
import engine.gridobject.Enemy;
import engine.gridobject.GridObject;
import engine.gridobject.Player;
import engine.world.Canvas;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class Main extends JPanel {
	
	
		
	
//	public World initializeWorld(int tileWidth, int tileHeight, int numTileSize ) {
//		World world = new World(tileWidth, tileHeight, numTileSize);
//		world.initCanvas();
//		world.makeTileMatrix();
//		return world;
//	}

	public void checkCollisions(World world, CollisionMatrix cm) {
		for (int i = 0; i < world.getGridObjectList().size(); i++) {
			for (int j = 0; j < world.getGridObjectList().size(); j++) {
				if (world
						.getGridObjectList()
						.get(i)
						.getBounds()
						.intersects(
								world.getGridObjectList().get(j).getBounds())) {
					cm.getMatrix()[i][j].doCollision();
				}
			}
		}
	}

	public void doGameLoop(World world, CollisionMatrix cm) {
		while (true) {
			world.repaint();
			checkCollisions(world, cm);
			for (GridObject go : world.getGridObjectList()) {
				go.move();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addObjects(World world){
		world.setTileObject(new Player("p.png", 2,1), 3, 3);
		world.setTileObject(new BackAndForthMover("rival.png",1,1, 150, 300, 0, 0),5,5);
		for(int i=0; i<world.getTileSize()[0]; i++){
			world.setTileObject(new Barrier("tree.png",2), i, 0);
			world.setTileObject(new Barrier("tree.png",2), i, world.getTileSize()[1]-1-1);
		}
		for(int i=0; i<world.getTileSize()[1]; i++){
			world.setTileObject(new Barrier("tree.png",2), 0, i);
			world.setTileObject(new Barrier("tree.png",2), world.getTileSize()[0]-1,i );
		}
		
		world.setTileObject(new Barrier("cabinets.jpg",2), 4, 3);
	}

	public static void main(String[] args) {
		Main engine = new Main();
		Canvas canvas = new Canvas (800,800);
		WalkAroundWorld waWorld = new WalkAroundWorld(40, canvas.getWidth(), canvas.getHeight());
		canvas.setWorld(waWorld);
		engine.addObjects(waWorld);
		CollisionMatrix cm = new CollisionMatrix(waWorld.getGridObjectList());
		engine.doGameLoop(waWorld, cm);
	}

}