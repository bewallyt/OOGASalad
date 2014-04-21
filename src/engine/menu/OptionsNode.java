package engine.menu;

import engine.gridobject.person.Player;
import GameView.GameFrame;
import GameView.GameSelect;

public class OptionsNode extends MenuNode {
	
	private Player myPlayer;
	private MenuManager myMenuManager;
	
	public OptionsNode(Player p, MenuManager mm){
		myPlayer = p;
		myMenuManager = mm;
		
	}
	
	public void doAction() {
		GameFrame game = new GameFrame();
		GameSelect select = new GameSelect();
		game.initialize(select.getSelectedGame());
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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
