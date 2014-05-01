package engine.state;

import java.awt.event.KeyEvent;

import GameView.GameChooserWorld;
import util.Constants;
import engine.gridobject.person.Player;

public class GameChooserState extends AbstractState {

	GameChooserWorld myGCW;
	Player myPlayer;

	public GameChooserState(Player p, GameChooserWorld gcw) {
		super();
		myGCW = gcw;
		myPlayer = p;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Constants.UP) {
			myGCW.moveUp();
		}
		if (e.getKeyCode() == Constants.DOWN) {
			myGCW.moveDown();
		}
		if (e.getKeyCode() == Constants.RIGHT) {
			myGCW.moveRight();

		}
		if (e.getKeyCode() == Constants.LEFT) {
			myGCW.moveLeft();
		}
		if (e.getKeyCode() == Constants.ENTER
				|| e.getKeyCode() == Constants.SPACE) {
			myGCW.setGameString();

		}

	}
}