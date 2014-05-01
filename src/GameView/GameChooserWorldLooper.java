package GameView;

import util.Constants;
import GameView.GameChooserWorld;
import engine.dialogue.DialogueDisplayControl;
import engine.state.GameChooserState;
import engine.world.GameLooper;
import engine.world.TitleWorld;
import engine.world.World;

public class GameChooserWorldLooper extends GameLooper {

	private GameChooserWorld myWorld;
	private String myGame;

	public GameChooserWorldLooper(GameChooserWorld currentWorld) {
		super(currentWorld);

		myWorld = (GameChooserWorld) getWorld();

		myWorld.getPlayer().setDialogueDisplayControl(
				new DialogueDisplayControl(myWorld));
		myWorld.getPlayer().setInteractionBox(myWorld);
		myWorld.getPlayer().setState(
				new GameChooserState(myWorld.getPlayer(), myWorld));
		myWorld.createTitleNodes();

		myGame = myWorld.getGameString();

	}

	@Override
	public World doLoop() {

		myGame = myWorld.getGameString();

		if (myGame != null) {
			TitleWorld title = new TitleWorld(500, 500, myWorld.getPlayer(),
					myGame + "Title.png");
			if (myGame == "Pokemon") {
				title.setMusic(Constants.POKEMON_MUSIC);
			} else if (myGame == "FinalFantasy") {
				title.setMusic(Constants.FF_MUSIC);
			} else if (myGame == "Zelda") {
				title.setMusic(Constants.ZELDA_MUSIC);
			} else {
				title.setMusic(Constants.OTHER_MUSIC);
			}
			return title;
		}
		return null;
	}
}
