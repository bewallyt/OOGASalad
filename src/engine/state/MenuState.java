package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.dialogue.TransparentDisplayer;
import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import util.Constants;

public class MenuState extends AbstractState {

	private MenuManager myMenu;
	private Player myPlayer;

	public MenuState(Player p, MenuManager m) {
		super();
		myPlayer = p;
		myMenu = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == Constants.UP) {
			myMenu.moveCursorUp();
		}
		if (e.getKeyCode() == Constants.DOWN) {
			myMenu.moveCursorDown();
		}
		if (e.getKeyCode() == Constants.A || e.getKeyCode() == Constants.ENTER) {
			myMenu.select();
		}
		if (e.getKeyCode() == Constants.SPACE || e.getKeyCode() == Constants.ESC) {
			myPlayer.setState(new WalkAroundState(myPlayer));
			myPlayer.setInteractionBox(new TransparentDisplayer());
		}
	}

}
