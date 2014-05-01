package engine.state;

import java.awt.event.KeyEvent;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;

public class NameState extends AbstractState {


	private Player myPlayer;
	private MenuManager myMenuManager;
	
	public NameState(Player p, MenuManager mm){
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
		if (e.getKeyCode() < 150) {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
			myPlayer.setInteractionBox(myMenuManager);
		}
		
	}

}
