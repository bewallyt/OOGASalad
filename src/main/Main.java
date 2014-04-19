package main;

//import util.Music;
import GameView.GameFrame;
import GameView.GameSelect;

public class Main {
	private Main(){
		
	}
	
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
		
//		URL main = Main.class.getResource("/music/pokeTest.wav");
//		
//		Music test = new Music(main);
//		test.start();
		
	}
}
