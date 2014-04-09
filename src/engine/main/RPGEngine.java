package engine.main;

import engine.collision.CollisionMatrix;
import engine.gridobject.Building;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.world.Canvas;
import engine.world.SurroundingChecker;
import engine.world.World;

/**
 * The Abstract Class RPGEngine. Extend to create a new RPG game! Main method must be added to subclasses
 */
public abstract class RPGEngine{

	/** The my canvas. */
	private Canvas myCanvas;

	/** The my current world. */
	private World myCurrentWorld;

	private Player myPlayer;

	private CollisionMatrix myCollisionMatrix;;

	/**
	 * Initialize game. Call initializeCanvas. Must be called by main method
	 */
	public abstract void initializeGame();

	/**
	 * Adds a grid object to the specified tile.
	 *
	 * @param gridObject the grid object
	 * @param xTile the x coordinate of the tile
	 * @param yTile the y coordinate of the tile
	 */
	public void addGridObject(GridObject gridObject, int xTile, int yTile){
		myCurrentWorld.setTileObject(gridObject, xTile, yTile);
		myCollisionMatrix = new CollisionMatrix(myCurrentWorld.getGridObjectList());
	}
	/**
	 * Run. Called by the main game loop. This method is called at every frame
	 */
	public abstract void run();

	/**
	 * Initialize canvas.
	 *
	 * @param width the width of the canvas
	 * @param height the height of the canvas
	 */
	public void initializeCanvas(int width, int height){
		Canvas canvas = new Canvas(width, height);
		myCanvas = canvas;
	}



	/**
	 * Adds a new world.
	 *
	 * @param world the world to be added
	 */
	public void addNewWorld(World world){
		myCanvas.setWorld(world);
		myCurrentWorld = world;
		for (int i = 0; i < world.getTileGridWidth(); i++) {
			for (int j = 0; j < world.getTileGridHeight(); j++) {
				world.getTileMatrix()[i][j].setBackgroundImage("grass.jpg");
			}
		}
		//		addPlayer(myPlayer.getAnimImages(), myPlayer.getSpeed(), myPlayer.getWidth(), myPlayer.getHeight());
		myCollisionMatrix=null;
	}

	public void addBuildingWorld(World world){
		myCanvas.setWorld(world);
		myCurrentWorld = world;
		for (int i = 0; i < world.getTileGridWidth(); i++) {
			for (int j = 0; j < world.getTileGridHeight(); j++) {
				world.getTileMatrix()[i][j].setBackgroundImage("pokecenterfloor.png");
			}
		}
//		for(int i=0; i<world.getTileGridWidth(); i++){
//			addGridObject(new Barrier("tree.png",1,2), i, 0);
//			addGridObject(new Barrier("tree.png",1,2), i, world.getTileGridHeight()-1-1);
//		}
		System.out.println("hi");
		addPlayer(myPlayer.getAnimImages(), myPlayer.getSpeed(), myPlayer.getNumTilesWidth(), myPlayer.getNumTilesHeight());
		addGridObject(getPlayer(), 3, 3);
		myCollisionMatrix=null;
	}

	/**
	 * Do game loop. Called every frame. Repaints the world, moves all GridObjects, and checks collisions. 
	 * Calls run()
	 *
	 * @param world the world
	 * @param cm the CollisionMatrix
	 * @throws InterruptedException the interrupted exception
	 */
	public void doGameLoop() throws InterruptedException {
		while (true) {
			myCanvas.repaint();
			checkCollisions(myCollisionMatrix);
			for (GridObject go : myCurrentWorld.getGridObjectList()) {
				go.move();
				if(go instanceof Building){
					if(((Building) go).isBuildingEntered()){
						System.out.println("new world");
						addBuildingWorld(((Building) go).getBuildingWorld());
					}
				}
				if(go instanceof Enemy){
					if(((Enemy) go).battleInitiated())
						System.out.println("battle!");
				}
			}
			run();
			Thread.sleep(10);
		}

	}

	public void addPlayer(String[] animImages, double speed, int numTilesWidth, int numTilesHeight){
		Player player = new Player(animImages, speed, numTilesWidth, numTilesHeight);
		player.setSurroundingsChecker(new SurroundingChecker(myCurrentWorld));
		myPlayer = player;
		//addGridObject(player, myCurrentWorld.get, numTilesHeight);
	}

	/**
	 * Check collisions. Called by doGameLoop
	 *
	 * @param world the world
	 * @param cm the cm
	 */
	private void checkCollisions(CollisionMatrix cm) {
		for (int i = 0; i < myCurrentWorld.getGridObjectList().size(); i++) {
			for (int j = 0; j < myCurrentWorld.getGridObjectList().size(); j++) {
				if (myCurrentWorld.getGridObjectList().get(i).getBounds().intersects(
						myCurrentWorld.getGridObjectList().get(j).getBounds())) {
					if(cm!=null)cm.getMatrix()[i][j].doCollision();
				}
			}
		}
	}

	public World getCurrentWorld(){
		return myCurrentWorld;
	}

	public Player getPlayer(){
		return myPlayer;
	}



}