package engine.gridobject.person;

import java.awt.event.KeyEvent;
import java.util.List;

import engine.*;
import engine.gridobject.GridObject;
import engine.world.SurroundingChecker;
import engine.world.Tile;

public class Player extends RuleFollower {
	private int count = 0;

	public boolean aClick = false;
	//private KeyHandler myKeyHandler;
	private SurroundingChecker mySurroundingChecker;
	
	private boolean isAnimated = false;
	private String[] myAnimImages;
 
	
	public Player(String image, double speed, int numTilesWidth, int numTilesHeight, SurroundingChecker checker) {
		super(image, speed, numTilesWidth, numTilesHeight);
		setMyItems(null);
		mySurroundingChecker = checker;
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
		System.out.println(count);
		if (e.getKeyCode() == AbstractGameState.UP){
			setDY(-super.getSpeed());
			if(isAnimated)	setImage(myAnimImages[6]);
		}
		if (e.getKeyCode() == AbstractGameState.DOWN){
			setDY(super.getSpeed());
			if(isAnimated && count < 10)	{
				setImage(myAnimImages[0]);
				count++;
			}
			if(isAnimated && count >= 10 && count < 20) {
				setImage(myAnimImages[1]);
				count++;
			}
			if(isAnimated && count >= 20) {
				setImage(myAnimImages[2]);
				count++;
			}
			if(count>30)
				count = 0;
		}
		if (e.getKeyCode() == AbstractGameState.RIGHT){
			setDX(super.getSpeed());
			if(isAnimated)	setImage(myAnimImages[9]);
		}
		if (e.getKeyCode() == AbstractGameState.LEFT){
			setDX(-super.getSpeed());
			if(isAnimated)	setImage(myAnimImages[3]);
		}		
		if (e.getKeyCode() == AbstractGameState.A) {
			aClick = true;
			GridObject surroundingNPC = mySurroundingChecker.checkSurroundings(this);
			// to do: call surroundingNPC.doDialogue()...but this hasn't been implemented yet, 
			// later tonight or tomorrow morning.

		}
			
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == AbstractGameState.UP
				|| e.getKeyCode() == AbstractGameState.DOWN)
			setDY(0);

		if (e.getKeyCode() == AbstractGameState.RIGHT
				|| e.getKeyCode() == AbstractGameState.LEFT)
			setDX(0);
		if (e.getKeyCode() == AbstractGameState.A)
			aClick=false;
	}
	
	public boolean getAClick(){
		return aClick;
	}
	
	
}
