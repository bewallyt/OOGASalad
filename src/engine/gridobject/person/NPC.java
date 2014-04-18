package engine.gridobject.person;

import java.util.ArrayList;
import java.util.List;

import engine.Dialogue;
import engine.dialogue.ConversationManager;
import engine.dialogue.DialogueDisplayControl;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.TransparentDisplayer;
import engine.state.DialogueState;

public class NPC extends Person {
	
	protected List<String> myDialogue;
	private Movement myMovement;
	private Player myPlayer;
	private NPCResponseNode myResponseNode;
	
	/**
	 * Instantiates a new npc.
	 *
	 * @param image the image
	 * @param speed the speed
	 * @param numTilesWidth the width in tiles
	 * @param numTilesHeight the height in tiles

	 * @param movementType the movement type. 1=move back and forth 2=follow player if it gets close 3=stand still
	 * @param player the player
	 */
	public NPC(String[] animImages, double speed, int numTilesWidth, int numTilesHeight, int movementType, Player player) {
		super(animImages, speed, numTilesWidth, numTilesHeight);
		myDialogue=new ArrayList<String>();
		myPlayer=player;
		myMovement = (Movement) Reflection.createInstance("engine.gridobject.person.Movement" + movementType,
															this, player  );
		
		// CHANGE THIS
		myResponseNode = null;
	}

	public void setResponseNode(NPCResponseNode n) {
		myResponseNode = n;
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

	
	@Override
	public void doAction(){
		doDialogue();
	}
	
	@Override
	public Dialogue doDialogue(){
		Dialogue d = null;		
		System.out.println("Conversation Mode");
		ConversationManager conversation = new ConversationManager(myPlayer, this, myResponseNode);
		myPlayer.setState(new DialogueState(conversation));
		super.setInteractionBox(conversation);
		return d;
	}
	





}


