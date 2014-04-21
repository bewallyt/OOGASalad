package engine.menu.nodes;

import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;

public class ExitNode extends MenuNode{
	
	private Player myPlayer;
	private MenuManager myMenuManager;
	
	public ExitNode(Player p, MenuManager mm){
		myPlayer = p;
		myMenuManager = mm;
		
	}
	public void doAction(){
		System.exit(0);
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
