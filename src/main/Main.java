package main;

import GameView.GameFrame;
import GameView.GameSelect;

public class Main {
	private Main(){
		
	}
	
	public static void main(String[] args) {
		GameSelect select = new GameSelect();
		GameFrame game = new GameFrame(select.getGameName());
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
