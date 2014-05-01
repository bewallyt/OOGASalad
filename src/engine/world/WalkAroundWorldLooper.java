package engine.world;

import util.Constants;

import Data.WorldDataManager;
import GameView.GameChooserWorld;
import engine.collision.CollisionMatrix;
import engine.dialogue.AbstractManager;
import engine.dialogue.ConversationManager;
import engine.dialogue.DialogueDisplayControl;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.NotificationManager;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.state.DialogueState;
import engine.state.ExitState;
import engine.state.GameChooserState;
import engine.state.SaveState;
import engine.state.WalkAroundState;

public class WalkAroundWorldLooper extends GameLooper {

	WalkAroundWorld myWorld;

	public WalkAroundWorldLooper(WalkAroundWorld currentWorld) {
		super(currentWorld);
		myWorld = (WalkAroundWorld) getWorld();
		getWorld().getPlayer().setSurroundingsChecker(
				new SurroundingChecker(myWorld));
		getWorld().getPlayer().setState(
				new WalkAroundState(getWorld().getPlayer()));
		setDialogueDisplayControl();
		setMenuControl();
	}

	private void setDialogueDisplayControl() {
		for (GridObject go : (myWorld.getGridObjectList())) {
			// if (go instanceof Person) {
			go.setDialogueDisplayControl(new DialogueDisplayControl(myWorld));
			// }
		}
	}

	private void setMenuControl() {

	}

	@Override
	public World doLoop() {
		if (myWorld.getPlayer().getState() instanceof WalkAroundState) {
			checkCollisions(myWorld.getCollisionMatrix());
			for (GridObject go : (myWorld.getGridObjectList())) {
				go.move();
				Door d = myWorld.getPlayer().isDoorEntered();

				if(d!=null){
					if (d.getWorld().getSavedPlayerPosition() == null) {
						d.getWorld().setPlayerPositionFromDoor(d.getToX()*40, d.getToY()*40);
					}
					return d.getWorld();
				}
				if (go instanceof Enemy) {
					if (((Enemy) go).isBattle()
							&& myWorld.getPlayer().getState() instanceof WalkAroundState) {
						if (!((Enemy) go).getWasBattled()) {
							return ((Enemy) go).getWorld();
						}
					}
				}
			}

		} else if (myWorld.getPlayer().getState() instanceof SaveState) {
			SaveState saveState = (SaveState) myWorld.getPlayer().getState();
			if (saveState.isSavingState()) {
				saveState.setSavingState(false);

				saveWorld(saveState.getSaveFileName());
			}
		} else if (myWorld.getPlayer().getState() instanceof ExitState) {
			GameChooserWorld titleScreen = new GameChooserWorld(1000, 1000,
					getWorld().getPlayer(), "TitleScreen.png");
			titleScreen.setMusic("/music/PokemonIntro.wav");
			return titleScreen;
		}
		return null;
	}

	private void checkCollisions(CollisionMatrix cm) {
		for (int i = 0; i < ((WalkAroundWorld) getWorld()).getGridObjectList()
				.size(); i++) {
			for (int j = 0; j < ((WalkAroundWorld) getWorld())
					.getGridObjectList().size(); j++) {
				if (myWorld
						.getGridObjectList()
						.get(i)
						.getBounds()
						.intersects(
								myWorld.getGridObjectList().get(j).getBounds())) {
					if (cm != null) {
						cm.getMatrix()[i][j].doCollision();
					}
				}
			}
		}
	}

	private void saveWorld(String filename) {
		WorldDataManager wdManager = myWorld.getWorldDataManager();
		wdManager.saveWorld(myWorld, filename);
	}
}
