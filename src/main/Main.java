package main;

import GameView.GameDummy;
import GameView.GameFrame;
import GameView.GameSelect;

public class Main {
	private Main(){
		
	}
	
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		GameSelect select = new GameSelect(game);
				
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
