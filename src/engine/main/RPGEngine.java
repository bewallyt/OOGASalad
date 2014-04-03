package engine.main;

import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.world.ArenaWorld;
import engine.world.Canvas;
import engine.world.WalkAroundWorld;
import engine.world.World;

/**
 * The Abstract Class RPGEngine. Extend to create a new RPG game! Main method must be added to subclasses
 */
public abstract class RPGEngine{
	
	/** The my canvas. */
	private Canvas myCanvas;
	
	/** The my current world. */
	private World myCurrentWorld;
	
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
	 * Adds a new walkaroundworld.
	 *
	 * @param tileSize the tile size
	 */
	public void addNewWalkAroundWorld(int tileSize){
		World world = new ArenaWorld(tileSize);
		world.setDimensions(myCanvas.getWidth(), myCanvas.getHeight());
		myCanvas.setWorld(world);
		myCurrentWorld = world;
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
			myCurrentWorld.repaint();
			checkCollisions(myCollisionMatrix);
			for (GridObject go : myCurrentWorld.getGridObjectList()) {
				go.move();
			}
			run();
			Thread.sleep(10);
		}
		
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
					cm.getMatrix()[i][j].doCollision();
				}
			}
		}
	}
	
	public World getCurrentWorld(){
		return myCurrentWorld;
	}
	
}
