package engine.gridobject.person;

import java.awt.event.KeyEvent;

import engine.AbstractState;
import engine.WalkAroundState;
import engine.gridobject.Door;
import engine.gridobject.item.Item;
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

	public boolean hasItem(String myItemName) {
		if (myItemName != null) {
			for (Item i : super.getItems()) {
				if (i.getName().equals(myItemName)) return true;
			}
		}

		return false;
	}

}
