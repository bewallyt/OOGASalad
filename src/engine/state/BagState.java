package engine.state;

import java.awt.event.KeyEvent;

import util.Constants;
import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.menu.managers.BagManager;

public class BagState extends AbstractState {

	private MenuManager myMenuManager;
	private BagManager myBagManager;
	private Player myPlayer;
	
	public BagState(Player p, MenuManager mm, BagManager wm){
		myPlayer = p;
		myMenuManager = mm;
		myBagManager = wm;
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

		if (e.getKeyCode() == Constants.UP) {
			myBagManager.moveCursorUp();
		}
		if (e.getKeyCode() == Constants.DOWN) {
			myBagManager.moveCursorDown();
		}
		if (e.getKeyCode() == Constants.SPACE || e.getKeyCode() == Constants.ESC) {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
			myPlayer.setInteractionBox(myMenuManager);
		}
		
	}

}
