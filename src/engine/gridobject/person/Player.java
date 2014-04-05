package engine.gridobject.person;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.world.Tile;

public class Player extends RuleFollower {
	public boolean aClick = false;
	
	private boolean isAnimated = false;
	private String[] myAnimImages;
	public Player(String image, double speed, int numTilesWidth, int numTilesHeight) {
		super(image, speed, numTilesWidth, numTilesHeight);
		myItems = null;
	}
	
//	public Player(String[] animImages, double speed, int numTilesWidth, int numTilesHeight) {
//		super(animImages, speed, numTilesWidth, numTilesHeight);
//		isAnimated = true;
//		myAnimImages = animImages;
//		myItems = null;
//	}
	
	public void getAnimImages(String[] animImages) {
		myAnimImages = animImages;
		isAnimated = true;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Control.UP){
			myDY = -mySpeed;
			if(isAnimated)	setImage(myAnimImages[6]);
		}
		if (e.getKeyCode() == Control.DOWN){
			myDY = mySpeed;
			if(isAnimated)	setImage(myAnimImages[0]);
		}
		if (e.getKeyCode() == Control.RIGHT){
			myDX = mySpeed;
			if(isAnimated)	setImage(myAnimImages[9]);
		}
		if (e.getKeyCode() == Control.LEFT){
			myDX = -mySpeed;
			if(isAnimated)	setImage(myAnimImages[3]);
		}
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
