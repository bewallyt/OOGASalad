package engine.main;

import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.NPC;
import engine.world.WalkAroundWorld;

public class Main extends RPGEngine {

//	private Player myPlayer;
	private NPC myNPC;


	public static void main(String[] args) {
		Main engine = new Main();
		engine.initializeGame();
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void makeOutsideWorld(){
		WalkAroundWorld outsideWorld = new WalkAroundWorld(40, 1000, 1000);

		addNewWorld(outsideWorld);
		outsideWorld.paintFullBackround("grassSmall.png");



		String[] anim = new String[]{"PlayerUp0.png", "PlayerUp1.png", "PlayerUp2.png", "PlayerRight0.png", "PlayerRight1.png", "PlayerRight2.png",
		"PlayerDown0.png", "PlayerDown1.png", "PlayerDown2.png", "PlayerLeft0.png", "PlayerLeft1.png", "PlayerLeft2.png"};
		addPlayer(anim,2,1, 1);


		addGridObject(getPlayer(), 3, 3);
		Enemy bafm = new Enemy(new String[] {"rival.png","rival.png","rival.png","rival.png"},1,1,1, 3, getPlayer());
		bafm.battleOnSight();
		addGridObject(bafm,10,10);
		bafm.addDialogue("Hey fight me");
		Barrier pokeCenter = new Barrier("pokecenter.png",4, 4);
		addGridObject(pokeCenter, 4, 3);
		WalkAroundWorld buildingWorld = new WalkAroundWorld(40, 1000, 1000);
		buildingWorld.paintFullBackround("pokecenterfloor.png");
		Door door = new Door("cabinets.jpg", 1, 1);
		door.setBuildingWorld(buildingWorld);
		addGridObject(door, 6, 6);

		for(int i=0; i<outsideWorld.getTileGridWidth(); i++){
			addGridObject(new Barrier("tree.png",1,2), i, 0);
			addGridObject(new Barrier("tree.png",1,2), i, outsideWorld.getTileGridHeight()-1-1);
		}
		for(int i=0; i<outsideWorld.getTileGridHeight(); i++){
			addGridObject(new Barrier("tree.png",1,2), 0, i);
			addGridObject(new Barrier("tree.png",1,2), outsideWorld.getTileGridWidth()-1,i );
		}
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