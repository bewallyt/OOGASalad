package engine.state;

import java.awt.event.KeyEvent;

import util.Constants;
import engine.gridobject.Barrier;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;

public class WalkAroundState extends AbstractState {

	private Player myPlayer;

	public WalkAroundState(Player p) {
		super();
		myPlayer = p;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Constants.UP) {
			myPlayer.setDY(-myPlayer.getSpeed());
		}
		if (e.getKeyCode() == Constants.DOWN) {
			myPlayer.setDY(myPlayer.getSpeed());
		}
		if (e.getKeyCode() == Constants.RIGHT) {
			myPlayer.setDX(myPlayer.getSpeed());
		}
		if (e.getKeyCode() == Constants.LEFT) {
			myPlayer.setDX(-myPlayer.getSpeed());
		}
		if (e.getKeyCode() == Constants.A) {
			GridObject surrounding = myPlayer.getSurroundingChecker()
					.checkSurroundings(myPlayer).get(0);
			if (surrounding != null) {
				surrounding.doAction();
				if(surrounding.getPickupable()!=null){
					(surrounding.getPickupable()).pickUp(myPlayer);
					((Barrier) surrounding).displayAlertBox(myPlayer, surrounding.getPickupable());
					surrounding.setPickupable(null);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Constants.UP || e.getKeyCode() == Constants.DOWN)
			myPlayer.setDY(0);
		if (e.getKeyCode() == Constants.RIGHT || e.getKeyCode() == Constants.LEFT)
			myPlayer.setDX(0);
		if (e.getKeyCode() == Constants.A)
			myPlayer.setAClick(false);
		if (e.getKeyCode() == Constants.SPACE) {
			MenuManager mm = new MenuManager(myPlayer, new String[] {"Weapon", "Bag", "Name", "Save", "Exit"});
			mm.createMenuNodes();
			myPlayer.setState(new MenuState(myPlayer, mm));
			myPlayer.setInteractionBox(mm);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}