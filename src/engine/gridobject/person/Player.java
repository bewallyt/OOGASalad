package engine.gridobject.person;

import java.awt.event.KeyEvent;

import engine.gridobject.Door;
import engine.gridobject.item.Item;
import engine.state.AbstractState;
import engine.state.WalkAroundState;
import engine.world.SurroundingChecker;
//import engine.AbstractGameState;

public class Player extends Person {
	private int count = 0;

	public boolean aClick = false;
	private AbstractState myState;
	private SurroundingChecker mySurroundingChecker;
	private String[] myAnimImages;
	private Door enteredDoor=null;
	private double originalSpeed;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param animImages the anim images (must be 12)
	 * @param speed the speed of the player
	 * @param numTilesWidth the width of the player in tiles
	 * @param numTilesHeight the height of the player in tiles
	 */
	public Player(String[] animImages, double speed, int numTilesWidth, int numTilesHeight) {
		super(animImages, speed, numTilesWidth, numTilesHeight);
		myState = new WalkAroundState(this);
		setMyItems(null);
	}
	
	public void setSurroundingsChecker(SurroundingChecker surroundingChecker){
		mySurroundingChecker = surroundingChecker;
	}
	
	public void setState(AbstractState state) {
		myState = state;
	}
	
	public void keyPressed(KeyEvent e) {
		myState.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		myState.keyReleased(e);
	}
	
	public void setAClick(boolean b) {
		aClick = b;
	}
	
	public double getOriginalSpeed(){
		return originalSpeed;
	}

	public Door isDoorEntered(){
		Door door = enteredDoor;
		enteredDoor=null;
		return door;
	}
	
	public void enterDoor(Door door){
		enteredDoor=door;
	}

	public SurroundingChecker getSurroundingChecker() {
		return mySurroundingChecker;
	}

	/**
	 * Checks for item.
	 *
	 * @param myItemName the my item name
	 * @return true, if player has the item
	 */
	public boolean hasItem(String myItemName) {
		if (myItemName != null) {
			for (Item i : super.getItems()) {
				if (i.getName().equals(myItemName)) return true;
			}
		}

		return false;
	}

}
