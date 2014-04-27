package engine.state;

import java.awt.event.KeyEvent;

import GameView.GameFrame;
import GameView.TitleManager;
import engine.Control;
import engine.gridobject.person.Player;

public class GameSelectState extends AbstractState {

	private Player myPlayer;
	private TitleManager myTM;

	private StringBuffer buffer = new StringBuffer();
	private String displayString;
	private String loadFile;
	public final static String LOAD_FINISHED = "Load Complete!";

	public GameSelectState(TitleManager tm, Player p) {
		super();
		myTM = tm;
		myPlayer = p;
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
		if (loadFile == null) {
			if (e.getKeyCode() == Control.ESC) {
				myPlayer.setState(new TitleState(myPlayer, myTM));
				myTM.toggleStartPressed();

			} else if (e.getKeyCode() != Control.ENTER
					&& e.getKeyCode() != Control.SHIFT) {
				buffer.append(e.getKeyChar());
				displayString = new String(buffer.toString());

			} else if (e.getKeyCode() == Control.ENTER) {
				loadFile = buffer.toString();
				load(loadFile);
				myTM.toggleIsGameLoaded();

			}
		} else {
			myPlayer.setState(new TitleState(myPlayer, myTM));
		}

	}
	
	public String getDisplayString() {
		if (loadFile != null) {
			displayString = "Load Complete!";
		}
		return displayString;
	}

	public void load(String loadFile) {
//		DataManager dm = new DataManager();
//		WorldData loadedData = dm.loadWorldDataFromFile(loadFile + ".json");
		GameFrame gm = new GameFrame();
		gm.initialize(loadFile);
	}

}
