package main;

import GameView.GameFrame;
import GameView.GameSelect;

public class Main {
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		GameSelect select = new GameSelect();
		
		// could possibly surround with try/catch for cancellation instances
		game.initialize(select.getSelectedGame());
		
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
