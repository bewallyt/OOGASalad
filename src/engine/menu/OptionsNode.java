package engine.menu;

import GameView.GameFrame;
import GameView.GameSelect;

public class OptionsNode extends MenuNode {
	public void doAction() {
		GameFrame game = new GameFrame();
		GameSelect select = new GameSelect(game);
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
