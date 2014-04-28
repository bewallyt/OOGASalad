package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.menu.managers.WeaponInfoManager;
import engine.menu.managers.WeaponManager;
import engine.state.WeaponInfoState;

public class WeaponInfoNode extends MenuNode {
	
	private WeaponInfoManager myWIM;
	private WeaponManager myWeaponManager;
	private Player myPlayer;
	private MenuManager myMenuManager;
	
	public WeaponInfoNode(Player p, WeaponManager wm, MenuManager mm){
		myPlayer = p;
		myWeaponManager = wm;
		myMenuManager = mm;
		
	}

	@Override
	public void doAction() {
		changeState();
		changeWorld();
	}

	@Override
	public void changeWorld() {
		myWIM = new WeaponInfoManager(myPlayer);
		myPlayer.setInteractionBox(myWIM);

	}

	@Override
	public void changeState() {
		myPlayer.setState(new WeaponInfoState(myPlayer, myWeaponManager, myMenuManager));

	}

}
