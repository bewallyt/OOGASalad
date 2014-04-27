package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
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
		if (e.getKeyCode() == Control.UP) {
			myWeaponManager.moveCursorUp();
		}
		if (e.getKeyCode() == Control.DOWN) {
			myWeaponManager.moveCursorDown();
		}
		if (e.getKeyCode() == Control.A || e.getKeyCode() == Control.ENTER) {
			myWeaponManager.select();
		}
		if (e.getKeyCode() == Control.SPACE || e.getKeyCode() == Control.ESC) {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
			myPlayer.setInteractionBox(myMenuManager);
		}
		
	}

}
