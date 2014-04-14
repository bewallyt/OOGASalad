package engine.main;

import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.world.ArenaWorld;
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
	
	public Canvas retMyCanvas(){
		return myCanvas;
	}


	public void addNewArenaWorld(ArenaWorld world){
		
	}
	/**
	 * Adds a new world.
	 *
	 * @param world the world to be added
	 */
	public void addNewWorld(World world){
		myCanvas.setWorld(world);
		myCurrentWorld = world;
		
		//		addPlayer(myPlayer.getAnimImages(), myPlayer.getSpeed(), myPlayer.getWidth(), myPlayer.getHeight());
		myCollisionMatrix=null;
	}

	public void addBuildingWorld(World world){
		myCanvas.setWorld(world);
		myCurrentWorld = world;
		addPlayer(myPlayer.getAnimImages(), myPlayer.getSpeed(), myPlayer.getNumTilesWidth(), myPlayer.getNumTilesHeight());
//		myPlayer.setPosition(myCurrentWorld.getTileGridWidth()*myCurrentWorld.getTileSize()/2, 0);
		addGridObject(getPlayer(), world.getTileGridWidth()/2, world.getTileGridHeight()-3);
		myPlayer.setFacing(0);
		
		
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
				if(myPlayer.enterBuilding()!=null){
					System.out.println("new world");
					addBuildingWorld(myPlayer.enterBuilding().getBuildingWorld());
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
	
	public void paintConstantBackground(String background){
		myCurrentWorld.paintFullBackround(background);
	}



}