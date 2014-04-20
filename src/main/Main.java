package main;

//import util.Music;
import java.net.URL;

import util.Music;
import GameView.GameFrame;
import GameView.GameSelect;

public class Main {
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		GameSelect select = new GameSelect();
		
		// could possibly surround with try/catch for cancellation instances
		game.initialize(select.getSelectedGame());

//		URL mainURL = Main.class.getResource("/music/pokeTest.wav");
//		Music music = new Music(mainURL);
//		music.start();

		
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
