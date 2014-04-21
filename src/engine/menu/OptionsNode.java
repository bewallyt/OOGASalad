package engine.menu;

import GameView.GameFrame;
import GameView.GameSelect;

public class OptionsNode extends MenuNode {
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
