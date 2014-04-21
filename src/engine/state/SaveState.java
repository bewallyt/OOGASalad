package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;

public class SaveState extends AbstractState{

	private Player myPlayer;
	private MenuManager myMenuManager;
	private String saveFile;
	private StringBuffer buffer = new StringBuffer();
	private String displayString;

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
			myPlayer.setInteractionBox(myMenuManager);
		}
		else if (e.getKeyCode() != Control.ENTER && e.getKeyCode() != Control.SHIFT) {
			buffer.append(e.getKeyChar());
			displayString = new String(buffer.toString());
		}
		else if(e.getKeyCode() == Control.ENTER){
			saveFile = buffer.toString();
			System.out.println(saveFile);
		}

	}

	public String getDisplayString() {
		return displayString;
	}


}
