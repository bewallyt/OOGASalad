package engine.main;

import java.util.List;

import engine.collision.CollisionMatrix;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.world.ArenaWorld;
import engine.world.Canvas;
import engine.world.SurroundingChecker;
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
	
	private World myPreviousWorld;


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
//	public void addGridObject(GridObject gridObject, int xTile, int yTile){
//		myCurrentWorld.setTileObject(gridObject, xTile, yTile);
//		myCollisionMatrix = new CollisionMatrix(myCurrentWorld.getGridObjectList());
//	}
	
	
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
	 * Sets the world passed in as the current world that will be painted.
	 *
	 * @param world the world to be set as current world
	 */
	public void setWorld(World world){
		myCanvas.setWorld(world);
		myPreviousWorld = myCurrentWorld;
		myCurrentWorld = (WalkAroundWorld) world;
		myCurrentWorld.getPlayer().setSurroundingsChecker(new SurroundingChecker(myCurrentWorld));
	}

	/**
	 *  Will change the displayed world to the world sent in as a parameter
	 * @param world
	 * @param x X location of the player at spawn time (pixels)
	 * @param y Y location of the player at spawn time (pixels)
	 */
	public void changeWorld(World world, int x, int y) {
		myCurrentWorld.getPlayer().setPosition(x, y);
		setWorld(world);
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
			checkCollisions(((WalkAroundWorld) myCurrentWorld).getCollisionMatrix());
			for (GridObject go : ((WalkAroundWorld) myCurrentWorld).getGridObjectList()) {
				go.move();
				Door d = myCurrentWorld.getPlayer().isDoorEntered();
				if(d!=null){
					System.out.println("new world");
					changeWorld(d.getBuildingWorld(), 100, 50);
					break;
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


	/**
	 * Check collisions. Called by doGameLoop
	 *
	 * @param world the world
	 * @param cm the cm
	 */
	private void checkCollisions(CollisionMatrix cm) {
		for (int i = 0; i < myCurrentWorld.getGridObjectList().size(); i++) {
			List<GridObject> myList = myCurrentWorld.getGridObjectList();
			for (int j = 0; j < myCurrentWorld.getGridObjectList().size(); j++) {
				if (myCurrentWorld.getGridObjectList().get(i).getBounds().intersects(
						myCurrentWorld.getGridObjectList().get(j).getBounds())) {
					if(cm!=null) {
						cm.getMatrix()[i][j].doCollision();
					}
				}
			}
		}
	}

	public World getCurrentWorld(){
		return myCurrentWorld;
	}

	public void paintConstantBackground(String background){
		myCurrentWorld.paintFullBackround(background);
	}



}