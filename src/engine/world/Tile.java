package engine.world;

import engine.gridobject.GridObject;

public class Tile {


	private int myWidth;
	private int myHeight;
	private int myX;
	private int myY;
	private GridObject myObject;


	/**
	 * Instantiates a new tile.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public Tile(int width, int height, int x, int y) {
		myWidth = width;
		myHeight = height;
		myX = x;
		myY=y;
	}
	
	
	/**
	 * Sets the tile object.
	 *
	 * @param sprite the new tile object
	 */
	public void setTileObject(GridObject obj){
		myObject = obj;
		obj.setPosition(myX, myY);
		obj.setSize(myWidth, myHeight);
		obj.setImage(obj.getImageFile());
		
	}
	
	public int[] getPosition(){
		return new int[] {myX,myY};
	}
	
	
	

}
