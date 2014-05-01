package engine.gridobject.person;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import util.Constants;
import engine.gridobject.Door;
import engine.item.Item;
import engine.item.Weapon;
import engine.state.AbstractState;
import engine.state.WalkAroundState;
import engine.world.SurroundingChecker;

//import engine.AbstractGameState;

public class Player extends Person {

	private static final int MAX_EXPERIENCE = 100;
	private static final int MIN_EXPERIENCE = 0;
	private static final int DEFAULT_PLAYER_HEIGHT = 1;
	private static final int DEFAULT_PLAYER_WIDTH = 1;
	public boolean aClick = false;
	private AbstractState myState;
	private SurroundingChecker mySurroundingChecker;
	private Door enteredDoor = null;
	private double originalSpeed;
	private int myExperience;
	private Random myRandom = new Random();
	private String myName;

	/**
	 * Instantiates a new player.
	 *
	 * @param animImages the anim images
	 * @param name the name
	 * @param speed the speed
	 * @param items the items
	 * @param weps the weapons
	 */

	public Player(String[] animImages, String name, double speed, String[] items, String[] weps, HashMap<String, Weapon> allWeapons) {
		super(animImages, name, speed, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT);
		myState = new WalkAroundState(this);
		myExperience=0;
		myName = name;
		setMyStartX(9);
		setMyStartY(9);
		addAllWeapons(allWeapons, weps);

	}
	

	public Player(String[] animImages, String name, double speed, String[] items, String[] weps) {
		super(animImages, name, speed, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT);
		myState = new WalkAroundState(this);

		myExperience=MIN_EXPERIENCE;	}
	
	public Player(){
		super();
	}

	public void setSurroundingsChecker(SurroundingChecker surroundingChecker) {
		mySurroundingChecker = surroundingChecker;
	}

	public void setState(AbstractState state) {
		myState = state;
	}

	public AbstractState getState() {
		return myState;
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

	public double getOriginalSpeed() {
		return originalSpeed;
	}

	public Door isDoorEntered() {
		Door door = enteredDoor;
		enteredDoor = null;
		return door;
	}

	public void enterDoor(Door door) {
		enteredDoor = door;
	}

	public SurroundingChecker getSurroundingChecker() {
		return mySurroundingChecker;
	}

	/**
	 * Checks for item.
	 *
	 * @param myItemName the item name
	 * @return true, if the item exists
	 */
	public boolean hasItem(String myItemName) {
		if (myItemName != null) {
			for (Item i : super.getItems()) {
				if (i.toString().equals(myItemName))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Increase experience.
	 *
	 * @param increase the increase
	 */
	public void increaseExperience(int increase){
		myExperience+=increase;
		if(myExperience<MIN_EXPERIENCE)myExperience=0;
		if(myExperience>=MAX_EXPERIENCE){
			levelUp();
		}
	}

	/**
	 * Level up.
	 */
	private void levelUp() {
		getStatsMap().get(Constants.LEVEL).changeValue(1);
		getStatsMap().get(Constants.DAMAGE).changeValue(myRandom.nextInt(3));
		getStatsMap().get(Constants.SPEED).changeValue(myRandom.nextInt(3));
		getStatsMap().get(Constants.DEFENSE).changeValue(myRandom.nextInt(3));
		myExperience=0;
	}
	public int getExperience(){
		return myExperience;
	}
	
	public String getName(){
		return myName;
	}

}
