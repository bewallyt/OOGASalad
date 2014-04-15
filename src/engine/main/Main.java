package engine.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import authoring.WorldData;
import Data.FileStorer;
import GameView.GameSelect;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;

public class Main extends RPGEngine {

//	private Player myPlayer;
	private NPC myNPC;
	private FileStorer myData;
	private WorldData myWorldData;


	public static void main(String[] args) {
		//GameSelect select = new GameSelect();
//		Main engine = new Main(select.getGameName());
		Main engine = new Main("hi");
		
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Main(String fileName) {
		myData = new FileStorer();
		
		try {
			myWorldData = myData.getWorldData(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initializeGame();
		
	}

	private void makeOutsideWorld(){
		List<GridObject> gridObjectList = new ArrayList<GridObject>();
		List<Integer> xList = new ArrayList<Integer>();
		List<Integer> yList = new ArrayList<Integer>();
		
		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", 
				"PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
				"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", 
				"PlayerLeft1.png", "PlayerLeft2.png"};
		Player player = new Player(anim, 2, 1, 1);
		
		gridObjectList.add(player);
		xList.add(6);
		yList.add(6);

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				gridObjectList.add(new Barrier(myWorldData.getMap("defaultworldkey").getTileData(i, j).getImageName() + ".png", 1, 1));
				xList.add(i);
				yList.add(j);
			}
		}

		gridObjectList.add(new Barrier(myWorldData.getMap("defaultworldkey").getTileData(1, 1).getImageName()+".png", 1, 1));
//		gridObjectList.add(new Barrier("pokecenter.png",4, 4));		
		
		WalkAroundWorld outsideWorld = new WalkAroundWorld(40, 1000, 1000, player, gridObjectList);
		setWorld(outsideWorld); // this is only called for the initial world
		
		for(int i = 0; i < 25; i++) {
			System.out.println(gridObjectList.get(i).getImageFile() + " " + i);
			outsideWorld.setTileObject(gridObjectList.get(i), xList.get(i), yList.get(i));
		}
//		outsideWorld.setTileObject(gridObjectList.get(0), 4, 4);
//		outsideWorld.setTileObject(gridObjectList.get(1), 1, 1);
		

		
		
		
//		outsideWorld.paintFullBackround("grassSmall.png");

	}

	@Override
	public void initializeGame() {
		initializeCanvas(400, 400);
		makeOutsideWorld();

	}

	@Override
	public void run() {

	}



}