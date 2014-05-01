package engine.state;

import java.awt.event.KeyEvent;

import GameView.TitleManager;
import engine.gridobject.person.Player;

public class TitleState extends AbstractState {

	private Player myPlayer;
	private TitleManager myTM;

	public TitleState(Player p, TitleManager tm) {
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
		if (e.getKeyCode() < 150) {
			myTM.toggleStartPressed();
			myPlayer.setState(new GameSelectState(myTM, myPlayer));
		}

	}

}
