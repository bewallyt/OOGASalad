package engine.gridobject.person;

import java.awt.event.KeyEvent;

import engine.AbstractGameState;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.world.SurroundingChecker;

public class Player extends RuleFollower {
	private int count = 0;

	public boolean aClick = false;
	//private KeyHandler myKeyHandler;
	private SurroundingChecker mySurroundingChecker;
	private String[] myAnimImages;
	private AbstractGameState myState;
	private boolean enterDoor;
	private double originalSpeed;
 
	
	public Player(String[] animImages, double speed, int numTilesWidth, int numTilesHeight) {
		super(animImages, speed, numTilesWidth, numTilesHeight);
		setMyItems(null);
	}
	
	public void setSurroundingsChecker(SurroundingChecker surroundingChecker){
		mySurroundingChecker = surroundingChecker;
	}
	
	public void keyPressed(KeyEvent e) {
//		System.out.println("playerx: " + this.getX() + "playery: " + this.getY());
		if (e.getKeyCode() == AbstractGameState.UP){
			System.out.println("up");
			setDY(-getSpeed());
			
		}
		if (e.getKeyCode() == AbstractGameState.DOWN){
			setDY(getSpeed());
		}
		if (e.getKeyCode() == AbstractGameState.RIGHT){
			setDX(getSpeed());
		}
		if (e.getKeyCode() == AbstractGameState.LEFT){
			setDX(-getSpeed());
		}		
		if (e.getKeyCode() == AbstractGameState.A) {
			GridObject surrounding = mySurroundingChecker.checkSurroundings(this);
			if(surrounding!=null){
				surrounding.doDialogue();
			}
			
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
	
	public double getOriginalSpeed(){
		return originalSpeed;
	}
	
	public Door enterBuilding(){
		GridObject surrounding = mySurroundingChecker.checkSurroundings(this);
		if(surrounding instanceof Barrier && ((Barrier) surrounding).hasDoor() && ((Barrier) surrounding).getDoor().playerAtDoor(this) && getFacing()==0){
			System.out.println("GO IN DOOR");
			return  ((Barrier) surrounding).getDoor();
		}
		return null;
	}
	
	
}
