package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.menu.managers.WeaponInfoManager;
import engine.menu.managers.WeaponManager;

public class WeaponMenuNode extends MenuNode {
	
	private WeaponInfoManager myWIM;
	private WeaponManager myWeaponManager;
	private Player myPlayer;
	
	public WeaponMenuNode(Player p, WeaponManager wm){
		myPlayer = p;
		myWeaponManager = wm;
		
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
		// TODO Auto-generated method stub

	}

}
