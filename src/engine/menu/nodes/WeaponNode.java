package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.MenuInteractionMatrix;
import engine.menu.managers.MenuManager;
import engine.menu.managers.WeaponManager;
import engine.state.WeaponState;

public class WeaponNode extends MenuNode {

	private Player myPlayer;
	private MenuManager myMenuManager;
	private WeaponManager myWeaponManager;

	public WeaponNode(Player p, MenuManager mm) {
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
		myWeaponManager = new WeaponManager(myPlayer, myMenuManager,
				new MenuInteractionMatrix(1, myPlayer.getWeaponList().size()));
		myPlayer.setInteractionBox(myWeaponManager);
		myWeaponManager.createWeaponInfoNodes();

	}

	@Override
	public void changeState() {
		myPlayer.setState(new WeaponState(myPlayer, myMenuManager,
				myWeaponManager));

	}

	@Override
	public String getGame() {
		// TODO Auto-generated method stub
		return null;
	}

}
