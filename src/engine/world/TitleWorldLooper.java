package engine.world;


import GameView.GameSelectManager;
import engine.dialogue.DialogueDisplayControl;
import engine.state.TitleState;

public class TitleWorldLooper extends GameLooper {
	
	TitleWorld myWorld;
	GameSelectManager myGSM;

	public TitleWorldLooper(TitleWorld currentWorld) {
		super(currentWorld);
		myWorld = (TitleWorld) getWorld();
		myGSM   = new GameSelectManager();
		myWorld.getPlayer().setDialogueDisplayControl(new DialogueDisplayControl(myWorld));
		myWorld.getPlayer().setInteractionBox(myGSM);
		//myWorld.getPlayer().setState(new TitleState(myGSM));
	}

	@Override
	public World doLoop() {
		// TODO Auto-generated method stub
		return null;
	}

}
