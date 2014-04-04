package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.World;

public class Control implements KeyListener{
	private World myWorld;
	private Canvas  myCanvas;
	public static int UP = 38;
	public static int DOWN = 40;
	public static int LEFT = 37;
	public static int RIGHT = 39;
	public static int A = 65;
	
	public Control (Canvas c, World world){
		myWorld = world;
		myCanvas = c;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		for (GridObject gridObject : myWorld.getGridObjectList()){
			if (gridObject instanceof Player){
				((Player) gridObject).keyPressed(e);
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (GridObject gridObject : myWorld.getGridObjectList()){
			if (gridObject instanceof Player){
				((Player) gridObject).keyReleased(e);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		
	}

}
