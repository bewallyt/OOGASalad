package engine.world;

import util.Constants;
import GameView.GameFrame;
import GameView.TitleManager;
import engine.dialogue.DialogueDisplayControl;
import engine.gridobject.person.Player;
import engine.state.TitleState;

public class TitleWorldLooper extends GameLooper {

	private TitleWorld myWorld;
	private TitleManager myTM;
	private boolean isGameLoaded;
	

	public TitleWorldLooper(TitleWorld currentWorld) {
		super(currentWorld);
		
		
		
		myWorld = (TitleWorld) getWorld();
		myTM = new TitleManager(myWorld.getPlayer());
		myWorld.getPlayer().setDialogueDisplayControl(
				new DialogueDisplayControl(myWorld));
		myWorld.getPlayer().setInteractionBox(myTM);
		myWorld.getPlayer()
				.setState(new TitleState(myWorld.getPlayer(), myTM));
		
		isGameLoaded = myTM.getIsGameLoaded();
		
	}

	@Override
	public World doLoop() {
		isGameLoaded =myTM.getIsGameLoaded();
		if(isGameLoaded){
			// Load new world
			
			GameFrame gm = new GameFrame();
			gm.initialize(myTM.getLoadFile());
			World firstWorld = gm.getInitialWorld();
			Player p = firstWorld.getPlayer();
			p.setPosition(gm.getXStart()*Constants.TILE_SIZE, gm.getYStart()*Constants.TILE_SIZE);
			return gm.getInitialWorld();
			
		}
		return null;
	}

}
