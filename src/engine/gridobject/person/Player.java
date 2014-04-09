package engine.gridobject.person;

import java.awt.event.KeyEvent;
import java.util.List;

import engine.*;
import engine.gridobject.Building;
import engine.gridobject.GridObject;
import engine.world.SurroundingChecker;
import engine.world.Tile;

public class Player extends RuleFollower {
	

	public boolean aClick = false;
	//private KeyHandler myKeyHandler;
	private SurroundingChecker mySurroundingChecker;
	private String[] myAnimImages;
	private AbstractGameState myState;
	private boolean enterDoor;
	private double originalSpeed;
 
	
	public Player(String[] animImages, double speed, int numTilesWidth, int numTilesHeight) {
		super(animImages, speed, numTilesWidth, numTilesHeight);
		myAnimImages = animImages;
		setMyItems(null);
	}
	
	public void setSurroundingsChecker(SurroundingChecker surroundingChecker){
		mySurroundingChecker = surroundingChecker;
	}
	public void getAnimImages(String[] animImages) {
		myAnimImages = animImages;
	}
	
	public void keyPressed(KeyEvent e) {

//		System.out.println("playerx: " + this.getX() + "playery: " + this.getY());
		if (e.getKeyCode() == AbstractGameState.UP){
			setDY(-getSpeed());
			GridObject surrounding = mySurroundingChecker.checkSurroundings(this);
			if(surrounding instanceof Building && ((Building) surrounding).playerAtDoor(this)){
				System.out.println("GO IN DOOR");
				((Building) surrounding).enterBuilding();
			}
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
		if (e.getKeyCode() == AbstractGameState.SPACE) {
			//this.setSize(get)
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
	
	
}
