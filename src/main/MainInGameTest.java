package main;

import GameView.GameFrame;
import GameView.GameSelectTest;

public class MainInGameTest {

	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		GameSelectTest select = new GameSelectTest();

		// could possibly surround with try/catch for cancellation instances
		game.initialize(select.getSelectedGame());

		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
