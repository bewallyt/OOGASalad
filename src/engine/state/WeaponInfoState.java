package engine.state;

import java.awt.event.KeyEvent;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.menu.managers.WeaponManager;

public class WeaponInfoState extends AbstractState {
	
	private Player myPlayer;
	private MenuManager myMenuManager;
	private WeaponManager myWeaponManager;
	
	public WeaponInfoState(Player p, WeaponManager wm, MenuManager mm){
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
		if (e.getKeyCode() < 150) {
			myPlayer.setState(new WeaponState(myPlayer, myMenuManager, myWeaponManager));
			myPlayer.setInteractionBox(myWeaponManager);
		}
		
	}

}
