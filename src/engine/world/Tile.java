package engine.world;

public class Tile {

<<<<<<< HEAD:src/engine/world/Tile.java
	private double myWidth;
	private double myHeight;
	private Sprite mySprite;
=======
	private double myX;
	private double myY;
	
>>>>>>> dc5c7dc7c8e1effb9b2db3845253afc3906c305f:src/engine/Tile.java

	/**
	 * Instantiates a new tile.
	 *
	 * @param x the width of the tile.
	 * @param y the height of the tile
	 */
	public Tile(double x, double y) {
		myWidth = x;
		myHeight = y;
	}
	
	
	/**
	 * Sets the tile object.
	 *
	 * @param sprite the new tile object
	 */
	public void setTileObject(Sprite sprite){
		mySprite = sprite;
	}
	
	
	

}
