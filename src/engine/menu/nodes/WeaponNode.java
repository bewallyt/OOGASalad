package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.menu.managers.WeaponManager;
import engine.state.WeaponState;

public class WeaponNode extends MenuNode {
	
	private Player myPlayer;
	private MenuManager myMenuManager;
	private WeaponManager myWeaponManager;
	
	public WeaponNode(Player p, MenuManager mm){
		myPlayer = p;
		myMenuManager = mm;
		
	}

	@Override
	public void doAction() {
		changeState();
		changeWorld();
		
	}

	@Override
	public void changeWorld() {
		myWeaponManager = new WeaponManager(myPlayer);
		myPlayer.setInteractionBox(myWeaponManager);
		
	}

	@Override
	public void changeState() {
		myPlayer.setState(new WeaponState(myPlayer, myMenuManager));
		
	}

}
