package engine.world;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:src/engine/world/World.java
public class World {
=======
import javax.swing.JFrame;

public class Canvas extends JFrame{
>>>>>>> 957af7ab172f0b5ad1e3ee1aabf1bb8119d433a0:src/engine/Canvas.java

	private int myNumTileWidth;
	private int myNumTileHeight;
	private double myTileWidth;
	private double myTileHeight;
	private Tile[][] myTileMatrix;

	/**
	 * Instantiates a new canvas.
	 * 
	 * @param numTiles
	 *            the number of tiles
	 */
	public World(int numTileWidth, int numTileHeight, double tileWidth,
			double tileHeight) {
		myNumTileWidth = numTileWidth;
		myNumTileHeight = numTileHeight;
		myTileHeight = tileHeight;
		myTileWidth = tileWidth;
	}

	/**
	 * Make empty matrix of tiles.
	 * 
	 * @return the tile matrix
	 */
	private Tile[][] makeTileMatrix() {
		Tile[][] tileMatrix = new Tile[myNumTileWidth][myNumTileHeight];
		for (int i = 0; i < myNumTileWidth; i++) {
			for (int j = 0; j < myNumTileHeight; i++) {
				tileMatrix[i][j] = new Tile(myTileWidth, myTileHeight);
			}
		}
		myTileMatrix = tileMatrix;
		return tileMatrix;
	}

	/**
	 * Gets the background image.
	 * 
	 * @param imageName
	 *            the image name
	 * @return the background image
	 */
	private Image getBackgroundImage(String imageName) {

	}

	public void setTileSprite(Sprite sprite, int xTile, int yTile) {
		myTileMatrix[xTile][yTile].setTileObject(sprite);
	}

	public void makeCanvas(){
		double height = myNumTileHeight * myTileHeight;
		double width = myNumTileWidth * myTileWidth;
		Image background = getBackgroundImage();
		getContentPane().setSize(width, height);
	}
}
