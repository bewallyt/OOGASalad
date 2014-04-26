package engine.world;


import GameView.TitleManager;
import engine.dialogue.DialogueDisplayControl;
import engine.state.TitleState;

public class TitleWorldLooper extends GameLooper {
	
	TitleWorld myWorld;
	TitleManager myGSM;

	public TitleWorldLooper(TitleWorld currentWorld) {
		super(currentWorld);
		myWorld = (TitleWorld) getWorld();
		myGSM   = new TitleManager(myWorld.getPlayer());
		myWorld.getPlayer().setDialogueDisplayControl(new DialogueDisplayControl(myWorld));
		myWorld.getPlayer().setInteractionBox(myGSM);
		myWorld.getPlayer().setState(new TitleState(myWorld.getPlayer(), myGSM));
	}

	@Override
	public World doLoop() {
		// TODO Auto-generated method stub
		return null;
	}

}
