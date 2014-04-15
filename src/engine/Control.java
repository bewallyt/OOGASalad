package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

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
	public static int SPACE = 32;
	Set<Integer> pressedKeys = new HashSet<Integer>();

	
	public Control(Canvas c, World world){
		myWorld = world;
		myCanvas = c;
	}
	
	public World getWorld() {
		return myWorld;
	}
	
	public Canvas getCanvas() {
		return myCanvas;
	}
	
	public void keyPressed(KeyEvent e){
		if(pressedKeys.size() == 0) {
			myWorld.getPlayer().keyPressed(e);
			pressedKeys.add(e.getKeyCode());
		}
	}
				
	

	public void keyReleased(KeyEvent e) {		
		myWorld.getPlayer().keyReleased(e);
		pressedKeys.remove(e.getKeyCode());
	}
		
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
