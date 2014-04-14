package engine.gridobject.person;

import java.awt.event.KeyEvent;
import java.util.List;

import engine.AbstractState;
//import engine.AbstractGameState;
import engine.Control;
import engine.WalkAroundState;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.world.SurroundingChecker;

public class Player extends RuleFollower {
	private int count = 0;

	public boolean aClick = false;
	private AbstractState myState;
	private SurroundingChecker mySurroundingChecker;
	private String[] myAnimImages;
	private boolean enterDoor;
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
	
	public Door enterBuilding(){
		List<GridObject> surroundings = mySurroundingChecker.checkSurroundings(this);
		for(GridObject surrounding : surroundings){
			if(surrounding instanceof Door && ((Door) surrounding).playerAtDoor(this)){
				return  (Door) surrounding;
			}
		}
		
		return null;
	}

	public SurroundingChecker getSurroundingChecker() {
		return mySurroundingChecker;
	}

}
