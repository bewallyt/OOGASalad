package engine.world;

import java.util.ArrayList;
import java.util.List;

import engine.collision.CollisionHandler;
import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;

public abstract class World {
	
	private int myNumTileWidth;
	private int myNumTileHeight;
	private int myTileSize;
	private Tile[][] myTileMatrix;
	private List<GridObject> myGridObjectList;
	private Player myPlayer;
	private CollisionMatrix myCollisionMatrix;
	private int[] mySavedPlayerPosition;
	
	

	/**
	 * Instantiates a new World.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public World(int tileSize, int playWidth, int playHeight, Player p, List<GridObject> gridObjects) {
		myPlayer = p;
		myTileSize=tileSize;
		myGridObjectList = gridObjects;
		myNumTileWidth = playWidth/myTileSize;
		myNumTileHeight = playHeight/myTileSize;
		makeTileMatrix();
		myCollisionMatrix = new CollisionMatrix(myGridObjectList);
	}
	
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
		
	public Player getPlayer(){
		return myPlayer;
	}

	public CollisionMatrix getCollisionMatrix() {
		return myCollisionMatrix;
	}
	
	public void savePlayerPosition(){
		mySavedPlayerPosition = new int[] {myPlayer.getX(),myPlayer.getY(),reverseFacing()};
	}
	
	public int[] getSavedPlayerPosition(){
		return mySavedPlayerPosition;
	}
	
	private int reverseFacing(){
		if(myPlayer.getFacing()==1)return 3;
		else{
			return Math.abs(myPlayer.getFacing()-2);
		}
	}
}

