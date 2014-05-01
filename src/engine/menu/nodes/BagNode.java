package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.MenuInteractionMatrix;
import engine.menu.managers.BagManager;
import engine.menu.managers.MenuManager;
import engine.state.BagState;

public class BagNode extends MenuNode {

	private Player myPlayer;
	private MenuManager myMenuManager;
	private BagManager myBagManager;

	public BagNode(Player p, MenuManager mm) {
		myPlayer = p;
		myMenuManager = mm;

	}

	@Override
	public void doAction() {
		changeWorld();
		changeState();

	}

	@Override
	public void changeWorld() {
		myBagManager = new BagManager(myPlayer, myMenuManager,
				new MenuInteractionMatrix(1, myPlayer.getItems().size()));
		myPlayer.setInteractionBox(myBagManager);

	}

	@Override
	public void changeState() {
		myPlayer.setState(new BagState(myPlayer, myMenuManager,
				myBagManager));

	}

	@Override
	public String getGame() {
		// TODO Auto-generated method stub
		return null;
	}

}
