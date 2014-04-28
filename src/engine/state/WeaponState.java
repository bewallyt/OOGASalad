package engine.state;

import java.awt.event.KeyEvent;

import util.Constants;
import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;

public class WeaponState extends AbstractState {
	
	private MenuManager myMenuManager;
	private Player myPlayer;
	
	public WeaponState(Player p, MenuManager mm){
		myPlayer = p;
		myMenuManager = mm;
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
		}
		if (e.getKeyCode() == Constants.DOWN) {
		}
		if (e.getKeyCode() == Constants.A || e.getKeyCode() == Constants.ENTER) {
		}
		if (e.getKeyCode() == Constants.SPACE || e.getKeyCode() == Constants.ESC) {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
			myPlayer.setInteractionBox(myMenuManager);
		}
		
	}

}
