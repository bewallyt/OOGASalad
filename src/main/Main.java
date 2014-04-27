package main;

import engine.gridobject.person.Player;
import engine.main.RPGEngine;
import engine.world.TitleWorld;

public class Main extends RPGEngine {

	public static void main(String[] args) {
		Main engine = new Main();
		engine.initializeGame();
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	public void startEngine() {
//		Main engine = new Main();
//		engine.initializeGame();
//		try {
//			engine.doGameLoop();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void makeTitleScreen() {

		Player playerDummy = new Player();
		TitleWorld titleScreen = new TitleWorld(1000, 1000, playerDummy);

		titleScreen.setBackground("PokemonBackground.png");
		setWorld(titleScreen);

		titleScreen.setMusic("/music/PokemonIntro.wav");
	}

	@Override
	public void initializeGame() {
		setInit(true);
		initializeCanvas(500, 500);
		makeTitleScreen();
	}
}