package engine.dialogue;

import engine.world.WalkAroundWorld;
import engine.world.World;

/**
 * This class allows for an NPC to have access to the worlds TextDisplayer without the NPC having access 
 * to anything else. It acts as a link, if you will, between the NPC and the World. Anytime a world is changed,
 * the NPC will get an updated DialogueDisplayControl object.
 *
 */

public class DialogueDisplayControl {
	
	private WalkAroundWorld myWorld;
	
	public DialogueDisplayControl(World w) {
		myWorld = (WalkAroundWorld) w;
	}
	
	public void setInteractionBox(InteractionBox b) {
		myWorld.setTextDisplayer(b);
	}
	
}
