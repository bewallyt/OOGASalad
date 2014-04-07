package engine.world;

import java.util.ArrayList;
import java.util.List;

import engine.GameObject;
import engine.gridobject.GridObject;

public abstract class World {
	
	private int myNumTileWidth;
	private int myNumTileHeight;
	private int myTileSize;
	private Tile[][] myTileMatrix;
	private List<GridObject> myGridObjectList;
	private String myBackground;

	/**
	 * Instantiates a new World.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public World(int tileSize, String background) {
		myTileSize=tileSize;
		myGridObjectList = new ArrayList<GridObject>();
		myBackground = background;
//		Image myBackground = new ScaledImage(width, height,myBackground).scaleImage();
	}
	
	public void setDimensions(int width, int height){
		myNumTileWidth = width/myTileSize;
		myNumTileHeight = height/myTileSize;
		makeTileMatrix();
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
	
	public String getBackgroundString() {
		return myBackground;

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
		myGridObjectList.add(obj);
	}
	
	public Tile[][] getTileMatrix() {
		return myTileMatrix;
	}
	
	public void setViewSize(){
		
	}

}
