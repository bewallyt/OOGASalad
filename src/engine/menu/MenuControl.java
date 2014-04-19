package engine.menu;

import engine.dialogue.InteractionBox;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class MenuControl {
	
	private WalkAroundWorld myWorld;
	
	public MenuControl(World w) {
		myWorld = (WalkAroundWorld) w;
	}
	
	public void setInteractionBox(InteractionBox b) {
		myWorld.setMenuDisplayer(b);
	}

}
