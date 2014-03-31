package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.gridobject.GridObject;
import engine.gridobject.Player;
import engine.world.World;

public class Control implements KeyListener{
	World myWorld;
	public Control (World world){
		myWorld = world;
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
