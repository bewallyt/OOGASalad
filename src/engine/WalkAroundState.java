package engine;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.World;

public class WalkAroundState extends AbstractGameState {

	Set<Integer> pressedKeys = new HashSet<Integer>();
	public WalkAroundState(Canvas c, World world) {
		super(c, world);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		for (GridObject gridObject : super.getWorld().getGridObjectList()){
			if (gridObject instanceof Player && pressedKeys.size()==0){
				((Player) gridObject).keyPressed(e);
				pressedKeys.add(e.getKeyCode());
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		for (GridObject gridObject : super.getWorld().getGridObjectList()){
			if (gridObject instanceof Player){
				((Player) gridObject).keyReleased(e);
				pressedKeys.remove(e.getKeyCode());
			}
		}
	}
}