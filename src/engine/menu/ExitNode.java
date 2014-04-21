package engine.menu;

import engine.gridobject.person.Player;

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
	void changeWorld() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void changeState() {
		// TODO Auto-generated method stub
		
	}
}
