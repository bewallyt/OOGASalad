package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.gridobject.person.Player;
import engine.menu.MenuManager;
import engine.menu.SaveManager;

public class SaveState extends AbstractState {

	private Player myPlayer;
	private MenuManager myMenuManager;

	public SaveState(Player p, MenuManager mm) {
		super();
		myPlayer = p;
		myMenuManager = mm;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Control.ESC) {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
		}

	}

}
