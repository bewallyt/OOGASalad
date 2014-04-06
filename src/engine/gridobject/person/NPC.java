package engine.gridobject.person;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import engine.Dialogue;

public class NPC extends RuleFollower {
	protected List<String> myDialogue;
	private Movement myMovement;
	private Player myPlayer;
	
	/**
	 * Instantiates a new npc.
	 *
	 * @param image the image
	 * @param speed the speed
	 * @param numTilesWidth the num tiles width
	 * @param numTilesHeight the num tiles height
	 * @param movementType the movement type. 1=move back and forth, 2=follow player when player gets close, 3=don't move
	 * @param player the player
	 */
	public NPC(String image, double speed, int numTilesWidth, int numTilesHeight, int movementType, Player player) {
		super(image, speed, numTilesWidth, numTilesHeight);
		myDialogue=new ArrayList<String>();
		myPlayer=player;
		myMovement = (Movement) Reflection.createInstance("engine.gridobject.person.Movement" + movementType, this, player  );
	}

	public void addDialogue(String dialogue){
		myDialogue.add(dialogue);
	}
	public List<String> getDialogueList(){
		return myDialogue;
		
	}

	public void doNextDialogue(){
		System.out.println("hi");
		if(myDialogue.size()>0){
			new Dialogue("Dialogue.png", myDialogue.get(0));
			myDialogue.remove(0);
		}
	}
	
	public Player getPlayer(){
		return myPlayer;
	}
	
	@Override
	public void uniqueMove(){
		myMovement.move();
	}
	
	/**
	 * How far from player.
	 *
	 * @return how far the enemy is from the player
	 */
	public int howFarFromPlayer() {
		return getDistance(getX(), getY(), getPlayer().getX(), getPlayer().getY());
	}

	/**
	 * Gets the distance.
	 *
	 * @param x1 the x1
	 * @param y1 the y1
	 * @param x2 the x2
	 * @param y2 the y2
	 * @return the distance
	 */
	private int getDistance(int x1, int y1, int x2, int y2){
		return (int) Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1,2));
	}
	
	 
	 
	





}


