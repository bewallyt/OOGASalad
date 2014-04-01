package engine.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;

import javax.swing.JPanel;

import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.gridobject.NPC;
import engine.gridobject.Player;
import engine.world.World;

public class Engine extends JPanel {

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
		Engine engine = new Engine();
		World world = engine.initializeWorld(20,20,40,40);
		
		world.setTileObject(new Player("p.png", 2), 3, 3);
		world.setTileObject(new NPC("cabinets.jpg", 2), 4, 4);
		CollisionMatrix cm = new CollisionMatrix(world.getGridObjectList());
		engine.doGameLoop(world, cm);
	}

}