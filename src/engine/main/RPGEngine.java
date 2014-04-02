package engine.main;

import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.gridobject.NPC;
import engine.gridobject.Player;
import engine.world.World;

public abstract class RPGEngine{
	public abstract void initializeGame();
	
	public abstract void run();
	
	public World initializeWorld(int numTileWidth, int numTileHeight,
			int tileWidth, int tileHeight) {
		World world = new World(numTileWidth, numTileHeight, tileWidth,
				tileHeight);
		world.initCanvas();
		world.makeTileMatrix();
		return world;
	}

	private void doGameLoop(World world, CollisionMatrix cm) throws InterruptedException {
		while (true) {
			world.repaint();
			checkCollisions(world, cm);
			for (GridObject go : world.getGridObjectList()) {
				go.move();
			}
			run();
			Thread.sleep(10);
		}
		
	}

	private void checkCollisions(World world, CollisionMatrix cm) {
		for (int i = 0; i < world.getGridObjectList().size(); i++) {
			for (int j = 0; j < world.getGridObjectList().size(); j++) {
				if (world.getGridObjectList().get(i).getBounds().intersects(
						world.getGridObjectList().get(j).getBounds())) {
					cm.getMatrix()[i][j].doCollision();
				}
			}
		}
	}
	
	public void main(){
		
	}
}
