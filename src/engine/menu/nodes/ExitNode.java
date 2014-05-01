package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;
import engine.state.ExitState;

public class ExitNode extends MenuNode{
	
	private Player myPlayer;
	private MenuManager myMenuManager;
	
	public ExitNode(Player p, MenuManager mm){
		myPlayer = p;
		myMenuManager = mm;
		
	}
	public void doAction(){
		changeState();
	}

	@Override
	public void changeWorld() {

		
	}

	@Override
	public void changeState() {
		myPlayer.setState(new ExitState());
	}
}
