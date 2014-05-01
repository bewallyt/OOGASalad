package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.menu.managers.SaveManager;
import engine.state.SaveState;

public class SaveNode extends MenuNode {
	
	private Player myPlayer;
	private MenuManager myMenuManager;
	private SaveManager mySaveManager;
	
	public SaveNode(Player p, MenuManager mm){
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
		mySaveManager = new SaveManager(myPlayer);
		myPlayer.setInteractionBox(mySaveManager);
		
		
	}

	@Override
	public void changeState() {
		myPlayer.setState(new SaveState(myPlayer, myMenuManager));
		
	}
	@Override
	public String getGame() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
