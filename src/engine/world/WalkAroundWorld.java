package engine.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.battle.Weapon;
import engine.collision.CollisionHandler;
import engine.collision.CollisionMatrix;
import engine.dialogue.InteractionBox;
import engine.dialogue.TextDisplayer;
import engine.dialogue.TransparentDisplayer;
import engine.gridobject.GridObject;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;

public class WalkAroundWorld extends World {


	private int myNumTileWidth;
	private int myNumTileHeight;
	private int myTileSize;
	private Tile[][] myTileMatrix;
	private List<GridObject> myGridObjectList;
	private CollisionMatrix myCollisionMatrix;
	private TextDisplayer myTextDisplayer;
	List<Enemy> myRandomEncounters = new ArrayList<Enemy>();

	/**
	 * Instantiates a new World.
	 *
	 * @param the width of the playfield in pixels
	 * @param the height of the playfield in pixels
	 * @param p the player in the world
	 * @param tileSize the size of each tile
	 * @param gridObjects the list of grid objects in every world
	 */
	public WalkAroundWorld(int playWidth, int playHeight, Player p,int tileSize, List<GridObject> gridObjects) {
		super(playWidth, playHeight, p);
		myNumTileWidth = playWidth/tileSize;
		myNumTileHeight = playHeight/tileSize;
		myGridObjectList = gridObjects;
		myTileSize=tileSize;
		makeTileMatrix();
		myCollisionMatrix = new CollisionMatrix(myGridObjectList);
		myTextDisplayer = new TextDisplayer(new TransparentDisplayer());

	}

	/**
	 * Sets a new collision handler.
	 *
	 * @param handler the handler
	 * @param x the x coordinate of the collision matrix
	 * @param y the y coordinate of the collision matrix
	 */
	public void setCollisionHandler(CollisionHandler handler, int x, int y) {
		myCollisionMatrix.setCollisionHandler(handler, x, y);
	}

	public int getTileSize(){
		return myTileSize;
	}

	public int getTileGridHeight() {
		return myNumTileHeight;
	}

	/**
	 * Returns the width of the canvas, in number of tiles
	 * 
	 * @return Returns the width of the canvas, in number of tiles
	 */
	public int getTileGridWidth() {
		return myNumTileWidth;
	}

	public void paintFullBackround(String fileName){
		for (int i = 0; i < getTileGridWidth(); i++) {
			for (int j = 0; j < getTileGridHeight(); j++) {
				getTileMatrix()[i][j].setBackgroundImage(fileName);
			}
		}
	}

	/**
	 * Make empty matrix of tiles.
	 * 
	 * @return the tile matrix
	 */
	public Tile[][] makeTileMatrix() {
		System.out.println(myNumTileWidth + " " + myNumTileHeight);
		Tile[][] tileMatrix = new Tile[myNumTileWidth][myNumTileHeight];
		for (int i = 0; i < myNumTileWidth; i++) {
			for (int j = 0; j < myNumTileHeight; j++) {
				tileMatrix[i][j] = new Tile(myTileSize,i*myTileSize,j*myTileSize);
			}
		}

		myTileMatrix = tileMatrix;

		return tileMatrix;
	}

	public List<GridObject> getGridObjectList(){
		return myGridObjectList;
	}

	public void setTileObject(GridObject obj, int xTile, int yTile) {
		myTileMatrix[xTile][yTile].setTileObject(obj);
	}

	public Tile[][] getTileMatrix() {
		return myTileMatrix;
	}

	public void setViewSize(){

	}

	public CollisionMatrix getCollisionMatrix() {
		return myCollisionMatrix;
	}

	public TextDisplayer getTextDisplayer() {
		return myTextDisplayer;
	}


	/**
	 * This method will place an InteractionBox into the TextDisplayer (container for the goods).
	 * This TextDisplayer will then be painted in every cycle.
	 * @param b InteractionBox to put into the TextDisplayer
	 */
	public void setTextDisplayer(InteractionBox b) {
		myTextDisplayer.setInteractionBox(b);
	}

	/**
	 * Adds an enemy to a world to be randomly encountered when random battles are initiated
	 *
	 * @param enemy the enemy (must be given exactly 1 weapon)
	 */
	public void addRandomEncounter(Enemy enemy){
		myRandomEncounters.add(enemy);
	}

	/**
	 * Gets a random enemy from the world's randomencounter list.
	 *
	 * @return the random enemy
	 */
	public Enemy getRandomEncounter(){
		int rand = new Random().nextInt(myRandomEncounters.size());
		return myRandomEncounters.get(rand);
	}


}