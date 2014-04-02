package engine.main;

import javax.swing.JPanel;

import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
import engine.gridobject.GridObject;
import engine.gridobject.Player;
import engine.world.World;

public class Main extends JPanel {
	
	
		
	

	public World initializeWorld(int numTileWidth, int numTileHeight,
			int tileWidth, int tileHeight) {
		World world = new World(numTileWidth, numTileHeight, tileWidth,
				tileHeight);
		world.initCanvas();
		world.makeTileMatrix();
		return world;
	}

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

	public static void main(String[] args) {
		Main engine = new Main();
		World world = engine.initializeWorld(20,20,40,40);
		
		world.setTileObject(new Player("p.png", 2), 3, 3);
		world.setTileObject(new Barrier("cabinets.jpg"), 10, 4);
		CollisionMatrix cm = new CollisionMatrix(world.getGridObjectList());
		engine.doGameLoop(world, cm);
	}

}