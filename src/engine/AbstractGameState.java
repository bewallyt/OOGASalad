package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.World;

public abstract class AbstractGameState implements KeyListener{
	
	private World myWorld;
	private Canvas  myCanvas;
	public static int UP = 38;
	public static int DOWN = 40;
	public static int LEFT = 37;
	public static int RIGHT = 39;
	public static int A = 65;
	public static int SPACE = 32;
	
	public AbstractGameState (Canvas c, World world){
		myWorld = world;
		myCanvas = c;
	}
	
	public World getWorld() {
		return myWorld;
	}
	
	public Canvas getCanvas() {
		return myCanvas;
	}
	
	@Override
	public abstract void keyPressed(KeyEvent e);
				
	

	@Override
	public abstract void keyReleased(KeyEvent e);
		
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
