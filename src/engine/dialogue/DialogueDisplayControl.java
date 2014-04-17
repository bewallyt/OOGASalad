package engine.dialogue;

import engine.world.WalkAroundWorld;
import engine.world.World;

public class DialogueDisplayControl {
	
	private WalkAroundWorld myWorld;
	
	public DialogueDisplayControl(World w) {
		myWorld = (WalkAroundWorld) w;
	}
	
	public void setInteractionBox(InteractionBox b) {
		myWorld.setTextDisplayer(b);
	}
	
}
