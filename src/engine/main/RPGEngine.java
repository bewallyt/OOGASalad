package engine.main;

import engine.GameLooper;
import engine.gridobject.person.Reflection;
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
	private GameLooper myGameLooper;
	


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
		System.out.println("world " + world);
		myCanvas.setWorld(world);
		myCurrentWorld = world;
		
	}

	/**
	 *  Will change the displayed world to the world sent in as a parameter
	 * @param world
	 * @param x X location of the player at spawn time (pixels)
	 * @param y Y location of the player at spawn time (pixels)
	 */
	public void changeWorld(World world, int x, int y) {
		myCurrentWorld.savePlayerPosition();
		setWorld(world);
		if(myCurrentWorld.getSavedPlayerPosition()!=null){
			setSavedPosition();
		}
		else{
			myCurrentWorld.getPlayer().setPosition(myCurrentWorld.getPlayer().getStartX(), 
					myCurrentWorld.getPlayer().getStartY());
		}
	}

	private void setSavedPosition() {
		myCurrentWorld.getPlayer().setFacing(myCurrentWorld.getSavedPlayerPosition()[2]);
		if(myCurrentWorld.getPlayer().getFacing()==2) myCurrentWorld.getPlayer().setPosition(myCurrentWorld.getSavedPlayerPosition()[0], myCurrentWorld.getSavedPlayerPosition()[1]+20);
		if(myCurrentWorld.getPlayer().getFacing()==0) myCurrentWorld.getPlayer().setPosition(myCurrentWorld.getSavedPlayerPosition()[0], myCurrentWorld.getSavedPlayerPosition()[1]-20);
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
			String classname = myCurrentWorld.getClass().getName();
			GameLooper cl = (GameLooper) Reflection.createInstance(classname+"Looper", myCurrentWorld);
			if(cl.doLoop()!=null){
				System.out.println("change");
				changeWorld(cl.doLoop(),50,100);
			}
			run();
			Thread.sleep(10);
		}
	}


	public World getCurrentWorld(){
		return myCurrentWorld;
	}

	public void paintConstantBackground(String background){
		((WalkAroundWorld) myCurrentWorld).paintFullBackround(background);
	}
}