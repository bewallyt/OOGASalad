package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;

public class BagNode extends MenuNode {

	private Player myPlayer;
	private MenuManager myMenuManager;

	public BagNode(Player p, MenuManager mm) {
		myPlayer = p;
		myMenuManager = mm;

	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeWorld() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub

	}

}
