package engine.gridobject.person;

import java.awt.event.KeyEvent;

import engine.*;
import engine.world.Tile;

public class Player extends RuleFollower {
	
	public boolean aClick = false;
	//private KeyHandler myKeyHandler;
	
	public Player(String image, double speed, int numTilesWidth, int numTilesHeight) {
		super(image, speed, numTilesWidth, numTilesHeight);
		myItems = null;
	
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == AbstractGameState.UP)
			myDY = -mySpeed;
		if (e.getKeyCode() == AbstractGameState.DOWN)
			myDY = mySpeed;
		if (e.getKeyCode() == AbstractGameState.RIGHT)
			myDX = mySpeed;
		if (e.getKeyCode() == AbstractGameState.LEFT)
			myDX = -mySpeed;
		if (e.getKeyCode() == AbstractGameState.A)
			aClick = true;
			
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == AbstractGameState.UP
				|| e.getKeyCode() == AbstractGameState.DOWN)
			myDY = 0;

		if (e.getKeyCode() == AbstractGameState.RIGHT
				|| e.getKeyCode() == AbstractGameState.LEFT)
			myDX = 0;
		if (e.getKeyCode() == AbstractGameState.A)
			aClick=false;
	}
	
	public boolean getAClick(){
		return aClick;
	}
	
	
}
