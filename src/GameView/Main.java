package GameView;

import util.Constants;
import engine.main.RPGEngine;

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

	@Override
	public void initializeGame() {
		setInit(true);
		initializeCanvas(Constants.CANVASWIDTH, Constants.CANVASHEIGHT);
		makeTitleScreen();
	}
}