package main;



import java.util.ArrayList;
import java.util.List;

import authoring.WorldData;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.item.KeyItem;
import engine.item.StatBuffer;
import engine.main.RPGEngine;
import engine.world.ArenaWorld;
import engine.world.TitleWorld;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class MainInGameTest extends RPGEngine {
	private WorldData myWorldData;



	public static void main(String[] args) {
		MainInGameTest engine = new MainInGameTest();
		engine.initializeGame();
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void makeOutsideWorld(){

		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
				"PlayerLeft1.png", "PlayerLeft2.png"};

		Player player = new Player(anim, "Player", 2, new String[1], new String[1]);

		TitleWorld titleScreen = new TitleWorld(1000, 1000, player);
		setWorld(titleScreen); // this is only called for the initial world
		
		titleScreen.setMusic("/music/pokeTest.wav");
}

	@Override
	public void initializeGame() {
		setInit(true);
		//initMusicTest();
		initializeCanvas(500, 500);
		makeOutsideWorld();
	}
}