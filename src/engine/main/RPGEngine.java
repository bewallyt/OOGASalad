package engine.main;

import GameView.GameChooserWorld;
import util.Constants;
import engine.gridobject.person.Player;
import engine.gridobject.person.Reflection;
import engine.world.ArenaWorld;
import engine.world.ArenaWorldLooper;
import engine.world.Canvas;
import engine.world.GameLooper;
import engine.world.TitleWorld;
import engine.world.WalkAroundWorld;
import engine.world.World;

/**
 * The Abstract Class RPGEngine. Extend to create a new RPG game! Main method
 * must be added to subclasses
 */
public abstract class RPGEngine {
	private Canvas myCanvas;
	private World myCurrentWorld;
	private GameLooper myGameLooper;
	private World myPreviousWorld;
	private Boolean isInitialized = false;

	/**
	 * Initialize game. Call initializeCanvas. Must be called by main method
	 */
	public abstract void initializeGame();

	/**
	 * Initialize canvas.
	 * 
	 * @param width
	 *            the width of the canvas
	 * @param height
	 *            the height of the canvas
	 */
	public void initializeCanvas(int width, int height) {
		Canvas canvas = new Canvas(width, height);
		myCanvas = canvas;
	}

	public void makeTitleScreen() {
		GameChooserWorld titleScreen = new GameChooserWorld(
				Constants.TITLEWIDTH, Constants.TITLEHEIGHT, new Player(),
				Constants.GAME_CHOOSER_BACKGROUND);
		titleScreen.createTitleNodes();
		setWorld(titleScreen);

		titleScreen.setMusic(Constants.TITLE_MUSIC);
	}

	/**
	 * Sets the world passed in as the current world that will be painted.
	 * 
	 * @param world
	 *            the world to be set as current world
	 */
	public void setWorld(World world) {
		myCanvas.setWorld(world);
		myCurrentWorld = world;
		String classname = myCurrentWorld.getClass().getName();
		if (classname.equals("engine.world.ArenaWorld"))
			myGameLooper = new ArenaWorldLooper(myCurrentWorld);
		else
			myGameLooper = (GameLooper) Reflection.createInstance(classname
					+ "Looper", myCurrentWorld);

	}

	/**
	 * Will change the displayed world to the world sent in as a parameter. If
	 * the player has previously been in the world, will load the saved
	 * position. If not, supply x and y
	 * 
	 * @param world
	 * @param x
	 *            X location of the player at spawn time (pixels)
	 * @param y
	 *            Y location of the player at spawn time (pixels)
	 */
	public void changeWorld(World world) {
		myCurrentWorld.savePlayerPosition();
		if (myCurrentWorld.getMusic() != null)
			myCurrentWorld.getMusic().stop();
		myPreviousWorld = myCurrentWorld;
		setWorld(world);
		if (myCurrentWorld.getSavedPlayerPosition() != null) {
			setSavedPosition();
		} else {
			myCurrentWorld.getPlayer().setPosition(
					myCurrentWorld.getPlayer().getStartX(),
					myCurrentWorld.getPlayer().getStartY());
		}
		if (myCurrentWorld.getMusic() != null)
			myCurrentWorld.getMusic().start();
	}

	/**
	 * Sets the Player's position and facing based on the saved position.
	 */
	private void setSavedPosition() {
		myCurrentWorld.getPlayer().setFacing(
				myCurrentWorld.getSavedPlayerPosition()[2]);
		if (myCurrentWorld.getPlayer().getFacing() == 2)
			myCurrentWorld.getPlayer().setPosition(
					myCurrentWorld.getSavedPlayerPosition()[0],
					myCurrentWorld.getSavedPlayerPosition()[1] + 20);
		else if (myCurrentWorld.getPlayer().getFacing() == 0)
			myCurrentWorld.getPlayer().setPosition(
					myCurrentWorld.getSavedPlayerPosition()[0],
					myCurrentWorld.getSavedPlayerPosition()[1] - 20);
		else {
			myCurrentWorld.getPlayer().setPosition(
					myCurrentWorld.getSavedPlayerPosition()[0],
					myCurrentWorld.getSavedPlayerPosition()[1]);
		}
	}

	public void setInit(Boolean bool) {
		isInitialized = bool;
	}

	/**
	 * Do game loop. Called every frame. Repaints the world, moves all
	 * GridObjects, and checks collisions.
	 * 
	 * 
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void doGameLoop() throws InterruptedException {
		if (myCurrentWorld.getMusic() != null) {
			myCurrentWorld.getMusic().start();
		}
		while (isInitialized) {
			myCanvas.repaint();
			World newWorld = myGameLooper.doLoop();
			if (newWorld != null) {
				changeWorld(newWorld);

			}
			Thread.sleep(10);
		}
	}

	/**
	 * Gets the current world.
	 * 
	 * @return the current world
	 */
	public World getCurrentWorld() {
		return myCurrentWorld;
	}

	/**
	 * Paints the same background on every tile.
	 * 
	 * @param background
	 *            the background
	 */
	public void paintConstantBackground(String background) {
		((WalkAroundWorld) myCurrentWorld).paintFullBackround(background);
	}
}