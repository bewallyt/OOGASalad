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
import engine.gridobject.Player;
import engine.world.World;



public class Engine extends JPanel{
	
	public World initializeWorld(){
		World world = new World(40, 40, 20, 20);
		world.initCanvas();
		world.makeTileMatrix();
		return world;
	}
	
	public void doGameLoop(World world) {
		while (true) {
			world.repaint();
			for(GridObject go : world.getGridObjectList()){
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
		World world = engine.initializeWorld();
		world.setTileObject(new Player("grass.jpg", 2),10,10);
		CollisionMatrix cm = new CollisionMatrix(world.getGridObjectList());
		engine.doGameLoop(world);
	}
	
}