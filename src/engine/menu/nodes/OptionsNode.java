package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.menu.managers.OptionsManager;
import engine.state.LoadState;


public class OptionsNode extends MenuNode {
	
	private Player myPlayer;
	private MenuManager myMenuManager;
	private OptionsManager myOptionsManager;
	
	public OptionsNode(Player p, MenuManager mm){
		myPlayer = p;
		myMenuManager = mm;
		
	}
	
	public void doAction() {
		changeWorld();
		changeState();
	}

	@Override
	public void changeWorld() {
		myOptionsManager = new OptionsManager(myPlayer);
		myPlayer.setInteractionBox(myOptionsManager);
		
	}

	@Override
	public void changeState() {
		myPlayer.setState(new LoadState(myPlayer, myMenuManager));
		
	}
}
