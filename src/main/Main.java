package main;

import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		JFrame select = new JFrame();
		
		String[] games = {"mountainTest", "test1", "test2"};
		game.initialize((String) JOptionPane.showInputDialog(select, "Select a Game", "", JOptionPane.PLAIN_MESSAGE, null, games, ""));
//		GameSelect select = new GameSelect(game);
//		game.initialize("mountainTest");
				
		try {
			game.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
