package engine.state;

import java.awt.event.KeyEvent;

import GameView.GameSelectManager;
import engine.gridobject.person.Player;

public class TitleState extends AbstractState{
	
	private Player myPlayer;
	private GameSelectManager myGSM;
	
	public TitleState(Player p, GameSelectManager gsm) {
		super();
		myPlayer = p;
		myGSM = gsm;
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
		// TODO Auto-generated method stub
		
	}

}
