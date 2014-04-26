package engine.world;

import engine.dialogue.DialogueDisplayControl;

public class TitleWorldLooper extends GameLooper {
	
	TitleWorld myWorld;

	public TitleWorldLooper(TitleWorld currentWorld) {
		super(currentWorld);
		myWorld = (TitleWorld) getWorld();
		myWorld.getPlayer().setDialogueDisplayControl(new DialogueDisplayControl(currentWorld));
	}

	@Override
	public World doLoop() {
		// TODO Auto-generated method stub
		return null;
	}

}
