package engine.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import Data.FileStorer;
import GameView.MapDataParser;
import authoring.MapData;
import authoring.PlayerData;
import authoring.WorldData;
import engine.Statistic;
import engine.battle.Weapon;
import engine.collision.BattleCollision;
import engine.collision.EnterCollision;
import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.item.Item;
import engine.world.ArenaWorld;
import engine.world.WalkAroundWorld;

public class Main extends RPGEngine {

//	private Player myPlayer;
	private WorldData myWorldData;
	// private DataManager myData;
	private FileStorer myData;
	private Player myPlayer;


	public static void main(String[] args) {
		Main engine = new Main();
		engine.initializeGame();
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	
	private void makeOutsideWorld2(){
		List<GridObject> gridObjectList = new ArrayList<GridObject>();

		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
				"PlayerLeft1.png", "PlayerLeft2.png"};
		Player player = new Player(anim, 2);


		gridObjectList.add(player);
	
		WalkAroundWorld outsideWorld = new WalkAroundWorld(1000, 1000, player, 40, gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world
		
		outsideWorld.setTileObject(gridObjectList.get(0), 1, 6);
		
		
	}

	@Override
	public void initializeGame() {
		setInit(true);
		initializeCanvas(500, 500);
		makeOutsideWorld2();
	}
}