package main;

import GameView.GameFrame;

public class Main {
	private Main(){
		
	}
	
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
