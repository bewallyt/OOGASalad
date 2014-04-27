package engine.state;

import java.awt.event.KeyEvent;
import java.io.File;

import GameView.TitleManager;
import engine.Control;
import engine.gridobject.person.Player;

public class GameSelectState extends AbstractState {

	private Player myPlayer;
	private TitleManager myTM;

	private StringBuffer buffer = new StringBuffer();
	private String displayString;
	private String loadFile;
	private boolean isLoaded = false;
	private boolean isInvalidFile = false;

	public GameSelectState(TitleManager tm, Player p) {
		super();
		myTM = tm;
		myPlayer = p;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(isInvalidFile){
			buffer.delete(0, buffer.length()-1);
			displayString = "";
			isInvalidFile = false;
			loadFile = null;
		}
		
		if (loadFile == null) {
			if (e.getKeyCode() == Control.ESC) {
				myPlayer.setState(new TitleState(myPlayer, myTM));
				myTM.toggleStartPressed();

			} else if (e.getKeyCode() != Control.ENTER
					&& e.getKeyCode() != Control.SHIFT
					&& e.getKeyCode() != Control.BACKSPACE) {
				buffer.append(e.getKeyChar());
				displayString = new String(buffer.toString());

			} else if (e.getKeyCode() == Control.ENTER && buffer.length() != 0) {
				loadFile = buffer.toString();
				load(loadFile);
				

			} else if (e.getKeyCode() == Control.BACKSPACE
					&& buffer.length() != 0) {
				buffer.deleteCharAt(buffer.length() - 1);
				displayString = new String(buffer.toString());

			}
		} 

	}

	public String getDisplayString() {
		if (isLoaded) {
			displayString = "Load Complete!";
		}
		return displayString;
	}

	private void load(String loadFile) {
		File f = new File("./src/SavedGames/" + loadFile);
		System.out.println("f.exists(): " + f.exists());
		//System.out.println("f.isDirectory: " + f.isDirectory());
		if (f.exists()) {
			isLoaded = true;
			myTM.setLoadFile(loadFile);
			myTM.toggleIsGameLoaded();
		}
		else{
			displayString = "Invalid Filename!";
			isInvalidFile = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
