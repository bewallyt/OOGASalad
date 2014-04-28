package engine.state;

import java.awt.event.KeyEvent;

import util.Constants;
import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.menu.managers.WeaponManager;

public class WeaponState extends AbstractState {
	
	private MenuManager myMenuManager;
	private WeaponManager myWeaponManager;
	private Player myPlayer;
	
	public WeaponState(Player p, MenuManager mm, WeaponManager wm){
		myPlayer = p;
		myMenuManager = mm;
		myWeaponManager = wm;
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
			myWeaponManager.moveCursorUp();
		}
		if (e.getKeyCode() == Constants.DOWN) {
			myWeaponManager.moveCursorDown();
		}
		if (e.getKeyCode() == Constants.A || e.getKeyCode() == Constants.ENTER) {
			myWeaponManager.select();
		}
		if (e.getKeyCode() == Constants.SPACE || e.getKeyCode() == Constants.ESC) {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
			myPlayer.setInteractionBox(myMenuManager);
		}
		
	}

}
