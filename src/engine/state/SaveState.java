package engine.state;

import java.awt.event.KeyEvent;

import engine.Control;
import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;

public class SaveState extends AbstractState {

	private Player myPlayer;
	private MenuManager myMenuManager;
	private String saveFile;
	private StringBuffer buffer = new StringBuffer();
	private String displayString;
	public final static String SAVE_FINISHED = "Save Complete!";
	private boolean isSavingState = false;

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
		if (saveFile == null) {
			if (e.getKeyCode() == Control.ESC) {
				myPlayer.setState(new MenuState(myPlayer, myMenuManager));
				myPlayer.setInteractionBox(myMenuManager);
				
			} else if (e.getKeyCode() != Control.ENTER
					&& e.getKeyCode() != Control.SHIFT) {
				buffer.append(e.getKeyChar());
				displayString = new String(buffer.toString());
				
			} else if (e.getKeyCode() == Control.ENTER) {
				saveFile = buffer.toString();
				save(saveFile);
			}
		} else {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
			myPlayer.setInteractionBox(myMenuManager);
		}

	}

	public String getDisplayString() {
		if (saveFile != null) {
			displayString = "Save Complete!";
		}
		return displayString;
	}

	public void save(String saveFile) {
		isSavingState = true;
	}
	
	public boolean isSavingState() {
		return isSavingState;
	}
	
	public void setSavingState(boolean status) {
		isSavingState = status;
	}
	
	public String getSaveFileName() {
		return saveFile;
	}

}
