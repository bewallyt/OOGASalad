package main;

import java.net.URL;

import util.Music;
import GameView.GameDummy;
import GameView.GameFrame;
import GameView.GameSelect;

public class Main {
	private Main(){
		
	}
	
	public static void main(String[] args) {

//		URL main = Main.class.getResource("/music/pokeTest.wav");
//		
//		Music test = new Music(main);
//		test.start();
		
		
		GameFrame game = new GameFrame();
		GameSelect select = new GameSelect(game);
				
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
