package engine.gridobject.person;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.world.Tile;

public class Player extends RuleFollower {
	public boolean aClick = false;
	public Player(String image, double speed, int numTilesWidth, int numTilesHeight) {
		super(image, speed, numTilesWidth, numTilesHeight);
		myItems = null;
	
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Control.UP)
			myDY = -mySpeed;
		if (e.getKeyCode() == Control.DOWN)
			myDY = mySpeed;
		if (e.getKeyCode() == Control.RIGHT)
			myDX = mySpeed;
		if (e.getKeyCode() == Control.LEFT)
			myDX = -mySpeed;
		if (e.getKeyCode() == Control.A)
			aClick = true;
			
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Control.UP
				|| e.getKeyCode() == Control.DOWN)
			myDY = 0;

		if (e.getKeyCode() == Control.RIGHT
				|| e.getKeyCode() == Control.LEFT)
			myDX = 0;
		if (e.getKeyCode() == Control.A)
			aClick=false;
	}
	
	public boolean getAClick(){
		return aClick;
	}
	
	
}
