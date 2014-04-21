package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.state.MenuState;
import engine.state.SaveState;
import Data.DataManager;

public class SaveNode extends MenuNode {
	
	private Player myPlayer;
	private MenuManager myMenuManager;
	
	public SaveNode(Player p, MenuManager mm){
		myPlayer = p;
		myMenuManager = mm;
		
	}
	@Override
	public void doAction() {
		changeState();
	}

	@Override
	public void changeWorld() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeState() {
		myPlayer.setState(new SaveState(myPlayer, myMenuManager));
		
	}
	

	public void save(String saveFile){
		DataManager dm = new DataManager();
		dm.saveWorldDataToFile(saveFile);
	}

}
