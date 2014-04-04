package GameView;

import engine.Dialogue;
import engine.collision.CollisionMatrix;
import engine.gridobject.Barrier;
import engine.gridobject.person.BackAndForthMover;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.WalkAroundWorld;
import engine.world.World;
import engine.main.RPGEngine;;

public class GameFrame extends RPGEngine {

	private int spriteWidth = 1;
	private int spriteHeight = 1;
	
	Player myPlayer;
	BackAndForthMover myEnemy;
	public void addObjects(World world){
		Player player = myPlayer = initPlayer();
		addGridObject(player, 3, 3);
		
		
		
		BackAndForthMover bafm = myEnemy= new BackAndForthMover("rival.png",1,1,1, 350, 550, 0, 0, player);
		addGridObject(bafm,10,10);
		bafm.addDialogue("Hey Bitch. Fight Me!");
		addGridObject(new Barrier("pokecenter.png",4, 4), 4, 3);

		for(int i=0; i<world.getTileSize()[0]; i++){
			addGridObject(new Barrier("tree.png",1,2), i, 0);
			addGridObject(new Barrier("tree.png",1,2), i, world.getTileSize()[1]-1-1);
		}
		for(int i=0; i<world.getTileSize()[1]; i++){
			addGridObject(new Barrier("tree.png",1,2), 0, i);
			addGridObject(new Barrier("tree.png",1,2), world.getTileSize()[0]-1,i );
		}

	}

	@Override
	public void initializeGame() {
		initializeCanvas(800, 800);
		addNewWalkAroundWorld(40,"grass.jpg");
		addObjects(getCurrentWorld());
	}

	@Override
	public void run() {
		if(myPlayer.getAClick())
			myEnemy.doNextDialogue();
	}
	
	private Player initPlayer() {
		String[] animImages = new String[12];
		animImages[0] = "fs.png";
		animImages[3] = "ls.png";
		animImages[6] = "bs.png";
		animImages[9] = "rs.png";
		Player player = new Player("player.png",2,spriteWidth, spriteHeight);
		player.getAnimImages(animImages);
		return player;
	}
	
//	private BackAndForthMover initEnemies() {
//		BackAndForthMover bafm = myEnemy= new BackAndForthMover("rival.png",1,1,1, 350, 550, 0, 0, player);		
//	}

	public static void main(String[] args) {
		GameFrame engine = new GameFrame();
		engine.initializeGame();
		try {
			engine.doGameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//		Canvas canvas = new Canvas (800,800);
		//		WalkAroundWorld waWorld = new WalkAroundWorld(40, canvas.getWidth(), canvas.getHeight());
		//		canvas.setWorld(waWorld);
		//		engine.addObjects(waWorld);
		//		CollisionMatrix cm = new CollisionMatrix(waWorld.getGridObjectList());
		//		engine.doGameLoop(waWorld, cm);
	}

}