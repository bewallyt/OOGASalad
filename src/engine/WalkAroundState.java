package engine;

import java.awt.event.KeyEvent;

import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.World;

public class WalkAroundState extends AbstractGameState {

	public WalkAroundState(Canvas c, World world) {
		super(c, world);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		for (GridObject gridObject : super.getWorld().getGridObjectList()){
			if (gridObject instanceof Player){
				((Player) gridObject).keyPressed(e);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		for (GridObject gridObject : super.getWorld().getGridObjectList()){
			if (gridObject instanceof Player){
				((Player) gridObject).keyReleased(e);
			}
		}
	}
}