package engine.gridobject.person;

import java.awt.event.KeyEvent;
import java.util.List;

import engine.AbstractGameState;
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
			GridObject surrounding = mySurroundingChecker.checkSurroundings(this).get(0);
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
		List<GridObject> surroundings = mySurroundingChecker.checkSurroundings(this);
		for(GridObject surrounding : surroundings){
			if(surrounding instanceof Door && ((Door) surrounding).playerAtDoor(this)){
				return  (Door) surrounding;
			}
		}
		
		return null;
	}
}
