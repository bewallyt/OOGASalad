package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import engine.world.Canvas;
import engine.world.World;

public class Control implements KeyListener{
	
	private World myWorld;
	private Canvas  myCanvas;
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
