package engine.state;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import engine.Control;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.menu.MenuManager;
import engine.world.Canvas;
import engine.world.World;

public class WalkAroundState extends AbstractState {

	private Player myPlayer;
	private MenuManager myMenuManager;

	public WalkAroundState(Player p) {
		super();
		myPlayer = p;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Control.UP) {
			myPlayer.setDY(-myPlayer.getSpeed());
		}
		if (e.getKeyCode() == Control.DOWN) {
			myPlayer.setDY(myPlayer.getSpeed());
		}
		if (e.getKeyCode() == Control.RIGHT) {
			myPlayer.setDX(myPlayer.getSpeed());
		}
		if (e.getKeyCode() == Control.LEFT) {
			myPlayer.setDX(-myPlayer.getSpeed());
		}
		if (e.getKeyCode() == Control.A) {
			GridObject surrounding = myPlayer.getSurroundingChecker()
					.checkSurroundings(myPlayer).get(0);
			if (surrounding != null) {
				surrounding.doAction();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Control.UP || e.getKeyCode() == Control.DOWN)
			myPlayer.setDY(0);

		if (e.getKeyCode() == Control.RIGHT || e.getKeyCode() == Control.LEFT)
			myPlayer.setDX(0);
		if (e.getKeyCode() == Control.A)
			myPlayer.setAClick(false);
		// Added to test menu.
		if (e.getKeyCode() == Control.SPACE) {
			myPlayer.setState(new MenuState(myMenuManager));
			//myMenuManager.toggleMenu();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}