package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
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
		if (e.getKeyCode() == Control.UP) {
		}
		if (e.getKeyCode() == Control.DOWN) {
		}
		if (e.getKeyCode() == Control.A || e.getKeyCode() == Control.ENTER) {
		}
		if (e.getKeyCode() == Control.SPACE || e.getKeyCode() == Control.ESC) {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
			myPlayer.setInteractionBox(myMenuManager);
		}
		
	}

}
