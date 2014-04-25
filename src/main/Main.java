package main;

import GameView.GameFrame;
import GameView.GameSelect;

public class Main {
	
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		GameSelect select = new GameSelect();

		// try-catch?
		game.initialize(select.getSelectedGame());
		
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
